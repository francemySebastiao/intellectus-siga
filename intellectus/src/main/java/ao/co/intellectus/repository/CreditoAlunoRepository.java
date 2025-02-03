package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.CreaditoDeAluno;

public interface CreditoAlunoRepository extends CrudRepository<CreaditoDeAluno, Integer>{
   public List<CreaditoDeAluno> findByAluno(Aluno aluno);
   
   public List<CreaditoDeAluno> findByAnoLectivoId(Integer anoLectivo);
   
   //public List<CreaditoDeAluno> findByAnoLectivoIdAnd(Integer anoLectivo);
   
   
}
