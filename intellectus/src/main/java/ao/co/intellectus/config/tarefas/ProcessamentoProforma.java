/*package ao.co.intellectus.config.tarefas;

import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ao.co.intellectus.model.Guia;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.servico.GerarGuiaService;

@Singleton
@Component
@EnableScheduling
public class ProcessamentoProforma {
	
	private @Autowired GuiaPagamentoRepository guiaPagamentoRepository;
	private @Autowired GerarGuiaService gerarGuiaService;
	
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	
	
	@Scheduled(fixedDelay = MINUTO * 1)
	public void validaPagamentoGuia() {
		
		List<Guia> guiasPagamento = this.guiaPagamentoRepository.BUSCAR_GUIA_SEM_PROFORMA_HASH();
		
		for (Guia guia : guiasPagamento) {
			this.gerarGuiaService.gerarFileNumeroProforma(guia);
		}
		
	}

}
*/