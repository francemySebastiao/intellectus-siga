package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Equivalencia;

public interface EquivalenciaRepository extends CrudRepository<Equivalencia,Integer>{
   public List<Equivalencia> findByAluno(Aluno aluno);
   public List<Equivalencia> findByAlunoAndAnoLectivo(Aluno aluno,AnoLectivo anoLectivo);
}