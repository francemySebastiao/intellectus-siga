package ao.co.intellectus.model;

public enum Permissao {
	GRAVAR("GRAVAR"),LEITURA("LEITURA");
	
	private String descricao;
	
	Permissao(String descricao){
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
