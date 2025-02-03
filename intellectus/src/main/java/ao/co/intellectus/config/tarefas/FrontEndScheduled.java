package ao.co.intellectus.config.tarefas;
/*
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;

import javax.inject.Singleton;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.servico.cafold.EmailService;
import ao.co.intellectus.servico.cafold.RedeService;
import ao.co.intellectus.servico.cafold.SistemaOperativoService;

@Singleton
@Component
@EnableScheduling
public class FrontEndScheduled {

	@Autowired
	private EmailService emailService;
	@Autowired
	private SistemaOperativoService sistemaOperativoService;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private RedeService redeService;
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	private String assunto, mensagem;
	
	@Scheduled(fixedDelay=MINUTO*1440)
	public void iniciar() throws UnknownHostException {
		Instituicao instituicao = this.instituicaoRepository.findOne(2);
		
		try {
			if(instituicao == null) {
				this.assunto = "Erro: SIGA";
				this.mensagem = "Houve um erro ao validar a instituição."
							  + "\nPor favor validar com o pessoal de desenvolvimento."
							  + "\nHost: "+this.redeService.getNomeHost()
							  + "\nData: "+new Date();
				this.emailService.reportarFalha(this.assunto, this.mensagem);
			}else {
				try {
					if(sistemaOperativoService.servicoEstaEmExecucao("nginx.exe")) {
						
					}else {
						Runtime.getRuntime().exec("cmd.exe /c cd c:\\"
								+ "&& cd NgNix\\nginx-1"
								+ "&& start nginx.exe"
								+ "&& cd c:\\"
								+ "&& cd NgNix\\nginx-2"
								+ "&& start nginx.exe"
								+ "&& cd c:\\"
								+ "&& cd API_Build "
								+ "&& start npm start");
					
						this.mensagemDeSucesso(instituicao);
					}
				} catch (IOException erro) {
					this.assunto = "Erro: SIGA - "+instituicao.getDescricao()+"";
					this.mensagem = "Houve um erro ao iniciar o Front-End. Por favor, validar com o pessoal de desenvolvimento."
							+ "\nCódigo do erro: "+erro.toString()
							+ "\nHost: "+this.redeService.getNomeHost()
							+ "\nData: "+new Date();
					this.emailService.reportarFalha(this.assunto, this.mensagem);
				}				
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	private void mensagemDeSucesso(Instituicao instituicao) throws MessagingException, UnknownHostException {
		String host = this.redeService.getNomeHost();
		if((!host.equalsIgnoreCase("NTINT05")) && (!host.equalsIgnoreCase("NTINT03")) && (!host.equalsIgnoreCase("cr1.southafricanorth1-a.control.database.windows.net"))) {
			this.assunto = "SIGA - "+instituicao.getDescricao()+"";
			
			String codigo = instituicao.getCode();
			String unidade="";
			if(codigo.equals("0010")) {
				unidade="Universidade Gregório Semedo";
			}
			
			if(codigo.equals("0020")) {
				unidade="Instituto Superior Politécnico Gregório Semedo - LUBANGO";
			}

			if(codigo.equals("0030")) {
				unidade="Instituto Superior Politécnico Gregório Semedo - LUBANGO";
			}
			
			
			
			this.mensagem = "O SIGA foi inicializado com sucesso. "
					+"\n\n=========Dados do Computador======="
					+"\nHost: "+this.redeService.getNomeHost()
					+"\nUsuário: "+this.redeService.getUsuario()
					+"\n   IP: "+this.redeService.getIp() 
					+"\nData: "+new Date()
			        +"\nUNIDADE: "+unidade;
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			
			
		} 
	}
	
}
*/