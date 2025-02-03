package ao.co.intellectus.model.request;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.DeclaracaoDestino;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.TipoDeDeclaracao;

public class DocumentoRequest {
	private Integer id;
	private Integer numeroDeclaracao;
	private Integer anoDeclaracao;
	@Temporal(TemporalType.DATE) 
	private Date dataDeclaracao;
	@ManyToOne
	@JoinColumn(name="codigo_aluno")
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name="codigo_guia_pagamento")
	private Guia guiaPagamento;
	@ManyToOne
	@JoinColumn(name="codigo_declaracao_destino")
	private DeclaracaoDestino declaracaoDestino;
	@ManyToOne
	@JoinColumn(name="codigo_tipo_declaracao")
	private TipoDeDeclaracao tipoDeclaracao;
	private boolean retirado;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroDeclaracao() {
		return numeroDeclaracao;
	}
	public void setNumeroDeclaracao(Integer numeroDeclaracao) {
		this.numeroDeclaracao = numeroDeclaracao;
	}
	public Integer getAnoDeclaracao() {
		return anoDeclaracao;
	}
	public void setAnoDeclaracao(Integer anoDeclaracao) {
		this.anoDeclaracao = anoDeclaracao;
	}
	public Date getDataDeclaracao() {
		return dataDeclaracao;
	}
	public void setDataDeclaracao(Date dataDeclaracao) {
		this.dataDeclaracao = dataDeclaracao;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Guia getGuiaPagamento() {
		return guiaPagamento;
	}
	public void setGuiaPagamento(Guia guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}
	public DeclaracaoDestino getDeclaracaoDestino() {
		return declaracaoDestino;
	}
	public void setDeclaracaoDestino(DeclaracaoDestino declaracaoDestino) {
		this.declaracaoDestino = declaracaoDestino;
	}
	public TipoDeDeclaracao getTipoDeclaracao() {
		return tipoDeclaracao;
	}
	public void setTipoDeclaracao(TipoDeDeclaracao tipoDeclaracao) {
		this.tipoDeclaracao = tipoDeclaracao;
	}
	public boolean isRetirado() {
		return retirado;
	}
	public void setRetirado(boolean retirado) {
		this.retirado = retirado;
	}
}
