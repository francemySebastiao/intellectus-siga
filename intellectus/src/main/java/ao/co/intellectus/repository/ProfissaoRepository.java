package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.Profissao;

public interface ProfissaoRepository extends CrudRepository<Profissao,Integer>{
	public Profissao findByDescricao(String descricao);
}
