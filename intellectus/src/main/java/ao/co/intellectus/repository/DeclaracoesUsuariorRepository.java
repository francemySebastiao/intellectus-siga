package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.DeclaracoesUsuario;

public interface DeclaracoesUsuariorRepository extends CrudRepository<DeclaracoesUsuario,Integer>{
     public DeclaracoesUsuario findByUsuario(Integer usuario);
}
