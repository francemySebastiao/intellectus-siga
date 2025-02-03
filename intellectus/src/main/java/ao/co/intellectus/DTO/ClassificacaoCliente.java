package ao.co.intellectus.DTO;

import javax.persistence.Basic;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import ao.co.intellectus.model.TipoDisciplina;

public class ClassificacaoCliente {

	private Integer id;
	private Integer numeroDeAluno;
	private String nome;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] foto;
	private Float primeiraAvaliacao;
	private Float segundaAvaliacao;
	private Float exame;
	private Float exameOral;
	private Float recurso;
	private Float recursoOral;
	private Float epocaEspecial;
	private Float epocaEspecialOral;
	private Float melhorNota;
	private Float melhoriaOral;
	private Float primeiroTrabalho;
	private Float segundoTrabalho;
	private Float pratica;
	private Float verao;
	private Float notaFinalContinua;
	private Float notaFinal;
	private boolean validada;
	@Enumerated(EnumType.STRING)
	private TipoDisciplina tipoDisciplina;
	private boolean semFrequencia;
	private boolean aprovado;
	private boolean podeRecurso;
	private boolean contencioso;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Float getPrimeiraAvaliacao() {
		return primeiraAvaliacao;
	}

	public void setPrimeiraAvaliacao(Float primeiraAvaliacao) {
		this.primeiraAvaliacao = primeiraAvaliacao;
	}

	public Float getSegundaAvaliacao() {
		return segundaAvaliacao;
	}

	public void setSegundaAvaliacao(Float segundaAvaliacao) {
		this.segundaAvaliacao = segundaAvaliacao;
	}

	public Float getExame() {
		return exame;
	}

	public void setExame(Float exame) {
		this.exame = exame;
	}

	public Float getExameOral() {
		return exameOral;
	}

	public void setExameOral(Float exameOral) {
		this.exameOral = exameOral;
	}

	public Float getRecurso() {
		return recurso;
	}

	public void setRecurso(Float recurso) {
		this.recurso = recurso;
	}

	public Float getEpocaEspecial() {
		return epocaEspecial;
	}

	public void setEpocaEspecial(Float epocaEspecial) {
		this.epocaEspecial = epocaEspecial;
	}

	public Float getMelhorNota() {
		return melhorNota;
	}

	public void setMelhorNota(Float melhorNota) {
		this.melhorNota = melhorNota;
	}

	public Float getPrimeiroTrabalho() {
		return primeiroTrabalho;
	}

	public void setPrimeiroTrabalho(Float primeiroTrabalho) {
		this.primeiroTrabalho = primeiroTrabalho;
	}

	public Float getSegundoTrabalho() {
		return segundoTrabalho;
	}

	public void setSegundoTrabalho(Float segundoTrabalho) {
		this.segundoTrabalho = segundoTrabalho;
	}

	public Float getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(Float notaFinal) {
		this.notaFinal = notaFinal;
	}

	public boolean isValidada() {
		return validada;
	}

	public void setValidada(boolean validada) {
		this.validada = validada;
	}

	public TipoDisciplina getTipoDisciplina() {
		return tipoDisciplina;
	}

	public void setTipoDisciplina(TipoDisciplina tipoDisciplina) {
		this.tipoDisciplina = tipoDisciplina;
	}

	public Float getRecursoOral() {
		return recursoOral;
	}

	public void setRecursoOral(Float recursoOral) {
		this.recursoOral = recursoOral;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Float getPratica() {
		return pratica;
	}

	public void setPratica(Float pratica) {
		this.pratica = pratica;
	}

	public Float getNotaFinalContinua() {
		return notaFinalContinua;
	}

	public void setNotaFinalContinua(Float notaFinalContinua) {
		this.notaFinalContinua = notaFinalContinua;
	}

	public Float getVerao() {
		return verao;
	}

	public void setVerao(Float verao) {
		this.verao = verao;
	}

	public boolean isSemFrequencia() {
		return semFrequencia;
	}

	public void setSemFrequencia(boolean semFrequencia) {
		this.semFrequencia = semFrequencia;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public Float getEpocaEspecialOral() {
		return epocaEspecialOral;
	}

	public void setEpocaEspecialOral(Float epocaEspecialOral) {
		this.epocaEspecialOral = epocaEspecialOral;
	}

	public Float getMelhoriaOral() {
		return melhoriaOral;
	}

	public void setMelhoriaOral(Float melhoriaOral) {
		this.melhoriaOral = melhoriaOral;
	}

	public boolean isPodeRecurso() {
		return podeRecurso;
	}

	public void setPodeRecurso(boolean podeRecurso) {
		this.podeRecurso = podeRecurso;
	}

	public boolean isContencioso() {
		return contencioso;
	}

	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}

}
