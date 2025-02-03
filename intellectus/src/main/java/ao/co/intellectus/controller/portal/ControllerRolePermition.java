package ao.co.intellectus.controller.portal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.portal.PermirionViewModel;
import ao.co.intellectus.DTO.portal.RolePermitionViewModel;
import ao.co.intellectus.model.portal.Permition;
import ao.co.intellectus.model.portal.Role;
import ao.co.intellectus.model.portal.RolePermition;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.portal.PermitionRepository;
import ao.co.intellectus.repository.portal.RolePermitionRepository;
import ao.co.intellectus.repository.portal.RoleRespository;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/portal/role/permition")
public class ControllerRolePermition {
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private RoleRespository roleRepository;
	@Autowired
	private RolePermitionRepository rolePermitionRepository;
	@Autowired
	private PermitionRepository permitionRepository;
	
	
	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody RolePermitionViewModel rP) {
		RolePermition rolePermition;
		Permition permition;
		Role role = this.roleRepository.findOne(rP.getRole());
		List<PermirionViewModel> permitions = rP.getPermitions();
		for (PermirionViewModel o : permitions) {
			permition     = this.permitionRepository.findOne(o.getId());
			rolePermition = new RolePermition();
			rolePermition.setRole(role);
			rolePermition.setPermition(permition);	
		    this.rolePermitionRepository.save(rolePermition);
		}
		return this.httpResponse.MensagemDeRetorno(0, "Operação realizada com sucesso.");
	}
	
	@GetMapping(value = "/buscar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> busarPorRole(@RequestParam Integer codigo) {
		
		List<RolePermition> pRole = this.rolePermitionRepository.findByRoleId(codigo);
		
		List<Permition> permitions=new ArrayList<Permition>();
		for (RolePermition o : pRole) {
			permitions.add(o.getPermition());
		}
		return this.httpResponse.MensagemDeRetorno(0, "Operação realizada com sucesso.",permitions);
	}
}
