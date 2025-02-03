package ao.co.intellectus.DTO;

import java.util.List;

public class FacturaEmpresaDTO {

	private Integer idEmpresa;
	private String descricaoEmpresa;
	private List<FacturaEmpresaCliente> facturaEmpresaCliente;
	
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
	public List<FacturaEmpresaCliente> getFacturaEmpresaCliente() {
		return facturaEmpresaCliente;
	}
	public void setFacturaEmpresaCliente(List<FacturaEmpresaCliente> facturaEmpresaCliente) {
		this.facturaEmpresaCliente = facturaEmpresaCliente;
	}
	
	
	
}
