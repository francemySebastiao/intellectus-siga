package ao.co.intellectus.controller.notificacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.DestinatarioCampanhaView;
import ao.co.intellectus.model.alerta.Campanha;
import ao.co.intellectus.model.alerta.DestinatarioCampanha;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.notificacoes.CampanhaService;
import ao.co.intellectus.servico.notificacoes.DestinatarioCampanhaService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/destinatarioCampanha")
public class ControllerDestinatarioCampanha {

	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private CampanhaService campanhaService;
	@Autowired
	private DestinatarioCampanhaService destinatarioCampanhaService;

	@PostMapping(path = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody DestinatarioCampanhaView destinatarioCampanhaView) {
		Campanha campanha = this.campanhaService.campanha(destinatarioCampanhaView.getCampanha());
		DestinatarioCampanha destinatarioCampanha = new DestinatarioCampanha();
		destinatarioCampanha.setDestinatario(destinatarioCampanhaView.getDestinatario());
		destinatarioCampanha.setCampanha(campanha);
		destinatarioCampanha.setEnviada(false);
		this.destinatarioCampanhaService.salvar(destinatarioCampanha);
		return this.httpResponse.MensagemDeRetorno(0, "Destinario salvo com sucesso.");
	}
	

}
