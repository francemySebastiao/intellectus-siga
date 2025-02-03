package ao.co.intellectus.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.Municipio;
import ao.co.intellectus.model.Provincia;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.model.request.MunicipioRequest;
import ao.co.intellectus.repository.MuniciopioRepository;
import ao.co.intellectus.repository.ProvinciaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/municipio")
public class ControllerMunicipio {
	@Autowired
	private MuniciopioRepository municipioRepository;
	@Autowired
	private ProvinciaRepository provinciaRepository;

	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() {

		ResponseCliente c = new ResponseCliente();

		Iterable<Municipio> municipios = this.municipioRepository.findAll();
		if(municipios == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de município encontrado.");
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(municipios);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody MunicipioRequest municipioRequest) {

		ResponseCliente c = new ResponseCliente();

		try {

			Provincia provincia = municipioRequest.getProvincia() != null
					? this.provinciaRepository.findOne(municipioRequest.getProvincia())
					: null;
			Municipio pesquisado = this.municipioRepository.findByDescricaoAndProvincia(municipioRequest.getDescricao(),
					provincia);
			Municipio municipio = new Municipio();

			if (pesquisado == null) {
				BeanUtils.copyProperties(municipioRequest, municipio, "provincia");

				municipio.setProvincia(provincia);

				this.municipioRepository.save(municipio);

				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(municipio);
				c.setMensagem("Municipio salvo com sucesso!");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setResultado(municipio);
				c.setMensagem("Esse Municipio já existe.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		} catch (Exception ex) {
			// TODO: handle exception
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody MunicipioRequest municipioRequest) {

		ResponseCliente c = new ResponseCliente();

		try {

			Provincia provincia = municipioRequest.getProvincia() != null ? this.provinciaRepository.findOne(municipioRequest.getProvincia()) : null;
			//Municipio pesquisado = this.municipioRepository.findByDescricaoAndProvincia(municipioRequest.getDescricao(),provincia);
			Municipio municipio = new Municipio();

			BeanUtils.copyProperties(municipioRequest, municipio, "provincia");

			municipio.setProvincia(provincia);

			this.municipioRepository.save(municipio);

			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(municipio);
			c.setMensagem("Dados do municipio actualizado com sucesso!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

		} catch (Exception ex) {
			// TODO: handle exception
		
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

	}
}
