package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.WorkingDocumentTotals;

public interface WorkingDocumentTotalsRepository extends JpaRepository<WorkingDocumentTotals, String> {

	@Query(value="SELECT * FROM V_WORKING_Document_Total WHERE DOCUMENT_NUMBER =:documentNumber",nativeQuery=true)
	public List<WorkingDocumentTotals> buscarWorkingDocumentTotals(@Param("documentNumber") String documentNumber);
}
