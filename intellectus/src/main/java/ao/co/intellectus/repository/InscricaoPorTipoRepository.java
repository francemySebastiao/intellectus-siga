package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.InscricaoPorTipo;

public interface InscricaoPorTipoRepository extends CrudRepository<InscricaoPorTipo,Integer>{
   public List<InscricaoPorTipo> findByAno(Integer ano);
}
