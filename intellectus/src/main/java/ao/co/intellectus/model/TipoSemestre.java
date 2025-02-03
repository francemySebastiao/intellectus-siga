package ao.co.intellectus.model;

public enum TipoSemestre {

	ANUAL("ANUAL"), PRIMEIRO_SEMESTRE("PRIMEIRO_SEMESTRE"), SEGUNDO_SEMESTRE("SEGUNDO_SEMESTRE");

	private String descricao;

	TipoSemestre(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
