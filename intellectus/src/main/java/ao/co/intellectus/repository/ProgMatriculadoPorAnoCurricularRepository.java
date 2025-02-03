package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.ProgMatriculadoPorAnoCurricular;

public interface ProgMatriculadoPorAnoCurricularRepository extends CrudRepository<ProgMatriculadoPorAnoCurricular,Integer>{
      public List<ProgMatriculadoPorAnoCurricular> findByAnoLectivo(Integer anoLectivo);
}
