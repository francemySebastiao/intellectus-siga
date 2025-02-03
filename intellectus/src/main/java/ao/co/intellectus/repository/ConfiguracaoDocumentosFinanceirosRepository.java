package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.ConfiguracaoDocumentosFinanceiros;

public interface ConfiguracaoDocumentosFinanceirosRepository extends JpaRepository<ConfiguracaoDocumentosFinanceiros, Integer> {
	
	public List<ConfiguracaoDocumentosFinanceiros> findByCodigoAndEnviado(Integer codigo,boolean enviado);

}
