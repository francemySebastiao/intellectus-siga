package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.LogSaft;

public interface LogSaftRepository extends JpaRepository<LogSaft, Integer> {

	@Query(value="SELECT * FROM T_LOG_SAFT WHERE START_DATE =:data1",nativeQuery=true)
	public LogSaft buscarStartDate(@Param("data1") String data1);
}
