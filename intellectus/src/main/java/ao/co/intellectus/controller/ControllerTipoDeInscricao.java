package ao.co.intellectus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.TipoInscricao;
import ao.co.intellectus.repository.TipoDeInscricaoRepository;

@RestController
@RequestMapping("/tipoDeInscricao")
public class ControllerTipoDeInscricao {

	@Autowired
	private TipoDeInscricaoRepository tipoInscricaoRepository;
	
	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public List<TipoInscricao> inscricoes() {
		return tipoInscricaoRepository.findByStatus(true);
	}
}
