package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ao.co.intellectus.model.TaxTable;

public interface TaxTableRepository extends JpaRepository<TaxTable, String> {

	@Query(value="SELECT * FROM V_TAXTABLE",nativeQuery=true)
	public List<TaxTable> buscarTaxTable();
}
