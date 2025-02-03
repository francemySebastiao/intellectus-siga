package ao.co.intellectus.model;

public enum TipoEmpresa {
	PUBLICA("Privada"),PRIVADA("Pública");
	
	private String descricao;
	
	private TipoEmpresa(String descricao) {
	    this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
