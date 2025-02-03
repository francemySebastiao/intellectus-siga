package ao.co.intellectus.model.enumeracao;

public enum TipoPublicoAlvo {

	ALUNO("ALUNO"), CANDIDATO("CANDIDATO");

	private String descricao;

	private TipoPublicoAlvo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
