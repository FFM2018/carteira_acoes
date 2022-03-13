package br.com.carteira.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.carteira.api.model.form.EmpresaForm;
import br.com.carteira.domain.model.Empresa;

@Component
public class EmpresaFormDisassembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Empresa toDomainObject(EmpresaForm empresaForm) {
		return modelMapper.map(empresaForm, Empresa.class);
	}
	
	public void copyToDomainObject(EmpresaForm empresaForm, Empresa empresa) {
		modelMapper.map(empresaForm, empresa);
	}
}
