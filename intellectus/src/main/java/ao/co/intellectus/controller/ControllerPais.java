package ao.co.intellectus.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.MapaFinanceiro;
import ao.co.intellectus.model.Pais;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.MapaFinanceiroRepository;
import ao.co.intellectus.repository.PaisRepository;
import ao.co.intellectus.servico.cafold.GerarRelatorioExcel;


@RestController
@RequestMapping("/pais")
public class ControllerPais {

	@Autowired
	private PaisRepository paisRepository;
	@Autowired
	private MapaFinanceiroRepository mapaFinanceiroRepository;
	@Autowired
	private GerarRelatorioExcel excel;
	

	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listaPais() {
		ResponseCliente c = new ResponseCliente();

		Iterable<Pais> todos = this.paisRepository.findAll();

		c.setResultado(todos);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody Pais pais) {

		ResponseCliente c = new ResponseCliente();

		Pais paisCopia = new Pais();
		BeanUtils.copyProperties(pais, paisCopia, "instit");

		this.paisRepository.save(pais);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(paisCopia);
		c.setMensagem("Pais salvo com sucesso!");

		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody Pais pais) {

		ResponseCliente c = new ResponseCliente();

		Pais paisCopia = new Pais();
		BeanUtils.copyProperties(pais, paisCopia, "instit");

		this.paisRepository.save(pais);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(paisCopia);
		c.setMensagem("Dados alterado com sucesso!");

		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/todosPaises", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> paises(@RequestParam(defaultValue = "0") int page) {
		return new ResponseEntity<>(paisRepository.findAll(new PageRequest(page, 10)), HttpStatus.OK);
	}

	@RequestMapping(value = "/mapaFinanceiro", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<InputStreamResource> mapaFinanceiro() throws IOException {
		List<MapaFinanceiro> mapa = this.mapaFinanceiroRepository.findByGrauAndLectivo("LICENCIATURA",2019);
		ByteArrayInputStream in = excel.mapaFinanceiro(mapa);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=MAPA FINANCEIRO.xlsx");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
		
}
