package ao.co.intellectus.servico.cafold;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import ao.co.intellectus.model.JWT;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseClienteRecupercao;
import ao.co.intellectus.model.reponse.ResponseCode;

@ControllerAdvice
public class ObjectoDeRetornoParcial {

	public static ResponseEntity<ResponseCliente> MensagemDeRetorno(Object resultado, int codigo) {
		ResponseCliente c = new ResponseCliente();
		c.setResultado(resultado);
		c.setCodigo(ResponseCode.values()[codigo].getDescricao());
		
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	public static ResponseEntity<ResponseCliente> MensagemDeRetorno(int codigo, String mensagem) {
		ResponseCliente c = new ResponseCliente();
		c.setCodigo(ResponseCode.values()[codigo].getDescricao());
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	public static ResponseEntity<ResponseCliente> MensagemDeRetorno(Object resultado, int codigo, String mensagem) {
		ResponseCliente c = new ResponseCliente();
		c.setResultado(resultado);
		c.setCodigo(ResponseCode.values()[codigo].getDescricao());
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	public static ResponseEntity<ResponseClienteRecupercao> MensagemDeRetornoRecuperacao(String mensagem,int codigo){
		ResponseClienteRecupercao c = new ResponseClienteRecupercao();
		c.setCodigo(ResponseCode.values()[codigo].getDescricao());
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseClienteRecupercao>(c, HttpStatus.OK);
	}
	
	public static ResponseEntity<ResponseCliente> MensagemDeRetorno(Object resultado,PermissaoCurso permissao, int codigo, String mensagem) {
		ResponseCliente c = new ResponseCliente();
		c.setResultado(resultado);
		c.setCodigo(ResponseCode.values()[codigo].getDescricao());
		c.setMensagem(mensagem);
		if(permissao != null) {
			c.setPermissao(permissao.getPermissao());
		}
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	public static ResponseEntity<JWT> JWT(int code, String token, String message) {
		JWT jwt = new JWT();
		jwt.setCode(ResponseCode.values()[code].getDescricao());
		jwt.setToken(token);
		jwt.setMessage(message);
		return new ResponseEntity<JWT>(jwt, HttpStatus.OK);
	}

}
