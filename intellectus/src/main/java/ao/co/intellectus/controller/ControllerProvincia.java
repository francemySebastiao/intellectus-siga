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

import ao.co.intellectus.model.Pais;
import ao.co.intellectus.model.Provincia;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.model.request.ProvinciaRequest;
import ao.co.intellectus.repository.PaisRepository;
import ao.co.intellectus.repository.ProvinciaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/provincia")
public class ControllerProvincia {
	@Autowired
	private ProvinciaRepository provinciaRepository;
	@Autowired
	private PaisRepository paisReposiotry;

	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarProvincias() {
		Iterable<Provincia> provincias = this.provinciaRepository.findAll();
		if(provincias == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Não há registo de províncias.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(provincias, 0, null);
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody ProvinciaRequest provinciaRequest) {

		ResponseCliente c = new ResponseCliente();

		try {

			Provincia pesquisado = provinciaRepository.findByCodigoProvinciaAndProvincia(
					provinciaRequest.getCodigoProvincia(), provinciaRequest.getProvincia());
			Provincia provincia = new Provincia();

			if (pesquisado == null) {

				Pais pais = provinciaRequest.getPais() != null ? this.paisReposiotry.findOne(provinciaRequest.getPais())
						: null;

				BeanUtils.copyProperties(provinciaRequest, provincia, "pais");

				provincia.setPais(pais);

				this.provinciaRepository.save(provincia);

				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(provincia);
				c.setMensagem("Provincia salvo com sucesso!");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setResultado(provincia);
				c.setMensagem("Essa provincia já existe.");
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
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody ProvinciaRequest provinciaRequest) {
		try {
			//Provincia pesquisado = provinciaRepository.findByCodigoProvinciaAndProvincia(provinciaRequest.getCodigoProvincia(), provinciaRequest.getProvincia());
			Provincia provincia = new Provincia();

			Pais pais = provinciaRequest.getPais() != null ? this.paisReposiotry.findOne(provinciaRequest.getPais()): null;
			BeanUtils.copyProperties(provinciaRequest, provincia, "pais");
			provincia.setPais(pais);
			this.provinciaRepository.save(provincia);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Província alterada com sucesso.");
		} catch (Exception ex) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Teve erro em: " + ex.getCause());
		}
	}
}
