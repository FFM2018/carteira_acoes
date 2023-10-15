package br.com.carteira.api.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.carteira.domain.model.Setor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmpresaForm {

	@NotBlank
	@NotNull
	private String nome;
	@NotBlank
	@NotNull
	private String cnpj;
	
	private Setor setor;
	
}
