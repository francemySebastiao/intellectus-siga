package ao.co.intellectus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ao.co.intellectus.model.NotaCreditoDetalhe;

public interface NotaCreditoDetalheRepository extends JpaRepository<NotaCreditoDetalhe, Long> {

	@Query(value="SELECT * FROM T_NOTA_CREDITO_DATELHE WHERE N_NOTA_CREDITO =:notaCredito",nativeQuery=true)
	public NotaCreditoDetalhe buscarNumeroNotaCredito(@Param("notaCredito") String notaCredito);
}
