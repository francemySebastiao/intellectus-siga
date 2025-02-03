package ao.co.intellectus.repository.portal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.portal.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUserName(String userName);

	public List<User> findByNameOrUserCodeOrUserName(String name, String userCode, String userName);

	public User findByUserCodeOrUserNameAllIgnoreCase(String userCode, String userName);

	public User findByIdNotAndUserCodeOrUserName(Integer id, String userCode, String userName);
	
	public List<User> findByEmail(String email);
	
	public User findById(Integer codigo);
}
