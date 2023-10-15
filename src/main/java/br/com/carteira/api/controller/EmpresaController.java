package br.com.carteira.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.carteira.api.assembler.EmpresaDtoAssembler;
import br.com.carteira.api.assembler.EmpresaFormDisassembler;
import br.com.carteira.api.model.dto.EmpresaDto;
import br.com.carteira.api.model.form.EmpresaForm;
import br.com.carteira.domain.model.Empresa;
import br.com.carteira.domain.model.Setor;
import br.com.carteira.domain.service.CadastroEmpresaService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/empresa")
public class EmpresaController {
	
	private final CadastroEmpresaService cadastroEmpresaService;	
	
	private final EmpresaFormDisassembler empresaFormDisassebler;	
	
	private final EmpresaDtoAssembler empresaDtoAssembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmpresaDto add(@RequestBody @Valid EmpresaForm empresaForm) {
		
		Empresa empresa = empresaFormDisassebler.toDomainObject(empresaForm);
		
		empresa = cadastroEmpresaService.save(empresa);
		
		return empresaDtoAssembler.toModel(empresa);
	}	
	
	
	@GetMapping
	public List<EmpresaDto> getAllEmpresa(){
		 List<Empresa> empresas = cadastroEmpresaService.getAllEmpresa();
		    List<EmpresaDto> listaEmpresas = empresaDtoAssembler.toCollectionModel(empresas);
		    return listaEmpresas;
	}
	

	@GetMapping("/{empresaId}")
	public EmpresaDto getById(@PathVariable Long empresaId) {
		Empresa empresa = cadastroEmpresaService.find(empresaId);
		
		return empresaDtoAssembler.toModel(empresa);
	}
	
	@DeleteMapping("/{empresaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long empresaId) {
		cadastroEmpresaService.remove(empresaId);
		
	}
	
	@GetMapping("/setores")
	public List<Setor> getAllSetor() {
		List<Setor> setor = new ArrayList<Setor>();
		setor.addAll(cadastroEmpresaService.getAllSetor());		
		
		return setor;
	}
}
