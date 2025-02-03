package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.TipoDisciplina;

public class DisciplinaAproveitamentoCliente {
	private String disciplina;
	private Integer ano;
	@Enumerated(EnumType.STRING)
	private TipoDisciplina tipo;
	private Float nota;
	private boolean validada;
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public TipoDisciplina getTipo() {
		return tipo;
	}
	public void setTipo(TipoDisciplina tipo) {
		this.tipo = tipo;
	}
	public Float getNota() {
		return nota;
	}
	public void setNota(Float nota) {
		this.nota = nota;
	}
	public boolean isValidada() {
		return validada;
	}
	public void setValidada(boolean validada) {
		this.validada = validada;
	}
}