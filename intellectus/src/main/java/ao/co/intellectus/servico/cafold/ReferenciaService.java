package ao.co.intellectus.servico.cafold;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import ao.co.intellectus.DTO.proxpay.Unidade;

@Service
public class ReferenciaService {
		
	public void enviarReferencia(Unidade unidade) throws UnirestException {
		Gson gson = new Gson();
		String json = gson.toJson(unidade);
		String payload = "{\"reference\":" + json + "}";
		// mandar para proxypay
		Unirest.post("https://api.proxypay.co.ao/references")
		.basicAuth("api", "un5kodf4euv5s13b6i22qf0bkks1mosn")
		.header("accept", "application/vnd.proxypay.v1+json")
		.header("Content-Type", "application/json").body(payload).asJson();
	}

}
