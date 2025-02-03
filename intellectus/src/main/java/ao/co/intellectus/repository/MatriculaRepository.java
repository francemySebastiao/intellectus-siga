package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.EmpresaConvenio;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.PlanoPagamento;
import ao.co.intellectus.model.Turma;

public interface MatriculaRepository extends CrudRepository<Matricula, Integer> {
	
	@Query(value="SELECT * FROM T_MATRICULA WHERE NUMERO_DE_ALUNO =:numeroAluno AND CODIGO_ANO_LECTIVO =:anoLectivo AND ANULADO = 0", nativeQuery=true)
	public List<Matricula> buscarAlunoAndAnoLectivoAndAnulado(@Param("numeroAluno") String numeroAluno, @Param("anoLectivo") Integer anoLectivo);
	
	@Query(value="SELECT TOP(1) * FROM T_MATRICULA WHERE NUMERO_DE_ALUNO =:numeroAluno ORDER BY ANO_CURRICULAR DESC", nativeQuery=true)
	public Matricula buscarUltimaMatricula(@Param("numeroAluno") String numeroAluno);
	
	public List<Matricula> findByAlunoAndCursoAndAnoLectivoAndSemestre(Aluno aluno, Curso curso, int anoLectivo, String Semestre);

	public List<Matricula> findByAluno(Aluno aluno);

	public List<Matricula> findByAlunoNumeroDeAluno(String numero);

	public List<Matricula> findByAlunoOrderByAnoLectivoDesc(Aluno aluno);
	
	public List<Matricula> findByAnuladoAndAlunoOrderByAnoLectivoDesc(boolean anulado,Aluno aluno);

	public List<Matricula> findByAlunoAndAnoLectivoAndSemestre(Aluno aluno, AnoLectivo anoLectivo, String semestre);

	public Matricula findByAlunoAndAnoLectivo(Aluno aluno, AnoLectivo anoLectivo);

	public Matricula findByAlunoAndAnoLectivoAndAnulado(Aluno aluno, AnoLectivo anoLectivo, boolean anulado);

	public Matricula findByAlunoNumeroDeAluno(String numeroDoAluno, AnoLectivo anoLectivo, boolean anulado);

	// public Matricula findByAlunoAndAnoLectivoAnoLectivoAndAnulado(Aluno
	// aluno,Integer anoLectivo,boolean anulado);

	public List<Matricula> findByAlunoAndAnulado(Aluno aluno, boolean anulado);
	
	public List<Matricula> findByAlunoAndAnoLectivoOrderByDataAnulamentoDesc(Aluno aluno, AnoLectivo anoLectivo);
	
	public List<Matricula> findByAnoLectivo(AnoLectivo anoLectivo);
	public List<Matricula> findByAnoLectivoAndCurso(AnoLectivo anoLectivo,Curso curso);
	public List<Matricula> findByTurmaBase(Turma turma);
	

	public List<Matricula> findByAnoLectivoAndPlanoPagamento(AnoLectivo anoLectivo, PlanoPagamento planoPagamento);

	public List<Matricula> findByEmpresaConvenioAndAnoLectivo(EmpresaConvenio empresaConvenio, AnoLectivo anoLectivo);

	// BUSCA DOS ALUNOS DA TURMA DESEJADA...
	public List<Matricula> findByCursoAndAnoLectivoAndTurmaBaseAndAnoCurricular(Curso curso, AnoLectivo anoLectivo,Turma turma, Integer AnoCurricular);

	public List<Matricula> findByAnoLectivoAndAnulado(AnoLectivo anoLectivo, boolean anulado);

	public List<Matricula> findByAlunoAndAnuladoAndCurso(Aluno aluno, boolean anulado, Curso curso);

	public List<Matricula> findByAnoLectivoAndAnuladoAndCursoGrau(AnoLectivo anoLectivo, boolean anulado, Grau grau);

	public List<Matricula> findByAnoLectivoAndAnuladoAndCursoGrauAndTipoInscricaoId(AnoLectivo anoLectivo,boolean anulado, Grau grau, Integer codigoTipoInscricao);

	public List<Matricula> findByAnoLectivoAndAnuladoAndProcessamentoReferencia(AnoLectivo anoLectivo, boolean anulado,boolean processo);

	public List<Matricula> findByAlunoAndAnoCurricular(Aluno aluno, Integer anoCurricular);

	public List<Matricula> findByAnoCurricularAndAnoLectivoAndCursoAndTurmaBase(Integer anoCurricular,AnoLectivo anoLectivo, Curso curso, Turma turma);

	public List<Matricula> findByAnoCurricularAndAnoLectivoAndCursoAndTurmaBaseAndAnulado(Integer anoCurricular,AnoLectivo anoLectivo, Curso curso, Turma turma, boolean anulado);

	public List<Matricula> findByAnoCurricularAndCursoAndTurmaBase(Integer anoCurricular, Curso curso, Turma turma);

	public Matricula findByAlunoAndAnoLectivoAndCursoAndTurmaBase(Aluno aluno, AnoLectivo anoLectivoAtivo, Curso curso,Turma turmaBase);

	public List<Matricula> findByCursoAndInscritoOnlineAndVerificado(Curso curso, boolean b, boolean c);
	
	@Query(value = "SELECT TOP(5) * FROM T_MATRICULA AS A JOIN T_TURMA AS B ON (A.CODIGO_TURMA_BASE = B.ID) JOIN T_CURSO AS C ON (A.CODIGO_CURSO = C.ID) WHERE A.CODIGO_ANO_LECTIVO =:anoLectivo AND TURNO =:turno AND CODIGO_TIPO_INSCRICAO =:tipoInscricao AND ANULADO = 0 AND C.GRAU =:grau", nativeQuery = true)
	public List<Matricula> buscarAlunoAnoCompletoManha(@Param("anoLectivo")Integer anoLectivo, @Param("tipoInscricao")Integer tipoInscricao, @Param("turno")String turno, @Param("grau")String grau);

	

	
}
