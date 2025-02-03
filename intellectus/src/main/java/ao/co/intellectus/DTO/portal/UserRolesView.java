package ao.co.intellectus.DTO.portal;

import java.util.List;

import ao.co.intellectus.model.portal.User;

public class UserRolesView {

	private User user;
	private List<UserRolesEspecificoView> roleUserRolesViewEspecifica;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<UserRolesEspecificoView> getRoleUserRolesViewEspecifica() {
		return roleUserRolesViewEspecifica;
	}

	public void setRoleUserRolesViewEspecifica(List<UserRolesEspecificoView> roleUserRolesViewEspecifica) {
		this.roleUserRolesViewEspecifica = roleUserRolesViewEspecifica;
	}

}
