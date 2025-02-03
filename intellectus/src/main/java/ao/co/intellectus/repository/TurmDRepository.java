package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.TurmD;

public interface TurmDRepository extends CrudRepository<TurmD,Integer>{
    public List<TurmD> findByAnoLectivoAndDisciplinaAndDocenteAndCurso(Integer anoLectivo,Integer disciplina,Integer docente,Integer curso);
}
 