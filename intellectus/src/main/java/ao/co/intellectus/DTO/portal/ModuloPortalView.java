package ao.co.intellectus.DTO.portal;

import javax.persistence.Column;

public class ModuloPortalView {

	private Integer id;
	private String name;
	private Boolean active;
	private String description;
	private Boolean seleccionado;

	@Column(length = 4000)
	private String fileIconName;
	@Column(length = 4000)
	private String enderecoUrl;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	public String getFileIconName() {
		return fileIconName;
	}
	public void setFileIconName(String fileIconName) {
		this.fileIconName = fileIconName;
	}
	public String getEnderecoUrl() {
		return enderecoUrl;
	}
	public void setEnderecoUrl(String enderecoUrl) {
		this.enderecoUrl = enderecoUrl;
	}

	

}
