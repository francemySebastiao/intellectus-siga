package ao.co.intellectus.servico.exception;

public class TipoDeUsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TipoDeUsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}
