package ao.co.intellectus.servico.exception;

public class EmailInstituicionalNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailInstituicionalNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
