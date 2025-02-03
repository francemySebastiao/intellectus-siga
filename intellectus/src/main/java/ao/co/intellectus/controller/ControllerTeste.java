package ao.co.intellectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.EmailCliente;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.cafold.EmailService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/teste")
public class ControllerTeste {
	
	@Autowired
	private HttpResponse notificacaoService;
	@Autowired
	private EmailService emailService;
	
	@GetMapping(value = "/email", produces = "application/json")
	public ResponseEntity<ResponseCliente> email(){
		Candidato candidato = new Candidato();
		//candidato.setExame("MATEMATICA_PORTUGUES");
		candidato.setExame("PORTUGUES_HISTORIA");
		EmailCliente emailCliente = new EmailCliente();
		String[] destinatario = {"francisco.lourenco@intellectus.co.ao"};
		emailCliente.setAssunto("Teste de Email");
		emailCliente.setMensagem("Em anexo está tópico do exame: ");
		emailCliente.setDestinatario(destinatario);
		this.emailService.enviar(candidato, emailCliente);
		return this.notificacaoService.MensagemDeRetorno(0, "Email enviado com sucesso.");
	}

}
