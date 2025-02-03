package ao.co.intellectus.controller.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.portal.roleModulePortalView;
import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.Role;
import ao.co.intellectus.model.portal.RoleModule;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import ao.co.intellectus.servico.portal.ModuloPortalService;
import ao.co.intellectus.servico.portal.RoleModulePortalService;
import ao.co.intellectus.servico.portal.RoleService;

@RestController
@RequestMapping("/portal/roleModule")
public class ControllerRoleModule {

	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private RoleModulePortalService roleModuleService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuloPortalService moduloPortalService;

	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody roleModulePortalView roleModuleView) {
		RoleModule roleModule = new RoleModule();
		Role role = this.roleService.role(roleModuleView.getRole());
		Modulo modulo = this.moduloPortalService.modulo(roleModuleView.getModulo());
		roleModule.setDescricao(roleModuleView.getDescricao());
		roleModule.setComponente(roleModuleView.getComponente());
		roleModule.setActive(roleModuleView.isActive());
		roleModule.setModulo(modulo);
		roleModule.setRole(role);
		this.roleModuleService.salvar(roleModule);
		return this.httpResponse.MensagemDeRetorno(0, "Perfil adcioando ao módulo com sucesso. ");
	}
	
	@DeleteMapping(value = "/remover", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> remover(@RequestBody roleModulePortalView roleModuleView) {
		RoleModule roleModule = this.roleModuleService.roleModule(roleModuleView.getId());
		this.roleModuleService.remover(roleModule);
		return this.httpResponse.MensagemDeRetorno(0, "Permissão do perfil removido com sucesso.");
	}
}
