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
@Table(name="t_registro_documentos")
public class RegistroDocumentos {
	@Id
	@GeneratedValue
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
	@Column(nullable=true)
	private boolean defesaTcc;
	@Column(nullable=true)
	private boolean projectoFinal;
	@Column(nullable=true)
	private boolean cerimonia;
	private Integer anoCurricular;
	@Column(nullable=true,name="data_missao")
	@Temporal(TemporalType.DATE) 
	private Date dataEmissao;
	//private String entidadePesquisa;
	/*@ManyToOne
	@JoinColumn(name="usuario_solicitou")
	private Usuario usuario_solicitou;
	@ManyToOne
	@JoinColumn(name="usuario_emitiu")
	private Usuario usuario_emitiu;*/
	
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
	public boolean isRetirado() {
		return retirado;
	}
	public void setRetirado(boolean retirado) {
		this.retirado = retirado;
	}
	public TipoDeDeclaracao getTipoDeclaracao() {
		return tipoDeclaracao;
	}
	public void setTipoDeclaracao(TipoDeDeclaracao tipoDeclaracao) {
		this.tipoDeclaracao = tipoDeclaracao;
	}
	public boolean isDefesaTcc() {
		return defesaTcc;
	}
	public void setDefesaTcc(boolean defesaTcc) {
		this.defesaTcc = defesaTcc;
	}
	public boolean isProjectoFinal() {
		return projectoFinal;
	}
	public void setProjectoFinal(boolean projectoFinal) {
		this.projectoFinal = projectoFinal;
	}
	public boolean isCerimonia() {
		return cerimonia;
	}
	public void setCerimonia(boolean cerimonia) {
		this.cerimonia = cerimonia;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao=dataEmissao;
	}
	
	public Date getDataEmissao() {
		return this.dataEmissao;
	}
	/*
	public String getEntidadePesquisa() {
		return entidadePesquisa;
	}
	public void setEntidadePesquisa(String entidadePesquisa) {
		this.entidadePesquisa = entidadePesquisa;
	}
	*/
	/*public Usuario getUsuario_solicitou() {
		return usuario_solicitou;
	}
	public void setUsuario_solicitou(Usuario usuario_solicitou) {
		this.usuario_solicitou = usuario_solicitou;
	}
	public Usuario getUsuario_emitiu() {
		return usuario_emitiu;
	}
	public void setUsuario_emitiu(Usuario usuario_emitiu) {
		this.usuario_emitiu = usuario_emitiu;
	}*/
	
	
} 
