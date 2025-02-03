package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_DISCDOC")
public class DisciplinaDocente {
	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "DISCIPLINA")
	private String disciplina;

	@Column(name = "DISCIPLINA_ID")
	private Integer disciplinaId;

	@Column(name = "CURSO_ID")
	private Integer cursoId;

	@Column(name = "CURSO")
	private String curso;

	@Column(name = "DOCENTE")
	private Integer docente;

	@Column(name = "ANO_LECTIVO")
	private Integer anoLectivo;

	@Column(name = "TURMA")
	private String turma;

	@Column(name = "CODIGO_TURMA")
	private Integer codigoTurma;

	@Column(name = "TOTAL_AULAS")
	private Integer totalAulas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Integer getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(Integer disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

	public Integer getCursoId() {
		return cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
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

	public Integer getTotalAulas() {
		return totalAulas;
	}

	public void setTotalAulas(Integer totalAulas) {
		this.totalAulas = totalAulas;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public Integer getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(Integer codigoTurma) {
		this.codigoTurma = codigoTurma;
	}
}