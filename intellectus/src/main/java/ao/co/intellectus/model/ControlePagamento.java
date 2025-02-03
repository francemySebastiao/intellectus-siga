package ao.co.intellectus.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class ControlePagamento {
    @Id
    @GeneratedValue
	private Integer id;
    private Integer anoCurricular;
    private boolean anulado;
    private boolean contenciso;
    @ManyToOne
    @JoinColumn(name="codigo_aluno")
    private Aluno numeroDeAluno;
    @ManyToOne
    @JoinColumn(name="codigo_curso")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name="codigo_ano_lectivo")
    private AnoLectivo anoLectivo;
    private String nome;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Aluno getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Aluno numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	public boolean isContenciso() {
		return contenciso;
	}
	public void setContenciso(boolean contenciso) {
		this.contenciso = contenciso;
	}
}
