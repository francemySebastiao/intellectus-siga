package ao.co.intellectus.repository.portal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.Role;

public interface RoleRespository extends JpaRepository<Role, Integer> {

	List<Role> findByName(String nome);
	List<Role> findByNameAndModulo(String nome, Modulo modulo);
	List<Role> findByIdNotAndNameAndModulo(Integer id, String name, Modulo modulo);

}
