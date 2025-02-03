package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_MATRICULADOS_POR_ANO_CURRICULAR")
public class ProgMatriculadoPorAnoCurricular {
	@Id
	private Integer id;
	private Integer anoCorricular;
	private Integer anoLectivo;
	private Integer totalInscrito;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
	public Integer getTotalInscrito() {
		return totalInscrito;
	}
	public void setTotalInscrito(Integer totalInscrito) {
		this.totalInscrito = totalInscrito;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
}
