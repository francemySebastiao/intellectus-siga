package ao.co.intellectus.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AlunoDisciplinasMatriculadas;
import ao.co.intellectus.DTO.AlunoForSolicitacaoCliente;
import ao.co.intellectus.DTO.AnosInscricoes;
import ao.co.intellectus.DTO.DisciplinasInscritasCleinte;
import ao.co.intellectus.DTO.InscricaoPorAno;
import ao.co.intellectus.DTO.PedidoDocumentoCliente;
import ao.co.intellectus.DTO.PedidoParcialDTO;
import ao.co.intellectus.DTO.PedidoPorGuiaCliente;
import ao.co.intellectus.DTO.PedidoResumoCliente;
import ao.co.intellectus.DTO.ResgistroDocumentoCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.CertificadoIntermedio;
import ao.co.intellectus.model.CertificadoIntermedioMestrado;
import ao.co.intellectus.model.DeclaracaoDestino;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.EmolumentoPedido;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.RegistroDocumentos;
import ao.co.intellectus.model.TipoDeDeclaracao;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.enumeracao.TipoFatura;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CertificadoIntermedioMestradoRepository;
import ao.co.intellectus.repository.CertificadoIntermedioRepository;
import ao.co.intellectus.repository.DeclaracaoDestinoRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoPedidoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.RegistroDocumentoRepository;
import ao.co.intellectus.repository.TipoDeDeclaracaoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarNumeroDocumento;
import ao.co.intellectus.servico.cafold.GuiaService;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.guias.GerarNumeroDeGuia;
import ao.co.intellectus.servico.guias.GuiaDeInscricao;
import ao.co.intellectus.servico.guias.GuiaHistorico;
import ao.co.intellectus.util.FormataData;

@RestController
@RequestMapping("/pedidoDocumento")
public class ControllerPedidoDocumento {
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private MatriculaRepository repositoryMatricula;
	@Autowired
	private HistoricoAlunoRepository historicoAlunoRepository;
	@Autowired
	private EmolumentoPedidoRepository emolumentoPedidoRepository;
	@Autowired
	private RegistroDocumentoRepository registroDocumentoRepository;
	@Autowired
	private TipoDeDeclaracaoRepository tipoDeclaracaoRepository;
	@Autowired
	private DeclaracaoDestinoRepository declaracaoDestinoRepository;
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaPagamentoRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private CertificadoIntermedioRepository certificadoIntermedioRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private GuiaService guiaService;
	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;
	@Autowired
	private GeradorDeArquivo gerarDocService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CertificadoIntermedioMestradoRepository certificadoIntermedioMestradoRepo;
	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepo;
	
	FormataData forma = new FormataData();
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() {
		ResponseCliente c = new ResponseCliente();

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(null);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscaAlunoForSolicitacao/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscaAlunoForSolicitacao(@PathVariable String numero) {
		ResponseCliente c = new ResponseCliente();
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);

		AlunoForSolicitacaoCliente aForSolicitacao = new AlunoForSolicitacaoCliente();

		BeanUtils.copyProperties(aluno, aForSolicitacao);

		aForSolicitacao.setCursoId(aluno.getCurso().getId());
		aForSolicitacao.setCurso(aluno.getCurso().getPlano());
		aForSolicitacao.setGrau(aluno.getCurso().getGrau().getDescricao());

		List<Matricula> inscricaoPorAno = this.repositoryMatricula.findByAlunoAndAnulado(aluno, false);

		InscricaoPorAno inscricao;
		List<InscricaoPorAno> inscricoes = new ArrayList<InscricaoPorAno>();
		for (Matricula matricula : inscricaoPorAno) {
			inscricao = new InscricaoPorAno();

			inscricao.setId(matricula.getId());
			inscricao.setAnoCurricular(matricula.getAnoCurricular());
			inscricao.setDataInscricao(matricula.getData());
			inscricao.setCurso(matricula.getCurso().getDescricao());
			inscricao.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
			inscricao.setAnoLectivoDescricao(matricula.getAnoLectivo().getAnoLectivoDescricao());
			if (matricula.getTipoInscricao() != null)
				inscricao.setTipoInscricao(matricula.getTipoInscricao().getDescricao());
			inscricao.setSigla(matricula.getCurso().getSigla());

			inscricoes.add(inscricao);
		}
		aForSolicitacao.setInscricaoPorAno(inscricoes);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(aForSolicitacao);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/declaracaoSimples", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> declaraLicenciatura() {
		ResponseCliente c = new ResponseCliente();

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(null);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/declaracaoPresenca", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> declaraPresenca() {
		ResponseCliente c = new ResponseCliente();

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(null);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	// ResgistroDocumentoCliente

	@RequestMapping(value = "/pedidosAluno{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pedidosAluno(@PathVariable String numero) {
		ResponseCliente c = new ResponseCliente();
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(null);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/geral", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> geral(@RequestBody ResgistroDocumentoCliente registro) {
		ResponseCliente c = new ResponseCliente();
		
		
		
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);
		
		Usuario usuario = this.usuarioRepository
				.findByUserName(registro.getUserName() != null ? registro.getUserName() : null);
		
		
		RegistroDocumentos rDocumentos = new RegistroDocumentos();
		String mensagem;
		Aluno aluno = this.alunoRepository.findOne(registro.getAluno());
		mensagem = "Pedido registrado com sucesso.";
		
		TipoDeDeclaracao tipoDeclaracao = registro.getTipoDeclaracao() != null ? this.tipoDeclaracaoRepository.findOne(registro.getTipoDeclaracao()) : null;

		List<CertificadoIntermedio> cursadasConsocilidadas;
		List<CertificadoIntermedio> disciplinasCursadas;
		
		//MESTRADO
		List<CertificadoIntermedioMestrado> cursadasConsocilidadasMestrado;
		List<CertificadoIntermedioMestrado> disciplinasCursadasMestrado;

		List<Guia> guias = this.guiaRepository.findByAlunoAndLiquidadaAndAnulada(aluno, false,false);
		System.err.println(!guias.isEmpty());
		//&& tipoDeclaracao.getId()==4 || tipoDeclaracao.getId()==8
		if(!guias.isEmpty() && (tipoDeclaracao.getId()==1 ||tipoDeclaracao.getId()==8 && tipoDeclaracao.getId()==4) ) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Existem guias em não liquidadas.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		

		int maiorAno=0;
		List<Matricula> inscricoes = this.repositoryMatricula.findByAluno(aluno);
		for (Matricula matricula : inscricoes) {
			if(matricula.getAnoCurricular()>=maiorAno) {				
				maiorAno=matricula.getAnoCurricular();
			}
		}
		
		if(tipoDeclaracao.getId()==12 && maiorAno>2) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Este aluno não é do 1º Ano");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		/*if(tipoDeclaracao.getId() == 14 && !aluno.isInscrito()) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Estudante não tem matrícula activa em pelo menos uma UC");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			
		}*/
		
		//System.out.println("Maior " + maiorAno);
		/*List<HistoricoAluno> hAluno = historicoAlunoRepository.findByAluno(aluno);
		if(aluno.getCurso().getGrau() == Grau.MESTRADO) {
			
			for (HistoricoAluno historicoAluno : hAluno) {
				//System.out.println("Historico " + historicoAluno.isAprovado());
				if(!historicoAluno.isAprovado() && tipoDeclaracao.getId() == 1 || tipoDeclaracao.getId() == 8) {
					
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					c.setMensagem("Estudante não concluiu parte lectiva");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
					
				}else if(tipoDeclaracao.getId() == 16 && (!historicoAluno.isAprovado()) && maiorAno < 2) {
					
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					c.setMensagem("Estudante não concluiu pelo menos uma UC");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
					
				}else if(tipoDeclaracao.getId() == 17 && (!historicoAluno.isAprovado()) && maiorAno < 2) {
					
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					c.setMensagem("Estudante não concluiu parte lectiva");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}
		}*/
		
		
		/*if(tipoDeclaracao.getId() == 19 && maiorAno < 2) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Estudante não tem matrícula activa na segunda parte do curso de mestrado");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			
		}*/

		if (tipoDeclaracao.getId() == 4 || tipoDeclaracao.getId() == 5 || tipoDeclaracao.getId() == 1 || tipoDeclaracao.getId()==8) {
			List<Disciplina> qtdDisciplinas = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);

			int totalCurso = qtdDisciplinas.size();
			int totalCursoMestrado = qtdDisciplinas.size();
			
			int totalCursadas = 0;
			int totalCursadasMestrado = 0;
			
			int disciplinaNull = 0;
			
			int totalCursoMestradoActual = 0;
			int totalCursadasMestradoActual = 0;
			// INICIALIZAR UM ARRAY VAZIU PARA APLICAR AS DISCIPLINAS DE FORMA A NÃO
			// REPETIR.
			cursadasConsocilidadas = new ArrayList<CertificadoIntermedio>();
			
			//MESTRADO
			cursadasConsocilidadasMestrado = new ArrayList<CertificadoIntermedioMestrado>();
			// BUSCA TODAS AS DISCIPLINAS CURSADAS E EQUIVALENCIAS LANÇADAS.
			System.out.println("Aluno:"+aluno.getNumeroDeAluno());
			disciplinasCursadas = this.certificadoIntermedioRepository.findByNumeroAndValidacao(Integer.parseInt(aluno.getNumeroDeAluno()), true);
			
			disciplinasCursadasMestrado = this.certificadoIntermedioMestradoRepo
					.findByNumeroAndValidacao(Integer.parseInt(aluno.getNumeroDeAluno()), true);
			
			System.out.println("Passou:");
			for (CertificadoIntermedio it : disciplinasCursadas) {
				if (!cursadasConsocilidadas.contains(it)) {
					cursadasConsocilidadas.add(it);
				}
			}
			
			// MESTRADO
			for (CertificadoIntermedioMestrado mest : disciplinasCursadasMestrado) {
				if (!cursadasConsocilidadasMestrado.contains(mest)) {
					cursadasConsocilidadasMestrado.add(mest);
				}
			}
			
			// MESTRADO
			for (CertificadoIntermedioMestrado cim : cursadasConsocilidadasMestrado) {
				if(cim.getNotaFinal() == null) {
					
					disciplinaNull += 1;
				}
			}
			// PEGAMOS O TOTAL DE DISCIPLINAS FINALIZADAS ELIMINADO REPETIÇÕES
			totalCursadas = disciplinasCursadas.size();
			
			
			//MESTRADO
			totalCursadasMestrado = disciplinasCursadasMestrado.size();
			totalCursoMestradoActual = totalCursoMestrado - disciplinaNull;
			totalCursadasMestradoActual = totalCursadasMestrado - disciplinaNull;
			
			if(aluno.getCurso().getGrau() == Grau.LICENCIATURA || aluno.getCurso().getGrau() == Grau.POSGRADUACAO){
				// verifica se o total do curso ainda é maior que o total cursada
				if (totalCurso > totalCursadas) {
					c.setCodigo(ResponseCode.values()[1].getDescricao());
					c.setMensagem(mensagem);
					c.setMensagem("Existem disciplinas não finalizadas: " + totalCursadas + "/" + totalCurso);
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}
			
			if(aluno.getCurso().getGrau() == Grau.MESTRADO){
				// verifica se o total do curso ainda é maior que o total cursada
				if (totalCursoMestradoActual > totalCursadasMestradoActual) {
					c.setCodigo(ResponseCode.values()[1].getDescricao());
					c.setMensagem(mensagem);
					c.setMensagem("Existem disciplinas não finalizadas mestrado: " + totalCursadas + "/" + totalCurso);
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}
			
		}

		if (tipoDeclaracao.getId() == 2 || tipoDeclaracao.getId() == 3) {
			List<Disciplina> qtdDisciplinas = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);

			int totalCurso = qtdDisciplinas.size();
			int totalCursadas = 0;
			// INICIALIZAR UM ARRAY VAZIU PARA APLICAR AS DISCIPLINAS DE FORMA A NÃO
			// REPETIR.
			cursadasConsocilidadas = new ArrayList<CertificadoIntermedio>();
			// BUSCA TODAS AS DISCIPLINAS CURSADAS E EQUIVALENCIAS LANÇADAS.
			System.out.println("Aluno:"+aluno.getNumeroDeAluno());
			disciplinasCursadas = this.certificadoIntermedioRepository.findByNumeroAndValidacao(Integer.parseInt(aluno.getNumeroDeAluno()), true);
			
			System.out.println("Passou:");
			for (CertificadoIntermedio it : disciplinasCursadas) {
				if (!cursadasConsocilidadas.contains(it)) {
					cursadasConsocilidadas.add(it);
				}
			}
			// PEGAMOS O TOTAL DE DISCIPLINAS FINALIZADAS ELIMINADO REPETIÇÕES
			totalCursadas = disciplinasCursadas.size();
			// verifica se o total do curso ainda é maior que o total cursada
			if (totalCurso == totalCursadas) {
				c.setCodigo(ResponseCode.values()[1].getDescricao());
				c.setMensagem(mensagem);
				c.setMensagem("Não pode solicitar esta declaração, cursado: " + totalCursadas + "/" + totalCurso);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		}

		//Usuario usuarioSolicitou = this.usuarioRepository.findByUserCode(registro.getUsuarioSolicitou());
		DeclaracaoDestino declaracaoDestino = registro.getDeclaracaoDestino() != null ? this.declaracaoDestinoRepository.findOne(registro.getDeclaracaoDestino()): null;
		AnoLectivo anoLectivo = registro.getAnoDeclaracao() != null ? this.anoLectivoRepository.findByAnoLectivo(registro.getAnoDeclaracao()) : null;

		// busca dados financeiros.
		EmolumentoPedido emolumentoPedido = this.emolumentoPedidoRepository.findByTipoDeDeclaracao(tipoDeclaracao);
		//EmolumentoHistorico emolumentoHistorico;
		
		List<AnoLectivo> anoLectivoActivo = this.anoLectivoRepository.findByStatus(true);
		//Matricula alunoInscricao = repositoryMatricula.findByAlunoAndAnoLectivo(aluno, anoLectivoActivo.get(0));
		
		EmolumentoHistorico emolumentoHistorico = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumentoPedido.getEmolumento(), aluno.getCurso(), anoLectivoActivo.get(0));
		
		/*if(alunoInscricao == null) {
			
			//Matricula alunoSem = repositoryMatricula.buscarUltimaMatricula(aluno.getNumeroDeAluno());
			emolumentoHistorico = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumentoPedido.getEmolumento(), aluno.getCurso(), anoLectivoActivo.get(0));
		}else {
		
			emolumentoHistorico = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumentoPedido.getEmolumento(), aluno.getCurso(), alunoInscricao.getAnoLectivo());
		}*/

		Instituicao instituicao = this.instituicaoRepository.findOne(2);
		Moeda moeda = this.moedaRepository.findByStatus(true);

		if(tipoDeclaracao.getId()==10) {
			TipoDeDeclaracao decTransferencia = this.tipoDeclaracaoRepository.findOne(2);
			 rDocumentos.setTipoDeclaracao(decTransferencia);
		}else {
			rDocumentos.setTipoDeclaracao(tipoDeclaracao);
		}

		rDocumentos.setDeclaracaoDestino(declaracaoDestino);
		//rDocumentos.setUsuario_solicitou(usuarioSolicitou);
		rDocumentos.setAnoDeclaracao(registro.getAnoDeclaracao());
		rDocumentos.setAluno(aluno);
		rDocumentos.setDataDeclaracao(new Date());
		rDocumentos.setAnoDeclaracao(anoLectivo.getAnoLectivo());
		//rDocumentos.setEntidadePesquisa(registro.getEntidadePesquisa());
		// rDocumentos.setGuiaPagamento(guiaPagamento);
		// Salvar um determinado pedido
		RegistroDocumentos registroSalvo = this.registroDocumentoRepository.save(rDocumentos);
		// Instanciar a guia para o pedido
		Guia guia = GuiaDeInscricao.guiaDeInscricao(aluno, anoLectivo, emolumentoHistorico, moeda, instituicao);
		guia.setMoeda(moeda);
		guia.setValor(emolumentoHistorico.getValor());
		guia.setInstituicao(instituicao);
		guia.setUltimaModificacao(new Date());
		guia.setAcordo(false);
		guia.setAnulada(false);
		guia.setAlterada(false);
		guia.setLiquidada(false);
		guia.setGerouCredito(false);
		guia.setGeradaReferencia(false);
		guia.setLiquidacaoAGT(false);
		guia.setLiquidacaoCredito(false);
		guia.setGeradaOnline(false);
		guia.setUsuarioEmitiu(usuario);
		guia.setDataSistema(dataSistema);
		Guia g = this.guiaRepository.save(guia);
		// geração do número da guia
		String definitivo = "";
		Integer lectivo = anoLectivo.getAnoLectivo();
		
		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);

		Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
		// metódo gerar numero de guia chamado
		// definitivo = gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
		definitivo = GerarNumeroDeGuia.gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
		Guia guiaExistente = this.guiaRepository.findByNumeroGuia(definitivo);
		if (guiaExistente != null) {
			do {
				proximoNumeroInteiro++;
				// metódo gerar numero de guia chamado
				definitivo = GerarNumeroDeGuia.gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
				guiaExistente = this.guiaRepository.findByNumeroGuia(definitivo);
				
			} while (guiaExistente != null);
		}
		
		String numero ="";
		
		/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
		String ano = String.valueOf(anoActivo.getAnoLectivo());
		String anoSubstring = ano.substring(2,4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);*/
		
		NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
		Long proximoNumero = numeroGeradoFP.getProximoNumero();
		
		//String numero = gerarNumeroDocService.geracaoNumero();
		numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
		
		Guia proformaExiste = this.guiaRepository.findProforma(numero);
		GuiaCandidatura proformaExistente = guiaCandidaturaRepo.buscarProforma(numero);
		if (proformaExiste != null || proformaExistente != null) {
			do {
				proximoNumero++;
				
				numero =  gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
				proformaExiste = this.guiaRepository.findProforma(numero);
				proformaExistente = guiaCandidaturaRepo.buscarProforma(numero);
			} while (proformaExiste != null || proformaExistente != null);
		}
		// setar o valor da guia
		g.setNumeroGuia(definitivo);
		g.setNumeroFacturaProforma(numero);
		g.setTipoFactura(TipoFatura.FACTURA_PROFORMA);
		g.setParaAcordoPagamento(false);
		

		// final geração do número da guia
		g.setUltimaModificacao(new Date());
		Guia guiaGuardada = this.guiaRepository.save(g);
		
		this.gerarDocService.gerarFileProformaAluno(guiaGuardada);
		
		numeroGeradoFP.setUltimoNumero(proximoNumero);
		numeroGeradoFP.setProximoNumero(proximoNumero + 1);
		this.numeroGeradoRepository.save(numeroGeradoFP);
		
		// atualizador os dados da ultima guia e a proxima guia
		numeroGerado.setUltimoNumero(proximoNumeroInteiro);
		numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
		this.numeroGeradoRepository.save(numeroGerado);

	
		// Historico Guia
		GuiaPagamentoHistorico guiaHistorico = GuiaHistorico.guiaHistorico(aluno, anoLectivo,
				emolumentoPedido.getEmolumento(), g, emolumentoHistorico);

		this.historicoGuiaPagamentoRepository.save(guiaHistorico);
		this.guiaService.gerarHistoricoAudit(guiaHistorico);
		// setar a guia ao pedido e gerar o numero do pedido
		registroSalvo.setGuiaPagamento(g);
		registroSalvo.setNumeroDeclaracao(registroSalvo.getId());
		// Salvar o pedido pela segunda vez.
		this.registroDocumentoRepository.save(registroSalvo);
		// mensagem="Este pedido não pode ser associado a este aluno.";
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarAlunoAndDisciplinas/{numero}/{userCode}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarAlunoAndDisciplinas(@PathVariable String numero, Integer userCode) {
		ResponseCliente c = new ResponseCliente();
		AlunoDisciplinasMatriculadas dAluno = new AlunoDisciplinasMatriculadas();

		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);
		// System.out.println("Aluno: " + aluno);

		if (aluno == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum aluno encontrado com este número.");

			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		// List<Matricula> matriculas =
		// this.repositoryMatricula.findByAlunoAndAnulado(aluno, false);

		List<Matricula> matriculas = this.repositoryMatricula.findByAluno(aluno);

		if (matriculas == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Não existe nenhuma inscrição.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<HistoricoAluno> historico = this.historicoAlunoRepository.findByAlunoAndCurso(aluno, aluno.getCurso());
		if (historico.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Não existe nenhuma disciplina em historico.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<RegistroDocumentos> pedidos = this.registroDocumentoRepository.findByAluno(aluno);

		List<PedidoDocumentoCliente> pedidosDocumentos = new ArrayList<PedidoDocumentoCliente>();
		PedidoDocumentoCliente pedidoDocumento;
		for (RegistroDocumentos rD : pedidos) {
			pedidoDocumento = new PedidoDocumentoCliente();

			pedidoDocumento.setAnoDeclaracao(rD.getAnoDeclaracao());
			pedidoDocumento.setDataPedido(rD.getDataDeclaracao());
			if (rD.getDeclaracaoDestino() != null) {
				pedidoDocumento.setDeclaracaoDestino(rD.getDeclaracaoDestino().getDescricao());
				pedidoDocumento.setDeclaracaoDestinoId(rD.getDeclaracaoDestino().getId());
			}
			if (rD.getGuiaPagamento() != null)
				pedidoDocumento.setGuiaPaga(rD.getGuiaPagamento().isLiquidada());
			pedidoDocumento.setIdPedido(rD.getId());
			pedidoDocumento.setRetirado(rD.isRetirado());
			if (rD.getGuiaPagamento().getUsuarioEmitiu() != null) {
				pedidoDocumento.setUserEmitiu(rD.getGuiaPagamento().getUsuarioEmitiu().getName());
			} else {
				pedidoDocumento.setUserEmitiu(null);
			}
			pedidoDocumento.setTipoDeclaracao(rD.getTipoDeclaracao().getDescricao());

			pedidosDocumentos.add(pedidoDocumento);
		}
		dAluno.setPedidos(pedidosDocumentos);

		dAluno.setAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
		dAluno.setNome(aluno.getNome());
		dAluno.setCurso(aluno.getCurso().getPlano());
		dAluno.setFimCurso(aluno.isFimCurso());
		dAluno.setContencioso(aluno.isContencioso());
		dAluno.setGrau(aluno.getCurso().getGrau().getDescricao());

		List<InscricaoPorAno> inscricoes = new ArrayList<InscricaoPorAno>();
		InscricaoPorAno inscricao;

		for (Matricula matricula : matriculas) {
			inscricao = new InscricaoPorAno();
			inscricao.setId(matricula.getId());
			inscricao.setAnoCurricular(matricula.getAnoCurricular());
			inscricao.setDataInscricao(matricula.getData());
			inscricao.setCurso(matricula.getCurso().getDescricao());
			inscricao.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
			inscricao.setAnoLectivoDescricao(matricula.getAnoLectivo().getAnoLectivoDescricao());
			// if(matricula.getTipoInscricao()!=null)
			inscricao.setTipoInscricao(matricula.getTipoInscricao().getDescricao());
			inscricao.setSigla(matricula.getCurso().getSigla());
			inscricoes.add(inscricao);
		}
		dAluno.setInscricaoPorAno(inscricoes);

		List<DisciplinasInscritasCleinte> fDisciplinas = new ArrayList<DisciplinasInscritasCleinte>();
		DisciplinasInscritasCleinte fDisciplina;
		for (HistoricoAluno historicoAluno : historico) {
			fDisciplina = new DisciplinasInscritasCleinte();

			fDisciplina.setDescricao(historicoAluno.getDisciplina().getDescricao());
			fDisciplina.setId(historicoAluno.getDisciplina().getId());
			fDisciplina.setCodigoMatricula(historicoAluno.getMatricula().getId());

			if (!historicoAluno.getMatricula().isAnulado())
				fDisciplinas.add(fDisciplina);
		}

		dAluno.setDisciplinasInscritas(fDisciplinas);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(dAluno);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarAlunoAndPedidos/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarAlunoAndPedidos(@PathVariable String numero) {
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);
		if (aluno == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(aluno, 2, "Nenhum aluno encontrado com este número.");
		} else {
			List<RegistroDocumentos> pedidos = this.registroDocumentoRepository.findByAluno(aluno);
			List<PedidoDocumentoCliente> pedidosDocumentos = new ArrayList<PedidoDocumentoCliente>();
			for (RegistroDocumentos rD : pedidos) {
				PedidoDocumentoCliente pedidoDocumento = new PedidoDocumentoCliente();
				pedidoDocumento.setAnoDeclaracao(rD.getAnoDeclaracao());
				pedidoDocumento.setDataPedido(rD.getDataDeclaracao());
				if (rD.getDeclaracaoDestino() != null) {
					pedidoDocumento.setDeclaracaoDestino(rD.getDeclaracaoDestino().getDescricao());
					pedidoDocumento.setDeclaracaoDestinoId(rD.getDeclaracaoDestino().getId());
				}
				if (rD.getGuiaPagamento() != null)
					pedidoDocumento.setGuiaPaga(rD.getGuiaPagamento().isLiquidada());
				pedidoDocumento.setIdPedido(rD.getId());
				pedidoDocumento.setRetirado(rD.isRetirado());
				pedidoDocumento.setTipoDeclaracao(rD.getTipoDeclaracao().getDescricao());
				pedidosDocumentos.add(pedidoDocumento);
			}
			return ObjectoDeRetornoParcial.MensagemDeRetorno(pedidosDocumentos, 0, null);
		}
	}

	@RequestMapping(value = "/documentos/por/anoLectivo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarDocumentosPorAnoLectivo() {
		ResponseCliente c = new ResponseCliente();

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(null);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/pedidoPorGuia/{numeroGuia}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarDetalhePedido(@PathVariable String numeroGuia) {
		ResponseCliente c = new ResponseCliente();
		// Busca a guia com o devido pagamento
		Guia guia = this.guiaRepository.findByNumeroGuiaAndLiquidada(numeroGuia, true);
		if (guia != null) {
			PedidoPorGuiaCliente pedido = new PedidoPorGuiaCliente();
			pedido.setNumeroGuia(numeroGuia);
			pedido.setContencioso(guia.getAluno().isContencioso());
			pedido.setCurso(guia.getAluno().getCurso().getPlano());
			pedido.setFimCurso(guia.getAluno().isFimCurso());
			pedido.setInativo(guia.getAluno().isInactivo());
			pedido.setNome(guia.getAluno().getNome());
			pedido.setNumeroDeAluno(Integer.parseInt(guia.getAluno().getNumeroDeAluno()));
			List<AnosInscricoes> anoInscricoes = new ArrayList<AnosInscricoes>();
			AnosInscricoes a;
			List<Matricula> inscricoes = this.repositoryMatricula.findByAlunoAndAnulado(guia.getAluno(), false);
			for (Matricula m : inscricoes) {
				a = new AnosInscricoes();
				a.setAnoLectivo(m.getAnoLectivo().getAnoLectivo());
				a.setCodigoAnoLectivo(m.getAnoLectivo().getId());
				a.setBolseiro(m.getEmpresaConvenio() != null ? true : false);
				if (m.getEmpresaConvenio() != null)
					a.setEmpresaConvenio(m.getEmpresaConvenio().getDesignacao());
				anoInscricoes.add(a);
			}
			pedido.setAnoInscricoes(anoInscricoes);

			List<GuiaPagamentoHistorico> emolumento = this.historicoGuiaPagamentoRepository.findByGuia(guia);

			for (GuiaPagamentoHistorico o : emolumento) {

				if (o.getEmolumento().getId() == 32) {
					Emolumento eml = this.emolumentoRepository.findById(5);
					o.setEmolumento(eml);
				}

				if (o.getEmolumento().getId() == 102) {
					Emolumento eml = this.emolumentoRepository.findById(5);
					o.setEmolumento(eml);
				}

				EmolumentoPedido ePedido = this.emolumentoPedidoRepository
						.findByEmolumentoCodigo(o.getEmolumento().getCodigo());
				if (ePedido != null) {
					pedido.setEmolumentoPedido(ePedido);
				}
			}
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(pedido);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {

			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Verifique a guia de pagamento.");
			c.setResultado(null);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/pedidoPorGuia", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> gerarPeidoPelaGuia(@RequestBody PedidoResumoCliente pedido) {
		ResponseCliente c = new ResponseCliente();

		Guia guia = this.guiaRepository.findByNumeroGuia(pedido.getGuiaPagamento());

		List<RegistroDocumentos> registo = this.registroDocumentoRepository.findByGuiaPagamento(guia);

		if (registo.isEmpty()) {

			TipoDeDeclaracao tipoDeclaracao = this.tipoDeclaracaoRepository.findOne(pedido.getTipoDeclaracao());

			DeclaracaoDestino declaracaoDestino = null;
			if (pedido.getDeclaracaoDestino() != null)
				declaracaoDestino = this.declaracaoDestinoRepository.findOne(pedido.getDeclaracaoDestino());

			RegistroDocumentos rDocumento = new RegistroDocumentos();

			rDocumento.setAluno(guia.getAluno());
			rDocumento.setAnoDeclaracao(pedido.getAnoDeclaracao());
			rDocumento.setDataDeclaracao(new Date());
			rDocumento.setDeclaracaoDestino(declaracaoDestino);
			rDocumento.setGuiaPagamento(guia);
			rDocumento.setRetirado(false);

			if (pedido.getTipoDeclaracao() == 7) {
				tipoDeclaracao = this.tipoDeclaracaoRepository.findOne(3);
			}
			rDocumento.setTipoDeclaracao(tipoDeclaracao);

			RegistroDocumentos rSalvo = this.registroDocumentoRepository.save(rDocumento);

			rSalvo.setNumeroDeclaracao(rSalvo.getId());

			this.registroDocumentoRepository.save(rSalvo);

			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem("Pedido efetivado com sucesso.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
			c.setCodigo(ResponseCode.values()[1].getDescricao());
			c.setMensagem("Já existe um pedido com esta Guia.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	// pedidoDocumento/imprimirSegundaVia/{numero}

	@RequestMapping(value = "/imprimirSegundaVia/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> imprimirSegundaVia(@PathVariable Integer numero) {
		ResponseCliente c = new ResponseCliente();
		RegistroDocumentos registro = this.registroDocumentoRepository.findOne(numero);
		registro.setRetirado(false);
		this.registroDocumentoRepository.save(registro);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Pedido liberado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// http://192.168.1.216:8810/intellectus/pedidoDocumento/atualizar
	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody PedidoParcialDTO pedidoParcialDTO) {
		ResponseCliente c = new ResponseCliente();
		RegistroDocumentos registro = this.registroDocumentoRepository.findOne(pedidoParcialDTO.getIdPedido());

		registro.setDataEmissao(pedidoParcialDTO.getDataEmissao());
		this.registroDocumentoRepository.save(registro);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Pedido atualizado com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	         
	@RequestMapping(value = "/buscaAlunoRequerimento", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscaAlunoRequerimento(@RequestParam String numero) {
		ResponseCliente c = new ResponseCliente();
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);
		
		if(aluno.getCurso().getGrau() != Grau.MESTRADO) 
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Este não é um aluno de mestrado");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/Listagem_5", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todosMestrandos(@RequestParam String data1, @RequestParam String data2) throws ParseException {
		ResponseCliente c = new ResponseCliente();
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);

		
		List<RegistroDocumentos> documentos = registroDocumentoRepository.buscarTodos(dataF1, dataF2);
		List<PedidoDocumentoMestrado> listaDTO = new ArrayList<PedidoDocumentoMestrado>();
		if (documentos.isEmpty()) {
			c.setMensagem("Nenhum documento Encontrado.");
			c.setCodigo(600);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		for (RegistroDocumentos u : documentos) {
			PedidoDocumentoMestrado docx = new PedidoDocumentoMestrado();
			docx.setNomeAluno(u.getAluno().getNome());
			docx.setNumeroAluno(u.getAluno().getNumeroDeAluno());
			docx.setTipoDeclaracao(u.getTipoDeclaracao().getDescricao());
			docx.setDataEmissao(u.getDataEmissao());
			listaDTO.add(docx);
		}
		c.setResultado(listaDTO);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}*/
	
	/*@RequestMapping(value = "/Listagem_6", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> Listagem_6(@RequestParam String data1, @RequestParam String data2) throws ParseException {
		ResponseCliente c = new ResponseCliente();
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);

		
		List<RegistroDocumentos> documentos = registroDocumentoRepository.Listagem_7(dataF1, dataF2);
		List<PedidoDocumentoMestrado> listaDTO = new ArrayList<PedidoDocumentoMestrado>();
		if (documentos.isEmpty()) {
			c.setMensagem("Nenhum documento Encontrado.");
			c.setCodigo(600);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		for (RegistroDocumentos u : documentos) {
			PedidoDocumentoMestrado docx = new PedidoDocumentoMestrado();
			docx.setNomeAluno(u.getAluno().getNome());
			docx.setTipoDeclaracao(u.getTipoDeclaracao().getDescricao());
			docx.setDataEmissao(u.getDataEmissao());
			listaDTO.add(docx);
		}
		c.setResultado(listaDTO);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}*/
	
	/*@RequestMapping(value = "/Listagem_6_1", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> Listagem_6_1(@RequestParam String data1, @RequestParam String data2, String letra) throws ParseException {
		ResponseCliente c = new ResponseCliente();
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);

		
		List<RegistroDocumentos> documentos = registroDocumentoRepository.Listagem_7_2(dataF1, dataF2, letra);
		List<PedidoDocumentoMestrado> listaDTO = new ArrayList<PedidoDocumentoMestrado>();
		if (documentos.isEmpty()) {
			c.setMensagem("Nenhum documento Encontrado111.");
			c.setCodigo(600);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		for (RegistroDocumentos u : documentos) {
			PedidoDocumentoMestrado docx = new PedidoDocumentoMestrado();
			docx.setNomeAluno(u.getAluno().getNome());
			docx.setTipoDeclaracao(u.getTipoDeclaracao().getDescricao());
			docx.setDataEmissao(u.getDataEmissao());
			listaDTO.add(docx);
		}
		c.setResultado(listaDTO);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}*/
	
	/*RequestMapping(value = "/Listagem_7", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> Listagem_7(@RequestParam String data1, @RequestParam String data2) throws ParseException {
		ResponseCliente c = new ResponseCliente();
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);

		
		List<RegistroDocumentos> documentos = registroDocumentoRepository.Listagem_7(dataF1, dataF2);
		List<PedidoDocumentoMestrado> listaDTO = new ArrayList<PedidoDocumentoMestrado>();
		if (documentos.isEmpty()) {
			c.setMensagem("Nenhum documento Encontrado.");
			c.setCodigo(600);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		for (RegistroDocumentos u : documentos) {
			PedidoDocumentoMestrado docx = new PedidoDocumentoMestrado();
			docx.setNomeAluno(u.getAluno().getNome());
			docx.setNumeroAluno(u.getAluno().getNumeroDeAluno());
			docx.setTipoDeclaracao(u.getTipoDeclaracao().getDescricao());
			docx.setDataEmissao(u.getDataEmissao());
			listaDTO.add(docx);
		}
		c.setResultado(listaDTO);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}*/
	
	
	/*@RequestMapping(value = "/Listagem_7_2", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> Listagem_7_2(@RequestParam String data1, @RequestParam String data2, String nome) throws ParseException {
		ResponseCliente c = new ResponseCliente();
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);

		
		List<RegistroDocumentos> documentos = registroDocumentoRepository.Listagem_7_2(dataF1, dataF2, nome);
		List<PedidoDocumentoMestrado> listaDTO = new ArrayList<PedidoDocumentoMestrado>();
		if (documentos.isEmpty()) {
			c.setMensagem("Nenhum documento Encontrado.");
			c.setCodigo(600);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		for (RegistroDocumentos u : documentos) {
			PedidoDocumentoMestrado docx = new PedidoDocumentoMestrado();
			docx.setNomeAluno(u.getAluno().getNome());
			docx.setTipoDeclaracao(u.getTipoDeclaracao().getDescricao());
			docx.setDataEmissao(u.getDataEmissao());
			docx.setNumeroAluno(u.getAluno().getNumeroDeAluno());
			listaDTO.add(docx);
		}
		c.setResultado(listaDTO);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}*/
	
	/*@RequestMapping(value = "/Listagem_7_3", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> Listagem_7_3(@RequestParam String data1, @RequestParam String data2, @RequestParam Character letra1, @RequestParam Character letra2) throws ParseException {
		ResponseCliente c = new ResponseCliente();
		
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);

		
		List<RegistroDocumentos> documentos = registroDocumentoRepository.listagem_7_3(dataF1, dataF2, letra1, letra2);
		List<PedidoDocumentoMestrado> listaDTO = new ArrayList<PedidoDocumentoMestrado>();
		if (documentos.isEmpty()) {
			c.setMensagem("Nenhum documento Encontrado.");
			c.setCodigo(600);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		for (RegistroDocumentos u : documentos) {
			PedidoDocumentoMestrado docx = new PedidoDocumentoMestrado();
			docx.setNomeAluno(u.getAluno().getNome());
			docx.setNumeroAluno(u.getAluno().getNumeroDeAluno());
			docx.setTipoDeclaracao(u.getTipoDeclaracao().getDescricao());
			docx.setDataEmissao(u.getDataEmissao());
			listaDTO.add(docx);
		}
		c.setResultado(listaDTO);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}*/
	
	/*@RequestMapping(value = "/historico_certificado_mestrado", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> historicoCertificadoMestrado() throws ParseException {
		ResponseCliente c = new ResponseCliente();
		
		List<RegistroDocumentos> documentos = registroDocumentoRepository.buscarHistoricoCertificado();
		List<PedidoDocumentoMestrado> listaDTO = new ArrayList<PedidoDocumentoMestrado>();
		if (documentos.isEmpty()) {
			c.setMensagem("Nenhum registro encontrado.");
			c.setCodigo(600);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		for (RegistroDocumentos u : documentos) {

			PedidoDocumentoMestrado docx = new PedidoDocumentoMestrado();
			//docx.setNomeOperador(u.getUsuario_emitiu().getName());
			docx.setNumeroDeclaracao(u.getNumeroDeclaracao());
			docx.setTipoDeclaracao(u.getTipoDeclaracao().getDescricao());
			docx.setDataEmissao(u.getDataEmissao());
			docx.setNomeAluno(u.getAluno().getNome());
			
			listaDTO.add(docx);
		}
		c.setResultado(listaDTO);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}*/
}
