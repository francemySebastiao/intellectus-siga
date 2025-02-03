package ao.co.intellectus.servico.exema_electronico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.exame_electronico.RepostaExameEletronicoView;
import ao.co.intellectus.model.exame_eletronico.Pergunta;
import ao.co.intellectus.model.exame_eletronico.Resposta;
import ao.co.intellectus.repository.exame_electronico.RepostaRepository;
import ao.co.intellectus.servico.exception.DadoInvalidoException;
import ao.co.intellectus.servico.exception.ExameElectronicoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class RespostaExameElectronicoService {

	@Autowired
	private RepostaRepository repostaExameElectronicoRepository;
	@Autowired
	private PerguntaExamElectronicoService perguntaExamElectronicoService;

	public List<Resposta> todas() {
		List<Resposta> todas = this.repostaExameElectronicoRepository.findAll();
		if (todas.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de repostas não encontradas.");
		return todas;
	}

	public List<Resposta> pesquisarPorPergunta(Integer id) {
		Pergunta pergunta = this.perguntaExamElectronicoService.pergunta(id);
		List<Resposta> respostas = this.repostaExameElectronicoRepository.findByPergunta(pergunta);
		if (respostas.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum registo de perguntas encontrado.");
		return respostas;
	}
	
	public 	List<RepostaExameEletronicoView> validarRespostas(List<RepostaExameEletronicoView> respostas){
		if(respostas == null || respostas.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de respostas não encotradas.");
		else if(respostas.size() != 4) 
			throw new DadoInvalidoException("A pergunta de ter 4 respostas obrigatoriamente.");	
		else
			return respostas;		
	}

	public void salvar(RepostaExameEletronicoView respostaExameEletronicoView) {
		Resposta resposta = new Resposta();
		if (respostaExameEletronicoView.getId() != null)
			resposta.setId(respostaExameEletronicoView.getId());
		resposta.setDescricao(respostaExameEletronicoView.getDescricao());
		resposta.setCorreta(respostaExameEletronicoView.getCorreta());
		Pergunta pergunta = this.perguntaExamElectronicoService.pergunta(respostaExameEletronicoView.getPergunta());
		resposta.setPergunta(pergunta);
		this.repostaExameElectronicoRepository.save(resposta);
	}
	
	public void salvar(Pergunta pergunta, List<RepostaExameEletronicoView> todasRespostasDoExame) {
		for (RepostaExameEletronicoView umaDasRespostasDoExame : todasRespostasDoExame) {
			Resposta resposta = new Resposta();
			resposta.setDescricao(umaDasRespostasDoExame.getDescricao());
			resposta.setCorreta(umaDasRespostasDoExame.getCorreta());
			resposta.setPergunta(pergunta);
			this.repostaExameElectronicoRepository.save(resposta);
		}
	}

	public boolean validarPergunta(Integer id) {
		if(id == null)
			throw new RegistoNaoEncontradoException("Erro ao validar repostas.");
		return this.repostaExameElectronicoRepository.exists(id);
	}
	
	public Resposta validarResposta(Integer id) {
		Resposta resposta = this.repostaExameElectronicoRepository.getOne(id);
		if(resposta == null)
			throw new ExameElectronicoException("Erro ao validar a resposta.");
		return resposta;
	}

}
