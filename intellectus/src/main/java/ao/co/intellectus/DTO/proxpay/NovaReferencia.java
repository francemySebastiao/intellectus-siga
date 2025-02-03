package ao.co.intellectus.DTO.proxpay;

import java.util.Calendar;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class NovaReferencia {
	public static Unidade criarReferencias(Unidade up){		
		
		Unidade unidade=new Unidade();
		unidade.setAmount(up.getAmount());
		
		Filds filds=new Filds();
		
		filds.setDescription(up.getCustom_fields().getDescription());
		filds.setMobile(up.getCustom_fields().getMobile());
		filds.setName(up.getCustom_fields().getName());
		filds.setGuia(up.getCustom_fields().getGuia());
		
		unidade.setCustom_fields(filds);
		
        Calendar hoje = Calendar.getInstance();
		
		int ano = hoje.get(Calendar.YEAR);
		
		int mes = hoje.get(Calendar.MONTH)+1;
		
		Integer dia = hoje.get(Calendar.DAY_OF_MONTH);
		
		if(dia<10) {
			if(mes<10) {
				unidade.setExpiry_date(ano+"-"+0+mes+"-"+0+dia); 
			}else {
				unidade.setExpiry_date(ano+"-"+mes+"-"+0+dia);  
			}
		}else {
			
			if(mes<10) {
				unidade.setExpiry_date(ano+"-"+0+mes+"-"+dia);
			}else {
				unidade.setExpiry_date(ano+"-"+mes+"-"+dia);
			} 
		} 
		
		Gson gson=new Gson();
		
		String json = gson.toJson(unidade); 
		
		String payload="{\"reference\":"+json+"}";
		
		HttpResponse<JsonNode> jsonResponse = null;
		try {
			jsonResponse = Unirest.post("https://api.proxypay.co.ao/references")
			          .basicAuth("api", "eu4vhrpaidth94mc58mn1ilduk8n6gf2")
			          .header("accept", "application/vnd.proxypay.v2+json") 
			          .header("Content-Type", "application/json")
			          .body(payload)
			          .asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Integer statusCode = jsonResponse.getStatus();
    
	    if (statusCode == 200) {
	        System.out.println("StatusCode 200 " + jsonResponse.getBody().toString());
	    } else {
	        System.err.println("StatusCode " + statusCode.toString() + " " + jsonResponse.getBody().toString());
	    }
		return unidade;
	}
}
