package ao.co.intellectus.DTO;

public class AcademicoFicha {
	private Integer anoCorricular;
    private String disciplina;
    private String tipo;
    private String turma;
    private String tipoInscricao;
    
	public Integer getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(String tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
}
