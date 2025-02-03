package ao.co.intellectus.config.tarefas;

import java.util.List;

import javax.inject.Singleton;

import ao.co.intellectus.DTO.GuiaPagamentoCodigo;
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
public class ProcessamentoFacturaRecibo {

	private @Autowired GuiaPagamentoRepository guiaPagamentoRepository;
	private @Autowired GerarGuiaService gerarGuiaService;

	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;


	public  Guia conversaoGuia(GuiaPagamentoCodigo g){
		Guia  guia = new Guia();
		guia.setId(g.getId());
		guia.setDataLiquidacao(g.getDataLiquidacao());
		guia.setDataEmissaoFr(g.getDataEmissaoFr());
		guia.setHashFacturaRecibo(g.getHashFacturaRecibo());
		guia.setNumeroFacturaRecibo(g.getNumeroFacturaRecibo());
		guia.setNumeroGuia(g.getNumeroGuia());
		return guia;
	}

	@Scheduled(fixedDelay = MINUTO * 1)
	public void validaPagamentoGuia() {
		
		List<GuiaPagamentoCodigo> guiasPagamento = this.guiaPagamentoRepository.BUSCAR_GUIA_SEM_FACTURA_RECIBO_HASH();
		
		for (GuiaPagamentoCodigo guiad : guiasPagamento) {
			Guia  guia = conversaoGuia(guiad);
			this.gerarGuiaService.gerarFileNumeroFacturaRecibo(guia);
		}
	}
}
