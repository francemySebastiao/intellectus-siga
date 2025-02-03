package ao.co.intellectus.servico.cafold;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.RelatorioFinanceiro;
import ao.co.intellectus.model.enumeracao.TipoRelatorio;
import ao.co.intellectus.repository.RelatorioFinanceiroRepository;

@Service
public class RelatorioFinanceiroService {
	
	@Autowired
	private RelatorioFinanceiroRepository relatorioFinanceiroRepository;
	
	public List<RelatorioFinanceiro> pendentes(){
		return this.relatorioFinanceiroRepository.findByProximo(true);
	}
	
	public List<RelatorioFinanceiro> pendentes(TipoRelatorio tipoRelatorio){
		return this.relatorioFinanceiroRepository.findByProximoAndTipoRelatorio(true, tipoRelatorio);
	}
	
	public void registarEnvioDeRelatorio(RelatorioFinanceiro relatorioFinanceiro,byte[] listagemEmolumento,byte[] listagemBancaria) {
		relatorioFinanceiro.setProximo(false);
		relatorioFinanceiro.setEnviado(true);
		if(listagemEmolumento != null && listagemBancaria != null)
			this.relatorioFinanceiroRepository.save(relatorioFinanceiro);
	}
	
	public void registarEnvioDeRelatorio(RelatorioFinanceiro relatorioFinanceiro,byte[] anexo) {
		relatorioFinanceiro.setProximo(false);
		relatorioFinanceiro.setEnviado(true);
		if(anexo != null )
			this.relatorioFinanceiroRepository.save(relatorioFinanceiro);
	}
	
}
