package ao.co.intellectus.controller.portal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.portal.UserRolePortalView;
import ao.co.intellectus.DTO.portal.UserRolesEspecificoView;
import ao.co.intellectus.DTO.portal.UserRolesPortalView;
import ao.co.intellectus.DTO.portal.UserRolesView;
import ao.co.intellectus.model.portal.Role;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.model.portal.UserRole;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import ao.co.intellectus.servico.portal.RoleService;
import ao.co.intellectus.servico.portal.UserPortalService;
import ao.co.intellectus.servico.portal.UserRolePortalService;

@RestController
@RequestMapping("/portal/userRole")
public class ControllerUserRole {

	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private UserPortalService userPortalService;
	@Autowired
	private RoleService roleServie;
	@Autowired
	private UserRolePortalService userRolePortalService;

	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody UserRolePortalView userRolePortal) {
		UserRole userRole = new UserRole();
		User user = this.userPortalService.user(userRolePortal.getUser());
		Role role = this.roleServie.role(userRolePortal.getRole());
		userRole.setUser(user);
		userRole.setRole(role);
		userRole.setActive(userRolePortal.getActive());
		this.userRolePortalService.salvar(userRole);
		return this.httpResponse.MensagemDeRetorno(0, "Perfil atribuído com sucesso.");
	}

	@DeleteMapping(value = "/remover", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> remover(@RequestBody UserRolePortalView userRolePortal) {
		UserRole userRole = this.userRolePortalService.userRole(userRolePortal.getId());
		this.userRolePortalService.remover(userRole);
		return this.httpResponse.MensagemDeRetorno(0, "Perfil removido com sucesso");
	}

	@GetMapping(value = "/pesquisar/{id}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@PathVariable Integer id) {
		UserRolesView userRolesView = new UserRolesView();
		User user = this.userPortalService.user(id);
		List<UserRolesEspecificoView> roles = new ArrayList<UserRolesEspecificoView>();
		for (UserRole userRole : this.userRolePortalService.userRoles(user)) {
			UserRolesEspecificoView userRolesEspecifico = new UserRolesEspecificoView();
			userRolesEspecifico.setId(userRole.getId());
			userRolesEspecifico.setActive(userRole.getActive());
			userRolesEspecifico.setRole(userRole.getRole());;
			roles.add(userRolesEspecifico);
		}
		userRolesView.setUser(user);
		userRolesView.setRoleUserRolesViewEspecifica(roles);
		return this.httpResponse.MensagemDeRetorno(0, userRolesView);
	}

	@GetMapping(value = "/pesquisarRoles/{id}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pesquisarRoles(@PathVariable Integer id) {
		User user = this.userPortalService.user(id);
		
		List<Role> roles = this.userRolePortalService.roles(user);
		return this.httpResponse.MensagemDeRetorno(0, roles);
	}
	
	@PostMapping(value = "/registar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> registar(@RequestBody UserRolesPortalView userRolePortal) {
		User user = this.userPortalService.user(userRolePortal.getUser());
		for(Role role: userRolePortal.getRoles()) {
			if(this.userRolePortalService.permissaoExiste(user, role) == false) {
				UserRole userRole = new UserRole();
				userRole.setRole(role);
				userRole.setUser(user);
				userRole.setActive(true);
				this.userRolePortalService.salvar(userRole);
			}
		}
		return this.httpResponse.MensagemDeRetorno(0, "Perfis atribuídos com sucesso.");
	}
	
	@PutMapping(value = "/mudarEstado", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> mudarEstado(@RequestBody UserRolePortalView userRolesView) {
		User user = this.userPortalService.user(userRolesView.getUser());
		UserRole userRole = this.userRolePortalService.mudarEstado(user, userRolesView.getRole());
		String mensagem = userRole.getActive() ? "Permissão activada" : "Permissão desactivada";
		return this.httpResponse.MensagemDeRetorno(0, mensagem + " com sucesso !");
	}

}
