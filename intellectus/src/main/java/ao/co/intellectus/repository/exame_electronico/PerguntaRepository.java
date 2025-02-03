package ao.co.intellectus.repository.exame_electronico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.exame_eletronico.Pergunta;
import ao.co.intellectus.model.exame_eletronico.TipoExame;

public interface PerguntaRepository extends JpaRepository<Pergunta, Integer>{

	
  public List<Pergunta> findByDescricaoAndTipoExame(String pergunta,TipoExame tipoExame);
  
  public List<Pergunta> findByTipoExameAndCurso(TipoExame tipoExame,Curso curso);
  public List<Pergunta> findByTipoExameAndCursoAndEstado(TipoExame tipoExame,Curso curso, Boolean estado);
  
  public List<Pergunta> findByTipoExameAndCursoIdAndAnoLectivoId(TipoExame tipoExame,Integer curso,Integer anoLectivo);
 public List<Pergunta> findByTipoExameAndCursoIdAndAnoLectivoIdAndEstado(TipoExame tipoExame, Integer curso, Integer anoLectivo, Boolean estado);

  public Pergunta findById(Integer id);
  public List<Pergunta> findByDescricao(String descricao);
  
  
	

}
