package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_PROG_INSCRI_ANUAL")
public class InscricaoAnual {
	@Id
	private Integer id;
	private Integer ano;
	private Integer mesInteiro;
	private String mes;
	private Integer total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMesInteiro() {
		return mesInteiro;
	}

	public void setMesInteiro(Integer mesInteiro) {
		this.mesInteiro = mesInteiro;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
