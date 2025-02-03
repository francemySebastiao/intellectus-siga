package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_compensassao_efetiva")
public class CompensassaoEfetiva {
	@Id
	@GeneratedValue
	private Integer id;
	private Integer numero;
	@Column(length=150)
	private String nome;
	@Column(length=15)
	private String telefone;
	@Column(length=15)
	private String numeroGuia;
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	@Column(length=150)
	private String descricao;
	private double valor; 
	private String referencia;
	private boolean compensado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCompensado;
	@Column(length=50)
	private String numeroDeOperacao;
	@Column(nullable=true)
	private boolean liquidada;
	@Temporal(TemporalType.DATE)
	private Date dataLiquidacao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public boolean isCompensado() {
		return compensado;
	}
	public void setCompensado(boolean compensado) {
		this.compensado = compensado;
	}
	public Date getDataCompensado() {
		return dataCompensado;
	}
	public void setDataCompensado(Date dataCompensado) {
		this.dataCompensado = dataCompensado;
	}
	public String getNumeroDeOperacao() {
		return numeroDeOperacao;
	}
	public void setNumeroDeOperacao(String numeroDeOperacao) {
		this.numeroDeOperacao = numeroDeOperacao;
	}
	public boolean isLiquidada() {
		return liquidada;
	}
	public void setLiquidada(boolean liquidada) {
		this.liquidada = liquidada;
	}
	public Date getDataLiquidacao() {
		return dataLiquidacao;
	}
	public void setDataLiquidacao(Date dataLiquidacao) {
		this.dataLiquidacao = dataLiquidacao;
	}
}
