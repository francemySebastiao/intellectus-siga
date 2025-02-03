package ao.co.intellectus.DTO.proxpay;

import java.util.ArrayList;
import java.util.List;

public class PagamentosInternoRetorno {
	private List<PagamentosInterno> pagamentosInterno=new ArrayList<PagamentosInterno>();

	public List<PagamentosInterno> getPagamentosInterno() {
		return pagamentosInterno;
	}

	public void setPagamentosInterno(List<PagamentosInterno> pagamentosInterno) {
		this.pagamentosInterno = pagamentosInterno;
	}
	
	
}
