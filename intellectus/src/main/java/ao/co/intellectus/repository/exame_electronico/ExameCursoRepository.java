package ao.co.intellectus.repository.exame_electronico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.exame_eletronico.ExameCurso;
import ao.co.intellectus.model.exame_eletronico.TipoExame;

public interface ExameCursoRepository extends JpaRepository<ExameCurso, Integer>{

	public List<ExameCurso> findByCurso(Curso curso);
	
	public List<ExameCurso> findByCursoAndTipoExame(Curso curso, TipoExame tipoExame);

	public ExameCurso findByCursoAndTipoExameNot(Curso curso, TipoExame tipoExame);
	
	public List<ExameCurso> findByTipoExame(TipoExame tipoExame);
	
}
