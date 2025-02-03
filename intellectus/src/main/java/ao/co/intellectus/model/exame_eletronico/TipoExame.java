package ao.co.intellectus.model.exame_eletronico;

public enum TipoExame {

	HISTORIA("História"), MATEMATICA("Matemática"), PORTUGUES("Lingua Portuguesa"), MATEMATICA2("Matemática Arquitetura");

	private String descricao;

	private TipoExame(String discricao) {
		this.descricao = discricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
