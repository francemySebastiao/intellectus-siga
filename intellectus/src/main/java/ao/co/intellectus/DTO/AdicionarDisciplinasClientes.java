package ao.co.intellectus.DTO;

import java.util.List;

public class AdicionarDisciplinasClientes {
	private Integer aluno;
	private Integer anoLectivo;
	private List<DisciplinasClienteAdicionar> disciplinaTurma;

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
	public List<DisciplinasClienteAdicionar> getDisciplinaTurma() {
		return disciplinaTurma;
	}
	public void setDisciplinaTurma(List<DisciplinasClienteAdicionar> disciplinaTurma) {
		this.disciplinaTurma = disciplinaTurma;
	}
}
