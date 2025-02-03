package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Disciplina;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Integer> {
	public List<Disciplina> findByCursoAndAnoCorricular(Curso curso, int anoCurricular);
	public List<Disciplina> findByCursoAndAnoCorricularAndStatus(Curso curso, int anoCurricular,boolean status);
	public List<Disciplina> findByCursoAndStatus(Curso curso,boolean status);
	public List<Disciplina> findByCurso(Curso curso);
	
	public List<Disciplina> findByCursoAndStatusOrderByAnoCorricularAsc(Curso curso,boolean estado);
	public Disciplina findByDescricao(String descricao);
	public Disciplina findByDescricaoAndCurso(String descricao, Curso curso);
	public Disciplina findByCursoAndIdAndStatus(Curso curso,int id,boolean status);
	
	public List<Disciplina> findByCursoAndStatusAndAnoCorricular(Curso curso,boolean status,int anoCurricular);
	
	public List<Disciplina> findByCursoAndCursoDeVeraoAndStatus(Curso curso,boolean cursoDeVerao,boolean status);
	public List<Disciplina> findByStatus(boolean status);
	   
	@Query(value="SELECT * FROM T_DISCIPLINA O WHERE CONTROLE_TRANSICAO_ANO<>'F'",nativeQuery = true)
	public List<Disciplina> buscarDisciplinasParaEquivalencia();
	
	@Query(value="SELECT * FROM T_DISCIPLINA O",nativeQuery = true)
	public List<Disciplina> buscarDisciplinasParaHorarios();
	
	@Query(value="SELECT * from T_DISCIPLINA WHERE CODIGO_CURSO =:codigo_curso AND APROVACAO_DISCIPLINA = 1", nativeQuery=true)
	public List<Disciplina> descricao(@Param("codigo_curso") Integer codigo_curso);
}