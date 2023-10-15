package br.com.carteira.api.model.form;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AcaoIdForm {
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;
}
