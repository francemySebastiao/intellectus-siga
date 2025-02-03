package ao.co.intellectus.model;

public enum Situacao {
	CURSANDO("Cursando"), APROVADO("Aprovado"), REPROVADO("Reprovado"), PENDENTE("Pendente");

	private String descricao;

	Situacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
