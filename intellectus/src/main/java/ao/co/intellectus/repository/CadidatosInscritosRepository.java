package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.CadidatosInscritos;

public interface CadidatosInscritosRepository extends CrudRepository<CadidatosInscritos, Integer>{
	public CadidatosInscritos findByAnoLectivo(Integer anoLectivo);
}
