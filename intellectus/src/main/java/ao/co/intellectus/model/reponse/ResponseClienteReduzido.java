package ao.co.intellectus.model.reponse;

public class ResponseClienteReduzido {
	private String mensagem = "";
	private int codigo;
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
