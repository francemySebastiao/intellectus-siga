package ao.co.intellectus.repository.portal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.Role;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.model.portal.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
	public List<UserRole> findByUserAndRoleModulo(User user,Modulo module);
	public List<UserRole> findByUser(User user);
	public UserRole findByUserAndRole(User user, Role role);
	public UserRole findByUserAndId(User user, Integer role);
}
