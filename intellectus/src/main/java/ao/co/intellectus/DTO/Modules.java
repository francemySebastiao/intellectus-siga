package ao.co.intellectus.DTO;

import java.util.List;

import ao.co.intellectus.DTO.portal.RolesPermissoes;

public class Modules {

	private String name;
	private String url;
	private String fileIconName;
	private boolean activo;
	private List<Roles> roles;
	private List<RolesPermissoes> rolesPermissoes;

	public List<RolesPermissoes> getRolesPermissoes() {
		return rolesPermissoes;
	}

	public void setRolesPermissoes(List<RolesPermissoes> rolesPermissoes) {
		this.rolesPermissoes = rolesPermissoes;
	}

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

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
