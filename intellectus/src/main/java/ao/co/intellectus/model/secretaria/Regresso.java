package ao.co.intellectus.model.secretaria;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Regresso {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nomeEstudante;
	private String numeroEstudante;
	private String contactoEstudante;
	private Boolean temDispositivo;
	private Boolean regressa;
	@Temporal(TemporalType.DATE)
	private Date data;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeEstudante() {
		return nomeEstudante;
	}
	public void setNomeEstudante(String nomeEstudante) {
		this.nomeEstudante = nomeEstudante;
	}
	public String getNumeroEstudante() {
		return numeroEstudante;
	}
	public void setNumeroEstudante(String numeroEstudante) {
		this.numeroEstudante = numeroEstudante;
	}
	public String getContactoEstudante() {
		return contactoEstudante;
	}
	public void setContactoEstudante(String contactoEstudante) {
		this.contactoEstudante = contactoEstudante;
	}
	public Boolean getTemDispositivo() {
		return temDispositivo;
	}
	public void setTemDispositivo(Boolean temDispositivo) {
		this.temDispositivo = temDispositivo;
	}
	public Boolean getRegressa() {
		return regressa;
	}
	public void setRegressa(Boolean regressa) {
		this.regressa = regressa;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	

}
