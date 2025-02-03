package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_pagamento_referencia")
public class PagamentoReferencia {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(length = 20)
	private String referencia;
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	private double valor;
	private boolean compensado;
	@Temporal(TemporalType.DATE)
	private Date dataCompensacao;
	@ManyToOne
	@JoinColumn(name = "codigo_guia")
	private Guia guia;
	@Column(length = 100)
	private String descricao;
	private Integer numeroAluno;
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name="codigo_mes_processamento")
	private Mes mes;
	@Column(nullable=true)
    private boolean liquidada;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public boolean isCompensado() {
		return compensado;
	}
	public void setCompensado(boolean compensado) {
		this.compensado = compensado;
	}
	public Guia getGuia() {
		return guia;
	}
	public void setGuia(Guia guia) {
		this.guia = guia;
	}
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataCompensacao() {
		return dataCompensacao;
	}
	public void setDataCompensacao(Date dataCompensacao) {
		this.dataCompensacao = dataCompensacao;
	}
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Mes getMes() {
		return mes;
	}
	public void setMes(Mes mes) {
		this.mes = mes;
	}
	public boolean isLiquidada() {
		return liquidada;
	}
	public void setLiquidada(boolean liquidada) {
		this.liquidada = liquidada;
	}
	
}