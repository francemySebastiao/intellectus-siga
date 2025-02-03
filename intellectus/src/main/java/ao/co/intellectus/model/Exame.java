package ao.co.intellectus.model;

public enum Exame {
    MATEMATICA("Matemática"),PORTUGUES("Lingua portuguesa"),HISTORIA("História"),MATEMATICA_PORTUGUES("Matemática + Lingua portuguesa"),PORTUGUES_HISTORIA("Lingua portuguesa + História"),NA("Não Aplicavel");
	
	private String descricao;
	
	private Exame(String descricao) {
	    this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
