package ao.co.intellectus.model.colegio;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ActividadeExtraCurriculares {
	@Id
	@GeneratedValue
	private Integer id;
	private String descricao;
	private double valor;
	public Integer getId() {
		return id;
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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
