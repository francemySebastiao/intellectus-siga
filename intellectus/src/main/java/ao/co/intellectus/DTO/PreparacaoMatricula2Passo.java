package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Turno;

public class PreparacaoMatricula2Passo {
	private Integer numeroDeAluno;
	private String nome;
	private String curso;
	private Integer cursoCodigo;
	private Integer anoCurricular;
	private boolean contencioso;
	private boolean fimCurso;
	private boolean inativo;
	@Enumerated(EnumType.STRING)
	private Turno turno;
	private Integer anoLectivo;
	private Integer planoPagamento;
	private Integer porcentagem;
	private boolean bolseiro;
	private Integer tipoInscricao;
	private Integer turma;
	
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
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
	public Integer getCursoCodigo() {
		return cursoCodigo;
	}
	public void setCursoCodigo(Integer cursoCodigo) {
		this.cursoCodigo = cursoCodigo;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
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
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getPlanoPagamento() {
		return planoPagamento;
	}
	public void setPlanoPagamento(Integer planoPagamento) {
		this.planoPagamento = planoPagamento;
	}
	public Integer getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(Integer porcentagem) {
		this.porcentagem = porcentagem;
	}
	public boolean isBolseiro() {
		return bolseiro;
	}
	public void setBolseiro(boolean bolseiro) {
		this.bolseiro = bolseiro;
	}
	public Integer getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(Integer tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public Integer getTurma() {
		return turma;
	}
	public void setTurma(Integer turma) {
		this.turma = turma;
	}
	
}
