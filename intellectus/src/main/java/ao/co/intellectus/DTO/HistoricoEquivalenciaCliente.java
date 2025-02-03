package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class HistoricoEquivalenciaCliente {
	private Integer id;
	private Integer idEquivalencia;
	private String disciplinaOrigem;
	private float notaOrigem;
	private String disciplinaDestino;
	private boolean primeiraValidacao;
	@Temporal(TemporalType.DATE)
	private Date dataPrimeiraValidacao;
	private boolean segundaValidacao; 
	@Temporal(TemporalType.DATE)
	private Date dataSegundaValidacao;
	private boolean terceiraValidacao;
	@Temporal(TemporalType.DATE)
	private Date dataTerceiraValidacao;
	private Integer anoCurricular;
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
	public String getDisciplinaDestino() {
		return disciplinaDestino;
	}
	public void setDisciplinaDestino(String disciplinaDestino) {
		this.disciplinaDestino = disciplinaDestino;
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
	public Integer getIdEquivalencia() {
		return idEquivalencia;
	}
	public void setIdEquivalencia(Integer idEquivalencia) {
		this.idEquivalencia = idEquivalencia;
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
