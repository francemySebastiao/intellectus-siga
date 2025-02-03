package ao.co.intellectus.relatorios;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.servico.cafold.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/academico")
public class ControllerRelatorioAcademico {

	public static Connection conectar() {

		Conexao con = new Conexao();
		return con.getConn();
	}
	
	/*
	  Objectivo:Esse relatório permite listar a quantidade de alunos matriculados por turno e curso..
	 */
	@RequestMapping(value = "/quantidade/matriculados", method = RequestMethod.GET, produces = "application/json")
	//@GetMapping("/quantidade/matriculados")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioQuantidadeMatriculados(@RequestParam String id_ano, @RequestParam String grau)
			throws Exception {


		byte[] relatrio = servicoQuantidadeMatriculados(id_ano, grau);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoQuantidadeMatriculados(String id_ano, String grau) throws JRException {
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("grau", grau);
		paramets.put("codigo_ano", Integer.parseInt(id_ano));

		InputStream inputStream = null;

		inputStream = this.getClass().getResourceAsStream("/relatorio/R_Controle_Confirmacao_Turno.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	

	@RequestMapping(value = "/quantidade/candidatos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioQuantidadeCandidatos(@RequestParam String id_ano, @RequestParam String grau)
			throws Exception {

		byte[] relatrio = servicoQuantidadeCandidatos(id_ano, grau);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoQuantidadeCandidatos(String id_ano, String grau) throws JRException {
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("grau", grau);
		paramets.put("codigo_ano", Integer.parseInt(id_ano));

		InputStream inputStream = null;

		inputStream = this.getClass().getResourceAsStream("/relatorio/R_Candidatos_por_turno.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}
