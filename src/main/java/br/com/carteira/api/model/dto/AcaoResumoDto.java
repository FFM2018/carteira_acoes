package br.com.carteira.api.model.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "Ações")
@Getter
@Setter
public class AcaoResumoDto  extends RepresentationModel<AcaoResumoDto>{
	private Long id;
	private String nome;
	
}
