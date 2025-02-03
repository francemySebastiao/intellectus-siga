package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.TipoDeDeclaracao;

public interface TipoDeDeclaracaoRepository extends CrudRepository<TipoDeDeclaracao,Integer>{
	public TipoDeDeclaracao findById(Integer codigo);
	public TipoDeDeclaracao findByDescricao(String descricao);
	public List<TipoDeDeclaracao> findByStatus(boolean estado);
}
