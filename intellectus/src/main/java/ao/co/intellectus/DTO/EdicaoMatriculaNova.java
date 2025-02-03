package ao.co.intellectus.DTO;

import java.util.List;

public class EdicaoMatriculaNova {
	private Integer idMatricula;
	private Integer numeroDeAluno;
	private String nome;
	private String curso;
	private Integer duracaoCurso;
	private Integer cursoCodigo;
	private Integer anoCurricular;
	private boolean contencioso;
	private boolean fimCurso;
	private boolean inativo;
	private Integer anoLectivo;
	private Integer turmaBaseId;
	private String turmaBaseDescricao;
	private boolean bolseiro;
	private Integer percentagemDesconto;
	private Integer idPlanoDePagamento;
	private List<PlanoPagamentoEdicao> planosDePagamento;
	private Integer idEmpresaConvenio;
	private List<BolseiroMatriculaEdicao> empresasConevenio;
	private List<TipoInscricaoEdicao> tipoInscricoes;
	private List<DisciplinaCliente> disciplinas;
	private String userName;
	private Integer userCode;

	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Integer getDuracaoCurso() {
		return duracaoCurso;
	}

	public void setDuracaoCurso(Integer duracaoCurso) {
		this.duracaoCurso = duracaoCurso;
	}

	public Integer getCursoCodigo() {
		return cursoCodigo;
	}

	public void setCursoCodigo(Integer cursoCodigo) {
		this.cursoCodigo = cursoCodigo;
	}

	public Integer getAnoCurricular() {
		return anoCurricular;
	}

	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}

	public boolean isContencioso() {
		return contencioso;
	}

	public void setContencioso(boolean contencioso) {
		this.contencioso = contencioso;
	}

	public boolean isFimCurso() {
		return fimCurso;
	}

	public void setFimCurso(boolean fimCurso) {
		this.fimCurso = fimCurso;
	}

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public List<DisciplinaCliente> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaCliente> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<TipoInscricaoEdicao> getTipoInscricoes() {
		return tipoInscricoes;
	}

	public void setTipoInscricoes(List<TipoInscricaoEdicao> tipoInscricoes) {
		this.tipoInscricoes = tipoInscricoes;
	}

	public Integer getTurmaBaseId() {
		return turmaBaseId;
	}

	public void setTurmaBaseId(Integer turmaBaseId) {
		this.turmaBaseId = turmaBaseId;
	}

	public String getTurmaBaseDescricao() {
		return turmaBaseDescricao;
	}

	public void setTurmaBaseDescricao(String turmaBaseDescricao) {
		this.turmaBaseDescricao = turmaBaseDescricao;
	}

	public List<BolseiroMatriculaEdicao> getEmpresasConevenio() {
		return empresasConevenio;
	}

	public void setEmpresasConevenio(List<BolseiroMatriculaEdicao> empresasConevenio) {
		this.empresasConevenio = empresasConevenio;
	}

	public boolean isBolseiro() {
		return bolseiro;
	}

	public void setBolseiro(boolean bolseiro) {
		this.bolseiro = bolseiro;
	}

	public List<PlanoPagamentoEdicao> getPlanosDePagamento() {
		return planosDePagamento;
	}

	public void setPlanosDePagamento(List<PlanoPagamentoEdicao> planosDePagamento) {
		this.planosDePagamento = planosDePagamento;
	}

	public Integer getPercentagemDesconto() {
		return percentagemDesconto;
	}

	public void setPercentagemDesconto(Integer percentagemDesconto) {
		this.percentagemDesconto = percentagemDesconto;
	}

	public Integer getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Integer getIdPlanoDePagamento() {
		return idPlanoDePagamento;
	}

	public void setIdPlanoDePagamento(Integer idPlanoDePagamento) {
		this.idPlanoDePagamento = idPlanoDePagamento;
	}

	public Integer getIdEmpresaConvenio() {
		return idEmpresaConvenio;
	}

	public void setIdEmpresaConvenio(Integer idEmpresaConvenio) {
		this.idEmpresaConvenio = idEmpresaConvenio;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}
}
