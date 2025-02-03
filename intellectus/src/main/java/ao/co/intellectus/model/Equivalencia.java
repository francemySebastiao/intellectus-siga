package ao.co.intellectus.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_equivalencia")
public class Equivalencia {
	@Id 
	@GeneratedValue
	private Integer id;
	private Integer numeroAluno;
	@Enumerated(EnumType.STRING)
	private TipoEquivalencia tipo;
	private String escolaOrigem;
	@Temporal(TemporalType.DATE)
	private Date dataEquivalencia;
	private String cursoOrigem;
	@ManyToOne
	@JoinColumn(name="codigo_curso_destino")
	private Curso cursoDestino;
	@ManyToOne
	@JoinColumn(name="codigo_aluno")
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name="codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	private boolean fechada;
	
	@ManyToOne
	@JoinColumn(name="codigo_usuario_acordo")
	private Usuario usuarioEquivalencia;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}
	public TipoEquivalencia getTipo() {
		return tipo;
	}
	public void setTipo(TipoEquivalencia tipo) {
		this.tipo = tipo;
	}
	public String getEscolaOrigem() {
		return escolaOrigem;
	}
	public void setEscolaOrigem(String escolaOrigem) {
		this.escolaOrigem = escolaOrigem;
	}
	public Date getDataEquivalencia() {
		return dataEquivalencia;
	}
	public void setDataEquivalencia(Date dataEquivalencia) {
		this.dataEquivalencia = dataEquivalencia;
	}
	public String getCursoOrigem() {
		return cursoOrigem;
	}
	public void setCursoOrigem(String cursoOrigem) {
		this.cursoOrigem = cursoOrigem;
	}
	public Curso getCursoDestino() {
		return cursoDestino;
	}
	public void setCursoDestino(Curso cursoDestino) {
		this.cursoDestino = cursoDestino;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public boolean isFechada() {
		return fechada;
	}
	public void setFechada(boolean fechada) {
		this.fechada = fechada;
	}
	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Usuario getUsuarioEquivalencia() {
		return usuarioEquivalencia;
	}
	public void setUsuarioEquivalencia(Usuario usuarioEquivalencia) {
		this.usuarioEquivalencia = usuarioEquivalencia;
	}
}