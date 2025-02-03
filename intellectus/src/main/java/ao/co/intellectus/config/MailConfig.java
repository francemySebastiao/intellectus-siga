package ao.co.intellectus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import ao.co.intellectus.servico.cafold.EmailService;
import ao.co.intellectus.servico.cafold.PropertiesService;

@Configuration
@ComponentScan(basePackageClasses = EmailService.class)
public class MailConfig {
	
	@Autowired
	private PropertiesService propertiesService;
	private final String USUARIO = "siga@intellectus.co.ao";
	private final String SENHA = "Intell@123";

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.office365.com");
		mailSender.setPort(587);
		mailSender.setUsername(USUARIO);
		mailSender.setPassword(SENHA);
		mailSender.setJavaMailProperties(propertiesService.getProperties());
		return mailSender;
	}

}
