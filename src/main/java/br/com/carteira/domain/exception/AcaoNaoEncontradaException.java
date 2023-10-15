package br.com.carteira.domain.exception;

public class AcaoNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;
	
	public AcaoNaoEncontradaException(String mensagem) {
		// TODO Auto-generated constructor stub	
		super(mensagem);		
	}
	
	public AcaoNaoEncontradaException(Long empresaId) {
		this(String.format("Não existe um cadastro da ação com o código %d", empresaId));
	}
}
