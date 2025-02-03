package ao.co.intellectus.model.portal;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_portal_role_permition")
@Embeddable
public class RolePermition {
	
	@Id
	@GeneratedValue
	private Integer id; 
	@ManyToOne
	@JoinColumn(name="codigo_permition")
	private Permition permition;
	@ManyToOne
	@JoinColumn(name="codigo_role")
	private Role role;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Permition getPermition() {
		return permition;
	}
	public void setPermition(Permition permition) {
		this.permition = permition;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
