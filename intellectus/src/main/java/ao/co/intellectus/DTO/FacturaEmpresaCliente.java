package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class FacturaEmpresaCliente {

	private Long idFactura;
	private Integer codigoEmpresa;
	private String numeroFactura;
	private double valorFactura;
	private String estadoFactura;
	private Boolean liquidacao;
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	@Temporal(TemporalType.DATE)
	private Date dataLiquidacao;
	@Temporal(TemporalType.DATE)
	private Date dataAnulacao;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	private String numeroNotaCredito;
	private Double valorNotaCredito;
	
	public Integer getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(Integer codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public double getValorFactura() {
		return valorFactura;
	}
	public void setValorFactura(double valorFactura) {
		this.valorFactura = valorFactura;
	}
	public String getEstadoFactura() {
		return estadoFactura;
	}
	public void setEstadoFactura(String estadoFactura) {
		this.estadoFactura = estadoFactura;
	}
	
	public Boolean getLiquidacao() {
		return liquidacao;
	}
	public void setLiquidacao(Boolean liquidacao) {
		this.liquidacao = liquidacao;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	public Date getDataLiquidacao() {
		return dataLiquidacao;
	}
	public void setDataLiquidacao(Date dataLiquidacao) {
		this.dataLiquidacao = dataLiquidacao;
	}
	public Date getDataAnulacao() {
		return dataAnulacao;
	}
	public void setDataAnulacao(Date dataAnulacao) {
		this.dataAnulacao = dataAnulacao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Double getValorNotaCredito() {
		return valorNotaCredito;
	}
	public void setValorNotaCredito(Double valorNotaCredito) {
		this.valorNotaCredito = valorNotaCredito;
	}
	public String getNumeroNotaCredito() {
		return numeroNotaCredito;
	}
	public void setNumeroNotaCredito(String numeroNotaCredito) {
		this.numeroNotaCredito = numeroNotaCredito;
	}
	
	
}
