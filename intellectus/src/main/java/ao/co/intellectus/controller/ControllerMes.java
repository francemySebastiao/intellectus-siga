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

import ao.co.intellectus.model.Mes;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.MesRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/mes")
public class ControllerMes {
	
	@Autowired
	private MesRepository MesRepository;
	
	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> alunos() {
		ResponseCliente c=new ResponseCliente();
		
		Iterable<Mes> todos = this.MesRepository.findAll();
		
		c.setResultado(todos);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody Mes mes) {

		ResponseCliente c = new ResponseCliente();

		try {

			Mes pesquisado = this.MesRepository.findByDescricao(mes.getDescricao());

			if (pesquisado == null) {

				this.MesRepository.save(mes);

				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(mes);
				c.setMensagem("Mês salvo com sucesso!");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setResultado(mes);
				c.setMensagem("Esse Mês já existe.");
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
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody Mes mes) {
		try {
			Mes pesquisado = this.MesRepository.findByDescricao(mes.getDescricao());
			if (pesquisado == null) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(mes, 3, "Este mes não existe.");
			} else {
				if(pesquisado.getId() != mes.getId())
					return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "O mes introduzido já existe.");
				pesquisado.setDescricao(mes.getDescricao());
				this.MesRepository.save(pesquisado);
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0,"Mes salvo com sucesso");
			}
		} catch (Exception ex) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 3, "Teve erro em: " + ex.getCause());
		}
	} 
}
