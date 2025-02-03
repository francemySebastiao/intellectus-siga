package ao.co.intellectus.DTO.portal;

import java.util.List;

public class ModulesComposto {
	private String name;
	private String fileIconName;
	
	private List<RolesPermissoes> rolesPermissoes;
	
	
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
	public List<RolesPermissoes> getRolesPermissoes() {
		return rolesPermissoes;
	}
	public void setRolesPermissoes(List<RolesPermissoes> rolesPermissoes) {
		this.rolesPermissoes = rolesPermissoes;
	}
	
}
