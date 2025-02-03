package ao.co.intellectus.relatorios;



import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import ao.co.intellectus.model.ListagemBancaria;
import ao.co.intellectus.model.ListagemEmolumento;
import ao.co.intellectus.model.MapaFinanceiro;
import ao.co.intellectus.model.RegistroDocumentos;
import ao.co.intellectus.servico.cafold.ListagemBancoService;
import ao.co.intellectus.servico.cafold.ListagemCertificadoExport;
import ao.co.intellectus.servico.cafold.ListagemEmolumentoService;
import ao.co.intellectus.servico.cafold.MapaFinanceiroService;
import ao.co.intellectus.servico.cafold.NovoModeloRelatorioFinanceiro;



@RestController
@RequestMapping("/estudos")
public class ControllerRelatorioEstudos {
	@Autowired
	private MapaFinanceiroService mapaFinanceiroService;
	
	@Autowired
	private ListagemBancoService listagemBancoService;
	
	@Autowired
	private ListagemEmolumentoService listagemEmolumentoService;
	
	
	@Autowired
	private  NovoModeloRelatorioFinanceiro execel;
	
	@Autowired
	private  ListagemCertificadoExport ListagemExecel;
	
	@RequestMapping(value = "/mapamensaltestetodosS", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<StreamingResponseBody> MetodoTesteMapa(String grau,Integer ano) {
	  List<MapaFinanceiro> mapa = this.mapaFinanceiroService.listarPorAnoEGrau(grau, ano);
	  Workbook workBook =execel.TesteMapa(mapa);

	  return ResponseEntity
	    .ok()
	    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"mapatesteFinanceiro.xlsx\"")
	    .body(workBook::write);
	}
	
	
	/*@RequestMapping(value = "/export/listagem-certificado_1", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<StreamingResponseBody> Listagem_1(String data1, String data2) throws ParseException {
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);
		
	  List<RegistroDocumentos> listagem = this.mapaFinanceiroService.buscarListagem_1(dataF1, dataF2);
	  Workbook workBook = ListagemExecel.ListagemDeCertificados_1(listagem);

	  return ResponseEntity
	    .ok()
	    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"Listagem_de_Certificado_Por_Data(Ordem Alfabetica).xlsx\"")
	    .body(workBook::write);
	}*/
	
	/*@RequestMapping(value = "/export/listagem-certificado_2", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<StreamingResponseBody> Listagem_2(String data1, String data2) throws ParseException {
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);
		
	  List<RegistroDocumentos> listagem = this.mapaFinanceiroService.buscarListagem_2(dataF1, dataF2);
	  Workbook workBook = ListagemExecel.ListagemDeCertificados_2(listagem);

	  return ResponseEntity
	    .ok()
	    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"Listagem_de_Certificado_Por_Data(Ordem Alfabetica e Modelo).xlsx\"")
	    .body(workBook::write);
	}*/

	
	@RequestMapping(value = "/mapamensal", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<StreamingResponseBody> TesteMapaFinanceiro(String grau,Integer ano) {
	  List<MapaFinanceiro> mapa = this.mapaFinanceiroService.listarPorAnoEGrau(grau, ano);
	  Workbook workBook =execel.doGerarMapaFinanceiroTeste(mapa);

	  return ResponseEntity
	    .ok()
	    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"mapaFinanceiro.xlsx\"")
	    .body(workBook::write);
	}
	
	@GetMapping("/mapareconcilhacaobancaria")
	public ResponseEntity<StreamingResponseBody> TesteMapaReconcialicaoBancaria(String de,String para)throws IOException, ParseException {
	  List<ListagemBancaria> mapa = this.listagemBancoService.listarPorDataDePara(de, para);
	 
	  Workbook workBook = execel.doMapaReconcialicaoBancaria(mapa);

	  return ResponseEntity
	    .ok()
	    .contentType(MediaType.APPLICATION_OCTET_STREAM)
	    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"mapaBanco.xlsx\"")
	    .body(workBook::write);
	}
	
	@RequestMapping(value = "/mapaEmolumentos/{de}/{para}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<StreamingResponseBody> TesteMapaEmolumentos(@PathVariable String de,@PathVariable String para) throws IOException, ParseException  {
		List<ListagemEmolumento> mapa = this.listagemEmolumentoService.listarPorDataDePara(de, para);
		
		 Workbook workBook = execel.doMapaEmolumentos(mapa);
		
	return ResponseEntity
		.ok()
		.contentType(MediaType.APPLICATION_OCTET_STREAM)
		.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"LISTAGEM-EMOLUMENTO.xlsx\"")
		.body(workBook::write);
	}
	
	@RequestMapping(value = "/reconciliacao-bancaria/{de}/{para}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<StreamingResponseBody> TesteMapaBancos(@PathVariable String de,@PathVariable String para) throws IOException, ParseException  {
		List<ListagemBancaria> mapa = this.listagemBancoService.listarPorDataDePara(de, para);
		 Workbook workBook = execel.doMapaBancos(mapa);
		 
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"RECONCIALIAÇAO-BANCARIA.xlsx\"")
				.body(workBook::write);
	}

	
}
