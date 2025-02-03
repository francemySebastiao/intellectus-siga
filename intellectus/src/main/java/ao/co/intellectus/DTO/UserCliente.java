package ao.co.intellectus.DTO;

import java.util.List;

public class UserCliente {
	private String name;
	private String userName;
	private String userCode;
	private boolean admin;
	private String instCode;
	private List<Modules> modules;
	private boolean goToModule;
	private String iat;

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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getInstCode() {
		return instCode;
	}

	public void setInstCode(String instCode) {
		this.instCode = instCode;
	}

	public List<Modules> getModules() {
		return modules;
	}

	public void setModules(List<Modules> modules) {
		this.modules = modules;
	}

	public boolean isGoToModule() {
		return goToModule;
	}

	public void setGoToModule(boolean goToModule) {
		this.goToModule = goToModule;
	}

	public String getIat() {
		return iat;
	}

	public void setIat(String iat) {
		this.iat = iat;
	}

}
