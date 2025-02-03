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

import ao.co.intellectus.DTO.portal.RedifinirSenha;
import ao.co.intellectus.DTO.portal.UserPortalView;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import ao.co.intellectus.servico.portal.UserPortalService;

@RestController
@RequestMapping("/portal/usuario")
public class ControllerUser {

	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private UserPortalService userPortalSerVice;

	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody UserPortalView userPortal) {
		User user = new User();
		user.setUserName(userPortal.getUserName());
		user.setPassword(userPortal.getPassword());
		user.setName(userPortal.getName());
		user.setEmail(userPortal.getEmail());
		user.setActive(userPortal.isActive());
		user.setUserType(userPortal.getUserType());
		user.setInstCode(userPortal.getInstCode());
		user.setAdmin(userPortal.isAdmin());
		user.setTipoAcesso(userPortal.getTipoAcesso());
		// TODO : Analizar regra de negócio para os dados abaixo
		user.setTemporalIn(userPortal.getTemporalIn());
		user.setTemporalEnd(userPortal.getTemporalEnd());
		user.setUserCode(userPortal.getUserCode()); // TODO : Como gerar o código do usuário
		this.userPortalSerVice.salvar(user);
		return this.httpResponse.MensagemDeRetorno(0, "Usuário cadastrado com sucesso.");
	}

	@PutMapping(value = "/actualizar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody UserPortalView userPortal) {
		User user = new User();
		user.setId(userPortal.getId());
		user.setUserName(userPortal.getUserName());
		user.setPassword(userPortal.getPassword());
		user.setName(userPortal.getName());
		user.setEmail(userPortal.getEmail());
		user.setActive(userPortal.isActive());
		user.setUserType(userPortal.getUserType());
		user.setInstCode(userPortal.getInstCode());
		user.setAdmin(userPortal.isAdmin());
		user.setTipoAcesso(userPortal.getTipoAcesso());
		// TODO : Analizar regra de negócio para os dados abaixo
		user.setTemporalIn(userPortal.getTemporalIn());
		user.setTemporalEnd(userPortal.getTemporalEnd());
		user.setUserCode(userPortal.getUserCode()); // TODO : Como gerar o código do usuário
		this.userPortalSerVice.actualizar(user);
		return this.httpResponse.MensagemDeRetorno(0, "Usuário actualizado com sucesso.");
	}
	
	@PutMapping(value = "/redifinirSenha", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> redifinirSenha(@RequestBody RedifinirSenha dadosUser) {
		User user = this.userPortalSerVice.user(dadosUser.getUser());
		if(dadosUser.getNovaSenha().equals(dadosUser.getConfirmarSenha())) {
			user.setPassword(dadosUser.getNovaSenha());
			this.userPortalSerVice.refinirSenha(user);
			return this.httpResponse.MensagemDeRetorno(0, "Senha actualizada com sucesso !");
		}else {
			return this.httpResponse.MensagemDeRetorno(2, "As senhas introduzidas não correspodem !");
		}
	}

	@PostMapping(value = "/pesquisar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pesquisar(@RequestBody UserPortalView userPortal) {
		List<User> users = this.userPortalSerVice.pesquisar(userPortal);
		return this.httpResponse.MensagemDeRetorno(0, users);
	}
	
	@GetMapping(value = "/mudarEstado/{usuarioID}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pesquisar(@PathVariable Integer usuarioID) {
		User user = this.userPortalSerVice.user(usuarioID);
		user = this.userPortalSerVice.mudarEstado(user);
		String mensagem = user.isActive() ? "Usuário activado" : "Usuário desactivada";
		return this.httpResponse.MensagemDeRetorno(0, mensagem+ " com sucesso !");
	}


}
