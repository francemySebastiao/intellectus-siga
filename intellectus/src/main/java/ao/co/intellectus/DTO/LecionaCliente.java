package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Semana;
import ao.co.intellectus.model.Semestre;
import ao.co.intellectus.model.TipoAula;

public class LecionaCliente {
	private Integer id;
	private Integer AnoCurricular;	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	@Enumerated(EnumType.STRING)
	private TipoAula tipoAula;
	@Enumerated(EnumType.STRING)
	private Semana diaSemana;
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	private Integer horario;
	private Integer disciplina;
	private Integer turma;
	private Integer curso;
	private Integer docente;
	private Integer anoLectivo;
	private Integer sala;
	private Integer intervalo;
	@Temporal(TemporalType.DATE)
	private Date diaAula;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnoCurricular() {
		return AnoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		AnoCurricular = anoCurricular;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public TipoAula getTipoAula() {
		return tipoAula;
	}
	public void setTipoAula(TipoAula tipoAula) {
		this.tipoAula = tipoAula;
	}
	public Semana getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(Semana diaSemana) {
		this.diaSemana = diaSemana;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public Integer getHorario() {
		return horario;
	}
	public void setHorario(Integer horario) {
		this.horario = horario;
	}
	public Integer getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
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
	public Integer getIntervalo() {
		return intervalo;
	}
	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}
	public Date getDiaAula() {
		return diaAula;
	}
	public void setDiaAula(Date diaAula) {
		this.diaAula = diaAula;
	}
}