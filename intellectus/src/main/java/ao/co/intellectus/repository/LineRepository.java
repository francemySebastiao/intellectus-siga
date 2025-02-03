package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Line;

public interface LineRepository extends JpaRepository<Line, String> {

	@Query(value="SELECT * FROM LINE_LT WHERE INVOICE_NO =:invoiceNo",nativeQuery=true)
	public List<Line> buscarLine(@Param("invoiceNo") String invoiceNo);
	
	//@Query(value="SELECT * FROM V_WORKING_LINE WHERE DOCUMENT_NUMBER =:documentNumber",nativeQuery=true)
	//public List<Line> buscarWorkLine(@Param("documentNumber") String documentNumber);
	
	//@Query(value="SELECT * FROM V_Payment_LINE WHERE PAYMENT_REF_NO =:paymentRefNo",nativeQuery=true)
	//public List<Line> buscarPaymentLine(@Param("paymentRefNo") String paymentRefNo);
}
