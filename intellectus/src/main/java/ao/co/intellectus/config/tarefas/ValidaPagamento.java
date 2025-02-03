/*
package ao.co.intellectus.config.tarefas;

import java.math.BigDecimal;
import java.net.InetAddress;
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
import ao.co.intellectus.model.Banco;
import ao.co.intellectus.model.Bordero;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.repository.BancoRepository;
import ao.co.intellectus.repository.BorderoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.cafold.VerifiqueInternet;

@Singleton
@Component
@EnableScheduling
public class ValidaPagamento {
	private RestTemplate rest;
	//@Autowired
	//private CompensassaoProvisoriaRepository compensassaoRepository;
	@Autowired
	private GuiaPagamentoRepository guiaPagamentoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private BorderoRepository borderoRepository;
	@Autowired
	private BancoRepository bancoRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepository;
	
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	
	@Scheduled(fixedDelay=MINUTO*7)
	public void validaPagamentoGuia() throws UnknownHostException {
    	this.rest=new RestTemplate();
    	
    	String hostName = InetAddress.getLocalHost().getHostName();
    	  	 
    	boolean connect = VerifiqueInternet.connect("https://www.google.com/");
    	
    	if(connect) {
    		String uris="https://api.proxypay.co.ao/events/payments";
    		
    		Instituicao instituicao = this.instituicaoRepository.findOne(2);
    		
    		Usuario usuario = this.usuarioRepository.findByUserName("proxpay");
    		
    		Banco banco = this.bancoRepository.findByBanco("Pagamentos Rerefência");
    	
    		rest.getInterceptors().add(new BasicAuthorizationInterceptor("api","un5kodf4euv5s13b6i22qf0bkks1mosn"));
    		ResponseEntity<RetornoPagamentos> recebido = rest.getForEntity(uris, RetornoPagamentos.class);
    		RetornoPagamentos pagaBody                 = recebido.getBody();
    		List<Pagamentos> pagamentos                = pagaBody.getPayments();
    		
    		//System.err.println("TOTAL GUIAS: "+pagamentos.size());
    		
    		for (Pagamentos p : pagamentos) { 
    			
    			//p.getCustom_fields().getUnidade().equals(instituicao.getCode())
    			
    			if(p.getCustom_fields().getUnidade()!=null) {
    				
    				if(instituicao.getCode().equals(p.getCustom_fields().getUnidade())) {
    					//System.err.println("A UNIDADE EM QUESTÃO É: "+p.getCustom_fields().getUnidade());  
    					compensaReferencia(usuario, banco, p);
    				}
    			}else {
    				if(instituicao.getCode().equals("0010") || "UGS".equals(p.getCustom_fields().getUnidade())) { 
    					//System.err.println("A UNIDADE EM QUESTÃO É: "+p.getCustom_fields().getUnidade()+" - INTELLECTUS - UGS");    	
    					compensaReferencia(usuario, banco, p);
    				}
    			}
    		}
    	}else { 
    		System.err.println("Verificando o  sinal de Internet... NAME SERVER:"+hostName);	
    	}	
	}

	private void compensaReferencia(Usuario usuario, Banco banco, Pagamentos p) {
		//List<Guia> guia = this.guiaPagamentoRepository.findByNumeroGuiaOrderByIdAsc(p.getCustom_fields().getGuia());
		List<Guia> guia = this.guiaPagamentoRepository.findByNumeroGuiaOrderByIdAsc(p.getCustom_fields().getGuia());
		
		//System.err.println("GUIA PAGAMENTO "+p.getCustom_fields().getGuia()); 
		
		if(!guia.isEmpty()) {
			//&& guia.get(0).getAluno().getNome().equals(p.getCustom_fields().getName())
			if(!guia.get(0).isLiquidada() && guia.get(0).getValor()==p.getAmount()) {
				
				guia.get(0).setReferencia(p.getReference_number().toString());
				guia.get(0).setDataLiquidacao(new Date());
				guia.get(0).setBordero(p.getReference_number().toString());
				guia.get(0).setUsuarioLiquidou(usuario);
				guia.get(0).setLiquidada(true); 
				
				//System.err.println("GPG "+p.getCustom_fields().getGuia()); 
				
				guia.get(0).setAcordo(false);
				guia.get(0).setAnulada(false);
				guia.get(0).setGerouCredito(false);
				guia.get(0).setGeradaReferencia(false);
				guia.get(0).setGeradaOnline(false);
				Guia gPaga = this.guiaPagamentoRepository.save(guia.get(0));
				
				Bordero bordero=new Bordero();
				
				bordero.setAluno(gPaga.getAluno());
				bordero.setBanco(banco);
				bordero.setDataDeposito(new Date());
				bordero.setDataRegistro(new Date());
				bordero.setGuia(gPaga);
				bordero.setNumero(p.getReference_number().toString());
				bordero.setMoeda(banco.getMoeda());
				
				this.borderoRepository.save(bordero);					
				
				if(guia.get(0)!=null) {
					String delete="https://api.proxypay.co.ao/events/payments/"+p.getId();
					Unirest.delete(delete)
					.basicAuth("api", "un5kodf4euv5s13b6i22qf0bkks1mosn")
					.header("accept", "application/vnd.proxypay.v1+json") 
					.header("Content-Type", "application/json"); 
					
					
					rest.delete(delete);	
					System.err.println("Comepnsassão validada com sucesso....");	
				}	   
			}
		}
		
		//CANDIDATURA
		List<GuiaCandidatura> guiasCandidatura = this.guiaCandidaturaRepository.buscarGuiaParaComepensarAGORA(p.getCustom_fields().getGuia(),new BigDecimal(p.getAmount()));
		for (GuiaCandidatura u : guiasCandidatura) {
			u.setBordero(p.getReference_id());
			u.setDataLiquidacao(new Date());
			u.setLiquidada(true);
			this.guiaCandidaturaRepository.save(u);
			
			String delete="https://api.proxypay.co.ao/events/payments/"+p.getId();
			Unirest.delete(delete)
			.basicAuth("api", "un5kodf4euv5s13b6i22qf0bkks1mosn")
			.header("accept", "application/vnd.proxypay.v1+json") 
			.header("Content-Type", "application/json"); 
			
			
			rest.delete(delete);	
			System.err.println("Comepnsassão validada com sucesso....");	
		}
	}
} */

//public class ValidaPagamento{}
