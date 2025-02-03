package ao.co.intellectus.model;

public enum StatusCredito {

	NORMAL("Normal"), USADO("Usado"), PARCIAL("Parcial");

	private String descricao;

	private StatusCredito(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
