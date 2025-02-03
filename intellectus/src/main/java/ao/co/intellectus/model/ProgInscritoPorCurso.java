package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_INSCRITO_POR_CURSO")
public class ProgInscritoPorCurso {
	@Id
	private Integer id;
	private Integer anoLectivo;
	private String curso;
	private Integer totalInscrito;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Integer getTotalInscrito() {
		return totalInscrito;
	}
	public void setTotalInscrito(Integer totalInscrito) {
		this.totalInscrito = totalInscrito;
	}
}
