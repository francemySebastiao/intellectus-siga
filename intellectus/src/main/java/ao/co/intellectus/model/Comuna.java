package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_comuna")
public class Comuna { 
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="codigo_municipio")
	private Municipio municipio;
	private String descricao;
	private Integer codigoComuna;
	
	public Comuna() {
	}
	
	public Comuna(Integer id) {
		this.id = id;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getCodigoComuna() {
		return codigoComuna;
	}
	public void setCodigoComuna(Integer codigoComuna) {
		this.codigoComuna = codigoComuna;
	}
}
