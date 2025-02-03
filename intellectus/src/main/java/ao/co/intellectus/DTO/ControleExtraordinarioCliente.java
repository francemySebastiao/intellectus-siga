package ao.co.intellectus.DTO;

import java.util.List;

public class ControleExtraordinarioCliente {

	private Integer userCode;
	private String userName;
	private Integer numeroDeAluno;
	private List<DisciplinaDespacho> disciplinas;

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(Integer numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}

	public List<DisciplinaDespacho> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<DisciplinaDespacho> disciplinas) {
		this.disciplinas = disciplinas;
	}

}
