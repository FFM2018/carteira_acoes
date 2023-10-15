package br.com.carteira.domain.exception;

public class HistoricoNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public HistoricoNaoEncontradaException(String mensagem) {
		// TODO Auto-generated constructor stub	
		super(mensagem);		
	}
	
	public HistoricoNaoEncontradaException(Long historicoId) {
		this(String.format("Não existe um cadastro de empresa com o código %d", historicoId));
	}
}
