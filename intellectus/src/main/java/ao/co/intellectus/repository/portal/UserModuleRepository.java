package ao.co.intellectus.repository.portal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.model.portal.UserModule;

public interface UserModuleRepository extends JpaRepository<UserModule, Integer> {
	public List<UserModule> findByUser(User user);

	public UserModule findByUserAndModulo(User user, Modulo modulo);

	public List<UserModule> findByUserAndActiveTrue(User user);

	public List<UserModule> findByModuloIn(List<Modulo> modulo);

}
