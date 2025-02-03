package ao.co.intellectus.DTO;

public class GuiaMultaCliente {
    private String numeroGuia;
    private boolean processamento;
    public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public boolean isProcessamento() {
		return processamento;
	}
	public void setProcessamento(boolean processamento) {
		this.processamento = processamento;
	}
}
