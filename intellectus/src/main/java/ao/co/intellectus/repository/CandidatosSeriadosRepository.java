package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.CandidatosSeriados;

public interface CandidatosSeriadosRepository extends CrudRepository<CandidatosSeriados, Integer>{
	public CandidatosSeriados findByAnoLectivo(Integer anoLectivo);
}
