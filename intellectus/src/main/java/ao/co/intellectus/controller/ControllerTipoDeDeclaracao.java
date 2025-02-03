package ao.co.intellectus.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.TipoDeDeclaracao;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.TipoDeDeclaracaoRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/tipoDeDeclaracao")
public class ControllerTipoDeDeclaracao {
	@Autowired
	private TipoDeDeclaracaoRepository tipoDeDeclaracaoRepository;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todosDocumentosActivos() { 
		Iterable<TipoDeDeclaracao> todos = this.tipoDeDeclaracaoRepository.findByStatus(true);
		if(todos == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de documento activo.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(todos, 0,null);
	}
	
	@RequestMapping(value = "/todosDocumentos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todosDocumentos() { 
		Iterable<TipoDeDeclaracao> todos = this.tipoDeDeclaracaoRepository.findAll();
		if(todos == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de documento");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(todos, 0,null);
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody TipoDeDeclaracao tipoDeDeclaracao){
		TipoDeDeclaracao documento = this.tipoDeDeclaracaoRepository.findByDescricao(tipoDeDeclaracao.getDescricao());
		if(documento != null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "O documento introduzido já existe.");
		documento = new TipoDeDeclaracao();
		BeanUtils.copyProperties(tipoDeDeclaracao, documento);
		this.tipoDeDeclaracaoRepository.save(documento);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Documento salvo com sucesso.");
	}
	
	@RequestMapping(value= "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody TipoDeDeclaracao tipoDeDeclaracao){
		//TipoDeDeclaracao documento = this.tipoDeDeclaracaoRepository.findById(tipoDeDeclaracao.getId());
		
		TipoDeDeclaracao documento = new TipoDeDeclaracao();
		BeanUtils.copyProperties(tipoDeDeclaracao, documento);
		this.tipoDeDeclaracaoRepository.save(documento);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Documento actualizado com sucesso.");
	}
	
}
