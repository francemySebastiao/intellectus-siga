package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.MapaFinanceiro;

public interface MapaFinanceiroRepository extends CrudRepository<MapaFinanceiro,Integer>{
   public List<MapaFinanceiro> findByGrauAndLectivo(String grau,Integer lectivo);
}
