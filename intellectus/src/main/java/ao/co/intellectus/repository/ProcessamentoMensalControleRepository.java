package ao.co.intellectus.repository;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.ProcessamentoMensalControle;

public interface ProcessamentoMensalControleRepository extends CrudRepository<ProcessamentoMensalControle,Integer>{
	public ProcessamentoMensalControle findByAnoLectivoAndMes(Integer anoLectivo,String mes);
}
