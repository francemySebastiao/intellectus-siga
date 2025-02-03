package ao.co.intellectus.DTO;

public class DisciplinaEquivalencia {
	
	private Integer id;
	private String disciplinaOrigem;
	private Float notaOrigem;
	private Integer disciplinaDestino;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDisciplinaOrigem() {
		return disciplinaOrigem;
	}
	public void setDisciplinaOrigem(String disciplinaOrigem) {
		this.disciplinaOrigem = disciplinaOrigem;
	}
	public Float getNotaOrigem() {
		return notaOrigem;
	}
	public void setNotaOrigem(Float notaOrigem) {
		this.notaOrigem = notaOrigem;
	}
	public Integer getDisciplinaDestino() {
		return disciplinaDestino;
	}
	public void setDisciplinaDestino(Integer disciplinaDestino) {
		this.disciplinaDestino = disciplinaDestino;
	}
}
