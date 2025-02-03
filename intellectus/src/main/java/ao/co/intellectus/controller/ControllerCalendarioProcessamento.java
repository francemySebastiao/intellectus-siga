package ao.co.intellectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.CalendarioProcessamentoForm;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.CalendarioProcessamentoService;

@RestController
@RequestMapping("/calendario-processamento")
public class ControllerCalendarioProcessamento {	
	@Autowired
	private CalendarioProcessamentoService calendarioProcessamentoService;
	
	@PostMapping(value = "/salvar",produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody CalendarioProcessamentoForm calendarioDTO){
       return calendarioProcessamentoService.salvar(calendarioDTO);
	}
	
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todos(){
	   return this.calendarioProcessamentoService.buscarTodos();
	}
}
