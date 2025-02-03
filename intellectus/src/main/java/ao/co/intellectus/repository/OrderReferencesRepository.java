package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.OrderReference;

public interface OrderReferencesRepository extends JpaRepository<OrderReference, String> {

	@Query(value="SELECT * FROM V_Order_References WHERE INVOICE_NO =:invoiceNo",nativeQuery=true)
	public List<OrderReference> buscarOrderReference(@Param("invoiceNo") String invoiceNo);
}
