package ao.co.intellectus.DTO.portal;

import java.util.List;

public class RolesPermissoesModel {
	private String role;
    private List<RolesPermissoes> functionalities;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<RolesPermissoes> getFunctionalities() {
		return functionalities;
	}
	public void setFunctionalities(List<RolesPermissoes> functionalities) {
		this.functionalities = functionalities;
	}
}
