/*package ao.co.intellectus.config.tarefas;

import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ao.co.intellectus.model.NotaCredito;
import ao.co.intellectus.repository.NotaCreditoRepository;
import ao.co.intellectus.servico.GerarGuiaService;

@Singleton
@Component
@EnableScheduling
public class ProcessamentoNotaCredito {

	private @Autowired NotaCreditoRepository notaCreditoRepository;
	private @Autowired GerarGuiaService gerarGuiaService;
	
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	
	
	@Scheduled(fixedDelay = MINUTO * 1)
	public void validaPagamentoGuia() {
		
		List<NotaCredito> notasDeCredito = this.notaCreditoRepository.BUSCAR_NOTA_CREDITO_HASH();
		
		for (NotaCredito notaCredito : notasDeCredito) {
			this.gerarGuiaService.gerarFileNotaCredito(notaCredito);
		}
		
	}
}
*/