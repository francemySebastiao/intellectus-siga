package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_horario")
public class Horario {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Enumerated(EnumType.STRING)
	private TipoHorario tipoHorario;
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	
	@ManyToOne
	@JoinColumn(name="codigo_curso")
	private Curso curso;
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name="codigo_turma")
	private Turma turma;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public TipoHorario getTipoHorario() {
		return tipoHorario;
	}
	public void setTipoHorario(TipoHorario tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}