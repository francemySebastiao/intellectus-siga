package ao.co.intellectus.servico.exception;

public class DadoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DadoInvalidoException(String mensagem) {
		super(mensagem);
	}

}
