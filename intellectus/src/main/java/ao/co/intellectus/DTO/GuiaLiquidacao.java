package ao.co.intellectus.DTO;

import java.util.Date;

public class GuiaLiquidacao {
	private Integer id;
	private Date dataDeposito;
	private Date dataRegistro;
	private Integer banco;
	private Integer moeda;
	private double valorDeposito;
	private String numeroBorderaux;
	private boolean candidatura;
	private boolean suplemento;
	private boolean liquidacaoCredito;
	private double multaCalculada;
	private String userName;
	private Integer userCode;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDataDeposito() {
		return dataDeposito;
	}
	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public Integer getBanco() {
		return banco;
	}
	public void setBanco(Integer banco) {
		this.banco = banco;
	}
	public Integer getMoeda() {
		return moeda;
	}
	public void setMoeda(Integer moeda) {
		this.moeda = moeda;
	}
	public double getValorDeposito() {
		return valorDeposito;
	}
	public void setValorDeposito(double valorDeposito) {
		this.valorDeposito = valorDeposito;
	}
	public String getNumeroBorderaux() {
		return numeroBorderaux;
	}
	public void setNumeroBorderaux(String numeroBorderaux) {
		this.numeroBorderaux = numeroBorderaux;
	}
	public boolean isCandidatura() {
		return candidatura;
	}
	public void setCandidatura(boolean candidatura) {
		this.candidatura = candidatura;
	}
	public boolean isSuplemento() {
		return suplemento;
	}
	public void setSuplemento(boolean suplemento) {
		this.suplemento = suplemento;
	}
	public boolean isLiquidacaoCredito() {
		return liquidacaoCredito;
	}
	public void setLiquidacaoCredito(boolean liquidacaoCredito) {
		this.liquidacaoCredito = liquidacaoCredito;
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
	public double getMultaCalculada() {
		return multaCalculada;
	}
	public void setMultaCalculada(double multaCalculada) {
		this.multaCalculada = multaCalculada;
	}
}