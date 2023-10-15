package br.com.carteira.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.carteira.api.assembler.CarteiraDtoAssembler;
import br.com.carteira.api.assembler.CarteiraFormDisassembler;
import br.com.carteira.api.model.dto.CarteiraDto;
import br.com.carteira.api.model.form.AcaoForm;
import br.com.carteira.api.model.form.CarteiraForm;
import br.com.carteira.api.openApi.CarteiraControllerOpenApi;
import br.com.carteira.domain.model.Acao;
import br.com.carteira.domain.model.Carteira;
import br.com.carteira.domain.repository.CarteiraRepository;
import br.com.carteira.domain.service.CadastroAcaoService;
import br.com.carteira.domain.service.CarteiraService;


@RestController
@RequestMapping(path = "api/carteira")
public class CarteiraConrtoller implements CarteiraControllerOpenApi{	
	
	CarteiraDtoAssembler carteiraDtoAssembler;		
	
	CarteiraRepository carteiraRepository;
	
	CarteiraService carteiraService;
	
	CarteiraFormDisassembler carteiraFormDisassembler;
	
	CadastroAcaoService cadastroAcaoService;
	
	@GetMapping
	public List<CarteiraDto> listar(){
		System.out.println("Passou aqui!!!");
		List<Carteira> carteiraList =  carteiraRepository.findAll();		
		
		return carteiraDtoAssembler.toCollectionModel(carteiraList);
	}

	@Override
	@GetMapping("/{carteiraId}")
	public CarteiraDto find(@PathVariable Long carteiraId) {
		Carteira carteira =  carteiraService.find(carteiraId);
		
		return carteiraDtoAssembler.toModel(carteira);
	}
	
	@GetMapping("/teste")
	public String teste() {
		return "FOiiiiiiii";
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CarteiraDto add(@RequestBody @Valid CarteiraForm carteiraForm) {
		Carteira carteira = carteiraFormDisassembler.toDomainObject(carteiraForm);		
		
		carteira = carteiraService.save(carteira);
		return carteiraDtoAssembler.toModel(carteira);

	}

	@Override
	@PutMapping("/{carteiraId}")
	public CarteiraDto update(@PathVariable Long carteiraId, @RequestBody @Valid CarteiraForm carteiraForm) {
		
		
		Carteira carteira =  carteiraService.find(carteiraId);
		carteiraFormDisassembler.copyToDomainObject(carteiraForm, carteira);
		Acao acao = cadastroAcaoService.buscar(carteiraForm.getAcao().getId());
				
		carteira.setAcao(acao);
		
		carteira = carteiraService.save(carteira);
		
		return carteiraDtoAssembler.toModel(carteira);
	}

	@Override
	@DeleteMapping("/{carteiraId}")
	public CarteiraDto remove(@PathVariable Long carteiraId) {
		Carteira carteira =  carteiraService.find(carteiraId);
		carteiraService.remove(carteiraId);
		return carteiraDtoAssembler.toModel(carteira);
	}

	
	public CarteiraConrtoller(CarteiraDtoAssembler carteiraDtoAssembler, CarteiraRepository carteiraRepository,
			CarteiraService carteiraService, CarteiraFormDisassembler carteiraFormDisassembler,
			CadastroAcaoService cadastroAcaoService) {
		
		this.carteiraDtoAssembler = carteiraDtoAssembler;
		this.carteiraRepository = carteiraRepository;
		this.carteiraService = carteiraService;
		this.carteiraFormDisassembler = carteiraFormDisassembler;
		this.cadastroAcaoService = cadastroAcaoService;
	}

}
