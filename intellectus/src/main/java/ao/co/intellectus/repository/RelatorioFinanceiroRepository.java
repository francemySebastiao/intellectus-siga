package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.RelatorioFinanceiro;
import ao.co.intellectus.model.enumeracao.TipoRelatorio;

public interface RelatorioFinanceiroRepository extends JpaRepository<RelatorioFinanceiro, Integer>{
    public List<RelatorioFinanceiro> findByProximo(boolean proximo);
    public List<RelatorioFinanceiro> findByProximoAndTipoRelatorio(boolean proximo,TipoRelatorio tipoRelatorio);
}
