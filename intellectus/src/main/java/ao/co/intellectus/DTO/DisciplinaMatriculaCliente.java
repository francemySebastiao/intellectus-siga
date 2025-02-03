package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Turno;

public class DisciplinaMatriculaCliente {
	private int anoCurricular;
	private Integer curso;
	@Enumerated(EnumType.STRING)
	private Turno turno;
	private Integer tipoInscricao;
	private boolean primeiroAno;
	private boolean segundoAno;
	private boolean terceiroAno;
	private boolean quartoAno;
	private boolean quintoAno;
	private Integer acordoDePagamento;

	public int getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(int anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Integer getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(Integer tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public boolean isPrimeiroAno() {
		return primeiroAno;
	}
	public void setPrimeiroAno(boolean primeiroAno) {
		this.primeiroAno = primeiroAno;
	}
	public boolean isSegundoAno() {
		return segundoAno;
	}
	public void setSegundoAno(boolean segundoAno) {
		this.segundoAno = segundoAno;
	}
	public boolean isTerceiroAno() {
		return terceiroAno;
	}
	public void setTerceiroAno(boolean terceiroAno) {
		this.terceiroAno = terceiroAno;
	}
	public boolean isQuartoAno() {
		return quartoAno;
	}
	public void setQuartoAno(boolean quartoAno) {
		this.quartoAno = quartoAno;
	}
	public boolean isQuintoAno() {
		return quintoAno;
	}
	public void setQuintoAno(boolean quintoAno) {
		this.quintoAno = quintoAno; 
	}
	public Integer getAcordoDePagamento() {
		return acordoDePagamento;
	}
	public void setAcordoDePagamento(Integer acordoDePagamento) {
		this.acordoDePagamento = acordoDePagamento;
	}
}
