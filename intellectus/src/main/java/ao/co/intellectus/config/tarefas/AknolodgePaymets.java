/*
package ao.co.intellectus.config.tarefas;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mashape.unirest.http.Unirest;

import ao.co.intellectus.DTO.proxpay.Pagamentos;
import ao.co.intellectus.DTO.proxpay.RetornoPagamentos;
import ao.co.intellectus.model.PagamentosRemoto;
import ao.co.intellectus.repository.PagamentosRemotoRepository;
import ao.co.intellectus.servico.cafold.VerifiqueInternet;


@Singleton
@Component
@EnableScheduling
public class AknolodgePaymets {
	private RestTemplate rest;
	@Autowired
	private PagamentosRemotoRepository pagamentosRemotoRepository;

	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	
	@Scheduled(fixedDelay=MINUTO*5)
	public void validaPagamentoGuia() throws UnknownHostException {
		boolean connect = VerifiqueInternet.connect("https://www.google.com/");
		if (connect) {
			String uris = "https://api.proxypay.co.ao/events/payments";
			rest=new RestTemplate();
			rest.getInterceptors().add(new BasicAuthorizationInterceptor("api", "un5kodf4euv5s13b6i22qf0bkks1mosn"));
			ResponseEntity<RetornoPagamentos> recebido = rest.getForEntity(uris, RetornoPagamentos.class);
			RetornoPagamentos pagaBody = recebido.getBody();
			List<Pagamentos> pagamentos = pagaBody.getPayments();

			for (Pagamentos o : pagamentos) {
				PagamentosRemoto pagamento=new PagamentosRemoto();
				pagamento.setBordereux(o.getId());
				pagamento.setCompensado(false);
				pagamento.setDataExportado(new Date().toString()); 
				pagamento.setDataPagmento(o.getDatetime());
				pagamento.setNumeroGuia(o.getCustom_fields().getGuia());
				pagamento.setReferencia(o.getReference_number().toString());
				pagamento.setValor(new BigDecimal(o.getAmount()));
				pagamento.setNumeroAluno(o.getCustom_fields().getNumeroDeAluno());
				pagamento.setTelefone(o.getCustom_fields().getMobile());
				pagamento.setUnidade(o.getCustom_fields().getUnidade());
				PagamentosRemoto save = this.pagamentosRemotoRepository.save(pagamento);
				if(save!=null) {
					String delete="https://api.proxypay.co.ao/events/payments/"+o.getId();
					Unirest.delete(delete)
					.basicAuth("api", "un5kodf4euv5s13b6i22qf0bkks1mosn")
					.header("accept", "application/vnd.proxypay.v1+json") 
					.header("Content-Type", "application/json"); 		
					rest.delete(delete);
				}
			}
		}
	}
} 
*/