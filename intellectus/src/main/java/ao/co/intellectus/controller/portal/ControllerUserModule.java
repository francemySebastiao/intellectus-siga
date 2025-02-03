package ao.co.intellectus.controller.portal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.portal.ModuloPortalView;
import ao.co.intellectus.DTO.portal.UserModuleView;
import ao.co.intellectus.DTO.portal.UserModuleViews;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.model.portal.UserModule;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import ao.co.intellectus.servico.portal.ModuloPortalService;
import ao.co.intellectus.servico.portal.UserModuleService;
import ao.co.intellectus.servico.portal.UserPortalService;

@RestController
@RequestMapping("/portal/userModule")
public class ControllerUserModule {
	
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private UserPortalService userService;
	@Autowired
	private UserModuleService userModuleService;
	@Autowired
	private ModuloPortalService moduloPortalService;
	
	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody UserModuleView userModuleView){
		User user = this.userService.user(userModuleView.getUser());
		this.userModuleService.atrelagemDeModulo(userModuleView.getModulos(), user);
		return this.httpResponse.MensagemDeRetorno(0,"Modulo(s) adcionado/removido(s) com sucesso !");
	}
	
	@GetMapping(value = "/activo/{userId}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> activo(@PathVariable Integer userId){
		User user = this.userService.user(userId);
		UserModuleViews userModuleViews = new UserModuleViews();
		List<ModuloPortalView> modulos = new ArrayList<ModuloPortalView>();
		for(ModuloPortalView modulo: this.moduloPortalService.todos()) {
			ModuloPortalView moduloPortalView = new ModuloPortalView();
			moduloPortalView.setId(modulo.getId());
			moduloPortalView.setActive(modulo.getActive() == null ? false : false);
			moduloPortalView.setSeleccionado(moduloPortalView.getActive());
			encontrado:
			for(UserModule userModule: this.userModuleService.modulos(user)) {
				if(moduloPortalView.getId() == userModule.getModulo().getId()) {
					moduloPortalView.setActive(userModule.isActive());
					moduloPortalView.setSeleccionado(userModule.isActive());
					break encontrado;
				}
			}
			moduloPortalView.setName(modulo.getName());
			moduloPortalView.setDescription(modulo.getDescription());
			moduloPortalView.setFileIconName(modulo.getFileIconName());
			moduloPortalView.setEnderecoUrl(modulo.getEnderecoUrl());
			modulos.add(moduloPortalView);
		}
		userModuleViews.setUser(user);
		userModuleViews.setModulos(modulos);
		return this.httpResponse.MensagemDeRetorno(0,userModuleViews);
	}

}
