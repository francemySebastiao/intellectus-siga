package ao.co.intellectus.controller.configuracaoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.CalendarioAcademicoView;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.cafold.CalendarioAcademicoService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/calendarioAcademico")
public class ControllerCalendarioAcademico {
	
	@Autowired
	private CalendarioAcademicoService calendarioAcademicoSerivce;
	@Autowired
	private HttpResponse notificacaoService;
	
	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody CalendarioAcademicoView calendarioAcademicoView) {
		this.calendarioAcademicoSerivce.salvar(calendarioAcademicoView);
		return this.notificacaoService.MensagemDeRetorno(0, "Calendário Académico salvo com sucesso.");
	}
	
	@PutMapping(value = "/actualizar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody CalendarioAcademicoView calendarioAcademicoView) {
		this.calendarioAcademicoSerivce.salvar(calendarioAcademicoView);
		return this.notificacaoService.MensagemDeRetorno(0, "Calendário Académico actualizado com sucesso.");
	}

}
