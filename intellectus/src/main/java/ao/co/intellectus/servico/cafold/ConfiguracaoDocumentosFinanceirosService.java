package ao.co.intellectus.servico.cafold;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.ConfiguracaoDocumentosFinanceiros;
import ao.co.intellectus.repository.ConfiguracaoDocumentosFinanceirosRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


@Service
public class ConfiguracaoDocumentosFinanceirosService {
	
	@Autowired
	private ConexaoService conexaoService; 
	@Autowired
	private ConfiguracaoDocumentosFinanceirosRepository configuracaoDocumentosFinanceirosRepository;
	
	public List<ConfiguracaoDocumentosFinanceiros> porEnviar(Integer codigoDoCodumento){
		return this.configuracaoDocumentosFinanceirosRepository.findByCodigoAndEnviado(codigoDoCodumento,false);
	}
	
	public void enviado(ConfiguracaoDocumentosFinanceiros configuracaoDocumentosFinanceiros) {
		configuracaoDocumentosFinanceiros.setEnviado(true);
		this.configuracaoDocumentosFinanceirosRepository.save(configuracaoDocumentosFinanceiros);
	}
	
	public byte[] ficheiro(Double valor) throws JRException, ClassNotFoundException, SQLException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("valor", valor);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Alunos_Dividas.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conexaoService.getConexaoLocal());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
