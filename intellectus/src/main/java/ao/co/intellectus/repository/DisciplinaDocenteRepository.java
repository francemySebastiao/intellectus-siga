package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.DisciplinaDocente;

public interface DisciplinaDocenteRepository extends CrudRepository<DisciplinaDocente,Integer>{
	public List<DisciplinaDocente> findByDocenteAndAnoLectivo(Integer professor,Integer anoLectivo);
} 