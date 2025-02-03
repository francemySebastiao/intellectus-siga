package ao.co.intellectus.DTO.proxpay;

import java.util.ArrayList;
import java.util.List;

public class ReferenciasRetornoDTO {
	private List<ReferenciaRetornoDTO> referenciaRetornoDTO = new ArrayList<ReferenciaRetornoDTO>();

	public List<ReferenciaRetornoDTO> getReferenciaRetornoDTO() {
		return referenciaRetornoDTO;
	}

	public void setReferenciaRetornoDTO(List<ReferenciaRetornoDTO> referenciaRetornoDTO) {
		this.referenciaRetornoDTO = referenciaRetornoDTO;
	}	
}
