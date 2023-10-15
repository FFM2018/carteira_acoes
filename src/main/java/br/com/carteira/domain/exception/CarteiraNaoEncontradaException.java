package br.com.carteira.domain.exception;

public class CarteiraNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;
	
	public CarteiraNaoEncontradaException(String mensagem) {
		// TODO Auto-generated constructor stub	
		super(mensagem);		
	}
	
	public CarteiraNaoEncontradaException(Long empresaId) {
		this(String.format("Não existe um cadastro da carteira com o código %d", empresaId));
	}
}
