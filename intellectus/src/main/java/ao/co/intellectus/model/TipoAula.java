package ao.co.intellectus.model;

public enum TipoAula {
	TEORICA("Teórica"), PRATICA("Prática"), TEORICA_PRATICA("Teórico/Prática");
	private String descricao;

	private TipoAula(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
