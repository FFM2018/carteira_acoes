package br.com.carteira.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carteira.api.model.form.HistoricoForm;
import br.com.carteira.domain.model.Historico;

@Component
public class HistoricoFormDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Historico toDomainObject(HistoricoForm historicoForm) {
		return modelMapper.map(historicoForm, Historico.class);
	}
	
	public void copyToDomainObject(HistoricoForm historicoForm, Historico historico) {
		modelMapper.map(historicoForm, historico);
	}
}
