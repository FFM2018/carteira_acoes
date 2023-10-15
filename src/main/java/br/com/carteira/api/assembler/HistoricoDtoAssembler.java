package br.com.carteira.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.carteira.api.controller.HistoricoConrtoller;
import br.com.carteira.api.model.dto.HistoricoDto;
import br.com.carteira.domain.model.Historico;

@Component
public class HistoricoDtoAssembler extends RepresentationModelAssemblerSupport<Historico, HistoricoDto>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	public HistoricoDtoAssembler() {
		super(HistoricoConrtoller.class, HistoricoDto.class);
	}
	
	@Override
	public HistoricoDto toModel(Historico historico) {
		HistoricoDto historicoDto = instantiateModel(historico);
		modelMapper.map(historico,  historicoDto);
		
		return historicoDto;
	}
	
	@Override
	public CollectionModel<HistoricoDto> toCollectionModel(Iterable<? extends Historico> entities){
		
		CollectionModel<HistoricoDto> collectionModel = super.toCollectionModel(entities);
		
		return collectionModel;
	}
}
