package ao.co.intellectus.servico.cafold;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class FicheiroService {

	public byte[] ByteArrayParaByte(ByteArrayInputStream byteArrayInputStream) throws IOException {
		byte[] array = new byte[byteArrayInputStream.available()];
		byteArrayInputStream.read(array);
		return array;
	}
	
}
