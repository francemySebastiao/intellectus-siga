package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_CANDIDATOS_POR_GRAU")
public class CandidatoPorGrau {
	@Id
	private Integer id;
	@Column(name="ANO_LECTIVO")
	private Integer anoLectivo;
	
	@Column(name="GRAU")
	private String grau;
	
	@Column(name="TOTAL_INSCRITOS")
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

	public String getGrau() {
		return grau;
	}

	public void setGrau(String grau) {
		this.grau = grau;
	}

	public Integer getTotalInscrito() {
		return totalInscrito;
	}

	public void setTotalInscrito(Integer totalInscrito) {
		this.totalInscrito = totalInscrito;
	}
	
	
}
