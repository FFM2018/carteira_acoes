package br.com.carteira.api.model.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "carteira")
@Getter
@Setter
public class CarteiraDto extends RepresentationModel<CarteiraDto>{
	private Long id;
	private Long quantidade;	
	private AcaoDto acao;
	
	
}
