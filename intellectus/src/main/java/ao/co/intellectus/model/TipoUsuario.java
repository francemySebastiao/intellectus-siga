package ao.co.intellectus.model;

public enum TipoUsuario {
    A("Aluno(a)"),F("Funcionario(a)"),D("Docente"),C("Candidato");
	
	private String descricao;
	
	TipoUsuario(String descricao){
		this.descricao=descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}