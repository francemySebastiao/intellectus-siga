package ao.co.intellectus.DTO;

public class AlunoCreditoCliente {
	private String numero;
	private String nome;
	private String curso;
	private double credito;
	private boolean contenciso;
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public double getCredito() {
		return credito;
	}
	public void setCredito(double credito) {
		this.credito = credito;
	}
	public boolean isContenciso() {
		return contenciso;
	}
	public void setContenciso(boolean contenciso) {
		this.contenciso = contenciso;
	}
}
