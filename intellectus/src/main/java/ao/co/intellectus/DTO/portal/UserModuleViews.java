package ao.co.intellectus.DTO.portal;

import java.util.List;

import ao.co.intellectus.model.portal.User;

public class UserModuleViews {

	private User user;
	private List<ModuloPortalView> modulos;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<ModuloPortalView> getModulos() {
		return modulos;
	}

	public void setModulos(List<ModuloPortalView> modulos) {
		this.modulos = modulos;
	}

	

}
