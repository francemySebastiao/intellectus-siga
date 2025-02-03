package ao.co.intellectus.DTO;

import ao.co.intellectus.model.TipoDisciplina;

public class DisciplinaCliente {
	private Integer idHistorico;
	private boolean adicionar;
	private boolean remover;
    private Integer id;
    private String descricao;
    private Integer anoCurricular;
    private String sigla;
    private Integer idTurma;
    private String turma;
    private boolean isMatricula;
    private boolean disciplinaChecada;
    private boolean concluida;
    private boolean matriculaCorrente;
    private TipoDisciplina tipoDisciplina;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public Integer getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public boolean isMatricula() {
		return isMatricula;
	}
	public void setMatricula(boolean isMatricula) {
		this.isMatricula = isMatricula;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public boolean isConcluida() {
		return concluida;
	}
	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}
	public boolean isDisciplinaChecada() {
		return disciplinaChecada;
	}
	public void setDisciplinaChecada(boolean disciplinaChecada) {
		this.disciplinaChecada = disciplinaChecada;
	}
	public TipoDisciplina getTipoDisciplina() {
		return tipoDisciplina;
	}
	public void setTipoDisciplina(TipoDisciplina tipoDisciplina) {
		this.tipoDisciplina = tipoDisciplina;
	}
	public boolean isMatriculaCorrente() {
		return matriculaCorrente;
	}
	public void setMatriculaCorrente(boolean matriculaCorrente) {
		this.matriculaCorrente = matriculaCorrente;
	}
	public Integer getIdHistorico() {
		return idHistorico;
	}
	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}
	public boolean isRemover() {
		return remover;
	}
	public void setRemover(boolean remover) {
		this.remover = remover;
	}
	public boolean isAdicionar() {
		return adicionar;
	}
	public void setAdicionar(boolean adicionar) {
		this.adicionar = adicionar;
	}
}