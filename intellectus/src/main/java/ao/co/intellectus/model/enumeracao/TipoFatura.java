package ao.co.intellectus.model.enumeracao;

public enum TipoFatura {

	FACTURA_PROFORMA("Factura Proforma"), FACTURA_RECIBO("Factura Recibo"), FACTURA_CREDITO("Factura Crédito");
	private String descricao;
	
	private TipoFatura(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
