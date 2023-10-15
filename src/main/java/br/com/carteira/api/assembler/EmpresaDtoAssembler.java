package br.com.carteira.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import br.com.carteira.api.model.dto.EmpresaDto;
import br.com.carteira.domain.model.Empresa;

@Component
public class EmpresaDtoAssembler{
	
	private final ModelMapper modelMapper;
	
	public EmpresaDto toModel(Empresa empresa) {
		return modelMapper.map(empresa, EmpresaDto.class);
	}
	

	public List<EmpresaDto> toCollectionModel(List<Empresa> empresas) {
		return empresas.stream()
				.map(empresa -> toModel(empresa))
				.collect(Collectors.toList());
	}

	public EmpresaDtoAssembler(ModelMapper modelMapper) {		
		this.modelMapper = modelMapper;
	}
	
	
}
