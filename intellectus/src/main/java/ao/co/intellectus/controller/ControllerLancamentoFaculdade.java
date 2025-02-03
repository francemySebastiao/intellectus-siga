package ao.co.intellectus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.LancamentoFaculdadeCliente;
import ao.co.intellectus.model.Faculdade;
import ao.co.intellectus.model.LancamentoFaculdade;
import ao.co.intellectus.model.Prova;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.FaculdadeRepository;
import ao.co.intellectus.repository.LancamentoFaculdadeRepository;
import ao.co.intellectus.repository.ProvaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/lancamento/faculdade")
public class ControllerLancamentoFaculdade {
	@Autowired
	private LancamentoFaculdadeRepository lancamentoFaculdadeRepository;
	@Autowired
	private FaculdadeRepository faculdadeRepository;
	@Autowired
	private ProvaRepository provaRepository;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todas() { 	
         List<LancamentoFaculdade> todos = this.lancamentoFaculdadeRepository.findAll();
         if(todos.isEmpty())
        	 return ObjectoDeRetornoParcial.MensagemDeRetorno(2, "Nenhum registo de faculdade encontrado.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(todos, 0);
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody LancamentoFaculdadeCliente lancamentoFaculdadeCliente) { 
		LancamentoFaculdade lancamentoFaculdade = new  LancamentoFaculdade();
		Faculdade faculdade = this.faculdadeRepository.findOne(lancamentoFaculdadeCliente.getId());
		Prova prova = this.provaRepository.findOne(lancamentoFaculdadeCliente.getProva());
		if(faculdade == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(2, "Registo de faculdade não encontrado.");
		else if(prova == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(2, "Registo de prova não encontrado.");
		lancamentoFaculdade.setFaculdade(faculdade);
		lancamentoFaculdade.setProva(prova);
		lancamentoFaculdade.setPermissao(lancamentoFaculdadeCliente.getPermissao());
		this.lancamentoFaculdadeRepository.save(lancamentoFaculdade);
        return ObjectoDeRetornoParcial.MensagemDeRetorno(0, "Faculdade actualizdo com sucesso.");
	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody LancamentoFaculdadeCliente lancamentoFaculdadeCliente) { 
		LancamentoFaculdade lancamentoFaculdade = this.lancamentoFaculdadeRepository.getOne(lancamentoFaculdadeCliente.getId());
		Faculdade faculdade = this.faculdadeRepository.findOne(lancamentoFaculdadeCliente.getId());
		Prova prova = this.provaRepository.findOne(lancamentoFaculdadeCliente.getProva());
		if(lancamentoFaculdade == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(2, "Registo de lançamento não encontrado");
		else if(faculdade == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(2, "Registo de faculdade não encontrado.");
		else if(prova == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(2, "Registo de prova não encontrado.");
		lancamentoFaculdade.setFaculdade(faculdade);
		lancamentoFaculdade.setProva(prova);
		lancamentoFaculdade.setPermissao(lancamentoFaculdadeCliente.getPermissao());
		this.lancamentoFaculdadeRepository.save(lancamentoFaculdade);
        return ObjectoDeRetornoParcial.MensagemDeRetorno(0, "Faculdade actualizdo com sucesso.");
	}
	
}
