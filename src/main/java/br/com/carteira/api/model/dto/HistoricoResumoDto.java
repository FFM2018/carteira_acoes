package br.com.carteira.api.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "historicos")
@Getter
@Setter
public class HistoricoResumoDto extends RepresentationModel<HistoricoResumoDto>{
	private Long id;
	private BigDecimal valorUnidade;
	private Long quantidade;
	private Character operacao;
	private AcaoResumoDto acao;
	
	//private Long corretoraId;
	
	
}
