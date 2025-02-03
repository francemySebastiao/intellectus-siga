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

import ao.co.intellectus.DTO.portal.RolePortalView;
import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.Role;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.portal.ModuloRepository;
import ao.co.intellectus.repository.portal.RoleRespository;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import ao.co.intellectus.servico.portal.ModuloPortalService;
import ao.co.intellectus.servico.portal.RoleService;

@RestController
@RequestMapping("/portal/role")
public class ControllerRole {

	
	///portal/role/todos
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuloPortalService moduloPortalService;
	@Autowired
	private ModuloRepository moduloRepository;
	@Autowired
	private RoleRespository roleRespository;
	
	
	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody RolePortalView rolePortal) {
		Role role = new Role();
		
		role.setName(rolePortal.getName());
		role.setActive(rolePortal.isActive());
		role.setDescription(rolePortal.getDescription());
		
		System.err.println("MODULO: "+rolePortal.getModulo());
		
		Modulo modulo = this.moduloRepository.findById(rolePortal.getModulo());
		role.setModulo(modulo);
		
		
		this.roleRespository.save(role);
		
		//this.roleService.salvar(role);
		
		return this.httpResponse.MensagemDeRetorno(0, "Perfil cadastrado com sucesso.");
	}

	@PutMapping(value = "/atualizar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody RolePortalView rolePortal) {
		Role role = this.roleService.role(rolePortal.getId());
		Modulo modulo = this.moduloPortalService.modulo(rolePortal.getModulo());
		
		role.setName(rolePortal.getName());
		role.setActive(rolePortal.isActive());
		role.setDescription(rolePortal.getDescription());
		role.setModulo(modulo);
		this.roleService.actualizar(role);
		return this.httpResponse.MensagemDeRetorno(0, "Perfil actualizado com sucesso.");
	}

	@GetMapping(value = "/pesquisar/{nome}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@PathVariable String nome) {
		List<Role> roles = this.roleService.pesquisar(nome);
		return this.httpResponse.MensagemDeRetorno(0, roles);
	}
	
	@GetMapping(value = "/pesquisar/codigo/{codigo}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCodigo(@PathVariable Integer codigo) {
		Role role = this.roleRespository.findOne(codigo);
		return this.httpResponse.MensagemDeRetorno(0, role);
	}
	
	@GetMapping(value = "/pesquisar/{nome}/{moduloId}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@PathVariable String nome, @PathVariable Integer moduloId) {
		Modulo modulo = this.moduloPortalService.modulo(moduloId);
		List<Role> roles = this.roleService.pesquisar(nome, modulo);
		return this.httpResponse.MensagemDeRetorno(0, roles);
	}
	
	@GetMapping(value = "/todas", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todos() {
		
		List<Role> role = this.roleRespository.findAll();
		
		return this.httpResponse.MensagemDeRetorno(0, role);
	}

}
