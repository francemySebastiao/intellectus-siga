package ao.co.intellectus.DTO.portal;

import java.util.List;

public class RolePermitionViewModel {
	private Integer role;
    private List<PermirionViewModel> permitions;
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public List<PermirionViewModel> getPermitions() {
		return permitions;
	}
	public void setPermitions(List<PermirionViewModel> permitions) {
		this.permitions = permitions;
	}
}
