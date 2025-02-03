package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Moeda;

public interface MoedaRepository extends CrudRepository<Moeda,Integer>{
	public Moeda findByStatus(boolean status);
	public Moeda findByDesignacaoOrMoeda(String designacao, String moeda);
	@Query(value="select * from t_moeda where id=:id",nativeQuery=true)
	Moeda buscarID(@Param("id") Integer id);
}
