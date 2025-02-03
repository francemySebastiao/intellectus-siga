package ao.co.intellectus.controller.portal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.portal.PermitionViewModel;
import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.Permition;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.portal.ModuloRepository;
import ao.co.intellectus.repository.portal.PermitionRepository;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/portal/permissao")
public class ControllerPermition {
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private PermitionRepository permitionRepository;
	@Autowired
	private ModuloRepository moduloRepository;
	
	 
	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody PermitionViewModel pn) {
		Permition permition=new Permition();
		
		permition.setActive(pn.isActive());
		permition.setComponent(pn.getComponent());
		permition.setDescription(pn.getDescription());
		
		System.err.println("O MUDULO A ACTIVAR: "+pn.getModulo());
		
		Modulo modulo = this.moduloRepository.findById(pn.getModulo());
		
		if(modulo!=null) {
			permition.setModulo(modulo);			
			System.err.println("MODULO ENCONTRADO: "+modulo);
		}
		this.permitionRepository.save(permition); 
		return this.httpResponse.MensagemDeRetorno(0, "Permissão cadastrada com sucesso.");
	}
	
	@PutMapping(value = "/atualizar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody PermitionViewModel pn) {
		
		Modulo modulo = this.moduloRepository.findById(pn.getModulo());

		Permition permition = this.permitionRepository.findOne(pn.getId());

		if(permition==null) {
			return this.httpResponse.MensagemDeRetorno(1, "Verifique o codigo da permissão.");	
		}
		
		
		if(modulo==null) {
			return this.httpResponse.MensagemDeRetorno(1, "Verifique o modulo da permissão");	
		}
		
		
		
		permition.setActive(pn.isActive());
		permition.setComponent(pn.getComponent());
		permition.setDescription(pn.getDescription());
		permition.setModulo(modulo);
		
		this.permitionRepository.save(permition);
		
		return this.httpResponse.MensagemDeRetorno(0, "Permissão actualizada com sucesso.");
	}

	
	///portal/permissao/todas
	@GetMapping(value = "/todas", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todos() {
	
		List<Permition> permitions = this.permitionRepository.findAll();
		
		return this.httpResponse.MensagemDeRetorno(0, permitions);
	}
}
