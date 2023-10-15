package br.com.carteira.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.carteira.api.controller.HistoricoConrtoller;
import br.com.carteira.api.model.dto.HistoricoDto;
import br.com.carteira.api.model.dto.HistoricoResumoDto;
import br.com.carteira.domain.model.Historico;

@Component
public class HistoricoResumoDtoAssembler extends RepresentationModelAssemblerSupport<Historico, HistoricoResumoDto>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	public HistoricoResumoDtoAssembler() {
		super(HistoricoConrtoller.class, HistoricoResumoDto.class);
	}
	
	@Override
	public HistoricoResumoDto toModel(Historico historico) {
		HistoricoResumoDto historicoResumoDto = instantiateModel(historico);
		modelMapper.map(historico,  historicoResumoDto);
		
		return historicoResumoDto;
	}
	
	@Override
	public CollectionModel<HistoricoResumoDto> toCollectionModel(Iterable<? extends Historico> entities){
		
		CollectionModel<HistoricoResumoDto> collectionModel = super.toCollectionModel(entities);
		
		return collectionModel;
	}
}
