package ao.co.intellectus.controller;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import ao.co.intellectus.DTO.AnosInscricoes;
import ao.co.intellectus.DTO.GuiaCliente;

public class GuiaAlunos {
	private Integer numeroAluno;
	private String nome;
	private String curso;
	private double credito;
	private boolean contencioso;
	private boolean fimCurso;
	private boolean inativo;
	private boolean bolseiro;
	private String empresaConvenio;
	private Integer percentagemDesconto;
	private String grau;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] foto;
    private List<GuiaCliente> guiasAlunos;
    private List<AnosInscricoes> anosInscricoes;
	
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<GuiaCliente> getGuiasAlunos() {
		return guiasAlunos;
	}
	public void setGuiasAlunos(List<GuiaCliente> guiasAlunos) {
		this.guiasAlunos = guiasAlunos;
	}
	public double getCredito() {
		return credito;
	}
	public void setCredito(double credito) {
		this.credito = credito;
	}
	public boolean isContencioso() {
		return contencioso;
	}
	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}
	public boolean isFimCurso() {
		return fimCurso;
	}
	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}
	public boolean isInativo() {
		return inativo;
	}
	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public List<AnosInscricoes> getAnosInscricoes() {
		return anosInscricoes;
	}
	public void setAnosInscricoes(List<AnosInscricoes> anosInscricoes) {
		this.anosInscricoes = anosInscricoes;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public boolean isBolseiro() {
		return bolseiro;
	}
	public void setBolseiro(boolean bolseiro) {
		this.bolseiro = bolseiro;
	}
	public String getEmpresaConvenio() {
		return empresaConvenio;
	}
	public void setEmpresaConvenio(String empresaConvenio) {
		this.empresaConvenio = empresaConvenio;
	}
	public Integer getPercentagemDesconto() {
		return percentagemDesconto;
	}
	public void setPercentagemDesconto(Integer percentagemDesconto) {
		this.percentagemDesconto = percentagemDesconto;
	}
	public String getGrau() {
		return grau;
	}
	public void setGrau(String grau) {
		this.grau = grau;
	}
}