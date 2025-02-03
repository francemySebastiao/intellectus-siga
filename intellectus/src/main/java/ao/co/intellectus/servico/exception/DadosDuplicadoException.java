package ao.co.intellectus.servico.exception;

public class DadosDuplicadoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DadosDuplicadoException(String mensagem) {
		super(mensagem);
	}

}
