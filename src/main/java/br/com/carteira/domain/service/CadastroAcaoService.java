package br.com.carteira.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carteira.domain.exception.AcaoNaoEncontradaException;
import br.com.carteira.domain.model.Acao;
import br.com.carteira.domain.repository.AcaoRepository;

@Service
public class CadastroAcaoService {

	@Autowired
	AcaoRepository acaoRepository;
	
	public Acao buscar(Long acaoId) {	
		
		return acaoRepository.findById(acaoId).orElseThrow(() -> new AcaoNaoEncontradaException(acaoId));
	}
	
	
	@Transactional
	public Acao adicionar(Acao acao) {
		return acaoRepository.save(acao);
	}
	
}
