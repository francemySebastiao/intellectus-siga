package ao.co.intellectus.DTO;

public class TurmaAndDisciplinaCliente {
    private Integer turma;
    private Integer disciplina;
    private Integer AnoLectivo;
    private Integer numeroDeAluno;
    private Integer docente;
    
	public Integer getTurma() {
		return turma;
	}
	public void setTurma(Integer turma) {
		this.turma = turma;
	}
	public Integer getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}
	public Integer getAnoLectivo() {
		return AnoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		AnoLectivo = anoLectivo;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public Integer getDocente() {
		return docente;
	}
	public void setDocente(Integer docente) {
		this.docente = docente;
	}
}
