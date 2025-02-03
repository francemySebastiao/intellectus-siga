package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_PROG_TURDOC")
public class TurmaDocente {
	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "TURMA_ID")
	private Integer turmaId;

	@Column(name = "TURMA")
	private String turma;

	@Column(name = "DOCENTE")
	private Integer docente;

	@Column(name = "ANO_LECTIVO")
	private Integer anoLectivo;

	@Column(name = "TOTAL_AULAS")
	private Integer totalAulas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(Integer turmaId) {
		this.turmaId = turmaId;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
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
}
