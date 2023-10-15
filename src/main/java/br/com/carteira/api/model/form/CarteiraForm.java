package br.com.carteira.api.model.form;

import javax.validation.constraints.NotBlank;

import br.com.carteira.domain.model.Acao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarteiraForm {
	
	
	private Acao acao;
	
	private Long quantidade;
}
