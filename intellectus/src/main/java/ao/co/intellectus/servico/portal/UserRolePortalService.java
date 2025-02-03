package ao.co.intellectus.servico.portal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.portal.Role;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.model.portal.UserRole;
import ao.co.intellectus.repository.portal.UserRoleRepository;
import ao.co.intellectus.servico.exception.DadoInvalidoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class UserRolePortalService {

	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public UserRole userRole(Integer id) {
		UserRole userRole = this.userRoleRepository.getOne(id);
		if(userRole == null) 
			throw new RegistoNaoEncontradoException("Perfil do usuário não encontrado.");
		return userRole;
	}

	public void salvar(UserRole userRole) {
		if(this.permissaoExiste(userRole.getUser(), userRole.getRole()))
			throw new DadoInvalidoException("Esta permissão já existe para este usuário.");
		this.userRoleRepository.save(userRole);
	}
	
	public void remover(UserRole userRole) {
		this.userRoleRepository.delete(userRole);
	}

	public void actualizar(UserRole userRole) {
		this.userRoleRepository.save(userRole);
	}

	public List<Role> roles(User user) {
		List<Role> roles = new ArrayList<Role>();
		for (UserRole userRole : this.userRoleRepository.findByUser(user)) {
			roles.add(userRole.getRole());
		}
		return roles;
	}
	
	public List<UserRole> userRoles(User user) {
		return this.userRoleRepository.findByUser(user);
	}
	
	public boolean permissaoExiste(User user, Role role) {
		return this.userRoleRepository.findByUserAndRole(user, role) != null;
	}
	
	public UserRole mudarEstado(User user, Integer role) {
		UserRole userRole = this.userRoleRepository.findByUserAndId(user, role);
		if(userRole == null)
			throw new RegistoNaoEncontradoException("Permissão não encontrada !");
		userRole.setActive(userRole.getActive() == null? false:!userRole.getActive());
		userRole = this.userRoleRepository.save(userRole);
		return userRole;
	}

}
