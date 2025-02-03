package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Tax;

public interface TaxRepository extends JpaRepository<Tax, String> {

	@Query(value="SELECT * FROM V_TAX WHERE INVOICE_NO =:invoiceNo AND LINE_NUMBER =:lineNumber",nativeQuery=true)
	public List<Tax> buscarTax(@Param("invoiceNo") String invoiceNo, @Param("lineNumber") Integer lineNumber);
	
	//@Query(value="SELECT * FROM V_WORKING_Tax WHERE DOCUMENT_NUMBER =:documentNumber AND LINE_NUMBER =:lineNumber",nativeQuery=true)
	//public List<Tax> buscarWorkTax(@Param("documentNumber") String documentNumber, @Param("lineNumber") Integer lineNumber);
}
