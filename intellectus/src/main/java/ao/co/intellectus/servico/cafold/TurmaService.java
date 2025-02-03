package ao.co.intellectus.servico.cafold;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Turma;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repositoryTurma;
	public List<Turma> todas(){
		List<Turma> turmas = this.repositoryTurma.findAll();
		if(turmas.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de turmas não encontradas.");
		return turmas;
	}
	
}
