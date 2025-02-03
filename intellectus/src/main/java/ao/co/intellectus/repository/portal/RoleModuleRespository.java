package ao.co.intellectus.repository.portal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.Role;
import ao.co.intellectus.model.portal.RoleModule;

public interface RoleModuleRespository extends JpaRepository<RoleModule, Integer> {
	
	public List<RoleModule> findByRoleAndModulo(Role role,Modulo module);
	public List<RoleModule> findByRole(Role role);
	public List<RoleModule> findByRoleName(String name);
	public RoleModule findByIdNotAndComponenteAndRoleAndModulo(Integer id, String componente, Role role, Modulo modulo);

	
}
