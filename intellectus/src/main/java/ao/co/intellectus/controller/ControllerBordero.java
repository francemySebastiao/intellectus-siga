package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.BorderoCliente;
import ao.co.intellectus.DTO.EmolumentoGuiaCliente;
import ao.co.intellectus.model.Bordero;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.HistoricoCredito;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.BorderoRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoCreditoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;

@RestController
@RequestMapping("/bordero")
public class ControllerBordero {
	@Autowired
	private BorderoRepository borderoRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private HistoricoCreditoRepository historicoCreditoRepository;
	
	
	@RequestMapping(value = "/buscarPorCodigo/{numero}/{tipo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCodigo(@PathVariable String numero,@PathVariable Integer tipo) { 
		//BorderoCliente
		ResponseCliente c=new ResponseCliente();
		
		Bordero bordero=null;
		HistoricoCredito credito=null;
        if(tipo==0) {
        	Guia guia = this.guiaRepository.findByNumeroGuiaAndLiquidadaAndAnulada(numero, true, false);
        	
        	if(guia!=null)
        	  bordero = this.borderoRepository.findByGuia(guia);
        	
        	
        	if(guia==null) {
        		credito = this.historicoCreditoRepository.findByBorderoInternoAndAnulado(numero,false);
        		
        		if(credito!=null) {
        			bordero = this.borderoRepository.findOne(credito.getBordero().getId());        			
        		}
        	}
        	if(guia==null && credito==null) {
        		c.setCodigo(ResponseCode.values()[2].getDescricao());
        		c.setMensagem("Não existe nenhuma guia com este número.");
        		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
        	}
        }else {
        	bordero = this.borderoRepository.findByNumero(numero);
        	
        	if(bordero==null) {
        		credito = this.historicoCreditoRepository.findByBorderoInternoAndAnulado(numero,false);
        		
        		if(credito!=null) {
        			bordero = this.borderoRepository.findOne(credito.getBordero().getId());        			
        		}
        	}
        }
        
        if(bordero==null) {
         	c.setCodigo(ResponseCode.values()[2].getDescricao());
    		c.setMensagem("Número de operação não existe.");
    		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
        } 
        BorderoCliente b=new BorderoCliente();
        
        
        if(bordero.getGuia()!=null && credito==null) {
            b.setDataEmissao(bordero.getGuia().getDataEmicao());
            b.setNumeroGuia(bordero.getGuia().getNumeroGuia());
            b.setDataLiquidacao(bordero.getGuia().getDataLiquidacao());
            b.setRefSigaGuia(bordero.getGuia().getId());	
        }
        
        if(credito!=null && bordero.getGuia()==null) {
            b.setDataEmissao(credito.getDataRegisto());
            b.setNumeroGuia(credito.getBorderoInterno());
            b.setDataLiquidacao(credito.getDataRegisto());
            b.setRefSigaGuia(credito.getId());
        }
        
        b.setAluno(bordero.getAluno().getNumeroDeAluno());
        b.setNome(bordero.getAluno().getNome());
        b.setBanco(bordero.getBanco().getBanco());
        b.setConta(bordero.getBanco().getNumeroConta());
        b.setDataDeposito(bordero.getDataDeposito());
        b.setDataRegistro(bordero.getDataRegistro());
        b.setRefSiga(bordero.getId());
        b.setMoeda(bordero.getMoeda().getDesignacao());
        b.setNumero(bordero.getNumero());
        b.setValor(bordero.getValor());
        
        //b.getNumeroGuia()
		List<EmolumentoGuiaCliente> guiasHist=new ArrayList<EmolumentoGuiaCliente>();
		EmolumentoGuiaCliente guiaHist;
        if(bordero.getGuia()!=null && credito==null) {
        	if(bordero.getGuia().getUsuarioEmitiu()!=null)
        	b.setEmitidoPor(bordero.getGuia().getUsuarioEmitiu().getName());
        	
        	if(bordero.getGuia().getUsuarioLiquidou()!=null)
        	b.setRegistradoPor(bordero.getGuia().getUsuarioLiquidou().getName());        	
        
			List<GuiaPagamentoHistorico> todas = this.historicoGuiaRepository.findByGuia(bordero.getGuia());
			
			for (GuiaPagamentoHistorico gph : todas) {
				guiaHist=new EmolumentoGuiaCliente();
				
				guiaHist.setValor(gph.getValor());
				guiaHist.setEmolumento(gph.getEmolumento().getEmolumento());
				guiaHist.setId(gph.getEmolumento().getId());
				guiaHist.setCodigo(gph.getEmolumento().getCodigo());
			  	guiasHist.add(guiaHist);
			}
        }
        
        //guiasHist.clear();
        
        if(credito!=null) {
            b.setEmitidoPor(credito.getUsuarioEmitiu().getName());
            b.setRegistradoPor(credito.getUsuarioEmitiu().getName());       
        	guiaHist=new EmolumentoGuiaCliente();
			
			guiaHist.setValor(credito.getValorDeposito());
			guiaHist.setEmolumento("Crédito");
			guiaHist.setId(1);
			guiaHist.setCodigo(1);
		  	guiasHist.add(guiaHist);
        }
        
        b.setEmolumentos(guiasHist);
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(b);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/historicoCreditoAluno/{numeroAluno}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarHistoricoCredito(@PathVariable Integer numero) { 
		//BorderoCliente
		ResponseCliente c=new ResponseCliente();
		
		Bordero bordero;
       
        bordero = this.borderoRepository.findByNumero(numero.toString());
        
        
        if(bordero==null) {
         	c.setCodigo(ResponseCode.values()[2].getDescricao());
    		c.setMensagem("Número de operação não existe.");
    		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
        } 
        BorderoCliente b=new BorderoCliente();
        b.setAluno(bordero.getGuia().getNumeroDeAluno());
        b.setNome(bordero.getAluno().getNome());
   
        b.setBanco(bordero.getBanco().getBanco());
        
        //b.setConta(Integer.parseInt(bordero.getBanco().getNumeroConta()));
        b.setConta(bordero.getBanco().getNumeroConta());
        
        b.setDataDeposito(bordero.getDataDeposito());
        //guia
        b.setDataEmissao(bordero.getGuia().getDataEmicao());
        
        b.setNumeroGuia(bordero.getGuia().getNumeroGuia());
        
        b.setDataLiquidacao(bordero.getGuia().getDataLiquidacao());
        b.setRefSigaGuia(bordero.getGuia().getId());
        //bordero
        b.setDataRegistro(bordero.getDataRegistro());
        b.setRefSiga(bordero.getId());
        b.setMoeda(bordero.getMoeda().getDesignacao());
        b.setNumero(bordero.getNumero());
        b.setValor(bordero.getValor());
      
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(b);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
}
