package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Faculdade;

public interface CursoRepository extends JpaRepository<Curso,Long>{
	public Curso findByDescricao(String descricao);
    public Curso findByCodigo(String codigo);
    public List<Curso>  findByDescricaoLike(String descricao);
   
    @Query("SELECT c.id,c.descricao FROM Curso c where c.status=1")
    public Iterable<Curso> ListSelectMenu();
    
    public Curso findByIdAndStatus(Integer id,boolean status);
    
    public Curso findById(Integer id);
    
    public List<Curso> findByStatus(boolean status);
	public List<Curso> findByFaculdade(Faculdade faculdade);
}
 