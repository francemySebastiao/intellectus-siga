package ao.co.intellectus.servico.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.enumeracao.TipoFatura;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarGuiaService;
import ao.co.intellectus.servico.GerarNumeroDocumento;
import ao.co.intellectus.util.FormataData;

@Service
public class GerarNumeroProformaImpl implements GerarGuiaService{
	
	@Autowired
	private GuiaPagamentoRepository repository;
	
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	
	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepository;
	
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;
	@Autowired
	private GeradorDeArquivo gerarDocService;
	
	FormataData forma = new FormataData();

	@Override
	public void gerarFileNumeroProforma(Guia saveGuia) {
		
		System.out.println("Está a chegar aqui");
		
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);
		
		String numero = "";

		System.out.println("Aqui também");
		AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
		if(anoActivo == null) {
			System.err.println("Valor nulo");
		}else {
			System.err.println("Passou");
		}
		System.out.println("But not here");
		/*String ano = String.valueOf(anoActivo.getAnoLectivo());
		String anoSubstring = ano.substring(2, 4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);*/

		NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
		Long proximoNumeroFP = numeroGeradoFP.getProximoNumero();
		System.out.println("Pagar proximo número " + proximoNumeroFP);
		// String numero = gerarNumeroDocService.geracaoNumero();
		numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumeroFP);

		Guia proformaExisteFP = this.repository.findProforma(numero);
		GuiaCandidatura proformaCandExist = guiaCandidaturaRepository.buscarProforma(numero);
		if (proformaExisteFP != null || proformaCandExist != null) {
			do {
				proximoNumeroFP++;

				numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumeroFP);
				proformaExisteFP = this.repository.findProforma(numero);
				proformaCandExist = guiaCandidaturaRepository.buscarProforma(numero);
			} while (proformaExisteFP != null || proformaCandExist != null);
		}

		saveGuia.setParaAcordoPagamento(false);
		saveGuia.setGeradaOnline(false);
		saveGuia.setGeradaReferencia(false);
		saveGuia.setGerouCredito(false);
		saveGuia.setDataSistema(dataSistema);
		saveGuia.setNumeroFacturaProforma(numero);
		saveGuia.setTipoFactura(TipoFatura.FACTURA_PROFORMA);
		saveGuia.setUltimaModificacao(new Date());

		Guia guiaGuardada = this.repository.save(saveGuia);

		this.gerarDocService.gerarFileProformaAluno(guiaGuardada);

		numeroGeradoFP.setUltimoNumero(proximoNumeroFP);
		numeroGeradoFP.setProximoNumero(proximoNumeroFP + 1);
		this.numeroGeradoRepository.save(numeroGeradoFP);
		//guiaGuardada.setTipoFactura(TipoFatura.FACTURA_PROFORMA);

		// SALVA NOVAMENTE A GUIA COM SEU NÚMERO
		this.repository.save(guiaGuardada);
		
	}

	@Override
	public void gerarFileNumeroFacturaRecibo(Guia guia) {
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        //String dataSistema = sdf.format(guia.getDataLiquidacao());
		
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);
		
		String numero = "";

		/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
		String ano = String.valueOf(anoActivo.getAnoLectivo());
		String anoSubstring = ano.substring(2, 4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);*/

		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(7);
		Long proximoNumero = numeroGerado.getProximoNumero();

		numero = gerarNumeroDocService.gerarNumeroFacturaRecibo(numero, forma.anoLectivo(), proximoNumero);

		Guia FacturaReciboExiste = this.repository.findFacturaRecibo(numero);
		GuiaCandidatura faturaReciboCandidatura = this.guiaCandidaturaRepository.buscarRecibo(numero);
		if (FacturaReciboExiste != null || faturaReciboCandidatura != null) {
			do {
				proximoNumero++;

				numero = gerarNumeroDocService.gerarNumeroFacturaRecibo(numero, forma.anoLectivo(), proximoNumero);
				FacturaReciboExiste = this.repository.findFacturaRecibo(numero);
				faturaReciboCandidatura = this.guiaCandidaturaRepository.buscarRecibo(numero);
			} while (FacturaReciboExiste != null || faturaReciboCandidatura != null);
		}
		guia.setDataEmissaoFr(guia.getDataEmicao());
		guia.setAcordo(false);
		//guia.setDataSistemaFr(guia.getDataSistema());
		//guia.setParaAcordoPagamento(false);
		//guia.setGeradaOnline(false);
		//guia.setGeradaReferencia(false);
		//guia.setGerouCredito(false);
		guia.setNumeroFacturaRecibo(numero);
		guia.setHashFacturaRecibo(numero);
		guia.getDataLiquidacao();
		guia.getId();

		Guia guiaGuardada = this.repository.save(guia);
		
		this.gerarDocService.gerarFileFacturaReciboAluno(guiaGuardada);

		numeroGerado.setUltimoNumero(proximoNumero);
		numeroGerado.setProximoNumero(proximoNumero + 1);
		this.numeroGeradoRepository.save(numeroGerado);
		
	}

}
