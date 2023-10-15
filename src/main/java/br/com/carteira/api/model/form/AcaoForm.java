package br.com.carteira.api.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.carteira.domain.model.Empresa;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
public class AcaoForm {
	
	private String nome;
	
	//@NotNull
	private Empresa empresa;
}
