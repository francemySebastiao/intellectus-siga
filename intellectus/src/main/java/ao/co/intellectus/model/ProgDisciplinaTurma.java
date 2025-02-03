package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_TURMA_DISCIPLINA")
public class ProgDisciplinaTurma {
	@Id
    private Integer id;
    private String turma;
    private Integer TurmaId;
    private String disciplina;
    private Integer disciplinaId;
    private Integer anoLectivo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	} 
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public Integer getTurmaId() {
		return TurmaId;
	}
	public void setTurmaId(Integer turmaId) {
		TurmaId = turmaId;
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
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
}
