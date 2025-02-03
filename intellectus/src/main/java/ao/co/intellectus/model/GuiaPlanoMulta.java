package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_guia_plano_multa")
public class GuiaPlanoMulta {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="codigo_guia_multa_valor")
	private GuiaMultaValor guiaMultaValor;
	@ManyToOne
	@JoinColumn(name="codigo_guia")
	private Guia guia;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public GuiaMultaValor getGuiaMultaValor() {
		return guiaMultaValor;
	}
	public void setGuiaMultaValor(GuiaMultaValor guiaMultaValor) {
		this.guiaMultaValor = guiaMultaValor;
	}
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
}
