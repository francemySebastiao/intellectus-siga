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

import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;


@RestController
@RequestMapping("/moeda")
public class ControllerMoeda {

	@Autowired
	private MoedaRepository repositoryMoeda;

	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces="application/json")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody Moeda m) {
		Moeda moeda = this.repositoryMoeda.findByDesignacaoOrMoeda(m.getDesignacao(), m.getMoeda());
		if(moeda != null && moeda.getDesignacao().equalsIgnoreCase(m.getDesignacao()))
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Já existe uma moeda com esta desiganação.");
		else if(moeda != null && moeda.getMoeda().equalsIgnoreCase(m.getMoeda()))
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Já está existe uma moeda com esta sigla");
		moeda = new Moeda();
		BeanUtils.copyProperties(m, moeda);
		this.repositoryMoeda.save(moeda);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Moeda salva com sucesso.");
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody Moeda m) {
		Moeda moeda = this.repositoryMoeda.findByDesignacaoOrMoeda(m.getDesignacao(), m.getMoeda());
		if(moeda != null && moeda.getId() != m.getId() && moeda.getDesignacao().equalsIgnoreCase(m.getDesignacao()))
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Já existe uma moeda com esta designação.");
		else if(moeda != null && moeda.getId() != m.getId() && moeda.getMoeda().equalsIgnoreCase(m.getMoeda()))
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Já está existe uma moeda com esta sigla.");
		moeda = new Moeda();
		BeanUtils.copyProperties(m, moeda);
		this.repositoryMoeda.save(moeda);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Moeda alterada com sucesso.");
	}
	
	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Iterable<Moeda> todos() {
		return repositoryMoeda.findAll();
	}
	
	@RequestMapping(value = "/todasMoedas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todas() {
		Iterable<Moeda> todas = repositoryMoeda.findAll();
		if(todas == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhuma Moeda encontrada.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(todas, 0, null);
	}
	
}