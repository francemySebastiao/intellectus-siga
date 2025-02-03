package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.TipoDisciplina;

public class DisciplinaConcluidaCliente {
    private String disciplina;
    private Integer anoCurricular;
    @Enumerated(EnumType.STRING)
    private TipoDisciplina tipo;
    private Float nota;
    private String anoLectivo;
    private String sigla;
    private boolean equivalencia;
    private Integer totalConcluidas;
    private Integer totalCurso;
    private String anoLectivoDescricao;
    
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
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
	public String getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(String anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public boolean isEquivalencia() {
		return equivalencia;
	}
	public void setEquivalencia(boolean equivalencia) {
		this.equivalencia = equivalencia;
	}
	public Integer getTotalConcluidas() {
		return totalConcluidas;
	}
	public void setTotalConcluidas(Integer totalConcluidas) {
		this.totalConcluidas = totalConcluidas;
	}
	public Integer getTotalCurso() {
		return totalCurso;
	}
	public void setTotalCurso(Integer totalCurso) {
		this.totalCurso = totalCurso;
	}
}
