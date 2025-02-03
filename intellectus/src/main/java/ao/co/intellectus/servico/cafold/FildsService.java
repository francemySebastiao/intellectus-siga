package ao.co.intellectus.servico.cafold;

import java.util.List;

import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.proxpay.Filds;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;

@Service
public class FildsService {
	
	public Filds filds(List<GuiaPagamentoHistorico> guiaHistorico, Guia guia, String telefone) {
		Filds custom_fields = new Filds();
		custom_fields.setDescription(guiaHistorico.get(0).getEmolumento().getEmolumento() + " - " + guiaHistorico.get(0).getObs());
		custom_fields.setMobile(telefone);
		custom_fields.setEmail(guia.getAluno().getEmail());
		custom_fields.setName(guia.getAluno().getNome());
		custom_fields.setGuia(guia.getNumeroGuia());
		return custom_fields;
	}

}
