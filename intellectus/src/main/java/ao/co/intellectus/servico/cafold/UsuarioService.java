package ao.co.intellectus.servico.cafold;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.Modules;
import ao.co.intellectus.DTO.RecuperacaoSenha;
import ao.co.intellectus.DTO.Roles;
import ao.co.intellectus.DTO.UserCliente;
import ao.co.intellectus.DTO.portal.Functionalities;
import ao.co.intellectus.DTO.portal.ModuleRetorno;
import ao.co.intellectus.DTO.portal.RolesPermissoes;
import ao.co.intellectus.DTO.portal.UserClienteRetorno;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Docente;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.portal.Modulo;
import ao.co.intellectus.model.portal.RoleModule;
import ao.co.intellectus.model.portal.User;
import ao.co.intellectus.model.portal.UserModule;
import ao.co.intellectus.model.portal.UserRole;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.repository.portal.RoleModuleRespository;
import ao.co.intellectus.repository.portal.UserModuleRepository;
import ao.co.intellectus.repository.portal.UserRoleRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private TipoUsuarioService tipoUsuarioService;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private DocenteService docenteService;
	@Autowired
	private DataService dataService;
	@Autowired
	private UserModuleRepository userModuleRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private RoleModuleRespository roleModuleRespository;
	
	//private final static String CHAVE_API = "INT&LL&CTU$UGS!";
	private final static long TEMPO_MAXIMO_TOKEN = 3600000L;
	private static String CHAVE_API = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
	
	public Usuario usuario(Integer userCode) {
		return this.usuarioRepository.findByUserCode(userCode);
	}
	
	public Usuario usuario(String userCode) {
		Usuario usuario = this.usuarioRepository.findOne(Integer.valueOf(userCode));
		if(usuario  == null)
			throw new RegistoNaoEncontradoException("Registo de usuário não foi encontrada");
		return usuario;
	}
	
	public Usuario usuario(Integer userCode, String usuerName) {
		Usuario usuario = this.usuarioRepository.findByUserCodeOrUserName(userCode,usuerName);
		if(usuario  == null)
			throw new RegistoNaoEncontradoException("Registo de usuário não foi encontrada");
		return usuario;
	}
	
	public Usuario usuarioPorEmail(String email) {
		return email == null ? null : this.usuarioRepository.findByEmail(email);
	}
	
	public List<Usuario> todos(){
		List<Usuario> todos = this.usuarioRepository.findAll();
		if(todos.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum registo de usuários não encontrado.");
		return todos;
	}
	
	public String nomeDoUsuario(RecuperacaoSenha recuperacaoSenha) {
		String tipoDeUsuario = this.tipoUsuarioService.validarTipoUsuario(recuperacaoSenha.getTipoUsuario());
		switch(tipoDeUsuario) {
			case "D":
				Docente docente = this.docenteService.docenctePorEmailAndDataDeNascimento(recuperacaoSenha.getEmail(), recuperacaoSenha.getDataDeNascimento());
				return docente.getNome();
			case "A":
				Aluno aluno = this.alunoService.alunoPorEmailAndDataDeNascimento(recuperacaoSenha.getEmail(), recuperacaoSenha.getDataDeNascimento()); 
				return aluno.getNome();	
		}
		return null;
	}
	

	
	
	
    //Método de amostra para construir um JWT
    public  String criarJWT(String id, User usuario, String assunto) {

        //O algoritmo de assinatura JWT que usaremos para assinar o token
        SignatureAlgorithm assinaturaDoAlgoritmo = SignatureAlgorithm.HS256;
   
        Date tempoActual = this.dataService.getTempoActual();
        
        //Assinaremos nosso JWT com nosso segredo ApiKey
        byte[] chave_api = DatatypeConverter.parseBase64Binary(CHAVE_API);
        Key signingKey = new SecretKeySpec(chave_api, assinaturaDoAlgoritmo.getJcaName());
        
        UserCliente userCliente = new UserCliente();
        userCliente.setName(usuario.getName());
        userCliente.setUserName(usuario.getUserName());
        userCliente.setUserCode(usuario.getUserCode().toString());
        userCliente.setAdmin(usuario.isAdmin());
        userCliente.setInstCode(usuario.getInstCode().toString());
        
        List<Modules> modules = new ArrayList<Modules>();
	        
            //MODULOS QUE TEM
            Modules m=null;
            List<Roles> roles = new ArrayList<Roles>();
	    	Roles role = new Roles();
	    	List<UserModule> modulos = this.userModuleRepository.findByUser(usuario);
	    	 List<UserRole> usurRole;
	    	for (UserModule o : modulos) {
	    		m = new Modules();
            	m.setName(o.getModulo().getName());
    	        m.setFileIconName(o.getModulo().getFileIconName());
    	        m.setUrl(o.getModulo().getEnderecoUrl());
    	        m.setActivo(o.getModulo().isActive());
    	        
    	        System.err.println("VERIFICANDO A URL: "+o.getModulo().getEnderecoUrl());
    	        
    	        usurRole = this.userRoleRepository.findByUserAndRoleModulo(usuario, o.getModulo());
    	        if (!usurRole.isEmpty()) {
    	        	for (UserRole u : usurRole) {
    	        		role = new Roles();
    	        		role.setRole(u.getRole().getName());
    	        		roles.add(role);
    	        	}
    	        	
    	        	m.setRoles(roles);
    	        	modules.add(m); 
    	        	//roles=null;    	        	
    	        }else {
    	        	m.setRoles(new ArrayList<Roles>());	
    	        	modules.add(m); 
    	        }
			}
            
	    
		userCliente.setModules(modules);
        userCliente.setGoToModule(false);
        userCliente.setIat("1576756220");
        
        JwtBuilder builder = Jwts.builder()
        		.setHeaderParam("typ","JWT")
        		.setIssuedAt(tempoActual)
        		.claim("token", userCliente)
        		.signWith(assinaturaDoAlgoritmo, signingKey);
        
        //Defininindo o tempo de expiração 
        tempoDeExpiracao(TEMPO_MAXIMO_TOKEN, tempoActual, builder);
        
        //Constrói o JWT e o serializa em uma sequência compacta e segura para URL
        return builder.compact();
    }

    
    
    //,List<UserRole> userRole
    public  String tokenPermissoes(String id, User usuario,Modulo module) {
    	 //O algoritmo de assinatura JWT que usaremos para assinar o token
        SignatureAlgorithm assinaturaDoAlgoritmo = SignatureAlgorithm.HS256;
   
        Date tempoActual = this.dataService.getTempoActual();
        
        //Assinaremos nosso JWT com nosso segredo ApiKey
        byte[] chave_api = DatatypeConverter.parseBase64Binary(CHAVE_API);
        Key signingKey = new SecretKeySpec(chave_api, assinaturaDoAlgoritmo.getJcaName());
        
        //UserCliente userCliente = new UserCliente();
        UserClienteRetorno userCliente=new UserClienteRetorno(); 
        userCliente.setName(usuario.getName());
        userCliente.setUserName(usuario.getUserName());
        userCliente.setUserCode(usuario.getUserCode().toString());
        userCliente.setAdmin(usuario.isAdmin());
        userCliente.setInstCode(usuario.getInstCode().toString());
        
        //List<Modules> modules = new ArrayList<Modules>();
        
        List<ModuleRetorno> modules = new ArrayList<ModuleRetorno>();
        
	        
            //MODULOS QUE TEM
           
            ModuleRetorno mR=null;
            List<Roles> roles = new ArrayList<Roles>();
	    	List<RolesPermissoes> rolesPermissoes=new ArrayList<RolesPermissoes>();
	    	
	    	
	    	RolesPermissoes rlps;
	    	Functionalities app;
	    	List<Functionalities> functionalities=null;
            
            Roles role = new Roles();
	    	
            //List<UserModule> modulos = this.userModuleRepository.findByUser(usuario);
	    	//findByUserAndModulo
            UserModule modulos = this.userModuleRepository.findByUserAndModulo(usuario,module);
            
	    	List<UserRole> usurRole;
	    	if(modulos!=null) {
	    		
	    		
	    		
	    		mR=new ModuleRetorno();
            	
	    		mR.setName(modulos.getModulo().getName());
	    		mR.setFileIconName(modulos.getModulo().getFileIconName());
	   
    	        usurRole = this.userRoleRepository.findByUserAndRoleModulo(usuario, modulos.getModulo());
    	        if (!usurRole.isEmpty()) {
    	        	
    	        	
    	        	
    	        	for (UserRole u : usurRole) {
    	        		rlps=new RolesPermissoes();
    	        		role = new Roles();
    	        		role.setRole(u.getRole().getName());
    	        		rlps.setRole(u.getRole().getName());
    	       
    	        		
    	        		//ESSA PESQUISA DEVE MUDAR PARA OBEDECER A NOVA ESTRUTURA....
    	        		List<RoleModule> roleModule = this.roleModuleRespository.findByRoleAndModulo(u.getRole(), modulos.getModulo());
    	        		
    	        		
    	        		//PEGA OS COMPONENTES, É AQUI ONDE VAMOS ACTUAR...
    	        		functionalities=new ArrayList<Functionalities>();
    	        		for (RoleModule b : roleModule) {
    	        			app=new Functionalities();
    	        			app.setComponente(b.getComponente());
    	        			app.setDescricao(b.getDescricao());
    	        			functionalities.add(app);
						}
    	        		
    	        		rlps.setFunctionalities(functionalities);
    	        		
    	        		
    	        		roles.add(role);
    	        		rolesPermissoes.add(rlps);
    	        	}
    	        	mR.setRoles(rolesPermissoes);
    	        	
    	        	modules.add(mR);   	        	
    	        }else {
    	        	mR.setRoles(new ArrayList<RolesPermissoes>());
    	        	modules.add(mR); 
    	        }
    	        
    	        
    	        
			}//FIM DO IF...
            
	    
		userCliente.setModules(modules);
        userCliente.setGoToModule(false);
        userCliente.setIat("1576756220");
        
        JwtBuilder builder = Jwts.builder()
        		.setHeaderParam("typ","JWT")
        		.setIssuedAt(tempoActual)
        		.claim("token", userCliente)
        		.signWith(assinaturaDoAlgoritmo, signingKey);
        
        //Defininindo o tempo de expiração 
        tempoDeExpiracao(TEMPO_MAXIMO_TOKEN, tempoActual, builder);
        
        //Constrói o JWT e o serializa em uma sequência compacta e segura para URL
        return builder.compact();
    }
    
    
    
    
    
	private void tempoDeExpiracao(long tempoDeExpiracao, Date tempoActual, JwtBuilder builder) {
		if(tempoDeExpiracao > 0) {
        	long tempoTotal = tempoActual.getTime() + tempoDeExpiracao;
        	Date expiracao = new Date(tempoTotal);
        	builder.setExpiration(expiracao);
        }
	}
	
}
