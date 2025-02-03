package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.TipoDisciplina;
import ao.co.intellectus.model.Turma;

public interface HistoricoAlunoRepository extends CrudRepository<HistoricoAluno,Integer>{
	public List<HistoricoAluno> findByAlunoAndCurso(Aluno aluno,Curso curso);
	
	public List<HistoricoAluno> findByAlunoAndCursoAndAprovadoAndDisciplinaStatus(Aluno aluno,Curso curso,boolean aprovado,boolean status);
	
	//MAIOR OU IGUAL QUE...
	public List<HistoricoAluno> findByAlunoAndCursoAndAprovadoAndDisciplinaStatusAndNotaFinalGreaterThanEqual(Aluno aluno,Curso curso,boolean aprovado,boolean status,float notaFinal);
	
	public List<HistoricoAluno> findByAlunoAndCursoAndAprovadoAndDisciplinaStatusAndValidadaAndNotaFinalGreaterThanEqual(Aluno aluno,Curso curso,boolean aprovado,boolean status,boolean validada,float notaFinal);
	
	
	//MENO QUE...
	public List<HistoricoAluno> findByAlunoAndCursoAndAprovadoAndDisciplinaStatusAndNotaFinalLessThan(Aluno aluno,Curso curso,boolean aprovado,boolean status,float notaFinal);
	
	public List<HistoricoAluno> findByAluno(Aluno aluno);
	public List<HistoricoAluno> findByAlunoNumeroDeAluno(String numero);
	
	
	//select * from t_historico_aluno where ano_curricular=1 and codigo_ano_lectivo=19 and codigo_curso=19 and codigo_turma=2
	//COMENTADO...
	public List<HistoricoAluno> findByAnoCorricularAndAnoLectivoAndCursoAndTurma(Integer anoCorricular,AnoLectivo anoLectivo,Curso curso,Turma turma);
	
	public List<HistoricoAluno> findByAlunoAndCursoOrderByAnoCorricular(Aluno aluno,Curso curso);
	public List<HistoricoAluno> findByAlunoOrderByAnoCorricular(Aluno aluno);
	public List<HistoricoAluno> findByAlunoAndMatricula(Aluno aluno,Matricula matricula);
	public List<HistoricoAluno> findByMatricula(Matricula matricula);
	public List<HistoricoAluno> findByMatriculaAndDisciplinaTipo(Matricula matricula,TipoDisciplina tipo);
	
	public List<HistoricoAluno> findByMatriculaAndDisciplinaTipoAndDisciplinaTipo(Matricula matricula,TipoDisciplina tipo,TipoDisciplina anual);
	
	
	public List<HistoricoAluno> findByMatriculaAndAprovado(Matricula matricula,boolean aprovado);
	public List<HistoricoAluno> findByAprovadoAndAluno(boolean aprovado,Aluno aluno);
	public List<HistoricoAluno> findByAprovadoAndAlunoNumeroDeAluno(boolean aprovado,String numero);
	
	
	public List<HistoricoAluno> findByAprovadoAndAlunoAndCurso(boolean aprovado,Aluno aluno,Curso curso);
	//public List<HistoricoAluno> findByAprovadoAndAluno(boolean aprovado,Aluno aluno);
    public List<HistoricoAluno> findByFechadaAndMatricula(boolean fechada,Matricula matricula);
    public List<HistoricoAluno> findByFechadaAndAluno(boolean fechada,Aluno aluno);
	public List<HistoricoAluno> findByAlunoAndAnoLectivo(Aluno aluno,AnoLectivo anoLectivo);
	public List<HistoricoAluno> findByAlunoAndAnoCorricularAndDisciplina(Aluno aluno,Integer anoCurricular,Disciplina disciplina);
	
	//ORDENAR POR NAME DE ALUNO
	public List<HistoricoAluno> findByTurmaAndDisciplinaAndAnoLectivoAndAnoCorricular(Turma turma,Disciplina disciplina,AnoLectivo anoLectivo,Integer ano);
	
	////OrderByAlunoNome
	//public List<HistoricoAluno> findByTurmaAndDisciplinaAndAnoLectivoAndAnoCorricularOrderByAnoLectivoAsc(Turma turma,Disciplina disciplina,AnoLectivo anoLectivo,Integer ano);
	
	public List<HistoricoAluno> findByTurmaAndDisciplinaAndAnoLectivoAndAnoCorricularOrderByMatriculaAlunoNomeAsc(Turma turma,Disciplina disciplina,AnoLectivo anoLectivo,Integer ano);
	
	
	
	//nova busca de çlotação nas turmas
	public List<HistoricoAluno> findByTurmaAndDisciplinaAndAnoLectivoAndDisciplinaAnoCorricularOrderByMatriculaAlunoNomeAsc(Turma turma,Disciplina disciplina,AnoLectivo anoLectivo,Integer ano);
	
	
	public List<HistoricoAluno> findByTurmaAndDisciplinaAndAnoLectivoOrderByMatriculaAlunoNomeAsc(Turma turma,Disciplina disciplina,AnoLectivo anoLectivo);
	
	
	
	//SUB
	//public List<HistoricoAluno> findByAlunoAndAnoLectivoAndDisciplina(Aluno aluno,AnoLectivo anoLectivo,Disciplina disciplina);
	
	public List<HistoricoAluno> findByAlunoAndMatriculaAnoLectivoAndDisciplina(Aluno aluno,AnoLectivo anoLectivo,Disciplina disciplina);
	
	
	//public HistoricoAluno findByAlunoAndAnoLectivoAndDisciplinaAndMatriculaAnulado(Aluno aluno,AnoLectivo anoLectivo,Disciplina disciplina,boolean anulado);
	
	public List<HistoricoAluno> findByDisciplinaAndAluno(Disciplina disciplina,Aluno aluno);
	
	public List<HistoricoAluno> findByDisciplinaAndAlunoAndMatriculaAnoLectivo(Disciplina disciplina,Aluno aluno,AnoLectivo anoLectivo);//TAKI
	
	public List<HistoricoAluno> findByDisciplinaAndAlunoAndAprovado(Disciplina disciplina,Aluno aluno,boolean aprovado);
	
	public List<HistoricoAluno> findByCursoAndAnoLectivoAndAnoCorricular(Curso curso,AnoLectivo anoLectivo,Integer anoCorricular);
	public List<HistoricoAluno> findByAlunoAndDisciplinaAnoCorricular(Aluno aluno,Integer anocurricular);
	public List<HistoricoAluno> findByAlunoAndDisciplinaAnoCorricularAndMatriculaCurso(Aluno aluno,Integer anocurricular,Curso curso);
	

	@Query(value="SELECT * FROM T_HISTORICO_ALUNO WHERE CODIGO_ANO_LECTIVO =:codigoAnoLectivo AND CODIGO_CURSO =:codigoCurso AND CODIGO_DISCIPLINA =:codigoDisciplina AND CODIGO_TURMA =:codigoTurma   ",nativeQuery=true)
	public List<HistoricoAluno> validarNotas(@Param("codigoAnoLectivo") Integer codigoAnoLectivo, @Param("codigoCurso")Integer codigoCurso, @Param("codigoDisciplina")Integer codigoDisciplina, @Param("codigoTurma")Integer codigoTurma);
	
	public HistoricoAluno NumeroDeAlunoAndDisciplinaId(String numeroDeAluno,Integer codigoDisciplina);
	
	@Query(value="SELECT * FROM T_HISTORICO_ALUNO WHERE NUMERO_DE_ALUNO =:numeroAluno AND NOTA_FINAL IS NULL",nativeQuery=true)
	public List<HistoricoAluno> disciplinaNotaNull(@Param("numeroAluno") String numeroAluno);
	
	@Query(value="SELECT * FROM T_HISTORICO_ALUNO AS A JOIN T_DISCIPLINA AS B ON (A.CODIGO_DISCIPLINA = B.ID) WHERE A.CODIGO_MATRICULA =:matricula AND B.TIPO =:semestre",nativeQuery=true)
	public List<HistoricoAluno> historicoAlunoSemestre(@Param("matricula") Integer matricula, @Param("semestre") String semestre);
	
}