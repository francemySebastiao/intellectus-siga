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

import ao.co.intellectus.DTO.BancoCliente;
import ao.co.intellectus.model.Banco;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.BancoRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/banco")
public class ControllerBanco {
	@Autowired
	private BancoRepository bancoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() { 
		
		ResponseCliente c=new ResponseCliente();
		
		Iterable<Banco> bancos = this.bancoRepository.findByEstado(true);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(bancos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody BancoCliente bancoCliente){
		Banco banco = new Banco();
		banco.setBanco(bancoCliente.getBalcao());
		banco.setBalcao(bancoCliente.getBalcao());
		banco.setNumeroConta(bancoCliente.getNumeroConta());
		banco.setEstado(bancoCliente.isEstado());
		Moeda moeda = this.moedaRepository.findOne(bancoCliente.getMoeda());
		if(moeda != null) {
			banco.setMoeda(moeda);
			banco.setFormaPagamento(bancoCliente.getFormaPagamento());
			
			this.bancoRepository.save(banco);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Banco registado com sucesso.");
		}else {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Introduza uma moeda válida.");
		}
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> alterar(@RequestBody BancoCliente bancoCliente){
		Banco banco = this.bancoRepository.findOne(bancoCliente.getId());
		banco.setBanco(bancoCliente.getBalcao());
		banco.setBalcao(bancoCliente.getBalcao());
		banco.setNumeroConta(bancoCliente.getNumeroConta());
		Moeda moeda = this.moedaRepository.findOne(bancoCliente.getMoeda());
		if(moeda != null) {
			banco.setMoeda(moeda);
			banco.setEstado(bancoCliente.isEstado());
			banco.setFormaPagamento(bancoCliente.getFormaPagamento());
			this.bancoRepository.save(banco);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Banco actualizado com sucesso.");
		}else {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Introduza uma moeda válida.");
		}
	}
	
}
