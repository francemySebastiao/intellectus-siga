package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_numero_gerado")
public class NumeroGerado {
	@Id
	@GeneratedValue
	private Integer id;
	private Long proximoNumero;
	private Long ultimoNumero;
	@Column(length=20, unique = true)
	private String descricao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getProximoNumero() {
		return proximoNumero;
	}
	public void setProximoNumero(Long proximoNumero) {
		this.proximoNumero = proximoNumero;
	}
	public Long getUltimoNumero() {
		return ultimoNumero;
	}
	public void setUltimoNumero(Long ultimoNumero) {
		this.ultimoNumero = ultimoNumero;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}