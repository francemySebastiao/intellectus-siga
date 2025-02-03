package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.WorkDocumentsStatus;

public interface WorkDocumentStatusRepository extends JpaRepository<WorkDocumentsStatus, String> {

	@Query(value="SELECT * FROM V_WORKING_Document_Status WHERE DOCUMENT_NUMBER =:documentNumber ",nativeQuery=true)
	public List<WorkDocumentsStatus> buscarWorkDocumentStatus(@Param("documentNumber") String documentNumber);
}
