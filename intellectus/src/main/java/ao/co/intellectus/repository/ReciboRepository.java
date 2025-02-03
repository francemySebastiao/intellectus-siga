package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Recibo;

public interface ReciboRepository extends JpaRepository<Recibo, Long> {
	
	@Query(value="SELECT * FROM T_RECIBO WHERE N_RECIBO =:recibo",nativeQuery=true)
	public Recibo buscarNumeroRecibo(@Param("recibo") String recibo);
	
	@Query(value="SELECT * FROM T_RECIBO WHERE CODIGO_EMPRESA =:empresa",nativeQuery=true)
	public List<Recibo> buscarRecibosEmpresa(@Param("empresa") Integer empresa);

}
