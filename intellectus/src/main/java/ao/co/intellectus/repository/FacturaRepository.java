package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

	@Query(value="SELECT * FROM T_FACTURA WHERE N_FACTURA =:factura",nativeQuery=true)
	public Factura buscarNumeroFactura(@Param("factura") String factura);
	
	@Query(value="SELECT * FROM T_FACTURA WHERE CODIGO_EMPRESA =:empresa",nativeQuery=true)
	public List<Factura> buscarFacturasEmpresas(@Param("empresa") Integer empresa);
	
	@Query(value="SELECT * FROM T_FACTURA WHERE ID =:id",nativeQuery=true)
	public Factura buscarId(@Param("id") Long id);
	
	
}
