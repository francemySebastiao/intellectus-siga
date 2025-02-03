package ao.co.intellectus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ao.co.intellectus.model.ListagemBancaria;

public interface ListagemBancariaRepository extends CrudRepository<ListagemBancaria,String> {
	
	List<ListagemBancaria> findByDataLiquidacaoBetween(Date inicio, Date fim);
	List<ListagemBancaria> findByAnoLectivoAndMesLiquidacaoInteiro(Integer anoLectivo, Integer mesLiquidacao);
	
}
