package br.com.carteira.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carteira.api.model.form.AcaoForm;
import br.com.carteira.domain.model.Acao;
import br.com.carteira.domain.model.Empresa;

@Component
public class AcaoFormDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Acao toDomainObject(AcaoForm acaoForm) {
		return modelMapper.map(acaoForm, Acao.class);
	}
	
	public void copyToDomainObject(AcaoForm acaoForm, Acao acao) {
		
		acao.setEmpresa(new Empresa());
		
		modelMapper.map(acaoForm, acao);
	}
}
