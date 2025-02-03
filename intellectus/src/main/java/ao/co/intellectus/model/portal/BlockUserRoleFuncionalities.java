package ao.co.intellectus.model.portal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_portal_block_user_role")
public class BlockUserRoleFuncionalities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "role_Id")
	private Role role;
	@ManyToOne
	@JoinColumn(name = "user_Id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "role_module_id")
	private RoleModule roleModule;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RoleModule getRoleModule() {
		return roleModule;
	}

	public void setRoleModule(RoleModule roleModule) {
		this.roleModule = roleModule;
	}

}
