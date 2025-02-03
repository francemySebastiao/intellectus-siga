package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class BorderoCliente {
	private Date dataRegistro;
	//JUNÇÕES COM OUTRAS TABELAS.
	private String moeda;
	private String nome;
	private String aluno;
	private String numero;
	@Temporal(TemporalType.DATE)
	private Date dataDeposito;
	private double valor;
	private Integer refSiga;
	private String banco;
	//private Integer conta;
	private String conta;
	
	private String registradoPor;
	private String numeroGuia;
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;
	private String emitidoPor;
	@Temporal(TemporalType.DATE)
	private Date dataLiquidacao;
	private Integer refSigaGuia;
	private List<EmolumentoGuiaCliente> emolumentos; 
	
	
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataRegistro() {
		return dataRegistro;
	}
	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getDataDeposito() {
		return dataDeposito;
	}
	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Integer getRefSiga() {
		return refSiga;
	}
	public void setRefSiga(Integer refSiga) {
		this.refSiga = refSiga;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	public String getRegistradoPor() {
		return registradoPor;
	}
	public void setRegistradoPor(String registradoPor) {
		this.registradoPor = registradoPor;
	}
	
	public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public String getEmitidoPor() {
		return emitidoPor;
	}
	public void setEmitidoPor(String emitidoPor) {
		this.emitidoPor = emitidoPor;
	}
	public Date getDataLiquidacao() {
		return dataLiquidacao;
	}
	public void setDataLiquidacao(Date dataLiquidacao) {
		this.dataLiquidacao = dataLiquidacao;
	}
	public Integer getRefSigaGuia() {
		return refSigaGuia;
	}
	public void setRefSigaGuia(Integer refSigaGuia) {
		this.refSigaGuia = refSigaGuia;
	}
	public List<EmolumentoGuiaCliente> getEmolumentos() {
		return emolumentos;
	}
	public void setEmolumentos(List<EmolumentoGuiaCliente> emolumentos) {
		this.emolumentos = emolumentos;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	
}
