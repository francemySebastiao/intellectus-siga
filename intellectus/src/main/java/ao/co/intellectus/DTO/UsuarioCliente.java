package ao.co.intellectus.DTO;

public class UsuarioCliente {
	private String name;
	private String userName;
	private Integer userCode;
    private Integer instCode;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getInstCode() {
		return instCode;
	}
	public void setInstCode(Integer instCode) {
		this.instCode = instCode;
	}
}
