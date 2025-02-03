package ao.co.intellectus.DTO;

public class AgrupamentoDeCandidatos {

	private int anoLectivo;
	private String curso;
	private int numeroDeCandidatos;
	private String turno;
	private String sexo;
	private String dataDeCandidatura;

	public int getNumeroDeCandidatos() {
		return numeroDeCandidatos;
	}

	public void setNumeroDeCandidatos(int numeroDeCandidatos) {
		this.numeroDeCandidatos = numeroDeCandidatos;
	}

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

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataDeCandidatura() {
		return dataDeCandidatura;
	}

	public void setDataDeCandidatura(String dataDeCandidatura) {
		this.dataDeCandidatura = dataDeCandidatura;
	}

}
