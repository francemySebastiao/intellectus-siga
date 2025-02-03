package ao.co.intellectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.CursoEnsinoMedio;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.CursoEnsinoMedioRepository;

@RestController
@RequestMapping("/cursoEnsinoMedio")
public class ControllerCursoEnsinoMedio {
	@Autowired
	private CursoEnsinoMedioRepository cursoEsinoMedioRepository;

	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() { 
		ResponseCliente c=new ResponseCliente();
		Iterable<CursoEnsinoMedio> todos = this.cursoEsinoMedioRepository.findAll();
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

}
