package ao.co.intellectus.servico;

import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.NotaCredito;

@Service
public interface GerarGuiaService {

	//public String gerarGuia();
	
	public void gerarFileNumeroProforma( Guia guia);
	
	public void gerarFileNumeroFacturaRecibo( Guia guia);
	
	public void gerarFileNotaCredito(NotaCredito notaCredito);
}
