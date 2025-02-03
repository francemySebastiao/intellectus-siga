package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_prova")
public class Prova {	
	@Id
	@GeneratedValue
	private Integer id;
	private String prova;
	@Column(nullable=true)
	private boolean extraordinaria;
	private String descricao;
	private String descricaoPauta;
	private Integer ordem;
	@Column(nullable=true)
	private boolean estado;
	private String sigla;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProva() {
		return prova;
	}
	public void setProva(String prova) {
		this.prova = prova;
	}
	public boolean isExtraordinaria() {
		return extraordinaria;
	}
	public void setExtraordinaria(boolean extraordinaria) {
		this.extraordinaria = extraordinaria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricaoPauta() {
		return descricaoPauta;
	}
	public void setDescricaoPauta(String descricaoPauta) {
		this.descricaoPauta = descricaoPauta;
	}
	public Integer getOrdem() {
		return ordem;
	}
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}