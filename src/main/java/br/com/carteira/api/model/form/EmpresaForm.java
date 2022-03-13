package br.com.carteira.api.model.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpresaForm {

	@NotBlank
	private String nome;
}
