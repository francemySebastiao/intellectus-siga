package ao.co.intellectus.model.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.TipoDisciplina;

public class DisciplinaRequest {
	private Integer id;
	private String descricao;
	private String sigla;
	private int anoCorricular;
	private boolean status;
	private boolean opcinal;
	private int horas;
	private int totalCargaHoraria;
	@Enumerated(EnumType.ORDINAL)
	private TipoDisciplina tipo;
	private Integer ponderacao;
	private boolean nuclear;
	private boolean extraCurricular;
	private String controleTransicaoAno;
	private Integer curso;
	private boolean cursoDeVerao;
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
	public int getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(int anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isOpcinal() {
		return opcinal;
	}
	public void setOpcinal(boolean opcinal) {
		this.opcinal = opcinal;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public int getTotalCargaHoraria() {
		return totalCargaHoraria;
	}
	public void setTotalCargaHoraria(int totalCargaHoraria) {
		this.totalCargaHoraria = totalCargaHoraria;
	}
	public TipoDisciplina getTipo() {
		return tipo;
	}
	public void setTipo(TipoDisciplina tipo) {
		this.tipo = tipo;
	}
	public Integer getPonderacao() {
		return ponderacao;
	}
	public void setPonderacao(Integer ponderacao) {
		this.ponderacao = ponderacao;
	}
	public boolean isNuclear() {
		return nuclear;
	}
	public void setNuclear(boolean nuclear) {
		this.nuclear = nuclear;
	}
	public boolean isExtraCurricular() {
		return extraCurricular;
	}
	public void setExtraCurricular(boolean extraCurricular) {
		this.extraCurricular = extraCurricular;
	}
	public String getControleTransicaoAno() {
		return controleTransicaoAno;
	}
	public void setControleTransicaoAno(String controleTransicaoAno) {
		this.controleTransicaoAno = controleTransicaoAno;
	}
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	public boolean isCursoDeVerao() {
		return cursoDeVerao;
	}
	public void setCursoDeVerao(boolean cursoDeVerao) {
		this.cursoDeVerao = cursoDeVerao;
	}

}
