package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.GeracaoErrada;

public interface GeracaoErradaRepository extends CrudRepository<GeracaoErrada,Integer>{
   public List<GeracaoErrada>  findByLiquidada(Integer liquidada);
}
