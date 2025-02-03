package ao.co.intellectus.servico.exema_electronico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.exame_eletronico.ExameCurso;
import ao.co.intellectus.model.exame_eletronico.ExameEletronico;
import ao.co.intellectus.model.exame_eletronico.TipoExame;
import ao.co.intellectus.repository.exame_electronico.ExameCursoRepository;
import ao.co.intellectus.repository.exame_electronico.ExameEletronicoRepository;
import ao.co.intellectus.servico.exception.ExameElectronicoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class ExameEletronicoService {

	@Autowired
	private ExameEletronicoRepository exameEletronicoRepository;
	@Autowired
	private ExameCursoRepository exameCursoRepository;

	public ExameEletronico validarPergunta(Integer id) {
		ExameEletronico exameEletronico = this.exameEletronicoRepository.getOne(id);
		if (exameEletronico == null)
			throw new ExameElectronicoException("Erro ao validar a pergunta.");
		return exameEletronico;
	}

	public boolean validarExame(Integer id) {
		return this.exameEletronicoRepository.exists(id);
	}

	public List<ExameEletronico> todos() {
		List<ExameEletronico> todos = this.exameEletronicoRepository.findAll();
		if (todos.isEmpty())
			throw new RegistoNaoEncontradoException("Nenum registo de exame encontrado.");
		return todos;
	}
	
	public List<ExameCurso> validarTipoDeExame(TipoExame exame){
		List<ExameCurso> examesCurso = this.exameCursoRepository.findByTipoExame(exame);
		if(examesCurso.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de perguntas não encontrado.");
		return examesCurso;
	}

}
