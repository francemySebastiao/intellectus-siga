package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.WorkDocument;

public interface WorkDocumentRepository extends JpaRepository<WorkDocument, String> {

	@Query(value="SELECT *, CAST(substring(Document_Number, CHARINDEX('/', Document_Number)+1,len(Document_Number)) AS INT) as n_documento FROM V_workDocument WHERE DATA_EMISSAO BETWEEN :data1 AND :data2 ORDER BY n_documento ASC",nativeQuery=true)
	public List<WorkDocument> buscarWorkDocument(@Param("data1") String data1, @Param("data2") String data2);
}
