package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.PlanoPagamento;

public interface PlanoPagamentoRepository extends CrudRepository<PlanoPagamento,Integer>{
	public PlanoPagamento findByDescricao(String descricao);
	
}
