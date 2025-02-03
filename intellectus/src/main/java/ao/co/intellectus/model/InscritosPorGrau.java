package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="V_PROG_INSC_POR_GRAU")
public class InscritosPorGrau {
	@Id
	private Integer id;
	private String grau;
	private Integer ano;
	@Column(name="total_inscritos")
	private Integer totalInscrito;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Integer getTotalInscrito() {
		return totalInscrito;
	}
	public void setTotalInscrito(Integer totalInscrito) {
		this.totalInscrito = totalInscrito;
	}
}
