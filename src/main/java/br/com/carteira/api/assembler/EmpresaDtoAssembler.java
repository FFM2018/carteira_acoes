package br.com.carteira.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.carteira.api.controller.EmpresaController;
import br.com.carteira.api.model.dto.EmpresaDto;
import br.com.carteira.domain.model.Empresa;

@Component
public class EmpresaDtoAssembler extends RepresentationModelAssemblerSupport<Empresa, EmpresaDto>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	public EmpresaDtoAssembler() {
		super(EmpresaController.class, EmpresaDto.class);
	}
	
	@Override
	public EmpresaDto toModel(Empresa empresa) {
		EmpresaDto empresaDto = instantiateModel(empresa);
		modelMapper.map(empresa,  empresaDto);
		
		return empresaDto;
	}
	
	@Override
	public CollectionModel<EmpresaDto> toCollectionModel(Iterable<? extends Empresa> entities){
		
		CollectionModel<EmpresaDto> collectionModel = super.toCollectionModel(entities);
		
		return collectionModel;
	}
}
