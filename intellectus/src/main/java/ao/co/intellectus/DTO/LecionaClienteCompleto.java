package ao.co.intellectus.DTO;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Semestre;
import ao.co.intellectus.model.TipoAula;
import ao.co.intellectus.model.TipoHorario;

public class LecionaClienteCompleto {
	@Enumerated(EnumType.STRING)
	private TipoHorario tipoHorario;
	private Integer disciplina;
	private Integer AnoCurricular;	
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	@Enumerated(EnumType.STRING)
	private TipoAula tipoAula;
	@Enumerated(EnumType.STRING)
	private Integer curso;
	private Integer docente;
	private Integer anoLectivo;
	private Integer sala;
	private String turma;
	private Integer intervalo;
	public TipoHorario getTipoHorario() {
		return tipoHorario;
	}
	public void setTipoHorario(TipoHorario tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
	public Integer getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}
	public Integer getAnoCurricular() {
		return AnoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		AnoCurricular = anoCurricular;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public TipoAula getTipoAula() {
		return tipoAula;
	}
	public void setTipoAula(TipoAula tipoAula) {
		this.tipoAula = tipoAula;
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
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public Integer getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}
}