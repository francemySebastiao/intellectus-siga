package ao.co.intellectus.model;

public enum TipoHorario {
	DOCENTE("Docente"),TURMA("Turma"), SALA("Sala");

	private String descricao;

	TipoHorario(String descricao){
	   this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
