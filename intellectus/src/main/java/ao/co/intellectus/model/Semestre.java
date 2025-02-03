package ao.co.intellectus.model;

public enum Semestre {
	
	PRIMEIRO("Primeiro"),SEGUNDO("Segundo"),ANUAL("Anual");
	
	private String descricao;
	
	private Semestre(String descricao) {
	    this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
 