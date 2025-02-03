package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.HistoricoSimples;
import ao.co.intellectus.model.PlanoPagamento;
import ao.co.intellectus.model.TipoInscricao;
import ao.co.intellectus.model.Turma;

public class MatriculaAndHistorico {
	private Integer idMatricula;
	private Integer numeroAluno;
	private String nome;
	private String curso;
	private Turma turma;
	private PlanoPagamento planoPagamento;
	private TipoInscricao tipoInscricao;
	private List<HistoricoSimples> historicoAluno;
	public List<HistoricoSimples> getHistoricoAluno() {
		return historicoAluno;
	}
	public void setHistoricoAluno(List<HistoricoSimples> historicoAluno) {
		this.historicoAluno = historicoAluno;
	}
	public Integer getIdMatricula() {
		return idMatricula;
	}
	public void setIdMatricula(Integer idMatricula) {
		this.idMatricula = idMatricula;
	}
	public Integer getNumeroAluno() {
		return numeroAluno;
	}
	public void setNumeroAluno(Integer numeroAluno) {
		this.numeroAluno = numeroAluno;
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
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	public PlanoPagamento getPlanoPagamento() {
		return planoPagamento;
	}
	public void setPlanoPagamento(PlanoPagamento planoPagamento) {
		this.planoPagamento = planoPagamento;
	}
	public TipoInscricao getTipoInscricao() {
		return tipoInscricao;
	}
	public void setTipoInscricao(TipoInscricao tipoInscricao) {
		this.tipoInscricao = tipoInscricao;
	}
}
