package ao.co.intellectus.DTO.colegio;

import java.util.Date;
import java.util.List;

public class MatriculaConfirmadaViewModel {
	private String nome;
	private Integer aluno;
	private Integer anoLectivo;
	private String curso;
	private Date data;
	private String numeroDeAluno;
	private Integer PercentagemDesconto;
	private String turmaBase;
	private String planoPagamento;
	private List<DisciplinaClienteColegio> disciplinas;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getAluno() {
		return aluno;
	}
	public void setAluno(Integer aluno) {
		this.aluno = aluno;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public Integer getPercentagemDesconto() {
		return PercentagemDesconto;
	}
	public void setPercentagemDesconto(Integer percentagemDesconto) {
		PercentagemDesconto = percentagemDesconto;
	}
	public String getTurmaBase() {
		return turmaBase;
	}
	public void setTurmaBase(String turmaBase) {
		this.turmaBase = turmaBase;
	}
	public String getPlanoPagamento() {
		return planoPagamento;
	}
	public void setPlanoPagamento(String planoPagamento) {
		this.planoPagamento = planoPagamento;
	}
	public List<DisciplinaClienteColegio> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<DisciplinaClienteColegio> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
