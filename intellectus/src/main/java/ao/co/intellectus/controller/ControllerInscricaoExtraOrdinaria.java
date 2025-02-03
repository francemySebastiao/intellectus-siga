package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import ao.co.intellectus.DTO.AlunoInscricaoExtraordinaria;
import ao.co.intellectus.DTO.BuscaCursoDeVerao;
import ao.co.intellectus.DTO.DisciplinaExtraordinariaCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.InscricaoExtraordinaria;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.PlanoPagamento;
import ao.co.intellectus.model.ProgCursoDeVeraoMap;
import ao.co.intellectus.model.Situacao;
import ao.co.intellectus.model.TipoDisciplina;
import ao.co.intellectus.model.TipoInscricao;
import ao.co.intellectus.model.TipoProvaExtraOrdinaria;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.ccd.HistoricoAlunoCdd;
import ao.co.intellectus.model.ccd.MatriculaCcd;
import ao.co.intellectus.model.enumeracao.TipoFatura;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.InscricaoExtraordinariaRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.PlanoPagamentoRepository;
import ao.co.intellectus.repository.ProgCursoDeVeraoMapRepository;
import ao.co.intellectus.repository.TipoDeInscricaoRepository;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.repository.ccd.HistoricoAlunoCddRepository;
import ao.co.intellectus.repository.ccd.MatriculaCcdRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarNumeroDocumento;
import ao.co.intellectus.servico.InscricaoExtraOrdinariaService;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.cafold.GuiaService;
import ao.co.intellectus.servico.cafold.HistoricoAlunoService;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.guias.GerarNumeroDeGuia;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import ao.co.intellectus.util.FormataData;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/inscricaoExtraOrdinaria")
public class ControllerInscricaoExtraOrdinaria {
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private HistoricoAlunoRepository historicoAluno;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private InscricaoExtraordinariaRepository inscricaoExtraOrdinaria;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaPagamentoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private HistoricoAlunoService historicoAlunoService;
	@Autowired
	private GuiaService guiaService;
	@Autowired
	private ProgCursoDeVeraoMapRepository progCursoDeVeraoMapRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private MatriculaCcdRepository matriculaRepositoryCcd;
	@Autowired
	private TipoDeInscricaoRepository tipoInscricaoRepository;
	@Autowired
	private PlanoPagamentoRepository planoPagamentoRepository;
	@Autowired
	private HistoricoAlunoCddRepository historicoAlunoCddRepository;

	@Autowired
	private InscricaoExtraOrdinariaService inscricaoExtraOrdinariaService;

	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;

	@Autowired
	private GeradorDeArquivo gerarDocService;

	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepo;
	
	FormataData forma = new FormataData();

	// METODO REFATORADO PARA O PADRÃO DE PROJECTO FACILITADO.
	@RequestMapping(value = "/buscaAlunoDisciplina/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscaAlunoDisciplina(@PathVariable String numero) {
		return inscricaoExtraOrdinariaService.buscaAlunoDisciplina(numero);
	}

	/*
	 * INSCRIÇÃO EM ÉPOCA EXTRAORDINÁRIA DESENVOLVEDOR: ERNESTO TADEU T. SAMBONGO
	 * DATA ALTERAÇÃO: 29-11-2018
	 */
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	@Transactional
	public ResponseEntity<ResponseCliente> salvar(@RequestBody AlunoInscricaoExtraordinaria efetuarMatricula) {

		if (efetuarMatricula.getTipoProvaExtraOrdinaria() == TipoProvaExtraOrdinaria.CCD
				&& efetuarMatricula.getTipoProvaExtraOrdinaria() != null) {
			return this.InscricaoCCD(efetuarMatricula);
		}

		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		ResponseCliente c = new ResponseCliente();
		float valorGuia = 0;

		// String mensagem="";
		Matricula matricula = new Matricula();
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(efetuarMatricula.getNumero().toString());

		List<AnoLectivo> lectivoGuia = this.anoLectivoRepository.findByStatus(true);

		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(efetuarMatricula.getAnoLectivo());

		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		List<DisciplinaExtraordinariaCliente> disciplinas = efetuarMatricula.getDisciplinas();
		Moeda moeda = this.moedaRepository.findOne(3);

		//Matricula matriculaPesquisa = matriculaRepository.findByAlunoAndAnoLectivo(aluno, anoLectivo);

		Usuario usuarioInscrveu = this.usuarioRepository.findOne(
				efetuarMatricula.getUsuarioInscreveu() != null ? efetuarMatricula.getUsuarioInscreveu() : null);

		// findByAnoLectivoAndAlunoAndDisciplina

		// boolean epocaEspecial = false;

		Emolumento emolumento = null;
		EmolumentoHistorico hEmolumento = null;
		InscricaoExtraordinaria ied;

		Matricula matriculado;
		// HistoricoAluno historico;
		Turma turma = null;
		List<DisciplinaExtraordinariaCliente> dis = efetuarMatricula.getDisciplinas();

		for (DisciplinaExtraordinariaCliente soma : dis) {
			if (soma.getTipoProva() == TipoProvaExtraOrdinaria.ER) {
				// anoLectivo = this.anoLectivoRepository.findByAnoLectivo(2019);
				emolumento = this.emolumentoRepository.findOne(7);
				hEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						aluno.getCurso(), anoLectivo);
				valorGuia = hEmolumento.getValor();
			}

			if (soma.getTipoProva() == TipoProvaExtraOrdinaria.EE) {

				emolumento = this.emolumentoRepository.findOne(170);
				hEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						aluno.getCurso(), anoLectivo);
				valorGuia = hEmolumento.getValor();
			}

			if (soma.getTipoProva() == TipoProvaExtraOrdinaria.PE) {

				emolumento = this.emolumentoRepository.findOne(2);
				hEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						aluno.getCurso(), anoLectivo);

				valorGuia = hEmolumento.getValor();
			}

			if (soma.getTipoProva() == TipoProvaExtraOrdinaria.M) {
				emolumento = this.emolumentoRepository.findOne(38);
				hEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						aluno.getCurso(), anoLectivo);
				valorGuia = hEmolumento.getValor();
			}

			if (soma.getTipoProva() == TipoProvaExtraOrdinaria.CV) {
				Disciplina disciplina = this.disciplinaRepository.findOne(soma.getId());

				if (TipoDisciplina.ANUAL.equals(disciplina.getTipo())) {
					emolumento = this.emolumentoRepository.findOne(411);
				} else {
					emolumento = this.emolumentoRepository.findOne(407);
				}

				hEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						aluno.getCurso(), anoLectivo);
				valorGuia = hEmolumento.getValor();
			}

		}

		List<InscricaoExtraordinaria> inscrito = null;

		for (DisciplinaExtraordinariaCliente dec : disciplinas) {
			// EMISSÃO DE GUIA DE PAGAMENTO

			// VALIDA AS DISCIPLINAS
			Disciplina disciplina = this.disciplinaRepository.findOne(dec.getId());

			inscrito = this.inscricaoExtraOrdinaria.findByAnoLectivoAndAlunoAndDisciplinaAndProva(anoLectivo, aluno,
					disciplina, dec.getTipoProva());

			if (inscrito.isEmpty()) {
				Guia guia = new Guia();
				guia.setAcordo(false);
				guia.setAnulada(false);
				guia.setLiquidada(false);
				guia.setGerouCredito(false);
				guia.setGeradaReferencia(false);
				guia.setGeradaOnline(false);
				guia.setAluno(aluno);
				guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
				guia.setAutomaticamente(true);
				guia.setDataEmicao(new Date());
				guia.setAlterada(false);
				guia.setLiquidada(false);
				guia.setGerouCredito(false);
				guia.setGeradaReferencia(false);
				guia.setLiquidacaoAGT(false);
				guia.setLiquidacaoCredito(false);
				guia.setGeradaOnline(false);
				guia.setInstituicao(instituicao);
				guia.setMoeda(moeda);
				guia.setAnoLectivo(lectivoGuia.get(0));
				guia.setValor(valorGuia);
				guia.setDataVencimento(new Date());
				guia.setUsuarioEmitiu(usuarioInscrveu);
				guia.setDataSistema(dataSistema);
				guia.setUltimaModificacao(new Date());

				Guia g = this.guiaRepository.save(guia);
				// gera o numero de guia para o aluno

				String definitivo = "";
				Integer lectivo = anoLectivo.getAnoLectivo();
				NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);
				Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
				// metódo gerar numero de guia chamado
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
				// setar o valor da guia
				g.setNumeroGuia(definitivo);

				// atualizador os dados da ultima guia e a proxima guia
				numeroGerado.setUltimoNumero(proximoNumeroInteiro);
				numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
				this.numeroGeradoRepository.save(numeroGerado);
				g.setUltimaModificacao(new Date());
				// final geração do número da guia

				String numero = "";

				/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
				String ano = String.valueOf(anoActivo.getAnoLectivo());
				String anoSubstring = ano.substring(2, 4);
				Integer anoLimpo = Integer.parseInt(anoSubstring);*/

				NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
				Long proximoNumero = numeroGeradoFP.getProximoNumero();

				// String numero = gerarNumeroDocService.geracaoNumero();
				numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);

				Guia proformaExiste = this.guiaRepository.findProforma(numero);
				GuiaCandidatura proformaCandidaturaExistente = guiaCandidaturaRepo.buscarProforma(numero);
				if (proformaExiste != null || proformaCandidaturaExistente != null) {
					do {
						proximoNumero++;

						numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
						proformaExiste = this.guiaRepository.findProforma(numero);
						proformaCandidaturaExistente = guiaCandidaturaRepo.buscarProforma(numero);
					} while (proformaExiste != null || proformaCandidaturaExistente != null);
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
				Guia guiaDisciplina = this.guiaRepository.save(g);

				ied = new InscricaoExtraordinaria();

				// regista guia histórico...
				// GuiaPagamentoHistorico guiaHistorico = GuiaHistorico.guiaHistorico(aluno,
				// anoLectivo, emolumento,guiaDisciplina, hEmolumento);

				// (Aluno aluno,AnoLectivo anoLectivo,Emolumento emolumento,Guia
				// pGuia,EmolumentoHistorico eH)

				GuiaPagamentoHistorico guiaHistorico = new GuiaPagamentoHistorico();

				double valorComIva = 0;
				double valorImposto = 0;

				valorImposto = (hEmolumento.getValor() * emolumento.getPercentagemIva()) / 100;
				valorComIva = hEmolumento.getValor() + valorImposto;

				double valorTotalIvaDesconto = (valorComIva
						- (hEmolumento.getValor() * emolumento.getPercentagemDesconto()) / 100);
				double desconto = (hEmolumento.getValor() * emolumento.getPercentagemDesconto()) / 100;

				guiaHistorico.setAluno(aluno);
				guiaHistorico.setAnoLectivo(lectivoGuia.get(0));
				guiaHistorico.setEmolumento(emolumento);
				guiaHistorico.setGuia(guiaDisciplina);
				guiaHistorico.setQuantidade("1");
				guiaHistorico.setValor(valorGuia);
				guiaHistorico.setCodigoIva(emolumento.getCodigoIva());
				guiaHistorico.setPercentagemIva(emolumento.getPercentagemIva().toString());
				guiaHistorico.setValorImposto(FormataData.formatarValor(valorImposto));
				guiaHistorico.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));
				guiaHistorico.setDesconto(FormataData.formatarValor(desconto));

				guiaHistorico.setObs("Recurso " + dec.getDescricao());
				this.historicoGuiaPagamentoRepository.save(guiaHistorico);
				this.guiaService.gerarHistoricoAudit(guiaHistorico);

				// GERAR HISTORICO

				// TAKI
				List<HistoricoAluno> histMat = null;

				if (dec.getTipoProva() == TipoProvaExtraOrdinaria.ER
						|| dec.getTipoProva() == TipoProvaExtraOrdinaria.M) {
					histMat = this.historicoAluno.findByDisciplinaAndAlunoAndMatriculaAnoLectivo(disciplina, aluno,
							lectivoGuia.get(0));
					for (HistoricoAluno hMat : histMat) {
						turma = hMat.getTurma();
					}
				} else {
					turma = this.turmaRepository.findOne(55);
				}

				// final geração do numero de guia
				ied.setAluno(aluno);
				ied.setAnoLectivo(anoLectivo);
				ied.setDataRegistro(new Date());
				ied.setGuiaPagamento(g);
				ied.setInstituicao(instituicao);
				ied.setDisciplina(disciplina);
				ied.setNomeroDeAluno(aluno.getNumeroDeAluno());
				ied.setTipoProvaExtraOrdinaria(dec.getTipoProva());
				ied.setProva(dec.getTipoProva());
				ied.setTurma(turma);
				ied.setUsuarioInscreveu(usuarioInscrveu);
				this.inscricaoExtraOrdinaria.save(ied);

				matriculado = this.matriculaRepository.findByAlunoAndAnoLectivoAndAnulado(aluno, lectivoGuia.get(0),
						false);

				// List<ControloProvaExtraordianaria> despachado =
				// this.despachoRepository.findByNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));

				if (dec.getTipoProva() == TipoProvaExtraOrdinaria.EE || dec.getTipoProva() == TipoProvaExtraOrdinaria.PE
						|| dec.getTipoProva() == TipoProvaExtraOrdinaria.CV) {
					if (matriculado != null) {
						setarHistoricoAluno(aluno, usuarioInscrveu, lectivoGuia, matriculado, turma, disciplina);
					} else {
						List<Matricula> matriculas = this.matriculaRepository
								.findByAnuladoAndAlunoOrderByAnoLectivoDesc(false, aluno);
						// BARRADAS
						if (!matriculas.isEmpty()) {
							setarHistoricoAluno(aluno, usuarioInscrveu, lectivoGuia, matriculas.get(0), turma,
									disciplina);
						}
					}
				}
			}
		}

		String mensagem = "";
		Integer codigo = 0;
		if (inscrito != null) {
			if (!inscrito.isEmpty()) {
				if (disciplinas.size() == 1) {
					mensagem = "Inscrição interrompida, já houve uma inscrição antes.";
					codigo = 3;
				}

				if (disciplinas.size() > 1) {
					mensagem = "Alguma disciplina já foi inscrita.";
				}
			} else {
				mensagem = "Inscrição efetivada com sucesso!";
			}
		} else {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setResultado(matricula);
			c.setMensagem("Verifique os dados.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		// inscricoes
		c.setCodigo(ResponseCode.values()[codigo].getDescricao());
		c.setResultado(matricula);
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<ResponseCliente> InscricaoCCD(AlunoInscricaoExtraordinaria efetuarMatricula) {
		float valorGuia = 0;
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(efetuarMatricula.getNumero().toString());

		List<AnoLectivo> lectivoGuia = this.anoLectivoRepository.findByStatus(true);

		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(efetuarMatricula.getAnoLectivo());

		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		Moeda moeda = this.moedaRepository.findOne(3);

		Usuario usuarioInscrveu = this.usuarioRepository.findOne(
				efetuarMatricula.getUsuarioInscreveu() != null ? efetuarMatricula.getUsuarioInscreveu() : null);
		TipoInscricao tipoInscricao = this.tipoInscricaoRepository.findOne(5);
		// TODO: Validar plano de pagamento
		PlanoPagamento planoPagamento = this.planoPagamentoRepository.findOne(1);

		Emolumento emolumento = null;
		EmolumentoHistorico historicoEmolumento = null;
		InscricaoExtraordinaria ied;

		Turma turma = this.turmaRepository.findOne(55);

		MatriculaCcd matricula = new MatriculaCcd();
		Optional<MatriculaCcd> matriculado = this.matriculaRepositoryCcd.findByAlunoAndAnoLectivoAndAnulado(aluno,
				lectivoGuia.get(0), false);
		if (matriculado.isPresent()) {
			matricula = matriculado.get();
			Long total = this.historicoAlunoCddRepository.countByMatriculaCcd(matricula);

			// RETIRANDO A VALIDAÇÃO DO NÚMERO DE INSCRIÇÕES.
			// if(total == 3)
			// return this.httpResponse.MensagemDeRetorno(2, "Atingiu o número máximo de
			// cursos permitidos !");

		} else {
			matricula.setAnoCurricular(efetuarMatricula.getAnoAtual());
			matricula.setCurso(aluno.getCurso());
			matricula.setAluno(aluno);
			matricula.setAnoLectivo(anoLectivo);
			matricula.setTipoInscricao(tipoInscricao);
			matricula.setData(new Date());
			matricula.setProcessamentoReferencia(false);
			matricula.setNumeroDeAluno(aluno.getNumeroDeAluno());
			matricula.setAnoLectivoMatricula(anoLectivo.getAnoLectivo().toString());
			matricula.setPlanoPagamento(planoPagamento);
			;
			matricula.setTurmaBase(turma);
			matricula.setUsuarioInscreveu(usuarioInscrveu);
			matricula.setDataInscricao(new Date());
			matricula.setPercentagemDesconto(0);
			matricula.setUltimaModificacao(new Date());
			matricula.setVerificado(false);
			matricula.setInscritoOnline(false);
			matricula = matriculaRepositoryCcd.save(matricula);
		}

		// GERAR GUIA AQUI EMCIMA
		Guia guia = new Guia();
		guia.setAcordo(false);
		guia.setAnulada(false);
		guia.setLiquidada(false);
		guia.setGerouCredito(false);
		guia.setGeradaReferencia(false);
		guia.setGeradaOnline(false);
		guia.setAluno(aluno);
		guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
		guia.setAutomaticamente(true);
		guia.setDataEmicao(new Date());
		guia.setLiquidada(false);
		guia.setInstituicao(instituicao);
		guia.setMoeda(moeda);
		guia.setAnoLectivo(lectivoGuia.get(0));
		guia.setValor(valorGuia);
		guia.setDataVencimento(new Date());
		guia.setUsuarioEmitiu(usuarioInscrveu);
		guia.setUltimaModificacao(new Date());
		Guia g = this.guiaRepository.save(guia);
		// gera o numero de guia para o aluno
		String definitivo = "";
		Integer lectivo = anoLectivo.getAnoLectivo();
		NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);
		Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
		// metódo gerar numero de guia chamado
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
		// setar o valor da guia
		g.setNumeroGuia(definitivo);

		// atualizador os dados da ultima guia e a proxima guia
		numeroGerado.setUltimoNumero(proximoNumeroInteiro);
		numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
		this.numeroGeradoRepository.save(numeroGerado);
		g.setUltimaModificacao(new Date());
		// final geração do número da guia
		Guia guiaDisciplina = this.guiaRepository.save(g);
		// FIM-GERAÇÃO DE GUIA.

		for (DisciplinaExtraordinariaCliente dec : efetuarMatricula.getDisciplinas()) {
			// PEGA A DISCIPLIAN
			Disciplina disciplina = this.disciplinaRepository.findOne(dec.getId());

			// TODO - OPTIMIZAÇÃO DA VERBOSIDADE DE QUERYS.
			// PEGA O HISTORICO DE EMOLUMENTO.
			historicoEmolumento = disciplina.getTipo() == TipoDisciplina.ANUAL
					? this.emolumentoHistoricoRepository.findByCursoAndAnoLectivoAndEmolumentoId(aluno.getCurso(),
							lectivoGuia.get(0), 415)
					: this.emolumentoHistoricoRepository.findByCursoAndAnoLectivoAndEmolumentoId(aluno.getCurso(),
							lectivoGuia.get(0), 414);

			System.err.println("VERIFICAR O EMOLUMENTO: " + historicoEmolumento);

			// valorGuia = historicoEmolumento.getValor();

			GuiaPagamentoHistorico guiaHistorico = new GuiaPagamentoHistorico();

			guiaHistorico.setAluno(aluno);
			guiaHistorico.setAnoLectivo(lectivoGuia.get(0));
			guiaHistorico.setEmolumento(historicoEmolumento.getEmolumento());
			guiaHistorico.setGuia(guiaDisciplina);
			// O VALOR DO HISTORICO DA GUIA ESTAVA A SER SABOTADO, ESTAVA A RECEBER ZERO.
			guiaHistorico.setValor(historicoEmolumento.getValor());

			valorGuia += historicoEmolumento.getValor();

			guiaHistorico.setObs("CCD - " + dec.getDescricao());
			this.historicoGuiaPagamentoRepository.save(guiaHistorico);

			List<InscricaoExtraordinaria> inscrito = this.inscricaoExtraOrdinaria
					.findByAnoLectivoAndAlunoAndDisciplinaAndProva(anoLectivo, aluno, disciplina, dec.getTipoProva());
			if (inscrito.isEmpty()) {
				ied = new InscricaoExtraordinaria();
				// this.guiaService.gerarHistoricoAudit(guiaHistorico);
				// GERAR A MATRICULA EXTRAORDINÁRIA.
				ied.setAluno(aluno);
				ied.setAnoLectivo(anoLectivo);
				ied.setDataRegistro(new Date());
				ied.setGuiaPagamento(g);
				ied.setInstituicao(instituicao);
				ied.setDisciplina(disciplina);
				ied.setNomeroDeAluno(aluno.getNumeroDeAluno());
				ied.setTipoProvaExtraOrdinaria(dec.getTipoProva());
				ied.setProva(dec.getTipoProva());
				ied.setTurma(turma);
				ied.setUsuarioInscreveu(usuarioInscrveu);
				this.inscricaoExtraOrdinaria.save(ied);

				// APLICAR HISTORICO CCD.
				setarHistoricoAlunoCCd(usuarioInscrveu, lectivoGuia, matricula, disciplina);
			}
		}
		// RE-APLICA O VALOR DA GUIA DE ACORDO AO VALOR DO EMOLUMENTO.
		guiaDisciplina.setValor(valorGuia);
		this.guiaRepository.save(guiaDisciplina);
		return httpResponse.MensagemDeRetorno(0, "Inscrição efetivada com sucesso!", matricula);
	}

	private void setarHistoricoAluno(Aluno aluno, Usuario usuarioInscrveu, List<AnoLectivo> lectivoGuia,
			Matricula matriculado, Turma turma, Disciplina disciplina) {
		List<HistoricoAluno> inscrito = this.historicoAluno.findByAlunoAndMatriculaAnoLectivoAndDisciplina(aluno,
				lectivoGuia.get(0), disciplina);
		if (inscrito.isEmpty()) {
			HistoricoAluno historico = new HistoricoAluno();
			historico.setAnoCorricular(disciplina.getAnoCorricular());
			historico.setTurma(turma);
			historico.setSituacao(Situacao.CURSANDO);
			historico.setDisciplina(disciplina);
			historico.setDataInscricao(new Date());
			historico.setAluno(aluno);
			historico.setNumeroDeAluno(aluno.getNumeroDeAluno());
			historico.setMatricula(matriculado);
			historico.setAnoLectivo(lectivoGuia.get(0));
			historico.setCurso(aluno.getCurso());
			historico.setUsuarioInscreveu(usuarioInscrveu);
			historico.setSemFrequencia(true);
			historico.setExtraordinaria(true);
			historico.setUltimaModificacao(new Date());
			// CABEÇALHO DE OBRIGATÓRIEDADE
			historico.setAprovado(false);
			// historico.setExtraordinaria(false);
			historico.setPode2epoca(false);
			historico.setPodeMelhoria(false);
			historico.setValidada(false);
			historico.setFechada(false);
			historico.setPodeEpEsp(false);
			this.historicoAluno.save(historico);
			this.historicoAlunoService.gerarHistorico(historico);
		}
	}

	private void setarHistoricoAlunoCCd(Usuario usuarioInscrveu, List<AnoLectivo> lectivoGuia, MatriculaCcd matriculado,
			Disciplina disciplina) {
		Aluno aluno = matriculado.getAluno();
		Turma turma = matriculado.getTurmaBase();
		List<HistoricoAlunoCdd> inscrito = this.historicoAlunoCddRepository
				.findByAlunoAndMatriculaCcdAnoLectivoAndDisciplina(aluno, lectivoGuia.get(0), disciplina);
		if (inscrito.isEmpty()) {
			HistoricoAlunoCdd historico = new HistoricoAlunoCdd();
			historico.setAnoCorricular(disciplina.getAnoCorricular());
			historico.setTurma(turma);
			historico.setSituacao(Situacao.CURSANDO);
			historico.setDisciplina(disciplina);
			historico.setDataInscricao(new Date());
			historico.setAluno(aluno);
			historico.setNumeroDeAluno(aluno.getNumeroDeAluno());
			historico.setMatriculaCcd(matriculado);
			historico.setAnoLectivo(lectivoGuia.get(0));
			historico.setCurso(aluno.getCurso());
			historico.setUsuarioInscreveu(usuarioInscrveu);
			historico.setSemFrequencia(true);
			historico.setExtraordinaria(true);
			historico.setUltimaModificacao(new Date());
			// CABEÇALHO DE OBRIGATÓRIEDADE
			historico.setAprovado(false);
			// historico.setExtraordinaria(false);
			historico.setPode2epoca(false);
			historico.setPodeMelhoria(false);
			historico.setValidada(false);
			historico.setFechada(false);
			historico.setPodeEpEsp(false);
			this.historicoAlunoCddRepository.save(historico);
//			this.historicoAlunoService.gerarHistorico(historico);
		}
	}

	@RequestMapping(value = "/inscricoesPorCurso", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> historicoCreditoEmpresas(@RequestBody BuscaCursoDeVerao credito) {
		// List<ProgCursoDeVeraoMap> inscricoes =
		// this.progCursoDeVeraoMapRepository.findByProvaAndCodigoCursoAndDataInscricaoBetweenAndPagamento(credito.getProva(),credito.getCodigoCurso(),credito.getInicio(),
		// credito.getFim(),credito.getEstadoPagamento());
		// findByProvaAndCodigoCursoAndDataInscricaoBetween
		List<ProgCursoDeVeraoMap> inscricoes = this.progCursoDeVeraoMapRepository
				.findByProvaAndCodigoCursoAndDataInscricaoBetween(credito.getProva(), credito.getCodigoCurso(),
						credito.getInicio(), credito.getFim());
		/*
		 * for (ProgCursoDeVeraoMap o : inscricoes) {
		 * 
		 * }
		 */

		return ObjectoDeRetornoParcial.MensagemDeRetorno(inscricoes, 0, null);
	}

	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}

	@GetMapping("/lista/prova/extraordinaria")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioprovas(@RequestParam String data1, @RequestParam String data2,
			@RequestParam Integer codigoDisciplina, @RequestParam String pagamento, @RequestParam String prova)
			throws Exception {

		Map<String, Object> paramets = new HashMap<>();

		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);

		paramets.put("data1", dataF1);
		paramets.put("data2", dataF2);

		paramets.put("id_disciplina", codigoDisciplina);
		paramets.put("pagamento", pagamento);
		paramets.put("prova", prova);

		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorio/R_Inscritos_Disciplinas_Curso_Verao.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	@GetMapping("/listagem/CCD/{ano}")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> listagemCCD(@PathVariable Integer ano) throws Exception {
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("ano", ano);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Listagem_Inscritos_CCD.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	@GetMapping("/fichaInscricao/CCD/{aluno}")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> fichaInscricaoCdd(@PathVariable Integer aluno) throws Exception {
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_aluno", aluno);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/Ficha_CCD.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<ResponseCliente> InscricaoCCDBack(AlunoInscricaoExtraordinaria efetuarMatricula) {
		float valorGuia = 0;
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(efetuarMatricula.getNumero().toString());

		List<AnoLectivo> lectivoGuia = this.anoLectivoRepository.findByStatus(true);

		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(efetuarMatricula.getAnoLectivo());

		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		Moeda moeda = this.moedaRepository.findOne(3);

		Usuario usuarioInscrveu = this.usuarioRepository.findOne(
				efetuarMatricula.getUsuarioInscreveu() != null ? efetuarMatricula.getUsuarioInscreveu() : null);
		TipoInscricao tipoInscricao = this.tipoInscricaoRepository.findOne(5);
		// TODO: Validar plano de pagamento
		PlanoPagamento planoPagamento = this.planoPagamentoRepository.findOne(1);

		Emolumento emolumento = null;
		EmolumentoHistorico historicoEmolumento = null;
		InscricaoExtraordinaria ied;

		Turma turma = this.turmaRepository.findOne(55);

		for (DisciplinaExtraordinariaCliente soma : efetuarMatricula.getDisciplinas()) {
			if (soma.getTipoProva() == TipoProvaExtraOrdinaria.CCD) {
				Disciplina disciplina = this.disciplinaRepository.findOne(soma.getId());
				emolumento = disciplina.getTipo().getDescricao().equals("Anual")
						? this.emolumentoRepository.findOne(415)
						: this.emolumentoRepository.findOne(414);

				historicoEmolumento = this.emolumentoHistoricoRepository
						.findByEmolumentoAndCursoAndAnoLectivo(emolumento, aluno.getCurso(), lectivoGuia.get(0));

				valorGuia = historicoEmolumento.getValor();
			}
		}

		MatriculaCcd matricula = new MatriculaCcd();
		Optional<MatriculaCcd> matriculado = this.matriculaRepositoryCcd.findByAlunoAndAnoLectivoAndAnulado(aluno,
				lectivoGuia.get(0), false);
		if (matriculado.isPresent()) {
			matricula = matriculado.get();
			Long total = this.historicoAlunoCddRepository.countByMatriculaCcd(matricula);
			if (total == 3)
				return this.httpResponse.MensagemDeRetorno(2, "Atingiu o número máximo de cursos permitidos !");

		} else {
			matricula.setAnoCurricular(efetuarMatricula.getAnoAtual());
			matricula.setCurso(aluno.getCurso());
			matricula.setAluno(aluno);
			matricula.setAnoLectivo(anoLectivo);
			matricula.setTipoInscricao(tipoInscricao);
			matricula.setData(new Date());
			matricula.setProcessamentoReferencia(false);
			matricula.setNumeroDeAluno(aluno.getNumeroDeAluno());
			matricula.setAnoLectivoMatricula(anoLectivo.getAnoLectivo().toString());
			matricula.setPlanoPagamento(planoPagamento);
			;
			matricula.setTurmaBase(turma);
			matricula.setUsuarioInscreveu(usuarioInscrveu);
			matricula.setDataInscricao(new Date());
			matricula.setPercentagemDesconto(0);
			matricula.setUltimaModificacao(new Date());
			matricula.setVerificado(false);
			matricula.setInscritoOnline(false);
			matricula = matriculaRepositoryCcd.save(matricula);
		}

		for (DisciplinaExtraordinariaCliente dec : efetuarMatricula.getDisciplinas()) {
			Disciplina disciplina = this.disciplinaRepository.findOne(dec.getId());
			List<InscricaoExtraordinaria> inscrito = this.inscricaoExtraOrdinaria
					.findByAnoLectivoAndAlunoAndDisciplinaAndProva(anoLectivo, aluno, disciplina, dec.getTipoProva());
			if (inscrito.isEmpty()) {
				Guia guia = new Guia();
				guia.setAcordo(false);
				guia.setAnulada(false);
				guia.setLiquidada(false);
				guia.setGerouCredito(false);
				guia.setGeradaReferencia(false);
				guia.setGeradaOnline(false);
				guia.setAluno(aluno);
				guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
				guia.setAutomaticamente(true);
				guia.setDataEmicao(new Date());
				guia.setLiquidada(false);
				guia.setInstituicao(instituicao);
				guia.setMoeda(moeda);
				guia.setAnoLectivo(lectivoGuia.get(0));
				guia.setValor(valorGuia);
				guia.setDataVencimento(new Date());
				guia.setUsuarioEmitiu(usuarioInscrveu);
				guia.setUltimaModificacao(new Date());
				Guia g = this.guiaRepository.save(guia);
				// gera o numero de guia para o aluno
				String definitivo = "";
				Integer lectivo = anoLectivo.getAnoLectivo();
				NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);
				Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
				// metódo gerar numero de guia chamado
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
				// setar o valor da guia
				g.setNumeroGuia(definitivo);

				// atualizador os dados da ultima guia e a proxima guia
				numeroGerado.setUltimoNumero(proximoNumeroInteiro);
				numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
				this.numeroGeradoRepository.save(numeroGerado);
				g.setUltimaModificacao(new Date());
				// final geração do número da guia
				Guia guiaDisciplina = this.guiaRepository.save(g);

				ied = new InscricaoExtraordinaria();

				GuiaPagamentoHistorico guiaHistorico = new GuiaPagamentoHistorico();

				guiaHistorico.setAluno(aluno);
				guiaHistorico.setAnoLectivo(lectivoGuia.get(0));
				guiaHistorico.setEmolumento(emolumento);
				guiaHistorico.setGuia(guiaDisciplina);
				guiaHistorico.setValor(valorGuia);

				guiaHistorico.setObs("CCD " + dec.getDescricao());
				this.historicoGuiaPagamentoRepository.save(guiaHistorico);
				this.guiaService.gerarHistoricoAudit(guiaHistorico);

				// final geração do numero de guia
				ied.setAluno(aluno);
				ied.setAnoLectivo(anoLectivo);
				ied.setDataRegistro(new Date());
				ied.setGuiaPagamento(g);
				ied.setInstituicao(instituicao);
				ied.setDisciplina(disciplina);
				ied.setNomeroDeAluno(aluno.getNumeroDeAluno());
				ied.setTipoProvaExtraOrdinaria(dec.getTipoProva());
				ied.setProva(dec.getTipoProva());
				ied.setTurma(turma);
				ied.setUsuarioInscreveu(usuarioInscrveu);
				this.inscricaoExtraOrdinaria.save(ied);

				// Guardar Matrícula
				setarHistoricoAlunoCCd(usuarioInscrveu, lectivoGuia, matricula, disciplina);
			}
		}
		return httpResponse.MensagemDeRetorno(0, "Inscrição efetivada com sucesso!", matricula);
	}
}