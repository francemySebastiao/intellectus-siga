package ao.co.intellectus.servico.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.portal.RoleModule;
import ao.co.intellectus.repository.portal.RoleModuleRespository;
import ao.co.intellectus.servico.exception.DadoInvalidoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class RoleModulePortalService {
	
	@Autowired
	private RoleModuleRespository roleModuleRespository;
	
	public void salvar(RoleModule roleModule) {
		if(this.dadosInvalidados(roleModule))
			throw new DadoInvalidoException("Já existe um perfil com esta permissão.");
		this.roleModuleRespository.save(roleModule);
	}
	
	public void remover(RoleModule roleModule) {
		this.roleModuleRespository.delete(roleModule);
	}
	
	public RoleModule roleModule(Integer id) {
		RoleModule roleModule = this.roleModuleRespository.getOne(id);
		if(roleModule == null)
			throw new RegistoNaoEncontradoException("Permissão do perfil não encontrada.");
		return roleModule;
	}
	
	private boolean dadosInvalidados(RoleModule rm) {
		RoleModule roleModule = this.roleModuleRespository.findByIdNotAndComponenteAndRoleAndModulo(rm.getId(),rm.getComponente(), rm.getRole(), rm.getModulo());
		if(roleModule != null)
			throw new DadoInvalidoException("Já existe um perfil com esta permissão.");
		return roleModule != null;
	}

}
