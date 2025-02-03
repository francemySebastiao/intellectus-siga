package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Turno;

public class TurmaDisponivelCliente {
	private Integer id;
	private int ano;
	private int capacidade;
	private boolean aceitaInscricoes;
	private String curso;
	private Integer cursoCodigo;
	private String turma;
	@Enumerated(EnumType.STRING)
	private Turno turno;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	
	public boolean isAceitaInscricoes() {
		return aceitaInscricoes;
	}
	public void setAceitaInscricoes(boolean aceitaInscricoes) {
		this.aceitaInscricoes = aceitaInscricoes;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Integer getCursoCodigo() {
		return cursoCodigo;
	}
	public void setCursoCodigo(Integer cursoCodigo) {
		this.cursoCodigo = cursoCodigo;
	}
}