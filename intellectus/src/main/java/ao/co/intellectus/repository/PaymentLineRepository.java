package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.PaymentLine;

public interface PaymentLineRepository extends JpaRepository<PaymentLine, String> {

	@Query(value="SELECT * FROM V_Payment_LINE WHERE PAYMENT_REF_NO =:paymentRefNo",nativeQuery=true)
	public List<PaymentLine> buscarPaymentLine(@Param("paymentRefNo") String paymentRefNo);
}
