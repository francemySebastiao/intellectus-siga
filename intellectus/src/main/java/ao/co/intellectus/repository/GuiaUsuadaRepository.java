package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaUsuada;

public interface GuiaUsuadaRepository extends CrudRepository<GuiaUsuada,Integer>{
     public List<GuiaUsuada> findByGuia(Guia guia);

}
