package ao.co.intellectus.DTO;

public class AlunoResumo {
	private Integer buscarId;
	private String nome;
	private boolean candidatura;
	
	public Integer getBuscarId() {
		return buscarId;
	}
	public void setBuscarId(Integer buscarId) {
		this.buscarId = buscarId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isCandidatura() {
		return candidatura;
	}
	public void setCandidatura(boolean candidatura) {
		this.candidatura = candidatura;
	}
}
