package ao.co.intellectus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="t_turmas_disponiveis")
public class TurmaDisponivel {
	
	@Id
	@GeneratedValue
	private Integer id;
	private int ano;
	private int capacidade;
	private boolean aceitaInscricoes;
	
	@ManyToOne
	@JoinColumn(name = "codigo_curso")
	private Curso curso;
	@ManyToOne
	@JoinColumn(name = "codigo_turma")
	private Turma turma;
	@Transient
	private Integer inscritos;
	

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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Integer getInscritos() {
		return inscritos;
	}

	public void setInscritos(Integer inscritos) {
		this.inscritos = inscritos;
	}
}