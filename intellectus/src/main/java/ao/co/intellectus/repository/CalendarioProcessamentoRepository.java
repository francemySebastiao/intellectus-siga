package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.CalendarioProcessamento;

public interface CalendarioProcessamentoRepository extends CrudRepository<CalendarioProcessamento,Integer>{
   public CalendarioProcessamento findByMesAProcessar(boolean mesProcessar);
}
