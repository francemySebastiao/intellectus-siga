package ao.co.intellectus.model.portal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_portal_module")
public class Modulo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private boolean active;
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
