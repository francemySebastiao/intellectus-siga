package ao.co.intellectus.servico.notificacoes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.alerta.Campanha;
import ao.co.intellectus.model.alerta.DestinatarioCampanha;
import ao.co.intellectus.repository.alerta.DestinatarioCampanhaRespository;

@Service
public class DestinatarioCampanhaService {

	@Autowired
	private DestinatarioCampanhaRespository destinatarioCampanhaRespository;

	public void salvar(DestinatarioCampanha destinatarioCampanha) {
		this.destinatarioCampanhaRespository.save(destinatarioCampanha);
	}

	public List<DestinatarioCampanha> destinariosPorCampanha(Campanha campanha) {
		return this.destinatarioCampanhaRespository.findByCampanhaAndEnviada(campanha, false);
	}
	
	public List<DestinatarioCampanha> destinatariosCampanha() {
		List<DestinatarioCampanha> destinatariosCampanha = this.destinatarioCampanhaRespository.findAll();
		return destinatariosCampanha;
	}

	public List<DestinatarioCampanha> destinatariosCampanha(List<Integer> destinarios) {
		List<DestinatarioCampanha> destinatariosCampanha = new ArrayList<DestinatarioCampanha>();
		for (Integer destinatario : destinarios) {
			DestinatarioCampanha destinatarioCampanha = this.destinatarioCampanhaRespository.findByIdAndEnviada(destinatario, false);
			destinatariosCampanha.add(destinatarioCampanha);
		}
		return destinatariosCampanha;
	}

}
