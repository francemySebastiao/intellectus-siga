package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ReciboEmpresaCliente {

	private Long idRecibo;
	private Integer codigoEmpresa;
	private String numeroRecibo;
	private double valorPago;
	private String estadoRecibo;
	private Boolean liquidacao;
	private Boolean alterada;
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	@Temporal(TemporalType.DATE)
	private Date dataLiquidacao;
	@Temporal(TemporalType.DATE)
	private Date dataAnulacao;
	@Temporal(TemporalType.DATE)
	private Date dataAlteracao;
	
	public Long getIdRecibo() {
		return idRecibo;
	}
	public void setIdRecibo(Long idRecibo) {
		this.idRecibo = idRecibo;
	}
	public Integer getCodigoEmpresa() {
		return codigoEmpresa;
	}
	public void setCodigoEmpresa(Integer codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	public String getEstadoRecibo() {
		return estadoRecibo;
	}
	public void setEstadoRecibo(String estadoRecibo) {
		this.estadoRecibo = estadoRecibo;
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
	public Boolean getAlterada() {
		return alterada;
	}
	public void setAlterada(Boolean alterada) {
		this.alterada = alterada;
	}
	public Date getDataAlteracao() {
		return dataAlteracao;
	}
	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
}
