package ao.co.intellectus.servico.cafold;

import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class PropertiesService {
	public Properties getProperties() {
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.debug", false);
		properties.put("mail.smtp.connectiontimeout", 10000);
		properties.put("mail.smtp.ssl.trust", "smtp.office365.com");
		return properties;
	}
}
