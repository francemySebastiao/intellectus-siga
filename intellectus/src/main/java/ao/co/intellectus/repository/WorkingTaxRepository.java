package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.WorkingTax;

public interface WorkingTaxRepository extends JpaRepository<WorkingTax, String> {

	@Query(value="SELECT * FROM V_WORKING_Tax WHERE DOCUMENT_NUMBER =:documentNumber AND LINE_NUMBER =:lineNumber",nativeQuery=true)
	public List<WorkingTax> buscarWorkingTax(@Param("documentNumber") String documentNumber, @Param("lineNumber") Integer lineNumber);
}
