package ao.co.intellectus.DTO.proxpay;

import java.util.ArrayList;
import java.util.List;

public class RetornoPagamentosDTO {
	private List<PagamentosDTO> payments=new ArrayList<PagamentosDTO>();

	public List<PagamentosDTO> getPayments() {
		return payments;
	}

	public void setPayments(List<PagamentosDTO> payments) {
		this.payments = payments;
	}
	
	
}
