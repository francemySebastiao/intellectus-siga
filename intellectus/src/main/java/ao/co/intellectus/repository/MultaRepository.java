package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.Multa;

public interface MultaRepository extends CrudRepository<Multa,Integer>{
    public Multa findByGuia(Guia guia);
}
