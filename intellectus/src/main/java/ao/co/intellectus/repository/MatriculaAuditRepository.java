package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.MatriculaAudit;

public interface MatriculaAuditRepository extends JpaRepository<MatriculaAudit, Integer>{

	// public List<MatriculaAudit> findByNumeroDeAluno(String numeroAluno);
     public List<MatriculaAudit> findByNumeroDeAluno (String numeroAluno);
	 
	Page<MatriculaAudit> findAllBynumeroDeAlunoAndAnoLectivoAnoLectivo(String numeroAluno, Integer anoLectivo,
			Pageable paginacao);
	

}
