package br.com.carteira.api.model.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
public class HistoricoForm {
	@CreationTimestamp
	private OffsetDateTime dataCriacao;
	private BigDecimal valorUnidade;
	private Character operacao;
	private AcaoIdForm acao;
	private Long quantidade;
	//private Long corretoraId;
}
