package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Sala;

public interface SalaRepository extends CrudRepository<Sala,Integer>{
	public Sala findByDesignacao(String designacao);
}
