package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ao.co.intellectus.model.TaxTableEntry;

public interface TaxTableEntryRepository extends JpaRepository<TaxTableEntry, String>{

	@Query(value="SELECT * FROM V_TAX_TABLE_ENTRY",nativeQuery=true)
	public List<TaxTableEntry> buscarTaxTableEntry();
}
