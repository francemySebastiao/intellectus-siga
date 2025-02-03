package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Intervalo;

public interface IntervaloRepository extends CrudRepository<Intervalo,Integer>{
	public List<Intervalo> findByDiaSemana(String diaSemana);
}
