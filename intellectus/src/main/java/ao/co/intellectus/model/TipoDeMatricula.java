package ao.co.intellectus.model;

public enum TipoDeMatricula {

	ANO_COMPLECTO("Ano completo"),POR_DISCIPLINA("Por Disciplina"),POR_DISCIPLINA_COM_EXAME("Por Disciplina com Exame");
	
	private String descricao;

	private TipoDeMatricula(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}