package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AlunoPlanoCodigo;
import ao.co.intellectus.DTO.AnosInscricoes;
import ao.co.intellectus.DTO.CompostoPlanoCliente;
import ao.co.intellectus.DTO.GuiaCliente;
import ao.co.intellectus.DTO.GuiaMultaCliente;
import ao.co.intellectus.DTO.MultaRetiradasCliente;
import ao.co.intellectus.DTO.PlanoControleCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAnexo;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaMultaNegociacao;
import ao.co.intellectus.model.GuiaMultaValor;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.GuiaPlanoMulta;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.enumeracao.TipoFatura;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoAnexoRepository;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.GuiaMultaNegociacaoRepository;
import ao.co.intellectus.repository.GuiaMultaValorRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.GuiaPlanoMultaRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarNumeroDocumento;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.cafold.GuiaService;
import ao.co.intellectus.servico.guias.GuiaDeInscricao;
import ao.co.intellectus.servico.guias.GuiaHistorico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/multaNegociacao")
public class ControllerGuiaMultaPlano {
	@Autowired
	private GuiaMultaNegociacaoRepository guiaMultaNegociacaoRepository;
	@Autowired
	private GuiaMultaValorRepository guiaMultaValorRepository;
	@Autowired
	private GuiaPlanoMultaRepository guiaPlanoMultaRepository;
	@Autowired
	private GuiaPagamentoRepository guiaPagamentoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private AlunoAnexoRepository alunoAnexoRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaRepository;
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private GuiaService guiaService;
	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;
	@Autowired
	private GeradorDeArquivo gerarDocService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(value = "/guiasAnuladas/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiasAnuladasAluno(@PathVariable String numero) { 
		ResponseCliente c=new ResponseCliente();
        Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);
        
        
        //List<GuiaMultaNegociacao> guiasNegocial = this.guiaMultaNegociacaoRepository.findByNumeroDeAluno(numero);
        
        if(aluno==null){
         	c.setCodigo(ResponseCode.values()[2].getDescricao());
    		c.setMensagem("Aluno não existe.");
    		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
        }
        
        
        
		GuiaCliente gCliente;
		GuiaAlunos guiaAlunos       = new GuiaAlunos();
	
		List<GuiaCliente> gClinetes = new ArrayList<GuiaCliente>();
		//List<Guia> guias            = this.guiaPagamentoRepository.findByAlunoAndLiquidadaAndAnulada(aluno, false, true);
		List<Guia> guias            = this.guiaPagamentoRepository.findByAlunoAndParaAcordoPagamento(aluno,true);
		//boolean verificar=false;
		
		
		for (Guia guia : guias) {
			gCliente = new GuiaCliente();
			BeanUtils.copyProperties(guia, gCliente, "Aluno", "AnoLectivo");
			gCliente.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			gCliente.setNumero(guia.getNumeroGuia());
			
			double valorGuia=0;
			List<GuiaPagamentoHistorico> guisEmoluemento = this.historicoGuiaRepository.findByGuia(guia);
			for (GuiaPagamentoHistorico h : guisEmoluemento) {
				valorGuia+=h.getValor();
			}
			gCliente.setValor(valorGuia);
			//Esta pesquisa complica o contexto todo, devo buscar apenas  a guia em referencia.
			List<GuiaMultaNegociacao> guiaMulta = this.guiaMultaNegociacaoRepository.findByGuia(guia);
			System.out.println("Pesquisa " + guiaMulta);
			if(guiaMulta.isEmpty())
			   gClinetes.add(gCliente); 
		}
		
		//INSCRIÇÕES FEITAS AO LONGO DO CURSO
		AnosInscricoes anoInscricao;
		List<AnosInscricoes> anosInscricoes=new ArrayList<AnosInscricoes>();
		List<Matricula> matriculas = this.matriculaRepository.findByAluno(aluno);
		for (Matricula matricula : matriculas) {
			anoInscricao=new AnosInscricoes();
			anoInscricao.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
			anosInscricoes.add(anoInscricao);
		}
		guiaAlunos.setAnosInscricoes(anosInscricoes);
		guiaAlunos.setNome(aluno.getNome());
		guiaAlunos.setNumeroAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
		guiaAlunos.setContencioso(aluno.isContencioso());
		guiaAlunos.setFimCurso(aluno.isFimCurso());
		guiaAlunos.setInativo(aluno.isInactivo());
		guiaAlunos.setCurso(aluno.getCurso().getPlano());
		
		//FOTO ALUNO.
		AlunoAnexo alunoAnexo = this.alunoAnexoRepository.findByAluno(aluno);
		guiaAlunos.setFoto(alunoAnexo.getFoto());		
		//GUIAS EXISTENTES.
		guiaAlunos.setGuiasAlunos(gClinetes);
		
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(guiaAlunos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	///multaNegociacao/processamento
	@Transactional
	@RequestMapping(value = "/processamento", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> processamento(@RequestBody MultaRetiradasCliente processamento) { 
		ResponseCliente c=new ResponseCliente();
		
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);
		
		Usuario usuario = this.usuarioRepository
				.findByUserName(processamento.getUserName() != null ? processamento.getUserName() : null);
		
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(processamento.getNumeroDeAluno());
		if(aluno==null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
	       	c.setMensagem("Aluno não existe.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		//Matricula matricula = matriculaRepository.buscarUltimaMatricula(aluno.getNumeroDeAluno());
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
		Moeda moeda                 = this.moedaRepository.findOne(3);
		Instituicao instituicao     = this.instituicaoRepository.findOne(2);
		Emolumento emolumento       = this.emolumentoRepository.findOne(106);
		
		GuiaMultaValor gMultaValor  =new GuiaMultaValor();
		gMultaValor.setConsolidado(false);
		gMultaValor.setData1Prestacao(processamento.getData1Prestacao());
		gMultaValor.setnPrestacoes(processamento.getnPrestacoes());
		gMultaValor.setTotalMulta(processamento.getTotalMulta());
		gMultaValor.setTotalSelecionado(processamento.getTotalSelecionado());
		gMultaValor.setValorAmnistia(processamento.getValorAmnistia());
		gMultaValor.setValorAPagar(processamento.getValorAPagar());
		gMultaValor.setDataProximaPrestacao(new Date());
		gMultaValor.setDataUltimaPrestacao(new Date());
		gMultaValor.setNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
		
		GuiaMultaValor mv              = this.guiaMultaValorRepository.save(gMultaValor);
		EmolumentoHistorico eHistorico = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento, aluno.getCurso(), anoLectivo.get(0));
		
		AlunoPlanoCodigo ac=new AlunoPlanoCodigo();
		ac.setCodigoPlano(mv.getId());
		ac.setNumeroDeAluno(aluno.getNumeroDeAluno());
		
		Guia guia;
		
		GuiaMultaNegociacao gmn;
		List<GuiaMultaCliente> guiasMulta = processamento.getGuiaMultaCliente();
		for (GuiaMultaCliente guiaMultaCliente : guiasMulta) {
			guia = this.guiaPagamentoRepository.findByNumeroGuiaAndAndParaAcordoPagamento(guiaMultaCliente.getNumeroGuia(), true);
			gmn=new GuiaMultaNegociacao();	
			List<GuiaPagamentoHistorico> guisHist = this.historicoGuiaRepository.findByGuia(guia);
			gmn.setAluno(aluno);
			gmn.setValor(guia.getValor());
			gmn.setAnulado(guia.isAnulada());
			
			gmn.setDataAnulado(guia.getDataVencimento());
			
			gmn.setDataEmissaoGuia(guia.getDataEmicao());
			gmn.setGuia(guia);
			gmn.setDataInsercao(new Date());
			gmn.setEmolumento(guisHist.get(0).getEmolumento());
			gmn.setGuiaMultaValor(mv);
			gmn.setNumeroDeAluno(aluno.getNumeroDeAluno());
			gmn.setDataEmissaoPlano(new Date());
			this.guiaMultaNegociacaoRepository.save(gmn);
		}
		
		GuiaPlanoMulta gp;
		//001
		GuiaPagamentoHistorico ultimaGuia=null;
		List<Date> dataPrestacoes = processamento.getDataPrestacoes();
		for(int i=0;i<processamento.getnPrestacoes();i++) {
			gp=new GuiaPlanoMulta();
			
			eHistorico.setValor(processamento.getValorFixo()); 
			Guia guiaDeInscricao = GuiaDeInscricao.guiaDeInscricao(aluno, anoLectivo.get(0), eHistorico, moeda, instituicao);
			guiaDeInscricao.setAcordo(true);
			//LOROTA
			guiaDeInscricao.setDataVencimento(dataPrestacoes.get(i));
			Guia guiaSalva            = this.guiaPagamentoRepository.save(guiaDeInscricao);
			Integer lectivo           = anoLectivo.get(0).getAnoLectivo();
			//geracao de numero de guia.
			String definitivo         = "";
			
			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);
			Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
			
			//gerar número de guia
			definitivo = geraNumeroDuia(lectivo, definitivo, proximoNumeroInteiro);

			Guia guiaExistente = this.guiaPagamentoRepository.findByNumeroGuia(definitivo);
			
			if(guiaExistente!=null) {	
				do {
					proximoNumeroInteiro++;
					//gerar número de guia
					definitivo = geraNumeroDuia(lectivo, definitivo, proximoNumeroInteiro);
					//pesquisa novamente a guia.
					guiaExistente = this.guiaPagamentoRepository.findByNumeroGuia(definitivo);
				}while(guiaExistente!=null);
			}
			
			
			String numero ="";
			
			AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
			String ano = String.valueOf(anoActivo.getAnoLectivo());
			String anoSubstring = ano.substring(2,4);
			Integer anoLimpo = Integer.parseInt(anoSubstring);
			
			NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
			Long proximoNumero = numeroGeradoFP.getProximoNumero();
			
			//String numero = gerarNumeroDocService.geracaoNumero();
			numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, anoLimpo, proximoNumero);
			
			Guia proformaExiste = this.guiaPagamentoRepository.findProforma(numero);
			if (proformaExiste != null) {
				do {
					proximoNumero++;
					
					numero =  gerarNumeroDocService.gerarNumeroFacturaProforma(numero, anoLimpo, proximoNumero);
					proformaExiste = this.guiaPagamentoRepository.findProforma(numero);
				} while (proformaExiste != null);
			}
			
			// setar o valor da guia
			guiaSalva.setNumeroGuia(definitivo);
			guiaSalva.setAlterada(false);
			guiaSalva.setDataSistema(dataSistema);
			guiaSalva.setTipoFactura(TipoFatura.FACTURA_PROFORMA);
			guiaSalva.setUsuarioEmitiu(usuario);
			
			guiaSalva.setNumeroFacturaProforma(numero);
			Guia guiaGuardada = this.guiaPagamentoRepository.save(guiaSalva);
			
			this.gerarDocService.gerarFileProformaAluno(guiaGuardada);
			
			numeroGeradoFP.setUltimoNumero(proximoNumero);
			numeroGeradoFP.setProximoNumero(proximoNumero + 1);
			this.numeroGeradoRepository.save(numeroGeradoFP);
			
			//guiaSalva.setNumeroGuia(definitivo);
			
			//geracao de numero de guia.
			
			numeroGerado.setUltimoNumero(proximoNumeroInteiro);
			numeroGerado.setProximoNumero(proximoNumeroInteiro+1);		
			this.numeroGeradoRepository.save(numeroGerado);
			
			this.guiaPagamentoRepository.save(guiaSalva);
			gp.setGuia(guiaSalva);
			gp.setGuiaMultaValor(gMultaValor);
			this.guiaPlanoMultaRepository.save(gp);
			
			//007
			EmolumentoHistorico eH = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento, aluno.getCurso(), anoLectivo.get(0));
			
			GuiaPagamentoHistorico guiaHistorico = GuiaHistorico.guiaHistorico(aluno, anoLectivo.get(0), emolumento, guiaSalva, eH);
			guiaHistorico.setCodigoIva(emolumento.getCodigoIva());
			guiaHistorico.setPercentagemIva(emolumento.getPercentagemIva().toString());
			guiaHistorico.setQuantidade("1");
			guiaHistorico.setGuia(guiaSalva);
			guiaHistorico.setObs(i+1+"ª Parcela de "+processamento.getnPrestacoes());
			
			ultimaGuia = this.historicoGuiaRepository.save(guiaHistorico);
			this.guiaService.gerarHistoricoAudit(guiaHistorico);
			
		}
		
		mv.setDataUltimaPrestacao(ultimaGuia.getGuia().getDataVencimento());
		this.guiaMultaValorRepository.save(mv);
		//BIT
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
       	c.setMensagem("Negóciação processada com sucesso.");
		c.setResultado(ac);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	private String geraNumeroDuia(Integer lectivo, String definitivo, Long proximoNumeroInteiro) {
		if(proximoNumeroInteiro>=1      && proximoNumeroInteiro<=9)
			definitivo=lectivo+"00000"  +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=10     && proximoNumeroInteiro<=99)
			definitivo=lectivo+"0000"   +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=100    && proximoNumeroInteiro<=999)
			definitivo=lectivo+"000"    +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=1000   && proximoNumeroInteiro<=9999)
			definitivo=lectivo+"00"     +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=10000  && proximoNumeroInteiro<=99999)
			definitivo=lectivo+"0"      +proximoNumeroInteiro;
		if(proximoNumeroInteiro>=100000 && proximoNumeroInteiro<=999999)
			definitivo=lectivo          +proximoNumeroInteiro.toString();
		return definitivo;
	}
	 
	@RequestMapping(value = "/planos/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> planos(@PathVariable Integer numero) { 
		ResponseCliente c=new ResponseCliente();
		List<GuiaMultaValor> planos = this.guiaMultaValorRepository.findByNumeroDeAluno(numero);
		Aluno aluno                 = this.alunoRepository.findByNumeroDeAluno(numero.toString());
		
		if(planos.isEmpty()) {
	       	c.setCodigo(ResponseCode.values()[2].getDescricao());
	       	c.setMensagem("Nenhum plano de pagamento encontrado.");
			c.setResultado(planos);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);			
		}
		 
		CompostoPlanoCliente composto                   =null;
		List<CompostoPlanoCliente>  compostoClientePlano=new ArrayList<CompostoPlanoCliente>();
		
		//CompostoCliente
        for (GuiaMultaValor guiaMultaValor : planos) {
        	composto=new CompostoPlanoCliente();
     
        	composto.setGuiaMultaValor(guiaMultaValor);
        	//this.guiaPlanoMultaRepository.fin
        	//acertar multas.
        	//findByGuiaMultaValor           --> primeiro
        	GuiaCliente gn;
        	List<GuiaCliente> gns                    =new ArrayList<GuiaCliente>();
        	List<GuiaMultaNegociacao> guiaNegociacao = this.guiaMultaNegociacaoRepository.findByGuiaMultaValor(guiaMultaValor);
        	for (GuiaMultaNegociacao mn : guiaNegociacao) {
        		gn        =new GuiaCliente();
        		Guia guia = this.guiaPagamentoRepository.findByNumeroGuia(mn.getGuia().getNumeroGuia());	
        		gn.setAcordo(guia.isAcordo());
        		gn.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
        		gn.setAnulada(guia.isAnulada());
        		gn.setAutomaticamente(guia.isAutomaticamente());
        		gn.setDataAnulada(guia.getDataAnulada());
        		gn.setDataEmicao(guia.getDataEmicao());
        		gn.setDataVencimento(guia.getDataVencimento());
        		gn.setId(guia.getId());
        		gn.setLiquidada(guia.isLiquidada());
        		gn.setMotivoAnulacaoGuia(guia.getMotivoAnulacaoGuia());
        		gn.setMotivoAnulacaoRecibo(guia.getMotivoAnulacaoRecibo());
        		gn.setNumero(guia.getNumeroGuia());
        		gn.setValor(guia.getValor());
        		
        		
        		gns.add(gn);
			}
        	//setar as guias anuladas que geraram o acordo.
        	composto.setGuiasAnuladasParaAcordo(gns);
        	//GuiaMultaNegociacaoRepository  --> segundo
        	
        	
        	gns.clear();
        	//buscar as guias resultantes da negociação.
        	List<GuiaPlanoMulta> guiasPlano = this.guiaPlanoMultaRepository.findByGuiaMultaValor(guiaMultaValor);
        	for (GuiaPlanoMulta gpm : guiasPlano) {
        		gn        =new GuiaCliente();
        		Guia guia = this.guiaPagamentoRepository.findByNumeroGuia(gpm.getGuia().getNumeroGuia());
        		
        		gn.setAcordo(guia.isAcordo());
        		gn.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
        		gn.setAnulada(guia.isAnulada());
        		gn.setAutomaticamente(guia.isAutomaticamente());
        		gn.setDataAnulada(guia.getDataAnulada());
        		gn.setDataEmicao(guia.getDataEmicao());
        		gn.setDataVencimento(guia.getDataVencimento());
        		gn.setId(guia.getId());
        		gn.setLiquidada(guia.isLiquidada());
        		gn.setMotivoAnulacaoGuia(guia.getMotivoAnulacaoGuia());
        		gn.setMotivoAnulacaoRecibo(guia.getMotivoAnulacaoRecibo());
        		gn.setNumero(guia.getNumeroGuia());
        		gn.setValor(guia.getValor());
        		
        		gns.add(gn);
        		
			}
        	composto.setGuiasDoAcordo(gns);	
        	compostoClientePlano.add(composto);
		}
        PlanoControleCliente plano=new PlanoControleCliente();
        plano.setContencioso(aluno.isContencioso());
        plano.setCurso(aluno.getCurso().getPlano());
        plano.setFimCurso(aluno.isFimCurso());
        plano.setInativo(aluno.isInactivo());
        plano.setNome(aluno.getNome());
        plano.setNumeroAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
        plano.setControlePlanos(compostoClientePlano);
        
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(plano);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/controle", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> controle(@RequestBody MultaRetiradasCliente processamento) { 
		ResponseCliente c=new ResponseCliente();
		Aluno aluno                 = this.alunoRepository.findByNumeroDeAluno(processamento.getNumeroDeAluno());
		if(aluno==null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
	       	c.setMensagem("Aluno não existe.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
       	c.setCodigo(ResponseCode.values()[0].getDescricao());
       	c.setMensagem("Negóciação processada com sucesso.");
		c.setResultado(null);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}
	
	@GetMapping("/plano/pagamento")
	@ResponseBody 
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioPlanoPagamento(@RequestParam String numero_aluno, @RequestParam Integer codigo_multa) throws Exception {
		byte[] relatrio = servicoPlanoPagamento(numero_aluno, codigo_multa);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}
	public byte[] servicoPlanoPagamento(String numero_aluno, Integer codigo_multa) throws JRException {
		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		

		String dataFormatada = formato.format(data);
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_aluno", numero_aluno);
		paramets.put("codigo_multa", codigo_multa);
		paramets.put("data",dataFormatada);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_termo_pagamento.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
}