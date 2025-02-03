package ao.co.intellectus.DTO;

import java.util.List;

public class ListaPropinasAtraso {

	private int anoLectivo;
	private String curso;
	private String turma;
	private List<AlunoPropinaAtraso> alunosComPropinaEmAtraso;

	public int getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(int anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public List<AlunoPropinaAtraso> getAlunosComPropinaEmAtraso() {
		return alunosComPropinaEmAtraso;
	}

	public void setAlunosComPropinaEmAtraso(List<AlunoPropinaAtraso> alunosComPropinaEmAtraso) {
		this.alunosComPropinaEmAtraso = alunosComPropinaEmAtraso;
	}

}
