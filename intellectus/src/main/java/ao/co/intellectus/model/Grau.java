package ao.co.intellectus.model;

public enum Grau {
	LICENCIATURA("Licenciatura"),MESTRADO("Mestrado"),DOUTORAMENTO("Doutoramento"),POSGRADUACAO("Pós Graduação"),
	CESE("CESE"),CET("Cet"), CEA("Cea");

	private String descricao;
	
	Grau(String descricao){
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
 
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
