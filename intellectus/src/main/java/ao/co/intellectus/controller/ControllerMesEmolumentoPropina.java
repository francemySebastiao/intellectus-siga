package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.MesEmolumentoPropinaCliente;
import ao.co.intellectus.DTO.MesEmolumentosPropinaCliente;
import ao.co.intellectus.model.MesEmolumentoPropina;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.MesEmolumentoPropinaRepository;

@RestController
@RequestMapping("/emolumentoPripina")
public class ControllerMesEmolumentoPropina {
	@Autowired
	private MesEmolumentoPropinaRepository mesEmolumentoPropinaRepository;

	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() {
		ResponseCliente c = new ResponseCliente();

		Iterable<MesEmolumentoPropina>    todos             = this.mesEmolumentoPropinaRepository.findAll();
		MesEmolumentosPropinaCliente      emolumentosRetorno=new MesEmolumentosPropinaCliente();
		List<MesEmolumentoPropinaCliente> emolumentos       =new ArrayList<MesEmolumentoPropinaCliente>();
		
		MesEmolumentoPropinaCliente emolumento;
		for (MesEmolumentoPropina mp : todos) {
			emolumento=new MesEmolumentoPropinaCliente();
			
			BeanUtils.copyProperties(mp, emolumento, "emolumentoNormal","emolumentoInscricaoDisciplina");
			
			emolumento.setEmolumentoNormal(mp.getEmolumentoNormal().getId());
			emolumento.setEmolumentoInscricaoDisciplina(mp.getEmolumentoInscricaoDisciplina().getId());
			
			emolumentos.add(emolumento);
		}
		
		emolumentosRetorno.setMesEmolumentos(emolumentos);
		
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(emolumentosRetorno);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}
