package br.com.carteira.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carteira.api.model.form.CarteiraForm;
import br.com.carteira.domain.model.Carteira;

@Component
public class CarteiraFormDisassembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public Carteira toDomainObject(CarteiraForm carteiraForm) {
		return modelMapper.map(carteiraForm, Carteira.class);
	}
	
	public void copyToDomainObject(CarteiraForm carteiraForm, Carteira carteira) {
		modelMapper.map(carteiraForm, carteira);
	}
}
