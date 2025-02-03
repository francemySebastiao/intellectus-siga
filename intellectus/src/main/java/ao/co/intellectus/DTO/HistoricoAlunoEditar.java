package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Situacao;
import ao.co.intellectus.model.TipoDisciplina;

public class HistoricoAlunoEditar {
    private Integer codigoDisciplina;
	private String disciplina;
	@Enumerated(EnumType.STRING)
	private TipoDisciplina tipo;
	private Integer anoCorricular;
	@Enumerated(EnumType.STRING)
	private Situacao situacao;
	private Integer numeroDeAluno;
	private Integer anoLectivo;
	private String turma;
	

	public Integer getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(Integer codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public TipoDisciplina getTipo() {
		return tipo;
	}
	public void setTipo(TipoDisciplina tipo) {
		this.tipo = tipo;
	}
	public Integer getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public Integer getAnoLectivo() {
		return anoLectivo;
	}
	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	
}
