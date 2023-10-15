package br.com.carteira.api.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Transient;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "historico")
@Getter
@Setter
public class HistoricoDto extends RepresentationModel<HistoricoDto>{
	private Long id;
	private String preco;
	private Long quantidade;
	private Character operacao;
	private AcaoResumoDto acao;
	private String taxa;
	private String totalComTaxa;
	private Long totalCotas;
	private String precoMedio;
	//private Long corretoraId;
	
	
}
