package ao.co.intellectus.controller;

import java.util.ArrayList;
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

import ao.co.intellectus.DTO.SumarioCliente;
import ao.co.intellectus.model.Sumario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.SumarioRepository;

@RestController
@RequestMapping("/sumario")
public class ControllerSumario {

	@Autowired
	private SumarioRepository repositorySumario;
	//@Autowired
	//private TurmaDisponivelRepository repositoryTurma;
	//@Autowired
	//private CursoRepository repositoryCurso;
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody SumarioCliente sumarioCliente) {
		
		ResponseCliente c=new ResponseCliente();
		//this.repositoryTurma.findOne(sumarioCliente.getTurma());
		
		Sumario sumario=new Sumario();
		BeanUtils.copyProperties(sumarioCliente, sumario, "turma","disciplina");
		this.repositorySumario.save(sumario);
		c.setResultado(sumarioCliente);
		c.setMensagem("Sumário Cadastrado com sucesso!");
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() { 
		
		ResponseCliente c=new ResponseCliente();
		
		Iterable<Sumario> todos = repositorySumario.findAll();
		List<Sumario> sumarios=new ArrayList<Sumario>();
		Sumario novoSumario;
		for (Sumario sumario : todos) {
			novoSumario=new Sumario();
			BeanUtils.copyProperties(sumario, novoSumario, "turma","disciplina");
			sumarios.add(novoSumario);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(sumarios);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}