package ao.co.intellectus.model;

public enum TipoAcesso {
	
	D("Determinado"),I("Indeterminado");
	
	private String descricao;
	
	private TipoAcesso(String descricao) {
	    this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}