package ao.co.intellectus.servico.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.portal.ModuloPortalView;
import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.model.portal.UserModule;
import ao.co.intellectus.repository.portal.UserModuleRepository;
import ao.co.intellectus.servico.exception.DadoInvalidoException;

@Service
public class UserModuleService {

	@Autowired
	private UserModuleRepository userModuleRepository;

	public void atrelagemDeModulo(List<ModuloPortalView> modulos, User user) {
		for (ModuloPortalView moduloPortalView : modulos) {
			Modulo modulo = new Modulo();
			modulo.setId(moduloPortalView.getId());
			modulo.setName(moduloPortalView.getName());
			modulo.setActive(moduloPortalView.getActive());
			modulo.setDescription(moduloPortalView.getDescription());
			modulo.setFileIconName(moduloPortalView.getFileIconName());
			modulo.setEnderecoUrl(moduloPortalView.getEnderecoUrl());
			if(moduloPortalView.getSeleccionado() == null)
					throw new DadoInvalidoException("Erro ao validar modulos seleccionados");
			else if (moduloPortalView.getSeleccionado()) {
				this.adicionarModulo(modulo, user);
			} else {
				this.removerModulo(modulo, user);
			}
		}
	}

	public List<UserModule> modulos(User user) {
		return this.userModuleRepository.findByUser(user);
	}

	public List<UserModule> modulosIn(List<Modulo> modulo) {
		return this.userModuleRepository.findByModuloIn(modulo);
	}

	private Boolean usuarioTemModelo(User user, Modulo modulo) {
		return this.userModuleRepository.findByUserAndModulo(user, modulo) != null;
	}

	public UserModule userModelo(User user, Modulo modulo) {
		return this.userModuleRepository.findByUserAndModulo(user, modulo);
	}

	private void adicionarModulo(Modulo modulo, User user) {
		if (this.usuarioTemModelo(user, modulo) == false) {
			UserModule userModule = new UserModule();
			userModule.setUser(user);
			userModule.setActive(true);
			userModule.setModulo(modulo);
			this.userModuleRepository.save(userModule);
		}
	}

	private void removerModulo(Modulo modulo, User user) {
		UserModule userModule = this.userModelo(user, modulo);
		if (userModule != null)
			this.userModuleRepository.delete(userModule);
	}
}
