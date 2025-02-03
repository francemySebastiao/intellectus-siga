package ao.co.intellectus.DTO;

public class BancoCliente {
	private Integer id;
	private String banco; 
	private String balcao;
	private String numeroConta;
	private Integer moeda;
	private String formaPagamento;
	private boolean estado;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getBalcao() {
		return balcao;
	}
	public void setBalcao(String balcao) {
		this.balcao = balcao;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	public Integer getMoeda() {
		return moeda;
	}
	public void setMoeda(Integer moeda) {
		this.moeda = moeda;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
