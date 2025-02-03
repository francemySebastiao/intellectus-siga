package ao.co.intellectus.repository.ccd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.ccd.HistoricoAlunoCdd;
import ao.co.intellectus.model.ccd.MatriculaCcd;

public interface HistoricoAlunoCddRepository extends JpaRepository<HistoricoAlunoCdd, Integer> {

	List<HistoricoAlunoCdd> findByAlunoAndMatriculaCcdAnoLectivoAndDisciplina(Aluno aluno, AnoLectivo anoLectivo,Disciplina disciplina);

	Long countByMatriculaCcd(MatriculaCcd matricula);


}
