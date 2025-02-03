package ao.co.intellectus.relatorios;

import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.servico.cafold.Conexao;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/estatistica_relatorio")
public class ControllerRelatorioEstatistica {
	
	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}
	
	

	@GetMapping("/listagens/candidatos/geral")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioListaCandidatos(@RequestParam String data1, @RequestParam String data2,
			@RequestParam String id_ano) throws Exception {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);
			
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data1", dataF1);
		paramets.put("data2", dataF2);
		paramets.put("codigo_ano", Integer.parseInt(id_ano));
		InputStream inputStream = null;

		inputStream = this.getClass().getResourceAsStream("/relatorio/R_Listagem_Candidatos__escid.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	
	
	@GetMapping("/listagens/candidatos/curso")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioListaCandidatosPorCurso(@RequestParam String data1, @RequestParam String data2,
			@RequestParam String id_ano) throws Exception {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
	

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);
			
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data1", dataF1);
		paramets.put("data2", dataF2);
		paramets.put("codigo_ano", Integer.parseInt(id_ano));
		InputStream inputStream = null;

		inputStream = this.getClass().getResourceAsStream("/relatorio/R_Listagem_Candidatos_curso_escid.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}
	
	
	@GetMapping("/listagens/candidatos/turno")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioListaCandidatosPorTurno(@RequestParam String data1, @RequestParam String data2,
			@RequestParam String id_ano) throws Exception {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);
			
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data1", dataF1);
		paramets.put("data2", dataF2);
		paramets.put("codigo_ano", Integer.parseInt(id_ano));
		InputStream inputStream = null;

		inputStream = this.getClass().getResourceAsStream("/relatorio/R_Listagem_Candidatos_curso_turno_escid.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}
	
	
	@GetMapping("/candidatos/portadores/especiais")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCandidatosPortadoresEspeciais(@RequestParam String ano_lectivo) throws Exception {
	
		
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano", ano_lectivo);
		
		InputStream inputStream = null;

		inputStream = this.getClass().getResourceAsStream("/relatorio/R_listagem_Nec_Especiais.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}
	
	
	
}
