package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.NumeroParaMatriz;

public interface NumeroParaMatrizRepository extends CrudRepository<NumeroParaMatriz,Integer>{
    public NumeroParaMatriz findByValor(Integer valor);
}
