package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Docente;
import ao.co.intellectus.model.MatrizDocente;

public interface MatrizDocenteRepository extends CrudRepository<MatrizDocente,Integer>{
      public List<MatrizDocente> findByDocente(Docente docente);
      
      public List<MatrizDocente> findByDocenteOrderByLinha(Docente docente);
      
}
