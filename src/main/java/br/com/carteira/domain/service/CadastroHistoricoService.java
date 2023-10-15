package br.com.carteira.domain.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carteira.domain.exception.AcaoNaoEncontradaException;
import br.com.carteira.domain.exception.HistoricoNaoEncontradaException;
import br.com.carteira.domain.model.Acao;
import br.com.carteira.domain.model.Historico;
import br.com.carteira.domain.repository.AcaoRepository;
import br.com.carteira.domain.repository.HistoricoRepository;

import org.apache.commons.math3.util.Precision;

@Service
public class CadastroHistoricoService {

	@Autowired
	HistoricoRepository historicoRepository;
	
	public Historico buscar(Long acaoId) {	
		
		return historicoRepository.findById(acaoId).orElseThrow(() -> new HistoricoNaoEncontradaException(acaoId));
	}
	
	@Transactional
	public Historico salvar(Historico historico) {
		return historicoRepository.save(historico);
	}
	
	public List<Historico> listar() {
	    List<Historico> lista = historicoRepository.findAll();
	    
	   //lista.stream().forEach(System.out::println);
	    
	    // Separar históricos por ID de ação
	    Map<Long, List<Historico>> historicoPorIdAcao = lista.stream()
	            .collect(Collectors.groupingBy(h -> h.getAcao().getId()));
	    
	    

	    // Somar total de cotas para cada ID de ação
	    historicoPorIdAcao = somarTotalCotas(historicoPorIdAcao);

	    // Somar valor total de compra para cada ID de ação
	    historicoPorIdAcao = somarValorTotalCompra(historicoPorIdAcao);

	    // Calcular o preço médio para cada ID de ação
	    historicoPorIdAcao = somarPrecoMedio(historicoPorIdAcao);
	    
	    historicoPorIdAcao.forEach((idAcao, historicos) -> {
	        System.out.println("Históricos para ação com ID " + idAcao + ":");
	        historicos.forEach(System.out::println);
	    });

	    return lista;
	}

	private Map<Long, List<Historico>> somarTotalCotas(Map<Long, List<Historico>> historicoMap) {
		System.out.println("Valores do mapa antes das manipulações:");
	    historicoMap.forEach((key, value) -> {
	        System.out.println("Chave: " + key);
	        System.out.println("Valores: ");
	        value.forEach(System.out::println);
	    });
	    return historicoMap.entrySet()
	            .stream()
	            .collect(Collectors.toMap(
	                    Map.Entry::getKey,
	                    entry -> entry.getValue().stream()
	                            .peek(historico -> historico.setTotalCotas(
	                                    entry.getValue().subList(0, entry.getValue().indexOf(historico) + 1)
	                                            .stream()
	                                            .mapToInt(Historico::getQuantidade)
	                                            .sum()))
	                            .collect(Collectors.toList())
	            ));
	}

	private Map<Long, List<Historico>> somarValorTotalCompra(Map<Long, List<Historico>> historicoPorIdAcao) {
	    return historicoPorIdAcao.entrySet()
	            .stream()
	            .collect(Collectors.toMap(
	                    Map.Entry::getKey,
	                    entry -> entry.getValue().stream()
	                            .peek(historico -> historico.setValorTotalComTaxa(
	                                    historico.getValorUnidade()
	                                            .multiply(BigDecimal.valueOf(historico.getQuantidade()))
	                                            .add(historico.getValorTaxa())))
	                            .collect(Collectors.toList())
	            ));
	}

	private Map<Long, List<Historico>> somarPrecoMedio(Map<Long, List<Historico>> historicoPorIdAcao) {
		Map<Long, List<Historico>> resultado = new HashMap<>();

		for (Map.Entry<Long, List<Historico>> entry : historicoPorIdAcao.entrySet()) {
			Long idAcao = entry.getKey();
		    List<Historico> historicosCorrespondentes = entry.getValue();
		    List<Historico> historicosComPrecoMedio = new ArrayList<>();
		    BigDecimal valorTotalPorCompraBigDecimal = new BigDecimal(0);
		    for (Historico historico : historicosCorrespondentes) {
		    	valorTotalPorCompraBigDecimal = valorTotalPorCompraBigDecimal.add(new BigDecimal(historico.getValorTotalComTaxa().toString()));
		        BigDecimal totalCotasBigDecimal = new BigDecimal(historico.getTotalCotas());

		        // Calculate the average price with two decimal places precision using divide and ROUND_HALF_UP
		        BigDecimal precoMedio = valorTotalPorCompraBigDecimal.divide(totalCotasBigDecimal, 2, RoundingMode.HALF_UP);
		        
		        // Set the calculated average price back to the Historico object
		        historico.setPrecoMedio(precoMedio);
		        System.out.println(historico);
		        historicosComPrecoMedio.add(historico);
		    }

		    resultado.put(idAcao, historicosComPrecoMedio);
		}
		
		return resultado;
	}


}
