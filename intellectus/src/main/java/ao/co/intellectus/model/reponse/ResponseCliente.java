package ao.co.intellectus.model.reponse;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;

import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.TipoUsuario;

public class ResponseCliente {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object resultado;
	private String mensagem;
	private int codigo;
	private int quantidadeTotalItens;
	    
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Paginator paginator;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Enumerated(EnumType.STRING)
	private Permissao permissao;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUsuario;

	
	public ResponseCliente() {
	 super();
	}
	
	public ResponseCliente(Object resultado,int codigo,String mensagem,int quantidadeTotalItens) {
		this.resultado = resultado;
		this.mensagem = mensagem;
		this.codigo = codigo;
	}
		
	
	public Object getResultado() {
		return resultado;
	}

	public void setResultado(Object resultado) {
		this.resultado = resultado;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getQuantidadeTotalItens() {
		return quantidadeTotalItens;
	}

	public void setQuantidadeTotalItens(int quantidadeTotalItens) {
		this.quantidadeTotalItens = quantidadeTotalItens;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}
}