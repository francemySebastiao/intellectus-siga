package ao.co.intellectus.controller.Auditoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.Auditoria.AvaliacoesAuditService;
import ao.co.intellectus.servico.Auditoria.EquivalenciaAuditService;
import ao.co.intellectus.servico.Auditoria.FichaAcademicaAuditService;
import ao.co.intellectus.servico.Auditoria.InscricaoExtraordinariAuditService;
import ao.co.intellectus.servico.Auditoria.MatriculaAuditService;



@RestController
@RequestMapping("/auditoria")
public class ControllerAlunoAuditoria {
	
	@Autowired
	private FichaAcademicaAuditService fService;
	@Autowired
	private EquivalenciaAuditService eService;
	@Autowired
	private InscricaoExtraordinariAuditService iService;
	@Autowired
	private MatriculaAuditService mService;
	
	@Autowired
	private AvaliacoesAuditService aService;
	
	

	@RequestMapping(value = "/avaliacoes", method = RequestMethod.GET, produces = "application/json")
	@Cacheable(value = "listaDeAuditoriaFichacademica")
	@CrossOrigin("*")
	public ResponseEntity<ResponseCliente> avaliacao(@RequestParam String numero) {
		return aService.buscarAvaliacoesPorNumero(numero); 
	}
	    
	

	@RequestMapping(value = "/Equivalencia", method = RequestMethod.GET, produces = "application/json")
	@Cacheable(value = "listaDeAuditoriaEquivalencia")
	@CrossOrigin("*")
	public ResponseEntity<ResponseCliente> buscarEquivalenciasPorNumero(@RequestParam String numero) {
		return eService.buscarEquivalenciasPorNumero(numero); 
	}
	
	@RequestMapping(value = "/matricula", method = RequestMethod.GET, produces = "application/json")
	@Cacheable(value = "listaDeAuditoriaMatricula")
	@CrossOrigin("*")
	public ResponseEntity<ResponseCliente> buscarMatriculasPorNumero(@RequestParam String numero) {
		return mService.buscarMatriculasPorNumero(numero); 
	}
	
	
	@RequestMapping(value = "/InscricaoExtraordinaria ", method = RequestMethod.GET, produces = "application/json")
	@Cacheable(value = "listaDeAuditoriaInscrição Extraordinaria")
	@CrossOrigin("*")
	public ResponseEntity<ResponseCliente> buscarInscricoesPorNumero(@RequestParam String numero) {
		return iService.buscarInscricoesPorNumero(numero);
	}
	@RequestMapping(value = "/fichaAcademica", method = RequestMethod.GET, produces = "application/json")
	@Cacheable(value = "listaDeAuditoriaFichacademica")
	@CrossOrigin("*")
	public ResponseEntity<ResponseCliente> fichaAcademica(@RequestParam String numero) {
		return fService.buscarFichaPorNumero(numero); 
	}
		
	
	
	    
	
	
	
	
	

}
