package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.DocumentStatus;

public interface DocumentStatusRepository extends JpaRepository<DocumentStatus, String> {

	@Query(value="SELECT * FROM V_Document_Status WHERE INVOICE_NO =:invoiceNo ",nativeQuery=true)
	public List<DocumentStatus> buscarDocumentStatus(@Param("invoiceNo") String invoiceNo);
	
	//@Query(value="SELECT * FROM V_WORKING_Document_Status WHERE DOCUMENT_NUMBER =:documentNumber ",nativeQuery=true)
	//public List<DocumentStatus> buscarWorkDocumentStatus(@Param("documentNumber") String documentNumber);
	
	//@Query(value="SELECT * FROM V_Payment_Document_Status WHERE PAYMENT_REF_NO =:paymentRefNo ",nativeQuery=true)
	//public List<DocumentStatus> buscarPaymentDocumentStatus(@Param("paymentRefNo") String paymentRefNo);
}
