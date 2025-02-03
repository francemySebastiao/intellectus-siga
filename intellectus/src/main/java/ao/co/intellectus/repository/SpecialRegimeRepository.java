package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.SpecialRegimes;

public interface SpecialRegimeRepository extends JpaRepository<SpecialRegimes, String> {

	@Query(value="SELECT * FROM V_Special_Regimes WHERE INVOICE_NO =:invoiceNo ",nativeQuery=true)
	public List<SpecialRegimes> buscarSpecialRegimes(@Param("invoiceNo") String invoiceNo);
}
