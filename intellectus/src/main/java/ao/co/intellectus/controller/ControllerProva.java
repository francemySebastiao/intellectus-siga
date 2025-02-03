package ao.co.intellectus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.Prova;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.ProvaRepository;

@RestController
@RequestMapping("/prova")
public class ControllerProva {
	
	@Autowired
	private ProvaRepository provaRepository;
	
	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarTodas() { 
		ResponseCliente c=new ResponseCliente();
		
		List<Prova> todas = this.provaRepository.findByEstado(true);
		
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todas);
		
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/extraordinarias", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> extraordinarias() { 
		ResponseCliente c=new ResponseCliente();
		
		Iterable<Prova> todas = this.provaRepository.findByExtraordinaria(true);
		
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todas);
		
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}
