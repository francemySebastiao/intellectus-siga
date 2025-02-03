package ao.co.intellectus.repository.exame_electronico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.exame_eletronico.Pergunta;
import ao.co.intellectus.model.exame_eletronico.Resposta;

public interface RepostaRepository extends JpaRepository<Resposta, Integer> {

	public List<Resposta> findByPergunta(Pergunta pergunta);
	//public List<Resposta> findByPerguntaEstado(Pergunta pergunta, Boolean estado);
	
	//public List<Resposta> findByPergunta();
	

}
