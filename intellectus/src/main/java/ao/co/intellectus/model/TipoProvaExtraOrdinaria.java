package ao.co.intellectus.model;

public enum TipoProvaExtraOrdinaria {
    ER("Época Recurso"),EE("Época Especial"),M("Melhoria"),CV("Curso de Verão"),PE("Prova extraordinária"), CCD("Curso de Curta Duração");
	
	private String descricao;
	
	TipoProvaExtraOrdinaria(String descricao){
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}