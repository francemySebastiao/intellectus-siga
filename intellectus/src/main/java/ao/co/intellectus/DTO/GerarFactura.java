package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class GerarFactura {

	private Integer empresa;
	private List<AlunoResumoCliente> bolseiros;
	//private Integer anoLectivo;
	@Temporal(TemporalType.DATE)
	private Date dataRegistro;
	private String userName;
	private Integer userCode;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	
	public Integer getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}
	
	
	public List<AlunoResumoCliente> getBolseiros() {
		return bolseiros;
	}
	public void setBolseiros(List<AlunoResumoCliente> bolseiros) {
		this.bolseiros = bolseiros;
	}
	/*public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}*/
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
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	
}
