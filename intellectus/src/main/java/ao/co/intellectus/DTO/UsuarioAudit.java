package ao.co.intellectus.DTO;

public class UsuarioAudit {
	private String userName;
	private Integer userCode;
	private Integer codigo;
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
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
}
