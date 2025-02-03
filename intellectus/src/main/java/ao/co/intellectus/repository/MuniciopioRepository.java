package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Municipio;
import ao.co.intellectus.model.Provincia;

public interface MuniciopioRepository extends CrudRepository<Municipio,Integer>{
	public Municipio findByDescricaoAndProvincia(String descricao, Provincia p);
}
