package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.DocumentTotals;

public interface DocumentTotalsRepository extends JpaRepository<DocumentTotals, String> {

	@Query(value="SELECT * FROM V_Document_Total_LT WHERE INVOICE_NO =:invoiceNo",nativeQuery=true)
	public List<DocumentTotals> buscarDocumentTotals(@Param("invoiceNo") String invoiceNo);
	
	//@Query(value="SELECT * FROM V_WORKING_Document_Total WHERE DOCUMENT_NUMBER =:documentNumber",nativeQuery=true)
	//public List<DocumentTotals> buscarWorkDocumentTotals(@Param("documentNumber") String documentNumber);
	
	//@Query(value="SELECT * FROM V_Payment_Document_Total WHERE PAYMENT_REF_NO =:payment_ref_no",nativeQuery=true)
	//public List<DocumentTotals> buscarPaymentDocumentTotals(@Param("payment_ref_no") String payment_ref_no);
}
