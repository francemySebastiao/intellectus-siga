package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_provincia")
public class Provincia {
	@Id
	@GeneratedValue
    private Integer id;
    private String provincia;
    private String sigla;
    private Integer codigoProvincia;
    private String concordancia;
    @ManyToOne
    @JoinColumn(name="codigo_pais")
    private Pais pais;
    
    public Provincia() {
    }
    
	public Provincia(Integer id) {
		this.id = id;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}
	public void setCodigoProvincia(Integer codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getConcordancia() {
		return concordancia;
	}
	public void setConcordancia(String concordancia) {
		this.concordancia = concordancia;
	}
	
}