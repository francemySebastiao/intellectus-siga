package ao.co.intellectus.controller.exame_electronico;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.exame_electronico.PerguntaCadastrada;
import ao.co.intellectus.DTO.exame_electronico.PerguntaExameEletronicoView;
import ao.co.intellectus.DTO.exame_electronico.PerguntasBusca;
import ao.co.intellectus.DTO.exame_electronico.RepostaDetalhe;
import ao.co.intellectus.DTO.exame_electronico.RepostaExameEletronicoView;
import ao.co.intellectus.model.exame_eletronico.ExameCurso;
import ao.co.intellectus.model.exame_eletronico.Pergunta;
import ao.co.intellectus.model.exame_eletronico.Resposta;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.exame_electronico.PerguntaRepository;
import ao.co.intellectus.repository.exame_electronico.RepostaRepository;
import ao.co.intellectus.servico.exception.DadosDuplicadoException;
import ao.co.intellectus.servico.exception.ExameElectronicoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import ao.co.intellectus.servico.exema_electronico.ExameEletronicoService;
import ao.co.intellectus.servico.exema_electronico.PerguntaExamElectronicoService;
import ao.co.intellectus.servico.exema_electronico.RespostaExameElectronicoService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/perguntaExameElectronico")
public class ControllerPergunta {

	@Autowired
	private PerguntaExamElectronicoService perguntas;
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private PerguntaRepository perguntaRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private RepostaRepository repostaExameElectronicoRepository;
	@Autowired
	private RespostaExameElectronicoService respostasService;
	@Autowired
	private ExameEletronicoService exameElectronicoService;
	@Autowired
	private RepostaRepository repostaRepository;

	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody PerguntaExameEletronicoView exame) {
		List<Pergunta> perguntas = this.perguntas.pergunta(exame.getDescricao(), exame.getTipoExame());
		if (perguntas.isEmpty()) {
			List<RepostaExameEletronicoView> todasRespostasDoExame = this.respostasService.validarRespostas(exame.getResposta());
			List<ExameCurso> examesCurso = 	this.exameElectronicoService.validarTipoDeExame(exame.getTipoExame());
			for (ExameCurso exameCurso : examesCurso) {
				Pergunta pergunta = new Pergunta();
				pergunta.setId(exame.getId());
				String examee=exame.getTipoExame().getDescricao();
				System.out.println("a descrição é: "+examee);
				switch (examee) {
				case "Matemática":pergunta.setClassificacao(2);
					break;
				case "Matemática Arquitetura":pergunta.setClassificacao(2);	
					break;
				default:pergunta.setClassificacao(1);
					break;
				}
				
				//pergunta.setClassificacao((exame.getTipoExame() == TipoExame.MATEMATICA ? 2 : 1));
				pergunta.setDescricao(exame.getDescricao());
				pergunta.setTipoExame(exame.getTipoExame());
				pergunta.setAnoLectivo(this.anoLectivoRepository.findOne(exame.getAnoLectivo()));
				pergunta.setCurso(exameCurso.getCurso());
				pergunta.setEstado(true);
				pergunta = this.perguntaRepository.save(pergunta);
				for (RepostaExameEletronicoView umaDasRespostasDoExame : todasRespostasDoExame) {
					Resposta resposta = new Resposta();
					resposta.setDescricao(umaDasRespostasDoExame.getDescricao());
					resposta.setCorreta(umaDasRespostasDoExame.getCorreta());
					resposta. setPergunta(pergunta);
					this.repostaExameElectronicoRepository.save(resposta);
				}
			}
			return this.httpResponse.MensagemDeRetorno(0, "Pergunta salva com sucesso.");
		} 
		throw new DadosDuplicadoException("Esta pergunta já existe.");
	}

	@PutMapping(value = "/actualizar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody PerguntaExameEletronicoView perguntaexameEletronico) {
		if (perguntas.validarPergunta(perguntaexameEletronico.getId())) {
			Pergunta perguntas = this.perguntaRepository.findById(perguntaexameEletronico.getId());
			perguntas.setDescricao(perguntaexameEletronico.getDescricao());
			perguntas.setEstado(true);
			perguntas = this.perguntaRepository.save(perguntas);
		}else
			throw new RegistoNaoEncontradoException("Não existe registo da pergunta introduzida.");
		return this.httpResponse.MensagemDeRetorno(0, "Pergunta actualizada com sucesso.");
	}
	@PutMapping(value = "/remover", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> Remover(@RequestBody PerguntaExameEletronicoView perguntaexameEletronico ) {
		Pergunta perguntas = this.perguntaRepository.findById(perguntaexameEletronico.getId());
				perguntas.setEstado(false);
				perguntas = this.perguntaRepository.save(perguntas);
			return this.httpResponse.MensagemDeRetorno(0, "Pergunta removida com sucesso.");	
	}
	/*@PutMapping(value = "/removerPorDescricao", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> Remover2(@RequestBody PerguntaExameEletronicoView perguntaexameEletronico ) {
		List<ExameCurso> examesCurso = 	this.exameElectronicoService.validarTipoDeExame(perguntaexameEletronico.getTipoExame());
		for (ExameCurso exameCurso : examesCurso) {
		Pergunta perguntas = this.perguntaRepository.findByDescricao(perguntaexameEletronico.getDescricao());
				perguntas.setEstado(false);
				perguntas = this.perguntaRepository.save(perguntas);
		}
			return this.httpResponse.MensagemDeRetorno(0, "Pergunta removida com sucesso.");	
	}*/
	//perguntaExameElectronico/perguntasCadastradas
	
	@PostMapping(value = "/perguntasCadastradas", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> perguntasCadastradas(@RequestBody PerguntasBusca exame) {
		List<PerguntaCadastrada> prsCadastrada=new ArrayList<PerguntaCadastrada>();
		exame.setEstado(true);
		List<Pergunta> perguntas = this.perguntaRepository.findByTipoExameAndCursoIdAndAnoLectivoIdAndEstado(exame.getTipoExame(),exame.getCodigoCurso(),exame.getAnoLectivo(),exame.getEstado());
		if(perguntas.isEmpty())
			throw new ExameElectronicoException("Nenhum registo de pergunta encontrado.");
		for (Pergunta o : perguntas) {
			PerguntaCadastrada prCadastrada=new PerguntaCadastrada();
			prCadastrada.setAnoLectivo(o.getAnoLectivo().getAnoLectivo());
			prCadastrada.setClassificacao(o.getClassificacao());
			prCadastrada.setCurso(o.getCurso().getDescricao());
			prCadastrada.setDescricao(o.getDescricao());
			prCadastrada.setId(o.getId());
			prCadastrada.setTipoExame(o.getTipoExame());
			prsCadastrada.add(prCadastrada);
		}
		return this.httpResponse.MensagemDeRetorno(0,prsCadastrada);
	}
	
	@RequestMapping(value = "/pesquisarPorPergunta/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pesquisarPorPergunta(@PathVariable Integer id) {
		Pergunta pergunta = this.perguntaRepository.findById(id);
		List<Resposta> reposta = this.repostaRepository.findByPergunta(pergunta);
		RepostaDetalhe repostaDetalhe;
		List<RepostaDetalhe> repostasDetalhe = new ArrayList<RepostaDetalhe>();
		for (Resposta o : reposta) {
			repostaDetalhe = new RepostaDetalhe();
			repostaDetalhe.setCerta(o.getCorreta());
			repostaDetalhe.setReposta(o.getDescricao());
			repostasDetalhe.add(repostaDetalhe);
		}
		return this.httpResponse.MensagemDeRetorno(0, repostasDetalhe);
	}
}
