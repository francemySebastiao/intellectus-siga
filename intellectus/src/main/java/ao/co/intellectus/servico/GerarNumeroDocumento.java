package ao.co.intellectus.servico;

import org.springframework.stereotype.Service;

@Service
public interface GerarNumeroDocumento {

	public String geracaoNumero();
	
	public String gerarNumeroFacturaProforma(String definitivo, Integer lectivo, Long proximoNumero);
	
	public String gerarNumeroFacturaRecibo(String definitivo, Integer lectivo, Long proximoNumero);
	
	public String gerarNumeroNotaCredito(String definitivo, Integer lectivo, Long proximoNumero);
	
	public String gerarNumeroFactura(String definitivo, Integer lectivo, Long proximoNumero);
	
	public String gerarNumeroRecibo(String definitivo, Integer lectivo, Long proximoNumero);
}
