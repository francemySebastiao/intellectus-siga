package ao.co.intellectus.repository.ccd;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.ccd.MatriculaCcd;

public interface MatriculaCcdRepository extends JpaRepository<MatriculaCcd, Integer> {

	List<MatriculaCcd> findByAluno(Aluno aluno);

	List<MatriculaCcd> findByAlunoNumeroDeAluno(String numero);

	Optional<MatriculaCcd> findByAlunoAndAnoLectivoAndAnulado(Aluno aluno, AnoLectivo anoLectivo, boolean nulado);

}
