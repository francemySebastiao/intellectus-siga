package ao.co.intellectus.DTO;

import java.util.List;

public class FacturaDetalhada {

	private String numeroFactura;
	
	private List<FacturaDetalheAlterada> facturaDetalhes;

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public List<FacturaDetalheAlterada> getFacturaDetalhes() {
		return facturaDetalhes;
	}

	public void setFacturaDetalhes(List<FacturaDetalheAlterada> facturaDetalhes) {
		this.facturaDetalhes = facturaDetalhes;
	}
	
	
}
