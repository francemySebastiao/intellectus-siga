package ao.co.intellectus.DTO;

public class AlunoPropinaAtraso {
	
	private String numeroDoAluno;
	private String nome;
	private int numeroDePropinasEmAtraso;
	public String getNumeroDoAluno() {
		return numeroDoAluno;
	}
	public void setNumeroDoAluno(String numeroDoAluno) {
		this.numeroDoAluno = numeroDoAluno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumeroDePropinasEmAtraso() {
		return numeroDePropinasEmAtraso;
	}
	public void setNumeroDePropinasEmAtraso(int numeroDePropinasEmAtraso) {
		this.numeroDePropinasEmAtraso = numeroDePropinasEmAtraso;
	}
}
