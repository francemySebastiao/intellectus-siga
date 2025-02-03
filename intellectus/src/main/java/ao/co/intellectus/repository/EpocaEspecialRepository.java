package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.EpocaEspecial;

public interface EpocaEspecialRepository extends CrudRepository<EpocaEspecial,Integer>{
	public EpocaEspecial findByNumeroAluno(Integer numero);
}
