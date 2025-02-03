package ao.co.intellectus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.Contador;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.ContradorRepository;

@RestController
@RequestMapping("/contador")
public class ControllerContador {
   
	@Autowired
	private ContradorRepository contadorRepository;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() { 
		
		ResponseCliente c=new ResponseCliente();
		
        List<Contador> todos = this.contadorRepository.findAll();
		
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todos);
		
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody Contador Pcontador) { 
		
		ResponseCliente c=new ResponseCliente();
		
        Contador contador = this.contadorRepository.findOne(Pcontador.getId());
        
        contador.setDescricao(Pcontador.getDescricao());
        contador.setContador(Pcontador.getContador());
        contador.setProximoValor(Pcontador.getProximoValor());
		
		
		this.contadorRepository.save(contador);
		
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
       	c.setMensagem("Parâmetro atualizado com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}
