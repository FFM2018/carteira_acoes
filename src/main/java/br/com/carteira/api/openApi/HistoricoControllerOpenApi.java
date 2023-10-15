package br.com.carteira.api.openApi;

import org.springframework.hateoas.CollectionModel;

import br.com.carteira.api.exceptionhandler.Problem;
import br.com.carteira.api.model.dto.HistoricoDto;
import br.com.carteira.api.model.form.HistoricoForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Historicos")
public interface HistoricoControllerOpenApi {
	@ApiOperation("Lista o historico")
	CollectionModel<HistoricoDto> listar();

//	@ApiOperation("Busca um ação por ID")
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID da ação é inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Ação não encontrada", response = Problem.class)
//	})
//	AcaoDto buscar(
//			@ApiParam(value = "ID de uma ação", example = "1", required = true)
//			Long acaoId);

	@ApiOperation("Cadastra um historico")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Historico cadastrado"),
	})
	HistoricoDto adicionar(
			@ApiParam(name = "corpo", value = "Representação de um novo historico", required = true)
			HistoricoForm historicoForm);

	

//	EmpresaDto atualizar(
//			@ApiParam(value = "ID de uma empresa", example = "1", required = true)
//			Long empresaId,
//			
//			@ApiParam(name = "corpo", value = "Representação de uma empresa com os novos dados", required = true)
//			EmpresaForm empresaForm);
//
	
//	@ApiOperation("Exclui um estado por ID")
//	@ApiResponses({
//		@ApiResponse(code = 204, message = "Empresa excluída"),
//		@ApiResponse(code = 404, message = "Empresa não encontrada", response = Problem.class)
//	})
//	void remover(
//			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
//			Long cozinhaId);
}
