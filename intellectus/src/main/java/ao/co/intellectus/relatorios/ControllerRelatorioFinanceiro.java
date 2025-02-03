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

import ao.co.intellectus.model.ListagemBancaria;
import ao.co.intellectus.model.ListagemEmolumento;
import ao.co.intellectus.model.MapaFinanceiro;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.cafold.GerarRelatorioExcel;
import ao.co.intellectus.servico.cafold.ListagemBancoService;
import ao.co.intellectus.servico.cafold.ListagemEmolumentoService;
import ao.co.intellectus.servico.cafold.MapaFinanceiroService;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/financeiro")
public class ControllerRelatorioFinanceiro {

	@Autowired 
	private GerarRelatorioExcel excel;
	@Autowired
	private ListagemEmolumentoService listagemEmolumentoService;
	@Autowired
	private ListagemBancoService listagemBancoService;
	@Autowired
	private MapaFinanceiroService mapaFinanceiroService;
	
	@RequestMapping(value = "/mapa-mensalidade/{grau}/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<InputStreamResource> mapaFinanceiro(@PathVariable String grau,@PathVariable Integer ano) throws IOException {
		List<MapaFinanceiro> mapa = this.mapaFinanceiroService.listarPorAnoEGrau(grau, ano);
		//ResponseEntity<byte[]>	 
		ByteArrayInputStream in = excel.mapaFinanceiro(mapa);
		// return IO ByteArray(in);
		HttpHeaders headers = new HttpHeaders();
		// set filename in header
		headers.add("Content-Disposition", "attachment; filename=MAPA FINANCEIRO.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
	@RequestMapping(value = "/mapaBancos/{de}/{para}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<InputStreamResource> reconciliacaoBancaria(@PathVariable String de,@PathVariable String para) throws IOException, ParseException {	
		List<ListagemBancaria> mapa = this.listagemBancoService.listarPorDataDePara(de, para);
		ByteArrayInputStream in   = excel.mapaReconcialicaoBancaria(mapa);
		HttpHeaders headers       = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=RECONCIALIAÇÃO-BANCÁRIA.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
	@RequestMapping(value = "/mapaEmolumentos/{de}/{para}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<InputStreamResource> mapaEmolumentos(@PathVariable String de,@PathVariable String para) throws IOException, ParseException  {
		List<ListagemEmolumento> mapa = this.listagemEmolumentoService.listarPorDataDePara(de, para);
		ByteArrayInputStream in = excel.mapaEmolumentos(mapa);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=LISTAGEM-EMOLUMENTO.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
	@RequestMapping(value = "/reconciliacao-bancaria/{de}/{para}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<InputStreamResource> mapaBancos(@PathVariable String de,@PathVariable String para) throws IOException, ParseException  {
		List<ListagemBancaria> mapa = this.listagemBancoService.listarPorDataDePara(de, para);
		ByteArrayInputStream in = excel.mapaBancos(mapa);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=RECONCIALIAÇÃO-BANCÁRIA.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@RequestMapping(value = "/mapa-financeiro/{grau}/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody 
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listaMapaFinanceiro(@PathVariable String grau,@PathVariable Integer ano) { 
		List<MapaFinanceiro> mapa = this.mapaFinanceiroService.listarPorAnoEGrau(grau, ano);
		return ObjectoDeRetornoParcial.MensagemDeRetorno(mapa, 0, null);
	} 
}
