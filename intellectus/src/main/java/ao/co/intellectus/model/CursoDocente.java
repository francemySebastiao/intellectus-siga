package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_CURDOC")
public class CursoDocente {
	@Id
	@Column(name="ID")
	private Integer id;
	@Column(name="plano")
    
	private String curso;
	@Column(name="CURSO_ID")
    
	private Integer cursoId;
	@Column(name="ANO_LECTIVO")
    
	private Integer anoLectivo;
	@Column(name="CODIGO_DOCENTE")
    private Integer docente;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Integer getCursoId() {
		return cursoId;
	}
	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getDocente() {
		return docente;
	}
	public void setDocente(Integer docente) {
		this.docente = docente;
	}
}
