package ao.co.intellectus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Equivalencia;
import ao.co.intellectus.model.HistoricoEquivalencia;

public interface EquivalenciaHistoricoRepository extends CrudRepository<HistoricoEquivalencia,Integer>{
    public List<HistoricoEquivalencia> findByEquivalencia(Equivalencia equivalencia);
    public List<HistoricoEquivalencia> findByAlunoAndDisciplinaDestino(Aluno aluno,Disciplina disciplina);
    public HistoricoEquivalencia findByDisciplinaDestinoAndEquivalencia(Disciplina disciplina,Equivalencia equivalencia);
    public List<HistoricoEquivalencia> findByAlunoAndEstado(Aluno aluno,boolean estado);
    public List<HistoricoEquivalencia> findByAluno(Aluno aluno);
    
    //notaOrigem
    public List<HistoricoEquivalencia> findByAlunoAndEstadoAndNotaOrigem(Aluno aluno,boolean estado,Float notaOrigem);
    
    public List<HistoricoEquivalencia> findByAlunoNumeroDeAlunoAndEstado(String numero,boolean estado);
    public List<HistoricoEquivalencia> findByAlunoNumeroDeAlunoAndEstadoAndDisciplinaDestinoCurso(String numero,boolean estado,Curso curso);
	public Page<HistoricoEquivalencia> findAll(Pageable paginacao);
	public Page<HistoricoEquivalencia> findBynumeroAlunoAndEquivalenciaDataEquivalencia(Integer numeroAluno, Date data,Pageable paginacao);
	public Page<HistoricoEquivalencia> findBynumeroAlunoAndEquivalenciaAnoLectivo(Integer numeroAluno,
			Integer anoLectivo, Pageable paginacao);
	public Page<HistoricoEquivalencia> findBynumeroAluno(Integer numeroAluno, Pageable paginacao); 
	
	
	
}