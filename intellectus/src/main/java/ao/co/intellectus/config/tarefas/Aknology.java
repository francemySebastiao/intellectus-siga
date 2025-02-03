/*package ao.co.intellectus.config.tarefas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.UnknownHostException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ao.co.intellectus.DTO.proxpay.Pagamentos;
import ao.co.intellectus.DTO.proxpay.RetornoPagamentos;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Banco;
import ao.co.intellectus.model.Bordero;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.PagamentosRemoto;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.enumeracao.TipoFatura;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.BancoRepository;
import ao.co.intellectus.repository.BorderoRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.PagamentosRemotoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarNumeroDocumento;

@Singleton
@Component
@EnableScheduling
public class Aknology {

	private RestTemplate rest;
	@Autowired
	private PagamentosRemotoRepository pagamentoRemotoRepository;
	@Autowired
	private GuiaPagamentoRepository guiaPagamentoRepo;
	@Autowired
	private BorderoRepository borderorepository;
	@Autowired
	private BancoRepository bancorepository;
	@Autowired
	private MoedaRepository moedarepository;
	@Autowired
	private AnoLectivoRepository anoLectivo;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;
	@Autowired
	private GeradorDeArquivo gerarDocService;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Value("${base.url}")
	private String baseUrl;

	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;

	@Scheduled(fixedDelay = MINUTO * 5)
	public void validaPagamentoGuia() throws UnknownHostException {
		rest = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<RetornoPagamentos> responseEntity = rest.exchange(baseUrl + "/fila-de-pagamentos?unidade=0010",
				HttpMethod.GET, requestEntity, RetornoPagamentos.class);

		Usuario usuario = this.usuarioRepository.findOne(47);

		if (responseEntity.getBody() != null) {

			RetornoPagamentos pagaBody = responseEntity.getBody();
			List<Pagamentos> pagamentos = pagaBody.getPayments();
			for (Pagamentos paga : pagamentos) {

				PagamentosRemoto pagamento = new PagamentosRemoto();
				pagamento.setBordereux(paga.getId());
				pagamento.setCompensado(true);
				pagamento.setDataExportado(new LocalDateTime().now().toString());
				pagamento.setDataCompensado(new LocalDateTime().now().toString());
				pagamento.setDataPagmento(paga.getDatetime());
				pagamento.setNumeroGuia(paga.getCustom_fields().getGuia());
				pagamento.setReferencia(paga.getReference_number().toString());

				BigDecimal valorArredondado = new BigDecimal(paga.getAmount()).setScale(2, RoundingMode.HALF_UP);

				pagamento.setValor(valorArredondado);
				pagamento.setNumeroAluno(paga.getCustom_fields().getNumeroDeAluno());
				pagamento.setTelefone(paga.getCustom_fields().getMobile());
				pagamento.setUnidade(paga.getCustom_fields().getUnidade());
				PagamentosRemoto salvo = this.pagamentoRemotoRepository.save(pagamento);

				Guia guiaPaga = guiaPagamentoRepo.findByNumeroGuia(paga.getCustom_fields().getGuia());

				if (guiaPaga != null) {

					if (!guiaPaga.isLiquidada()) {

						guiaPaga.setLiquidada(true);
						guiaPaga.setGerouCredito(false);
						guiaPaga.setBordero(paga.getReference_number().toString());
						guiaPaga.setUsuarioLiquidou(usuario);
						guiaPaga.setDataLiquidacao(new Date());

						Guia salva = this.guiaPagamentoRepo.save(guiaPaga);
						
						gerarFacturaRecibo(salva);

						Banco ba = bancorepository.BuscarID(21);
						Moeda moeda = moedarepository.buscarID(3);

						Bordero b = new Bordero();
						b.setAluno(salva.getAluno());
						b.setBanco(ba);
						b.setMoeda(moeda);
						b.setDataDeposito(new Date());
						b.setDataRegistro(new Date());
						b.setNumero(paga.getReference_number().toString());
						b.setGuia(salva);
						b.setValor(salva.getValor());

						this.borderorepository.save(b);
					}
				}

				if (salvo != null) {

					ResponseEntity<RetornoPagamentos> responseEntity1 = rest.exchange(
							baseUrl + "/remover-da-fila/" + paga.getId() + "?unidade=0010", HttpMethod.DELETE,
							requestEntity, RetornoPagamentos.class);
				}
			}
		}
	}

	private void gerarFacturaRecibo(Guia guia) {

		java.time.LocalDateTime localDate = java.time.LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		String numero = "";

		AnoLectivo anoActivo = anoLectivo.buscarPorEstado();
		String ano = String.valueOf(anoActivo.getAnoLectivo());
		String anoSubstring = ano.substring(2, 4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);

		NumeroGerado numeroGeradoFR = this.numeroGeradoRepository.findOne(7);
		Long proximoNumero = numeroGeradoFR.getProximoNumero();

		// String numero = gerarNumeroDocService.geracaoNumero();
		numero = gerarNumeroDocService.gerarNumeroFacturaRecibo(numero, anoLimpo, proximoNumero);

		Guia proformaExiste = this.guiaPagamentoRepo.findFacturaRecibo(numero);
		if (proformaExiste != null) {
			do {
				proximoNumero++;

				numero = gerarNumeroDocService.gerarNumeroFacturaRecibo(numero, anoLimpo, proximoNumero);
				proformaExiste = this.guiaPagamentoRepo.findFacturaRecibo(numero);
			} while (proformaExiste != null);
		}

		guia.setTipoFactura(TipoFatura.FACTURA_RECIBO);
		guia.setDataEmissaoFr(new Date());
		guia.setDataSistemaFr(dataSistema);
		guia.setNumeroFacturaRecibo(numero);
		guia.setUltimaModificacao(new Date());
		Guia guiaGuardada = this.guiaPagamentoRepo.save(guia);

		this.gerarDocService.gerarFileFacturaReciboAluno(guiaGuardada);

		numeroGeradoFR.setUltimoNumero(proximoNumero);
		numeroGeradoFR.setProximoNumero(proximoNumero + 1);
		this.numeroGeradoRepository.save(numeroGeradoFR);
	}
}
*/