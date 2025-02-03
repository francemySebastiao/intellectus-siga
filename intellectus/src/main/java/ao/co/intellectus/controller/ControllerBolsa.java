package ao.co.intellectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.Bolsa;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.BolsaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/bolsa")
public class ControllerBolsa {
	
	@Autowired
	private BolsaRepository bolsaRepository;
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody Bolsa bolsaCliente){
		Bolsa bolsa = this.bolsaRepository.findByCodigoOrDescricao(bolsaCliente.getCodigo(), bolsaCliente.getDescricao());
		if(bolsa != null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Este código ou tipo de bolsa já existe.");
		bolsa =  new Bolsa();
		bolsa.setCodigo(bolsaCliente.getCodigo());
		bolsa.setDescricao(bolsaCliente.getDescricao());
		this.bolsaRepository.save(bolsa);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Tipo de bolsa salva com sucusso.");	
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody Bolsa bolsaCliente){
		Bolsa bolsa = this.bolsaRepository.findByCodigoOrDescricao(bolsaCliente.getCodigo(), bolsaCliente.getDescricao());
		if(bolsa == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Erro ao validar a bolsa.");
		else if(bolsa.getId() != bolsaCliente.getId())
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Este código ou tipo de bolsa já existe.");
		bolsa.setCodigo(bolsaCliente.getCodigo());
		bolsa.setDescricao(bolsaCliente.getDescricao());
		this.bolsaRepository.save(bolsa);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Tipo de bolsa alterada com sucusso.");	
	}
	
	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todos(){
		Iterable<Bolsa> todas = this.bolsaRepository.findAll();
		if(todas == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de bolsa encontrado.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(todas, 0, null);	
	}

}
