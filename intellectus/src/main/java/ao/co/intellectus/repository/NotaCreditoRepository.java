package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.NotaCredito;

public interface NotaCreditoRepository extends JpaRepository<NotaCredito, Long> {

	@Query(value="SELECT * FROM T_NOTA_CREDITO WHERE N_NOTA_CREDITO =:notaCredito",nativeQuery=true)
	public NotaCredito buscarNumeroNotaCredito(@Param("notaCredito") String notaCredito);
	
	@Query(value="SELECT * FROM T_NOTA_CREDITO WHERE ID_FACTURA =:factura",nativeQuery=true)
	public NotaCredito buscarFactura(@Param("factura") Long factura);
	
	/*@Query(value="SELECT * FROM T_NOTA_CREDITO WHERE ID_FACTURA =:factura",nativeQuery=true)
	public List<NotaCredito> buscarNotaCredito(@Param("factura") Integer factura);*/
}
