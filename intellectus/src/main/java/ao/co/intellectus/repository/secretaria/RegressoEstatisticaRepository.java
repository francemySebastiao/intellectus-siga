package ao.co.intellectus.repository.secretaria;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.secretaria.RegressoEstatistica;

public interface RegressoEstatisticaRepository extends JpaRepository<RegressoEstatistica, Integer> {

	Long countByDataBeforeAndEnviada(Date data, Boolean enviada);

	List<RegressoEstatistica> findByDataBeforeAndEnviada(Date data, Boolean enviada);

	List<RegressoEstatistica> findByDataBefore(Date data);

}
