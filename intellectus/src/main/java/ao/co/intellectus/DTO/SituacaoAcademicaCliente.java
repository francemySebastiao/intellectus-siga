package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.GuiaEmAbertoCliente;

public class SituacaoAcademicaCliente {
	private AlunoResumoSituacao aluno;
	private List<InscricaoPorAno> inscricaoPorAno;
	private List<GuiaLiquidadaCliente> guiasLiquidadas;
	private List<GuiaEmAbertoCliente> guiasAbertas;
	private List<DisciplinaConcluidaCliente> disciplinasConcluidas;
	private List<DisciplinaCursandoCliente> disciplinasInscritas;
	private Integer disciplinasCurso;
	private String observacao;
	
	public AlunoResumoSituacao getAluno() {
		return aluno;
	}
	public void setAluno(AlunoResumoSituacao aluno) {
		this.aluno = aluno;
	}
	public List<InscricaoPorAno> getInscricaoPorAno() {
		return inscricaoPorAno;
	}
	public void setInscricaoPorAno(List<InscricaoPorAno> inscricaoPorAno) {
		this.inscricaoPorAno = inscricaoPorAno;
	}
	public List<GuiaLiquidadaCliente> getGuiasLiquidadas() {
		return guiasLiquidadas;
	}
	public void setGuiasLiquidadas(List<GuiaLiquidadaCliente> guiasLiquidadas) {
		this.guiasLiquidadas = guiasLiquidadas;
	}
	public List<GuiaEmAbertoCliente> getGuiasAbertas() {
		return guiasAbertas;
	}
	public void setGuiasAbertas(List<GuiaEmAbertoCliente> guiasAbertas) {
		this.guiasAbertas = guiasAbertas;
	}
	public List<DisciplinaConcluidaCliente> getDisciplinasConcluidas() {
		return disciplinasConcluidas;
	}
	public void setDisciplinasConcluidas(List<DisciplinaConcluidaCliente> disciplinasConcluidas) {
		this.disciplinasConcluidas = disciplinasConcluidas;
	}
	public List<DisciplinaCursandoCliente> getDisciplinasInscritas() {
		return disciplinasInscritas;
	}
	public void setDisciplinasInscritas(List<DisciplinaCursandoCliente> disciplinasInscritas) {
		this.disciplinasInscritas = disciplinasInscritas;
	}
	public Integer getDisciplinasCurso() {
		return disciplinasCurso;
	}
	public void setDisciplinasCurso(Integer disciplinasCurso) {
		this.disciplinasCurso = disciplinasCurso;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}