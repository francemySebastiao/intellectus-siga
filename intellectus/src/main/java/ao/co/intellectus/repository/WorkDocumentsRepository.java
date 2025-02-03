package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.WorkingDocuments;

public interface WorkDocumentsRepository extends JpaRepository<WorkingDocuments, String> {

	@Query(value="SELECT *, CAST(substring(N_FACTURA, CHARINDEX('/', N_FACTURA)+1,len(N_FACTURA)) AS INT) as n_documento FROM V_WorkingDocuments WHERE DATA_EMISSAO BETWEEN :data1 AND :data2 ORDER BY n_documento asc",nativeQuery=true)
	public List<WorkingDocuments> buscarWorkDocuments(@Param("data1") String data1, @Param("data2") String data2);
}
