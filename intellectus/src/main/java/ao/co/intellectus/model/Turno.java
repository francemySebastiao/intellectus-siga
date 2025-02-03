package ao.co.intellectus.model;

public enum Turno {
	MANHA("Manha"),TARDE("Tarde"),POSLABORAL("PosLaboral");
	
	private String descricao;
	
	Turno(String descricao){
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
