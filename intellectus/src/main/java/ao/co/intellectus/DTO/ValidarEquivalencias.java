package ao.co.intellectus.DTO;

import java.util.List;

public class ValidarEquivalencias {
	private List<ValidarEquivalenciaCliente> disciplinas;
	private Integer id;
	public List<ValidarEquivalenciaCliente> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(List<ValidarEquivalenciaCliente> disciplinas) {
		this.disciplinas = disciplinas;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
