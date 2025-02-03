package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Faculdade;

public interface FaculdadeRepository extends CrudRepository<Faculdade, Integer>{
	public Faculdade findByFaculdade(String faculdade);
}