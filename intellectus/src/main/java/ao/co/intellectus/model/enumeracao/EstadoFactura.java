package ao.co.intellectus.model.enumeracao;

public enum EstadoFactura {

	EMITIDA("Emitida"), PAGA("Paga"), ANULADA("Anulada"), ALTERADA("Alterada");
	private String descricao;
	
	private EstadoFactura(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
