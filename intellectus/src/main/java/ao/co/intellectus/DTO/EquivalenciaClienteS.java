package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.TipoEquivalencia;

public class EquivalenciaClienteS {
	private Integer id;
	private Integer numeroAluno;
	@Enumerated(EnumType.STRING)
	private TipoEquivalencia tipo;
	private String escolaOrigem;
	@Temporal(TemporalType.DATE)
	private Date dataEquivalencia;
	private String cursoOrigem;
	private String cursoDestino;
	private Integer cursoId;
	
	private String nome;
	private boolean fechada;
	//private List<HistoricoEquivalenciaCliente> historico;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public TipoEquivalencia getTipo() {
		return tipo;
	}
	public void setTipo(TipoEquivalencia tipo) {
		this.tipo = tipo;
	}
	public String getEscolaOrigem() {
		return escolaOrigem;
	}
	public void setEscolaOrigem(String escolaOrigem) {
		this.escolaOrigem = escolaOrigem;
	}
	public Date getDataEquivalencia() {
		return dataEquivalencia;
	}
	public void setDataEquivalencia(Date dataEquivalencia) {
		this.dataEquivalencia = dataEquivalencia;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isFechada() {
		return fechada;
	}
	public void setFechada(boolean fechada) {
		this.fechada = fechada;
	}
	public Integer getCursoId() {
		return cursoId;
	}
	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}
}
