package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.HistoricoTrocaCurso;

public interface HistoricoTrocaCursoRepository extends CrudRepository<HistoricoTrocaCurso,Integer>{
	public List<HistoricoTrocaCurso> findByAlunoAndEquivalenciaEfetivada(Aluno aluno,boolean equivalencia);
}
