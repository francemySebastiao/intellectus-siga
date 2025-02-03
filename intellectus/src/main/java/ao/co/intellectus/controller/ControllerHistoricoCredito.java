package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
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

import ao.co.intellectus.DTO.ContaCorrenteBuscar;
import ao.co.intellectus.DTO.CreditosResumoCliente;
import ao.co.intellectus.DTO.HistoricoCreditoCliente;
import ao.co.intellectus.DTO.HistoricoCreditoDetalhe;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Banco;
import ao.co.intellectus.model.Bordero;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.ContaCorrenteAluno;
import ao.co.intellectus.model.ContaCorrenteCandidato;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.HistoricoCredito;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.enumeracao.TipoFatura;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.BancoRepository;
import ao.co.intellectus.repository.BorderoRepository;
import ao.co.intellectus.repository.CandidatoRepository;
import ao.co.intellectus.repository.ContaCorrenteCandidatoRepository;
import ao.co.intellectus.repository.ContaCorrenteRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoCreditoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarNumeroDocumento;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.cafold.GuiaService;
import ao.co.intellectus.util.FormataData;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/historicoCredito")
public class ControllerHistoricoCredito {
	
	@Autowired
	private HistoricoCreditoRepository historicoCreditoRepository;
	@Autowired
	private BancoRepository bancoRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private BorderoRepository borderoRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CandidatoRepository candidatoRepository;
	@Autowired
	private ContaCorrenteCandidatoRepository contaCandidatoRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaRepository;
	@Autowired
	private GuiaService guiaService;
	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;
	@Autowired
	private GeradorDeArquivo gerarDocService;
	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepo;
	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	@Transactional
	public ResponseEntity<ResponseCliente> salvar(@RequestBody HistoricoCreditoCliente credito) {
		ResponseCliente c = new ResponseCliente();

		//BUSCA O USUARIO DA OPERAÇÃO PARA PODER PROCEDER.
		Usuario usuario = this.usuarioRepository.findByUserName(credito.getUserName() !=null ? credito.getUserName() : null);
		//Usuario usuario               = this.usuarioRepository.findOne(29);
		
		//VERIFICA SE EXISTE UM BORDERO COM ESTE NÚMERO.
		Bordero borderoExterno = this.borderoRepository.findByNumeroAndBancoId(credito.getBorderoExterno(),credito.getBanco());
		
		//SE FOR EXITIR DADOS, RETORNAR ERRO.
		if(borderoExterno!=null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Este número de operação já foi usado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		AnoLectivo anoLectivo  = this.anoLectivoRepository.findOne(credito.getAnoLectivo());

		Banco banco            = this.bancoRepository.findOne(credito.getBanco());
		Instituicao instituicao= this.instituicaoRepository.findOne(2);
		
		//REGISTAR O BORDERO NA TABELA BORDERO
		Bordero bordero=new Bordero();
		
		//SE NÃO FOR UM CANDIDATO...
		Aluno aluno =null;
		if(!credito.isCandidato()) {	
			 aluno = this.alunoRepository.findByNumeroDeAluno(credito.getNumeroDeAluno().toString());
			bordero.setAluno(aluno);
		}
		bordero.setBanco(banco);
		bordero.setDataRegistro(new Date());
		bordero.setDataDeposito(credito.getDataDepositoExterno());
		bordero.setValor(credito.getValorDeposito());
		bordero.setNumero(credito.getBorderoExterno());
		bordero.setMoeda(banco.getMoeda());
		//SALVAR BORDEREUX		    
		this.borderoRepository.save(bordero);
		
		HistoricoCredito hCredito=new HistoricoCredito();
		
		Candidato candidato=null;
		if(credito.isCandidato()) {
			candidato = this.candidatoRepository.findByNumeroCandidatoAndAnoLectivo(credito.getNumeroCandidato(), anoLectivo);
            
			hCredito.setCandidato(candidato);
		}else {
			hCredito.setAluno(aluno);
			hCredito.setNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
		}
		hCredito.setAnoLectivo(anoLectivo);
		hCredito.setBanco(banco);
		hCredito.setBordero(bordero);
		hCredito.setBorderoExterno(credito.getBorderoExterno());
		hCredito.setValorDeposito(credito.getValorDeposito());
		hCredito.setMoeda(banco.getMoeda());
		hCredito.setUsuarioEmitiu(usuario);
		//GERAR NÚMERO, DE OPERAÇÃO INTERNA
		//hCredito.setBorderoInterno(borderoInterno);
		//----------------------------------------------------------------
		gerarNumeroGuia(anoLectivo, hCredito);
		//----------------------------------------------------------------
		hCredito.setDataDepositoExterno(credito.getDataDepositoExterno());
		hCredito.setDataRegisto(new Date());
		hCredito.setInstituicao(instituicao);
		
		HistoricoCredito historicoSalvo = this.historicoCreditoRepository.save(hCredito);
		
		//BUSCA CONTA CORRENTE DO ALUNO
		if(!credito.isCandidato()) {			
			ContaCorrenteAluno conta = this.contaCorrenteRepository.findByAluno(aluno);
			conta.setValorAnterior(conta.getValor());
			conta.setValor(conta.getValor()+credito.getValorDeposito());
			contaCorrenteRepository.save(conta);
		}
		
		String numero = gerarGuia(anoLectivo, aluno, instituicao, usuario, hCredito);
		
		
		//BUSCA CONTA DO CANDIDATO
		if(credito.isCandidato()) {	
			
			
			ContaCorrenteCandidato contaCandidato = this.contaCandidatoRepository.findByAnoLectivoAndNumeroDeCandidato(candidato.getAnoLectivo(),credito.getNumeroCandidato().toString());
			
			
			if(contaCandidato!=null) {
				contaCandidato.setValorAnterior(contaCandidato.getValor());
				
				double vs=credito.getValorDeposito()+contaCandidato.getValor();
				
				contaCandidato.setValor(vs);
				contaCandidato.setDataMovimento(new Date());
				this.contaCandidatoRepository.save(contaCandidato);
			}else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setMensagem("Número de candidato não existe.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}				
		}
		
		historicoCreditoRepository.save(historicoSalvo);
		
		c.setResultado(numero);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Crédito efetivado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}


	//HistoricoCreditoCandidatoRepository
	@RequestMapping(value = "/salvarContaCandidato", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvarContaCandidato(@RequestBody HistoricoCreditoCliente credito) {
		ResponseCliente c = new ResponseCliente();

		//BUSCA O USUARIO DA OPERAÇÃO PARA PODER PROCEDER.
		Usuario usuario = this.usuarioRepository.findByUserName(credito.getUserName() !=null ? credito.getUserName() : null);
		//Usuario usuario               = this.usuarioRepository.findOne(29);
		
		//VERIFICA SE EXISTE UM BORDERO COM ESTE NÚMERO.
		Bordero borderoExterno = this.borderoRepository.findByNumeroAndBancoId(credito.getBorderoExterno(),credito.getBanco());
		
		//SE FOR EXITIR DADOS, RETORNAR ERRO.
		if(borderoExterno!=null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Este número de operação já foi usado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		AnoLectivo anoLectivo  = this.anoLectivoRepository.findOne(credito.getAnoLectivo());
		Aluno aluno            = this.alunoRepository.findByNumeroDeAluno(credito.getNumeroDeAluno().toString());
		Banco banco            = this.bancoRepository.findOne(credito.getBanco());
		Instituicao instituicao= this.instituicaoRepository.findOne(2);
		
		//REGISTAR O BORDERO NA TABELA BORDERO
		Bordero bordero=new Bordero();
		 
		bordero.setAluno(aluno);
		bordero.setBanco(banco);
		bordero.setDataRegistro(new Date());
		bordero.setDataDeposito(credito.getDataDepositoExterno());
		bordero.setValor(credito.getValorDeposito());
		bordero.setNumero(credito.getBorderoExterno());
		bordero.setMoeda(banco.getMoeda());
		//SALVAR BORDEREUX		    
		this.borderoRepository.save(bordero);
		
		HistoricoCredito hCredito=new HistoricoCredito();
		
		hCredito.setAluno(aluno);
		hCredito.setNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
		hCredito.setAnoLectivo(anoLectivo);
		hCredito.setBanco(banco);
		hCredito.setBordero(bordero);
		hCredito.setBorderoExterno(credito.getBorderoExterno());
		hCredito.setValorDeposito(credito.getValorDeposito());
		hCredito.setMoeda(banco.getMoeda());
		hCredito.setUsuarioEmitiu(usuario);
		//GERAR NÚMERO, DE OPERAÇÃO INTERNA
		//hCredito.setBorderoInterno(borderoInterno);
		
		//-------------------------------------------------------------------------------------------------
		gerarNumeroGuia(anoLectivo, hCredito);
		
		//--------------------------------------------------------------------------------------------------
		hCredito.setDataDepositoExterno(credito.getDataDepositoExterno());
		hCredito.setDataRegisto(new Date());
		hCredito.setInstituicao(instituicao);
		
		HistoricoCredito historicoSalvo = this.historicoCreditoRepository.save(hCredito);
		
		//BUSCA CONTA CORRENTE
		ContaCorrenteAluno conta = this.contaCorrenteRepository.findByAluno(aluno);
		
		conta.setValorAnterior(conta.getValor());
		conta.setValor(conta.getValor()+credito.getValorDeposito());
		contaCorrenteRepository.save(conta);
		
		c.setResultado(historicoSalvo);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Depósito efetivado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@Transactional
	private void gerarNumeroGuia(AnoLectivo anoLectivo, HistoricoCredito hCredito) {
		String definitivo         = "";
		Integer lectivo           = anoLectivo.getAnoLectivo();
		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(5);
		Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
		
		//metódo gerar numero de guia chamado
		definitivo = gerarNumeroBordereuxInterno(definitivo, lectivo, proximoNumeroInteiro);
		HistoricoCredito borderoExistente = this.historicoCreditoRepository.findByBorderoInterno(definitivo);
		if(borderoExistente!=null) {	
			do {
				proximoNumeroInteiro++;
				//metódo gerar numero de guia chamado
				definitivo = gerarNumeroBordereuxInterno(definitivo, lectivo, proximoNumeroInteiro);
				borderoExistente = this.historicoCreditoRepository.findByBorderoInterno(definitivo);
			}while(borderoExistente!=null);
		}
		//setar o valor da guia
		hCredito.setBorderoInterno(definitivo);
		
		//atualizador os dados da ultima guia e a proxima guia
		numeroGerado.setUltimoNumero(proximoNumeroInteiro);
		numeroGerado.setProximoNumero(proximoNumeroInteiro+1);		
		this.numeroGeradoRepository.save(numeroGerado);
	}
	
	@Transactional
	private String gerarGuia(AnoLectivo anoLectivo, Aluno aluno, Instituicao instituicao, Usuario usuario, HistoricoCredito hCredito) {
		
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);
		
		FormataData forma = new FormataData();
		
		GuiaPagamentoHistorico guiaHistorico = new GuiaPagamentoHistorico();
		Guia guia = new Guia();
		
		Banco banco = this.bancoRepository.findOne(hCredito.getBanco().getId());
		Bordero borderoux = borderoRepository.findByNumero(hCredito.getBorderoExterno());
		//Bordero bordero = new Bordero();
		
		
		guia.setAluno(aluno);
		guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
		guia.setDataEmicao(new Date());
		guia.setAutomaticamente(false);
		guia.setAnulada(false);
		guia.setAlterada(false);
		guia.setLiquidada(true);
		guia.setGerouCredito(false);
		guia.setGeradaReferencia(false);
		guia.setGeradaOnline(false);
		guia.setLiquidacaoAGT(true);
		guia.setDataSistema(dataSistema);
		guia.setAnoLectivo(anoLectivo);
		guia.setInstituicao(instituicao);
		guia.setParaAcordoPagamento(false);
		guia.setAcordo(false);
		guia.setUsuarioEmitiu(usuario);
		guia.setUsuarioLiquidou(usuario);
		guia.setDataLiquidacao(new Date());
		guia.setDataVencimento(new Date());
		guia.setDataEmissaoFr(new Date());
		guia.setDataSistemaFr(dataSistema);
		guia.setLiquidacaoCredito(false);
		guia.setBordero(hCredito.getBorderoExterno());
		
		Guia guiaSava = this.guiaRepository.save(guia);
		
		borderoux.setAluno(aluno);
		borderoux.setBanco(banco);
		borderoux.setDataRegistro(new Date());
		borderoux.setDataDeposito(hCredito.getDataDepositoExterno());
		borderoux.setValor(FormataData.formatarValor(Double.valueOf(hCredito.getValorDeposito())));
		borderoux.setNumero(hCredito.getBorderoExterno());
		borderoux.setGuia(guia);
	
		this.borderoRepository.save(borderoux);
		
		double valorGuia = 0;
		
			Emolumento emolumento = this.emolumentoRepository.findOne(1);

			guiaHistorico.setEmolumento(emolumento);
			guiaHistorico.setGuia(guiaSava);
			
			guiaHistorico.setValor(hCredito.getValorDeposito());
			
			guiaHistorico.setAluno(aluno);
			guiaHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
			guiaHistorico.setAnoLectivo(anoLectivo);
			guiaHistorico.setCodigoIva(emolumento.getCodigoIva());
			guiaHistorico.setQuantidade("1");
			guiaHistorico.setPercentagemIva(emolumento.getPercentagemIva().toString());
			guiaHistorico.setValorImposto(0);
			guiaHistorico.setValorTotal(FormataData.formatarValor(Double.valueOf(hCredito.getValorDeposito())));

			valorGuia = hCredito.getValorDeposito();
			this.historicoGuiaRepository.save(guiaHistorico);
			this.guiaService.gerarHistoricoAudit(guiaHistorico);
			
			
			String numero ="";
			
			/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
			String ano = String.valueOf(anoActivo.getAnoLectivo());
			String anoSubstring = ano.substring(2,4);
			Integer anoLimpo = Integer.parseInt(anoSubstring);*/
			
			NumeroGerado numeroGeradoFR = this.numeroGeradoRepository.findOne(7);
			Long proximoNumero = numeroGeradoFR.getProximoNumero();
			
			//String numero = gerarNumeroDocService.geracaoNumero();
			numero = gerarNumeroDocService.gerarNumeroFacturaRecibo(numero, forma.anoLectivo(), proximoNumero);
			
			Guia proformaExiste = this.guiaRepository.findFacturaRecibo(numero);
			GuiaCandidatura proformaGuiaExistente = guiaCandidaturaRepo.buscarRecibo(numero);
			if (proformaExiste != null || proformaGuiaExistente != null) {
				do {
					proximoNumero++;
					
					numero =  gerarNumeroDocService.gerarNumeroFacturaRecibo(numero, forma.anoLectivo(), proximoNumero);
					proformaExiste = this.guiaRepository.findFacturaRecibo(numero);
					proformaGuiaExistente = guiaCandidaturaRepo.buscarRecibo(numero);
				} while (proformaExiste != null || proformaGuiaExistente != null);
			}
			
			// setar o valor da guia
			guiaSava.setNumeroFacturaRecibo(numero);
			Guia guiaGuardada = this.guiaRepository.save(guiaSava);
			
			this.gerarDocService.gerarFileFacturaReciboAluno(guiaGuardada);
			
			numeroGeradoFR.setUltimoNumero(proximoNumero);
			numeroGeradoFR.setProximoNumero(proximoNumero + 1);
			this.numeroGeradoRepository.save(numeroGeradoFR);
			
			guiaGuardada.setTipoFactura(TipoFatura.FACTURA_RECIBO);
			
			guiaGuardada.setValor(FormataData.formatarValor(valorGuia));
			guiaGuardada.setUltimaModificacao(new Date());
			this.guiaRepository.save(guiaGuardada);
			
			return numero;
		
	}
	
	//ANULAR CRÉDITO
	@RequestMapping(value = "/anularCredito", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> anularCredito(@RequestBody HistoricoCreditoCliente credito) {
		ResponseCliente c = new ResponseCliente();

		//BUSCA O USUARIO DA OPERAÇÃO PARA PODER PROCEDER.
		//Usuario usuario = this.usuarioRepository.findByUserName(credito.getUserName() !=null ? credito.getUserName() : null);
		//Usuario usuario               = this.usuarioRepository.findOne(29);
		
		
		//c.setResultado(historicoSalvo);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Depósito efetivado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	//historicoCredito/buscarCredito UID
	@RequestMapping(value = "/buscarCredito", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> buscarCredito(@RequestBody ContaCorrenteBuscar pego) {
		ResponseCliente c = new ResponseCliente();
		HistoricoCreditoDetalhe hCredito=new HistoricoCreditoDetalhe();
		List<CreditosResumoCliente> creditos=new ArrayList<CreditosResumoCliente>();
		
		
		if(pego.isTipo()) {//INICIO PRIMEIRO
			AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(pego.getAnoLectivo());
			
			ContaCorrenteCandidato conta = this.contaCandidatoRepository.findByAnoLectivoAndNumeroDeCandidato(anoLectivo, pego.getBuscarId().toString());

			hCredito.setNome(conta.getCandidato().getNome());
			hCredito.setNumeroDeAluno(conta.getCandidato().getNumeroCandidato());
			hCredito.setCurso(conta.getCandidato().getCurso().getPlano());
		
			hCredito.setSaldoAtual(conta.getValor());
			
			List<HistoricoCredito> histoco = this.historicoCreditoRepository.findByCandidato(conta.getCandidato());
			//List<CreditosResumoCliente> creditos=new ArrayList<CreditosResumoCliente>();
			CreditosResumoCliente credito;
			for (HistoricoCredito historicoCredito : histoco) {
				credito=new CreditosResumoCliente();
				
				BeanUtils.copyProperties(historicoCredito, credito,"aluno","instituicao","moeda","anoLectivo","bordero","banco");
				credito.setId(historicoCredito.getId());
				credito.setMotivoAnulacao(historicoCredito.getMotivoDeAnulacao());
				credito.setAnulado(historicoCredito.isAnulado());
				credito.setPodeAnular(historicoCredito.isPodeAnular());
				if(historicoCredito.getBanco()!=null)
				credito.setBanco(historicoCredito.getBanco().getBanco());
				credito.setAnoLectivo(historicoCredito.getAnoLectivo().getAnoLectivo());
				credito.setAnoLectivoDescricao(historicoCredito.getAnoLectivo().getAnoLectivoDescricao());
				credito.setBorderoExterno(historicoCredito.getBorderoExterno());
				//credito.setNumeroFR(historicoCredito.getNumeroFacturaRecibo());
				if(historicoCredito.getMoeda()!=null)
				credito.setMoeda(historicoCredito.getMoeda().getDesignacao());
				creditos.add(credito);
			}
		}else {
			Aluno aluno = this.alunoRepository.findByNumeroDeAluno(pego.getBuscarId().toString());
			ContaCorrenteAluno conta = this.contaCorrenteRepository.findByAluno(aluno);
			
			hCredito.setNome(aluno.getNome());
			hCredito.setNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
			hCredito.setCurso(aluno.getCurso().getPlano());
			hCredito.setContencioso(aluno.isContencioso());
			hCredito.setFimCurso(aluno.isFimCurso());
			hCredito.setInativo(aluno.isInactivo());
			hCredito.setSaldoAtual(conta.getValor());
			List<HistoricoCredito> histoco = this.historicoCreditoRepository.findByAluno(aluno);
			CreditosResumoCliente credito;
			for (HistoricoCredito historicoCredito : histoco) {
				credito=new CreditosResumoCliente();
				
				BeanUtils.copyProperties(historicoCredito, credito,"aluno","instituicao","moeda","anoLectivo","bordero","banco");
				
				credito.setId(historicoCredito.getId());
				//credito.setNumeroFR(historicoCredito.getNumeroFacturaRecibo());
				credito.setMotivoAnulacao(historicoCredito.getMotivoDeAnulacao());
				credito.setAnulado(historicoCredito.isAnulado());
				credito.setPodeAnular(historicoCredito.isPodeAnular());
				if(historicoCredito.getBanco()!=null)
				credito.setBanco(historicoCredito.getBanco().getBanco());
				credito.setAnoLectivo(historicoCredito.getAnoLectivo().getAnoLectivo());
				credito.setAnoLectivoDescricao(historicoCredito.getAnoLectivo().getAnoLectivoDescricao());
				credito.setBorderoExterno(historicoCredito.getBorderoExterno());
				if(historicoCredito.getMoeda()!=null)
				credito.setMoeda(historicoCredito.getMoeda().getDesignacao());
				creditos.add(credito);
			}
			
		}//FINAL ELSE
		
		
		
		
		
		hCredito.setCreditos(creditos);
		c.setResultado(hCredito);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	private String gerarNumeroBordereuxInterno(String definitivo, Integer lectivo, Long proximoNumeroInteiro) {
		if(proximoNumeroInteiro>=1      && proximoNumeroInteiro<=9)
			definitivo="B"+lectivo+"00000"  +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=10     && proximoNumeroInteiro<=99)
			definitivo="B"+lectivo+"0000"   +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=100    && proximoNumeroInteiro<=999)
			definitivo="B"+lectivo+"000"    +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=1000   && proximoNumeroInteiro<=9999)
			definitivo="B"+lectivo+"00"     +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=10000  && proximoNumeroInteiro<=99999)
			definitivo="B"+lectivo+"0"      +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=100000 && proximoNumeroInteiro<=999999)
			definitivo="B"+lectivo          +proximoNumeroInteiro.toString();
		return definitivo;
	}
	
	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}
	
	
	@GetMapping("/credito/aluno")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCreditoAluno(@RequestParam String numero) throws Exception {
	
		
		byte[] relatrio = servicoCreditoAluno(numero);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCreditoAluno(String numero) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero", numero);
		
		HistoricoCredito credito = this.historicoCreditoRepository.findByBorderoInterno(numero);
		
		InputStream inputStream;
		if(credito.getNumeroDeAluno()!=null) {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guia_Credito.jasper");	
		}else {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Guia_Credito_candidato.jasper");
		}
		
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}
