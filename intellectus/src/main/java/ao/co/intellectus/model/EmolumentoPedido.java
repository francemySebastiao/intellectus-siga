package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_emolumento_pedido")
public class EmolumentoPedido {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="codigo_emolumento")
	private Emolumento emolumento;
	@ManyToOne
	@JoinColumn(name="codigo_tipo_de_declaracao")
	private TipoDeDeclaracao tipoDeDeclaracao;
	@Column(nullable=true)
	private boolean estado;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Emolumento getEmolumento() {
		return emolumento;
	}
	public void setEmolumento(Emolumento emolumento) {
		this.emolumento = emolumento;
	}
	public TipoDeDeclaracao getTipoDeDeclaracao() {
		return tipoDeDeclaracao;
	}
	public void setTipoDeDeclaracao(TipoDeDeclaracao tipoDeDeclaracao) {
		this.tipoDeDeclaracao = tipoDeDeclaracao;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
