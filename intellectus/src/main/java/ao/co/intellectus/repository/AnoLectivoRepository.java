package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.AnoLectivo;

public interface AnoLectivoRepository extends CrudRepository<AnoLectivo,Integer>{
	public List<AnoLectivo> findByStatus(boolean status);
	//  List<Person> findByLastnameOrderByFirstnameDesc(String lastname);
	public List<AnoLectivo> findByStatusOrderByAnoLectivoDesc(boolean status);
	public List<AnoLectivo> findByOrderByAnoLectivoDesc();
	
	@Query(value="SELECT * FROM T_ANO_LECTIVO WHERE STATUS = 1",nativeQuery=true)
	public AnoLectivo buscarPorEstado();
	
	public AnoLectivo findByAnoLectivo(Integer anoLectivo);
} 