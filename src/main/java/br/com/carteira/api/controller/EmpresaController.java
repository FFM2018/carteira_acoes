package br.com.carteira.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import br.com.carteira.api.assembler.EmpresaDtoAssembler;
import br.com.carteira.api.assembler.EmpresaFormDisassembler;
import br.com.carteira.api.model.dto.EmpresaDto;
import br.com.carteira.api.model.form.EmpresaForm;
import br.com.carteira.api.openApi.EmpresaControllerOpenApi;
import br.com.carteira.domain.model.Empresa;
import br.com.carteira.domain.service.CadastroEmpresaService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/empresas", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmpresaController implements EmpresaControllerOpenApi{
	
	@Autowired
	private CadastroEmpresaService cadastroEmpresaService;
	
	@Autowired
	private EmpresaFormDisassembler empresaFormDisassebler;	
	
	@Autowired
	private EmpresaDtoAssembler empresaDtoAssembler;
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmpresaDto adicionar(@RequestBody @Valid EmpresaForm empresaForm) {
		Empresa empresa = empresaFormDisassebler.toDomainObject(empresaForm);
		
		empresa = cadastroEmpresaService.salvar(empresa);
		
		return empresaDtoAssembler.toModel(empresa);
	}	
	

	@Override
	@DeleteMapping("/{empresaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long empresaId) {
		cadastroEmpresaService.excluir(empresaId);
		
	}
}
