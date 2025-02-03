package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.Prova;

public class HistoricoAlunosTurma {
	private Integer turma;
	private Integer disciplina;
	private Integer anoLectivo;
	private Integer anoCurricular;
	private List<ClassificacaoCliente> historicoAlunos;
	private ClassificacaoCliente historicoUMAlunos;
	private List<Prova> provas;
	private Integer userCode;
	private String userName;
	private Integer totalDeAlunos;
	private Integer totalAprovadosFrequencias;
	private Integer totalReprovados;
    private Integer totalRecurso;
    private Integer totalExame;
    private boolean validar;
    
	private Integer prova;
	
	
	public ClassificacaoCliente getHistoricoUMAlunos() {
		return historicoUMAlunos;
	}
	public void setHistoricoUMAlunos(ClassificacaoCliente historicoUMAlunos) {
		this.historicoUMAlunos = historicoUMAlunos;
	}
	public Integer getTurma() {
		return turma;
	}
	public void setTurma(Integer turma) {
		this.turma = turma;
	}
	public Integer getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Integer disciplina) {
		this.disciplina = disciplina;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public List<ClassificacaoCliente> getHistoricoAlunos() {
		return historicoAlunos;
	}
	public void setHistoricoAlunos(List<ClassificacaoCliente> historicoAlunos) {
		this.historicoAlunos = historicoAlunos;
	}
	public Integer getAnoCurricular() {
		return anoCurricular;
	}
	public void setAnoCurricular(Integer anoCurricular) {
		this.anoCurricular = anoCurricular;
	}
	public List<Prova> getProvas() {
		return provas;
	}
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
	public Integer getProva() {
		return prova;
	}
	public void setProva(Integer prova) {
		this.prova = prova;
	}
	public Integer getUserCode() {
		return userCode;
	}
	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getTotalDeAlunos() {
		return totalDeAlunos;
	}
	public void setTotalDeAlunos(Integer totalDeAlunos) {
		this.totalDeAlunos = totalDeAlunos;
	}
	public Integer getTotalAprovadosFrequencias() {
		return totalAprovadosFrequencias;
	}
	public void setTotalAprovadosFrequencias(Integer totalAprovadosFrequencias) {
		this.totalAprovadosFrequencias = totalAprovadosFrequencias;
	}
	public Integer getTotalReprovados() {
		return totalReprovados;
	}
	public void setTotalReprovados(Integer totalReprovados) {
		this.totalReprovados = totalReprovados;
	}
	public Integer getTotalRecurso() {
		return totalRecurso;
	}
	public void setTotalRecurso(Integer totalRecurso) {
		this.totalRecurso = totalRecurso;
	}
	public Integer getTotalExame() {
		return totalExame;
	}
	public void setTotalExame(Integer totalExame) {
		this.totalExame = totalExame;
	}
	public boolean isValidar() {
		return validar;
	}
	public void setValidar(boolean validar) {
		this.validar = validar;
	}
}