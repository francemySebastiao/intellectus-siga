package ao.co.intellectus.model.enumeracao;

public enum TipoCandidatura {
	CANDIDATURA_NORMAL("Candidatura normal"), 
	CANDIDATURA_EQUIVALENCIA("Candidatura por equivalência'"),
	CANDIDATURA_POR_CURSO_ACESSO("Candidatura pro curso de acesso"),N("Normal"),E("Equivalência"),C("Curso de acesso");

	private String descricao;

	private TipoCandidatura(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
