package ao.co.intellectus.model.reponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ObjectoDeRetorno {
	//MONTAGEM DO OBJECTO RETORNO DE FORMA NORMAL
	public static ResponseEntity<ResponseCliente> MensagemDeRetorno(Object data, int quantidadeTotalItens,Integer codigo,String mensagem) {
		ResponseCliente resultado=new ResponseCliente(data,codigo,mensagem,quantidadeTotalItens);
		return new ResponseEntity<ResponseCliente>(resultado, HttpStatus.OK);
	}
	
	//MONTAGEM DO OBJECTO RETORNO COM PAGINATOR
	public static ResponseEntity<ResponseCliente> MensagemDeRetorno(Object data,Paginator paginator, int quantidadeTotalItens,Integer codigo,String mensagem) {
		ResponseCliente resultado=new ResponseCliente(data,codigo,mensagem,quantidadeTotalItens);
		resultado.setPaginator(paginator);
		return new ResponseEntity<ResponseCliente>(resultado, HttpStatus.OK);
	}
	
}
