package ao.co.intellectus.model;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "t_matricula_audit")
public class MatriculaAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_matricula")
	private Date data;
	private boolean anulado;
	@Temporal(TemporalType.DATE)
	private Date dataAnulamento;
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	private String numeroDeAluno;
	private Integer anoCurricular;
	@Column(name = "ano_lectivo")
	private String anoLectivoMatricula;
	private boolean trabalhador;
	private boolean transitaAno;
	private Integer percentagemDesconto;
	// JUNÇÕES COM OUTRAS TABELAS...
	@ManyToOne
	@JoinColumn(name = "codigo_empres_convenio")
	private EmpresaConvenio empresaConvenio;
	@ManyToOne
	@JoinColumn(name = "codigo_tipo_inscricao")
	private TipoInscricao tipoInscricao;
	@ManyToOne
	@JoinColumn(name = "codigo_ano_lectivo")
	private AnoLectivo anoLectivo;
	@ManyToOne
	@JoinColumn(name = "codigo_curso")
	private Curso curso;
	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	private AlunoAudit AlunoAudit;
	@ManyToOne
	@JoinColumn(name = "codigo_turma_base")
	private Turma turmaBase;
	@ManyToOne
	@JoinColumn(name = "codigo_plano_pagamento")
	private PlanoPagamento planoPagamento;

	@Column(nullable = true)
	private boolean processamentoReferencia;

	@ManyToOne
	@JoinColumn(name = "codigo_usuario_inscreveu")
	private Usuario UsuarioInscreveu;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInscricao;

	@ManyToOne
	@JoinColumn(name = "codigo_usuario_alterou")
	private Usuario UsuarioAlterou;

	@ManyToOne
	@JoinColumn(name = "codigo_alterou_tipo_inscricao")
	private Usuario alterouTipoInscricao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracaoTipoInscricao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAlteracao;

	@ManyToOne
	@JoinColumn(name = "codigo_usuario_anulou")
	private Usuario usuarioAnulou;

	@Transient
	@Enumerated(EnumType.STRING)
	private Permissao permissao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public Date getDataAnulamento() {
		return dataAnulamento;
	}

	public void setDataAnulamento(Date dataAnulamento) {
		this.dataAnulamento = dataAnulamento;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}

	public Integer getAnoCurricular() {
		return anoCurricular;
	}

	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}

	public String getAnoLectivoMatricula() {
		return anoLectivoMatricula;
	}

	public void setAnoLectivoMatricula(String anoLectivoMatricula) {
		this.anoLectivoMatricula = anoLectivoMatricula;
	}

	public boolean isTrabalhador() {
		return trabalhador;
	}

	public void setTrabalhador(boolean trabalhador) {
		this.trabalhador = trabalhador;
	}

	public boolean isTransitaAno() {
		return transitaAno;
	}

	public void setTransitaAno(boolean transitaAno) {
		this.transitaAno = transitaAno;
	}

	public Integer getPercentagemDesconto() {
		return percentagemDesconto;
	}

	public void setPercentagemDesconto(Integer percentagemDesconto) {
		this.percentagemDesconto = percentagemDesconto;
	}

	public EmpresaConvenio getEmpresaConvenio() {
		return empresaConvenio;
	}

	public void setEmpresaConvenio(EmpresaConvenio empresaConvenio) {
		this.empresaConvenio = empresaConvenio;
	}

	public TipoInscricao getTipoInscricao() {
		return tipoInscricao;
	}

	public void setTipoInscricao(TipoInscricao tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}

	public AnoLectivo getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(AnoLectivo anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public AlunoAudit getAlunoAudit() {
		return AlunoAudit;
	}

	public void setAlunoAudit(AlunoAudit alunoAudit) {
		AlunoAudit = alunoAudit;
	}

	public Turma getTurmaBase() {
		return turmaBase;
	}

	public void setTurmaBase(Turma turmaBase) {
		this.turmaBase = turmaBase;
	}

	public PlanoPagamento getPlanoPagamento() {
		return planoPagamento;
	}

	public void setPlanoPagamento(PlanoPagamento planoPagamento) {
		this.planoPagamento = planoPagamento;
	}

	public boolean isProcessamentoReferencia() {
		return processamentoReferencia;
	}

	public void setProcessamentoReferencia(boolean processamentoReferencia) {
		this.processamentoReferencia = processamentoReferencia;
	}

	public Usuario getUsuarioInscreveu() {
		return UsuarioInscreveu;
	}

	public void setUsuarioInscreveu(Usuario usuarioInscreveu) {
		UsuarioInscreveu = usuarioInscreveu;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public Usuario getUsuarioAlterou() {
		return UsuarioAlterou;
	}

	public void setUsuarioAlterou(Usuario usuarioAlterou) {
		UsuarioAlterou = usuarioAlterou;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Usuario getAlterouTipoInscricao() {
		return alterouTipoInscricao;
	}

	public void setAlterouTipoInscricao(Usuario alterouTipoInscricao) {
		this.alterouTipoInscricao = alterouTipoInscricao;
	}

	public Date getDataAlteracaoTipoInscricao() {
		return dataAlteracaoTipoInscricao;
	}

	public void setDataAlteracaoTipoInscricao(Date dataAlteracaoTipoInscricao) {
		this.dataAlteracaoTipoInscricao = dataAlteracaoTipoInscricao;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public Usuario getUsuarioAnulou() {
		return usuarioAnulou;
	}

	public void setUsuarioAnulou(Usuario usuarioAnulou) {
		this.usuarioAnulou = usuarioAnulou;
	}

}
