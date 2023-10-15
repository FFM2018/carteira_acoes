package br.com.carteira.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carteira.api.model.dto.CarteiraDto;
import br.com.carteira.domain.model.Carteira;

@Component
public class CarteiraDtoAssembler{
	
	@Autowired
	private ModelMapper modelMapper;		
	
	
	public CarteiraDto toModel(Carteira carteira) {
		return modelMapper.map(carteira, CarteiraDto.class);
	}
	
	
	public List<CarteiraDto> toCollectionModel(List<Carteira> carteiras) {
		return carteiras.stream()
				.map(carteira -> toModel(carteira))
				.collect(Collectors.toList());
	}
	

}
