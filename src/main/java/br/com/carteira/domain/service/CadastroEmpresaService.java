package br.com.carteira.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import br.com.carteira.domain.exception.EntidadeEmUsoException;
import br.com.carteira.domain.exception.NegocioException;

import br.com.carteira.domain.exception.EmpresaNaoEncontradaException;
import br.com.carteira.domain.model.Empresa;
import br.com.carteira.domain.model.Setor;
import br.com.carteira.domain.repository.EmpresaRepository;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CadastroEmpresaService {	
	
	private final EmpresaRepository empresaRepository;
	
	@Transactional
	public Empresa save(Empresa empresa) {		
		
		Optional<Empresa> empresaPesquisa = empresaRepository.findByCnpj(empresa.getCnpj());
		
		if(empresaPesquisa.isPresent() && !empresaPesquisa.get().equals(empresa)) {
			throw new NegocioException(
					String.format("Já uma empresa cadastrada com este cnpj", empresa.getCnpj()));
		}
		
		return empresaRepository.save(empresa);
	}
	
	@Transactional
	public void remove(Long empresaId) {
		try {
			empresaRepository.deleteById(empresaId);
			
			empresaRepository.flush();
		} catch(EmptyResultDataAccessException e) {
			throw new EmpresaNaoEncontradaException(empresaId);
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removido, pois está em uso", empresaId));
		}
	}

	public Empresa find(Long empresaId) {

		return empresaRepository.findById(empresaId).orElseThrow(() -> new EmpresaNaoEncontradaException(empresaId));
	}
	
	public List<Empresa> getAllEmpresa(){
		return empresaRepository.findAll();
	}
	
	public List<Setor> getAllSetor(){
		return empresaRepository.getAllSetor();
	}
}
