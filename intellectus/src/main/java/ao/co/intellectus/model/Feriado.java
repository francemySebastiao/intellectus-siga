package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_feriado") 

public class Feriado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String mes;
	private Integer mesInteiro;
	private Integer dia;
	
	
	public Integer getId() { 
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) { 
		this.mes = mes;
	}
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	public Integer getMesInteiro() {
		return mesInteiro;
	}
	public void setMesInteiro(Integer mesInteiro) {
		this.mesInteiro = mesInteiro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feriado other = (Feriado) obj;
		if (id != other.id)
			return false;
		return true;
	}
}