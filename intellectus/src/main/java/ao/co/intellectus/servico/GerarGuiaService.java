package ao.co.intellectus.servico;

import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Guia;

@Service
public interface GerarGuiaService {

	//public String gerarGuia();
	
	public void gerarFileNumeroProforma( Guia guia);
	
	public void gerarFileNumeroFacturaRecibo( Guia guia);
}
