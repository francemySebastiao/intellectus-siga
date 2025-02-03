package ao.co.intellectus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Pais;

public interface PaisRepository extends CrudRepository<Pais,Integer>{
	
		Page<Pais> findAll(Pageable var);

}
