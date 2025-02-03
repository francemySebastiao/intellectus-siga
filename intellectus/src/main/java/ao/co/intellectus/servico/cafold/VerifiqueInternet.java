package ao.co.intellectus.servico.cafold;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class VerifiqueInternet {
	public static boolean connect(String addres) {
		try {
			URL url = new URL(addres);
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
