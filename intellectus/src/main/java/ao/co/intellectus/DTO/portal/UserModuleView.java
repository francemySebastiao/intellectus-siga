package ao.co.intellectus.DTO.portal;

import java.util.List;

public class UserModuleView {

	private Integer user;
	private List<ModuloPortalView> modulos;

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public List<ModuloPortalView> getModulos() {
		return modulos;
	}

	public void setModulos(List<ModuloPortalView> modulos) {
		this.modulos = modulos;
	}

}
