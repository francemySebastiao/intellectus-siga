package ao.co.intellectus.DTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ao.co.intellectus.model.Semestre;

public class FecharClassificacoesCliente {
	private Integer anoLectivo;
	private Integer curso;
	@Enumerated(EnumType.STRING)
	private Semestre semestre;
	private Integer anoCorricular;
	private boolean validar;
	private boolean fechar;

	public Integer getAnoLectivo() {
		return anoLectivo;
	}

	public void setAnoLectivo(Integer anoLectivo) {
		this.anoLectivo = anoLectivo;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

	public Integer getAnoCorricular() {
		return anoCorricular;
	}

	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}

	public boolean isValidar() {
		return validar;
	}

	public void setValidar(boolean validar) {
		this.validar = validar;
	}

	public boolean isFechar() {
		return fechar;
	}

	public void setFechar(boolean fechar) {
		this.fechar = fechar;
	}
}
