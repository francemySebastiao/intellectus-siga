package ao.co.intellectus.relatorios;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.RegistroBaseDadosAproveitamento;
import ao.co.intellectus.model.RegistroBaseDadosGraduadosPreliminarDefinitivo;
import ao.co.intellectus.model.RegistroBaseDadosMatriculaincial;
import ao.co.intellectus.model.RegistroBaseDeDadosPosGraduacao;
import ao.co.intellectus.model.ResgistroBaseDadosAcesso;
import ao.co.intellectus.servico.cafold.GerarRelatorioExcel;
import ao.co.intellectus.servico.cafold.MinistroService;

@RestController
@RequestMapping("/ministerio")
public class ControllerRelatorioMinisterio {
	
	@Autowired
	private MinistroService ministerioService;
	@Autowired 
	private GerarRelatorioExcel excel;

	
	@RequestMapping(value = "/preliminarDefinitivo/{anolectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<InputStreamResource> preliminarDefinitivo(@PathVariable Integer anolectivo) throws IOException, ParseException {	
		List<RegistroBaseDadosGraduadosPreliminarDefinitivo> mapa = this.ministerioService.registroBaseDadosGraduadosPreliminarDefinitivo(anolectivo);
		ByteArrayInputStream in   = excel.registroBaseDadosGraduadosPreliminarDefinitivo(mapa);
		HttpHeaders headers       = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=REGISTRO_BASE_DE_DADOS_GRADUADOS_PRELIMINAR_E_DEFINITIVO.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
	@RequestMapping(value = "/acesso/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<InputStreamResource> resgistroBaseDadosAcesso(@PathVariable Integer anoLectivo) throws IOException, ParseException {	
		List<ResgistroBaseDadosAcesso> mapa = this.ministerioService.resgistroBaseDadosAcesso(anoLectivo);
		ByteArrayInputStream in   = excel.resgistroBaseDadosAcesso(mapa);
		HttpHeaders headers       = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=REGISTRO_BASE_DE_DADOS_ACESSO.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
	@RequestMapping(value = "/matriculaInicial/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<InputStreamResource> matriculaInicial(@PathVariable Integer anoLectivo) throws IOException, ParseException {	
		List<RegistroBaseDadosMatriculaincial> mapa = this.ministerioService.registroBaseDadosMatriculaincial(anoLectivo);
		ByteArrayInputStream in   = excel.RegistroBaseDadosMatriculaincial(mapa);
		HttpHeaders headers       = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=REGISTRO_BASE_DE_DADOS_MATRICULA_INICIAL.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
	@RequestMapping(value = "/posGraduacao/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<InputStreamResource> registroBaseDeDadosPosGraduacao(@PathVariable Integer anoLectivo) throws IOException, ParseException {	
		List<RegistroBaseDeDadosPosGraduacao> mapa = this.ministerioService.registroBaseDeDadosPosGraduacao(anoLectivo);
		ByteArrayInputStream in   = excel.registroBaseDeDadosPosGraduacao(mapa);
		HttpHeaders headers       = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=REGISTRO_BASE_DE_DADOS_DE_POS_GRADUACAO.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
	@RequestMapping(value = "/aproveitamento/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<InputStreamResource> aproveitamento(@PathVariable Integer anoLectivo) throws IOException, ParseException {	
		List<RegistroBaseDadosAproveitamento> mapa = this.ministerioService.registroBaseDadosAproveitamento(anoLectivo);
		ByteArrayInputStream in   = excel.registroBaseDadosAproveitamento(mapa);
		HttpHeaders headers       = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=REGISTRO_BASE_DADOS_APROVEITAMENTO.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
	
}
