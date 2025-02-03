package ao.co.intellectus.servico;

import org.springframework.stereotype.Service;

import ao.co.intellectus.model.Factura;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.HistoricoCreditoEmpresa;
import ao.co.intellectus.model.NotaCredito;

@Service
public interface GeradorDeArquivo {

	public void gerarFileProformaCandidato( GuiaCandidatura guia);
	
	public void gerarFileFacturaReciboCandidato( GuiaCandidatura guia);
	
	public void gerarFileProformaAluno( Guia guia);
	
	public void gerarFileFacturaReciboAluno( Guia guia);
	
	public void gerarFileFacturaReciboCreditoAluno( HistoricoCreditoEmpresa credito);
	
	public void gerarFileNotaCredito(NotaCredito nota);
	
	public void gerarFileFactura(Factura factura);
	
}
