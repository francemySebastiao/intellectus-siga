package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.Reference;

public interface ReferencesRepository extends JpaRepository<Reference, String> {

	@Query(value="SELECT * FROM V_REFERENCES WHERE INVOICE_NO =:invoiceNo",nativeQuery=true)
	public List<Reference> buscarReferences(@Param("invoiceNo") String invoiceNo);
}
