package ao.co.intellectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.BuscaAluno;
import ao.co.intellectus.model.PresencaProfessor;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.PresencaProfessorRepository;

@RestController
@RequestMapping("/presencaProfessor")
public class ControllerPresencaProfessor {

	@Autowired
	private PresencaProfessorRepository presencaProfessorRepository;
	/*
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private LecionaRepository lecionaRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private DisciplinaRepository disciplinaREpository;
	@Autowired
	private SalaRepository salaRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private DocenteRepository docenteRepository;
	*/
	@RequestMapping(value = "/registrar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody BuscaAluno alunoBusca) {
		ResponseCliente c=new ResponseCliente();
	
		
	    c.setResultado(null);
	    c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todas() {
		ResponseCliente c = new ResponseCliente();
        
		Iterable<PresencaProfessor> todas = this.presencaProfessorRepository.findAll();
		
		c.setResultado(todas);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/porProfessor/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> porProfessor(@PathVariable Integer id) {
		ResponseCliente c = new ResponseCliente();

		//c.setResultado(alunos);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}
