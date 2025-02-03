package ao.co.intellectus.servico.proxypay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import ao.co.intellectus.DTO.proxpay.Referencias;

@Service
public class ReferenciasService {
	
	@Autowired
	private Gson gson;
	
	public String numero(HttpResponse<JsonNode> asJson) {
		String jsonDevolvido = asJson.getBody().getObject().toString();
		Referencias referencias = gson.fromJson(jsonDevolvido, Referencias.class);
		return referencias.getNumber();
	}

}
