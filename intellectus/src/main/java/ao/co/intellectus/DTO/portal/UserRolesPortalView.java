package ao.co.intellectus.DTO.portal;

import java.util.List;

import ao.co.intellectus.model.portal.Role;

public class UserRolesPortalView {

	private Integer user;
	private List<Role> roles;

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
