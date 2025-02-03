package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.CursoDocente;

public class TurmaDisciplinaDescricao {
    private List<TurmaDescricao> turma;
	private List<DisciplinaDescricao> disciplina;
	private List<CursoDocente> cursos;
	
	public List<TurmaDescricao> getTurma() {
		return turma;
	}
	public void setTurma(List<TurmaDescricao> turma) {
		this.turma = turma;
	}
	public List<DisciplinaDescricao> getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(List<DisciplinaDescricao> disciplina) {
		this.disciplina = disciplina;
	}
	public List<CursoDocente> getCursos() {
		return cursos;
	}
	public void setCursos(List<CursoDocente> cursos) {
		this.cursos = cursos;
	}
}
