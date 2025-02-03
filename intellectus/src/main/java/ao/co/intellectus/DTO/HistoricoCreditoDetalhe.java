package ao.co.intellectus.DTO;

import java.util.List;

public class HistoricoCreditoDetalhe {
	private Integer id;
	// DADOS DO ALUNO
	private String nome;
	private Integer numeroDeAluno;
	private String curso;
	private boolean contencioso;
	private boolean inativo;
	private boolean fimCurso;
	private Double saldoAtual;
	private String numeroFR;
	
	// DADOS DE VALORES

	private List<CreditosResumoCliente> creditos;

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public boolean isContencioso() {
		return contencioso;
	}
	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}
	public boolean isInativo() {
		return inativo;
	}
	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	public boolean isFimCurso() {
		return fimCurso;
	}
	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}
	public Double getSaldoAtual() {
		return saldoAtual;
	}
	public void setSaldoAtual(Double saldoAtual) {
		this.saldoAtual = saldoAtual;
	}
	public List<CreditosResumoCliente> getCreditos() {
		return creditos;
	}
	public void setCreditos(List<CreditosResumoCliente> creditos) {
		this.creditos = creditos;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumeroFR() {
		return numeroFR;
	}
	public void setNumeroFR(String numeroFR) {
		this.numeroFR = numeroFR;
	}
}
