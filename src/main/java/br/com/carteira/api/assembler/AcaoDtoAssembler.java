package br.com.carteira.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.carteira.api.model.dto.AcaoDto;
import br.com.carteira.domain.model.Acao;

@Component
public class AcaoDtoAssembler{
		
	private final ModelMapper modelMapper;	

	public AcaoDto toModel(Acao acao) {	
		return modelMapper.map(acao,  AcaoDto.class);		
	}
	

	public List<AcaoDto> toCollectionModel(List<Acao> acoes) {
		return acoes.stream()
				.map(acao -> toModel(acao))
				.collect(Collectors.toList());
	}


	public AcaoDtoAssembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
}
