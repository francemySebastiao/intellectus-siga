package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	public Usuario findByUserName(String userName);
	public Usuario findByUserCode(Integer userCode);
	public Usuario findByEmail(String email);
	
	public List<Usuario> findByOrderByName();
	
	public List<Usuario> findByFinaceiroOrderByName(boolean financeiro);
	public Usuario findByUserCodeOrUserName(Integer userCode, String usuerName);
	
	
}
