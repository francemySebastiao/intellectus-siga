package ao.co.intellectus.controller.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.portal.ModuloPortalView;
import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.portal.ModuloRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import ao.co.intellectus.servico.portal.ModuloPortalService;

@RestController
@RequestMapping("/portal/modulos")
public class ControllerModule {

	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private ModuloPortalService moduloPortalService;
	
	@Autowired
	private ModuloRepository moduloRepository;

	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody ModuloPortalView moduloPortal) {
		Modulo modulo = new Modulo();
		modulo.setName(moduloPortal.getName());
		modulo.setActive(moduloPortal.getActive());
		modulo.setDescription(moduloPortal.getDescription());
		modulo.setFileIconName(moduloPortal.getFileIconName());
		modulo.setEnderecoUrl(moduloPortal.getEnderecoUrl());
		this.moduloPortalService.salvar(modulo);
		return this.httpResponse.MensagemDeRetorno(0, "Modulo registrado com sucesso.");
	}

	@PutMapping(value = "/actualizar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody ModuloPortalView moduloPortal) {
		Modulo modulo = this.moduloRepository.findById(moduloPortal.getId());
	    if(modulo!=null) {
	    	modulo.setName(moduloPortal.getName());
	    	modulo.setActive(moduloPortal.getActive());
	    	modulo.setDescription(moduloPortal.getDescription());
	    	modulo.setFileIconName(moduloPortal.getFileIconName());
	    	modulo.setEnderecoUrl(moduloPortal.getEnderecoUrl());
	    	this.moduloRepository.save(modulo);
	    	return this.httpResponse.MensagemDeRetorno(0, "Modulo actualizado com sucesso.");
	    }else{
	    	return this.httpResponse.MensagemDeRetorno(2, "Ocorreu algum erro ao atualizar o modulo..");
	    }	
	}

	@GetMapping(value = "/pesquisar/{nome}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@PathVariable String nome) {
		Modulo modulos = this.moduloPortalService.pesquisar(nome);
		return this.httpResponse.MensagemDeRetorno(0, modulos);
	}
	
	@GetMapping(value = "/pesquisar/codigo/{codigo}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCodigo(@PathVariable Integer codigo) {
		Modulo modulo = this.moduloRepository.findById(codigo);
		return this.httpResponse.MensagemDeRetorno(0, modulo);
	}

	@GetMapping(value = "/todos", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todos() {
		List<ModuloPortalView> modulos = this.moduloPortalService.todos();
		if(modulos.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum registo de módulo foi encontrado !");
		return this.httpResponse.MensagemDeRetorno(0, modulos);
	}

}
