package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.GuiaMultaValor;
import ao.co.intellectus.model.GuiaPlanoMulta;

public interface GuiaPlanoMultaRepository extends CrudRepository<GuiaPlanoMulta,Integer>{
	public List<GuiaPlanoMulta> findByGuiaMultaValor(GuiaMultaValor guiaMultaValor);
}
