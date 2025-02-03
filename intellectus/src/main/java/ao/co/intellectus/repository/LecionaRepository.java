package ao.co.intellectus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Docente;
import ao.co.intellectus.model.Intervalo;
import ao.co.intellectus.model.Leciona;
import ao.co.intellectus.model.Sala;
import ao.co.intellectus.model.Semana;
import ao.co.intellectus.model.Semestre;
import ao.co.intellectus.model.Turma;

public interface LecionaRepository extends CrudRepository<Leciona,Integer>{   
	public List<Leciona> findByDocente(Docente docente);
	public List<Leciona> findByDocenteAndAnoLectivo(Docente docente,AnoLectivo anoLectivo);

	public Leciona findByDocenteAndAnoLectivoAndIntervaloAndDiaAula(Docente docente,AnoLectivo anoLectivo, Intervalo intervalo, Date dia_aula);
	public Leciona findById(Integer id);
	
	//public List<Leciona> findByDiaAula
	public List<Leciona> findByOrderByDiaAulaDesc();
	
    public List<Leciona> findByAnoLectivoAndTurmaAndAnoCurricular(AnoLectivo anoLectivo,Turma turma,Integer anoCurricular);
    public List<Leciona> findByAnoLectivoAndTurmaAndAnoCurricularAndCurso(AnoLectivo anoLectivo,Turma turma,Integer anoCurricular, Curso curso);
    public List<Leciona> findByDocenteAndDiaSemanaAndSemestreAndIntervaloAndAnoLectivo(Docente docente,Semana diaSemana, Semestre semestre, Intervalo intervalo,AnoLectivo anoLectivo);
    public List<Leciona> findByTurmaAndCursoAndAnoCurricularAndDiaSemanaAndSemestreAndIntervaloAndAnoLectivo(Turma turma, Curso curso, Integer anoCurricular, Semana diaSemana, Semestre semestre, Intervalo intervalo, AnoLectivo anoLectivo);
    public List<Leciona> findBySalaAndDiaSemanaAndSemestreAndIntervaloAndAnoLectivo(Sala sala,Semana diaSemana, Semestre semestre, Intervalo intervalo,AnoLectivo anoLectivo);
    public List<Leciona> findByAnoLectivoAndAnoCurricularAndCursoAndSemestreAndTurma(AnoLectivo anoLectivo,Integer anoCurricular, Curso curso, Semestre semestre,Turma turma);
    public List<Leciona> findByAnoLectivoAndAnoCurricularAndCursoAndSemestreAndSala(AnoLectivo anoLectivo,Integer anoCurricular, Curso curso, Semestre semestre,Sala sala);
    public List<Leciona> findByAnoLectivoAndAnoCurricularAndCursoAndDocenteAndSemestre(AnoLectivo anoLectivo,Integer anoCurricular, Curso curso,Docente docente,Semestre semestre);
    
    public List<Leciona> findByAnoLectivoAnoLectivoAndDocenteIdAndDisciplinaIdAndCursoId(Integer anoLectivo,Integer docente,Integer disciplina, Integer curso);
    
    public List<Leciona> findByAnoLectivoAndDocenteAndDisciplinaAndTurma(AnoLectivo anoLectivo,Docente docente,Disciplina disciplina,Turma turma);
    public List<Leciona> findByAnoLectivoAndTurmaAndDisciplina(AnoLectivo anoLectivo,Turma turma,Disciplina disciplina);
    
    public List<Leciona> findByAnoLectivoAndDocenteAndDisciplinaAndTurmaAndDiaSemanaAndIntervalo(AnoLectivo anoLectivo,Docente docente,Disciplina disciplina,Turma turma,Semana semana,Intervalo intervalo);
    
    
}