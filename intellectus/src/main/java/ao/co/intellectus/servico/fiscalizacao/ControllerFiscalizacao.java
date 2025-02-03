package ao.co.intellectus.servico.fiscalizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.PermissaoCursoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/fiscalizacao")
public class ControllerFiscalizacao {
	
	@SuppressWarnings("unused")
	@Autowired
	private UsuarioRepository usuarioReposiotry;
	@SuppressWarnings("unused")
	@Autowired
	private PermissaoCursoRepository permissaoCursoRepository;
	
	
	@RequestMapping(value = "/pendente/{userCode}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pendente(@PathVariable Integer userCode) { 
		
		
		

		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de aluno de encontrado");
	}
}


