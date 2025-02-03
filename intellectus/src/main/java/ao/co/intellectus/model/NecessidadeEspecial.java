package ao.co.intellectus.model;

public enum NecessidadeEspecial {
    SEM_NEE("Sem NEE"),CEGO("Cego"),SURDO("Surdo"),FISICO_MOTOR("Físico Motor"),OUTROS("Outros"),NA("Não Aplicavel");
	
	private String descricao;
	
	NecessidadeEspecial(String descricao){
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}