package ao.co.intellectus.DTO;

public class EmolumentoPedidoCliente {
	private Integer id;
	private Integer emolumento;
	private Integer tipoDeDeclaracao;
	private boolean estado;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmolumento() {
		return emolumento;
	}
	public void setEmolumento(Integer emolumento) {
		this.emolumento = emolumento;
	}

	public Integer getTipoDeDeclaracao() {
		return tipoDeDeclaracao;
	}

	public void setTipoDeDeclaracao(Integer tipoDeDeclaracao) {
		this.tipoDeDeclaracao = tipoDeDeclaracao;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
