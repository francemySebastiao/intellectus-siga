package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.model.Disciplina;

public class DisciplinaEfetive {
	private List<Disciplina> disciplinasAdicionais;

	public List<Disciplina> getDisciplinasAdicionais() {
		return disciplinasAdicionais;
	}

	public void setDisciplinasAdicionais(List<Disciplina> disciplinasAdicionais) {
		this.disciplinasAdicionais = disciplinasAdicionais;
	}

}
