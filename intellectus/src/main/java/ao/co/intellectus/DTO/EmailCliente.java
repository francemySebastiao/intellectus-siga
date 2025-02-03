package ao.co.intellectus.DTO;

public class EmailCliente {

	private String assunto;
	private String mensagem;
	private String remitente;
	private String[] destinatario;
	private String[] cc;
	private String[] bCC;
	private AnexoCliente anexo;

	public EmailCliente() {
		this.anexo = new AnexoCliente();
	}
	
	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String[] getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String[] destinatario) {
		this.destinatario = destinatario;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getbCC() {
		return bCC;
	}

	public void setbCC(String[] bCC) {
		this.bCC = bCC;
	}

	public AnexoCliente getAnexo() {
		return anexo;
	}

	public void setAnexo(AnexoCliente anexo) {
		this.anexo = anexo;
	}

}
