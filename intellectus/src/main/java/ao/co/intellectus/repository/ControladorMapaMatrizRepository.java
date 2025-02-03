package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.ControladorMapaMatriz;
import ao.co.intellectus.model.Docente;

public interface ControladorMapaMatrizRepository extends CrudRepository<ControladorMapaMatriz,Integer>{
     public ControladorMapaMatriz findByValorAndDocente(Integer valor,Docente docente);
     public List<ControladorMapaMatriz> findByDocente(Docente docente);
     public ControladorMapaMatriz findByDocenteAndAtivo(Docente docente,boolean ativo);
}
