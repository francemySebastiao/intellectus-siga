package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.TipoDisciplina;

public class DisciplinaCursandoCliente {
	private Integer idMatricula;
	private Integer anoCurricular;
	private String disciplina;
	private Integer anoLectivo;
    @Enumerated(EnumType.STRING)
    private TipoDisciplina tipo;
    
    private String turma;
    //AVALIAÇÕES
    private Float primeiraAvaliacao;
    private Float SegundaAvaliacvao;
    private Float terceiraAvaliacao;
    private Float quartaAvaliacao;
    private Float pratica;
    private Float finalFrequencia;
    private Float notaExame;
    private Float notaExameOral;
    private Float notaRecurso;
    private Float notaRecursoOral;
    private Float melhoriaNota;
    private Float notaFinal; 
    private Date dataNotaFinal;
    private Float notaEpocaEspecial;
    private Float notaEpocaEspecialOral;
    private boolean validado;
    private boolean visibilidade;
    private String anoLectivoDescricao;
	public String getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(String anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public Integer getIdMatricula() {
		return idMatricula;
	}
	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public TipoDisciplina getTipo() {
		return tipo;
	}
	public void setTipo(TipoDisciplina tipo) {
		this.tipo = tipo;
	}
	public Float getFinalFrequencia() {
		return finalFrequencia;
	}
	public void setFinalFrequencia(Float finalFrequencia) {
		this.finalFrequencia = finalFrequencia;
	}
	public Float getNotaExame() {
		return notaExame;
	}
	public void setNotaExame(Float notaExame) {
		this.notaExame = notaExame;
	}
	public Float getNotaExameOral() {
		return notaExameOral;
	}
	public void setNotaExameOral(Float notaExameOral) {
		this.notaExameOral = notaExameOral;
	}
	public Float getNotaRecurso() {
		return notaRecurso;
	}
	public void setNotaRecurso(Float notaRecurso) {
		this.notaRecurso = notaRecurso;
	}
	public Float getNotaRecursoOral() {
		return notaRecursoOral;
	}
	public void setNotaRecursoOral(Float notaRecursoOral) {
		this.notaRecursoOral = notaRecursoOral;
	}
	public Float getMelhoriaNota() {
		return melhoriaNota;
	}
	public void setMelhoriaNota(Float melhoriaNota) {
		this.melhoriaNota = melhoriaNota;
	}
	public Float getNotaFinal() {
		return notaFinal;
	}
	public void setNotaFinal(Float notaFinal) {
		this.notaFinal = notaFinal;
	}
	public Date getDataNotaFinal() {
		return dataNotaFinal;
	}
	public void setDataNotaFinal(Date dataNotaFinal) {
		this.dataNotaFinal = dataNotaFinal;
	}
	public Float getNotaEpocaEspecial() {
		return notaEpocaEspecial;
	}
	public void setNotaEpocaEspecial(Float notaEpocaEspecial) {
		this.notaEpocaEspecial = notaEpocaEspecial;
	}
	public Float getNotaEpocaEspecialOral() {
		return notaEpocaEspecialOral;
	}
	public void setNotaEpocaEspecialOral(Float notaEpocaEspecialOral) {
		this.notaEpocaEspecialOral = notaEpocaEspecialOral;
	}
	public boolean isValidado() {
		return validado;
	}
	public void setValidado(boolean validado) {
		this.validado = validado;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public Float getPrimeiraAvaliacao() {
		return primeiraAvaliacao;
	}
	public void setPrimeiraAvaliacao(Float primeiraAvaliacao) {
		this.primeiraAvaliacao = primeiraAvaliacao;
	}
	public Float getSegundaAvaliacvao() {
		return SegundaAvaliacvao;
	}
	public void setSegundaAvaliacvao(Float segundaAvaliacvao) {
		SegundaAvaliacvao = segundaAvaliacvao;
	}
	public Float getTerceiraAvaliacao() {
		return terceiraAvaliacao;
	}
	public void setTerceiraAvaliacao(Float terceiraAvaliacao) {
		this.terceiraAvaliacao = terceiraAvaliacao;
	}
	public Float getQuartaAvaliacao() {
		return quartaAvaliacao;
	}
	public void setQuartaAvaliacao(Float quartaAvaliacao) {
		this.quartaAvaliacao = quartaAvaliacao;
	}
	public Float getPratica() {
		return pratica;
	}
	public void setPratica(Float pratica) {
		this.pratica = pratica;
	}
	public boolean isVisibilidade() {
		return visibilidade;
	}
	public void setVisibilidade(boolean visibilidade) {
		this.visibilidade = visibilidade;
	}
}