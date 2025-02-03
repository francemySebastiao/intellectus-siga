package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.CursoAndPermissao;
import ao.co.intellectus.DTO.PermissaoAdicionar;
import ao.co.intellectus.DTO.PermissaoUsuario;
import ao.co.intellectus.DTO.RecuperacaoSenha;
import ao.co.intellectus.DTO.UsuarioAudit;
import ao.co.intellectus.DTO.UsuarioCurso;
import ao.co.intellectus.DTO.portal.GetTokenFuncionalidades;
import ao.co.intellectus.DTO.portal.Login;
import ao.co.intellectus.config.seguranca.Criptografar;
import ao.co.intellectus.model.JWT;
import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.model.portal.UserModule;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.PermissaoCursoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.repository.portal.ModuloRepository;
import ao.co.intellectus.repository.portal.UserModuleRepository;
import ao.co.intellectus.repository.portal.UserRepository;
import ao.co.intellectus.servico.cafold.EmailService;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.cafold.UsuarioService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/usuario")
public class ControllerUsuario {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PermissaoCursoRepository permissaoCursoRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private HttpResponse notificacaoService;
	@Autowired
	private UserRepository userRepository;
	//@Autowired
	//private UserRoleRepository userRoleRepository;
	@Autowired
	private ModuloRepository moduloRepository;
	@Autowired
	private UserModuleRepository userModuleRepository;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() {
		ResponseCliente c = new ResponseCliente();
		// List<Usuario> usuarios =
		// this.usuarioRepository.findByFinaceiroOrderByName(true);

		Iterable<Usuario> usuarios = this.usuarioRepository.findByOrderByName();
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(usuarios);

		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/financeiro", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarUsuariosFinanceiro() {
		ResponseCliente c = new ResponseCliente();

		List<Usuario> usuarios = this.usuarioRepository.findByFinaceiroOrderByName(true);
		// Iterable<Usuario> usuarios = this.usuarioRepository.findAll();

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(usuarios);

		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarCursos", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCursos(@RequestBody UsuarioAudit u) {
		ResponseCliente c = new ResponseCliente();

		// Usuario usuario = this.usuarioRepository.findByUserName(u.getUserName());

		Usuario usuario = this.usuarioRepository.findOne(u.getCodigo());

		PermissaoUsuario permissao = new PermissaoUsuario();

		permissao.setNome(usuario.getName());
		permissao.setCodigo(usuario.getId());
		CursoAndPermissao pm;
		List<CursoAndPermissao> pms = new ArrayList<CursoAndPermissao>();

		List<PermissaoCurso> usuarioCursos = this.permissaoCursoRepository.findByUsuario(usuario);
		for (PermissaoCurso o : usuarioCursos) {
			pm = new CursoAndPermissao();
			pm.setDescricao(o.getCurso().getDescricao());
			pm.setId(o.getCurso().getId());
			pm.setPermissao(o.getPermissao());
			pm.setCodigoPermissao(o.getId());

			pms.add(pm);
		}
		permissao.setCursoPermissao(pms);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(permissao);

		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// usuario/permissaoPorCurso
	@RequestMapping(value = "/permissaoPorCurso", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> permissaoPorCurso(@RequestBody UsuarioCurso u) {
		ResponseCliente c = new ResponseCliente();

		PermissaoCurso permissaoUsuario = this.permissaoCursoRepository.findByCursoIdAndUsuario(u.getCurso(),
				this.usuarioRepository.findByUserName(u.getUserName()));

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setPermissao(permissaoUsuario.getPermissao());
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/adicionarOrAtualizar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> adicionarOrAtualizar(@RequestBody PermissaoAdicionar u) {
		ResponseCliente c = new ResponseCliente();
		String mensagem = "";

		PermissaoCurso pc = this.permissaoCursoRepository.findOne(u.getCodigoPermissao());
		if (pc != null) {
			// PARA ATUALIZAR
			pc.setPermissao(u.getTipoPermissao());
			this.permissaoCursoRepository.save(pc);
			mensagem = "Atualizado com sucesso.";
		} else {
			// PARA ADICIONAR
			mensagem = "Adicionado com sucesso.";
		}
		c.setMensagem(mensagem);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/inciarSessao", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<JWT> IniciarSessao() {

		User usuario = new User();

		String token = this.usuarioService.criarJWT("2", usuario, "Iniciar sessão");

		return ObjectoDeRetornoParcial.JWT(0, token, "ok");
	}

	
	//usuario/login
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> IniciarSessao(@RequestBody Login login) {
		User usuario = this.userRepository.findByUserName(login.getUserName());
		if (usuario == null) {
			return this.notificacaoService.MensagemDeRetorno(2, "Usuario Inavalído.");
		}
		if (usuario.getPassword().equals(login.getPassword())) {
			String token = this.usuarioService.criarJWT("2", usuario, "Iniciar sessão");
			return this.notificacaoService.retornoLogin(0, "OK", token, Permissao.LEITURA, usuario.getUserType());
		} else {
			return this.notificacaoService.MensagemDeRetorno(2, "Verifique sua password.");
		}
	}

	@RequestMapping(value = "/getFuncionalidades", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> getFuncionalidades(@RequestBody GetTokenFuncionalidades getToken) {
		User usuario = this.userRepository.findByUserName(getToken.getUsuario());

		Modulo module = this.moduloRepository.findByName(getToken.getModulo());
        if(module==null) {
        	return this.notificacaoService.MensagemDeRetorno(2, "Problema de configuração do modulo.");
        }		
		
		UserModule userModule = this.userModuleRepository.findByUserAndModulo(usuario, module);
		
		
		if(userModule!=null) {
			String token = this.usuarioService.tokenPermissoes("2",usuario,module);			
			return this.notificacaoService.retornoLogin(0, "OK", token, Permissao.LEITURA, usuario.getUserType());
		}else {
			return this.notificacaoService.MensagemDeRetorno(2, "Sem acesso ao modulo requeirdo.");
		}
	}

	@PostMapping(value = "/password/reset", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> recupercao(@RequestBody RecuperacaoSenha recuperacaoSenha) {
		String nome = this.usuarioService.nomeDoUsuario(recuperacaoSenha);
		if (recuperacaoSenha.isEnviado()) {
			String assunto = "Recuperação de senha", mensagem;
			String emailMascarado = this.emailService.esconderPrefixo(recuperacaoSenha.getEmail());
			mensagem = "Prezado(a) " + nome + ", a sua nova senha é: " + recuperacaoSenha.getSenha();
			this.emailService.reportar(assunto, mensagem, recuperacaoSenha.getEmail());
			return this.notificacaoService.MensagemDeRetorno(0, "Uma nova senha foi enviada para " + emailMascarado
					+ ". Por favor, verifique a sua caixa de entrada.");
		} else {
			return this.notificacaoService.MensagemDeRetorno(4, "Usuário Encontrado.");
		}
	}

	@RequestMapping(value = "/encriptar", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public String encriptar() {
		List<User> users = this.userRepository.findAll();

		for (User u : users) {
			u.setPassword(Criptografar.encriptografar("Angola@13"));
			this.userRepository.save(u);
		}
		return "Processo finalizado com sucesso!";
	}
}
