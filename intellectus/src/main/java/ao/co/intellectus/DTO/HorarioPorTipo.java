package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Semestre;
import ao.co.intellectus.model.TipoHorario;

public class HorarioPorTipo {
	private Integer anoCurricular;	
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	private Integer turma;
	private Integer curso;
	private Integer docente;
	private Integer anoLectivo;
	private Integer sala;
	@Enumerated(EnumType.STRING)
	private TipoHorario tipoHorario;
	
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	
	public Integer getTurma() {
		return turma;
	}
	public void setTurma(Integer turma) {
		this.turma = turma;
	}
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	public Integer getDocente() {
		return docente;
	}
	public void setDocente(Integer docente) {
		this.docente = docente;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getSala() {
		return sala;
	}
	public void setSala(Integer sala) {
		this.sala = sala;
	}
	public TipoHorario getTipoHorario() {
		return tipoHorario;
	}
	public void setTipoHorario(TipoHorario tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
}