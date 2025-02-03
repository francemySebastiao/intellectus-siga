package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.TurmaDocente;

public interface TurmaDocenteRepository extends CrudRepository<TurmaDocente,Integer>{
     public List<TurmaDocente> findByAnoLectivoAndDocenteOrderByTurmaId(Integer anoLectivo,Integer id);
     
     //OrderByAnoLectivo
}
