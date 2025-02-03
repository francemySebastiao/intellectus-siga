package ao.co.intellectus.DTO;

public class GuiaAcordoPagamentoCliente {
	private Integer id;
	private String numeroGuia;
	private String userName;
	private Integer userCode;
	private boolean paraAcordoPagamento;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(String numeroGuia) {
		this.numeroGuia = numeroGuia;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserCode() {
		return userCode;
	}
	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}
	public boolean isParaAcordoPagamento() {
		return paraAcordoPagamento;
	}
	public void setParaAcordoPagamento(boolean paraAcordoPagamento) {
		this.paraAcordoPagamento = paraAcordoPagamento;
	}
}
