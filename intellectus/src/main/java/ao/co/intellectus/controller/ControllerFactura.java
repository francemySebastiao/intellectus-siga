package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AlunoResumoCliente;
import ao.co.intellectus.DTO.AnularFacturaDTO;
import ao.co.intellectus.DTO.FacturaDetalhada;
import ao.co.intellectus.DTO.FacturaDetalheAlterada;
import ao.co.intellectus.DTO.FacturaDetalheAlterar;
import ao.co.intellectus.DTO.FacturaEmpresaCliente;
import ao.co.intellectus.DTO.FacturaEmpresaDTO;
import ao.co.intellectus.DTO.FacturaLiquidacao;
import ao.co.intellectus.DTO.GerarFactura;
import ao.co.intellectus.DTO.ReciboEmpresaCliente;
import ao.co.intellectus.DTO.ReciboEmpresaDTO;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Banco;
import ao.co.intellectus.model.Bordero;
import ao.co.intellectus.model.ContaCorrenteEmpresa;
import ao.co.intellectus.model.EmpresaConvenio;
import ao.co.intellectus.model.Factura;
import ao.co.intellectus.model.FacturaDetalhe;
import ao.co.intellectus.model.HistoricoCreditoEmpresa;
import ao.co.intellectus.model.NotaCredito;
import ao.co.intellectus.model.NotaCreditoDetalhe;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.Recibo;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.enumeracao.EstadoFactura;
import ao.co.intellectus.model.enumeracao.TipoDoc;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.BancoRepository;
import ao.co.intellectus.repository.BorderoRepository;
import ao.co.intellectus.repository.ContaCorreteEmpresaRepository;
import ao.co.intellectus.repository.EmpresaConvenioRepository;
import ao.co.intellectus.repository.FacturaDetalheRepository;
import ao.co.intellectus.repository.FacturaRepository;
import ao.co.intellectus.repository.HistoricoCreditoEmpresaRepository;
import ao.co.intellectus.repository.NotaCreditoDetalheRepository;
import ao.co.intellectus.repository.NotaCreditoRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.ReciboRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarNumeroDocumento;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.util.FormataData;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/factura")
public class ControllerFactura {

	@Autowired
	private EmpresaConvenioRepository convenioRepo;
	@Autowired
	private AnoLectivoRepository anoLectivoRepo;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private FacturaRepository facturaRepo;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private FacturaDetalheRepository facturaDetalheRepo;
	@Autowired
	private ReciboRepository reciboRepo;
	@Autowired
	private BorderoRepository borderoRepository;
	@Autowired
	private BancoRepository bancoRepository;
	@Autowired
	private NotaCreditoRepository notaCreditoRepo;
	@Autowired
	private NotaCreditoDetalheRepository notaCreditoDetalheRepo;
	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;
	@Autowired
	private GeradorDeArquivo gerarDocService;
	@Autowired
	private ContaCorreteEmpresaRepository contaCorrenteEmpresaRepo;
	@Autowired
	private HistoricoCreditoEmpresaRepository histCreditoEmpresaRepo;

	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}
	
	FormataData forma = new FormataData();

	@RequestMapping(value = "/gerarFactura", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> gerarFactura(@RequestBody GerarFactura factura) {

		ResponseCliente c = new ResponseCliente();

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		EmpresaConvenio empresa = this.convenioRepo.findOne(factura.getEmpresa());

		Usuario usuario = this.usuarioRepository
				.findByUserName(factura.getUserName() != null ? factura.getUserName() : null);

		AnoLectivo anoActivo = anoLectivoRepo.buscarPorEstado();

		String numero = "";

		/*String ano = String.valueOf(anoActivo.getAnoLectivo());
		String anoSubstring = ano.substring(2, 4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);*/

		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(8);
		Long proximoNumero = numeroGerado.getProximoNumero();

		numero = gerarNumeroDocService.gerarNumeroFactura(numero, forma.anoLectivo(), proximoNumero);

		Factura facturaExiste = this.facturaRepo.buscarNumeroFactura(numero);
		if (facturaExiste != null) {
			do {
				proximoNumero++;

				numero = gerarNumeroDocService.gerarNumeroFactura(numero, forma.anoLectivo(), proximoNumero);
				facturaExiste = this.facturaRepo.buscarNumeroFactura(numero);
			} while (facturaExiste != null);
		}

		Factura fact = new Factura();
		fact.setAlterada(false);
		fact.setLiquidacao(false);
		fact.setDataEmissao(new Date());
		fact.setUsuarioEmitiu(usuario);
		fact.setDataSistema(dataSistema);
		fact.setEmpresaConvenio(empresa);
		fact.setDataVencimento(factura.getDataVencimento());

		Factura facturaSalva = this.facturaRepo.save(fact);

		List<AlunoResumoCliente> valorBolseiro = factura.getBolseiros();
		FacturaDetalhe facturaDetalhe;

		double valorFactura = 0;
		for (AlunoResumoCliente bolseiroCliente : valorBolseiro) {
			facturaDetalhe = new FacturaDetalhe();

			facturaDetalhe.setIdFactura(facturaSalva);
			facturaDetalhe.setCodigoIva("M21");
			facturaDetalhe.setPercentagemIva("0");
			facturaDetalhe.setNomeAluno(bolseiroCliente.getNome() + "-" + bolseiroCliente.getId());
			facturaDetalhe.setDescricao(bolseiroCliente.getDescricao());
			facturaDetalhe.setQuantidade(bolseiroCliente.getQuantidade());
			facturaDetalhe.setPrecoUnitario(bolseiroCliente.getValor());
			facturaDetalhe.setValorImposto(0);
			facturaDetalhe.setNumeroAluno(bolseiroCliente.getId());

			double valorQuantidade = bolseiroCliente.getQuantidade() * bolseiroCliente.getValor();
			double valorTotalDesconto = valorQuantidade - bolseiroCliente.getValorDesconto();

			facturaDetalhe.setDesconto(bolseiroCliente.getValorDesconto());
			facturaDetalhe.setValorSemDesconto(valorQuantidade);
			facturaDetalhe.setValorTotal(valorTotalDesconto);

			valorFactura += valorTotalDesconto;
			this.facturaDetalheRepo.save(facturaDetalhe);
		}

		facturaSalva.setNuneroFactura(numero);
		Factura facSave = this.facturaRepo.save(fact);

		facSave.setEstadoFactura(EstadoFactura.EMITIDA);
		facSave.setValor(valorFactura);
		Factura salva = this.facturaRepo.save(facSave);

		this.gerarDocService.gerarFileFactura(salva);

		numeroGerado.setUltimoNumero(proximoNumero);
		numeroGerado.setProximoNumero(proximoNumero + 1);
		this.numeroGeradoRepository.save(numeroGerado);

		c.setResultado(facSave);
		c.setMensagem("Factura emitida com sucesso. Nº: " + numero);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarFacturaPorEmpresa", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> facturaPorEmpresa(@RequestParam Integer empresa) {

		ResponseCliente c = new ResponseCliente();

		EmpresaConvenio empresaEncontrada = this.convenioRepo.findById(empresa);

		if (empresaEncontrada == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Esta empresa não existe.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<Factura> facturaEncontradas = this.facturaRepo.buscarFacturasEmpresas(empresa);
		if (facturaEncontradas.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Não existem facturas relacionadas a esta empresa.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<FacturaEmpresaCliente> fClinetes = new ArrayList<FacturaEmpresaCliente>();
		FacturaEmpresaCliente fEmpresa;
		FacturaEmpresaDTO facturaEmpresas = new FacturaEmpresaDTO();

		int a = facturaEncontradas.size();
		int b = facturaEncontradas.size();

		boolean pegar;

		// LOGICA ANTIGA PARA ORGANIZAR AS FACTURAS...
		for (Factura factura : facturaEncontradas) {
			fEmpresa = new FacturaEmpresaCliente();
			// BeanUtils.copyProperties(factura, fEmpresa, "Aluno", "AnoLectivo");
			NotaCredito notaCredito = notaCreditoRepo.buscarFactura(factura.getId());
			if (notaCredito != null) {
				fEmpresa.setValorNotaCredito(notaCredito.getValor());
			}
			if (factura.getEstadoFactura() == EstadoFactura.ANULADA
					|| factura.getEstadoFactura() == EstadoFactura.ALTERADA) {
				fEmpresa.setNumeroNotaCredito(notaCredito.getNumeroNotaCredito());
			}
			fEmpresa.setIdFactura(factura.getId());
			fEmpresa.setCodigoEmpresa(factura.getEmpresaConvenio().getId());
			fEmpresa.setNumeroFactura(factura.getNuneroFactura());
			fEmpresa.setDataLiquidacao(factura.getDataLiquidacao());
			fEmpresa.setDataAnulacao(factura.getDataAnulacao());
			fEmpresa.setValorFactura(factura.getValor());
			fEmpresa.setLiquidacao(factura.getLiquidacao());
			fEmpresa.setEstadoFactura(factura.getEstadoFactura().getDescricao());
			fEmpresa.setDataEmissao(factura.getDataEmissao());
			fEmpresa.setDataVencimento(factura.getDataVencimento());

			pegar = a == b ? true : false;
			a--;
			fEmpresa.setLiquidacao(pegar);
			fClinetes.add(fEmpresa);
		}

		facturaEmpresas.setIdEmpresa(empresaEncontrada.getId());
		facturaEmpresas.setDescricaoEmpresa(empresaEncontrada.getDesignacao());
		facturaEmpresas.setFacturaEmpresaCliente(fClinetes);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(facturaEmpresas);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarDatalheFactura", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> detalheFactura(@RequestParam Long factura) {

		ResponseCliente c = new ResponseCliente();

		Factura fatura = this.facturaRepo.buscarId(factura);

		if (fatura == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Esta factura não existe.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<FacturaDetalhe> facturaDetalhe = this.facturaDetalheRepo.buscarIdFactura(factura);

		if (facturaDetalhe.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Esta empresa não existe.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<FacturaDetalheAlterada> fClinetes = new ArrayList<FacturaDetalheAlterada>();
		FacturaDetalheAlterada fEmpresa;
		FacturaDetalhada facturaEmpresas = new FacturaDetalhada();

		// LOGICA ANTIGA PARA ORGANIZAR AS FACTURAS...
		for (FacturaDetalhe facturaDeta : facturaDetalhe) {
			fEmpresa = new FacturaDetalheAlterada();
			// BeanUtils.copyProperties(factura, fEmpresa, "Aluno", "AnoLectivo");
			fEmpresa.setCodigo(facturaDeta.getCodigo());
			fEmpresa.setCodigoIva(facturaDeta.getCodigoIva());
			fEmpresa.setDesconto(facturaDeta.getDesconto());
			fEmpresa.setNomeAluno(facturaDeta.getNomeAluno());
			fEmpresa.setNumeroAluno(facturaDeta.getNumeroAluno());
			fEmpresa.setDescricao(facturaDeta.getDescricao());
			fEmpresa.setPercentagemIva(facturaDeta.getPercentagemIva());
			fEmpresa.setPrecoUnitario(facturaDeta.getPrecoUnitario());
			fEmpresa.setQuantidade(facturaDeta.getQuantidade());
			fEmpresa.setValorImposto(facturaDeta.getValorImposto());
			fEmpresa.setValorTotal(facturaDeta.getValorTotal());

			fClinetes.add(fEmpresa);
		}

		facturaEmpresas.setNumeroFactura(fatura.getNuneroFactura());
		;
		facturaEmpresas.setFacturaDetalhes(fClinetes);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(facturaEmpresas);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarReciboPorEmpresa", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> ReciboPorEmpresa(@RequestParam Integer empresa) {

		ResponseCliente c = new ResponseCliente();

		EmpresaConvenio empresaEncontrada = this.convenioRepo.findById(empresa);

		if (empresaEncontrada == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Esta empresa não existe.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<Recibo> RecibosEncontrados = this.reciboRepo.buscarRecibosEmpresa(empresa);
		if (RecibosEncontrados.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Não existem recibos relacionadas a esta empresa.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<ReciboEmpresaCliente> rClinetes = new ArrayList<ReciboEmpresaCliente>();
		ReciboEmpresaCliente rEmpresa;
		ReciboEmpresaDTO reciboEmpresas = new ReciboEmpresaDTO();

		// LOGICA ANTIGA PARA ORGANIZAR AS FACTURAS...
		for (Recibo recibo : RecibosEncontrados) {
			rEmpresa = new ReciboEmpresaCliente();
			// BeanUtils.copyProperties(factura, fEmpresa, "Aluno", "AnoLectivo");

			rEmpresa.setIdRecibo(recibo.getId());
			rEmpresa.setCodigoEmpresa(recibo.getIdFactura().getEmpresaConvenio().getId());
			rEmpresa.setNumeroRecibo(recibo.getNumeroRecibo());
			rEmpresa.setEstadoRecibo(recibo.getEstadoRecibo().getDescricao());
			rEmpresa.setValorPago(recibo.getValorPago());
			rEmpresa.setDataEmissao(recibo.getDataEmissao());
			rEmpresa.setDataAnulacao(recibo.getDataAnulacao());
			rEmpresa.setAlterada(recibo.getAlterada());
			rEmpresa.setDataAlteracao(recibo.getDataAlteracao());

			rClinetes.add(rEmpresa);
		}

		reciboEmpresas.setIdEmpresa(empresaEncontrada.getId());
		reciboEmpresas.setDescricaoEmpresa(empresaEncontrada.getDesignacao());
		reciboEmpresas.setReciboEmpresaCliente(rClinetes);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Pesquisa feita.");
		c.setResultado(reciboEmpresas);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/liquidacao", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> liquidarFactura(@RequestBody FacturaLiquidacao liquidacao) {

		ResponseCliente c = new ResponseCliente();

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		AnoLectivo ano = this.anoLectivoRepo.buscarPorEstado();
		Usuario usuario = this.usuarioRepository
				.findByUserName(liquidacao.getUserName() != null ? liquidacao.getUserName() : null);
		Bordero borderoExterno = this.borderoRepository.findByNumeroAndBancoId(liquidacao.getNumeroOperacao(),
				liquidacao.getBanco());
		Banco banco = this.bancoRepository.findOne(liquidacao.getBanco());
		Factura facturaPesq = facturaRepo.buscarId(liquidacao.getIdFactura());

		try {

			if (borderoExterno != null) {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setMensagem("Este número de operação já foi usado.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			// Buscar Factura a ser liquidada
			Factura factura = this.facturaRepo.buscarId(liquidacao.getIdFactura());

			Date dataAtual = new Date();

			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			String actual = formato.format(dataAtual);
			String vencida = formato.format(factura.getDataVencimento());

			LocalDate dataVencimento = LocalDate.parse(vencida);
			LocalDate dataHoje = LocalDate.parse(actual);

			if (dataHoje.isAfter(dataVencimento)) {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setMensagem("Esta factura já tem a data vencida");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			factura.setEstadoFactura(EstadoFactura.PAGA);
			factura.setLiquidacao(true);
			factura.setDataLiquidacao(new Date());
			factura.setUsuarioLiquidou(usuario);
			Factura faturaPaga = facturaRepo.save(factura);

			String numero = "";

			/*String anoCivil = String.valueOf(ano.getAnoLectivo());
			String anoSubstring = anoCivil.substring(2, 4);
			Integer anoLimpo = Integer.parseInt(anoSubstring);*/

			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(9);
			Long proximoNumero = numeroGerado.getProximoNumero();

			numero = gerarNumeroDocService.gerarNumeroRecibo(numero, forma.anoLectivo(), proximoNumero);

			Recibo reciboExiste = this.reciboRepo.buscarNumeroRecibo(numero);
			if (reciboExiste != null) {
				do {
					proximoNumero++;

					numero = gerarNumeroDocService.gerarNumeroRecibo(numero, forma.anoLectivo(), proximoNumero);
					reciboExiste = this.reciboRepo.buscarNumeroRecibo(numero);
				} while (reciboExiste != null);
			}

			Recibo recibo = new Recibo();
			recibo.setIdFactura(faturaPaga);

			double valorDouble = liquidacao.getValorPago();

			recibo.setValorPago(valorDouble);
			recibo.setEstadoRecibo(EstadoFactura.EMITIDA);
			recibo.setNumeroOperacao(liquidacao.getNumeroOperacao());
			recibo.setUsuarioEmitiu(usuario);
			recibo.setDataEmissao(new Date());
			recibo.setDataSistema(dataSistema);
			recibo.setNumeroRecibo(numero);
			recibo.setAlterada(false);
			recibo.setEmpresaConvenio(faturaPaga.getEmpresaConvenio());
			Recibo reciboSalvo = reciboRepo.save(recibo);

			numeroGerado.setUltimoNumero(proximoNumero);
			numeroGerado.setProximoNumero(proximoNumero + 1);
			this.numeroGeradoRepository.save(numeroGerado);

			Bordero bordero = new Bordero();

			bordero.setEmpresa(facturaPesq.getEmpresaConvenio());
			bordero.setFactura(facturaPesq);
			bordero.setBanco(banco);
			bordero.setDataRegistro(new Date());
			bordero.setValor(liquidacao.getValorPago());
			bordero.setNumero(liquidacao.getNumeroOperacao());
			bordero.setMoeda(banco.getMoeda());
			this.borderoRepository.save(bordero);

			HistoricoCreditoEmpresa hCredito = new HistoricoCreditoEmpresa();

			hCredito.setEmpresa(factura.getEmpresaConvenio());
			hCredito.setAnoLectivo(ano);
			hCredito.setBanco(banco);
			hCredito.setBordero(bordero);
			hCredito.setBorderoExterno(liquidacao.getNumeroOperacao());
			hCredito.setValorDeposito(liquidacao.getValorPago());
			hCredito.setUsuarioEmitiu(usuario);

			gerarNumeroGuia(ano, hCredito);

			hCredito.setDataRegisto(new Date());
			this.histCreditoEmpresaRepo.save(hCredito);

			// ContaCorrenteAluno contaC = new ContaCorrenteAluno();

			ContaCorrenteEmpresa contaPesquisada = this.contaCorrenteEmpresaRepo
					.buscarEmpresa(factura.getEmpresaConvenio().getId());

			contaPesquisada.setEmpresa(factura.getEmpresaConvenio());
			contaPesquisada.setValorAnterior(contaPesquisada.getValor());
			contaPesquisada.setValor(contaPesquisada.getValor() + liquidacao.getValorPago());
			contaPesquisada.setDataMovimento(new Date());
			contaCorrenteEmpresaRepo.save(contaPesquisada);

			c.setResultado(reciboSalvo);
			c.setMensagem("Factura Liquidada com sucesso.");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

		} catch (Exception ex) {
			c.setMensagem(ex.getMessage() + " Erro do back");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/anularFactura", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> anularFactura(@RequestBody AnularFacturaDTO anularF) {

		ResponseCliente c = new ResponseCliente();

		AnoLectivo ano = this.anoLectivoRepo.buscarPorEstado();

		Factura factura = this.facturaRepo.buscarNumeroFactura(anularF.getNumero());

		Usuario usuario = this.usuarioRepository
				.findByUserName(anularF.getUserName() != null ? anularF.getUserName() : null);

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		String numero = "";

		/*String anoCivil = String.valueOf(ano.getAnoLectivo());
		String anoSubstring = anoCivil.substring(2, 4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);*/

		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(2006);
		Long proximoNumero = numeroGerado.getProximoNumero();

		numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, forma.anoLectivo(), proximoNumero);

		NotaCredito notaExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
		if (notaExiste != null) {
			do {
				proximoNumero++;

				numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, forma.anoLectivo(), proximoNumero);
				notaExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
			} while (notaExiste != null);
		}

		Factura facturaPesq = facturaRepo.buscarId(anularF.getIdFactura());
		if (facturaPesq != null && facturaPesq.getEstadoFactura() == EstadoFactura.PAGA) {
			c.setMensagem("Não pode anular uma factura paga.");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		factura.setEstadoFactura(EstadoFactura.ANULADA);
		factura.setDataAnulacao(new Date());
		factura.setUsuarioAnulou(usuario);
		factura.setMotivoAnulacao(anularF.getMotivoAnulacao());
		factura.setAlterada(false);

		Factura facturaSalva = facturaRepo.save(factura);

		NotaCredito notaCredito = new NotaCredito();

		notaCredito.setIdFactura(facturaSalva);
		notaCredito.setNumeroNotaCredito(numero);
		notaCredito.setTipoDoc(TipoDoc.FACTURA);
		notaCredito.setAlteracao(false);
		notaCredito.setValor(facturaSalva.getValor());
		notaCredito.setDataEmissao(new Date());
		notaCredito.setDataSistema(dataSistema);
		notaCredito.setUsuarioEmitiu(usuario);

		NotaCredito notaCreditoNC = notaCreditoRepo.save(notaCredito);

		this.gerarDocService.gerarFileNotaCredito(notaCreditoNC);

		numeroGerado.setUltimoNumero(proximoNumero);
		numeroGerado.setProximoNumero(proximoNumero + 1);
		this.numeroGeradoRepository.save(numeroGerado);

		c.setResultado(notaCreditoNC.getNumeroNotaCredito());
		c.setMensagem("Factura " + notaCreditoNC.getNumeroNotaCredito() + " anulada com sucesso.");
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/anularRecibo", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> anularRecibo(@RequestBody AnularFacturaDTO anularF) {

		ResponseCliente c = new ResponseCliente();

		Recibo recibo = this.reciboRepo.buscarNumeroRecibo(anularF.getNumero());
		AnoLectivo anoLectivo = this.anoLectivoRepo.buscarPorEstado();

		Usuario usuario = this.usuarioRepository
				.findByUserName(anularF.getUserName() != null ? anularF.getUserName() : null);
		ContaCorrenteEmpresa contaCorrente;

		String numero = "";

		/*String anoCivil = String.valueOf(anoLectivo.getAnoLectivo());
		String anoSubstring = anoCivil.substring(2, 4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);*/

		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(2006);
		Long proximoNumero = numeroGerado.getProximoNumero();

		numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, forma.anoLectivo(), proximoNumero);

		NotaCredito notaExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
		if (notaExiste != null) {
			do {
				proximoNumero++;

				numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, forma.anoLectivo(), proximoNumero);
				notaExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
			} while (notaExiste != null);
		}

		contaCorrente = this.contaCorrenteEmpresaRepo.buscarEmpresa(recibo.getIdFactura().getEmpresaConvenio().getId());
		if (contaCorrente.getValor() < recibo.getValorPago()) {
			c.setMensagem("Valor em crédito insuficinete.");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		Factura facturaAnular = facturaRepo.buscarId(recibo.getIdFactura().getId());
		
		if (facturaAnular.isAlterada()) {

			c.setMensagem("O Recibo Ref. a factura " + recibo.getIdFactura().getNuneroFactura() + " não pode ser anulada.");
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		recibo.setEstadoRecibo(EstadoFactura.ANULADA);
		recibo.setDataAnulacao(new Date());
		recibo.setUsuarioAnulou(usuario);
		recibo.setMotivoAnulacao(anularF.getMotivoAnulacao());

		Recibo reciboSalvo = reciboRepo.save(recibo);

		Bordero bordero = this.borderoRepository.buscarIdFactura(recibo.getIdFactura().getId());

		if (bordero != null)
			bordero.setNumero("0");
		bordero.setFactura(null);
		borderoRepository.save(bordero);

		NotaCredito notaCredito = new NotaCredito();

		notaCredito.setIdRecibo(reciboSalvo);
		//notaCredito.setIdFactura(facturaAnular);
		notaCredito.setValor(reciboSalvo.getValorPago());
		notaCredito.setNumeroNotaCredito(numero);
		notaCredito.setTipoDoc(TipoDoc.RECIBO);
		notaCredito.setDataEmissao(new Date());
		notaCredito.setUsuarioEmitiu(usuario);
		notaCredito.setAlteracao(false);

		NotaCredito salvo = this.notaCreditoRepo.save(notaCredito);

		this.gerarDocService.gerarFileNotaCredito(salvo);

		numeroGerado.setUltimoNumero(proximoNumero);
		numeroGerado.setProximoNumero(proximoNumero + 1);
		this.numeroGeradoRepository.save(numeroGerado);

		// VALOR
		Double valorAntigo = contaCorrente.getValor();
		double valorNovo = valorAntigo - recibo.getValorPago();
		// ACERTO DE CONTA
		contaCorrente.setValorAnterior(valorAntigo);
		contaCorrente.setValor(valorNovo);
		contaCorrente.setDataMovimento(new Date());
		this.contaCorrenteEmpresaRepo.save(contaCorrente);

		// FAZER HISTORICO DE ENTRADA
		HistoricoCreditoEmpresa historicoCredito = new HistoricoCreditoEmpresa();
		historicoCredito.setEmpresa(recibo.getIdFactura().getEmpresaConvenio());
		historicoCredito.setAnoLectivo(anoLectivo);
		historicoCredito.setUsuarioEmitiu(usuario);
		float fValor = (float) recibo.getIdFactura().getValor();
		historicoCredito.setValorDeposito(fValor);
		historicoCredito.setDataRegisto(new Date());
		historicoCredito.setBorderoInterno("EXTORN" + recibo.getNumeroRecibo());
		historicoCredito.setBorderoExterno("EXTORN" + recibo.getNumeroRecibo());
		historicoCredito.setUsuarioEmitiu(usuario);

		this.histCreditoEmpresaRepo.save(historicoCredito);

		c.setResultado(salvo.getNumeroNotaCredito());
		c.setMensagem("Recibo referente a " + recibo.getIdFactura().getNuneroFactura() + " anulada com sucesso.");
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/alterarFactura", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> alterar(@RequestBody FacturaDetalheAlterar alterar) {
		ResponseCliente c = new ResponseCliente();

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		AnoLectivo anoLectivo = this.anoLectivoRepo.buscarPorEstado();

		Factura factura = this.facturaRepo.buscarNumeroFactura(alterar.getNumeroFactura());

		if (factura.getEstadoFactura() == EstadoFactura.ALTERADA) {
			c.setCodigo(300);
			c.setMensagem("Esta factura já foi alterado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<FacturaDetalhe> facturaDetalhe = this.facturaDetalheRepo.buscarIdFactura(alterar.getIdFactura());

		if (facturaDetalhe.isEmpty()) {
			c.setCodigo(300);
			c.setMensagem("Esta factura não encontrada.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		Usuario usuario = this.usuarioRepository
				.findByUserName(alterar.getUserName() != null ? alterar.getUserName() : null);

		String numero = "";

		/*String anoCivil = String.valueOf(anoLectivo.getAnoLectivo());
		String anoSubstring = anoCivil.substring(2, 4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);*/

		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(2006);
		Long proximoNumero = numeroGerado.getProximoNumero();

		numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, forma.anoLectivo(), proximoNumero);

		NotaCredito notaExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
		if (notaExiste != null) {
			do {
				proximoNumero++;

				numero = gerarNumeroDocService.gerarNumeroNotaCredito(numero, forma.anoLectivo(), proximoNumero);
				notaExiste = this.notaCreditoRepo.buscarNumeroNotaCredito(numero);
			} while (notaExiste != null);
		}

		factura.setMotivoAnulacao(alterar.getMotivoAnulacao());
		factura.setAlterada(true);
		factura.setEstadoFactura(EstadoFactura.ALTERADA);
		factura.setDataAlteracao(new Date());
		factura.setUsuarioAlterou(usuario);
		Factura novaFactura = this.facturaRepo.save(factura);

		NotaCredito notaCreditoInst = new NotaCredito();

		notaCreditoInst.setAlteracao(true);
		notaCreditoInst.setIdFactura(novaFactura);
		notaCreditoInst.setNumeroNotaCredito(numero);
		notaCreditoInst.setValor(alterar.getValorTotalFactura());
		notaCreditoInst.setTipoDoc(TipoDoc.FACTURA);
		notaCreditoInst.setDataEmissao(new Date());
		notaCreditoInst.setDataSistema(dataSistema);
		notaCreditoInst.setUsuarioEmitiu(usuario);

		NotaCredito salvo = this.notaCreditoRepo.save(notaCreditoInst);

		this.gerarDocService.gerarFileNotaCredito(salvo);

		numeroGerado.setUltimoNumero(proximoNumero);
		numeroGerado.setProximoNumero(proximoNumero + 1);
		this.numeroGeradoRepository.save(numeroGerado);

		List<FacturaDetalheAlterada> fDetalhadaAlterada = alterar.getFacturaAlterada();

		for (FacturaDetalheAlterada fat : fDetalhadaAlterada) {

			NotaCreditoDetalhe notaCredito = new NotaCreditoDetalhe();

			notaCredito.setAlteracao(true);
			notaCredito.setCodigo(fat.getCodigo());
			notaCredito.setCodigoIva(fat.getCodigoIva());
			notaCredito.setDescricao(fat.getDescricao());
			notaCredito.setPercentagemIva(fat.getPercentagemIva());
			notaCredito.setPrecoUnitario(fat.getPrecoUnitario());
			notaCredito.setQuantidade(fat.getQuantidade());
			notaCredito.setValorImposto(fat.getValorImposto());
			notaCredito.setValorTotal(fat.getValorTotal());
			notaCredito.setIdFactura(novaFactura);
			notaCredito.setUsuarioEmitiu(usuario);
			notaCredito.setDesconto(fat.getDesconto());
			notaCredito.setIdNotaCredito(salvo);
			notaCredito.setNumeroNotaCredito(salvo.getNumeroNotaCredito());
			notaCredito.setNumeroAluno(fat.getNumeroAluno());

			this.notaCreditoDetalheRepo.save(notaCredito);

		}

		c.setResultado(salvo.getNumeroNotaCredito());
		c.setCodigo(200);
		c.setMensagem("Nota de crédito" + salvo.getNumeroNotaCredito() + " gerada com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/buscarNotaCreditoPorEmpresa", method =
	 * RequestMethod.GET, produces = "application/json")
	 * 
	 * @ResponseBody
	 * 
	 * @CrossOrigin(origins = "*") public ResponseEntity<ResponseCliente>
	 * NotaCreditoPorEmpresa(@RequestParam Integer empresa) {
	 * 
	 * ResponseCliente c = new ResponseCliente();
	 * 
	 * EmpresaConvenio empresaEncontrada = this.convenioRepo.findById(empresa);
	 * 
	 * if (empresaEncontrada == null) {
	 * c.setCodigo(ResponseCode.values()[2].getDescricao());
	 * c.setMensagem("Esta empresa não existe."); return new
	 * ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
	 * 
	 * List<NotaCredito> notaCreditoEncontradas =
	 * this.notaCreditoRepo.buscarNotaCredito(empresa); if
	 * (notaCreditoEncontradas.isEmpty()) {
	 * c.setCodigo(ResponseCode.values()[2].getDescricao());
	 * c.setMensagem("Não existe nota de crédito relacionada a esta empresa.");
	 * return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
	 * 
	 * List<NotaCreditoEmpresaCliente> ntClinetes = new
	 * ArrayList<NotaCreditoEmpresaCliente>(); NotaCreditoEmpresaCliente ntEmpresa;
	 * NotaCreditoEmpresaDTO notaCreditoEmpresas = new NotaCreditoEmpresaDTO();
	 * 
	 * 
	 * for (NotaCredito credito : notaCreditoEncontradas) { ntEmpresa = new
	 * NotaCreditoEmpresaCliente();
	 * 
	 * ntEmpresa.setIdNotaCredito(credito.getId());
	 * ntEmpresa.setCodigoEmpresa(empresa);
	 * ntEmpresa.setNomeEmpresa(empresaEncontrada.getDesignacao());
	 * ntEmpresa.setIdGuiaCandidatura(credito.getIdGuiaCandidatura().getId());
	 * ntEmpresa.setIdGuia(credito.getIdGuia().getId());
	 * ntEmpresa.setIdFactura(credito.getIdFactura().getId());
	 * ntEmpresa.setIdRecibo(credito.getIdRecibo().getId());
	 * ntEmpresa.setNumeroNotaCredito(credito.getNumeroNotaCredito());
	 * ntEmpresa.setAlteracao(credito.getAlteracao());
	 * ntEmpresa.setValor(credito.getValor());
	 * ntEmpresa.setDataEmissao(credito.getDataEmissao());
	 * 
	 * ntClinetes.add(ntEmpresa); }
	 * 
	 * notaCreditoEmpresas.setNotaCreditoEmpresaCliente(ntClinetes);
	 * 
	 * c.setCodigo(ResponseCode.values()[0].getDescricao());
	 * c.setMensagem("Pesquisa feita."); c.setResultado(notaCreditoEmpresas); return
	 * new ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
	 */

	@GetMapping("/relatorio/factura")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioFactura(@RequestParam String n_factura, @RequestParam String condicao)
			throws Exception {
		byte[] relatrio = servicoFactura(n_factura, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoFactura(String n_factura, String condicao) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_factura", n_factura);
		paramets.put("condicao", condicao);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Factura__Empresa.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		// DESCONECTAR ...

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/relatorio/recibo")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioRecibo(@RequestParam String n_recibo, @RequestParam String condicao)
			throws Exception {
		byte[] relatrio = servicoRecibo(n_recibo, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoRecibo(String n_recibo, String condicao) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_recibo", n_recibo);
		paramets.put("condicao", condicao);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Recibo.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		// DESCONECTAR ...

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/relatorio/notaCredito")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioNotaCredito(@RequestParam String n_notaCredito,
			@RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoNotaCredito(n_notaCredito, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoNotaCredito(String n_notaCredito, String condicao) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_nota_de_credito", n_notaCredito);
		paramets.put("condicao", condicao);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Nota_de_Credito.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		// DESCONECTAR ...

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	private String gerarNumeroBordereuxInterno(String definitivo, Integer lectivo, Long proximoNumeroInteiro) {
		if (proximoNumeroInteiro >= 1 && proximoNumeroInteiro <= 9)
			definitivo = "B" + lectivo + "00000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 10 && proximoNumeroInteiro <= 99)
			definitivo = "B" + lectivo + "0000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 100 && proximoNumeroInteiro <= 999)
			definitivo = "B" + lectivo + "000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 1000 && proximoNumeroInteiro <= 9999)
			definitivo = "B" + lectivo + "00" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 10000 && proximoNumeroInteiro <= 99999)
			definitivo = "B" + lectivo + "0" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 100000 && proximoNumeroInteiro <= 999999)
			definitivo = "B" + lectivo + proximoNumeroInteiro.toString();
		return definitivo;
	}

	/*
	 * private String gerarNumeroNotaCreditoTeste(String definitivo, Integer
	 * lectivo, Long proximoNumeroInteiro) { if (proximoNumeroInteiro >= 1 &&
	 * proximoNumeroInteiro <= 999999) definitivo = "NC UGS" + lectivo + "/" +
	 * proximoNumeroInteiro; return definitivo; }
	 */

	private void gerarNumeroGuia(AnoLectivo anoLectivo, HistoricoCreditoEmpresa hCredito) {
		String definitivo = "";
		Integer lectivo = anoLectivo.getAnoLectivo();
		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(5);
		Long proximoNumeroInteiro = numeroGerado.getProximoNumero();

		// metódo gerar numero de guia chamado
		definitivo = gerarNumeroBordereuxInterno(definitivo, lectivo, proximoNumeroInteiro);
		HistoricoCreditoEmpresa borderoExistente = this.histCreditoEmpresaRepo.findByBorderoInterno(definitivo);
		if (borderoExistente != null) {
			do {
				proximoNumeroInteiro++;
				// metódo gerar numero de guia chamado
				definitivo = gerarNumeroBordereuxInterno(definitivo, lectivo, proximoNumeroInteiro);
				borderoExistente = this.histCreditoEmpresaRepo.findByBorderoInterno(definitivo);
			} while (borderoExistente != null);
		}
		// setar o valor da guia
		hCredito.setBorderoInterno(definitivo);

		// atualizador os dados da ultima guia e a proxima guia
		numeroGerado.setUltimoNumero(proximoNumeroInteiro);
		numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
		this.numeroGeradoRepository.save(numeroGerado);
	}
	/*
	 * private void gerarFileFactura(Factura facturaSalva) {
	 * 
	 * String privateKey = "ChavePrivada.pem"; String nomeArquivo = "Registo.txt";
	 * String signedOutputFile = "Registo1.sha1"; String base64OutputFile =
	 * "Registo1.b64";
	 * 
	 * SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dataEmissao = formato.format(facturaSalva.getDataEmissao());
	 * 
	 * try { File arquivo = new File(nomeArquivo); FileWriter fileWriter = new
	 * FileWriter(arquivo, false); BufferedWriter bufferedWriter = new
	 * BufferedWriter(fileWriter);
	 * 
	 * // Escrever informações no arquivo bufferedWriter.write(dataEmissao + ";" +
	 * facturaSalva.getDataSystema() + ";" + facturaSalva.getNuneroFactura() + ";" +
	 * 0.0 + ";");
	 * 
	 * // Fechar o BufferedWriter bufferedWriter.close();
	 * 
	 * System.out.println("Arquivo gerado com sucesso!"); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * // Executar comando "openssl dgst -sha1 -sign ChavePrivada.pem -out //
	 * Registo1.sha1 Registo1.txt" executeCommand(
	 * String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey,
	 * signedOutputFile, nomeArquivo));
	 * 
	 * // Executar comando
	 * "openssl enc -base64 -in Registo1.sha1 -out Registo1.b64 -A"
	 * executeCommand(String.format("openssl enc -base64 -in %s -out %s -A",
	 * signedOutputFile, base64OutputFile));
	 * 
	 * try { // Lê o conteúdo do arquivo base64 String base64Data =
	 * readFileAsString(base64OutputFile); facturaSalva.setHash(base64Data);
	 * facturaRepo.save(facturaSalva);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * private void gerarFileFactura2(Factura facturaSalva) {
	 * 
	 * String privateKey = "ChavePrivada.pem"; String nomeArquivo = "Registo.txt";
	 * String signedOutputFile = "Registo1.sha1"; String base64OutputFile =
	 * "Registo1.b64";
	 * 
	 * SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dataEmissaoNC = formato.format(facturaSalva.getDataEmissao());
	 * 
	 * NumeroGerado numeroGeradoF = this.numeroGeradoRepository.findOne(8); Long
	 * ultimoNumero = numeroGeradoF.getUltimoNumero();
	 * 
	 * String numeroFT = "FT UGS23/" + ultimoNumero; Factura facturaFT =
	 * facturaRepo.buscarNumeroFactura(numeroFT);
	 * 
	 * try { File arquivo = new File(nomeArquivo); FileWriter fileWriter = new
	 * FileWriter(arquivo, false); BufferedWriter bufferedWriter = new
	 * BufferedWriter(fileWriter);
	 * 
	 * // Escrever informações no arquivo bufferedWriter.write(dataEmissaoNC + ";" +
	 * facturaSalva.getDataSystema() + ";" + facturaSalva.getNuneroFactura() + ";" +
	 * 0.0 + ";" + facturaFT.getHash());
	 * 
	 * // Fechar o BufferedWriter bufferedWriter.close();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * executeCommand( String.format("openssl dgst -sha1 -sign %s -out %s %s",
	 * privateKey, signedOutputFile, nomeArquivo));
	 * 
	 * executeCommand(String.format("openssl enc -base64 -in %s -out %s -A",
	 * signedOutputFile, base64OutputFile));
	 * 
	 * try { // Lê o conteúdo do arquivo base64 String base64Data =
	 * readFileAsString(base64OutputFile);
	 * 
	 * facturaSalva.setHash(base64Data); facturaRepo.save(facturaSalva);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * private void gerarFileNotaCredito(NotaCredito notaCreditoSalva) {
	 * 
	 * String privateKey = "ChavePrivada.pem"; String nomeArquivo = "Registo.txt";
	 * String signedOutputFile = "Registo1.sha1"; String base64OutputFile =
	 * "Registo1.b64";
	 * 
	 * SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dataEmissao = formato.format(notaCreditoSalva.getDataEmissao());
	 * 
	 * try { File arquivo = new File(nomeArquivo); FileWriter fileWriter = new
	 * FileWriter(arquivo, false); BufferedWriter bufferedWriter = new
	 * BufferedWriter(fileWriter);
	 * 
	 * // Escrever informações no arquivo bufferedWriter.write(dataEmissao + ";" +
	 * notaCreditoSalva.getDataSistema() + ";" +
	 * notaCreditoSalva.getNumeroNotaCredito() + ";" + 0.0 + ";");
	 * 
	 * // Fechar o BufferedWriter bufferedWriter.close();
	 * 
	 * System.out.println("Arquivo gerado com sucesso!"); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * // Executar comando "openssl dgst -sha1 -sign ChavePrivada.pem -out //
	 * Registo1.sha1 Registo1.txt" executeCommand(
	 * String.format("openssl dgst -sha1 -sign %s -out %s %s", privateKey,
	 * signedOutputFile, nomeArquivo));
	 * 
	 * // Executar comando
	 * "openssl enc -base64 -in Registo1.sha1 -out Registo1.b64 -A"
	 * executeCommand(String.format("openssl enc -base64 -in %s -out %s -A",
	 * signedOutputFile, base64OutputFile));
	 * 
	 * try { // Lê o conteúdo do arquivo base64 String base64Data =
	 * readFileAsString(base64OutputFile); notaCreditoSalva.setHash(base64Data);
	 * notaCreditoRepo.save(notaCreditoSalva);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * private void gerarFileNotaCredito2(NotaCredito notaCreditoSalva) {
	 * 
	 * String privateKey = "ChavePrivada.pem"; String nomeArquivo = "Registo.txt";
	 * String signedOutputFile = "Registo1.sha1"; String base64OutputFile =
	 * "Registo1.b64";
	 * 
	 * SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); String
	 * dataEmissaoNC = formato.format(notaCreditoSalva.getDataEmissao());
	 * 
	 * NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(2006); Long
	 * ultimoNumero = numeroGeradoFP.getUltimoNumero();
	 * 
	 * String notaNC = "NC UGS23/" + ultimoNumero; NotaCredito notaCreditoNC =
	 * notaCreditoRepo.buscarNumeroNotaCredito(notaNC);
	 * 
	 * try { File arquivo = new File(nomeArquivo); FileWriter fileWriter = new
	 * FileWriter(arquivo, false); BufferedWriter bufferedWriter = new
	 * BufferedWriter(fileWriter);
	 * 
	 * // Escrever informações no arquivo bufferedWriter.write(dataEmissaoNC + ";" +
	 * notaCreditoSalva.getDataSistema() + ";" +
	 * notaCreditoSalva.getNumeroNotaCredito() + ";" + 0.0 + ";" +
	 * notaCreditoNC.getHash());
	 * 
	 * // Fechar o BufferedWriter bufferedWriter.close();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * executeCommand( String.format("openssl dgst -sha1 -sign %s -out %s %s",
	 * privateKey, signedOutputFile, nomeArquivo));
	 * 
	 * executeCommand(String.format("openssl enc -base64 -in %s -out %s -A",
	 * signedOutputFile, base64OutputFile));
	 * 
	 * try { // Lê o conteúdo do arquivo base64 String base64Data =
	 * readFileAsString(base64OutputFile);
	 * 
	 * notaCreditoSalva.setHash(base64Data); notaCreditoRepo.save(notaCreditoSalva);
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * private static void executeCommand(String command) { try { Process process =
	 * Runtime.getRuntime().exec(command);
	 * 
	 * // Ler a saída do processo InputStream inputStream =
	 * process.getInputStream(); BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(inputStream)); String line; while ((line =
	 * reader.readLine()) != null) { System.out.println(line); }
	 * 
	 * // Aguardar a finalização do processo int exitCode = process.waitFor();
	 * System.out.println("Comando executado. Código de saída: " + exitCode); }
	 * catch (IOException | InterruptedException e) { e.printStackTrace(); } }
	 * 
	 * private static String readFileAsString(String filePath) throws
	 * FileNotFoundException { StringBuilder contentBuilder = new StringBuilder();
	 * Scanner scanner = new Scanner(new FileInputStream(filePath));
	 * 
	 * while (scanner.hasNextLine()) { contentBuilder.append(scanner.nextLine()); }
	 * 
	 * scanner.close();
	 * 
	 * return contentBuilder.toString(); }
	 */
}
