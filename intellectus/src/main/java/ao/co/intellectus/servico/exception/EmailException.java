package ao.co.intellectus.servico.exception;

public class EmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailException(String mensagem) {
		super(mensagem);
	}

}
