package ao.co.intellectus.model.request;

public class TurmaDisponivelRequest {
	private Integer id;
	private int ano;
	private int capacidade;
	private boolean aceitaInscricoes;
	private Integer curso;
	private Integer turma;
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
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	public Integer getTurma() {
		return turma;
	}
	public void setTurma(Integer turma) {
		this.turma = turma;
	}

}
