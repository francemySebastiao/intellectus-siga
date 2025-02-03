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
@Table(name = "t_historico_equivalencia_audit")
public class EquivalenciaHistoricoAlunoAudit {
	@Id
	@GeneratedValue
	private Integer id;
	private String disciplinaOrigem;
	private float notaOrigem;
	@ManyToOne
	@JoinColumn(name = "codigo_equivalencia")
	private EquivalenciaAudit equivalenciaAudit;
	@ManyToOne
	@JoinColumn(name = "codigo_disciplina_destino")
	private Disciplina disciplinaDestino;
	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	private AlunoAudit alunoAudit;
	private Integer numeroAluno;
	private boolean primeiraValidacao;
	@Temporal(TemporalType.DATE)
	private Date dataPrimeiraValidacao;
	private boolean segundaValidacao;
	@Temporal(TemporalType.DATE)
	private Date dataSegundaValidacao;
	private boolean terceiraValidacao;
	@Temporal(TemporalType.DATE)
	private Date dataTerceiraValidacao;
	@Column(nullable = true)
	private boolean estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getDisciplinaOrigem() {
		return disciplinaOrigem;
	}

	public void setDisciplinaOrigem(String disciplinaOrigem) {
		this.disciplinaOrigem = disciplinaOrigem;
	}

	public float getNotaOrigem() {
		return notaOrigem;
	}

	public void setNotaOrigem(float notaOrigem) {
		this.notaOrigem = notaOrigem;
	}

	public Disciplina getDisciplinaDestino() {
		return disciplinaDestino;
	}

	public void setDisciplinaDestino(Disciplina disciplinaDestino) {
		this.disciplinaDestino = disciplinaDestino;
	}

	public boolean isPrimeiraValidacao() {
		return primeiraValidacao;
	}

	public void setPrimeiraValidacao(boolean primeiraValidacao) {
		this.primeiraValidacao = primeiraValidacao;
	}

	public Date getDataPrimeiraValidacao() {
		return dataPrimeiraValidacao;
	}

	public void setDataPrimeiraValidacao(Date dataPrimeiraValidacao) {
		this.dataPrimeiraValidacao = dataPrimeiraValidacao;
	}

	public boolean isSegundaValidacao() {
		return segundaValidacao;
	}

	public void setSegundaValidacao(boolean segundaValidacao) {
		this.segundaValidacao = segundaValidacao;
	}

	public Date getDataSegundaValidacao() {
		return dataSegundaValidacao;
	}

	public void setDataSegundaValidacao(Date dataSegundaValidacao) {
		this.dataSegundaValidacao = dataSegundaValidacao;
	}

	public boolean isTerceiraValidacao() {
		return terceiraValidacao;
	}

	public void setTerceiraValidacao(boolean terceiraValidacao) {
		this.terceiraValidacao = terceiraValidacao;
	}

	public Date getDataTerceiraValidacao() {
		return dataTerceiraValidacao;
	}

	public void setDataTerceiraValidacao(Date dataTerceiraValidacao) {
		this.dataTerceiraValidacao = dataTerceiraValidacao;
	}
	
	public EquivalenciaAudit getEquivalenciaAudit() {
		return equivalenciaAudit;
	}

	public void setEquivalenciaAudit(EquivalenciaAudit equivalenciaAudit) {
		this.equivalenciaAudit = equivalenciaAudit;
	}

	public AlunoAudit getAlunoAudit() {
		return alunoAudit;
	}

	public void setAlunoAudit(AlunoAudit alunoAudit) {
		this.alunoAudit = alunoAudit;
	}
	 
	public Integer getNumeroAluno() {
		return numeroAluno;
	}

	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}