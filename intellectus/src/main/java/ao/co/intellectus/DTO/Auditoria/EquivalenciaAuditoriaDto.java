package ao.co.intellectus.DTO.Auditoria;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ao.co.intellectus.model.HistoricoEquivalencia;
@JsonInclude(Include.NON_NULL)
public class EquivalenciaAuditoriaDto {
	
	private String anoLectivoDescricao;
	private String userRealizouEquivalencia;;
	private String escolaOrigem;
	private String cursoOrigem;
	private String cursoDestino;
	private Date  dataEquivalencia;
	private float notaOrigem;
	private String disciplinaOrigem;
	private String disciplinaDestino;
	private boolean primeniraValidacao;
	private Date dataPrimeiraValidacao;
	private boolean segundaValidacao;
	private Date dataSegundaValidacao;
	private boolean terceiraValidacao;
	private Date dataTerceiraValidacao;
	private boolean estado;
	private boolean fechada;
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public String getUserRealizouEquivalencia() {
		return userRealizouEquivalencia;
	}
	public void setUserRealizouEquivalencia(String userRealizouEquivalencia) {
		this.userRealizouEquivalencia = userRealizouEquivalencia;
	}
	public String getEscolaOrigem() {
		return escolaOrigem;
	}
	public void setEscolaOrigem(String escolaOrigem) {
		this.escolaOrigem = escolaOrigem;
	}
	public String getCursoOrigem() {
		return cursoOrigem;
	}
	public void setCursoOrigem(String cursoOrigem) {
		this.cursoOrigem = cursoOrigem;
	}
	public String getCursoDestino() {
		return cursoDestino;
	}
	public void setCursoDestino(String cursoDestino) {
		this.cursoDestino = cursoDestino;
	}
	public Date getDataEquivalencia() {
		return dataEquivalencia;
	}
	public void setDataEquivalencia(Date dataEquivalencia) {
		this.dataEquivalencia = dataEquivalencia;
	}
	public float getNotaOrigem() {
		return notaOrigem;
	}
	public void setNotaOrigem(float notaOrigem) {
		this.notaOrigem = notaOrigem;
	}
	public String getDisciplinaOrigem() {
		return disciplinaOrigem;
	}
	public void setDisciplinaOrigem(String disciplinaOrigem) {
		this.disciplinaOrigem = disciplinaOrigem;
	}
	public String getDisciplinaDestino() {
		return disciplinaDestino;
	}
	public void setDisciplinaDestino(String disciplinaDestino) {
		this.disciplinaDestino = disciplinaDestino;
	}
	public boolean isPrimeniraValidacao() {
		return primeniraValidacao;
	}
	public void setPrimeniraValidacao(boolean primeniraValidacao) {
		this.primeniraValidacao = primeniraValidacao;
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
	public boolean isFechada() {
		return fechada;
	}
	public void setFechada(boolean fechada) {
		this.fechada = fechada;
	}
	
	
}
