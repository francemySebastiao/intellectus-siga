package ao.co.intellectus.servico.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.Role;
import ao.co.intellectus.repository.portal.RoleRespository;
import ao.co.intellectus.servico.exception.DadoInvalidoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class RoleService {

	@Autowired
	private RoleRespository roleRespository;

	public void salvar(Role role) {
		if(this.dadosValidos(role)) {
			this.roleRespository.save(role);			
		}else {
			throw new DadoInvalidoException("Já existe este perfil neste módulos");
		}
	}

	public void actualizar(Role role) {
		if(role.getId() == null || this.role(role.getId()) == null)
			throw new DadoInvalidoException("Registo de perfil nã encontrado.");
		this.salvar(role);
	}

	public Role role(Integer id) {
		Role role = this.roleRespository.getOne(id);
		if (role == null)
			throw new RegistoNaoEncontradoException("Registo de pefil não encontrado.");
		return role;
	}
	
	public boolean dadosValidos(Role role) {
		List<Role> roles = this.roleRespository.findByIdNotAndNameAndModulo(role.getId(),role.getName(), role.getModulo());
		return roles.isEmpty();
	}

	public List<Role> pesquisar(String nome) {
		List<Role> roles = this.roleRespository.findByName(nome);
		if (roles.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de Perfil não encontrado.");
		return roles;
	}

	public List<Role> pesquisar(String nome, Modulo modulo) {
		List<Role> roles = this.roleRespository.findByNameAndModulo(nome, modulo);
		if (roles.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de Perfil não encontrado.");
		return roles;
	}

}
