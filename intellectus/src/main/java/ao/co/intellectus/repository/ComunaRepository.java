package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Comuna;
import ao.co.intellectus.model.Municipio;

public interface ComunaRepository extends CrudRepository<Comuna,Integer>{
	public Comuna findByDescricaoAndMunicipio(String descricao, Municipio municipio);

}
