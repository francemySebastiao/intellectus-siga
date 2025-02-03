package ao.co.intellectus.servico.cafold;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import ao.co.intellectus.DTO.EmailCliente;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.exception.EmailException;

@Component
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private InstituicaoService instituicaoService;
	private final String USUARIO = "siga@intellectus.co.ao";

	public ResponseEntity<ResponseCliente> enviarGuiaLiquidada(String destinatario,String assunto,String mensagem, String tituloFicheiro  ,byte[] ficheiro){
	    try {
	    	MimeMessage message = this.javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(USUARIO);
	        helper.setBcc("dcf@ugs.ed.ao");
	        helper.setTo(destinatario);
	        helper.setSubject(assunto);
	        helper.setText(mensagem);
	        helper.addAttachment(tituloFicheiro+".pdf", new ByteArrayResource(ficheiro));
	        
	        this.javaMailSender.send(message);
	        return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Guia enviada com sucesso. Por favor, verifique o seu email.");
	    } catch (MessagingException e) {
	    	return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Erro ao enviar guia, tente novamente mais tarde.");
	    }
	}
	
	public void enviar(EmailCliente email){
	    try {
	    	MimeMessage message = this.javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        String tituloficheiro = null, extensao=null;
	        byte[] ficheiro = null;
	        if(email.getAnexo() != null) {
	        	tituloficheiro = email.getAnexo().getTitulo();
	        	extensao = email.getAnexo().getExtensao();
	        	ficheiro = email.getAnexo().getBinario();	        	
	        }
	        helper.setFrom(USUARIO);
	        helper.setSubject(email.getAssunto());
	        helper.setText(email.getMensagem());
	        helper.setTo(email.getDestinatario());
	        if(email.getCc() != null)
	        	helper.setCc(email.getCc());
	        if(email.getbCC()!= null)
	        	helper.setBcc(email.getbCC());
	        if(email.getAnexo().getBinario() != null)
	        	helper.addAttachment(tituloficheiro+extensao, new ByteArrayResource(ficheiro));
	      
	        
	        this.javaMailSender.send(message);
	    } catch (MessagingException e) {
	    	e.printStackTrace();
	    }
	}
	
	//TODO - ENVIAR FICHEIRO DE CANDIDATURA
	public void enviar(Candidato candidato,EmailCliente email) {
        try {
        	MimeMessage message = this.javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        String tituloficheiro = null, extensao=null;
	        byte[] ficheiro = null;
	        if(email.getAnexo() != null) {
	        	tituloficheiro = email.getAnexo().getTitulo();
	        	extensao = email.getAnexo().getExtensao();
	        	ficheiro = email.getAnexo().getBinario();	        	
	        }
	        helper.setFrom(USUARIO);
	        helper.setSubject(email.getAssunto());
	        helper.setText(email.getMensagem());
	        helper.setTo(email.getDestinatario());
	        if(email.getCc() != null)
	        	helper.setCc(email.getCc());
	        if(email.getbCC()!= null)
	        	helper.setBcc(email.getbCC());
	        if(email.getAnexo().getBinario() != null)
	        	helper.addAttachment(tituloficheiro+extensao, new ByteArrayResource(ficheiro));
	        if(candidato.getExame() != null) {
	        	topicos(candidato, helper);
	        }
			this.javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void enviar(EmailCliente email,byte[] listagemEmolumento,byte[] listagemBancaria) {
        try {
        	MimeMessage message = this.javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(USUARIO);
	        helper.setSubject(email.getAssunto());
	        helper.setText(email.getMensagem());
	        helper.setTo(email.getDestinatario());
	        if(email.getCc() != null)
	        	helper.setCc(email.getCc());
	        if(email.getbCC()!= null)
	        	helper.setBcc(email.getbCC());
	        if(listagemEmolumento != null)
	        	helper.addAttachment("LISTAGEM_EMOLUMENTO.xlsx", new ByteArrayResource(listagemEmolumento));
	        if(listagemBancaria != null)
	        	helper.addAttachment("LISTAGEM_BANCARIA.xlsx", new ByteArrayResource(listagemBancaria));
	        if(listagemEmolumento != null && listagemBancaria != null)
	        	this.javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarExcel(String assunto,String mensagem, String tituloFicheiro,byte[] ficheiro) throws MessagingException{
		MimeMessage message = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(USUARIO);
		helper.setTo("djanilson.lazaro@okayulatech.ao");
		helper.setCc("ernesto.sambongo@intellectus.co.ao");
		helper.setSubject(assunto);
		helper.setText(mensagem);
		helper.addAttachment(tituloFicheiro+".xlsx", new ByteArrayResource(ficheiro));
		this.javaMailSender.send(message);
	}
	
	public void reportarFalha(String assunto, String mensagem){
		try {
			MimeMessage message = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(USUARIO);
			helper.setTo("suporte@intellectus.co.ao");
			String copias[] = {"djanilson.lazaro@okayulatech.ao","ernesto.sambongo@intellectus.co.ao"};
			helper.setBcc(copias);
			helper.setSubject(assunto);
			helper.setText(mensagem);
			this.javaMailSender.send(message);			
		}catch (MessagingException e) {
			throw new EmailException("Erro ao enviar email. Verifique o email ou a conexão a internet.");
		}
	}
	
	public void reportar(String assunto, String mensagem, String destinatario){
		MimeMessage message = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(USUARIO);
			helper.setCc("djanilson.lazaro@okayulatech.ao");
			helper.setTo(destinatario);
			helper.setSubject(assunto);
			helper.setText(mensagem);
			this.javaMailSender.send(message);
		} catch (MessagingException e) {
			throw new EmailException("Erro ao enviar email. Verifique o email ou a conexão a internet");
		}
	}
	
	public String esconderPrefixo(String email) {
		String prefixo = email.substring(0, email.indexOf("@")), sufixo = email.substring(email.indexOf("@"), email.length());
		if(prefixo.length() > 1) {
			email = ""+prefixo.charAt(0);
			for(int i= 1; i< prefixo.length();i++) {
				email+="*";	
			}
			email +=sufixo;
		}
		return email;
	}
	
	private void topicos(Candidato candidato, MimeMessageHelper helper) throws MessagingException {
		Instituicao instituicao = this.instituicaoService.instituicao(2);
		if(instituicao.getSigla().equalsIgnoreCase("UGS")) {
			helper.addAttachment("Topico_Portugues.pdf", new ClassPathResource("doc/TOPICO_LINGUA_PORTUGUESA_2021.pdf"));
			helper.addAttachment("GUIA_DE_EXAME_ACESSO_2021.pdf", new ClassPathResource("doc/GUIA_DE_EXAME_ACESSO_2021.pdf"));	
			if(candidato.getExame() .equalsIgnoreCase("MATEMATICA_PORTUGUES"))
				helper.addAttachment("Topico_Matematica.pdf", new ClassPathResource("doc/TOPICO_MATEMATICA_2021.pdf"));
			else if(candidato.getExame().equalsIgnoreCase("PORTUGUES_HISTORIA"))
				helper.addAttachment("Topico_Historia.pdf", new ClassPathResource("doc/TOPICO_HISTORIA_2021.pdf"));	        		
		}else if(instituicao.getSigla().equalsIgnoreCase("IGS")) {
			helper.addAttachment("Topico_Portugues_Cultura.doc", new ClassPathResource("doc/Tópicos para o exame de admissão Língua Portuguesa e Cultura_ IGS_ 2017_IGS.doc"));
			if(candidato.getExame() .equalsIgnoreCase("MATEMATICA_PORTUGUES"))
				helper.addAttachment("Topico_Matematica.doc", new ClassPathResource("doc/TÓPICOS PARA O EXAME DE ACESSO Matimatica_IGS.doc"));
			else if(candidato.getExame().equalsIgnoreCase("PORTUGUES_HISTORIA"))
				helper.addAttachment("Topico_Historia.docx", new ClassPathResource("doc/História_IGS.docx"));
		}
	}

}
