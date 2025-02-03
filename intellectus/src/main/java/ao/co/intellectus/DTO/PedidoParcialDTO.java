package ao.co.intellectus.DTO;

import java.util.Date;

public class PedidoParcialDTO {
	private Integer declaracaoDestino;
	private Integer idPedido;
	private Date dataEmissao;
	public Integer getDeclaracaoDestino() {
		return declaracaoDestino;
	}
	public void setDeclaracaoDestino(Integer declaracaoDestino) {
		this.declaracaoDestino = declaracaoDestino;
	}
	
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	
}
