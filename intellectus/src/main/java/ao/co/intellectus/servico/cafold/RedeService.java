package ao.co.intellectus.servico.cafold;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.springframework.stereotype.Service;

@Service
public class RedeService {

	public String getIp() throws UnknownHostException {
		return Inet4Address.getLocalHost().getHostAddress();
	}
	
	public String getNomeHost() throws UnknownHostException {
		return Inet4Address.getLocalHost().getHostName();
	}
	
	public String getUsuario() {
		return System.getProperty("user.name");
	}
	
	public  boolean temConexaoInternet() {
		try { 
			URL url = new URL("https://www.google.com/");
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
