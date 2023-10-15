package br.com.carteira.api.openApi;

import br.com.carteira.api.exceptionhandler.Problem;
import br.com.carteira.api.model.dto.CarteiraDto;
import br.com.carteira.api.model.form.CarteiraForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Carteira")
public interface CarteiraControllerOpenApi {
//	@ApiOperation("Lista as empresas")
//	CollectionModel<EmpresaDto> listar();

	@ApiOperation("Busca um ação por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da carteira é inválida", response = Problem.class),
		@ApiResponse(code = 404, message = "Carteira não encontrada", response = Problem.class)
	})
	CarteiraDto find(
			@ApiParam(value = "ID de uma carteira", example = "1", required = true)
			Long carteiraid);

	@ApiOperation("Cadastra uma ação carteira")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Carteira Cadastrada"),
	})
	CarteiraDto add(
			@ApiParam(name = "corpo", value = "Representação de um nova ação na carteira", required = true)
			CarteiraForm carteiraForm);
	
	@ApiOperation("Atualiza uma carteira por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Carteira atualizada"),
		@ApiResponse(code = 404, message = "Carteira não encontrada", response = Problem.class)
	})
	CarteiraDto update(
			@ApiParam(value = "ID de uma carteira", example = "1", required = true)
			Long carteiraId,
			
			@ApiParam(name = "corpo", value = "Representação de uma carteira com os novos dados", required = true)
			CarteiraForm carteiraForm);
	
	@ApiOperation("Exclui uma carteira por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Carteira excluída"),
		@ApiResponse(code = 404, message = "Carteira não encontrada", response = Problem.class)
	})
	CarteiraDto remove(
			@ApiParam(value = "ID de uma carteira", example = "1", required = true)
			Long carteiraId);
}
