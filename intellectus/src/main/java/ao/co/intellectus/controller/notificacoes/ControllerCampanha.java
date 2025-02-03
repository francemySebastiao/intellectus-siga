package ao.co.intellectus.controller.notificacoes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AlvoParaEnviarMensagem;
import ao.co.intellectus.DTO.DestinarioReceberCampanhaView;
import ao.co.intellectus.DTO.PublicoAlvo;
import ao.co.intellectus.DTO.candidatura.MensagemRapidaParaCandidato;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.alerta.Campanha;
import ao.co.intellectus.model.alerta.DestinatarioCampanha;
import ao.co.intellectus.model.enumeracao.TipoPublicoAlvo;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.cafold.CandidatoServie;
import ao.co.intellectus.servico.cafold.MatriculasService;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
//import ao.co.intellectus.servico.mimo.MensagemService;
import ao.co.intellectus.servico.notificacoes.CampanhaService;
import ao.co.intellectus.servico.notificacoes.DestinatarioCampanhaService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/campanha")
public class ControllerCampanha {

	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private CampanhaService campanhaService;
	@Autowired
	private DestinatarioCampanhaService destinatarioCampanhaService;
	/*@Autowired
	private MensagemService mensagemService;*/
	@Autowired
	private CandidatoServie candidatoServie;
	@Autowired
	private MatriculasService matriculaService;
	
	
	@PostMapping(path = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody Campanha campanha){
		campanha.setDataRegisto(new Date());
		this.campanhaService.salvar(campanha);
		return this.httpResponse.MensagemDeRetorno(0, "Campanha salva com sucesso.");
	}
	
	@PostMapping(path = "/enviarMensagem", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> enviarMensagem(@RequestBody DestinarioReceberCampanhaView destinarioReceberCampanhaView){
		
		@SuppressWarnings("unused")
		Campanha campanha= this.campanhaService.campanha(destinarioReceberCampanhaView.getCampanha());
		
		for(DestinatarioCampanha destinatario: this.destinatarioCampanhaService.destinatariosCampanha(destinarioReceberCampanhaView.getDestinarios())) {
			destinatario.setEnviada(true);
			this.destinatarioCampanhaService.salvar(destinatario);
			//this.mensagemService.enviarSMS(destinatario.getDestinatario(), campanha.getMensagem());
		}
		return this.httpResponse.MensagemDeRetorno(0, "Campanha enivada com sucesso.");
	}
	

	@PostMapping("/publicoAlvo")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public  ResponseEntity<ResponseCliente> candidatosParaEnviarMensagem(@RequestBody PublicoAlvo publicoAlvo){
		List<AlvoParaEnviarMensagem> todos = new ArrayList<>();
		if(publicoAlvo.getTipoEnvio() == TipoPublicoAlvo.ALUNO) {
			for(Aluno aluno: this.matriculaService.alunos(publicoAlvo)) {
				AlvoParaEnviarMensagem candidatoParaEnviarMensagem = new AlvoParaEnviarMensagem();
				candidatoParaEnviarMensagem.setId(aluno.getId());
				candidatoParaEnviarMensagem.setNumero(aluno.getNumeroDeAluno());
				candidatoParaEnviarMensagem.setSelecionado(true);
				candidatoParaEnviarMensagem.setNome(aluno.getNome());
				candidatoParaEnviarMensagem.setTelefone(aluno.getTelefone());
				todos.add(candidatoParaEnviarMensagem);
			}
			return httpResponse.MensagemDeRetorno(0, todos);
		}else if(publicoAlvo.getTipoEnvio() == TipoPublicoAlvo.CANDIDATO) {			
			for(Candidato candidato: this.candidatoServie.candidatosPorData(publicoAlvo)) {
				AlvoParaEnviarMensagem candidatoParaEnviarMensagem = new AlvoParaEnviarMensagem();
				candidatoParaEnviarMensagem.setId(candidato.getId());
				candidatoParaEnviarMensagem.setNumero(candidato.getNumeroCandidato().toString());
				candidatoParaEnviarMensagem.setSelecionado(true);
				candidatoParaEnviarMensagem.setNome(candidato.getNome());
				candidatoParaEnviarMensagem.setTelefone(candidato.getTelefone());
				todos.add(candidatoParaEnviarMensagem);
			}
			return httpResponse.MensagemDeRetorno(0, todos);
		}else {
			throw new RegistoNaoEncontradoException("Seleccione um público alvo válido.");
		}
	}
	                      
	@PostMapping(path = "/mensagemRapidaParaCandidatos", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> mensagemRapidaParaCandidatos(@RequestBody MensagemRapidaParaCandidato porEnviar){
		try {			
			if(porEnviar.getCorpoMensagem().isEmpty() || porEnviar.getCorpoMensagem() == null) {
				throw new RegistoNaoEncontradoException("Introduza a mensagem por enviar");
			}else if(porEnviar.getListaTelefonica().isEmpty()) {
				throw new RegistoNaoEncontradoException("Nenhum candidato seleccionado");
			}else {
				for(AlvoParaEnviarMensagem alvo: porEnviar.getListaTelefonica()) {
					if(alvo.getSelecionado()) {
						
						if(alvo.getTelefone() == null)
							throw new RegistoNaoEncontradoException("Erro ao validar um dos número do " +alvo.getId());
						//this.mensagemService.enviarSMS(alvo.getTelefone(),porEnviar.getCorpoMensagem());	
					}
				}
				return this.httpResponse.MensagemDeRetorno(0, "Mensagem enivada com sucesso.");
			}
		}catch(Exception erro) {
			return this.httpResponse.MensagemDeRetorno(2, erro.getMessage());
		}
	}
	
	
}
