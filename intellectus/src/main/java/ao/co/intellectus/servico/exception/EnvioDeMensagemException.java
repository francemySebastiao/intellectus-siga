package ao.co.intellectus.servico.exception;

public class EnvioDeMensagemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnvioDeMensagemException(String memensagem) {
		super(memensagem);
	}

}
