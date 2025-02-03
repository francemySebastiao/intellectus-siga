package ao.co.intellectus.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.ListagemEmolumento;

public interface ListatagemEmolumentoRespository extends JpaRepository<ListagemEmolumento,String> {
	List<ListagemEmolumento> findByDataLiquidacaoBetween(Date inicio, Date fim);
	List<ListagemEmolumento> findByAnoLectivoAndMesLiquidacaoInteiro(Integer anoLectivo, Integer mesLiquidacao);
}
