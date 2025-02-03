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

import ao.co.intellectus.DTO.EmolumentoPedidoCliente;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoPedido;
import ao.co.intellectus.model.TipoDeDeclaracao;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.EmolumentoPedidoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.TipoDeDeclaracaoRepository;

@RestController
@RequestMapping("/emolumentoPedido")
public class ControllerEmolumentoPedido {
	@Autowired
	private EmolumentoPedidoRepository emolumentoPedidoRepository;
	@Autowired
	private EmolumentoRepository emolumentoRepository; 
	@Autowired
	private TipoDeDeclaracaoRepository tipoDeDeclaracaoRepository;
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todos() { 	
		ResponseCliente c=new ResponseCliente();
		
        Iterable<EmolumentoPedido> emolumentos = this.emolumentoPedidoRepository.findAll();
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(emolumentos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody EmolumentoPedidoCliente ePedidoCliente) { 	
		ResponseCliente c               =new ResponseCliente();
        EmolumentoPedido ePedido        = new EmolumentoPedido(); 
        Emolumento emolumento           = this.emolumentoRepository.findOne(ePedidoCliente.getEmolumento());
		TipoDeDeclaracao tipoDeclaracao = this.tipoDeDeclaracaoRepository.findOne(ePedidoCliente.getTipoDeDeclaracao());
		
		//this.emolumentoPedidoRepository.findByTipoDeDeclaracaoAndEmolumento(tipoDeclaracao,emolumento);
		
		
//		if(emolumento == null || tipoDeclaracao == null) {
//			c.setCodigo(ResponseCode.values()[3].getDescricao());
//	       	c.setMensagem("Pedido emolumento já existe.");
//			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);	
//		}
		
		ePedido.setEmolumento(emolumento); 
		ePedido.setTipoDeDeclaracao(tipoDeclaracao);
		ePedido.setEstado(ePedidoCliente.isEstado());
		
		this.emolumentoPedidoRepository.save(ePedido);
	    
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
       	c.setMensagem("Pedido emolumento registado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody EmolumentoPedidoCliente ePedidoCliente) { 	
		ResponseCliente c=new ResponseCliente();
		
		EmolumentoPedido ePedido = this.emolumentoPedidoRepository.findOne(ePedidoCliente.getId());
        
		Emolumento emolumento           = this.emolumentoRepository.findOne(ePedidoCliente.getEmolumento());
		TipoDeDeclaracao tipoDeclaracao = this.tipoDeDeclaracaoRepository.findOne(ePedidoCliente.getTipoDeDeclaracao());
		
		ePedido.setEmolumento(emolumento); 
		ePedido.setTipoDeDeclaracao(tipoDeclaracao);
		ePedido.setEstado(ePedidoCliente.isEstado());
		
		this.emolumentoPedidoRepository.save(ePedido);
		
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Pedido emolumento atualizado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

}
