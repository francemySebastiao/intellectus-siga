package ao.co.intellectus.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Factura;
import ao.co.intellectus.model.NotaCredito;

public interface NotaCreditoRepository extends JpaRepository<NotaCredito, Long> {

	@Query(value="SELECT * FROM T_NOTA_CREDITO WHERE N_NOTA_CREDITO =:notaCredito",nativeQuery=true)
	public NotaCredito buscarNumeroNotaCredito(@Param("notaCredito") String notaCredito);
	
	@Query(value="SELECT * FROM T_NOTA_CREDITO WHERE ID_FACTURA =:factura",nativeQuery=true)
	public NotaCredito buscarFactura(@Param("factura") Long factura);
	
	@Query(value="SELECT * FROM T_NOTA_CREDITO WHERE ID_GUIA_PAGAMENTO =:guia",nativeQuery=true)
	public NotaCredito buscarGuiaPagamento(@Param("guia") Long guia);
	
	@Query(value="SELECT * FROM T_NOTA_CREDITO WHERE ID_FACTURA =:factura",nativeQuery=true)
	public List<NotaCredito> buscarNotaCredito(@Param("factura") Integer factura);
	
	@Query(value="SELECT * FROM V_PROG_PROCESSAMENTO_NOTA_CREDITO", nativeQuery=true)
	public List<NotaCredito> BUSCAR_NOTA_CREDITO_HASH();
	
	@Query(value="SELECT SUM(VALOR) FROM T_NOTA_CREDITO WHERE DATA_EMISSAO BETWEEN :data1 AND :data2",nativeQuery=true)
	public Double  buscarDebits(@Param("data1") String data1, @Param("data2") String data2);
	
	@Query(value="SELECT * FROM T_NOTA_CREDITO WHERE DATA_EMISSAO BETWEEN :data1 AND :data2", nativeQuery=true)
	public List<NotaCredito> BUSCAR_NUMBER_OF_ENTRIES(@Param("data1") String data1, @Param("data2") String data2);
}
