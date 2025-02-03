package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class RegistrarCreditoEmpresaAluno {

	private Integer empresa;
	private Integer anoLectivo;
	private double valor;
	@Temporal(TemporalType.DATE)
	private Date dataRegistro;
	private String userName;
	private Integer userCode;
	private List<BolseirosListaCliente> bolseiros;
	public Integer getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
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
	public List<BolseirosListaCliente> getBolseiros() {
		return bolseiros;
	}
	public void setBolseiros(List<BolseirosListaCliente> bolseiros) {
		this.bolseiros = bolseiros;
	}
	
	
	

}
