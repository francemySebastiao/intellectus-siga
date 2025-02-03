package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.Turno;

public interface TurmaRepository extends JpaRepository<Turma,Integer>{
	public Turma findByTurma(String turma);
	
	public List<Turma> findByInstituicao(Instituicao instituicao);
	public List<Turma> findByTurno(Turno turno);
	
}
