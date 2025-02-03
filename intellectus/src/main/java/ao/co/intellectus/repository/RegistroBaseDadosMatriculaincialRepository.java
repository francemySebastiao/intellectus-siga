package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.RegistroBaseDadosMatriculaincial;

public interface RegistroBaseDadosMatriculaincialRepository extends CrudRepository<RegistroBaseDadosMatriculaincial, Integer> {
	public List<RegistroBaseDadosMatriculaincial> findByAnoLectivo(Integer anoLectivo);

}
