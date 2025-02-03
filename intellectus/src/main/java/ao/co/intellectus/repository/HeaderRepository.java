package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ao.co.intellectus.model.Header;

public interface HeaderRepository extends JpaRepository<Header, String> {

	@Query(value="SELECT * FROM V_HEADER",nativeQuery=true)
	public Header buscarHeader();
}
