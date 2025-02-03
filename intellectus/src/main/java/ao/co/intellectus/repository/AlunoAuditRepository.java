package ao.co.intellectus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.AlunoAudit;

public interface AlunoAuditRepository extends CrudRepository<AlunoAudit, Integer> {

	List<AlunoAudit> findByNumeroDeAluno(String numeroAluno);

	Page<AlunoAudit> findAllByNumeroDeAlunoAndUltimaModificacao(String numeroAluno, Date data, Pageable paginacao);
	

}
