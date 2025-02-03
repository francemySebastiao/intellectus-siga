package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_contador")
public class Contador {
	@Id
	@GeneratedValue
	private Integer id;
	private String contador;
	private String descricao;
	private Integer proximoValor;
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContador() {
		return contador;
	}
	public void setContador(String contador) {
		this.contador = contador;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getProximoValor() {
		return proximoValor;
	}
	public void setProximoValor(Integer proximoValor) {
		this.proximoValor = proximoValor;
	}
	
}
