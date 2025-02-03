package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.ModeloAvaliacao;
import ao.co.intellectus.model.Prova;

public interface ModeloAvaliacaoRepository extends CrudRepository<ModeloAvaliacao,Integer>{
	public ModeloAvaliacao findByProva(Prova prova); 

}
