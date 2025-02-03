package ao.co.intellectus.servico.portal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.portal.ModuloPortalView;
import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.repository.portal.ModuloRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class ModuloPortalService {

	@Autowired
	private ModuloRepository moduloRepository;

	public void salvar(Modulo modulo) {
		this.moduloRepository.save(modulo);
	}

	public void actualizar(Modulo modulo) {
		if (modulo.getId() == null || this.modulo(modulo.getId()) == null)
			throw new RegistoNaoEncontradoException("Registo de módulo não encontrado.");
		this.salvar(modulo);
	}

	public Modulo modulo(Integer id) {
		Modulo modulo = this.moduloRepository.getOne(id);
		if (modulo == null)
			throw new RegistoNaoEncontradoException("Nenhum registo de módulo encontrado.");
		return modulo;
	}
	
	public List<Modulo> modulos(List<Integer> modulos) {
		if (modulos == null || modulos.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum módulo seleccionado !");
		List<Modulo> resultados = new ArrayList<Modulo>();
		for(Integer modelo: modulos) {
			Modulo modulo = this.moduloRepository.findOne(modelo);
			if(modelo == null)
				continue;
			resultados.add(modulo);
		}
		return resultados;
	}

	public Modulo pesquisar(String nome) {
		Modulo modulos = this.moduloRepository.findByName(nome);
		if (modulos == null)
			throw new RegistoNaoEncontradoException("Registo de módulos não encontrado.");
		return modulos;
	}

	public List<ModuloPortalView> todos() {
		List<ModuloPortalView> modulos = new ArrayList<ModuloPortalView>();
		for(Modulo mudulo: this.moduloRepository.findAll()) {
			ModuloPortalView moduloPortalView = new ModuloPortalView();
			moduloPortalView.setId(mudulo.getId());
			moduloPortalView.setName(mudulo.getName());
			moduloPortalView.setActive(mudulo.isActive());
			moduloPortalView.setDescription(mudulo.getDescription());
			moduloPortalView.setFileIconName(mudulo.getFileIconName());
			moduloPortalView.setEnderecoUrl(mudulo.getEnderecoUrl());
	    	moduloPortalView.setSeleccionado(false);
			modulos.add(moduloPortalView);
		}
		return modulos;
	}
	
	
	
	public List<ModuloPortalView> modulosNot(List<Integer> modulos,List<ModuloPortalView> modulosNaoAtribuidos){
		for (Modulo modulo : this.moduloRepository.findByIdNotIn(modulos)) {
			ModuloPortalView moduloPortalView = new ModuloPortalView();
			moduloPortalView.setId(modulo.getId());
			moduloPortalView.setName(modulo.getName());
			moduloPortalView.setActive(modulo.isActive());
			moduloPortalView.setDescription(modulo.getDescription());
			moduloPortalView.setFileIconName(modulo.getFileIconName());
			moduloPortalView.setEnderecoUrl(modulo.getEnderecoUrl());
	    	moduloPortalView.setSeleccionado(false);
	    	modulosNaoAtribuidos.add(moduloPortalView);
		}
		return modulosNaoAtribuidos;
	}
	
	
	
}
