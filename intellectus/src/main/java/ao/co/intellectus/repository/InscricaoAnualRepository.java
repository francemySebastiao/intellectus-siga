package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.InscricaoAnual;

public interface InscricaoAnualRepository extends CrudRepository<InscricaoAnual,Integer>{
	public List<InscricaoAnual> findByAno(Integer ano);
}
