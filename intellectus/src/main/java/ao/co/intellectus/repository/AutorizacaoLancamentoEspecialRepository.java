package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.AutorizacaoLancamentoEspecial;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Docente;
import ao.co.intellectus.model.Prova;
import ao.co.intellectus.model.Turma;

public interface AutorizacaoLancamentoEspecialRepository extends JpaRepository<AutorizacaoLancamentoEspecial,Integer>{
	public List<AutorizacaoLancamentoEspecial> findByEmCurso(boolean emCurso);
	public List<AutorizacaoLancamentoEspecial> findByDocenteAndDisciplinaAndAnoLectivoAndTurmaAndEmCurso(Docente docente,Disciplina disciplina,AnoLectivo anoLectivo,Turma turma,boolean emCurso);
	
	
	//public List<AutorizacaoLancamentoEspecial> findByDocenteAndDisciplinaAndAnoLectivoAndTurmaAndEmCurso(Docente docente,Disciplina disciplina,AnoLectivo anoLectivo,Turma turma,boolean emCurso);
	
	
	public List<AutorizacaoLancamentoEspecial> findByDocenteAndDisciplinaAndAnoLectivoAndTurmaAndEmCursoAndProva(Docente docente,Disciplina disciplina,AnoLectivo anoLectivo,Turma turma,boolean emCurso,Prova prova);
	
	//public List<AutorizacaoLancamentoNota> findByEmCursoAndTipoDisciplinaAndProva(boolean emCurso,TipoDisciplina tipoDisciplina,Prova prova);
	//disciplina,docente,turma,prova
	
	//public List<AutorizacaoLancamentoEspecial> findByEmCursoAndDisciplinaAndDocenteAndTurmaAndProva(boolean emCurso,Disciplina disciplina,Docente docente,Turma turma);
	
	public List<AutorizacaoLancamentoEspecial> findByDocenteAndDisciplinaAndTurmaAndEmCursoAndProva(Docente docente,Disciplina disciplina,Turma turma,boolean emCurso,Prova prova);
	
}
