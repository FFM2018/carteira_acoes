package br.com.carteira.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.carteira.api.assembler.HistoricoDtoAssembler;
import br.com.carteira.api.assembler.HistoricoFormDisassembler;
import br.com.carteira.api.assembler.HistoricoResumoDtoAssembler;
import br.com.carteira.api.model.dto.HistoricoDto;
import br.com.carteira.api.model.dto.HistoricoResumoDto;
import br.com.carteira.api.model.form.HistoricoForm;
import br.com.carteira.api.openApi.HistoricoControllerOpenApi;
import br.com.carteira.domain.model.Acao;
import br.com.carteira.domain.model.Historico;
import br.com.carteira.domain.repository.HistoricoRepository;
import br.com.carteira.domain.service.CadastroAcaoService;
import br.com.carteira.domain.service.CadastroHistoricoService;

@RestController
@RequestMapping(path = "api/historico", produces = MediaType.APPLICATION_JSON_VALUE)
public class HistoricoConrtoller{

	@Autowired
	CadastroHistoricoService cadastrarHistoricoService;
	
	@Autowired
	HistoricoFormDisassembler historicoFormDisassebler;
	
	@Autowired
	HistoricoDtoAssembler historicoDtoAssembler;
	
	@Autowired
	CadastroAcaoService cadastoAcaoService;
	
	@Autowired
	HistoricoRepository historicoRepository;
	
	@Autowired
	HistoricoResumoDtoAssembler historicoResumoDtoAssembler;
	
//	@Override	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public HistoricoDto adicionar(@RequestBody @Valid HistoricoForm historicoForm) {
//		Historico historico = historicoFormDisassebler.toDomainObject(historicoForm);
//		
//		Long acaoId = historico.getAcao().getId();
//		
//        Acao acao = cadastoAcaoService.buscar(acaoId);
//        
//        historico.setAcao(acao);
//		
//		historico = cadastrarHistoricoService.salvar(historico);
//		
//		return historicoDtoAssembler.toModel(historico);
//	}
	
	//@Override
	@GetMapping
	public List<Historico> listar(){
		List<Historico> historicoList = new ArrayList<Historico>();
		 historicoList =  cadastrarHistoricoService.listar();
		
		for (Historico historico : historicoList) {
	        //System.out.println(historico);	        
	        // Imprima outros detalhes relevantes aqui
	    }
		return (List<Historico>) historicoList;
		//return historicoDtoAssembler.toCollectionModel(historicoList);
	}
	
	private Double calcularPrecoMedio() {
		
		
		return null;
	}
	
	@GetMapping("/listar-resumo")
	public CollectionModel<HistoricoResumoDto> listarResumo(){
		List<Historico> historico = historicoRepository.findAllByOrderByAcaoIdDesc();
		
		return historicoResumoDtoAssembler.toCollectionModel(historico);
	}
}
