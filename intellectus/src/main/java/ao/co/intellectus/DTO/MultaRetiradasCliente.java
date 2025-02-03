package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class MultaRetiradasCliente {
	private String numeroDeAluno;
	private double totalMulta;
	private double totalSelecionado;
	private double valorAmnistia;
	private double valorAPagar;
	private Integer nPrestacoes;
	private float valorFixo;
	@Temporal(TemporalType.DATE)
	private Date data1Prestacao;
	@Temporal(TemporalType.DATE)
	private List<Date> dataPrestacoes;
	private List<GuiaMultaCliente> guiaMultaCliente;
	private String userName;
	private Integer userCode;
	
	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public double getTotalMulta() {
		return totalMulta;
	}
	public void setTotalMulta(double totalMulta) {
		this.totalMulta = totalMulta;
	}
	public double getTotalSelecionado() {
		return totalSelecionado;
	}
	public void setTotalSelecionado(double totalSelecionado) {
		this.totalSelecionado = totalSelecionado;
	}
	public double getValorAmnistia() {
		return valorAmnistia;
	}
	public void setValorAmnistia(double valorAmnistia) {
		this.valorAmnistia = valorAmnistia;
	}
	public double getValorAPagar() {
		return valorAPagar;
	}
	public void setValorAPagar(double valorAPagar) {
		this.valorAPagar = valorAPagar;
	}
	public Integer getnPrestacoes() {
		return nPrestacoes;
	}
	public void setnPrestacoes(Integer nPrestacoes) {
		this.nPrestacoes = nPrestacoes;
	}
	public float getValorFixo() {
		return valorFixo;
	}
	public void setValorFixo(float valorFixo) {
		this.valorFixo = valorFixo;
	}
	public Date getData1Prestacao() {
		return data1Prestacao;
	}
	public void setData1Prestacao(Date data1Prestacao) {
		this.data1Prestacao = data1Prestacao;
	}
	public List<GuiaMultaCliente> getGuiaMultaCliente() {
		return guiaMultaCliente;
	}
	public void setGuiaMultaCliente(List<GuiaMultaCliente> guiaMultaCliente) {
		this.guiaMultaCliente = guiaMultaCliente;
	}
	public List<Date> getDataPrestacoes() {
		return dataPrestacoes;
	}
	public void setDataPrestacoes(List<Date> dataPrestacoes) {
		this.dataPrestacoes = dataPrestacoes;
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
	
}
