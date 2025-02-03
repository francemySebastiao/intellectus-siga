package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_historico_troca_curso")
public class HistoricoTrocaCurso {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="codigo_curso_antigo")
	private Curso cursoAtigo;
	@ManyToOne
	@JoinColumn(name="codigo_curso_novo")
	private Curso cursoNovo;
	@ManyToOne
	@JoinColumn(name="codigo_aluno")
	private Aluno aluno;
	private Integer numeroDeAluno;
	@Column(nullable=true)
	private boolean equivalenciaEfetivada;
	
	@Temporal(TemporalType.DATE)
	private Date dataTrocaCurso;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Curso getCursoAtigo() {
		return cursoAtigo;
	}
	public void setCursoAtigo(Curso cursoAtigo) {
		this.cursoAtigo = cursoAtigo;
	}
	public Curso getCursoNovo() {
		return cursoNovo;
	}
	public void setCursoNovo(Curso cursoNovo) {
		this.cursoNovo = cursoNovo;
	}
	public Date getDataTrocaCurso() {
		return dataTrocaCurso;
	}
	public void setDataTrocaCurso(Date dataTrocaCurso) {
		this.dataTrocaCurso = dataTrocaCurso;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
}
