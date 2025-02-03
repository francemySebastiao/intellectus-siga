package ao.co.intellectus.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ao.co.intellectus.model.Usuario;

public class PedidoDocumentoCliente {
	private Integer idPedido;
	private Integer anoDeclaracao;
	private String declaracaoDestino;
	private Integer declaracaoDestinoId;
	private String tipoDeclaracao; 
	private boolean retirado;
	private boolean guiaPaga;
	@Temporal(TemporalType.DATE)
	private Date dataPedido;
	private String userEmitiu;
	
	
	public String getUserEmitiu() {
		return userEmitiu;
	}
	public void setUserEmitiu(String userEmitiu) {
		this.userEmitiu = userEmitiu;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Integer getAnoDeclaracao() {
		return anoDeclaracao;
	}
	public void setAnoDeclaracao(Integer anoDeclaracao) {
		this.anoDeclaracao = anoDeclaracao;
	}
	public String getDeclaracaoDestino() {
		return declaracaoDestino;
	}
	public void setDeclaracaoDestino(String declaracaoDestino) {
		this.declaracaoDestino = declaracaoDestino;
	}
	public String getTipoDeclaracao() {
		return tipoDeclaracao;
	}
	public void setTipoDeclaracao(String tipoDeclaracao) {
		this.tipoDeclaracao = tipoDeclaracao;
	}
	public boolean isRetirado() {
		return retirado;
	}
	public void setRetirado(boolean retirado) {
		this.retirado = retirado;
	}
	public boolean isGuiaPaga() {
		return guiaPaga;
	}
	public void setGuiaPaga(boolean guiaPaga) {
		this.guiaPaga = guiaPaga;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Integer getDeclaracaoDestinoId() {
		return declaracaoDestinoId;
	}
	public void setDeclaracaoDestinoId(Integer declaracaoDestinoId) {
		this.declaracaoDestinoId = declaracaoDestinoId;
	}
}
