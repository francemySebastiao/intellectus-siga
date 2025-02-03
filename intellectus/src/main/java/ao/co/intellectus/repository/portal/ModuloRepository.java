package ao.co.intellectus.repository.portal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.portal.Modulo;

public interface ModuloRepository extends JpaRepository<Modulo, Integer>{

	public Modulo findByName(String nome);
	public Modulo findByIdNotAndName(Integer id, String nome);
	

	public Modulo findById(Integer id);
	public List<Modulo> findByIdNotIn(List<Integer> todosId);
	
	// public List<Modulo> findByName(String nome);

}
