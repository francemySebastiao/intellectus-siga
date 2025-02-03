package ao.co.intellectus.DTO;

import java.util.List;

public class PlanoControleCliente {
	private Integer numeroAluno;
	private String nome;
	private String curso;
	private double credito;
	private boolean contencioso;
	private boolean fimCurso;
	private boolean inativo;
	private List<CompostoPlanoCliente> controlePlanos;
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
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
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
	public List<CompostoPlanoCliente> getControlePlanos() {
		return controlePlanos;
	}
	public void setControlePlanos(List<CompostoPlanoCliente> controlePlanos) {
		this.controlePlanos = controlePlanos;
	}
}
