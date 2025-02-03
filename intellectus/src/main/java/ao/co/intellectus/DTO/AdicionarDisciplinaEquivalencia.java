package ao.co.intellectus.DTO;

import java.util.List;

public class AdicionarDisciplinaEquivalencia {
	private Integer idEquivalencia;
	private List<DisciplinaEquivalencia> disciplinas;
	public Integer getIdEquivalencia() {
		return idEquivalencia;
	}
	public void setIdEquivalencia(Integer idEquivalencia) {
		this.idEquivalencia = idEquivalencia;
	}
	public List<DisciplinaEquivalencia> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<DisciplinaEquivalencia> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
