package br.com.carteira.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.carteira.domain.exception.EmpresaNaoEncontradaException;
import br.com.carteira.domain.model.Empresa;
import br.com.carteira.domain.repository.EmpresaRepository;

@Service
public class CadastroEmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Transactional
	public Empresa salvar(Empresa empresa) {
		return empresaRepository.save(empresa);
	}
	
	@Transactional
	public void excluir(Long empresaId) {
		try {
			empresaRepository.deleteById(empresaId);
			
			empresaRepository.flush();
		} catch(EmptyResultDataAccessException e) {
			throw new EmpresaNaoEncontradaException(empresaId);
		} catch(DataIntegrityViolationException e) {
			String.format("Empresa de código %d não pode ser removida, pois está em uso", empresaId);
		}
	}
}
