package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.List;

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

import ao.co.intellectus.DTO.EmolumentoCursoCliente;
import ao.co.intellectus.DTO.EmolumentoHistoricoCliente;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.MoedaRepository;

@RestController
@RequestMapping("/historicoEmolumento")
public class ControllerHistoricoEmolumento {
	
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarTodos() {
		ResponseCliente c=new ResponseCliente();
		
		Iterable<EmolumentoHistorico> emolumento = this.emolumentoHistoricoRepository.findAll();
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(emolumento);	
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscarPorCodigo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCodigoCursoEAno(@RequestBody  EmolumentoHistoricoCliente emolumentoHistorico) {
		ResponseCliente c=new ResponseCliente(); 
				
		Emolumento emolumento = emolumentoHistorico.getEmolumento()!=null ? this.emolumentoRepository.findByCodigo(emolumentoHistorico.getEmolumento()):null;		
		Curso curso           = emolumentoHistorico.getCurso()     !=null ? this.cursoRepository.findByIdAndStatus(emolumentoHistorico.getCurso(),true):null;
		AnoLectivo anoLectivo = emolumentoHistorico.getAnoLectivo()!=null ? this.anoLectivoRepository.findOne(emolumentoHistorico.getAnoLectivo()):null;
		
		EmolumentoHistorico pagamento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento, curso, anoLectivo);
 
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(pagamento);	
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/buscarValores", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> valores(@RequestBody  EmolumentoHistoricoCliente emolumentoHistorico) {
		ResponseCliente c=new ResponseCliente();		
		Curso curso           = emolumentoHistorico.getCurso()     !=null ? this.cursoRepository.findByIdAndStatus(emolumentoHistorico.getCurso(),true):null;
		AnoLectivo anoLectivo = emolumentoHistorico.getAnoLectivo()!=null ? this.anoLectivoRepository.findOne(emolumentoHistorico.getAnoLectivo()):null;
		List<EmolumentoHistorico> emolumentos;
		
		if(curso==null) {
			emolumentos = this.emolumentoHistoricoRepository.findByAnoLectivo(anoLectivo);
		}else {			
			emolumentos = this.emolumentoHistoricoRepository.findByCursoAndAnoLectivo(curso, anoLectivo);
		}
		
		EmolumentoCursoCliente emolumento;
		List<EmolumentoCursoCliente> emolumentoExistentes=new ArrayList<EmolumentoCursoCliente>();
		
		for (EmolumentoHistorico eHistorico : emolumentos) {
			emolumento=new EmolumentoCursoCliente();
			
			emolumento.setId(eHistorico.getId());
			emolumento.setCodigoMoeda(eHistorico.getMoeda().getId());
			emolumento.setCurso(eHistorico.getCurso().getPlano());
			emolumento.setAnoLectivo(eHistorico.getAnoLectivo().getAnoLectivo());
			emolumento.setMoeda(eHistorico.getMoeda().getDesignacao());
			emolumento.setValor(eHistorico.getValor());
			emolumento.setEmolumento(eHistorico.getEmolumento().getEmolumento());
			
			emolumentoExistentes.add(emolumento);
		} 
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(emolumentoExistentes);	
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/buscarValor/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> valor(@PathVariable Integer id) {
		ResponseCliente c=new ResponseCliente();	
		
		EmolumentoHistorico emolumento = this.emolumentoHistoricoRepository.findOne(id);
		EmolumentoCursoCliente rEmolumento=new EmolumentoCursoCliente();
		
		rEmolumento.setId(emolumento.getId());
		rEmolumento.setAnoLectivo(emolumento.getAnoLectivo().getAnoLectivo());
		rEmolumento.setCodigoMoeda(emolumento.getMoeda().getId());
		rEmolumento.setCurso(emolumento.getCurso().getDescricao());
		rEmolumento.setEmolumento(emolumento.getEmolumento().getEmolumento());
		rEmolumento.setValor(emolumento.getValor());
		rEmolumento.setMoeda(emolumento.getMoeda().getDesignacao());
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(rEmolumento);	
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody EmolumentoCursoCliente hEmolumento) {
		ResponseCliente c=new ResponseCliente();	
		
		EmolumentoHistorico emolumento = this.emolumentoHistoricoRepository.findOne(hEmolumento.getId());
		
		Moeda moeda = this.moedaRepository.findOne(hEmolumento.getCodigoMoeda());
	
		emolumento.setId(emolumento.getId());
		emolumento.setAnoLectivo(emolumento.getAnoLectivo());
		emolumento.setCurso(emolumento.getCurso());
		emolumento.setEmolumento(emolumento.getEmolumento());
		emolumento.setValor(hEmolumento.getValor());
		emolumento.setMoeda(moeda);
		
		this.emolumentoHistoricoRepository.save(emolumento);
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(emolumento);	
		c.setMensagem("Emolumento atualizado com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}	
}