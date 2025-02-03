package ao.co.intellectus.servico.notificacoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.TipoUsuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseClienteReduzido;
import ao.co.intellectus.model.reponse.ResponseCode;

@Service
public class HttpResponse {

	public ResponseEntity<ResponseCliente> MensagemDeRetorno(int codigo) {
		return this.MensagemDeRetorno(codigo, null, null);
	}

	public ResponseEntity<ResponseCliente> MensagemDeRetorno(int codigo, String mensagem) {
		return this.MensagemDeRetorno(codigo, mensagem, null);
	}

	public ResponseEntity<ResponseCliente> MensagemDeRetorno(int codigo, Object resultado) {
		return this.MensagemDeRetorno(codigo, null, resultado);
	}

	public ResponseEntity<ResponseCliente> MensagemDeRetorno(int codigo, String mensagem, Object resultado) {

		return this.MensagemDeRetorno(codigo, mensagem, resultado, null);
	}

	public ResponseEntity<ResponseCliente> MensagemDeRetorno(int codigo, String mensagem, Object resultado,PermissaoCurso permissao) {
		ResponseCliente c = new ResponseCliente();
		c.setResultado(resultado);
		c.setCodigo(ResponseCode.values()[codigo].getDescricao());
		c.setMensagem(mensagem);
		
		if (permissao != null)
			c.setPermissao(permissao.getPermissao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	public ResponseEntity<ResponseCliente> retornoLogin(int codigo, String mensagem, Object resultado,Permissao permissao, TipoUsuario tipoUsuario) {
		ResponseCliente c = new ResponseCliente();
		c.setResultado(resultado);
		c.setCodigo(ResponseCode.values()[codigo].getDescricao());
		c.setMensagem(mensagem);
		c.setPermissao(permissao);
		c.setTipoUsuario(tipoUsuario);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	//***
	public ResponseEntity<ResponseClienteReduzido> retornoLogin(int codigo, String mensagem) {
		ResponseClienteReduzido c = new ResponseClienteReduzido();
		c.setCodigo(ResponseCode.values()[codigo].getDescricao());
		c.setMensagem(mensagem);
		//c.setTipoUsuario(tipoUsuario);
		return new ResponseEntity<ResponseClienteReduzido>(c, HttpStatus.OK);
	}
}
