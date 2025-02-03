package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class FacturaLiquidacao {

	private Long idFactura;
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	private String numeroOperacao;
	private Integer banco;
	private Float valorPago;
	private String userName;
	
	//private Integer anoLectivo;
	
	public Long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	
	public Float getValorPago() {
		return valorPago;
	}
	public void setValorPago(Float valorPago) {
		this.valorPago = valorPago;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/*public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}*/
	public String getNumeroOperacao() {
		return numeroOperacao;
	}
	public void setNumeroOperacao(String numeroOperacao) {
		this.numeroOperacao = numeroOperacao;
	}
	public Integer getBanco() {
		return banco;
	}
	public void setBanco(Integer banco) {
		this.banco = banco;
	}
	
	
}
