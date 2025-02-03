package ao.co.intellectus.DTO.portal;

import ao.co.intellectus.model.portal.Role;

public class UserRolesEspecificoView {

	private Integer id;
	private Role role;
	private Boolean active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
