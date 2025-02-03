package ao.co.intellectus.DTO;

import java.util.List;

public class FacturaDetalheAlterar {

	private Long idFactura;
	private String numeroFactura;
	private String motivoAnulacao;
	private Double valorTotalFactura;
	private String userName;
	private Integer userCode;
	private List<FacturaDetalheAlterada> facturaAlterada;
	
	
	public Long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public String getMotivoAnulacao() {
		return motivoAnulacao;
	}
	public void setMotivoAnulacao(String motivoAnulacao) {
		this.motivoAnulacao = motivoAnulacao;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}
	public List<FacturaDetalheAlterada> getFacturaAlterada() {
		return facturaAlterada;
	}
	public void setFacturaAlterada(List<FacturaDetalheAlterada> facturaAlterada) {
		this.facturaAlterada = facturaAlterada;
	}
	public Double getValorTotalFactura() {
		return valorTotalFactura;
	}
	public void setValorTotalFactura(Double valorTotalFactura) {
		this.valorTotalFactura = valorTotalFactura;
	}
}
