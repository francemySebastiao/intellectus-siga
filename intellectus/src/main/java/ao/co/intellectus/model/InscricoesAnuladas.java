package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_INSC_ANULADO")
public class InscricoesAnuladas {
	@Id
	private Integer id;
	
	@Column(name="ANO_LECTIVO")
	private Integer anoLectivo;
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
	public Integer getTotalInscrito() {
		return totalInscrito;
	}
	public void setTotalInscrito(Integer totalInscrito) {
		this.totalInscrito = totalInscrito;
	}
}
