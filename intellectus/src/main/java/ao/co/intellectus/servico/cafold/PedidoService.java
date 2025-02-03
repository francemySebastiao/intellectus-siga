package ao.co.intellectus.servico.cafold;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.AnexoCliente;
import ao.co.intellectus.DTO.EmailCliente;
import ao.co.intellectus.model.Faculdade;
import ao.co.intellectus.repository.FaculdadeRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class PedidoService {
	
	@Autowired
	private FaculdadeRepository faculdadeRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ConexaoService conexaoService;
	
	public void enviarRelatorioPedidosOnline() throws ClassNotFoundException, JRException, SQLException {
		EmailCliente emailCliente = new EmailCliente();
		
		emailCliente.setMensagem("Caríssimos, Segue em anexo a listagem de documento pagos e não processados.");
		
		String[] bCC = {"francisco.lourenco@intellectus.co.ao","ernesto.sambongo@intellectus.co.ao", "marcio.coelho@intellectus.co.ao"};
	    
		for(Faculdade faculdade: this.faculdadeRepository.findAll()) {
	    	
	    	byte[] solicitacoesPendentes = this.solicitacoesPendentes(faculdade.getFaculdade());
	    	
	    	if(faculdade.getFaculdade() != null && solicitacoesPendentes!= null) {
	    		emailCliente.setAssunto("Solicitação de documentos - " + faculdade.getFaculdade());
	    		
	    		System.out.println("EXACUNTA A "+faculdade.getFaculdade());
	    		
	    		String[] destinatario = {faculdade.getEmail()};
	    		
	    		if(faculdade.getEmailDecano()!=null) {
	    		    String[] decano = {faculdade.getEmailDecano()};
	    		    emailCliente.setCc(decano);
	    		}
	    		emailCliente.setDestinatario(destinatario);
	    		emailCliente.setbCC(bCC);
	    		
	    		emailCliente.setAnexo(new AnexoCliente("SOLICITACOES-PENDENTES", solicitacoesPendentes, ".pdf"));
	    		
	    		this.emailService.enviar(emailCliente);	
	    	}else {
	    		System.out.println("EXACUNTA A "+faculdade.getFaculdade()+"O RELATÓRIO ESTÁ VAZIO");
	    	}
	    }
	}
	
	private byte[] solicitacoesPendentes(String faculdade) throws JRException, ClassNotFoundException, SQLException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("faculdade", faculdade);
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/academico/R_Solicitacoes_Pendentes.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conexaoService.getConexaoActual());
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}
