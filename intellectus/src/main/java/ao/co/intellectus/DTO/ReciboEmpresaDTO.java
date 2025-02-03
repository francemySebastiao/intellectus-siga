package ao.co.intellectus.DTO;

import java.util.List;

public class ReciboEmpresaDTO {

	private Integer idEmpresa;
	private String descricaoEmpresa;
	private List<ReciboEmpresaCliente> reciboEmpresaCliente;
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}
	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}
	public List<ReciboEmpresaCliente> getReciboEmpresaCliente() {
		return reciboEmpresaCliente;
	}
	public void setReciboEmpresaCliente(List<ReciboEmpresaCliente> reciboEmpresaCliente) {
		this.reciboEmpresaCliente = reciboEmpresaCliente;
	}
	
	
}
