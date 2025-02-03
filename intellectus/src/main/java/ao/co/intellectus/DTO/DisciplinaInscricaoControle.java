package ao.co.intellectus.DTO;

public class DisciplinaInscricaoControle {
    private Integer id;
    private boolean verificada;
    private Integer anoCorricular;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isVerificada() {
		return verificada;
	}
	public void setVerificada(boolean verificada) {
		this.verificada = verificada;
	}
	public Integer getAnoCorricular() {
		return anoCorricular;
	}
	public void setAnoCorricular(Integer anoCorricular) {
		this.anoCorricular = anoCorricular;
	} 
}
