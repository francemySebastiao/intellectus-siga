package ao.co.intellectus.DTO;

public class PedidoResumoCliente {
	private Integer anoDeclaracao;
	private Integer numeroDoAluno;
	private Integer declaracaoDestino;
	private String guiaPagamento;
	private Integer tipoDeclaracao;
	
	
	public Integer getAnoDeclaracao() {
		return anoDeclaracao;
	}
	public void setAnoDeclaracao(Integer anoDeclaracao) {
		this.anoDeclaracao = anoDeclaracao;
	}
	public Integer getNumeroDoAluno() {
		return numeroDoAluno;
	}
	public void setNumeroDoAluno(Integer numeroDoAluno) {
		this.numeroDoAluno = numeroDoAluno;
	}
	public Integer getDeclaracaoDestino() {
		return declaracaoDestino;
	}
	public void setDeclaracaoDestino(Integer declaracaoDestino) {
		this.declaracaoDestino = declaracaoDestino;
	}
	public String getGuiaPagamento() {
		return guiaPagamento;
	}
	public void setGuiaPagamento(String guiaPagamento) {
		this.guiaPagamento = guiaPagamento;
	}
	public Integer getTipoDeclaracao() {
		return tipoDeclaracao;
	}
	public void setTipoDeclaracao(Integer tipoDeclaracao) {
		this.tipoDeclaracao = tipoDeclaracao;
	}
}
