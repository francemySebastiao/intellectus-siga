package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.ProgInscritoPorCurso;

public interface ProgInscritoPorCursoRepository extends CrudRepository<ProgInscritoPorCurso,Integer>{
	 public List<ProgInscritoPorCurso> findByAnoLectivo(Integer anoLectivo);
}
