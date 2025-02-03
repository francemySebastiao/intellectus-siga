package ao.co.intellectus.controller.notificacoes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.alerta.CampanhaPersonalizada;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.alerta.CampanhaPersonalizadaRepository;
//import ao.co.intellectus.servico.mimo.MensagemService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

//campanha-personalizada/enviar
@RestController
@RequestMapping("/campanha-personalizada")
public class ControllerCampanhaPersonalizada {
	@Autowired
	private HttpResponse httpResponse;
	/*@Autowired
	private MensagemService mensagemService;*/
	@Autowired
	private CampanhaPersonalizadaRepository campanhaPersonalizadaRepository;
	
	
	@RequestMapping(value = "/enviar", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> enviarMensagem(){
		
		List<CampanhaPersonalizada> campanha = this.campanhaPersonalizadaRepository.findByEnviado(false);
		for (CampanhaPersonalizada o : campanha) {
		      
			try {
				//this.mensagemService.enviarSMS(o.getDestinatario(), o.getMensagem());
				
				o.setData(new Date());
				o.setEnviado(true);
				this.campanhaPersonalizadaRepository.save(o);				
			}catch(Exception ex) {
				
			}
		}
		
		return this.httpResponse.MensagemDeRetorno(0, "Campanha enivada com sucesso.");
	}
	

	@RequestMapping(value = "/enviar2", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> enviar(){
		
		List<CampanhaPersonalizada> campanha = this.campanhaPersonalizadaRepository.findByEnviado(false);
		for (CampanhaPersonalizada o : campanha) {
		      
			try {
				@SuppressWarnings("unused")
				String mensagem="Caro(a) estudante, aulas on-line. no site entrar em sala informe: Email- " +o.getMensagem()+", Senha- "+o.getEmail()+". Para mais inf. no chat do site. IGS.";
				
			
				
				//Caro(a) estudante, acesso para aulas online: site: www.igs.ed.ao, Email: es180013@aluno.igs.ed.ao, Senha: angolaunaso13 - IGS.
				
				//this.mensagemService.enviarSMS(o.getDestinatario(), mensagem);
				
				o.setData(new Date());
				o.setEnviado(true);
				this.campanhaPersonalizadaRepository.save(o);			 	
			}catch(Exception ex) {
			
			}
		}
		
		return this.httpResponse.MensagemDeRetorno(0, "Campanha enivada com sucesso.");
	}
}
