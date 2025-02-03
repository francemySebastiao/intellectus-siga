package ao.co.intellectus.DTO;

import java.util.List;

public class AdicionarDisciplina {
	private Integer numeroDeAluno;
	private Integer anoLectivo;
	private List<DisciplinaNovaCliente> novas;

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

	public List<DisciplinaNovaCliente> getNovas() {
		return novas;
	}

	public void setNovas(List<DisciplinaNovaCliente> novas) {
		this.novas = novas;
	}
}
