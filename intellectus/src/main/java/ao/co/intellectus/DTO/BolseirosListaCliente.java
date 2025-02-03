package ao.co.intellectus.DTO;

public class BolseirosListaCliente {
	private String numeroDeAluno;
	private double valorAdd;
	private boolean status;
	
	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}
	public void setNumeroDeAluno(String numeroDeAluno) {
		this.numeroDeAluno = numeroDeAluno;
	}
	public double getValorAdd() {
		return valorAdd;
	}
	public void setValorAdd(double valorAdd) {
		this.valorAdd = valorAdd;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
