package ao.co.intellectus.model;

public enum TipoEquivalencia {
	I("Interna"),E("Externa");

	private String descricao;
	
	TipoEquivalencia(String descricao){
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
