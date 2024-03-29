package br.com.carteira.domain.exception;

public class EmpresaNaoEncontradaException extends EntidadeNaoEncontradaException{
	
	private static final long serialVersionUID = 1L;
	
	public EmpresaNaoEncontradaException(String mensagem) {
		// TODO Auto-generated constructor stub	
		super(mensagem);		
	}
	
	public EmpresaNaoEncontradaException(Long empresaId) {
		this(String.format("Não existe um cadastro de empresa com o código %d", empresaId));
	}
}
