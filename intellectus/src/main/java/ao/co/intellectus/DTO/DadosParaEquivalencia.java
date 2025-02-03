package ao.co.intellectus.DTO;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Documento;

public class DadosParaEquivalencia {
	private Integer numero;
	private String nome;
	@Enumerated(EnumType.STRING)
	private Documento tipoDocumento;
	private String numeroDocumento;
	private String escolaOrigem;
	private Integer escolaOrigemId;
	private String cursoDestino;
	private Integer cursoDestinoId;
	private List<CursoOrigemCliente> curosAlunos;
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Documento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(Documento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getEscolaOrigem() {
		return escolaOrigem;
	}
	public void setEscolaOrigem(String escolaOrigem) {
		this.escolaOrigem = escolaOrigem;
	}
	public Integer getEscolaOrigemId() {
		return escolaOrigemId;
	}
	public void setEscolaOrigemId(Integer escolaOrigemId) {
		this.escolaOrigemId = escolaOrigemId;
	}
	public String getCursoDestino() {
		return cursoDestino;
	}
	public void setCursoDestino(String cursoDestino) {
		this.cursoDestino = cursoDestino;
	}
	public Integer getCursoDestinoId() {
		return cursoDestinoId;
	}
	public void setCursoDestinoId(Integer cursoDestinoId) {
		this.cursoDestinoId = cursoDestinoId;
	}
	public List<CursoOrigemCliente> getCurosAlunos() {
		return curosAlunos;
	}
	public void setCurosAlunos(List<CursoOrigemCliente> curosAlunos) {
		this.curosAlunos = curosAlunos;
	}
}
