package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_municipio")
public class Municipio {
	@Id
	@GeneratedValue
	private Integer id;
	private String descricao;
	private String concordancia;
	private String codigoMunicipio;
	@ManyToOne
	@JoinColumn(name="codigo_provincia")
	private Provincia provincia;
	public Integer getId() {
		return id;
	}
	
	public Municipio() {
	}
	public Municipio(Integer id) {
		this.id = id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getConcordancia() {
		return concordancia;
	}
	public void setConcordancia(String concordancia) {
		this.concordancia = concordancia;
	}
	public String getCodigoMunicipio() {
		return codigoMunicipio;
	}
	public void setCodigoMunicipio(String codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}
	
}