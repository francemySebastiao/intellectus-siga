package ao.co.intellectus.DTO;

public class InputDisciplinaViewModel {
	private Integer id; 
	private Integer idTurma;
	private boolean matricuma;
	private Integer anoCorricular;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}
	public boolean isMatricuma() {
		return matricuma;
	}
	public void setMatricuma(boolean matricuma) {
		this.matricuma = matricuma;
	}
	public Integer getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	}
}
