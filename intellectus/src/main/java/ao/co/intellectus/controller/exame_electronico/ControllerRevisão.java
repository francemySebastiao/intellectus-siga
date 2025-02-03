package ao.co.intellectus.controller.exame_electronico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.exame_electronico.ExameRevisaoDTO;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.exame_eletronico.ExameEletronico;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.CandidatoRepository;
import ao.co.intellectus.repository.exame_electronico.ExameEletronicoRepository;
import ao.co.intellectus.servico.exception.ExameElectronicoException;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/exameRevisao")
public class ControllerRevisão {
	@Autowired
	private ExameEletronicoRepository exameEletronicoRepository;
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private HttpResponse httpResponse;
	
	@GetMapping(value = "/buscarPorNome", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorNome( @RequestParam String nome,@RequestParam Integer anoLectivo ) {
		List<ExameRevisaoDTO> exame1=new ArrayList<ExameRevisaoDTO>();
		List<ExameEletronico> exame = this.exameEletronicoRepository.findByCandidatoNomeAndAnoLectivoAnoLectivo(nome, anoLectivo);
		if(exame.isEmpty())
			throw new ExameElectronicoException("Candidato não realizou o exame.");
		for (ExameEletronico o : exame) {
			ExameRevisaoDTO exames=new ExameRevisaoDTO();
			exames.setNome(o.getCandidato().getNome());
			exames.setTipoExame(o.getTipoExame().getDescricao());
			exames.setNumeroDocumento(o.getCandidato().getNumeroDocumento());
			exames.setClassificacao(o.getClassificacao());
			exames.setPergunta(o.getPergunta().getDescricao());
			if(o.getResposta().getDescricao()==null) {
				exames.setResposta("Pergunta não respondida");
			}
			exames.setResposta(o.getResposta().getDescricao());
			
			exame1.add(exames);
		}
		return this.httpResponse.MensagemDeRetorno(0,exame1);
	}
	@GetMapping(value = "/buscarPorCodigo", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorNumeroCandidato( @RequestParam Integer numeroCandidato,@RequestParam Integer anoLectivo ) {
		List<ExameRevisaoDTO> exame1=new ArrayList<ExameRevisaoDTO>();
		List<ExameEletronico> exame = this.exameEletronicoRepository.findByCandidatoNumeroCandidatoAndAnoLectivoAnoLectivo(numeroCandidato, anoLectivo);
		if(exame.isEmpty())
			throw new ExameElectronicoException("Candidato não realizou o exame.");
		for (ExameEletronico o : exame) {
			ExameRevisaoDTO exames=new ExameRevisaoDTO();
			exames.setNome(o.getCandidato().getNome());
			exames.setTipoExame(o.getTipoExame().getDescricao());
			exames.setNumeroDocumento(o.getCandidato().getNumeroDocumento());
			exames.setClassificacao(o.getClassificacao());
			exames.setPergunta(o.getPergunta().getDescricao());
			if(o.getResposta().getDescricao()==null) {
				exames.setResposta("Pergunta não respondida");
			}
			exames.setResposta(o.getResposta().getDescricao());
			
			exame1.add(exames);
		}
		return this.httpResponse.MensagemDeRetorno(0,exame1);

	}

	@GetMapping(value = "/buscarPorNumeroDocumento", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorNumeroBilhete( @RequestParam String  numeroDocumento,@RequestParam Integer anoLectivo ) {
		Candidato candidato= this.candidatoRepository.findByNumeroDocumento(numeroDocumento);
		List<ExameRevisaoDTO> exame1=new ArrayList<ExameRevisaoDTO>();
		List<ExameEletronico> exame = this.exameEletronicoRepository.findByCandidatoNumeroCandidatoAndAnoLectivoAnoLectivo(candidato.getNumeroCandidato(), anoLectivo);
		if(exame.isEmpty())
			throw new ExameElectronicoException("Candidato não realizou o exame.");
		for (ExameEletronico o : exame) {
			ExameRevisaoDTO exames=new ExameRevisaoDTO();
			exames.setNome(o.getCandidato().getNome());
			exames.setTipoExame(o.getTipoExame().getDescricao());
			exames.setNumeroDocumento(o.getCandidato().getNumeroDocumento());
			if(o.getClassificacao()==null) {
				return this.httpResponse.MensagemDeRetorno(0, "Verifique a sua prova, existem perguntas não respondidas!");
			}
			exames.setClassificacao(o.getClassificacao());
			exames.setPergunta(o.getPergunta().getDescricao());
			if(o.getResposta().getDescricao()==null) {
				exames.setResposta("Pergunta não respondida");
			}
			exames.setResposta(o.getResposta().getDescricao());
			
			exame1.add(exames);
		}
		return this.httpResponse.MensagemDeRetorno(0,exame1);

	}
}
