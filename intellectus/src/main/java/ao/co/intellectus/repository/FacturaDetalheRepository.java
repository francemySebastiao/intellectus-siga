package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.FacturaDetalhe;

public interface FacturaDetalheRepository extends JpaRepository<FacturaDetalhe, Long> {

	@Query(value="SELECT * FROM T_FACTURA_DETALHE WHERE ID_FACTURA =:factura",nativeQuery=true)
	public List<FacturaDetalhe> buscarIdFactura(@Param("factura") Long factura);
}
