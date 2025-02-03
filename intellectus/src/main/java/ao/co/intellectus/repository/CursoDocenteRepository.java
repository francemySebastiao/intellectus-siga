package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.CursoDocente;

public interface CursoDocenteRepository extends CrudRepository<CursoDocente,Integer>{
    public List<CursoDocente> findByDocenteAndAnoLectivo(Integer docente,Integer anoLectivo);
}
