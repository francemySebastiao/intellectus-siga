package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Bolsa;

public interface BolsaRepository extends CrudRepository<Bolsa, Integer>{
	
	public Bolsa findByCodigoOrDescricao(String codigo, String descricao);

}
