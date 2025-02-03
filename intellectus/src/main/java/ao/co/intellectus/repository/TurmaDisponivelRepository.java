package ao.co.intellectus.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.TurmaDisponivel;
import ao.co.intellectus.model.Turno;

public interface TurmaDisponivelRepository extends CrudRepository<TurmaDisponivel,Integer>{
	public List<TurmaDisponivel> findByCursoAndAnoAndAceitaInscricoes(Curso curso,Integer ano,boolean aceitaInsceicoes);
	
	public List<TurmaDisponivel> findByCursoAndAceitaInscricoes(Curso curso,boolean aceitaInsceicoes);
	
	public TurmaDisponivel findByCursoAndTurmaAndAno(Curso curso, Turma turma, Integer ano);
	public TurmaDisponivel findByCursoAndTurmaAndAnoAndAceitaInscricoes(Curso curso, Turma turma, Integer ano,boolean aceitaInscricoes);
	
	
	public TurmaDisponivel findByCursoAndTurmaTurnoAndAnoAndAceitaInscricoes(Curso curso,Turno turma, Integer ano,boolean aceitaInscricao);
	
	
	public List<TurmaDisponivel> findByCurso(Curso curso);	
	//public List<TurmaDisponivel> findByCursoAndAceitaInscricoes(Curso curso,boolean aceitaInscricoes);	
	
}