package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Mes;

public interface MesRepository extends CrudRepository<Mes,Integer>{
	public Mes findByDescricao(String descricao);
}
