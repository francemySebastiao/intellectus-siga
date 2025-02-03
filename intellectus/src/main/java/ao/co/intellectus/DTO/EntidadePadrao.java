package ao.co.intellectus.DTO;

public class EntidadePadrao {
    private Integer codigo;
    private String motivoAnulacao;
    private Double valor;
	private String userName;
	private Integer userCode;
	private Boolean candidatura;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getMotivoAnulacao() {
		return motivoAnulacao;
	}
	public void setMotivoAnulacao(String motivoAnulacao) {
		this.motivoAnulacao = motivoAnulacao;
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
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Boolean isCandidatura() {
		return candidatura;
	}
	public void setCandidatura(Boolean candidatura) {
		this.candidatura = candidatura;
	}
	
}
