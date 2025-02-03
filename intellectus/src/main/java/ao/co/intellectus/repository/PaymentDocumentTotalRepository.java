package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.PaymentDocumentTotals;

public interface PaymentDocumentTotalRepository extends JpaRepository<PaymentDocumentTotals, String> {

	@Query(value="SELECT * FROM V_Payment_Document_Total WHERE PAYMENT_REF_NO =:payment_ref_no",nativeQuery=true)
	public List<PaymentDocumentTotals> buscarPaymentDocumentTotals(@Param("payment_ref_no") String payment_ref_no);
}
