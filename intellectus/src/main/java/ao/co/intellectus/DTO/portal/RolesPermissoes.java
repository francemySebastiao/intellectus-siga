package ao.co.intellectus.DTO.portal;

import java.util.List;

public class RolesPermissoes {
	private String role;
    private List<Functionalities> functionalities;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Functionalities> getFunctionalities() {
		return functionalities;
	}
	public void setFunctionalities(List<Functionalities> functionalities) {
		this.functionalities = functionalities;
	}
}
