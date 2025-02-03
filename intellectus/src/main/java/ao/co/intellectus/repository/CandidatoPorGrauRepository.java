package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.CandidatoPorGrau;

public interface CandidatoPorGrauRepository extends CrudRepository<CandidatoPorGrau, Integer>{
	public List<CandidatoPorGrau> findByAnoLectivo(Integer anoLectivo);
}
