package br.com.carteira.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Historico {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private OffsetDateTime data;
	
	@Column(name = "preco")
	private BigDecimal valorUnidade;
	
	private Character operacao;
	
	@ManyToOne
	@JoinColumn(name = "acao_id", nullable = false)
	private Acao acao;
	
	private int quantidade;
	
	@Column(name = "taxa")
	private BigDecimal valorTaxa;
	
	@Transient
	private BigDecimal valorTotalComTaxa;
	
	@Transient
	private int totalCotas;
	
	@Transient
	private BigDecimal precoMedio;
//	@Column(name = "corretora_id")
//	private Long corretoraId;
}
