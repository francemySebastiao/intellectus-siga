package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.SalesInvoices;

public interface SalesInvoiceRepository extends JpaRepository<SalesInvoices, String> {

	@Query(value="SELECT * FROM V_SALES_INVOICE WHERE DATA_EMISSAO BETWEEN :data1 AND :data2",nativeQuery=true)
	public List<SalesInvoices> buscarSalesInvoces(@Param("data1") String data1, @Param("data2") String data2);
	
	@Query(value="SELECT * FROM V_SALES_INVOICE WHERE DATA_EMISSAO BETWEEN :data1 AND :data2 AND ANULADO = 0",nativeQuery=true)
	public List<SalesInvoices> buscarDebits(@Param("data1") String data1, @Param("data2") String data2);
}
