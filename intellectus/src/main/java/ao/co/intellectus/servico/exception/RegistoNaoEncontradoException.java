package ao.co.intellectus.servico.exception;

public class RegistoNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RegistoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
