package ao.co.intellectus.model;

public enum Sexo {
	M("Masculino"),F("Femenino");
	
	private String descricao;
	
	Sexo(String descricao){
	   this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
