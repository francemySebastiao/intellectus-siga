package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.GuiaPagamentoHistorico;

public interface GuiaPagamentoHistoricoRepository extends JpaRepository<GuiaPagamentoHistorico, Integer> {

	@Query(value="SELECT * FROM T_GUIA_PAGAMENTO_HISTORICO WHERE CODIGO_GUIA_PAGAMENTO =:guia",nativeQuery=true)
	public List<GuiaPagamentoHistorico> buscarIdGuia(@Param("guia") Integer guia);
}
