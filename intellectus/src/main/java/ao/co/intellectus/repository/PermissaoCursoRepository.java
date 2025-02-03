package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.Usuario;

public interface PermissaoCursoRepository extends JpaRepository<PermissaoCurso,Integer>{
    
	
	public PermissaoCurso findByCursoIdAndUsuario(Integer curso,Usuario usuario);
    
    
    
    public PermissaoCurso findByCursoAndUsuarioUserName(Curso curso,String usuario);
    
    public List<PermissaoCurso> findByUsuario(Usuario usuario);
    
    public List<PermissaoCurso> findByUsuarioAndPermissao(Usuario usuario,Permissao permissao);
    
    public List<PermissaoCurso> findByUsuarioAndPermissaoAndCursoStatus(Usuario usuario,Permissao permissao,boolean status);
}
