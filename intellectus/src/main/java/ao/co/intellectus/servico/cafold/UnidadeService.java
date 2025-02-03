package ao.co.intellectus.servico.cafold;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.proxpay.Filds;
import ao.co.intellectus.DTO.proxpay.Unidade;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;

@Service
public class UnidadeService {

	@Autowired
	private DataService dataServico;
	@Autowired
	private FildsService fildsService; 
	@Autowired
	private UnidadeService unidadeService;


	public Unidade filds(List<GuiaPagamentoHistorico> guiaHistorico,Guia guia, String telefone) {
		Date data = guia.getDataVencimento(), hoje = new Date();
		if (hoje.getTime() >= data.getTime()) {
			data = hoje;
		} else {
			data = guia.getDataVencimento();
		}
		Filds custom_fields = this.fildsService.filds(guiaHistorico, guia, telefone);	
		Unidade unidade = this.unidadeService.unidade(guia, data);				
		unidade.setCustom_fields(custom_fields);
		return unidade;
	}
	
	private Unidade unidade(Guia guia,Date data) {
		Unidade unidade = new Unidade();
		String dataVencimento = this.dataServico.dataLocal(data);
		unidade.setExpiry_date(dataVencimento);
		unidade.setAmount(guia.getValor());	
		return unidade;
	}
}
