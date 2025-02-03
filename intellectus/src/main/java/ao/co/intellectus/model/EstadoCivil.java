package ao.co.intellectus.model;

public enum EstadoCivil {
    S("Solteiro(a)"),C("Casado(a)"),V("Viúvo(a)"),U("União de Facto"),D("Divorciado"),O("Outro"), VIÚVO("VIÚVO");
	
	private String descricao;
	
	EstadoCivil(String descricao){
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}