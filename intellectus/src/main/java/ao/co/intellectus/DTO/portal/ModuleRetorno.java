package ao.co.intellectus.DTO.portal;

import java.util.List;

public class ModuleRetorno {
	private String name;
	private String fileIconName;
	
	private List<RolesPermissoes> roles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileIconName() {
		return fileIconName;
	}
	public void setFileIconName(String fileIconName) {
		this.fileIconName = fileIconName;
	}
	public List<RolesPermissoes> getRoles() {
		return roles;
	}
	public void setRoles(List<RolesPermissoes> roles) {
		this.roles = roles;
	}
}
