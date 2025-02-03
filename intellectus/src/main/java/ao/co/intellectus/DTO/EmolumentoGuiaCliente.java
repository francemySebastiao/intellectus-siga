package ao.co.intellectus.DTO;

public class EmolumentoGuiaCliente {
	private Integer id;
	private Integer codigo;
	private String emolumento;
	private String obs;
	private double valor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getEmolumento() {
		return emolumento;
	}

	public void setEmolumento(String emolumento) {
		this.emolumento = emolumento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
}
