package ao.co.intellectus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mashape.unirest.http.exceptions.UnirestException;

import ao.co.intellectus.DTO.AlunoResumoSituacao;
import ao.co.intellectus.DTO.AproveitametoCliente;
import ao.co.intellectus.DTO.DisciplinaAproveitamentoCliente;
import ao.co.intellectus.DTO.DisciplinaConcluidaCliente;
import ao.co.intellectus.DTO.DisciplinaCursandoCliente;
import ao.co.intellectus.DTO.DisciplinaExtraordinariaCliente;
import ao.co.intellectus.DTO.EmolumentoCliente;
import ao.co.intellectus.DTO.GuiaLiquidadaCliente;
import ao.co.intellectus.DTO.InscricaoPorAno;
import ao.co.intellectus.DTO.ReferenciaRetornoDTO;
import ao.co.intellectus.DTO.SituacaoAcademicaCliente;
import ao.co.intellectus.DTO.TodasGuiasCliente;
import ao.co.intellectus.DTO.proxpay.Filds;
import ao.co.intellectus.DTO.proxpay.Unidade;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAnexo;
import ao.co.intellectus.model.CompensassaoProvisoria;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaEmAbertoCliente;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.RegistroDocumentos;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoAnexoRepository;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.CompensassaoProvisoriaRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.PermissaoCursoRepository;
import ao.co.intellectus.repository.RegistroDocumentoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.cafold.AlunoService;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.cafold.DisciplinaService;
import ao.co.intellectus.servico.cafold.EmailService;
import ao.co.intellectus.servico.cafold.EmolumentoService;
import ao.co.intellectus.servico.cafold.GuiaEmAbertoService;
import ao.co.intellectus.servico.cafold.GuiaLiquidadaService;
import ao.co.intellectus.servico.cafold.GuiaService;
import ao.co.intellectus.servico.cafold.InscricaoService;
import ao.co.intellectus.servico.cafold.MatriculasService;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.cafold.PermissaoService;
import ao.co.intellectus.servico.cafold.TodasGuiasService;
import ao.co.intellectus.servico.proxypay.ReferenciasService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
@RequestMapping("/situacao")
public class ControllerSituacaoAcademica {
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private HistoricoAlunoRepository historicoAlunoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private AlunoAnexoRepository alunoAnexoRepository;
	@Autowired
	private RegistroDocumentoRepository registroDocumentoRepository;
	@Autowired
	private EquivalenciaHistoricoRepository equivalenciaHistoricoRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaRepository;
	@Autowired
	private CompensassaoProvisoriaRepository compensassaoRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private PermissaoCursoRepository permissaoCursoRepository;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private MatriculasService matriculasService;
	@Autowired
	private InscricaoService inscricaoService;
	@Autowired
	private DisciplinaService disciplinaService;
	@Autowired
	private PermissaoService permissaoService;
	@Autowired
	private GuiaLiquidadaService guiaLiquidadaService;
	@Autowired
	private GuiaEmAbertoService guiaEmAbertoService;
	@Autowired
	private TodasGuiasService todasGuiaService;
	@Autowired
	private EmolumentoService emolumentoService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private GuiaService guiaService;
	@Autowired
	private ReferenciasService referenciasService;
	private String assunto, mensagem;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Value("${base.url}")
    private String baseUrl;

	/*
	 * DESENVOLVEDOR: ERNESTO TADEU TCHITECULO SAMBONGO DATA: 06-12-2018
	 * 
	 * 
	 */
	// situacao/buscarGuiaPorCodigo/
	@RequestMapping(value = "/buscarGuiaPorCodigo/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCodigo(@PathVariable String numero) {
		ResponseCliente c = new ResponseCliente();

		List<CompensassaoProvisoria> compensassao = this.compensassaoRepository.findByNumeroGuia(numero);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(compensassao);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// situacao/gerar-referencia/{numeroGuia}/{numero}
	
	/*@SuppressWarnings("unused")
	@RequestMapping(value = "/gerar-referencia/{numeroGuia}/{numeroTelefone}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos(@PathVariable Integer numeroGuia,
			@PathVariable String numeroTelefone) throws UnirestException {
		ResponseCliente c = new ResponseCliente();

		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		Guia guia = this.guiaRepository.findOne(numeroGuia);
		String mensagem = "";
		int codigo = 0;

		// 121956
		Date dataVencimento = guia.getDataVencimento();
		// PEGA DATA AGORA.
		Date hojeAtual = new Date();

		List<GuiaPagamentoHistorico> historico = this.historicoGuiaRepository.findByGuia(guia);
		for (GuiaPagamentoHistorico o : historico) {
			if ((o.getEmolumento().getId() >= 52 && o.getEmolumento().getId() <= 67)
					&& hojeAtual.getTime() >= dataVencimento.getTime()) {
				c.setMensagem("Mensalidade vencida deve ser tratada nas finanças.");
				c.setCodigo(300);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		}

		if (guia != null) {
			if (guia.isLiquidada()) {
				c.setMensagem("Está guia já foi liquidada.");
				c.setCodigo(300);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			List<CompensassaoProvisoria> guiaExiste = this.compensassaoRepository
					.findByNumeroGuia(guia.getNumeroGuia());

			boolean excluida = false;

			if (!guiaExiste.isEmpty()) {
				for (CompensassaoProvisoria pr : guiaExiste) {
					CompensassaoProvisoria compesassao = this.compensassaoRepository.findOne(pr.getId());
					this.compensassaoRepository.delete(compesassao);

					excluida = true;
				}
			}

			if (guiaExiste.isEmpty() || excluida) {
				if (guia.getAluno().isContencioso()) {
					return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,
							"Infelizmente não pode gerar referência porque está no contencioso.");
				} else {

					// COMEÇO DO METÓDO
					List<GuiaPagamentoHistorico> guiaHist = this.historicoGuiaRepository.findByGuia(guia);
					Unidade unidade = new Unidade();
					// BUSCA DATA VENCIMENTO DA GUIA.
					Date data = guia.getDataVencimento();

					// PEGA DATA AGORA.
					Date hoje = new Date();

					// VERIFICA SE A DATA HOJE FOR MAIOR QUE DATA VENCIMENTO É REDEFINIDA
					if (hoje.getTime() >= data.getTime()) {

						data = hoje;
						mensagem = "Refêrencia enviada com sucesso.";
					} else {
						data = guia.getDataVencimento();

						mensagem = "Refêrencia enviada com sucesso.";
					}

					codigo = 200;
					Locale local = new Locale("pt", "BR");
					DateFormat formato = new SimpleDateFormat("yyyy-MM-dd", local);

					String vencimento = formato.format(data);

					unidade.setExpiry_date(vencimento);
					unidade.setAmount(guia.getValor());

					// ------------------
					Filds custom_fields = new Filds();

					if (guiaHist.get(0).getObs() != null) {
						custom_fields.setDescription(
								guiaHist.get(0).getEmolumento().getEmolumento() + " - " + guiaHist.get(0).getObs());
					} else {
						custom_fields.setDescription(guiaHist.get(0).getEmolumento().getEmolumento());
					}

					// AQUI
					String telefone = numeroTelefone == null ? guia.getAluno().getTelefone().replace(" ", "") : numeroTelefone;

					custom_fields.setMobile(telefone);
					custom_fields.setEmail(guia.getAluno().getEmail());
					custom_fields.setName(guia.getAluno().getNome());
					custom_fields.setGuia(guia.getNumeroGuia());
					// novos campos
					custom_fields.setNumeroDeAluno(guia.getAluno().getNumeroDeAluno());
					custom_fields.setUnidade("0010");
					custom_fields.setInst_description("Intellectus - " + instituicao.getSigla());

					unidade.setCustom_fields(custom_fields);

					Gson gson = new Gson();
					String json = gson.toJson(unidade);

					String payload = "{\"reference\":" + json + "}";
					// mandar para proxypay
					HttpResponse<JsonNode> asJson = Unirest.post("https://api.proxypay.co.ao/references")
							.basicAuth("api", "un5kodf4euv5s13b6i22qf0bkks1mosn")
							.header("accept", "application/vnd.proxypay.v1+json")
							.header("Content-Type", "application/json").body(payload).asJson();
					//un5kodf4euv5s13b6i22qf0bkks1mosn
					CompensassaoProvisoria compensassao = new CompensassaoProvisoria();

					// Guia guia = this.guiaRepository.findByNumeroGuia(numeroGuia);

					// this.compensassaoRepository.findByNumeroGuia(numeroGuia);

					compensassao.setNumeroGuia(guia.getNumeroGuia());
					compensassao.setDataCriacao(new Date());
					compensassao.setDataVencimento(guia.getDataVencimento());
					compensassao.setDescricao(guiaHist.get(0).getEmolumento().getEmolumento());
					compensassao.setNome(guia.getAluno().getNome());
					compensassao.setNumero(Integer.parseInt(guia.getAluno().getNumeroDeAluno()));
					compensassao.setTelefone(numeroTelefone);
					compensassao.setValor(guia.getValor());
					compensassao.setReferencia(this.referenciasService.numero(asJson));
					// providi
					this.compensassaoRepository.save(compensassao);
					// FINAL DO METÓDO

					// ATUALIZA O Número de aluno
					Aluno aluno = this.alunoRepository.findByNumeroDeAluno(guia.getNumeroDeAluno());
					if (numeroTelefone != null || aluno.getTelefone() != numeroTelefone) {
						aluno.setTelefone(numeroTelefone);
						aluno.setUltimaModificacao(new Date());
						this.alunoRepository.save(aluno);
						this.alunoService.gerarHistorico(aluno);
					}
				}

			} else {

				mensagem = "A referência já havia sido enviada.";
				codigo = 300;
			}
			// mensagem="Referencia criada e enviada com sucesso.";
		} else {
			mensagem = "Guia não existe.";
			codigo = 300;
		}

		c.setMensagem(mensagem);
		c.setCodigo(codigo);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}*/

	@GetMapping("/buscaFoto")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> buscar(@RequestParam Integer id) throws Exception {
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(String.valueOf(id));
		AlunoAnexo fotoAluno = this.alunoAnexoRepository.findByAluno(aluno);

		byte[] relatrio = fotoAluno.getFoto();
		return new ResponseEntity<byte[]>(relatrio, HttpStatus.OK);
	}

	/*
	 * DESENVOLVEDOR: Ernesto Tadeu T. Sambongo ATUALIZAÇÃO: 19-06-2018
	 * 
	 */
	// situacao/situacaoAcademicaAluno/{numero}
	@RequestMapping(value = "/situacaoAcademicaAluno/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscar(@PathVariable String numero, @PathVariable String userName) {

		// AlunoAnexo
		ResponseCliente c = new ResponseCliente();
		// try {
		Aluno aluno = numero != null ? this.alunoRepository.findByNumeroDeAluno(numero) : null;
		if (aluno == null) {
			c.setMensagem("Nenhum aluno encontrado!");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		PermissaoCurso up = this.permissaoCursoRepository.findByCursoAndUsuarioUserName(aluno.getCurso(), userName);

		List<Matricula> matriculas = this.matriculaRepository.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());

		if (matriculas.isEmpty()) {
			c.setMensagem("Nenhuma matricula encontrada para este aluno!");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		SituacaoAcademicaCliente situacao = new SituacaoAcademicaCliente();

		AlunoAnexo fotoAluno = this.alunoAnexoRepository.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());

		// DADOS DO ALUNO
		AlunoResumoSituacao sAluno = new AlunoResumoSituacao();

		sAluno.setNome(aluno.getNome());
		sAluno.setCurso(aluno.getCurso().getPlano());
		sAluno.setCodigoCurso(aluno.getCurso().getId());
		sAluno.setNumero(Integer.parseInt(aluno.getNumeroDeAluno()));
		sAluno.setEmail(aluno.getEmail());
		sAluno.setTelefone(aluno.getTelefone());
		sAluno.setContencioso(aluno.isContencioso());
		sAluno.setAnulado(aluno.isInactivo());
		sAluno.setFimCurso(aluno.isFimCurso());

		if (fotoAluno != null)
			sAluno.setFoto(fotoAluno.getFoto());
		sAluno.setGrau(aluno.getCurso().getGrau().getDescricao());

		situacao.setAluno(sAluno);
		situacao.setObservacao(aluno.getObservacao());
		// DADOS DAS MATRICULAS FEITAS.
		InscricaoPorAno inscricao;
		List<InscricaoPorAno> inscricoes = new ArrayList<InscricaoPorAno>();

		// TODAS AS MATRICULAS FEITAS.
		for (Matricula matricula : matriculas) {
			inscricao = new InscricaoPorAno();

			inscricao.setId(matricula.getId());

			inscricao.setAnoCurricular(matricula.getAnoCurricular());
			inscricao.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
			inscricao.setAnoLectivoDescricao(matricula.getAnoLectivo().getAnoLectivoDescricao());
			inscricao.setCurso(matricula.getCurso().getPlano());
			inscricao.setDataInscricao(matricula.getData());
			inscricao.setDesconto(matricula.getPercentagemDesconto());

			if (matricula.getTurmaBase() != null)
				inscricao.setTurma(matricula.getTurmaBase().getTurma());

			if (matricula.getUsuarioInscreveu() != null)
				inscricao.setUsuario(matricula.getUsuarioInscreveu().getUserName());

			if (matricula.getPlanoPagamento() != null)
				inscricao.setPlanoPagamento(matricula.getPlanoPagamento().getDescricao());
			if (matricula.getTipoInscricao() != null)
				inscricao.setTipoInscricao(matricula.getTipoInscricao().getDescricao());

			inscricao.setSigla(matricula.getCurso().getSigla());
			inscricao.setAnulado(matricula.isAnulado());
			inscricao.setDataAnulamento(matricula.getDataAnulamento());
			inscricoes.add(inscricao);
		}
		situacao.setInscricaoPorAno(inscricoes);

		// INICIAR OS DADOS DE HISTORICO
		List<DisciplinaCursandoCliente> disciplinasHistorico = new ArrayList<DisciplinaCursandoCliente>();
		DisciplinaCursandoCliente disciplinaHistorico;

		// HISTORICO DE INSCRIÇÕES POR ANO LECTIVO
		List<HistoricoAluno> historicoAluno = this.historicoAlunoRepository
				.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());

		for (HistoricoAluno haluno : historicoAluno) {
			disciplinaHistorico = new DisciplinaCursandoCliente();

			disciplinaHistorico.setIdMatricula(haluno.getMatricula().getId());
			disciplinaHistorico.setDisciplina(haluno.getDisciplina().getDescricao());
			disciplinaHistorico.setAnoLectivo(haluno.getAnoLectivo().getAnoLectivo());
			disciplinaHistorico.setAnoLectivoDescricao(haluno.getAnoLectivo().getAnoLectivoDescricao());
			disciplinaHistorico.setAnoCurricular(haluno.getDisciplina().getAnoCorricular());
			disciplinaHistorico.setTipo(haluno.getDisciplina().getTipo());
			disciplinaHistorico.setTurma(haluno.getTurma().getTurma());
			disciplinaHistorico.setValidado(haluno.isValidada());
			// disciplinaHistorico.setValidado(haluno.is);
			// NOTAS...
			disciplinaHistorico.setFinalFrequencia(haluno.getNotaFinalContinua());
			disciplinaHistorico.setNotaFinal(haluno.getNotaFinal());
			disciplinaHistorico.setMelhoriaNota(haluno.getMelhoriaNota());
			// ÉPOCA ESPECIAL
			disciplinaHistorico.setNotaEpocaEspecial(haluno.getNotaEpocaEspecial());
			disciplinaHistorico.setNotaEpocaEspecialOral(haluno.getNotaEpocaEspecialOral());
			// EXAME
			disciplinaHistorico.setNotaExame(haluno.getNotaExame());
			disciplinaHistorico.setNotaExameOral(haluno.getNotaExameOral());
			// RECURSO
			disciplinaHistorico.setNotaRecurso(haluno.getNotaRecurso());
			disciplinaHistorico.setNotaRecursoOral(haluno.getNotaRecursoOral());

			// disciplinaHistorico.set
			disciplinasHistorico.add(disciplinaHistorico);
		}
		situacao.setDisciplinasInscritas(disciplinasHistorico);

		// GUIAS LIQUIDADAS
		GuiaLiquidadaCliente guiaLiquidada = new GuiaLiquidadaCliente();
		List<GuiaLiquidadaCliente> guiaLiquidadas = new ArrayList<GuiaLiquidadaCliente>();

		guiaLiquidadas.add(guiaLiquidada);
		situacao.setGuiasLiquidadas(guiaLiquidadas);
		// GUIAS LIQUIDADAS
		GuiaEmAbertoCliente guiaAberta = new GuiaEmAbertoCliente();
		List<GuiaEmAbertoCliente> guiaAbertas = new ArrayList<GuiaEmAbertoCliente>();
		guiaAbertas.add(guiaAberta);
		situacao.setGuiasAbertas(guiaAbertas);

		// DISCIPLINAS CONCLUIDAS NORMAIS
		DisciplinaConcluidaCliente disciplinasConcluida;
		List<DisciplinaConcluidaCliente> disciplinasConcluidas = new ArrayList<DisciplinaConcluidaCliente>();
		// List<HistoricoAluno> aprovadas =
		// this.historicoAlunoRepository.findByAprovadoAndAlunoNumeroDeAluno(true,
		// aluno.getNumeroDeAluno());

		List<Disciplina> disciplinasCurso = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);

		List<HistoricoAluno> aprovadas = this.historicoAlunoRepository
				.findByAlunoAndCursoAndAprovadoAndDisciplinaStatus(aluno, aluno.getCurso(), true, true);

		// AQUI TRATAMOS AS DISCIPLINAS QUE FORAM CONCLUIDAS... TANTO A EQUIVALENCIA
		// QUANTO A FREQUENCIA NORMAL
		Map<Integer, HistoricoAluno> mapaA = new HashMap<Integer, HistoricoAluno>();
		Map<Integer, HistoricoAluno> mapaAB = new HashMap<Integer, HistoricoAluno>();

		for (HistoricoAluno o : aprovadas) {
			if (!mapaA.containsKey(o.getDisciplina().getId())) {
				mapaA.put(o.getDisciplina().getId(), o);
				mapaAB.put(o.getDisciplina().getId(), o);
			}
		}

		// VER DADOS DO HISTORICO
		// HistoricoAluno mapaHa;
		for (HistoricoAluno o : aprovadas) {
			disciplinasConcluida = new DisciplinaConcluidaCliente();

			boolean containsKey = mapaA.containsKey(o.getDisciplina().getId());
			if (containsKey) {
				disciplinasConcluida.setAnoCurricular(o.getDisciplina().getAnoCorricular());
				disciplinasConcluida.setAnoLectivo(o.getAnoLectivo().getAnoLectivo().toString());
				disciplinasConcluida.setAnoLectivoDescricao(o.getAnoLectivo().getAnoLectivoDescricao());
				disciplinasConcluida.setDisciplina(o.getDisciplina().getDescricao());

				disciplinasConcluida.setNota(o.getNotaFinal());

				disciplinasConcluida.setSigla(o.getDisciplina().getSigla());
				disciplinasConcluida.setTipo(o.getDisciplina().getTipo());
				disciplinasConcluida.setTotalCurso(disciplinasCurso.size());

				// ATRIBUIR O VALOR AO ARRAY DE DISCIPLINAS
				disciplinasConcluidas.add(disciplinasConcluida);

				mapaA.remove(o.getDisciplina().getId());
			}
		}

		Map<Integer, HistoricoEquivalencia> mapaB = new HashMap<Integer, HistoricoEquivalencia>();

		// VER DADOS DE EQUIVALENCIA
		// BUSCA O HISTORICO DE EQUIVALENCIAS
		List<HistoricoEquivalencia> equivalencia = this.equivalenciaHistoricoRepository
				.findByAlunoNumeroDeAlunoAndEstadoAndDisciplinaDestinoCurso(aluno.getNumeroDeAluno(), true,
						aluno.getCurso());
		for (HistoricoEquivalencia o : equivalencia) {
			if (!mapaA.containsKey(o.getDisciplinaDestino().getId())) {
				mapaB.put(o.getDisciplinaDestino().getId(), o);
			}
		}
		for (HistoricoEquivalencia o : equivalencia) {
			disciplinasConcluida = new DisciplinaConcluidaCliente();
			// VERIFICA SE EXISTE NO MAPA A

			// mapaA.get(o.getDisciplinaDestino().getId());

			boolean containsKey = mapaAB.containsKey(o.getDisciplinaDestino().getId());

			if (!containsKey) {
				boolean containsKeyB = mapaB.containsKey(o.getDisciplinaDestino().getId());

				if (containsKeyB) {
					disciplinasConcluida.setAnoCurricular(o.getDisciplinaDestino().getAnoCorricular());
					disciplinasConcluida.setAnoLectivo(o.getEquivalencia().getAnoLectivo().getAnoLectivo().toString());
					disciplinasConcluida.setAnoLectivo(o.getEquivalencia().getAnoLectivo().getAnoLectivoDescricao());
					disciplinasConcluida.setDisciplina(o.getDisciplinaDestino().getDescricao());
					disciplinasConcluida.setNota(o.getNotaOrigem());
					disciplinasConcluida.setSigla(o.getDisciplinaDestino().getSigla());
					disciplinasConcluida.setTipo(o.getDisciplinaDestino().getTipo());
					disciplinasConcluida.setEquivalencia(true);

					// ATRIBUIR O VALOR AO ARRAY DE DISCIPLINAS
					disciplinasConcluidas.add(disciplinasConcluida);
					mapaB.remove(o.getDisciplinaDestino().getId());
				}
			}
		}

		situacao.setDisciplinasConcluidas(disciplinasConcluidas);

		// DISCIPLINAS CONCLUIDAS
		DisciplinaCursandoCliente disciplinaCursando = new DisciplinaCursandoCliente();
		List<DisciplinaCursandoCliente> disciplinasCursando = new ArrayList<DisciplinaCursandoCliente>();

		disciplinasCursando.add(disciplinaCursando);
		// GUIAS EM ABERTA
		List<Guia> guias = this.guiaRepository.findByAlunoNumeroDeAlunoAndLiquidadaAndAnulada(aluno.getNumeroDeAluno(),
				false, false);
		List<GuiaEmAbertoCliente> guiasNaoLiquidadas = new ArrayList<GuiaEmAbertoCliente>();
		GuiaEmAbertoCliente guiaNaoLiquidada;
		for (Guia guia : guias) {

			guiaNaoLiquidada = new GuiaEmAbertoCliente();

			guiaNaoLiquidada.setId(guia.getId());
			guiaNaoLiquidada.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			guiaNaoLiquidada.setAnoLectivoDescricao(guia.getAnoLectivo().getAnoLectivoDescricao());
			guiaNaoLiquidada.setDataEmissao(guia.getDataEmicao());
			guiaNaoLiquidada.setDataVencimento(guia.getDataVencimento());
			// guiaNaoLiquidada.setMoeda(guia.getMoeda().getDesignacao());
			guiaNaoLiquidada.setNumeroGuia(guia.getNumeroGuia());
			guiaNaoLiquidada.setValor(guia.getValor());

			guiasNaoLiquidadas.add(guiaNaoLiquidada);
		}
		situacao.setGuiasAbertas(guiasNaoLiquidadas);

		List<Guia> recidos = this.guiaRepository
				.findByAlunoNumeroDeAlunoAndLiquidadaAndAnulada(aluno.getNumeroDeAluno(), true, false);
		List<GuiaLiquidadaCliente> liquidadas = new ArrayList<GuiaLiquidadaCliente>();
		GuiaLiquidadaCliente liquidada;
		for (Guia guia : recidos) {
			liquidada = new GuiaLiquidadaCliente();

			liquidada.setId(guia.getId());
			liquidada.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			liquidada.setAnoLectivoDescricao(guia.getAnoLectivo().getAnoLectivoDescricao());
			liquidada.setDataEmissao(guia.getDataEmicao());
			liquidada.setDataLiquidada(guia.getDataLiquidacao());
			liquidada.setNumeroGuia(guia.getNumeroGuia());
			liquidada.setValor(guia.getValor());

			liquidadas.add(liquidada);
		}
		situacao.setGuiasLiquidadas(liquidadas);

		List<Disciplina> disciplina = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);

		int dCurso = disciplina.size();
		situacao.setDisciplinasCurso(dCurso);
		if (up != null) {
			c.setPermissao(up.getPermissao());
		}
		c.setResultado(situacao);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		/*
		 * } catch (Exception e) { c.setCodigo(ResponseCode.values()[1].getDescricao());
		 * c.setMensagem(e.getLocalizedMessage()); return new
		 * ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
		 */
	}

	/*
	 * DESENVOLVEDOR: Ernesto Tadeu T. Sambongo ATUALIZAÇÃO: 19-06-2018
	 * 
	 */
	// situacao/situacaoAcademicaAluno/{numero}
	@RequestMapping(value = "/situacaoAcademicaAlunoColegio/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarColegio(@PathVariable String numero, @PathVariable String userName) {

		//AlunoAnexo
		ResponseCliente c = new ResponseCliente();

		Aluno aluno = numero != null ? this.alunoRepository.findByNumeroDeAluno(numero) : null;
		if (aluno == null) {
			c.setMensagem("Nenhum aluno encontrado!");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		PermissaoCurso up = this.permissaoCursoRepository.findByCursoAndUsuarioUserName(aluno.getCurso(), userName);

		List<Matricula> matriculas = this.matriculaRepository.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());

		if (matriculas.isEmpty()) {
			c.setMensagem("Nenhuma matricula encontrada para este aluno!");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		SituacaoAcademicaCliente situacao = new SituacaoAcademicaCliente();

		AlunoAnexo fotoAluno = this.alunoAnexoRepository.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());

		// DADOS DO ALUNO
		AlunoResumoSituacao sAluno = new AlunoResumoSituacao();

		sAluno.setNome(aluno.getNome());
		sAluno.setCurso(aluno.getCurso().getPlano());
		sAluno.setCodigoCurso(aluno.getCurso().getId());
		sAluno.setNumero(Integer.parseInt(aluno.getNumeroDeAluno()));
		sAluno.setEmail(aluno.getEmail());
		sAluno.setTelefone(aluno.getTelefone());
		sAluno.setContencioso(aluno.isContencioso());
		sAluno.setAnulado(aluno.isInactivo());
		sAluno.setFimCurso(aluno.isFimCurso());
		sAluno.setFoto(fotoAluno.getFoto());
		sAluno.setGrau(aluno.getCurso().getGrau().getDescricao());

		situacao.setAluno(sAluno);
		situacao.setObservacao(aluno.getObservacao());
		// DADOS DAS MATRICULAS FEITAS.
		InscricaoPorAno inscricao;
		List<InscricaoPorAno> inscricoes = new ArrayList<InscricaoPorAno>();

		// TODAS AS MATRICULAS FEITAS.
		for (Matricula matricula : matriculas) {
			inscricao = new InscricaoPorAno();

			inscricao.setId(matricula.getId());

			inscricao.setAnoCurricular(matricula.getAnoCurricular());
			inscricao.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
			inscricao.setAnoLectivoDescricao(matricula.getAnoLectivo().getAnoLectivoDescricao());
			inscricao.setCurso(matricula.getCurso().getPlano());
			inscricao.setDataInscricao(matricula.getData());
			inscricao.setDesconto(matricula.getPercentagemDesconto());

			if (matricula.getTurmaBase() != null)
				inscricao.setTurma(matricula.getTurmaBase().getTurma());

			if (matricula.getUsuarioInscreveu() != null)
				inscricao.setUsuario(matricula.getUsuarioInscreveu().getUserName());

			if (matricula.getPlanoPagamento() != null)
				inscricao.setPlanoPagamento(matricula.getPlanoPagamento().getDescricao());
			if (matricula.getTipoInscricao() != null)
				inscricao.setTipoInscricao(matricula.getTipoInscricao().getDescricao());

			inscricao.setSigla(matricula.getCurso().getSigla());
			inscricao.setAnulado(matricula.isAnulado());
			inscricao.setDataAnulamento(matricula.getDataAnulamento());
			inscricoes.add(inscricao);
		}
		situacao.setInscricaoPorAno(inscricoes);

		// INICIAR OS DADOS DE HISTORICO
		List<DisciplinaCursandoCliente> disciplinasHistorico = new ArrayList<DisciplinaCursandoCliente>();
		DisciplinaCursandoCliente disciplinaHistorico;

		// HISTORICO DE INSCRIÇÕES POR ANO LECTIVO
		List<HistoricoAluno> historicoAluno = this.historicoAlunoRepository
				.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());

		for (HistoricoAluno haluno : historicoAluno) {
			disciplinaHistorico = new DisciplinaCursandoCliente();

			disciplinaHistorico.setIdMatricula(haluno.getMatricula().getId());
			disciplinaHistorico.setDisciplina(haluno.getDisciplina().getDescricao());
			disciplinaHistorico.setAnoLectivo(haluno.getAnoLectivo().getAnoLectivo());
			disciplinaHistorico.setAnoCurricular(haluno.getDisciplina().getAnoCorricular());
			disciplinaHistorico.setTipo(haluno.getDisciplina().getTipo());
			disciplinaHistorico.setTurma(haluno.getTurma().getTurma());
			disciplinaHistorico.setValidado(haluno.isValidada());
			// disciplinaHistorico.setValidado(haluno.is);
			// NOTAS...
			disciplinaHistorico.setFinalFrequencia(haluno.getNotaFinalContinua());
			disciplinaHistorico.setNotaFinal(haluno.getNotaFinal());
			disciplinaHistorico.setMelhoriaNota(haluno.getMelhoriaNota());
			// ÉPOCA ESPECIAL
			disciplinaHistorico.setNotaEpocaEspecial(haluno.getNotaEpocaEspecial());
			disciplinaHistorico.setNotaEpocaEspecialOral(haluno.getNotaEpocaEspecialOral());
			// EXAME
			disciplinaHistorico.setNotaExame(haluno.getNotaExame());
			disciplinaHistorico.setNotaExameOral(haluno.getNotaExameOral());
			// RECURSO
			disciplinaHistorico.setNotaRecurso(haluno.getNotaRecurso());
			disciplinaHistorico.setNotaRecursoOral(haluno.getNotaRecursoOral());

			// disciplinaHistorico.set
			disciplinasHistorico.add(disciplinaHistorico);
		}
		situacao.setDisciplinasInscritas(disciplinasHistorico);

		// GUIAS LIQUIDADAS
		GuiaLiquidadaCliente guiaLiquidada = new GuiaLiquidadaCliente();
		List<GuiaLiquidadaCliente> guiaLiquidadas = new ArrayList<GuiaLiquidadaCliente>();

		guiaLiquidadas.add(guiaLiquidada);
		situacao.setGuiasLiquidadas(guiaLiquidadas);
		// GUIAS LIQUIDADAS
		GuiaEmAbertoCliente guiaAberta = new GuiaEmAbertoCliente();
		List<GuiaEmAbertoCliente> guiaAbertas = new ArrayList<GuiaEmAbertoCliente>();
		guiaAbertas.add(guiaAberta);
		situacao.setGuiasAbertas(guiaAbertas);

		// DISCIPLINAS CONCLUIDAS NORMAIS
		DisciplinaConcluidaCliente disciplinasConcluida;
		List<DisciplinaConcluidaCliente> disciplinasConcluidas = new ArrayList<DisciplinaConcluidaCliente>();
		// List<HistoricoAluno> aprovadas =
		// this.historicoAlunoRepository.findByAprovadoAndAlunoNumeroDeAluno(true,
		// aluno.getNumeroDeAluno());

		List<Disciplina> disciplinasCurso = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);

		List<HistoricoAluno> aprovadas = this.historicoAlunoRepository
				.findByAlunoAndCursoAndAprovadoAndDisciplinaStatus(aluno, aluno.getCurso(), true, true);

		// AQUI TRATAMOS AS DISCIPLINAS QUE FORAM CONCLUIDAS... TANTO A EQUIVALENCIA
		// QUANTO A FREQUENCIA NORMAL
		Map<Integer, HistoricoAluno> mapaA = new HashMap<Integer, HistoricoAluno>();
		Map<Integer, HistoricoAluno> mapaAB = new HashMap<Integer, HistoricoAluno>();

		for (HistoricoAluno o : aprovadas) {
			if (!mapaA.containsKey(o.getDisciplina().getId())) {
				mapaA.put(o.getDisciplina().getId(), o);
				mapaAB.put(o.getDisciplina().getId(), o);
			}
		}

		// VER DADOS DO HISTORICO
		// HistoricoAluno mapaHa;
		for (HistoricoAluno o : aprovadas) {
			disciplinasConcluida = new DisciplinaConcluidaCliente();

			boolean containsKey = mapaA.containsKey(o.getDisciplina().getId());
			if (containsKey) {
				disciplinasConcluida.setAnoCurricular(o.getDisciplina().getAnoCorricular());
				disciplinasConcluida.setAnoLectivo(o.getAnoLectivo().getAnoLectivo().toString());
				disciplinasConcluida.setDisciplina(o.getDisciplina().getDescricao());

				disciplinasConcluida.setNota(o.getNotaFinal());

				disciplinasConcluida.setSigla(o.getDisciplina().getSigla());
				disciplinasConcluida.setTipo(o.getDisciplina().getTipo());
				disciplinasConcluida.setTotalCurso(disciplinasCurso.size());

				// ATRIBUIR O VALOR AO ARRAY DE DISCIPLINAS
				disciplinasConcluidas.add(disciplinasConcluida);

				mapaA.remove(o.getDisciplina().getId());
			}
		}

		Map<Integer, HistoricoEquivalencia> mapaB = new HashMap<Integer, HistoricoEquivalencia>();

		// VER DADOS DE EQUIVALENCIA
		// BUSCA O HISTORICO DE EQUIVALENCIAS
		List<HistoricoEquivalencia> equivalencia = this.equivalenciaHistoricoRepository
				.findByAlunoNumeroDeAlunoAndEstadoAndDisciplinaDestinoCurso(aluno.getNumeroDeAluno(), true,
						aluno.getCurso());
		for (HistoricoEquivalencia o : equivalencia) {
			if (!mapaA.containsKey(o.getDisciplinaDestino().getId())) {
				mapaB.put(o.getDisciplinaDestino().getId(), o);
			}
		}
		for (HistoricoEquivalencia o : equivalencia) {
			disciplinasConcluida = new DisciplinaConcluidaCliente();
			// VERIFICA SE EXISTE NO MAPA A

			// mapaA.get(o.getDisciplinaDestino().getId());

			boolean containsKey = mapaAB.containsKey(o.getDisciplinaDestino().getId());

			if (!containsKey) {
				boolean containsKeyB = mapaB.containsKey(o.getDisciplinaDestino().getId());

				if (containsKeyB) {
					disciplinasConcluida.setAnoCurricular(o.getDisciplinaDestino().getAnoCorricular());
					disciplinasConcluida.setAnoLectivo(o.getEquivalencia().getAnoLectivo().getAnoLectivo().toString());
					disciplinasConcluida.setAnoLectivo(o.getEquivalencia().getAnoLectivo().getAnoLectivoDescricao());
					disciplinasConcluida.setDisciplina(o.getDisciplinaDestino().getDescricao());
					disciplinasConcluida.setNota(o.getNotaOrigem());
					disciplinasConcluida.setSigla(o.getDisciplinaDestino().getSigla());
					disciplinasConcluida.setTipo(o.getDisciplinaDestino().getTipo());
					disciplinasConcluida.setEquivalencia(true);

					// ATRIBUIR O VALOR AO ARRAY DE DISCIPLINAS
					disciplinasConcluidas.add(disciplinasConcluida);
					mapaB.remove(o.getDisciplinaDestino().getId());
				}
			}
		}

		situacao.setDisciplinasConcluidas(disciplinasConcluidas);

		// DISCIPLINAS CONCLUIDAS
		DisciplinaCursandoCliente disciplinaCursando = new DisciplinaCursandoCliente();
		List<DisciplinaCursandoCliente> disciplinasCursando = new ArrayList<DisciplinaCursandoCliente>();

		disciplinasCursando.add(disciplinaCursando);
		// GUIAS EM ABERTA
		List<Guia> guias = this.guiaRepository.findByAlunoNumeroDeAlunoAndLiquidadaAndAnulada(aluno.getNumeroDeAluno(),
				false, false);
		List<GuiaEmAbertoCliente> guiasNaoLiquidadas = new ArrayList<GuiaEmAbertoCliente>();
		GuiaEmAbertoCliente guiaNaoLiquidada;
		for (Guia guia : guias) {

			guiaNaoLiquidada = new GuiaEmAbertoCliente();

			guiaNaoLiquidada.setId(guia.getId());
			guiaNaoLiquidada.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			guiaNaoLiquidada.setAnoLectivoDescricao(guia.getAnoLectivo().getAnoLectivoDescricao());
			guiaNaoLiquidada.setDataEmissao(guia.getDataEmicao());
			guiaNaoLiquidada.setDataVencimento(guia.getDataVencimento());
			// guiaNaoLiquidada.setMoeda(guia.getMoeda().getDesignacao());
			guiaNaoLiquidada.setNumeroGuia(guia.getNumeroGuia());
			guiaNaoLiquidada.setValor(guia.getValor());

			guiasNaoLiquidadas.add(guiaNaoLiquidada);
		}
		situacao.setGuiasAbertas(guiasNaoLiquidadas);

		List<Guia> recidos = this.guiaRepository
				.findByAlunoNumeroDeAlunoAndLiquidadaAndAnulada(aluno.getNumeroDeAluno(), true, false);
		List<GuiaLiquidadaCliente> liquidadas = new ArrayList<GuiaLiquidadaCliente>();
		GuiaLiquidadaCliente liquidada;
		for (Guia guia : recidos) {
			liquidada = new GuiaLiquidadaCliente();

			liquidada.setId(guia.getId());
			liquidada.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			liquidada.setAnoLectivoDescricao(guia.getAnoLectivo().getAnoLectivoDescricao());
			liquidada.setDataEmissao(guia.getDataEmicao());
			liquidada.setDataLiquidada(guia.getDataLiquidacao());
			liquidada.setNumeroGuia(guia.getNumeroGuia());
			liquidada.setValor(guia.getValor());

			liquidadas.add(liquidada);
		}
		situacao.setGuiasLiquidadas(liquidadas);

		List<Disciplina> disciplina = this.disciplinaRepository.findByCursoAndStatus(aluno.getCurso(), true);

		int dCurso = disciplina.size();
		situacao.setDisciplinasCurso(dCurso);
		if (up != null) {
			c.setPermissao(up.getPermissao());
		}
		c.setResultado(situacao);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/aproveitamento/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> aproveitamento(@PathVariable Integer numero) {
		ResponseCliente c = new ResponseCliente();

		Aluno aluno = numero != null ? this.alunoRepository.findByNumeroDeAluno(numero.toString()) : null;
		if (aluno == null) {
			c.setMensagem("Nenhum aluno encontrado.");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		AproveitametoCliente aproveitamento = new AproveitametoCliente();

		aproveitamento.setNome(aluno.getNome());
		aproveitamento.setContencioso(aluno.isContencioso());
		aproveitamento.setCurso(aluno.getCurso().getPlano());
		aproveitamento.setEmail(aluno.getEmail());
		aproveitamento.setTelefone(aluno.getTelefone());
		aproveitamento.setFimCurso(aluno.isFimCurso());
		aproveitamento.setNumero(Integer.parseInt(aluno.getNumeroDeAluno()));
		aproveitamento.setUnidadeDeApoio(aluno.getInstituicao().getDescricao());

		List<HistoricoAluno> historico = this.historicoAlunoRepository.findByAlunoOrderByAnoCorricular(aluno);

		DisciplinaAproveitamentoCliente disciplina;
		List<DisciplinaAproveitamentoCliente> disciplinas = new ArrayList<>();
		for (HistoricoAluno historicoAluno : historico) {
			disciplina = new DisciplinaAproveitamentoCliente();

			disciplina.setDisciplina(historicoAluno.getDisciplina().getDescricao());
			disciplina.setAno(historicoAluno.getAnoCorricular());
			disciplina.setTipo(historicoAluno.getDisciplina().getTipo());
			disciplina.setNota(historicoAluno.getNotaFinal());
			disciplina.setValidada(historicoAluno.isValidada());

			disciplinas.add(disciplina);
		}
		aproveitamento.setDisciplinas(disciplinas);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(aproveitamento);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	/*
	 * DESENVOLVEDOR: Severino Emilio ATUALIZAÇÃO: 22-06-2018
	 * 
	 */
	@RequestMapping(value = "/situacaoAcademicaAlunoPorNome/{nome}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarAlunoPorNome(@PathVariable String nome) {
		ResponseCliente c = new ResponseCliente();
		// try {
		Aluno aluno = nome != null ? this.alunoRepository.findByNome(nome) : null;
		if (aluno == null) {
			c.setMensagem("Nenhum aluno encontrado!");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		List<Matricula> matriculas = this.matriculaRepository.findByAluno(aluno);

		if (matriculas.isEmpty()) {
			c.setMensagem("Nenhuma matricula encontrada para este aluno!");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		SituacaoAcademicaCliente situacao = new SituacaoAcademicaCliente();

		// dados de aluno
		AlunoResumoSituacao sAluno = new AlunoResumoSituacao();
		sAluno.setNome(aluno.getNome());
		sAluno.setCurso(aluno.getCurso().getPlano());
		sAluno.setNumero(Integer.parseInt(aluno.getNumeroDeAluno()));
		sAluno.setEmail(aluno.getEmail());
		sAluno.setTelefone(aluno.getTelefone());
		sAluno.setContencioso(aluno.isContencioso());

		situacao.setAluno(sAluno);
		// final

		// dados de matricula
		InscricaoPorAno inscricao;
		List<InscricaoPorAno> inscricoes = new ArrayList<InscricaoPorAno>();
		// final

		// matriculas
		for (Matricula matricula : matriculas) {
			inscricao = new InscricaoPorAno();
			inscricao.setAnoCurricular(matricula.getAnoCurricular());
			inscricao.setAnoLectivo(matricula.getAnoLectivo().getAnoLectivo());
			inscricao.setAnoLectivoDescricao(matricula.getAnoLectivo().getAnoLectivoDescricao());
			inscricao.setCurso(matricula.getCurso().getPlano());
			inscricao.setDataInscricao(matricula.getData());
			inscricao.setDesconto(matricula.getPercentagemDesconto());
			inscricao.setPlanoPagamento(matricula.getPlanoPagamento().getDescricao());
			inscricao.setTipoInscricao(matricula.getTipoInscricao().getDescricao());
			inscricao.setSigla(matricula.getCurso().getSigla());

			inscricoes.add(inscricao);
		}
		situacao.setInscricaoPorAno(inscricoes);
		// final

		List<DisciplinaCursandoCliente> disciplinasHistorico = new ArrayList<DisciplinaCursandoCliente>();
		DisciplinaCursandoCliente disciplinaHistorico;

		List<HistoricoAluno> historicoAluno = this.historicoAlunoRepository.findByAlunoAndCurso(aluno,
				aluno.getCurso());
		for (HistoricoAluno haluno : historicoAluno) {
			disciplinaHistorico = new DisciplinaCursandoCliente();
			disciplinaHistorico.setDisciplina(haluno.getDisciplina().getDescricao());
			disciplinaHistorico.setTipo(disciplinaHistorico.getTipo());
			disciplinaHistorico.setAnoLectivo(haluno.getAnoLectivo().getAnoLectivo());
			disciplinaHistorico.setAnoLectivoDescricao(haluno.getAnoLectivo().getAnoLectivoDescricao());
			disciplinaHistorico.setAnoCurricular(haluno.getAnoCorricular());

			// disciplinaHistorico.set
			disciplinasHistorico.add(disciplinaHistorico);
		}
		situacao.setDisciplinasInscritas(disciplinasHistorico);
		// Guias Liquidadas
		GuiaLiquidadaCliente guiaLiquidada = new GuiaLiquidadaCliente();
		List<GuiaLiquidadaCliente> guiaLiquidadas = new ArrayList<GuiaLiquidadaCliente>();

		guiaLiquidadas.add(guiaLiquidada);
		situacao.setGuiasLiquidadas(guiaLiquidadas);
		// final

		// Guias Liquidadas
		GuiaEmAbertoCliente guiaAberta = new GuiaEmAbertoCliente();
		List<GuiaEmAbertoCliente> guiaAbertas = new ArrayList<GuiaEmAbertoCliente>();
		guiaAbertas.add(guiaAberta);
		situacao.setGuiasAbertas(guiaAbertas);
		// final

		// Disciplina concluidas
		DisciplinaConcluidaCliente disciplinasConcluida = new DisciplinaConcluidaCliente();
		List<DisciplinaConcluidaCliente> disciplinasConcluidas = new ArrayList<DisciplinaConcluidaCliente>();
		disciplinasConcluidas.add(disciplinasConcluida);

		situacao.setDisciplinasConcluidas(disciplinasConcluidas);
		// final

		// Disciplina concluidas
		DisciplinaCursandoCliente disciplinaCursando = new DisciplinaCursandoCliente();
		List<DisciplinaCursandoCliente> disciplinasCursando = new ArrayList<DisciplinaCursandoCliente>();

		disciplinasCursando.add(disciplinaCursando);

		situacao.setDisciplinasConcluidas(disciplinasConcluidas);
		// final
		// GuiaEmAbertoCliente guia;
		List<Guia> guias = this.guiaRepository.findByAlunoAndLiquidadaAndAnulada(aluno, false, false);
		List<GuiaEmAbertoCliente> guiasNaoLiquidadas = new ArrayList<GuiaEmAbertoCliente>();
		GuiaEmAbertoCliente guiaNaoLiquidada;
		for (Guia guia : guias) {
			guiaNaoLiquidada = new GuiaEmAbertoCliente();

			guiaNaoLiquidada.setId(guia.getId());
			guiaNaoLiquidada.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			guiaNaoLiquidada.setAnoLectivoDescricao(guia.getAnoLectivo().getAnoLectivoDescricao());
			guiaNaoLiquidada.setDataEmissao(guia.getDataEmicao());
			guiaNaoLiquidada.setDataVencimento(guia.getDataVencimento());
			// guiaNaoLiquidada.setMoeda(guia.getMoeda().getDesignacao());
			guiaNaoLiquidada.setNumeroGuia(guia.getNumeroGuia());
			guiaNaoLiquidada.setValor(guia.getValor());

			guiasNaoLiquidadas.add(guiaNaoLiquidada);
		}
		situacao.setGuiasAbertas(guiasNaoLiquidadas);

		List<Guia> recidos = this.guiaRepository.findByAlunoAndLiquidadaAndAnulada(aluno, true, false);
		List<GuiaLiquidadaCliente> liquidadas = new ArrayList<GuiaLiquidadaCliente>();
		GuiaLiquidadaCliente liquidada;
		for (Guia guia : recidos) {
			liquidada = new GuiaLiquidadaCliente();

			liquidada.setId(guia.getId());
			liquidada.setAnoLectivo(guia.getAnoLectivo().getAnoLectivo());
			liquidada.setAnoLectivoDescricao(guia.getAnoLectivo().getAnoLectivoDescricao());
			liquidada.setDataEmissao(guia.getDataEmicao());
			liquidada.setDataLiquidada(guia.getDataLiquidacao());
			liquidada.setNumeroGuia(guia.getNumeroGuia());
			liquidada.setValor(guia.getValor());

			liquidadas.add(liquidada);
		}
		situacao.setGuiasLiquidadas(liquidadas);

		c.setResultado(situacao);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		/*
		 * } catch (Exception e) { c.setCodigo(ResponseCode.values()[1].getDescricao());
		 * c.setMensagem("Erro Interno."); return new ResponseEntity<ResponseCliente>(c,
		 * HttpStatus.OK); }
		 */
	}

	// BUSCAR DISCIPLINAS CURSOS DE VERÃO
	@SuppressWarnings("unused")
	@RequestMapping(value = "/cursoVerao/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> cursoVerao(@PathVariable String numero) {

		List<DisciplinaExtraordinariaCliente> disciplinas = new ArrayList<DisciplinaExtraordinariaCliente>();
		DisciplinaExtraordinariaCliente ds;

		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);

		List<Disciplina> dsRetornadas = this.disciplinaRepository.findByCursoAndCursoDeVeraoAndStatus(aluno.getCurso(),
				true, true);

		Integer anoAluno = calcularAnoCurricular(aluno);

		boolean disciplinaConcluida = false;
		for (Disciplina o : dsRetornadas) {
			ds = new DisciplinaExtraordinariaCliente();

			List<HistoricoAluno> alD = this.historicoAlunoRepository.findByDisciplinaAndAlunoAndAprovado(o, aluno,
					true);
			if (!alD.isEmpty()) {
				disciplinaConcluida = true;
			}

			if (disciplinaConcluida == false) {
				List<HistoricoEquivalencia> shD = this.equivalenciaHistoricoRepository
						.findByAlunoAndDisciplinaDestino(aluno, o);

				for (HistoricoEquivalencia hs : shD) {
					if (hs.getNotaOrigem() > 0) {
						disciplinaConcluida = true;
					}
				}
			}

			if (disciplinaConcluida == false) {
				// if(o.getAnoCorricular()<=anoAluno) {
				ds.setAnoCorricular(o.getAnoCorricular());
				ds.setDescricao(o.getDescricao());
				ds.setTipo(o.getTipo().getDescricao());
				ds.setId(o.getId());
				disciplinas.add(ds);
				// }
			}
			disciplinaConcluida = false;
		}
		return ObjectoDeRetornoParcial.MensagemDeRetorno(disciplinas, 0, null);
	}

	public Integer calcularAnoCurricular(Aluno aluno) {
		Integer anoPego = 1;
		List<HistoricoAluno> hDs = this.historicoAlunoRepository.findByAprovadoAndAluno(true, aluno);
		for (HistoricoAluno o : hDs) {
			if (o.getDisciplina().getAnoCorricular() > anoPego) {
				anoPego = o.getDisciplina().getAnoCorricular();
			}
		}
		List<HistoricoEquivalencia> hHs = this.equivalenciaHistoricoRepository.findByAluno(aluno);
		for (HistoricoEquivalencia o : hHs) {
			if (o.getDisciplinaDestino().getAnoCorricular() > anoPego) {
				anoPego = o.getDisciplinaDestino().getAnoCorricular();
			}
		}
		return anoPego;
	}

	/*
	 * DESENVOLVEDOR: Severino Emilio ATUALIZAÇÃO: 22-06-2018
	 * 
	 */
	@RequestMapping(value = "/aproveitamentoInterno/{numero}", method = RequestMethod.GET)
	public void relatorioApro(HttpServletResponse response, @PathVariable String numero)
			throws JRException, IOException {
		InputStream inputStream = new FileInputStream((new File("E:/R_Academico.jrxml")));
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("numero_matricula", numero);
		// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
		// conectar());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conectar());
		// Configura a respota para o tipo PDF
		response.setContentType("application/pdf");
		// Define que o arquivo pode ser visualizado no navegador e também nome final do
		// arquivo
		// para fazer download do relatório troque 'inline' por 'attachment'
		response.setHeader("Content-Disposition", "inline; filename=listaCurso.pdf");
		// Faz a exportação do relatório para o HttpServletResponse
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}

	/*
	 * DESENVOLVEDOR: Severino Emilio ATUALIZAÇÃO: 22-06-2018
	 * 
	 */
	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}

	/*
	 * DESENVOLVEDOR: Severino Emilio ATUALIZAÇÃO: 22-06-2018
	 * 
	 */
	@RequestMapping(value = "/aproveitamentoIntermediario/{numero}", method = RequestMethod.GET)
	public void relatorioAproveitamentoIntermediario(HttpServletResponse response, @PathVariable String numero)
			throws JRException, IOException {
		InputStream inputStream = new FileInputStream((new File("E:/R_Certificado_Intermedio.jrxml")));
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("numero_matricula", numero);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conectar());
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=listaCurso.pdf");
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}

	/*
	 * DESENVOLVEDOR: Severino Emilio ATUALIZAÇÃO: 22-06-2018
	 */
	@RequestMapping(value = "/aproveitamentoFinal/{numero}", method = RequestMethod.GET)
	public void relatorioAproveitamentoFinal(HttpServletResponse response, @PathVariable String numero)
			throws JRException, IOException {
		InputStream inputStream = new FileInputStream((new File("E:/R_Certificado_Final.jrxml")));
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("numero_matricula", numero);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conectar());
		// Configura a respota para o tipo PDF
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=listaCurso.pdf");
		// Faz a exportação do relatório para o HttpServletResponse
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}

	/*
	 * DESENVOLVEDOR: Severino Emilio Relatorio: Aproveitamento interno ATUALIZAÇÃO:
	 * 26-06-2018
	 */
	@GetMapping("/relatorios/aproveitamento-interno")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioAproveitamentoInterno(@RequestParam Integer id) throws Exception {

		byte[] relatrio = servicoAproveitamentoInterno(id);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoAproveitamentoInterno(Integer numero) throws JRException {

		Map<String, Object> paramets = new HashMap<>();
		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		paramets.put("data", formato.format(data));
		paramets.put("numero_matricula", numero);
		paramets.put("condicao", "2");
		// oba

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Uso_Interno.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	/* FIM: relatorioAproveitamentoInterno */
	/*
	 * DESENVOLVEDOR: Severino Emilio Relatorio: Aproveitamento intermediario
	 * ATUALIZAÇÃO: 26-06-2018
	 */
	@GetMapping("/relatorios/aproveitamento-intermediario")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioAproveitamentoIntermediario(@RequestParam String numero_aluno,
			@RequestParam String condicao, @RequestParam Integer numeroPedido) throws Exception {

		RegistroDocumentos pedido = this.registroDocumentoRepository.findOne(numeroPedido);
		List<Matricula> inscricoes = this.matriculaRepository.findByAluno(pedido.getAluno());
		int ano = 0;
		int maximaMatricula = 0;
		for (Matricula m : inscricoes) {
			if (m.getAnoCurricular() > maximaMatricula) {
				maximaMatricula = m.getAnoCurricular();
				ano = m.getAnoCurricular();
			}
		}

		byte[] relatrio = servicoAproveitamentoIntermediario(numero_aluno, numeroPedido, condicao, ano);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoAproveitamentoIntermediario(String numero_aluno, Integer numeroPedido, String condicao,
			Integer ano) throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_aluno);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		paramets.put("ano_curricular", ano);
		paramets.put("numero_pedido", numeroPedido);

		InputStream inputStream = null;

		Aluno aluno = alunoRepository.findByNumeroDeAluno(numero_aluno);
		if (aluno.getCurso().getGrau() == Grau.LICENCIATURA) {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Certificado_Intermedio.jasper");
		} else if (aluno.getCurso().getGrau() == Grau.POSGRADUACAO) {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Certificado_Intermedio_Pos.jasper");
		} else if (aluno.getCurso().getGrau() == Grau.MESTRADO) {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Certificado_Intermedio_Mestrado.jasper");
		}

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumento(numeroPedido);

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/relatorios/certificado-final-parteLectiva-pos")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> declaracaoFinalParteLectivaPos(@RequestParam Integer numero_aluno,
			@RequestParam Integer anoLectivo, @RequestParam Integer numeroPedido,
			@RequestParam String condicao) throws Exception {
		
		byte[] relatrio = servicoDeclaracaoFinalParteLectivaPos(numero_aluno, anoLectivo, numeroPedido, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoDeclaracaoFinalParteLectivaPos(Integer numero_aluno, Integer anoLectivo,
			Integer numeroPedido, String condicao) throws JRException, SQLException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		String dataFormatada = formato.format(data);
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_aluno);
		paramets.put("ano_lectivo", anoLectivo);
		paramets.put("numero_pedido", numeroPedido);
		paramets.put("condicao", condicao);
		paramets.put("data", dataFormatada);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_certificado_Declaracao_Pos_graduacao.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		if (condicao.equals("1")) {
			alterEstadoDocumento(numeroPedido);
		}
		conectar().isClosed();
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	/* FIM: relatorioAproveitamentoIntermediario */
	/*
	 * DESENVOLVEDOR: Severino Emilio Relatorio: Aproveitamento Final ATUALIZAÇÃO:
	 * 26-06-2018
	 */
	@GetMapping("/relatorios/aproveitamento-final")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioAproveitamentoFinal(@RequestParam Integer id) throws Exception {

		byte[] relatrio = servicoAproveitamentoFinal(id);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoAproveitamentoFinal(Integer numero) throws JRException {

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Certificado_Final.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	/* FIM: relatorioAproveitamentoFinal */

	private void alterEstadoDocumento(Integer numeroPedido) {
		RegistroDocumentos pedido = this.registroDocumentoRepository.findOne(numeroPedido);
		pedido.setDataEmissao(new Date());
		pedido.setRetirado(true);
		this.registroDocumentoRepository.save(pedido);
	}
	
	private void alterEstadoDocumentoMestrado(Integer numeroPedido, Integer usuarioEmitiu) {
		RegistroDocumentos pedido = this.registroDocumentoRepository.findOne(numeroPedido);
		Usuario usuarioSolicitou = this.usuarioRepository.findByUserCode(usuarioEmitiu);
		
		
		if(usuarioSolicitou == null) {
			System.out.println("Usuario não existe");
		}else {
			System.out.println(usuarioSolicitou.getName() + " " + usuarioSolicitou.getUserCode() );
		}
		pedido.setDataEmissao(new Date());
		pedido.setRetirado(true);
		//pedido.setUsuario_emitiu(new Usuario(usuarioSolicitou.getId()));
		this.registroDocumentoRepository.save(pedido);

	}

	@GetMapping("/certificado-intermedio/semNota/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCertificadoIntermedioSemNotaMestrado(@RequestParam Integer numero_aluno,
			@RequestParam Integer numeroPedido, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCertificadoIntermedioSemNotaMestrado(numero_aluno, numeroPedido, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCertificadoIntermedioSemNotaMestrado(Integer numero_aluno, Integer numeroPedido, String condicao)
			throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_aluno);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_certificado_Declaracao_Pos_graduacao.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumento(numeroPedido);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@GetMapping("/certificado-intermedio/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCertificadoIntermedioMestrado(@RequestParam String numero_aluno,
			@RequestParam Integer numeroPedido, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCertificadoIntermedioMestrado(numero_aluno, numeroPedido, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCertificadoIntermedioMestrado(String numero_aluno, Integer numeroPedido, String condicao)
			throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_aluno);
		paramets.put("numeroPedido", numeroPedido);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		InputStream inputStream = null;
		if (condicao.equals("2")) {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Uso_Interno.jasper");
		} else {
			inputStream = this.getClass().getResourceAsStream("/relatorio/R_Certificado_Intermedio_Mestrado.jasper");
		}
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumento(numeroPedido);
		// koke
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
	@GetMapping("/certificado-provisorio/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCertificadoProvisorioMestrado(@RequestParam Integer numero_aluno,
			@RequestParam Integer numeroPedido, @RequestParam Integer anoLectivo, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCertificadoProvisorioMestrado(numero_aluno, numeroPedido, anoLectivo, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCertificadoProvisorioMestrado(Integer numero_aluno, Integer numeroPedido, Integer anoLectivo, String condicao)
			throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_aluno);
		paramets.put("id_ano_lectivo", anoLectivo);
		paramets.put("numero_pedido", numeroPedido);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_certificado_provisório.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumento(numeroPedido);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@GetMapping("/cartaPesquisa/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCartaPesquisaMestrado(@RequestParam Integer numero_aluno,
			@RequestParam Integer numeroPedido, @RequestParam Integer anoLectivo, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCartaPesquisaMestrado(numero_aluno, numeroPedido, anoLectivo, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCartaPesquisaMestrado(Integer numero_aluno, Integer numeroPedido, Integer anoLectivo, String condicao)
			throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_aluno);
		paramets.put("id_anolectivo", anoLectivo);
		paramets.put("numero_pedido", numeroPedido);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Declaracao_Carta_Pesquisa_Mestrado.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumento(numeroPedido);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	

	@RequestMapping(value = "/loginAPP/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> login(@PathVariable String numero, @PathVariable String userName) {
		Aluno aluno = this.alunoService.aluno(numero);
		if (aluno == null) {
			this.assunto = "Erro - Autenticação (Aplicativo)";
			this.mensagem = "O estudante com o número \"" + numero
					+ "\" não consegue iniciar sessão porque não foi encontrado seu registo.";
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(aluno, 2, "Nenhum registro de aluno encontrado.");
		} else {
			AlunoResumoSituacao dadosDoAluno = new AlunoResumoSituacao();
			AlunoAnexo fotoAluno = this.alunoAnexoRepository.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());
			PermissaoCurso permisaoCurso = this.permissaoService.permissaoAoCurso(numero, userName);
			dadosDoAluno.setNome(aluno.getNome());
			dadosDoAluno.setCurso(aluno.getCurso().getPlano());
			dadosDoAluno.setCodigoCurso(aluno.getCurso().getId());
			dadosDoAluno.setNumero(Integer.parseInt(aluno.getNumeroDeAluno()));
			dadosDoAluno.setEmail(aluno.getEmail());
			dadosDoAluno.setTelefone(aluno.getTelefone());
			dadosDoAluno.setContencioso(aluno.isContencioso());
			dadosDoAluno.setAnulado(aluno.isInactivo());
			dadosDoAluno.setFimCurso(aluno.isFimCurso());
			dadosDoAluno.setFoto(fotoAluno.getFoto());
			dadosDoAluno.setGrau(aluno.getCurso().getGrau().getDescricao());
			return ObjectoDeRetornoParcial.MensagemDeRetorno(dadosDoAluno, permisaoCurso, 0, null);
		}
	}

	@RequestMapping(value = "/disciplinasIncritasConcluidas/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> disciplinasIncritas(@PathVariable String numero,
			@PathVariable String userName) {
		List<Matricula> todasMatriculasDeUmAluno = this.matriculasService.todasMatriculasDeUmAluno(numero);
		if (todasMatriculasDeUmAluno.isEmpty()) {
			this.assunto = "Erro - Ver notas (Aplicativo)";
			this.mensagem = "O estudante com o número \"" + numero
					+ "\" não consegue ver as notas porque não há registo de matrículas.";
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de Matrícula encontrado.");
		} else {
			SituacaoAcademicaCliente situacao = new SituacaoAcademicaCliente();
			PermissaoCurso permisaoCurso = this.permissaoService.permissaoAoCurso(numero, userName);
			List<InscricaoPorAno> inscricaoPorAno = inscricaoService
					.todasInscricoesPorAnoDeUmAluno(todasMatriculasDeUmAluno);
			List<DisciplinaCursandoCliente> disciplinasInscritas = this.disciplinaService.disciplinasInscritas(numero);
			situacao.setInscricaoPorAno(inscricaoPorAno);
			situacao.setDisciplinasInscritas(disciplinasInscritas);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(situacao, permisaoCurso, 0, null);

		}
	}

	@RequestMapping(value = "/guiasAPP/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiasAPP(@PathVariable String numero, @PathVariable String userName) {
		Aluno aluno = this.alunoService.aluno(numero);
		if (aluno == null) {
			this.mensagem = "O estudante com o número \"" + numero
					+ "\" não consegue visualizar as Guias porque não há registo do mesmo.";
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de Aluno encontrado.");
		} else {
			SituacaoAcademicaCliente situacao = new SituacaoAcademicaCliente();
			PermissaoCurso permisaoCurso = this.permissaoService.permissaoAoCurso(numero, userName);
			List<GuiaLiquidadaCliente> guiasLuiquidadas = this.guiaLiquidadaService.guiasLuiquidadas(numero);
			List<GuiaEmAbertoCliente> guiasEmAbero = this.guiaEmAbertoService.guiasEmAbero(numero);
			situacao.setGuiasLiquidadas(guiasLuiquidadas);
			situacao.setGuiasAbertas(guiasEmAbero);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(situacao, permisaoCurso, 0, null);
		}
	}

	@RequestMapping(value = "/todasGuiasAPP/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todasGuiasAPP(@PathVariable String numero, @PathVariable String userName) {
		Aluno aluno = this.alunoService.aluno(numero);
		if (aluno == null) {
			this.mensagem = "O estudante com o número \"" + numero
					+ "\" não consegue visualizar as guias porque não há registo do mesmo.";
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de Aluno encontrado.");
		} else {
			List<TodasGuiasCliente> todasGuias = this.todasGuiaService.todasGuias(aluno.getNumeroDeAluno());
			return ObjectoDeRetornoParcial.MensagemDeRetorno(todasGuias, 0, null);
		}
	}

	@RequestMapping(value = "/guiasLiquidadasAPP/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiasLiquidadasAPP(@PathVariable String numero,
			@PathVariable String userName) {
		this.assunto = "Erro - Guias Liquidadas (Aplicativo)";
		Aluno aluno = this.alunoService.aluno(numero);
		if (aluno == null) {
			this.mensagem = "O estudante com o número \"" + numero
					+ "\" não consegue visualizar as guias liquidas porque não há registo do mesmo.";
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de Aluno encontrado.");
		} else {
			PermissaoCurso permisaoCurso = this.permissaoService.permissaoAoCurso(numero, userName);
			List<GuiaLiquidadaCliente> guiasLuiquidadas = this.guiaLiquidadaService.guiasLuiquidadas(numero);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(guiasLuiquidadas, permisaoCurso, 0, null);
		}
	}

	@RequestMapping(value = "/guiasAbertasAPP/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiasAbertasAPP(@PathVariable String numero, @PathVariable String userName) {
		this.assunto = "Erro - Guias Abertas (Aplicativo)";
		Aluno aluno = this.alunoService.aluno(numero);
		if (aluno == null) {
			this.mensagem = "O estudante com o número \"" + numero
					+ "\" não consegue visualizar as guias abertas porque não há registo do mesmo.";
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de Aluno encontrado.");
		} else {
			PermissaoCurso permisaoCurso = this.permissaoService.permissaoAoCurso(numero, userName);
			List<GuiaEmAbertoCliente> guiasEmAbero = this.guiaEmAbertoService.guiasEmAbero(numero);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(guiasEmAbero, permisaoCurso, 0, null);
		}
	}

	@RequestMapping(value = "/emolumentosDaGuiaAPP/{idDaGuia}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiasAPP(@PathVariable Integer idDaGuia) {
		this.assunto = "Erro - Listagem de emolumentos (Aplicativo)";
		this.mensagem = "Houve um erro ao tentar listar os emolumentos da Guia: " + idDaGuia;
		Guia guia = this.guiaRepository.findById(idDaGuia);
		if (guia == null) {
			this.mensagem += ". A guia não foi encontrada.";
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Erro ao carregar emolumentos");
		} else {
			List<EmolumentoCliente> emolumentos = this.emolumentoService.emolumentos(guia, guia.getAluno().getCurso(),
					guia.getAnoLectivo());
			if (emolumentos.isEmpty()) {
				this.mensagem += ". Não há registo de emolumentos.";
				this.emailService.reportarFalha(this.assunto, this.mensagem);
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Não há registo de emolumentos !");
			} else {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(emolumentos, 0, null);
			}
		}
	}

	@RequestMapping(value = "/guiaReciboApp/{codigoGuia}/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiaReciboApp(@PathVariable String codigoGuia, @PathVariable String numero,
			@PathVariable String userName) {
		try {
			this.assunto = "Erro - Guia Liquidada (Aplicativo)";
			this.mensagem = "O estudante com o número \"" + numero
					+ "\" não consegue solicitar uma Guia Liquidada por email porque";
			Aluno aluno = this.alunoService.aluno(numero);
			if (aluno == null) {
				this.mensagem += " não há registo do estudante.";
				this.emailService.reportarFalha(this.assunto, this.mensagem);
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum registo de Aluno encontrado.");
			} else {
				String destinatario = aluno.getEmailInstitucional();
				if (destinatario.isEmpty() || (destinatario == null)) {
					this.mensagem += " não possui um email institucional.";
					this.emailService.reportarFalha(this.assunto, this.mensagem);
					return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,
							"Encontramos alguma inconsitência no seu email. Por favor, contacto o suporte.");
				} else {
					this.assunto = "Guia Liquidada";
					this.mensagem = "Prezado(a) " + aluno.getNome() + ", em anexo está a Guia Liquidada que solicitou.";
					byte[] ficheiro = guiaService.guiaDePagamento(codigoGuia, userName);
					return emailService.enviarGuiaLiquidada(destinatario, this.assunto, mensagem, codigoGuia, ficheiro);
				}
			}
		} catch (JRException erro) {
			this.mensagem += " o ficheiro não está a ser gerado\n Erro: " + erro.getMessage();
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,
					"Erro ao validar guia. Tente novamente mais tarde");
		} catch (ClassNotFoundException | SQLException erro) {
			this.mensagem += " há um erro no Backend\n Codigo do Erro: " + erro.getMessage();
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,
					"Erro ao Processar guia. Tente novamente mais tarde");
		} catch (Exception erro) {
			this.mensagem += " há um erro no Backend\n Codigo do Erro: " + erro.getMessage();
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,
					"Erro ao Processar guia. Tente novamente mais tarde");
		}
	}
	

	@GetMapping("/requerimento/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioRequerimentoMestrado(@RequestParam String numero, String modelo) throws Exception {
		byte[] relatrio = servicoRequerimentoMestrado(numero, modelo);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoRequerimentoMestrado(String numero, String modelo)throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);
		
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_aluno", numero);
		paramets.put("tipo_de_modelo", modelo);
		paramets.put("data", formato.format(data));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Requerimento_Mestrado.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@GetMapping("/certificado-A/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCertificadoA(@RequestParam String numero_matricula, @RequestParam Integer ano_declaracao, @RequestParam Integer numero_declaracao, Integer usuarioEmitiu, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCertificadoA(numero_matricula, ano_declaracao, numero_declaracao, usuarioEmitiu, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCertificadoA(String numero_matricula, Integer ano_declaracao, Integer numero_declaracao, Integer usuarioEmitiu, String condicao)
			throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_matricula);
		paramets.put("ano_declaracao", ano_declaracao);
		paramets.put("numero_declaracao", numero_declaracao);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/Modelo_A.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumentoMestrado(numero_declaracao, usuarioEmitiu);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@GetMapping("/certificado-B/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCertificadoB(@RequestParam String numero_matricula, @RequestParam Integer ano_declaracao, @RequestParam Integer numero_declaracao, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCertificadoB(numero_matricula, ano_declaracao, numero_declaracao, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCertificadoB(String numero_matricula, Integer ano_declaracao, Integer numero_declaracao, String condicao)
			throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_matricula);
		paramets.put("ano_declaracao", ano_declaracao);
		paramets.put("numero_declaracao", numero_declaracao);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/Modelo_B.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumento(numero_declaracao);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
	@GetMapping("/certificado-C/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCertificadoC(@RequestParam String numero_matricula, @RequestParam Integer ano_declaracao, @RequestParam Integer numero_declaracao, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCertificadoC(numero_matricula, ano_declaracao, numero_declaracao, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCertificadoC(String numero_matricula, Integer ano_declaracao, Integer numero_declaracao, String condicao)
			throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_matricula);
		paramets.put("ano_declaracao", ano_declaracao);
		paramets.put("numero_declaracao", numero_declaracao);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/Modelo_C.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumento(numero_declaracao);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	
	@GetMapping("/certificado-D/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCertificadoD(@RequestParam String numero_matricula, @RequestParam Integer ano_declaracao, @RequestParam Integer numero_declaracao, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCertificadoD(numero_matricula, ano_declaracao, numero_declaracao, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCertificadoD(String numero_matricula, Integer ano_declaracao, Integer numero_declaracao, String condicao)
			throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_matricula);
		paramets.put("ano_declaracao", ano_declaracao);
		paramets.put("numero_declaracao", numero_declaracao);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/Modelo_D.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumento(numero_declaracao);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@GetMapping("/certificado-G/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCertificadoG(@RequestParam String numero_matricula, @RequestParam Integer ano_declaracao, @RequestParam Integer numero_declaracao, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCertificadoG(numero_matricula, ano_declaracao, numero_declaracao, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCertificadoG(String numero_matricula, Integer ano_declaracao, Integer numero_declaracao, String condicao)
			throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_matricula);
		paramets.put("ano_declaracao", ano_declaracao);
		paramets.put("numero_declaracao", numero_declaracao);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/Modelo_G.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumento(numero_declaracao);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	/*
	@GetMapping("/certificado-B/mestrado")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioCertificadoB(@RequestParam String numero_aluno, @RequestParam Integer codigo_ano_lectivo, @RequestParam Integer numero_declaracao, @RequestParam String condicao) throws Exception {
		byte[] relatrio = servicoCertificadoB(numero_aluno, codigo_ano_lectivo, numero_declaracao, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCertificadoB(String numero_aluno, Integer codigo_ano_lectivo, Integer numero_declaracao, String condicao)
			throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();

		paramets.put("numero_matricula", numero_aluno);
		paramets.put("ano_declaração", codigo_ano_lectivo);
		paramets.put("numero_declaracao", numero_declaracao);
		paramets.put("condicao", condicao);
		paramets.put("data", formato.format(data));
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/Modelo B.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		if (condicao.equals("1"))
			alterEstadoDocumento(numero_declaracao);
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}*/

	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/gerar-referencia/{numeroGuia}/{numeroTelefone}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos(@PathVariable Integer numeroGuia,
			@PathVariable String numeroTelefone) throws UnirestException {
		ResponseCliente c = new ResponseCliente();

		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		Guia guia = this.guiaRepository.findOne(numeroGuia);
		String mensagem = "";
		int codigo = 0;

		// 121956
		Date dataVencimento = guia.getDataVencimento();
		// PEGA DATA AGORA.
		Date hojeAtual = new Date();

		List<GuiaPagamentoHistorico> historico = this.historicoGuiaRepository.findByGuia(guia);
		for (GuiaPagamentoHistorico o : historico) {
			if ((o.getEmolumento().getId() >= 52 && o.getEmolumento().getId() <= 67)
					&& hojeAtual.getTime() >= dataVencimento.getTime()) {
				c.setMensagem("Mensalidade vencida deve ser tratada nas finanças.");
				c.setCodigo(300);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		}

		if (guia != null) {
			if (guia.isLiquidada()) {
				c.setMensagem("Está guia já foi liquidada.");
				c.setCodigo(300);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			List<CompensassaoProvisoria> guiaExiste = this.compensassaoRepository
					.findByNumeroGuia(guia.getNumeroGuia());

			boolean excluida = false;

			if (!guiaExiste.isEmpty()) {
				for (CompensassaoProvisoria pr : guiaExiste) {
					CompensassaoProvisoria compesassao = this.compensassaoRepository.findOne(pr.getId());
					this.compensassaoRepository.delete(compesassao);

					excluida = true;
				}
			}

			if (guiaExiste.isEmpty() || excluida) {
				if (guia.getAluno().isContencioso()) {
					return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,
							"Infelizmente não pode gerar referência porque está no contencioso.");
				} else {

					// COMEÇO DO METÓDO
					List<GuiaPagamentoHistorico> guiaHist = this.historicoGuiaRepository.findByGuia(guia);
					Unidade unidade = new Unidade();
					// BUSCA DATA VENCIMENTO DA GUIA.
					Date data = guia.getDataVencimento();

					// PEGA DATA AGORA.
					Date hoje = new Date();

					// VERIFICA SE A DATA HOJE FOR MAIOR QUE DATA VENCIMENTO É REDEFINIDA
					if (hoje.getTime() >= data.getTime()) {

						data = hoje;
						mensagem = "Refêrencia enviada com sucesso.";
					} else {
						data = guia.getDataVencimento();

						mensagem = "Refêrencia enviada com sucesso.";
					}

					codigo = 200;
					Locale local = new Locale("pt", "BR");
					DateFormat formato = new SimpleDateFormat("yyyy-MM-dd", local);

					String vencimento = formato.format(data);

					DecimalFormat df = new DecimalFormat("#.##");
					df.setRoundingMode(RoundingMode.HALF_UP);
					String valorFormatado = df.format(guia.getValor());
					valorFormatado = valorFormatado.replace(',', '.');
					 
					double valorArredondado = Double.parseDouble(valorFormatado);
					System.out.println("Valor arredondado");
					 
					
					unidade.setExpiry_date(vencimento);
					unidade.setAmount(valorArredondado);

					// ------------------
					Filds custom_fields = new Filds();

					if (guiaHist.get(0).getObs() != null) {
						custom_fields.setDescription(
								guiaHist.get(0).getEmolumento().getEmolumento() + " - " + guiaHist.get(0).getObs());
					} else {
						custom_fields.setDescription(guiaHist.get(0).getEmolumento().getEmolumento());
					}

					// AQUI
					String telefone = numeroTelefone == null ? guia.getAluno().getTelefone().replace(" ", "") : numeroTelefone;

					
					if(guia.getAluno().getEmail() == null) {
						return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,
								"Atualize o email do estudnte");
					}
					
					/*System.out.println("Validador " + validadorEmail.isValid(guia.getAluno().getEmail().trim()));
					if(validadorEmail.isValid(guia.getAluno().getEmail().trim()) == false) {
						return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,
								"Erro no email do estudante.");
					}*/
					
					
					custom_fields.setMobile(telefone);
					custom_fields.setEmail(guia.getAluno().getEmail());
					custom_fields.setName(guia.getAluno().getNome());
					custom_fields.setGuia(guia.getNumeroGuia());
					// novos campos
					custom_fields.setNumeroDeAluno(guia.getAluno().getNumeroDeAluno());
					custom_fields.setUnidade("0010");
					custom_fields.setInst_description(instituicao.getSigla());
					
					unidade.setCustom_fields(custom_fields);
					 
					RestTemplate restTemplate = new RestTemplate();
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					HttpEntity<Object> requestEntity = new HttpEntity<>(unidade, headers);
					ResponseEntity<ReferenciaRetornoDTO> responseEntity = restTemplate.exchange(baseUrl + "/criar",HttpMethod.POST, requestEntity, ReferenciaRetornoDTO.class);
					CompensassaoProvisoria compensassao = new CompensassaoProvisoria();


					compensassao.setNumeroGuia(guia.getNumeroGuia());
					compensassao.setDataCriacao(new Date());
					compensassao.setDataVencimento(guia.getDataVencimento());
					compensassao.setDescricao(guiaHist.get(0).getEmolumento().getEmolumento());
					compensassao.setNome(guia.getAluno().getNome());
					compensassao.setNumero(Integer.parseInt(guia.getAluno().getNumeroDeAluno()));
					compensassao.setTelefone(numeroTelefone);
					compensassao.setValor(guia.getValor());
					compensassao.setReferencia(responseEntity.getBody().getReferencia());
					// providi
					this.compensassaoRepository.save(compensassao);
					// FINAL DO METÓDO

					// ATUALIZA O Número de aluno
					Aluno aluno = this.alunoRepository.findByNumeroDeAluno(guia.getNumeroDeAluno());
					if (numeroTelefone != null || aluno.getTelefone() != numeroTelefone) {
						aluno.setTelefone(numeroTelefone);
						aluno.setUltimaModificacao(new Date());
						this.alunoRepository.save(aluno);
						this.alunoService.gerarHistorico(aluno);
					}
				}

			} else {

				mensagem = "A referência já havia sido enviada.";
				codigo = 300;
			}
			// mensagem="Referencia criada e enviada com sucesso.";
		} else {
			mensagem = "Guia não existe.";
			codigo = 300;
		}

		c.setMensagem(mensagem);
		c.setCodigo(codigo);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

	}
	
}