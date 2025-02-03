package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.HistoricoAlunoAudit;

public interface HistoricoAlunoAuditRepository extends CrudRepository<HistoricoAlunoAudit, Integer> {

	public List<HistoricoAlunoAudit> findByNumeroDeAlunoAndDisciplinaId(String numeroDeAluno,Integer codigoDisciplina);
	public List<HistoricoAlunoAudit> findByNumeroDeAluno(String numeroDeAluno);
}
