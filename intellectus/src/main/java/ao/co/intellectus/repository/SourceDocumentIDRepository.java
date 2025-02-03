package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.SourceDocumentId;

public interface SourceDocumentIDRepository extends JpaRepository<SourceDocumentId, String> {

	@Query(value="SELECT * FROM V_Source_Document_ID WHERE PAYMENT_REF_NO =:payment_ref_no",nativeQuery=true)
	public List<SourceDocumentId> buscarSourceDocumentID(@Param("payment_ref_no") String payment_ref_no);
}
