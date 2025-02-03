package ao.co.intellectus.model.enumeracao;

public enum TipoRelatorio {

	RC("Relatório de Candidaturas"), RF("Relatório Financeiro");
	private String descricao;
	
	private TipoRelatorio(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
