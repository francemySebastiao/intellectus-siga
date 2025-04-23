package ao.co.intellectus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.NotaCreditoDetalhe;

public interface NotaCreditoDetalheRepository extends JpaRepository<NotaCreditoDetalhe, Long> {

	@Query(value="SELECT * FROM T_NOTA_CREDITO_DETELHE WHERE N_NOTA_CREDITO =:notaCredito",nativeQuery=true)
	public List<NotaCreditoDetalhe> buscarNumeroNotaCredito(@Param("notaCredito") String notaCredito);
}
