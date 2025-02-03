package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Semestre;
import ao.co.intellectus.model.Turma;

public class MatriculaCliente {
	private Integer id;
	private Integer tipoInscricaoID;
	private Semestre semestre;
	private Integer anoLectivo;
	private Integer alunoID;
	private List<Disciplina> disciplinas;
	private List<Turma> turmas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTipoInscricaoID() {
		return tipoInscricaoID;
	}
	public void setTipoInscricaoID(Integer tipoInscricaoID) {
		this.tipoInscricaoID = tipoInscricaoID;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getAlunoID() {
		return alunoID;
	}
	public void setAlunoID(Integer alunoID) {
		this.alunoID = alunoID;
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public List<Turma> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
}
