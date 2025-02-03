package ao.co.intellectus.controller.exame_electronico;
/*
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

import ao.co.intellectus.model.exame_eletronico.Pergunta;
import ao.co.intellectus.model.exame_eletronico.Resposta;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.exame_electronico.PerguntaRepository;
import ao.co.intellectus.repository.exame_electronico.RepostaRepository;
import ao.co.intellectus.servico.exception.DadoInvalidoException;
import ao.co.intellectus.servico.exception.DadosDuplicadoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import ao.co.intellectus.servico.exema_electronico.PerguntaExamElectronicoService;
import ao.co.intellectus.servico.exema_electronico.RespostaExameElectronicoService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import ao.co.intellectus.viewModel.exame_electronico.PerguntaCadastrada;
import ao.co.intellectus.viewModel.exame_electronico.PerguntaExameEletronicoView;
import ao.co.intellectus.viewModel.exame_electronico.PerguntasBusca;
import ao.co.intellectus.viewModel.exame_electronico.RepostaDetalhe;
import ao.co.intellectus.viewModel.exame_electronico.RepostaExameEletronicoView;

@RestController
@RequestMapping("/perguntaExameElectronico")
public class ControllerPergunta2 {

	@Autowired
	private PerguntaExamElectronicoService perguntas;
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private RespostaExameElectronicoService respostas;
	@Autowired
	private PerguntaRepository perguntaRepository;
	@Autowired
	private RepostaRepository repostaRepository;

	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody PerguntaExameEletronicoView exame) {
		List<Pergunta> perguntas = this.perguntas.pergunta(exame.getDescricao(), exame.getTipoExame());
		if (perguntas.isEmpty()) {
			List<RepostaExameEletronicoView> todasRespostasDoExame = exame.getResposta();
			if (todasRespostasDoExame.size() != 4 || todasRespostasDoExame.isEmpty() || todasRespostasDoExame == null)
				throw new DadoInvalidoException("A pergunta deve ter 4 repostas.");
			Pergunta pergunta = this.perguntas.salvar(exame);
			this.respostas.salvar(pergunta, todasRespostasDoExame);
			return this.httpResponse.MensagemDeRetorno(0, "Pergunta salva com sucesso.");
		} else {
			throw new DadosDuplicadoException("Esta pergunta já existe.");
		}
	}

	@PutMapping(value = "/actualizar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(
			@RequestBody PerguntaExameEletronicoView perguntaexameEletronico) {
		if (perguntas.validarPergunta(perguntaexameEletronico.getId()))
			this.perguntas.salvar(perguntaexameEletronico);
		else
			throw new RegistoNaoEncontradoException("Não existe registo da pergunta introduzida.");
		return this.httpResponse.MensagemDeRetorno(0, "Pergunta actualizada com sucesso.");
	}

	// perguntaExameElectronico/perguntasCadastradas

	@PostMapping(value = "/perguntasCadastradas", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> perguntasCadastradas(@RequestBody PerguntasBusca exame) {

		List<PerguntaCadastrada> prsCadastrada = new ArrayList<PerguntaCadastrada>();

		PerguntaCadastrada prCadastrada;
		List<Pergunta> perguntas = this.perguntaRepository.findByTipoExameAndCursoIdAndAnoLectivoId(
				exame.getTipoExame(), exame.getCodigoCurso(), exame.getAnoLectivo());
		for (Pergunta o : perguntas) {
			prCadastrada = new PerguntaCadastrada();

			prCadastrada.setAnoLectivo(o.getAnoLectivo().getAnoLectivo());
			prCadastrada.setClassificacao(o.getClassificacao());
			prCadastrada.setCurso(o.getCurso().getDescricao());
			prCadastrada.setDescricao(o.getDescricao());
			prCadastrada.setId(o.getId());
			prCadastrada.setTipoExame(o.getTipoExame());

			prsCadastrada.add(prCadastrada);
		}
		return this.httpResponse.MensagemDeRetorno(0, prsCadastrada);
	}
	
	@RequestMapping(value = "/pesquisarPorPergunta/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pesquisarPorPergunta(@PathVariable Integer id) {
		Pergunta pergunta = this.perguntaRepository.findOne(id);
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
*/
