package ao.co.intellectus.model.enumeracao;

public enum TipoDoc {

	RECIBO("Recibo"), FACTURA("Factura"), FACTURA_RECIBO("Factura Recibo");
	private String descricao;
	
	private TipoDoc(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
