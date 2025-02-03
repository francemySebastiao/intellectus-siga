package ao.co.intellectus.repository.alerta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ao.co.intellectus.model.alerta.Campanha;
import ao.co.intellectus.model.alerta.DestinatarioCampanha;

public interface DestinatarioCampanhaRespository extends JpaRepository<DestinatarioCampanha, Integer> {

	DestinatarioCampanha findByIdAndEnviada(Integer destinatario, boolean enviada);
	List<DestinatarioCampanha> findByCampanhaAndEnviada(Campanha campanha, boolean enviada);
	

}
