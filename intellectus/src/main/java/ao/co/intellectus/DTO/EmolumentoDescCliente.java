package ao.co.intellectus.DTO;

public class EmolumentoDescCliente {
	private Integer id;
	private Integer codigo;
	private String emolumento;
	private boolean propina;
	private boolean status; 
	private boolean paraContecioso; 
	private boolean desconta;
	private double valorDesconto;
	private double valor;
	private double valorComIva;
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
	public boolean isPropina() {
		return propina;
	}
	public void setPropina(boolean propina) {
		this.propina = propina;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isParaContecioso() {
		return paraContecioso;
	}
	public void setParaContecioso(boolean paraContecioso) {
		this.paraContecioso = paraContecioso;
	}
	public boolean isDesconta() {
		return desconta;
	}
	public void setDesconta(boolean desconta) {
		this.desconta = desconta;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getValorDesconto() {
		return valorDesconto;
	}
	public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public double getValorComIva() {
		return valorComIva;
	}
	public void setValorComIva(double valorComIva) {
		this.valorComIva = valorComIva;
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
