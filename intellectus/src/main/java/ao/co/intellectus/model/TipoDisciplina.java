package ao.co.intellectus.model;

public enum TipoDisciplina {
	ANUAL("Anual"),PRIMEIRO_SEMESTRE("1º semestre"),SEGUNDO_SEMESTRE("2º semestre"),PROJECTO("Projecto");
	
	private String descricao;
	
	TipoDisciplina(String descricao) {
       this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
} 