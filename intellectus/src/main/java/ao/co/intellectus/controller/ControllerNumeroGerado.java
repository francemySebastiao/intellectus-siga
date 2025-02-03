package ao.co.intellectus.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/numeroGerado")
public class ControllerNumeroGerado {
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() { 
		ResponseCliente c=new ResponseCliente();
        List<NumeroGerado> todos = this.numeroGeradoRepository.findAll();
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody NumeroGerado ng){
		NumeroGerado numeroGerado = this.numeroGeradoRepository.findByDescricao(ng.getDescricao());
		if(numeroGerado != null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Esta descrição já existe.");
		if(ng.getUltimoNumero() >= ng.getProximoNumero())
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "O último número não pode ser maior ou igual ao próximo.");
		numeroGerado = new NumeroGerado();
		BeanUtils.copyProperties(ng,numeroGerado);
		this.numeroGeradoRepository.save(numeroGerado);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Geração de números com sucesso.");
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody NumeroGerado ng){
		NumeroGerado numeroGerado = this.numeroGeradoRepository.findByDescricao(ng.getDescricao());
		if(numeroGerado.getId() != ng.getId() && numeroGerado.getDescricao().equalsIgnoreCase(ng.getDescricao()))
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Esta descrição já existe.");
		if(ng.getUltimoNumero() >= ng.getProximoNumero())
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "O último número não pode ser maior ou igual ao próximo.");
		BeanUtils.copyProperties(ng,numeroGerado);
		this.numeroGeradoRepository.save(numeroGerado);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Geração de números com sucesso.");
	}
	
}
