package ao.co.intellectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.Profissao;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.ProfissaoRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/profissao")
public class ControllerProfissao {
	
	@Autowired
    private ProfissaoRepository profissaoRepository;
	
	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() {
		Iterable<Profissao> todas = profissaoRepository.findAll();
		if(todas == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "A profissão introduzida já existe.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(todas, 0);
	}
	
	@RequestMapping(value ="/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody Profissao p){
		Profissao profissao = this.profissaoRepository.findByDescricao(p.getDescricao());
		if(profissao !=null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Esta profissão já existe.");
		profissao = new Profissao();
		profissao.setDescricao(p.getDescricao());
		this.profissaoRepository.save(profissao);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Profissão adcionada com sucecco.");
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody Profissao p){
		Profissao profissao = this.profissaoRepository.findByDescricao(p.getDescricao());
		if(profissao.getId() != p.getId() && profissao.getDescricao().equalsIgnoreCase(p.getDescricao()))
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null,2, "A profissão introduzida já existe.");
		profissao.setDescricao(p.getDescricao());
		this.profissaoRepository.save(profissao);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null,0, "A profissão alterada com sucessso.");
	}
	
}
