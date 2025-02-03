package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Prova;

public interface ProvaRepository extends CrudRepository<Prova,Integer>{
    public List<Prova> findByExtraordinaria(boolean estraordinario);
    
    public List<Prova> findByEstado(boolean estado);
}
