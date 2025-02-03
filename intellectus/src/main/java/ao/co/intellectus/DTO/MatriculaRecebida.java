package ao.co.intellectus.DTO;

import java.util.List;

public class MatriculaRecebida {
	private Integer id;
	private Integer numeroDeAluno;
	private Integer empresaConvenio;
	private Integer empresaBolsa;
	private boolean bolseiro;
	private Integer cursoID;
	private String curso;
	private String descricaoCurso;
	private String nome;
	private Integer tipoInscricao;
	private Integer anoMatricula;
	private Integer anoCorricularInscricao;
	private Integer anoLectivo;
	private String dataMatricula;
	private Integer planoPagamento;
	private Integer turmaBase;
	private String userName;
	private Integer userCode;
	private Integer percentagemDesconto;
	private List<InputDisciplinaViewModel> disciplinaTurma;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpresaConvenio() {
		return empresaConvenio;
	}

	public void setEmpresaConvenio(Integer empresaConvenio) {
		this.empresaConvenio = empresaConvenio;
	}


	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public boolean isBolseiro() {
		return bolseiro;
	}

	public void setBolseiro(boolean bolseiro) {
		this.bolseiro = bolseiro;
	}

	public Integer getCursoID() {
		return cursoID;
	}

	public void setCursoID(Integer cursoID) {
		this.cursoID = cursoID;
	}

	public String getDescricaoCurso() {
		return descricaoCurso;
	}

	public void setDescricaoCurso(String descricaoCurso) {
		this.descricaoCurso = descricaoCurso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTipoInscricao() {
		return tipoInscricao;
	}

	public void setTipoInscricao(Integer tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}

	public Integer getAnoMatricula() {
		return anoMatricula;
	}

	public void setAnoMatricula(Integer anoMatricula) {
		this.anoMatricula = anoMatricula;
	}

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public String getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(String dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public List<InputDisciplinaViewModel> getDisciplinaTurma() {
		return disciplinaTurma;
	}

	public void setDisciplinaTurma(List<InputDisciplinaViewModel> disciplinaTurma) {
		this.disciplinaTurma = disciplinaTurma;
	}

	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}

	public Integer getPlanoPagamento() {
		return planoPagamento;
	}

	public void setPlanoPagamento(Integer planoPagamento) {
		this.planoPagamento = planoPagamento;
	}

	public Integer getTurmaBase() {
		return turmaBase;
	}

	public void setTurmaBase(Integer turmaBase) {
		this.turmaBase = turmaBase;
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

	public Integer getAnoCorricularInscricao() {
		return anoCorricularInscricao;
	}

	public void setAnoCorricularInscricao(Integer anoCorricularInscricao) {
		this.anoCorricularInscricao = anoCorricularInscricao;
	}

	public Integer getPercentagemDesconto() {
		return percentagemDesconto;
	}

	public void setPercentagemDesconto(Integer percentagemDesconto) {
		this.percentagemDesconto = percentagemDesconto;
	}

	public Integer getEmpresaBolsa() {
		return empresaBolsa;
	}

	public void setEmpresaBolsa(Integer empresaBolsa) {
		this.empresaBolsa = empresaBolsa;
	}
}