package ao.co.intellectus.DTO;

import java.util.Date;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Equivalencia;

public class HistoricoEquivalenciaModelo {
	private Integer id;
	private String disciplinaOrigem;
	private float notaOrigem;
	private Equivalencia equivalencia;
	private Integer disciplinaDestino;
	private Aluno aluno;
	private Integer numeroAluno;
	private boolean primeiraValidacao;
	private Date dataPrimeiraValidacao;
	private boolean segundaValidacao; 
	private Date dataSegundaValidacao;
	private boolean terceiraValidacao;
	private Date dataTerceiraValidacao;
	private boolean estado;
	public Integer anoCurricular;
	public String usuarioEquivalencia;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDisciplinaOrigem() {
		return disciplinaOrigem;
	}
	public void setDisciplinaOrigem(String disciplinaOrigem) {
		this.disciplinaOrigem = disciplinaOrigem;
	}
	public float getNotaOrigem() {
		return notaOrigem;
	}
	public void setNotaOrigem(float notaOrigem) {
		this.notaOrigem = notaOrigem;
	}
	public Equivalencia getEquivalencia() {
		return equivalencia;
	}
	public void setEquivalencia(Equivalencia equivalencia) {
		this.equivalencia = equivalencia;
	}
	public Integer getDisciplinaDestino() {
		return disciplinaDestino;
	}
	public void setDisciplinaDestino(Integer disciplinaDestino) {
		this.disciplinaDestino = disciplinaDestino;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public boolean isPrimeiraValidacao() {
		return primeiraValidacao;
	}
	public void setPrimeiraValidacao(boolean primeiraValidacao) {
		this.primeiraValidacao = primeiraValidacao;
	}
	public Date getDataPrimeiraValidacao() {
		return dataPrimeiraValidacao;
	}
	public void setDataPrimeiraValidacao(Date dataPrimeiraValidacao) {
		this.dataPrimeiraValidacao = dataPrimeiraValidacao;
	}
	public boolean isSegundaValidacao() {
		return segundaValidacao;
	}
	public void setSegundaValidacao(boolean segundaValidacao) {
		this.segundaValidacao = segundaValidacao;
	}
	public Date getDataSegundaValidacao() {
		return dataSegundaValidacao;
	}
	public void setDataSegundaValidacao(Date dataSegundaValidacao) {
		this.dataSegundaValidacao = dataSegundaValidacao;
	}
	public boolean isTerceiraValidacao() {
		return terceiraValidacao;
	}
	public void setTerceiraValidacao(boolean terceiraValidacao) {
		this.terceiraValidacao = terceiraValidacao;
	}
	public Date getDataTerceiraValidacao() {
		return dataTerceiraValidacao;
	}
	public void setDataTerceiraValidacao(Date dataTerceiraValidacao) {
		this.dataTerceiraValidacao = dataTerceiraValidacao;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}

	public String getUsuarioEquivalencia() {
		return usuarioEquivalencia;
	}

	public void setUsuarioEquivalencia(String usuarioEquivalencia) {
		this.usuarioEquivalencia = usuarioEquivalencia;
	}
	
	
}
