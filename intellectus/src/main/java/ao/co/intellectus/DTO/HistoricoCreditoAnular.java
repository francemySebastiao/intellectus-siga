package ao.co.intellectus.DTO;

public class HistoricoCreditoAnular {
	private Integer id;
    private String motivo;
    private Integer usuarioAnulou;
	private String userName;
	private Integer userCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Integer getUsuarioAnulou() {
		return usuarioAnulou;
	}
	public void setUsuarioAnulou(Integer usuarioAnulou) {
		this.usuarioAnulou = usuarioAnulou;
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
	
	
}
