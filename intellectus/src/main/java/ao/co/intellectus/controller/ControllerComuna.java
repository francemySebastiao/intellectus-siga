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

import ao.co.intellectus.model.Comuna;
import ao.co.intellectus.model.Municipio;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.model.request.ComunaRequest;
import ao.co.intellectus.repository.ComunaRepository;
import ao.co.intellectus.repository.MuniciopioRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/comuna")
public class ControllerComuna {

	@Autowired
	private ComunaRepository comunaRepository;
	@Autowired
	private MuniciopioRepository municipioRepository;

	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarComunas() {

		ResponseCliente c = new ResponseCliente();
		Iterable<Comuna> todas = this.comunaRepository.findAll();
		if(todas == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de comuna encontrado.");
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todas);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody ComunaRequest comunaRequest) {

		ResponseCliente c = new ResponseCliente();

		try {

			Municipio municipio = comunaRequest.getMunicipio() != null
					? this.municipioRepository.findOne(comunaRequest.getMunicipio())
					: null;

			Comuna pesquisado = this.comunaRepository.findByDescricaoAndMunicipio(comunaRequest.getDescricao(),
					municipio);

			Comuna comuna = new Comuna();

			if (pesquisado == null) {
				BeanUtils.copyProperties(comunaRequest, comuna, "municipio");

				comuna.setMunicipio(municipio);

				this.comunaRepository.save(comuna);

				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(comuna);
				c.setMensagem("Comuna salvo com sucesso!");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setResultado(municipio);
				c.setMensagem("Essa Comuna já existe.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		} catch (Exception ex) {
			// TODO: handle exception
			
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody ComunaRequest comunaRequest) {
		try {
			Municipio municipio = (comunaRequest.getMunicipio() != null)? this.municipioRepository.findOne(comunaRequest.getMunicipio()): null;
			Comuna pesquisado =this.comunaRepository.findOne(comunaRequest.getId());
			if (pesquisado == null) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Erro ao validar a comuna.");
			} else {
				Comuna comuna = new Comuna();
				BeanUtils.copyProperties(pesquisado, comuna, "municipio");
				comuna.setMunicipio(municipio);
				this.comunaRepository.save(comuna);
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Comuna salva com sucesso.");
			}
		} catch (Exception ex) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Teve erro em: " + ex.getCause());
		}
	}

}
