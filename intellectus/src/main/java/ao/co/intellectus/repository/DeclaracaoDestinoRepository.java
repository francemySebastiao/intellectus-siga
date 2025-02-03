package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.DeclaracaoDestino;

public interface DeclaracaoDestinoRepository extends CrudRepository<DeclaracaoDestino,Integer>{
	
	public DeclaracaoDestino findByDescricao(String descricao);
	
	public List<DeclaracaoDestino> findByDescricaoLike(String descricao);
}
