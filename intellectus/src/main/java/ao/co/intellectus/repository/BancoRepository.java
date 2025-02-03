package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Banco;

public interface BancoRepository extends CrudRepository<Banco,Integer>{
      public List<Banco> findByEstado(boolean estado);
      public Banco findByBanco(String banco);
      
    @Query(value="select * from t_banco where id=:id",nativeQuery=true)
  	Banco BuscarID(@Param("id") Integer id);
}
