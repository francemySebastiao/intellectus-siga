package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_TURMD")
public class TurmD {
    @Id
    @Column(name="ID")
	private Integer id;
    @Column(name="CODIGO_CURSO")
	private Integer curso;
    @Column(name="CODIGO_DISCIPLINA")
	private Integer disciplina;
    @Column(name="CODIGO_DOCENTE")
	private Integer docente;
    @Column(name="CODIGO_TURMA")
	private Integer turma;
    @Column(name="CODIGO_ANO_LECTIVO")
	private Integer anoLectivo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	public Integer getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}
	public Integer getDocente() {
		return docente;
	}
	public void setDocente(Integer docente) {
		this.docente = docente;
	}
	public Integer getTurma() {
		return turma;
	}
	public void setTurma(Integer turma) {
		this.turma = turma;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
}