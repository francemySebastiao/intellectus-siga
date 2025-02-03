package ao.co.intellectus.DTO;

public class EmolumentoCliente {
	private Integer id;
	private Integer codigo;
	private String emolumento;
	private double valor;
	private Integer percentagemIva;
	private String codigoIva;
	
	
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
	public Integer getPercentagemIva() {
		return percentagemIva;
	}
	public void setPercentagemIva(Integer percentagemIva) {
		this.percentagemIva = percentagemIva;
	}
	public String getCodigoIva() {
		return codigoIva;
	}
	public void setCodigoIva(String codigoIva) {
		this.codigoIva = codigoIva;
	}
	
	
}