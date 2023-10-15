package br.com.carteira.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.carteira.api.assembler.AcaoDtoAssembler;
import br.com.carteira.api.assembler.AcaoFormDisassembler;
import br.com.carteira.api.model.dto.AcaoDto;
import br.com.carteira.api.model.form.AcaoForm;
import br.com.carteira.domain.model.Acao;
import br.com.carteira.domain.repository.AcaoRepository;
import br.com.carteira.domain.service.CadastroAcaoService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/acao")
public class AcaoController {

	private final CadastroAcaoService cadastroAcaoService;
	
	private final AcaoDtoAssembler acaoDtoAssembler;
	
	private final AcaoFormDisassembler  acaoFormDisassembler;	
	
	private final AcaoRepository acaoRepository;
	
	@GetMapping
	public List<AcaoDto> listar() {
		List<Acao> acoes =  acaoRepository.findAll();
		
		return acaoDtoAssembler.toCollectionModel(acoes);
	}
	
	
	@GetMapping("/{acaoId}")
	public AcaoDto buscar(@PathVariable Long acaoId) {
		Acao acao = cadastroAcaoService.buscar(acaoId);
		
		return acaoDtoAssembler.toModel(acao);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AcaoDto adicionar(@RequestBody AcaoForm acaoForm) {
		Acao acao = acaoFormDisassembler.toDomainObject(acaoForm);
		
		//Long empresaId = acao.getEmpresa().getId();
		
		//Empresa empresa = cadastroEmpresaService.find(empresaId);
		
		//acao.setEmpresa(empresa);
		
		cadastroAcaoService.adicionar(acao);
		
		return acaoDtoAssembler.toModel(acao);
	}
	
}
