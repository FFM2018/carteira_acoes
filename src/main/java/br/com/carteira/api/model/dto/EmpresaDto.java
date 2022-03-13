package br.com.carteira.api.model.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Setter;
import lombok.Getter;

@Relation(collectionRelation = "empresas")
@Getter
@Setter
public class EmpresaDto extends RepresentationModel<EmpresaDto>{
	private Long id;
	private String nome;
}
