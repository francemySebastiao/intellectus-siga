package ao.co.intellectus.servico.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.NotaCredito;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.enumeracao.TipoDoc;
import ao.co.intellectus.model.enumeracao.TipoFatura;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.NotaCreditoRepository;
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
	
	@Autowired
	private NotaCreditoRepository notaCreditoRepo;
	
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
		
		/*LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);*/
		
		Instant instant = Instant.ofEpochMilli(guia.getDataLiquidacao().getTime());
	    LocalDateTime dataLiquidacao = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	    
	    LocalDateTime horaAtual = LocalDateTime.now();
	    
	    LocalDateTime dataHoraCombinada = LocalDateTime.of(dataLiquidacao.toLocalDate(), horaAtual.toLocalTime());
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	    String dataHoraFormatada = dataHoraCombinada.format(formatter);

		/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
		String ano = String.valueOf(anoActivo.getAnoLectivo());
		String anoSubstring = ano.substring(2, 4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);*/
	    
		String numero = "";

		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(7);
		Long proximoNumero = numeroGerado.getProximoNumero();

		numero = gerarNumeroDocService.gerarNumeroFacturaRecibo(numero, forma.anoLectivo(), proximoNumero);

		Guia FacturaReciboExiste = this.repository.findFacturaRecibo(numero);
		//GuiaCandidatura faturaReciboCandidatura = this.guiaCandidaturaRepository.buscarRecibo(numero);
		if (FacturaReciboExiste != null ) {
			do {
				proximoNumero++;

				numero = gerarNumeroDocService.gerarNumeroFacturaRecibo(numero, forma.anoLectivo(), proximoNumero);
				FacturaReciboExiste = this.repository.findFacturaRecibo(numero);
				//faturaReciboCandidatura = this.guiaCandidaturaRepository.buscarRecibo(numero);
			} while (FacturaReciboExiste != null);
		}

		//guia.setDataEmissaoFr(guia.getDataLiquidacao());
		//guia.setDataSistemaFr(dataHoraFormatada);
		guia.setParaAcordoPagamento(false);
		guia.setGeradaOnline(false);
		guia.setGeradaReferencia(false);
		guia.setGerouCredito(false);
		guia.setNumeroFacturaRecibo(numero);
		//guia.setTipoFactura(TipoFatura.FACTURA_RECIBO);

		Guia guiaGuardada = this.repository.save(guia);
		
		this.gerarDocService.gerarFileFacturaReciboAluno(guiaGuardada);

		numeroGerado.setUltimoNumero(proximoNumero);
		numeroGerado.setProximoNumero(proximoNumero + 1);
		this.numeroGeradoRepository.save(numeroGerado);
		
	}

	@Override
	public void gerarFileNotaCredito(NotaCredito notaCredito) {
		
		Instant instant = Instant.ofEpochMilli(notaCredito.getDataEmissao().getTime());
	    LocalDateTime dataEmissao = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	    
	    LocalDateTime horaAtual = LocalDateTime.now();
	    
	    LocalDateTime dataHoraCombinada = LocalDateTime.of(dataEmissao.toLocalDate(), horaAtual.toLocalTime());
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	    String dataHoraFormatada = dataHoraCombinada.format(formatter);

		String numero = "";

		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(10);
		Long proximoNumero = numeroGerado.getProximoNumero();

		numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, forma.anoLectivo(), proximoNumero);

		NotaCredito notaCreditoExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
		if (notaCreditoExiste != null) {
			do {
				proximoNumero++;

				numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, forma.anoLectivo(), proximoNumero);
				notaCreditoExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);

			} while (notaCreditoExiste != null);
		}

		//notaCredito.setDataSistema(dataHoraFormatada);
		notaCredito.setNumeroNotaCredito(numero);
		//notaCredito.setTipoDoc(TipoDoc.FACTURA_RECIBO);

		NotaCredito notaCreditoGuardada = this.notaCreditoRepo.save(notaCredito);
		
		this.gerarDocService.gerarFileNotaCredito(notaCreditoGuardada);

		numeroGerado.setUltimoNumero(proximoNumero);
		numeroGerado.setProximoNumero(proximoNumero + 1);
		this.numeroGeradoRepository.save(numeroGerado);
		
	}

}
