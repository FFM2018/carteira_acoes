package br.com.carteira.domain.service;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.carteira.domain.exception.CarteiraNaoEncontradaException;
import br.com.carteira.domain.exception.EntidadeEmUsoException;
import br.com.carteira.domain.model.Carteira;
import br.com.carteira.domain.model.Empresa;
import br.com.carteira.domain.repository.CarteiraRepository;

@Service
public class CarteiraService {	
	
	private final CarteiraRepository carteiraRepository;
	private final CadastroEmpresaService cadastroEmpresaService;
	private final CadastroAcaoService cadastroAcaoService;
	@Transactional
	public Carteira save(Carteira carteira) {	
		return carteiraRepository.save(carteira);
	}
	
	public Carteira find(Long carteiraId) {
		return carteiraRepository.findById(carteiraId).orElseThrow(() -> new CarteiraNaoEncontradaException(carteiraId));
	}
	
	@Transactional
	public void remove(Long cateiraId) {
		try {
			carteiraRepository.deleteById(cateiraId);
			
			// Descarrega todas as operações pendentes
			carteiraRepository.flush();
			
		} catch(EmptyResultDataAccessException e) {
			throw new CarteiraNaoEncontradaException(cateiraId);	
			
		} catch(DataIntegrityViolationException e) {			
			throw new EntidadeEmUsoException(
					String.format("Carteira de código %d não pode ser removida, pois está em uso", cateiraId));			
		}	
	}

	public CarteiraService(CarteiraRepository carteiraRepository, CadastroEmpresaService cadastroEmpresaService, CadastroAcaoService cadastroAcaoService) {		
		this.carteiraRepository = carteiraRepository;
		this.cadastroEmpresaService = cadastroEmpresaService;
		this.cadastroAcaoService = cadastroAcaoService;
	}
	
	
}
