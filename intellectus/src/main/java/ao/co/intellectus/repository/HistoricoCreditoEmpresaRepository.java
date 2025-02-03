package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.HistoricoCreditoEmpresa;

public interface HistoricoCreditoEmpresaRepository extends JpaRepository<HistoricoCreditoEmpresa, Integer>{

	@Query(value="SELECT * from T_HISTORICO_CREDITO_EMPRESA WHERE n_factura_recibo =:recibo", nativeQuery=true)
	public HistoricoCreditoEmpresa findFacturaRecibo(@Param("recibo") String recibo);
	
	public HistoricoCreditoEmpresa findByBorderoInterno(String borderoInterno);
}
