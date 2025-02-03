package ao.co.intellectus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.Sala;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.SalaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/sala")
public class ControllerSala {
	@Autowired
	private SalaRepository repositorySala;

	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarSalas() {
		Iterable<Sala> salas = this.repositorySala.findAll();
		if(salas == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhuma sala encontrado.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(salas, 2, null);
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody Sala sala) {

		ResponseCliente c = new ResponseCliente();

		try {

			Sala pesquisado = this.repositorySala.findByDesignacao(sala.getDesignacao());

			if (pesquisado == null) {

				this.repositorySala.save(sala);

				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(sala);
				c.setMensagem("Sala salvo com sucesso!");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setResultado(sala);
				c.setMensagem("Essa Sala já existe.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		} catch (Exception ex) {
			// TODO: handle exception
			
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

	}
	
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces= "application/json")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody Sala sala) {
		try {
			Sala pesquisado = this.repositorySala.findByDesignacao(sala.getDesignacao());
			if (pesquisado == null) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(sala, 3, "Esta sala não existe.");
			} else {
				if(pesquisado.getId() != sala.getId())
					return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "A sala introduzida já existe.");
				pesquisado.setDesignacao(sala.getDesignacao());
				pesquisado.setTipoSala(sala.getTipoSala());
				pesquisado.setCapacidade(sala.getCapacidade());
				pesquisado.setProjector(sala.isProjector());
				pesquisado.setQuadroMultimedia(sala.isQuadroMultimedia());
				pesquisado.setSom(sala.isSom());
				pesquisado.setEstado(sala.isEstado());
				pesquisado.setSector(sala.getSector());
				pesquisado.setMesa(sala.isMesa());
				this.repositorySala.save(pesquisado);
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0,"Sala salva com sucesso");
			}
		} catch (Exception ex) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 3, "Teve erro em: " + ex.getCause());
		}
	}
	
}
