package ao.co.intellectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.Feriado;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.FeriadoRepository;

@RestController
@RequestMapping("/feriado")
public class ControllerFeriado {

	@Autowired
	private FeriadoRepository feriadoRepository;

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody Feriado feriadoR) {
		ResponseCliente c = new ResponseCliente();

		Feriado feriado=new Feriado();
		feriado.setDia(feriadoR.getDia());
		feriado.setMes(feriadoR.getMes());
		
		this.feriadoRepository.save(feriado);
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(feriadoR);
		c.setMensagem("Feriado lançado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> buscarTodos() {
		ResponseCliente c = new ResponseCliente();
        
		Iterable<Feriado> feriados = this.feriadoRepository.findAll();
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(feriados);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}