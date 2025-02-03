package ao.co.intellectus.DTO.Auditoria;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ao.co.intellectus.model.RegistroDocumentos;
@JsonInclude(Include.NON_NULL)
public class DeclaracaoAuditoriaDto {
	
	private Integer idPedido;
	private String userEmitiu;
	private boolean retirado;
	private boolean guiaPaga;
	private String dataSolicitacao;
	private String tipoDeclaracao;
	private String anoDeclaracao;
	private String motivoDoDocumento;
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public String getUserEmitiu() {
		return userEmitiu;
	}
	public void setUserEmitiu(String userEmitiu) {
		this.userEmitiu = userEmitiu;
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
	public String getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public String getTipoDeclaracao() {
		return tipoDeclaracao;
	}
	public void setTipoDeclaracao(String tipoDeclaracao) {
		this.tipoDeclaracao = tipoDeclaracao;
	}
	public String getAnoDeclaracao() {
		return anoDeclaracao;
	}
	public void setAnoDeclaracao(String anoDeclaracao) {
		this.anoDeclaracao = anoDeclaracao;
	}
	public String getMotivoDoDocumento() {
		return motivoDoDocumento;
	}
	public void setMotivoDoDocumento(String motivoDoDocumento) {
		this.motivoDoDocumento = motivoDoDocumento;
	}
	
	
}
