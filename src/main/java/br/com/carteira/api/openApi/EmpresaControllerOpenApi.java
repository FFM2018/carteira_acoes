package br.com.carteira.api.openApi;

import java.util.List;

import org.springframework.hateoas.CollectionModel;

import br.com.carteira.api.exceptionhandler.Problem;
import br.com.carteira.api.model.dto.EmpresaDto;
import br.com.carteira.api.model.form.EmpresaForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Empresas")
public interface EmpresaControllerOpenApi {
	@ApiOperation("Lista as empresas")
	CollectionModel<EmpresaDto> listar();

//	@ApiOperation("Busca um estado por ID")
//	@ApiResponses({
//		@ApiResponse(code = 400, message = "ID da empresa inválido", response = Problem.class),
//		@ApiResponse(code = 404, message = "Empresa não encontrada", response = Problem.class)
//	})
//	EmpresaDto buscar(
//			@ApiParam(value = "ID de uma empresa", example = "1", required = true)
//			Long empresaId);
//
	@ApiOperation("Cadastra uma empresa")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Empresa cadastrada"),
	})
	EmpresaDto adicionar(
			@ApiParam(name = "corpo", value = "Representação de uma nova empresa", required = true)
			EmpresaForm empresaForm);

	

//	EmpresaDto atualizar(
//			@ApiParam(value = "ID de uma empresa", example = "1", required = true)
//			Long empresaId,
//			
//			@ApiParam(name = "corpo", value = "Representação de uma empresa com os novos dados", required = true)
//			EmpresaForm empresaForm);
//
	@ApiOperation("Exclui um estado por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Empresa excluída"),
		@ApiResponse(code = 404, message = "Empresa não encontrada", response = Problem.class)
	})
	void remover(
			@ApiParam(value = "ID de uma cozinha", example = "1", required = true)
			Long cozinhaId);
}
