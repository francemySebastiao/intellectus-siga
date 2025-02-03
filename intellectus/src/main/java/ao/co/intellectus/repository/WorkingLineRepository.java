package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.WorkingLine;

public interface WorkingLineRepository extends JpaRepository<WorkingLine, String> {

	@Query(value="SELECT * FROM V_WORKING_LINE WHERE DOCUMENT_NUMBER =:documentNumber",nativeQuery=true)
	public List<WorkingLine> buscarWorkingLine(@Param("documentNumber") String documentNumber);
}
