package ao.co.intellectus.controller.exame_electronico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.exame_electronico.RepostaExameEletronicoView;
import ao.co.intellectus.model.exame_eletronico.Resposta;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import ao.co.intellectus.servico.exema_electronico.RespostaExameElectronicoService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;


@RestController
@RequestMapping("/repostaExameElectronico")
public class ControllerReposta {
	
	@Autowired
	private RespostaExameElectronicoService respostaExameService;
	@Autowired
	private HttpResponse httpResponse;
	
	
	@GetMapping(value = "/todas", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todas(){
		return this.httpResponse.MensagemDeRetorno(0, this.respostaExameService.todas());
	}
	
	@GetMapping(value = "/pesquisarPorPergunta/{id}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pesquisarPorPergunta(@PathVariable Integer id){
		List<Resposta> respostas = this.respostaExameService.pesquisarPorPergunta(id);
		return this.httpResponse.MensagemDeRetorno(0, respostas);
	}
	
	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody RepostaExameEletronicoView repostaExameEletronicoView){
		this.respostaExameService.salvar(repostaExameEletronicoView);
		return this.httpResponse.MensagemDeRetorno(0, "Resposta salva com sucesso.");
	}
	
	@PutMapping(value = "/actualizar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody RepostaExameEletronicoView repostaExameEletronicoView){
		if(this.respostaExameService.validarPergunta(repostaExameEletronicoView.getId()))
			this.respostaExameService.salvar(repostaExameEletronicoView);
		else
			throw new RegistoNaoEncontradoException("Não existe registo da reposta introduzida.");
		return this.httpResponse.MensagemDeRetorno(0, "Pergunta actualizada com sucesso.");
	}

}
