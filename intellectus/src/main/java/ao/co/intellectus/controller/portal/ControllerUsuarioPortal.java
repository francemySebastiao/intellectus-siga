package ao.co.intellectus.controller.portal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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

import ao.co.intellectus.DTO.portal.UsuarioViewModel;
import ao.co.intellectus.DTO.portal.UsuariosPortalSemPass;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.portal.UserRepository;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/portal/usuarios")
public class ControllerUsuarioPortal {
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
    private UserRepository userRepository;
	
	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody UsuarioViewModel usuario) {
		User user=new User();
		user = this.userRepository.findByUserName(usuario.getUserName());
		if(user!=null) {
			return this.httpResponse.MensagemDeRetorno(0, "O nome de usuario já existe.");	
		}
		List<User> userEmail = this.userRepository.findByEmail(usuario.getEmail());
		if(userEmail!=null) {
			return this.httpResponse.MensagemDeRetorno(0, "O nome de usuario já existe.");
		}
		BeanUtils.copyProperties(usuario, user);
		this.userRepository.save(user);
		return this.httpResponse.MensagemDeRetorno(0, "Usuario cadastro com sucesso.");
	}
	
	
	@PutMapping(value = "/atualizar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody UsuarioViewModel usuario) {
		User user = this.userRepository.findById(usuario.getId());
		
		usuario.setId(user.getId());
		
		BeanUtils.copyProperties(usuario, user);
		
		this.userRepository.save(user);
		return this.httpResponse.MensagemDeRetorno(0, "Usuario cadastro com sucesso.");
	}
	
	@GetMapping(value = "/todos", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todos() {
		UsuariosPortalSemPass usuarioPw;
		List<UsuariosPortalSemPass> usuarioPws=new ArrayList<UsuariosPortalSemPass>(); 
		List<User> usuarios = this.userRepository.findAll();
		for (User user : usuarios) {
			usuarioPw=new UsuariosPortalSemPass();
			BeanUtils.copyProperties(user, usuarioPw);
			usuarioPws.add(usuarioPw);
		}
		return this.httpResponse.MensagemDeRetorno(0, usuarioPws);
	}
	
	///portal/usuarios/buscar/codigo/10
	@GetMapping(value = "/buscar/codigo/{codigo}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCodigo(@PathVariable Integer codigo) {
		UsuariosPortalSemPass usuarioPw=null;
		
		//List<User> usuarios = this.userRepository.findAll();
		
		User user = this.userRepository.findById(codigo);
	
		usuarioPw = new UsuariosPortalSemPass();
		
		BeanUtils.copyProperties(user, usuarioPw);
		
		return this.httpResponse.MensagemDeRetorno(0, usuarioPw);
	}
}