package ao.co.intellectus.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Semestre;

public class MatriculaEditarCliente {
	//DADOS DO ALUNO
	private String nomeAluno;
	private Integer numeroDeAluno;
	private String curso;
	private Integer cursoId;
	//DADOS DA MATRICULA
	private Integer anoCorricularInscricao;
	private Integer anoLectivo;
	private Integer anoLectivoDescricao;
	private Integer turmaBase;
	private String turmaBaseDescricao;
	private Integer tipoInscricao;
	private String tipoInscricaoDescricao;
	private Integer planoPagamento;
	private String planoPagamentoDescricao;
	private Integer empresaBolsa;
	private String empresaBolsaDescricao;
	private Integer percentagemDesconto;
	@Temporal(TemporalType.DATE)
	private Date dataMatricula;
	private boolean anulado;
	private boolean fimCurso;
	private boolean contenciso;
	private boolean inactivo;
	@Temporal(TemporalType.DATE)
	private Date dataAnulacao;
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	private List<HistoricoAlunoEditar> historico;
	private List<DisciplinaResumo> disciplinas;
	private List<TurmaCliente> turmas;
	private boolean bolseiro;
	
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Integer getAnoCorricularInscricao() {
		return anoCorricularInscricao;
	}
	public void setAnoCorricularInscricao(Integer anoCorricularInscricao) {
		this.anoCorricularInscricao = anoCorricularInscricao;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public Integer getAnoLectivoDescricao() {
		return anoLectivoDescricao;
	}
	public void setAnoLectivoDescricao(Integer anoLectivoDescricao) {
		this.anoLectivoDescricao = anoLectivoDescricao;
	}
	public Integer getTurmaBase() {
		return turmaBase;
	}
	public void setTurmaBase(Integer turmaBase) {
		this.turmaBase = turmaBase;
	}
	public String getTurmaBaseDescricao() {
		return turmaBaseDescricao;
	}
	public void setTurmaBaseDescricao(String turmaBaseDescricao) {
		this.turmaBaseDescricao = turmaBaseDescricao;
	}
	public Integer getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(Integer tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
	public String getTipoInscricaoDescricao() {
		return tipoInscricaoDescricao;
	}
	public void setTipoInscricaoDescricao(String tipoInscricaoDescricao) {
		this.tipoInscricaoDescricao = tipoInscricaoDescricao;
	}
	public Integer getPlanoPagamento() {
		return planoPagamento;
	}
	public void setPlanoPagamento(Integer planoPagamento) {
		this.planoPagamento = planoPagamento;
	}
	public String getPlanoPagamentoDescricao() {
		return planoPagamentoDescricao;
	}
	public void setPlanoPagamentoDescricao(String planoPagamentoDescricao) {
		this.planoPagamentoDescricao = planoPagamentoDescricao;
	}
	public Integer getEmpresaBolsa() {
		return empresaBolsa;
	}
	public void setEmpresaBolsa(Integer empresaBolsa) {
		this.empresaBolsa = empresaBolsa;
	}
	public String getEmpresaBolsaDescricao() {
		return empresaBolsaDescricao;
	}
	public void setEmpresaBolsaDescricao(String empresaBolsaDescricao) {
		this.empresaBolsaDescricao = empresaBolsaDescricao;
	}
	public Integer getPercentagemDesconto() {
		return percentagemDesconto;
	}
	public void setPercentagemDesconto(Integer percentagemDesconto) {
		this.percentagemDesconto = percentagemDesconto;
	}
	public Date getDataMatricula() {
		return dataMatricula;
	}
	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	public Date getDataAnulacao() {
		return dataAnulacao;
	}
	public void setDataAnulacao(Date dataAnulacao) {
		this.dataAnulacao = dataAnulacao;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public List<HistoricoAlunoEditar> getHistorico() {
		return historico;
	}
	public void setHistorico(List<HistoricoAlunoEditar> historico) {
		this.historico = historico;
	}
	public List<DisciplinaResumo> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<DisciplinaResumo> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public List<TurmaCliente> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<TurmaCliente> turmas) {
		this.turmas = turmas;
	}
	public boolean isBolseiro() {
		return bolseiro;
	}
	public void setBolseiro(boolean bolseiro) {
		this.bolseiro = bolseiro;
	}
	public boolean isFimCurso() {
		return fimCurso;
	}
	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}
	public boolean isContenciso() {
		return contenciso;
	}
	public void setContenciso(boolean contenciso) {
		this.contenciso = contenciso;
	}
	public boolean isInactivo() {
		return inactivo;
	}
	public void setInactivo(boolean inactivo) {
		this.inactivo = inactivo;
	}
	public Integer getCursoId() {
		return cursoId;
	}
	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}
}
