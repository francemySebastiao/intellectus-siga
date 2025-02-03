package ao.co.intellectus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_disciplina")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String descricao;
	private String sigla;
	private int anoCorricular;
	private boolean status;
	private boolean opcinal;
	private int horas;
	private int totalCargaHoraria;
	@Enumerated(EnumType.STRING)
	private TipoDisciplina tipo;
	private Integer ponderacao;
	private boolean nuclear;
	private boolean extraCurricular;
	private String controleTransicaoAno;
	@ManyToOne
	@JoinColumn(name = "codigo_curso")
	private Curso curso;
	@Column(nullable = true, name = "curso_de_verao")
	private Boolean cursoDeVerao;
	@Column(nullable = true)
	private boolean aprovacaoDisciplina;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public int getAnoCorricular() {
		return anoCorricular;
	}

	public void setAnoCorricular(int anoCorricular) {
		this.anoCorricular = anoCorricular;
	}

	public Boolean getCursoDeVerao() {
		return cursoDeVerao;
	}

	public void setCursoDeVerao(Boolean cursoDeVerao) {
		this.cursoDeVerao = cursoDeVerao;
	}

	public boolean isAprovacaoDisciplina() {
		return aprovacaoDisciplina;
	}

	public void setAprovacaoDisciplina(boolean aprovacaoDisciplina) {
		this.aprovacaoDisciplina = aprovacaoDisciplina;
	}

	
}