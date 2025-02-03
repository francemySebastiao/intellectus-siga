package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.HistoricoEquivalenciaCliente;
import ao.co.intellectus.model.Equivalencia;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.EquivalenciaRepository;

@RestController
@RequestMapping("/equivalenciaHistorico")
public class ControllerHistoricoEquivalencia {
	@Autowired
	private EquivalenciaHistoricoRepository equivalenciaHistorico;
	@Autowired
	private EquivalenciaRepository equivalenciaRepository;
	
	//HistoricoEquivalenciaCliente
	
	@RequestMapping(value = "/buscarHistorico/{equivalenciaP}", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> equivalencias(@PathVariable Integer equivalenciaP) {
		ResponseCliente c = new ResponseCliente();
	   
		Equivalencia equivalencia                 = this.equivalenciaRepository.findOne(equivalenciaP);
		List<HistoricoEquivalencia> equivalencias = this.equivalenciaHistorico.findByEquivalencia(equivalencia);
		
		
		HistoricoEquivalenciaCliente historicioEq;
		List<HistoricoEquivalenciaCliente> historicioEqs=new ArrayList<HistoricoEquivalenciaCliente>();
		for (HistoricoEquivalencia eqs : equivalencias) {
			historicioEq=new HistoricoEquivalenciaCliente();
			
            //configurar os valores do objecto historico equivalencias.
			historicioEq.setIdEquivalencia(eqs.getEquivalencia().getId());
			historicioEq.setId(eqs.getId());
			historicioEq.setDisciplinaOrigem(eqs.getDisciplinaOrigem());
			historicioEq.setDisciplinaDestino(eqs.getDisciplinaDestino().getDescricao());
			historicioEq.setNotaOrigem(eqs.getNotaOrigem());
			
			//primeira validação.
			historicioEq.setPrimeiraValidacao(eqs.isPrimeiraValidacao());
			historicioEq.setDataPrimeiraValidacao(eqs.getDataPrimeiraValidacao());
			
			//segunda validação
			historicioEq.setSegundaValidacao(eqs.isSegundaValidacao());
			historicioEq.setDataSegundaValidacao(eqs.getDataSegundaValidacao());
			
			//terceira validação
			historicioEq.setTerceiraValidacao(eqs.isTerceiraValidacao());
			historicioEq.setDataTerceiraValidacao(eqs.getDataTerceiraValidacao());
			
			historicioEq.setAnoCurricular(eqs.getDisciplinaDestino().getAnoCorricular());
			historicioEq.setUsuarioEquivalencia(eqs.getEquivalencia().getUsuarioEquivalencia()==null ? null:eqs.getEquivalencia().getUsuarioEquivalencia().getName());
			historicioEqs.add(historicioEq);
		}
		
		
	    c.setResultado(historicioEqs);
        c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}
