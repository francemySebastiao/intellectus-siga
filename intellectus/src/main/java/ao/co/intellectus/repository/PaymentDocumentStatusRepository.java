package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.PaymentDocumentStatus;

public interface PaymentDocumentStatusRepository extends JpaRepository<PaymentDocumentStatus, String> {

	@Query(value="SELECT * FROM V_Payment_Document_Status WHERE PAYMENT_REF_NO =:paymentRefNo ",nativeQuery=true)
	public List<PaymentDocumentStatus> buscarPaymentDocumentStatus(@Param("paymentRefNo") String paymentRefNo);
}
