package ao.co.intellectus.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AdicionarOrRemoverDisciplina;
import ao.co.intellectus.DTO.AlunoId2;
import ao.co.intellectus.DTO.AlunoResumoCliente;
import ao.co.intellectus.DTO.BolseiroAtualizar;
import ao.co.intellectus.DTO.BuscaAluno;
import ao.co.intellectus.DTO.ConvenioCliente;
import ao.co.intellectus.DTO.DisciplinaCliente;
import ao.co.intellectus.DTO.DisciplinaInscricaoControle;
import ao.co.intellectus.DTO.EdicaoMatriculaNova;
import ao.co.intellectus.DTO.EmpresaAnoLectivoCliente;
import ao.co.intellectus.DTO.FichaAcademicaCliente;
import ao.co.intellectus.DTO.FichaAcademicaRetorno;
import ao.co.intellectus.DTO.HistoricoAlunoCliente;
import ao.co.intellectus.DTO.InputDisciplinaViewModel;
import ao.co.intellectus.DTO.MatriculaAndHistorico;
import ao.co.intellectus.DTO.MatriculaColegioViewModel;
import ao.co.intellectus.DTO.MatriculaRecebida;
import ao.co.intellectus.DTO.PercentagemDesconto;
import ao.co.intellectus.DTO.TipoInscricaoMatricula;
import ao.co.intellectus.DTO.TransferenciaMatricula;
import ao.co.intellectus.DTO.TurmaBaseEdicao;
import ao.co.intellectus.DTO.TurmaDisciplinaAlterar;
import ao.co.intellectus.DTO.colegio.DisciplinaClienteColegio;
import ao.co.intellectus.DTO.colegio.MatriculaConfirmadaViewModel;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.ContaCorrenteEmpresa;
import ao.co.intellectus.model.Contador;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.EmpresaConvenio;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.model.HistoricoSimples;
import ao.co.intellectus.model.HistoricoTrocaCurso;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.Mes;
import ao.co.intellectus.model.Moeda;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.PlanoPagamento;
import ao.co.intellectus.model.RegistroDocumentos;
import ao.co.intellectus.model.Situacao;
import ao.co.intellectus.model.TipoDeDeclaracao;
import ao.co.intellectus.model.TipoInscricao;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.TurmaDisponivel;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.ccd.MatriculaCcd;
import ao.co.intellectus.model.enumeracao.TipoFatura;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.ContaCorrenteRepository;
import ao.co.intellectus.repository.ContaCorreteEmpresaRepository;
import ao.co.intellectus.repository.ContadorRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.EmpresaConvenioRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoTrocaCursoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.MesRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.PermissaoCursoRepository;
import ao.co.intellectus.repository.PlanoPagamentoRepository;
import ao.co.intellectus.repository.RegistroDocumentoRepository;
import ao.co.intellectus.repository.TipoDeDeclaracaoRepository;
import ao.co.intellectus.repository.TipoDeInscricaoRepository;
import ao.co.intellectus.repository.TurmaDisponivelRepository;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.repository.ccd.MatriculaCcdRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarNumeroDocumento;
import ao.co.intellectus.servico.cafold.AlunoService;
import ao.co.intellectus.servico.cafold.AnoLectivoService;
import ao.co.intellectus.servico.cafold.GuiaService;
import ao.co.intellectus.servico.cafold.HistoricoAlunoService;
import ao.co.intellectus.servico.cafold.MatriculasService;
import ao.co.intellectus.servico.exception.DadoInvalidoException;
import ao.co.intellectus.servico.guias.GerarNumeroDeGuia;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import ao.co.intellectus.util.FormataData;

@RestController
@RequestMapping("/matricula")
public class ControllerMatricula {
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private TipoDeInscricaoRepository tipoInscricaoRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private MatriculaRepository repositoryMatricula;
	@Autowired
	private MatriculaCcdRepository repositoryMatriculaCcd;
	@Autowired
	private AnoLectivoRepository repositoryAnoLectivo;
	@Autowired
	private DisciplinaRepository repositoryDisciplina;
	@Autowired
	private HistoricoAlunoRepository repositoryHistoricoAluno;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private EmpresaConvenioRepository empresaConvenioRepository;
	@Autowired
	private GuiaPagamentoRepository guia;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private PlanoPagamentoRepository planoPagamentoRepository;
	@Autowired
	private MesRepository mesRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaRepository;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private HistoricoTrocaCursoRepository historicoTrocaCursoRepository;
	@Autowired
	private RegistroDocumentoRepository registroDocumentoRepository;
	@Autowired
	private TipoDeDeclaracaoRepository tipoDeDeclaracaoRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private MoedaRepository moedaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private TurmaDisponivelRepository turmaDisponiveisRepository;
	@Autowired
	private PermissaoCursoRepository permissaoCursoRepository;
	@Autowired
	private ContadorRepository contadorRepository;
	@Autowired
	private EquivalenciaHistoricoRepository equivalenciaHistoricoRepository;
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	private MatriculasService matriculasService;
	@Autowired
	private HistoricoAlunoService historicoAlunoService;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private GuiaService guiaService;
	@Autowired
	private AnoLectivoService anoLectivoService;
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;
	@Autowired
	private GeradorDeArquivo gerarDocService;
	@Autowired
	private ContaCorreteEmpresaRepository contaCorrenteEmpresaRepo;
	@Autowired
	private GuiaCandidaturaRepository guiaCandidaturaRepo;
	
	FormataData forma = new FormataData();

	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> matriculas() {
		ResponseCliente c = new ResponseCliente();
		Iterable<Matricula> todas = repositoryMatricula.findAll();

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todas);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorId/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarMatriculaPorId(@PathVariable Integer id) {
		Matricula matricula = repositoryMatricula.findOne(id);
		ResponseCliente c = new ResponseCliente();

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(matricula);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarMatriculaAndHistorico/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarMatriculaAndHistorico(@PathVariable Integer id) {
		Matricula matricula = repositoryMatricula.findOne(id);
		List<HistoricoAluno> historico = repositoryHistoricoAluno.findByMatricula(matricula);
		
		
		MatriculaAndHistorico hm = new MatriculaAndHistorico();
		// HistoricoSimples
		hm.setCurso(matricula.getCurso().getDescricao());
		hm.setIdMatricula(matricula.getId());
		hm.setNome(matricula.getAluno().getNome());
		hm.setNumeroAluno(Integer.parseInt(matricula.getAluno().getNumeroDeAluno().toString()));
		hm.setPlanoPagamento(matricula.getPlanoPagamento());
		hm.setTipoInscricao(matricula.getTipoInscricao());
		hm.setTurma(matricula.getTurmaBase());

		HistoricoSimples hSimples;
		List<HistoricoSimples> alunosHistorico = new ArrayList<HistoricoSimples>();
		for (HistoricoAluno historicoAluno : historico) {
			hSimples = new HistoricoSimples();

			hSimples.setDisciplina(historicoAluno.getDisciplina().getDescricao());
			hSimples.setDisciplinaId(historicoAluno.getDisciplina().getId());

			hSimples.setTurma(matricula.getAnoCurricular() + historicoAluno.getTurma().getTurma());
			hSimples.setTurmaId(historicoAluno.getTurma().getId());

			alunosHistorico.add(hSimples);
		}
		hm.setHistoricoAluno(alunosHistorico);
		ResponseCliente c = new ResponseCliente();
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(hm);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/fichaAcademica/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> fichaAcademica(@PathVariable Integer id) {

		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(id.toString());

		// List<HistoricoAluno> historico = repositoryHistoricoAluno.findByAluno(aluno);
		FichaAcademicaRetorno hm = new FichaAcademicaRetorno();

		hm.setNome(aluno.getNome());
		hm.setNumeroAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
		hm.setCurso(aluno.getCurso().getDescricao());
		hm.setNomeDoPai(aluno.getNomeDoPai());
		hm.setNomeDaMae(aluno.getNomeDaMae());
		hm.setNacionalidade(aluno.getNacionalidade().getDescricao());
		hm.setNaturalidade(aluno.getMunicipio().getDescricao());
		hm.setProvinciaNascimento(aluno.getProvincia().getProvincia());
		hm.setTipoDocumento(aluno.getDocumentoIdentificacao().getDescricao());
		hm.setNumeroDocumento(aluno.getNumeroDocumento());

		Date nascimento = aluno.getDataNascimento();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nascimento);

		hm.setDiaNascimento(calendar.get(Calendar.DAY_OF_MONTH));
		Mes mes = this.mesRepository.findOne(calendar.get(Calendar.MONTH) + 1);

		hm.setAnoNascimento(calendar.get(Calendar.YEAR));
		hm.setMesNascimento(mes.getDescricao());

		FichaAcademicaCliente hSimples;
		List<FichaAcademicaCliente> alunosHistorico = new ArrayList<FichaAcademicaCliente>();

		List<Disciplina> disciplinas = this.repositoryDisciplina
				.findByCursoAndStatusOrderByAnoCorricularAsc(aluno.getCurso(), true);
		for (Disciplina disciplina : disciplinas) {
			hSimples = new FichaAcademicaCliente();

			hSimples.setAnoCurricular(disciplina.getAnoCorricular());
			hSimples.setDisciplina(disciplina.getDescricao());
			hSimples.setRegime(disciplina.getTipo().getDescricao());

			List<HistoricoAluno> inscDisciplina = this.repositoryHistoricoAluno
					.findByAlunoAndAnoCorricularAndDisciplina(aluno, disciplina.getAnoCorricular(), disciplina);

			for (HistoricoAluno historicoAluno : inscDisciplina) {
				hSimples.setAnoLectivo(historicoAluno.getAnoLectivo().getAnoLectivo());
				hSimples.setAnoLectivoDescricao((historicoAluno.getAnoLectivo().getAnoLectivoDescricao()));
				hSimples.setClassificacao(historicoAluno.getNotaFinal());

				if (!historicoAluno.isFechada())
					hSimples.setObs("n.v.");
			}
			alunosHistorico.add(hSimples);
		}

		hm.setHistoricoAluno(alunosHistorico);

		ResponseCliente c = new ResponseCliente();
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(hm);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorAnoAndEmpresa", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorAnoLectivoAndEmpresaBolsa(
			@RequestBody EmpresaAnoLectivoCliente busca) {
		ResponseCliente c = new ResponseCliente();
		AnoLectivo ano = this.repositoryAnoLectivo.findOne(busca.getAnoLectivo());
		EmpresaConvenio empresa = this.empresaConvenioRepository.findOne(busca.getEmpresa());
		ContaCorrenteEmpresa contaPesquisada = this.contaCorrenteEmpresaRepo.buscarEmpresa(busca.getEmpresa());
		
		System.out.println("Gato " + contaPesquisada);

		AlunoResumoCliente aluno;
		List<AlunoResumoCliente> alunos = new ArrayList<AlunoResumoCliente>();
		List<Matricula> matriculas = this.repositoryMatricula.findByEmpresaConvenioAndAnoLectivo(empresa, ano);
		ConvenioCliente convenio = new ConvenioCliente();

		for (Matricula matricula : matriculas) {
			aluno = new AlunoResumoCliente();
			aluno.setId(Integer.parseInt(matricula.getAluno().getNumeroDeAluno()));
			aluno.setNome(matricula.getAluno().getNome());

			aluno.setCurso(matricula.getCurso().getDescricao());

			alunos.add(aluno);
		}
		
		System.out.println("Não está a chegar aqui");
		convenio.setEmpresa(empresa.getId());
		convenio.setAnoLectivo(ano.getId());
		convenio.setBolseiros(alunos);
		convenio.setValor(contaPesquisada.getValor());
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(convenio);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarMatriculasDoAluno", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarMatriculas(@RequestBody BuscaAluno busca) {
		ResponseCliente c = new ResponseCliente();
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(busca.getNumero());

		// this.this.permissaoCursoRepository.findBy

		List<Matricula> matriculas = this.repositoryMatricula.findByAluno(aluno);
		if (matriculas.isEmpty()) {
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem("Nenhum registro encontrado!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		PermissaoCurso permissao = null;
		List<Matricula> ms = new ArrayList<Matricula>();
		for (Matricula matricula : matriculas) {
			permissao = this.permissaoCursoRepository.findByCursoAndUsuarioUserName(aluno.getCurso(),
					busca.getUserName());

			if (permissao != null)
				matricula.setPermissao(permissao.getPermissao());
			ms.add(matricula);
		}

		if (permissao != null) {
			c.setPermissao(permissao.getPermissao());
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(ms);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@PostMapping("/buscarMatriculasCcdDoAluno")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarMatriculasCCDDoAluno(@RequestBody BuscaAluno busca) {
		List<MatriculaCcd> matriculas = this.repositoryMatriculaCcd.findByAlunoNumeroDeAluno(busca.getNumero());
		if (matriculas.isEmpty()) 
			return this.httpResponse.MensagemDeRetorno(2,"Nenhum registro encontrado!");

		List<MatriculaCcd> ms = new ArrayList<MatriculaCcd>();
		matriculas.forEach(matricula->{
			PermissaoCurso permissao = this.permissaoCursoRepository.findByCursoAndUsuarioUserName(matricula.getAluno().getCurso(),busca.getUserName());
				matricula.setPermissao(permissao.getPermissao());
			ms.add(matricula);
		});
		
		return this.httpResponse.MensagemDeRetorno(0,null, ms);
	}
	
	
//	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
//	@CrossOrigin(origins = "*")
//	@ResponseBody
//	@Transactional
//	public ResponseEntity<ResponseCliente> salvar(@RequestBody MatriculaRecebida efetuarMatricula) {
//		 String numeroGuia;
//		//efetuarMatricula.getTipoInscricao()==5
//		
//		ResponseCliente c = new ResponseCliente();
//		String mensagem = "";
//		Matricula matricula = new Matricula();
//		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(efetuarMatricula.getNumeroDeAluno().toString());
//		if (aluno.isInscrito()) {
//			throw new DadoInvalidoException("Este aluno já se encontra inscrito.");
//		} else {
//			PlanoPagamento planoPagamento = this.planoPagamentoRepository.findOne(1);
//			//
//			Curso curso = aluno.getCurso().getId() != null ? this.cursoRepository.findById(aluno.getCurso().getId())
//					: null;
//
//			TipoInscricao tipoInscricao = efetuarMatricula.getTipoInscricao() != null
//					? this.tipoInscricaoRepository.findOne(efetuarMatricula.getTipoInscricao())
//					: null;
//			//
//			AnoLectivo anoLectivoAtivo = this.anoLectivoService.anoLectivo(efetuarMatricula.getAnoLectivo());
//			//
//			Turma turmaBase = efetuarMatricula.getTurmaBase() != null
//					? this.turmaRepository.findOne(efetuarMatricula.getTurmaBase())
//					: null;
//			Usuario usuario = this.usuarioRepository
//					.findByUserName(efetuarMatricula.getUserName() != null ? efetuarMatricula.getUserName() : null);
//
//			List<InputDisciplinaViewModel> disciplinaTurma = efetuarMatricula.getDisciplinaTurma();
//			Matricula existeMatricula = this.repositoryMatricula.findByAlunoAndAnoLectivoAndCursoAndTurmaBase(aluno,anoLectivoAtivo, curso,turmaBase); 
//			Matricula matriculAdanula  = this.repositoryMatricula.findByAlunoAndAnoLectivoAndCursoAndTurmaBase(aluno,anoLectivoAtivo, curso,turmaBase);
//			if(existeMatricula != null) {
//				throw new DadoInvalidoException("Já existe uma matricula este ano");
//			}else {
//				existeMatricula = this.repositoryMatricula.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivoAtivo, false);
//				if(existeMatricula != null)
//					throw new DadoInvalidoException("Já existe uma matricula este ano");
//			}
//
//			// VERIFICAR OS DADOS RECEBIDOS. MEXI
//			System.err.println("TIPO DE INSCRIÇÃO É: "+efetuarMatricula.getTipoInscricao());
//			List<Disciplina> disciplinasCurso = this.repositoryDisciplina.findByCursoAndAnoCorricularAndStatus(curso,efetuarMatricula.getAnoMatricula(), true);
//			if (efetuarMatricula.getTipoInscricao() != 4 || efetuarMatricula.getTipoInscricao()!=5) {
//				Map<Integer, DisciplinaInscricaoControle> mapa = new HashMap<Integer, DisciplinaInscricaoControle>();
//                System.err.println("COMO PODE O CAMARADA ESTAR A ENTRAR AQUI.... ?");
//				DisciplinaInscricaoControle controle;
//				for (InputDisciplinaViewModel o : disciplinaTurma) {
//					controle = new DisciplinaInscricaoControle();
//					controle.setId(o.getId());
//					mapa.put(o.getId(), controle);
//				}
//				DisciplinaInscricaoControle dControle = new DisciplinaInscricaoControle();
//				List<DisciplinaInscricaoControle> dsControle = new ArrayList<DisciplinaInscricaoControle>();
//				for (Disciplina o : disciplinasCurso) {
//					dControle = new DisciplinaInscricaoControle();
//					DisciplinaInscricaoControle encontradoDisc = mapa.get(o.getId());
//
//					if (encontradoDisc != null) {
//						dControle.setId(o.getId());
//						dControle.setVerificada(true);
//					} else {
//						dControle.setId(o.getId());
//						dControle.setVerificada(false);
//					}
//					dsControle.add(dControle);
//				}
//				// VERIFICAR
//				for (DisciplinaInscricaoControle o : dsControle) {
//					if (!o.isVerificada()) {
//						c.setCodigo(ResponseCode.values()[3].getDescricao());
//						c.setMensagem("Seleciona todas as disciplinas do " + efetuarMatricula.getAnoMatricula() + "º Ano");
//						//return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
//					}
//				}
//			}
//
//			//FAZ A VALIDAÇÃO DAS DISCIPLINAS ABAIXO. MEXI
//			if (efetuarMatricula.getTipoInscricao() == 4 && efetuarMatricula.getTipoInscricao()==5) {
//				List<HistoricoAluno> matriculasDoAluno = this.repositoryHistoricoAluno.findByAlunoAndCursoAndAprovadoAndDisciplinaStatus(aluno, aluno.getCurso(), true, true);
//
//				Map<Integer, HistoricoAluno> mapa = new HashMap<Integer, HistoricoAluno>();
//				for (HistoricoAluno o : matriculasDoAluno) {
//					if (!mapa.containsKey(o.getDisciplina().getId())) {
//						if (o.getNotaFinal() != null) {
//							if (o.getNotaFinal() >= 10)
//								mapa.put(o.getDisciplina().getId(), o);
//						}
//					}
//				}
//
//				Map<Integer, HistoricoEquivalencia> mapaE = new HashMap<Integer, HistoricoEquivalencia>();
//				// BUSCA O HISTORICO DE EQUIVALENCIAS
//				List<HistoricoEquivalencia> equivalencia = this.equivalenciaHistoricoRepository.findByAluno(aluno);
//				for (HistoricoEquivalencia o : equivalencia) {
//					if (!mapa.containsKey(o.getDisciplinaDestino().getId())) {
//						mapaE.put(o.getDisciplinaDestino().getId(), o);
//					}
//				}
//
//				List<HistoricoAlunoCliente> alunosCliente = new ArrayList<HistoricoAlunoCliente>();
//
//				HistoricoAlunoCliente cliente;
//
//				// busca todas as disciplinas activas de um curso
//				List<Disciplina> disciplinas = this.repositoryDisciplina.findByCursoAndStatus(aluno.getCurso(), true);
//				HistoricoAluno buscaHistorico;
//				HistoricoEquivalencia historicoEquivalencia;
//				for (Disciplina o : disciplinas) {
//					cliente = new HistoricoAlunoCliente();
//					buscaHistorico = mapa.get(o.getId());
//					historicoEquivalencia = mapaE.get(o.getId());
//
//					cliente.setAnoCorricular(o.getAnoCorricular());
//					cliente.setDisciplina(o.getDescricao());
//					cliente.setCodigoDisciplina(o.getId());
//
//					if (buscaHistorico != null) {
//						cliente.setAnoLectivo(buscaHistorico.getAnoLectivo().getAnoLectivo());
//						cliente.setAprovado(buscaHistorico.isAprovado());
//						cliente.setNotaFinal(buscaHistorico.getNotaFinal());
//						cliente.setSituacao(buscaHistorico.getSituacao());
//						cliente.setTipoDisciplina(buscaHistorico.getDisciplina().getTipo());
//						cliente.setTurma(buscaHistorico.getTurma().getTurma());
//					} else if (historicoEquivalencia != null) {
//						if (historicoEquivalencia.getNotaOrigem() >= 10) {
//							cliente.setAnoLectivo(
//									historicoEquivalencia.getEquivalencia().getAnoLectivo().getAnoLectivo());
//							cliente.setAprovado(true);
//							cliente.setNotaFinal(historicoEquivalencia.getNotaOrigem());
//							cliente.setSituacao(Situacao.APROVADO);
//							cliente.setTipoDisciplina(historicoEquivalencia.getDisciplinaDestino().getTipo());
//							cliente.setTurma("EQ");
//						}
//					}
//					alunosCliente.add(cliente);
//				}
//
//				DisciplinaInscricaoControle verifique;
//				List<DisciplinaInscricaoControle> verificarDisciplina = new ArrayList<DisciplinaInscricaoControle>();
//
//				for (HistoricoAlunoCliente o : alunosCliente) {
//					verifique = new DisciplinaInscricaoControle();
//					if (!o.isAprovado() && o.getAnoCorricular() <= efetuarMatricula.getAnoMatricula()) {
//						// System.err.println(o.getDisciplina()+" - CODIGO: "+o.getCodigoDisciplina());
//						// SETA OS VALORES
//						verifique.setId(o.getCodigoDisciplina());
//						verifique.setVerificada(false);
//						verifique.setAnoCorricular(o.getAnoCorricular());
//						// ADICIONA NO MAPA
//						// mapaR.put(o.getCodigoDisciplina(), verifique);
//						// ADICIONA NO ARRAY
//						verificarDisciplina.add(verifique);
//					}
//				}
//				Disciplina disciplina;
//
//				boolean temUmaAcima = false;
//				Map<Integer, DisciplinaInscricaoControle> mapaR = new HashMap<Integer, DisciplinaInscricaoControle>();
//
//				for (InputDisciplinaViewModel o : disciplinaTurma) {
//					disciplina = this.repositoryDisciplina.findOne(o.getId());
//					if (disciplina.getAnoCorricular() > efetuarMatricula.getAnoMatricula()) {
//						/*
//						 * if(disciplina.getTipo()==TipoDisciplina.PRIMEIRO_SEMESTRE) {
//						 * temUmaAcima=true;
//						 * 
//						 * }
//						 */
//					}
//					verifique = new DisciplinaInscricaoControle();
//
//					verifique.setAnoCorricular(disciplina.getAnoCorricular());
//					verifique.setId(o.getId());
//
//					mapaR.put(o.getId(), verifique);
//				}
//
//				// System.err.println("TOTAL DE ELEMENTO NO MAPA: "+mapaR.size());
//				DisciplinaInscricaoControle maps;
//
//				temUmaAcima = false;
//				if (temUmaAcima) {
//					for (DisciplinaInscricaoControle o : verificarDisciplina) {
//						// System.err.println("CODIGO MORSE: "+o.getId());
//						maps = mapaR.get(o.getId());
//						if (maps == null && o.getAnoCorricular() <= efetuarMatricula.getAnoMatricula()) {
//							c.setCodigo(ResponseCode.values()[3].getDescricao());
//							c.setMensagem("verifique as disciplinas abaixo.");
//							return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
//						}
//					}
//				}
//			}
//
//			
//			//VALIDA QUANTIDADE DE DISCIPLINA PARA O TIPO DE INSCRIÇÃO POR DISCIPLINAS
//			if (efetuarMatricula.getTipoInscricao() == 4) {
//				if (efetuarMatricula.getDisciplinaTurma().size() > 12) {
//					c.setCodigo(ResponseCode.values()[3].getDescricao());
//					c.setMensagem("Excedeu o número de disciplinas.");
//					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
//				}
//			}
//
//            //VERIFICA SE É BOLSEIRO E APLICA			
//			@SuppressWarnings("unused")
//			EmpresaConvenio empresaConvenio = null;
//			if (efetuarMatricula.isBolseiro()) {
//				empresaConvenio = this.empresaConvenioRepository.findOne(
//						efetuarMatricula.getEmpresaConvenio() != null ? efetuarMatricula.getEmpresaConvenio() : null);
//			}
//
//			//VALIDA O ANO CORRICULAR
//			if (efetuarMatricula.getAnoMatricula() == null) {
//				c.setCodigo(ResponseCode.values()[3].getDescricao());
//				c.setMensagem("Verifique o ano curricular do aluno: ERRO-01");
//				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
//			}
//			//BUSCA TODOS OS INSCRITOS NESTA TURMA
//			List<Matricula> liberar = this.repositoryMatricula.findByAnoCurricularAndAnoLectivoAndCursoAndTurmaBaseAndAnulado(efetuarMatricula.getAnoMatricula(),
//							anoLectivoAtivo, curso, turmaBase, false);
//
//			
//			
//			//BUSCA A TURMA
//			TurmaDisponivel tDisponivel = this.turmaDisponiveisRepository.findByCursoAndTurmaAndAno(curso, turmaBase,efetuarMatricula.getAnoMatricula());
//
//			//VERIFICA SE A TURMA ESTÁ PODENDO RECEBER MAIS ALUNOS
//			if (liberar.size() >= tDisponivel.getCapacidade()) {
//				c.setCodigo(ResponseCode.values()[3].getDescricao());
//				c.setMensagem("A turma selecionada já está cheia - " + liberar.size() + "Inscrições");
//				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
//			}
//
//			//IMPORTANTE - VERIFICA SE EXISTE AS DISCIPLINAS
//			if (disciplinaTurma.isEmpty()) {
//				c.setCodigo(ResponseCode.values()[3].getDescricao());
//				c.setMensagem("Verifique as disciplinas: ERRO-02");
//				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
//			}
//
//			
//			List<Matricula> buscarMatriculaDoAluno = repositoryMatricula.findByAluno(aluno);
//			// GERA A GUIA DE INSCRICAO E SE O ALUNO TIVER ANULADO A MATRICULA É GERADO A GUIA DE REABERTURA DE INSCRIÇÃO
//			Emolumento emolumento = this.emolumentoRepository.findByCodigo(7);
//			EmolumentoHistorico histEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento, curso, anoLectivoAtivo);
//			
//
//			if (efetuarMatricula.getAnoMatricula() == 2 && curso.getGrau() == Grau.MESTRADO) {
//				// 335
//				emolumento = this.emolumentoRepository.findOne(335);
//				histEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
//						curso, anoLectivoAtivo);
//			}
//			
//	        Instituicao instituicao = this.instituicaoRepository.findOne(2); 
//			Moeda moeda = moedaRepository.findOne(3);
//			
//			//GuiaPagamentoHistorico guiaHistorico;
//		    GuiaPagamentoHistorico gHistorico ;	
//			Guia guia = new Guia();			
//			  guia.setAcordo(false); 
//			  guia.setAnulada(false);
//			  guia.setLiquidada(false);
//			  guia.setGerouCredito(false); 
//			  guia.setGeradaReferencia(false);
//			  guia.setGeradaOnline(false);			  
//			  guia.setAluno(aluno);
//			  guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
//			  guia.setAutomaticamente(true); 
//			  guia.setDataEmicao(new Date());
//			  guia.setLiquidada(false); 
//			  guia.setUsuarioEmitiu(usuario);
//			  guia.setMoeda(moeda); 
//			  guia.setInstituicao(instituicao); 			 
//			  guia.setAnoLectivo(anoLectivoAtivo);			  		  
//			  guia.setDataVencimento(new Date());
//
//			  List<EmolumentoReaberturaInscricao> cEmolumento = new ArrayList<>();
//			  EmolumentoReaberturaInscricao eReabertura = new EmolumentoReaberturaInscricao(); 
//			  eReabertura.setId(emolumento.getId());
//			  eReabertura.setCodigo(emolumento.getCodigo());
//			  eReabertura.setEmolumento(emolumento.getEmolumento());
//			  eReabertura.setValor(histEmolumento.getValor());
//			  cEmolumento.add(eReabertura);
//			  
//			     existeMatricula = this.repositoryMatricula.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivoAtivo,false);
//			     matriculAdanula = this.repositoryMatricula.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivoAtivo,true);
//			   //findbyanoturmabsaanuldacursoAluno
//				if (existeMatricula != null) { 
//					c.setCodigo(ResponseCode.values()[3].getDescricao());
//					c.setMensagem("Já existe uma matricula este ano");
//					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
//				}
//				//buscarMatriculaDoAluno.		
//			if (matriculAdanula !=null) { 
//					  Emolumento emolumentos = this.emolumentoRepository.findByCodigo(124);
//					  EmolumentoHistorico histEmolumentos = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumentos, curso, anoLectivoAtivo);
//					  EmolumentoReaberturaInscricao EReabertura = new EmolumentoReaberturaInscricao();
//					  EReabertura.setId(emolumentos.getId());
//					  EReabertura.setCodigo(emolumentos.getCodigo());
//					  EReabertura.setEmolumento(emolumentos.getEmolumento());					  
//				      EReabertura.setValor(histEmolumentos.getValor());
//					  cEmolumento.add(EReabertura);
//					 
//			}
//
//			guia.setUltimaModificacao(new Date());
//			Guia guiaSalva = this.guiaRepository.save(guia);
//
//			double valorGuia = 0;
//			for (EmolumentoReaberturaInscricao emluCliente : cEmolumento) {
//				gHistorico = new GuiaPagamentoHistorico();
//				Emolumento emolumentofinal = this.emolumentoRepository.findOne(emluCliente.getId());
//				gHistorico.setEmolumento(emolumentofinal);
//				gHistorico.setGuia(guiaSalva);
//				gHistorico.setValor(emluCliente.getValor());
//				gHistorico.setAluno(aluno);
//				gHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
//				guia.setAnoLectivo(anoLectivoAtivo);
//				valorGuia += emluCliente.getValor();
//				this.historicoGuiaRepository.save(gHistorico);
//				this.guiaService.gerarHistoricoAudit(gHistorico);
//			}
//			String definitivo = "";
//
//			// anoInscricao
//			Integer lectivo = anoLectivoAtivo.getAnoLectivo();
//			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(3);
//			Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
//
//			// metódo gerar numero de guia chamado
//			definitivo = GerarNumeroDeGuia.gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
//			
//			Guia guiaExistente = this.guiaRepository.findByNumeroGuia(definitivo);
//			if (guiaExistente != null) {
//				do {
//					proximoNumeroInteiro++;
//					// metódo gerar numero de guia chamado		
//					definitivo = GerarNumeroDeGuia.gerarNumeroGuia(definitivo, lectivo, proximoNumeroInteiro);
//					guiaExistente = this.guiaRepository.findByNumeroGuia(definitivo);
//				} while (guiaExistente != null);
//			}
//			// setar o numero da guia
//			guiaSalva.setNumeroGuia(definitivo);
//
//			// atualizador os dados da ultima guia e a proxima guia
//			numeroGerado.setUltimoNumero(proximoNumeroInteiro);
//			numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
//			this.numeroGeradoRepository.save(numeroGerado);
//
//			guiaSalva.setValor(valorGuia);
//			guiaSalva.setUltimaModificacao(new Date());
//			Guia salvaDefinitiva = this.guiaRepository.save(guiaSalva);
//			numeroGuia = salvaDefinitiva.getNumeroGuia();
//			Guia gSalva = this.guia.save(guia);
//			
//
//			if (!buscarMatriculaDoAluno.isEmpty()) {
//				emolumento = this.emolumentoRepository.findOne(108);
//				histEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
//						curso, anoLectivoAtivo);
//
//				Contador contador = this.contadorRepository.findOne(32);
//				boolean cobrarMulta = false;
//				if (contador != null) {
//					if (contador.getProximoValor() == 1)
//						cobrarMulta = true;
//				}
//
//				if (histEmolumento != null && aluno.getCurso().getGrau() == Grau.LICENCIATURA && cobrarMulta) {
//					gHistorico = new GuiaPagamentoHistorico();
//					gHistorico.setAluno(aluno);
//					gHistorico.setAnoLectivo(anoLectivoAtivo);
//					gHistorico.setEmolumento(emolumento);
//					gHistorico.setGuia(gSalva);
//					gHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());					
//					gHistorico.setValor(histEmolumento.getValor());					
//					this.historicoGuiaRepository.save(gHistorico);
//					this.guiaService.gerarHistoricoAudit(gHistorico);
//					gSalva.setValor(gSalva.getValor() + histEmolumento.getValor());
//					gSalva.setUltimaModificacao(new Date());
//					this.guia.save(gSalva);
//				}
//			}
//			
//			
//			// PREPARA O RETONO
//			mensagem = buscarMatriculaDoAluno.isEmpty() ? "Matricula feita com sucesso" : "Confirmação de matricula feita com sucesso ";
//			// this.repositoryHistoricoAluno.
//			matricula.setAnoCurricular(efetuarMatricula.getAnoMatricula());
//			matricula.setCurso(curso);
//			matricula.setAluno(aluno);
//			matricula.setAnoLectivo(anoLectivoAtivo);
//			matricula.setTipoInscricao(tipoInscricao);
//			matricula.setData(new Date());
//			matricula.setProcessamentoReferencia(false);
//			// matricula.setEmpresaConvenio(empresaConvenio);
//			matricula.setNumeroDeAluno(aluno.getNumeroDeAluno());
//			matricula.setAnoLectivoMatricula(anoLectivoAtivo.getAnoLectivo().toString());
//			matricula.setPlanoPagamento(planoPagamento);
//			matricula.setTurmaBase(turmaBase);
//			matricula.setUsuarioInscreveu(usuario);
//			matricula.setDataInscricao(new Date());
//			matricula.setPercentagemDesconto(0);
//			matricula.setUltimaModificacao(new Date());
//			//verificado,inscritoOnline
//			matricula.setVerificado(false);
//			matricula.setInscritoOnline(false);
//			
//			Matricula matriculaFeita = repositoryMatricula.save(matricula);
//			this.matriculasService.gerarHistorico(matricula);
//					
//			HistoricoAluno historico;
//			for (InputDisciplinaViewModel busca : disciplinaTurma) {
//				historico = new HistoricoAluno();
//
//				Disciplina disciplina = this.repositoryDisciplina.findOne(busca.getId());
//				Turma turma = turmaRepository.findOne(busca.getIdTurma());
//				historico.setAnoCorricular(disciplina.getAnoCorricular());
//				historico.setTurma(turma);
//				historico.setSituacao(Situacao.CURSANDO);
//				historico.setDisciplina(disciplina);
//				historico.setDataInscricao(new Date());
//				historico.setAluno(aluno);
//				historico.setNumeroDeAluno(aluno.getNumeroDeAluno());
//				historico.setMatricula(matriculaFeita);
//				historico.setAnoLectivo(anoLectivoAtivo);
//				historico.setCurso(curso);
//				historico.setUsuarioInscreveu(usuario);
//
//				// CABEÇALHO DE OBRIGATÓRIEDADE
//				historico.setAprovado(false);
//				historico.setExtraordinaria(false);
//				historico.setPode2epoca(false);
//				historico.setPodeMelhoria(false);
//				historico.setValidada(false);
//				historico.setFechada(false);
//				historico.setPodeEpEsp(false);
//				historico.setSemFrequencia(false);
//
//				List<HistoricoAluno> existe = this.repositoryHistoricoAluno.findByAlunoAndMatriculaAnoLectivoAndDisciplina(aluno, anoLectivoAtivo, disciplina);
//				if (existe.isEmpty()) {
//					historico.setUltimaModificacao(new Date());
//					this.repositoryHistoricoAluno.save(historico);
//					this.historicoAlunoService.gerarHistorico(historico);
//				}
//			}
//
//			aluno.setInscrito(true);
//			aluno.setUltimaModificacao(new Date());
//			this.alunoRepository.save(aluno);
//			this.alunoService.gerarHistorico(aluno);
//			this.matriculasService.gerarHistorico(matriculaFeita);
//
//			c.setCodigo(ResponseCode.values()[0].getDescricao());
//			c.setResultado(matricula);
//			c.setMensagem(mensagem + "!");
//			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
//		}
//	}
//

	@Transactional
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody MatriculaRecebida efetuarMatricula) {
		
		//efetuarMatricula.getTipoInscricao()==5
		
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);
		
		ResponseCliente c = new ResponseCliente();
		String mensagem = "";
		Matricula matricula = new Matricula();
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(efetuarMatricula.getNumeroDeAluno().toString());
		if (aluno.isInscrito()) {
			throw new DadoInvalidoException("Este aluno já se encontra inscrito.");
		} else {
			PlanoPagamento planoPagamento = this.planoPagamentoRepository.findOne(1);
			//
			Curso curso = aluno.getCurso().getId() != null ? this.cursoRepository.findById(aluno.getCurso().getId())
					: null;

			TipoInscricao tipoInscricao = efetuarMatricula.getTipoInscricao() != null
					? this.tipoInscricaoRepository.findOne(efetuarMatricula.getTipoInscricao())
					: null;
			//
			AnoLectivo anoLectivoAtivo = this.anoLectivoService.anoLectivo(efetuarMatricula.getAnoLectivo());
			//
			Turma turmaBase = efetuarMatricula.getTurmaBase() != null
					? this.turmaRepository.findOne(efetuarMatricula.getTurmaBase())
					: null;
			Usuario usuario = this.usuarioRepository
					.findByUserName(efetuarMatricula.getUserName() != null ? efetuarMatricula.getUserName() : null);

			List<InputDisciplinaViewModel> disciplinaTurma = efetuarMatricula.getDisciplinaTurma();
			Matricula existeMatricula = this.repositoryMatricula.findByAlunoAndAnoLectivoAndCursoAndTurmaBase(aluno,anoLectivoAtivo, curso,turmaBase); 
			if(existeMatricula != null) {
				throw new DadoInvalidoException("Já existe uma matricula este ano");
			}else {
				existeMatricula = this.repositoryMatricula.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivoAtivo, false);
				if(existeMatricula != null)
					throw new DadoInvalidoException("Já existe uma matricula este ano");
			}

			// VERIFICAR OS DADOS RECEBIDOS. MEXI
			System.err.println("TIPO DE INSCRIÇÃO É: "+efetuarMatricula.getTipoInscricao());
			List<Disciplina> disciplinasCurso = this.repositoryDisciplina.findByCursoAndAnoCorricularAndStatus(curso,efetuarMatricula.getAnoMatricula(), true);
			if (efetuarMatricula.getTipoInscricao() != 4 || efetuarMatricula.getTipoInscricao()!=5) {
				Map<Integer, DisciplinaInscricaoControle> mapa = new HashMap<Integer, DisciplinaInscricaoControle>();
                System.err.println("COMO PODE O CAMARADA ESTAR A ENTRAR AQUI.... ?");
				DisciplinaInscricaoControle controle;
				for (InputDisciplinaViewModel o : disciplinaTurma) {
					controle = new DisciplinaInscricaoControle();
					controle.setId(o.getId());
					mapa.put(o.getId(), controle);
				}
				DisciplinaInscricaoControle dControle = new DisciplinaInscricaoControle();
				List<DisciplinaInscricaoControle> dsControle = new ArrayList<DisciplinaInscricaoControle>();
				for (Disciplina o : disciplinasCurso) {
					dControle = new DisciplinaInscricaoControle();
					DisciplinaInscricaoControle encontradoDisc = mapa.get(o.getId());

					if (encontradoDisc != null) {
						dControle.setId(o.getId());
						dControle.setVerificada(true);
					} else {
						dControle.setId(o.getId());
						dControle.setVerificada(false);
					}
					dsControle.add(dControle);
				}
				// VERIFICAR
				for (DisciplinaInscricaoControle o : dsControle) {
					if (!o.isVerificada()) {
						c.setCodigo(ResponseCode.values()[3].getDescricao());
						c.setMensagem("Seleciona todas as disciplinas do " + efetuarMatricula.getAnoMatricula() + "º Ano");
						//return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
					}
				}
			}

			//FAZ A VALIDAÇÃO DAS DISCIPLINAS ABAIXO. MEXI
			if (efetuarMatricula.getTipoInscricao() == 4 && efetuarMatricula.getTipoInscricao()==5) {
				List<HistoricoAluno> matriculasDoAluno = this.repositoryHistoricoAluno.findByAlunoAndCursoAndAprovadoAndDisciplinaStatus(aluno, aluno.getCurso(), true, true);

				Map<Integer, HistoricoAluno> mapa = new HashMap<Integer, HistoricoAluno>();
				for (HistoricoAluno o : matriculasDoAluno) {
					if (!mapa.containsKey(o.getDisciplina().getId())) {
						if (o.getNotaFinal() != null) {
							if (o.getNotaFinal() >= 10)
								mapa.put(o.getDisciplina().getId(), o);
						}
					}
				}

				Map<Integer, HistoricoEquivalencia> mapaE = new HashMap<Integer, HistoricoEquivalencia>();
				// BUSCA O HISTORICO DE EQUIVALENCIAS
				List<HistoricoEquivalencia> equivalencia = this.equivalenciaHistoricoRepository.findByAluno(aluno);
				for (HistoricoEquivalencia o : equivalencia) {
					if (!mapa.containsKey(o.getDisciplinaDestino().getId())) {
						mapaE.put(o.getDisciplinaDestino().getId(), o);
					}
				}

				List<HistoricoAlunoCliente> alunosCliente = new ArrayList<HistoricoAlunoCliente>();

				HistoricoAlunoCliente cliente;

				// busca todas as disciplinas activas de um curso
				List<Disciplina> disciplinas = this.repositoryDisciplina.findByCursoAndStatus(aluno.getCurso(), true);
				HistoricoAluno buscaHistorico;
				HistoricoEquivalencia historicoEquivalencia;
				for (Disciplina o : disciplinas) {
					cliente = new HistoricoAlunoCliente();
					buscaHistorico = mapa.get(o.getId());
					historicoEquivalencia = mapaE.get(o.getId());

					cliente.setAnoCorricular(o.getAnoCorricular());
					cliente.setDisciplina(o.getDescricao());
					cliente.setCodigoDisciplina(o.getId());

					if (buscaHistorico != null) {
						cliente.setAnoLectivo(buscaHistorico.getAnoLectivo().getAnoLectivo());
						cliente.setAprovado(buscaHistorico.isAprovado());
						cliente.setNotaFinal(buscaHistorico.getNotaFinal());
						cliente.setSituacao(buscaHistorico.getSituacao());
						cliente.setTipoDisciplina(buscaHistorico.getDisciplina().getTipo());
						cliente.setTurma(buscaHistorico.getTurma().getTurma());
					} else if (historicoEquivalencia != null) {
						if (historicoEquivalencia.getNotaOrigem() >= 10) {
							cliente.setAnoLectivo(
									historicoEquivalencia.getEquivalencia().getAnoLectivo().getAnoLectivo());
							cliente.setAprovado(true);
							cliente.setNotaFinal(historicoEquivalencia.getNotaOrigem());
							cliente.setSituacao(Situacao.APROVADO);
							cliente.setTipoDisciplina(historicoEquivalencia.getDisciplinaDestino().getTipo());
							cliente.setTurma("EQ");
						}
					}
					alunosCliente.add(cliente);
				}

				DisciplinaInscricaoControle verifique;
				List<DisciplinaInscricaoControle> verificarDisciplina = new ArrayList<DisciplinaInscricaoControle>();

				for (HistoricoAlunoCliente o : alunosCliente) {
					verifique = new DisciplinaInscricaoControle();
					if (!o.isAprovado() && o.getAnoCorricular() <= efetuarMatricula.getAnoMatricula()) {
						// System.err.println(o.getDisciplina()+" - CODIGO: "+o.getCodigoDisciplina());
						// SETA OS VALORES
						verifique.setId(o.getCodigoDisciplina());
						verifique.setVerificada(false);
						verifique.setAnoCorricular(o.getAnoCorricular());
						// ADICIONA NO MAPA
						// mapaR.put(o.getCodigoDisciplina(), verifique);
						// ADICIONA NO ARRAY
						verificarDisciplina.add(verifique);
					}
				}
				Disciplina disciplina;

				boolean temUmaAcima = false;
				Map<Integer, DisciplinaInscricaoControle> mapaR = new HashMap<Integer, DisciplinaInscricaoControle>();

				for (InputDisciplinaViewModel o : disciplinaTurma) {
					disciplina = this.repositoryDisciplina.findOne(o.getId());
					if (disciplina.getAnoCorricular() > efetuarMatricula.getAnoMatricula()) {
						/*
						 * if(disciplina.getTipo()==TipoDisciplina.PRIMEIRO_SEMESTRE) {
						 * temUmaAcima=true;
						 * 
						 * }
						 */
					}
					verifique = new DisciplinaInscricaoControle();

					verifique.setAnoCorricular(disciplina.getAnoCorricular());
					verifique.setId(o.getId());

					mapaR.put(o.getId(), verifique);
				}

				// System.err.println("TOTAL DE ELEMENTO NO MAPA: "+mapaR.size());
				DisciplinaInscricaoControle maps;

				temUmaAcima = false;
				if (temUmaAcima) {
					for (DisciplinaInscricaoControle o : verificarDisciplina) {
						// System.err.println("CODIGO MORSE: "+o.getId());
						maps = mapaR.get(o.getId());
						if (maps == null && o.getAnoCorricular() <= efetuarMatricula.getAnoMatricula()) {
							c.setCodigo(ResponseCode.values()[3].getDescricao());
							c.setMensagem("verifique as disciplinas abaixo.");
							return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
						}
					}
				}
			}

			
			//VALIDA QUANTIDADE DE DISCIPLINA PARA O TIPO DE INSCRIÇÃO POR DISCIPLINAS
			if (efetuarMatricula.getTipoInscricao() == 4) {
				if (efetuarMatricula.getDisciplinaTurma().size() > 12) {
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					c.setMensagem("Excedeu o número de disciplinas.");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}

            //VERIFICA SE É BOLSEIRO E APLICA			
			@SuppressWarnings("unused")
			EmpresaConvenio empresaConvenio = null;
			if (efetuarMatricula.isBolseiro()) {
				empresaConvenio = this.empresaConvenioRepository.findOne(
						efetuarMatricula.getEmpresaConvenio() != null ? efetuarMatricula.getEmpresaConvenio() : null);
			}

			//VALIDA O ANO CORRICULAR
			if (efetuarMatricula.getAnoMatricula() == null) {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setMensagem("Verifique o ano curricular do aluno: ERRO-01");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			//BUSCA TODOS OS INSCRITOS NESTA TURMA
			List<Matricula> liberar = this.repositoryMatricula.findByAnoCurricularAndAnoLectivoAndCursoAndTurmaBaseAndAnulado(efetuarMatricula.getAnoMatricula(),
							anoLectivoAtivo, curso, turmaBase, false);

			
			//BUSCA A TURMA
			TurmaDisponivel tDisponivel = this.turmaDisponiveisRepository.findByCursoAndTurmaAndAno(curso, turmaBase,efetuarMatricula.getAnoMatricula());

			//VERIFICA SE A TURMA ESTÁ PODENDO RECEBER MAIS ALUNOS
			if (liberar.size() >= tDisponivel.getCapacidade()) {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setMensagem("A turma selecionada já está cheia - " + liberar.size() + "Inscrições");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			//IMPORTANTE - VERIFICA SE EXISTE AS DISCIPLINAS
			if (disciplinaTurma.isEmpty()) {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setMensagem("Verifique as disciplinas: ERRO-02");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			
			
			
			
			
			
			
			List<Matricula> buscarMatriculaDoAluno = repositoryMatricula.findByAluno(aluno);
			// GERA A GUIA DE INSCRICAO
			Emolumento emolumento = this.emolumentoRepository.findByCodigo(7);
			EmolumentoHistorico histEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
					curso, anoLectivoAtivo);

			if (efetuarMatricula.getAnoMatricula() == 1 && curso.getGrau() == Grau.POSGRADUACAO) {
				
				emolumento = this.emolumentoRepository.findByCodigo(7);
				histEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						curso, anoLectivoAtivo);
			}
			
			if (efetuarMatricula.getAnoMatricula() == 2 && curso.getGrau() == Grau.MESTRADO) {
				// 335
				emolumento = this.emolumentoRepository.findOne(335);
				histEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						curso, anoLectivoAtivo);
			}

			
			
			Moeda moeda = this.moedaRepository.findOne(3);
			Instituicao instituicao = this.instituicaoRepository.findOne(2);
			Guia guia = new Guia();
			guia.setAcordo(false);
			guia.setAnulada(false);
			guia.setLiquidada(false);
			guia.setAlterada(false);
			guia.setGerouCredito(false);
			guia.setGeradaReferencia(false);
			guia.setGeradaOnline(false);
			guia.setParaAcordoPagamento(false);
			guia.setLiquidacaoAGT(false);
			guia.setLiquidacaoCredito(false);
			guia.setDataSistema(dataSistema);
			
			guia.setAluno(aluno);
			guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
			guia.setAutomaticamente(true);
			guia.setDataEmicao(new Date());
			guia.setLiquidada(false);
			guia.setUsuarioEmitiu(usuario);
			guia.setMoeda(moeda);
			guia.setInstituicao(instituicao);
			guia.setUsuarioEmitiu(new Usuario(59));
			// guia.setEmolumento(emolumento);
			guia.setAnoLectivo(anoLectivoAtivo);
			if(histEmolumento != null) {
				guia.setValor(histEmolumento.getValor());
			}

			guia.setDataVencimento(new Date());
			

			existeMatricula = this.repositoryMatricula.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivoAtivo,false);
			if (existeMatricula != null) {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setMensagem("Já existe uma matricula este ano");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			guia.setUltimaModificacao(new Date());
			this.guia.save(guia);

			String definitivo = "";
			Integer lectivo = anoLectivoAtivo.getAnoLectivo();
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
			GuiaCandidatura proformaCandidaturaExistente = guiaCandidaturaRepo.buscarProforma(numero);
			if (proformaExiste != null || proformaCandidaturaExistente != null) {
				do {
					proximoNumero++;
					
					numero =  gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
					proformaExiste = this.guiaRepository.findProforma(numero);
					proformaCandidaturaExistente = guiaCandidaturaRepo.buscarProforma(numero);
				} while (proformaExiste != null || proformaCandidaturaExistente != null);
			}
			// setar o valor da guia
			guia.setNumeroGuia(definitivo);
			guia.setNumeroFacturaProforma(numero);
			guia.setTipoFactura(TipoFatura.FACTURA_PROFORMA);
			
			guia.setUltimaModificacao(new Date());
			// final geração do número da guia
			Guia gSalva = this.guia.save(guia);
			
			this.gerarDocService.gerarFileProformaAluno(gSalva);
			
			numeroGeradoFP.setUltimoNumero(proximoNumero);
			numeroGeradoFP.setProximoNumero(proximoNumero + 1);
			this.numeroGeradoRepository.save(numeroGeradoFP);

			// atualizador os dados da ultima guia e a proxima guia
			numeroGerado.setUltimoNumero(proximoNumeroInteiro);
			numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
			this.numeroGeradoRepository.save(numeroGerado);
			
			double valorComIva = 0;
			double valorImposto = 0;
			
			valorImposto = (histEmolumento.getValor() * emolumento.getPercentagemIva()) / 100;
			valorComIva = histEmolumento.getValor() + valorImposto;
			
			double valorTotalIvaDesconto = (valorComIva - (histEmolumento.getValor() * emolumento.getPercentagemDesconto()) / 100);
			double desconto = (histEmolumento.getValor() * emolumento.getPercentagemDesconto()) / 100;
			
			GuiaPagamentoHistorico gHistorico = new GuiaPagamentoHistorico();

			gHistorico.setValorTotal(histEmolumento.getValor());
			gHistorico.setQuantidade("1");
			gHistorico.setCodigoIva(emolumento.getCodigoIva());
			gHistorico.setPercentagemIva(emolumento.getPercentagemIva().toString());
			gHistorico.setValorImposto(FormataData.formatarValor(valorImposto));
			gHistorico.setDesconto(FormataData.formatarValor(desconto));
			gHistorico.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));
			gHistorico.setAluno(aluno);
			gHistorico.setAnoLectivo(anoLectivoAtivo);
			gHistorico.setEmolumento(emolumento);
			gHistorico.setGuia(gSalva);
			gHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
			if(histEmolumento != null) {
				gHistorico.setValor(histEmolumento.getValor());
			}
			

			this.historicoGuiaRepository.save(gHistorico);
			this.guiaService.gerarHistoricoAudit(gHistorico);
			if (!buscarMatriculaDoAluno.isEmpty()) {
				emolumento = this.emolumentoRepository.findOne(108);
				histEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						curso, anoLectivoAtivo);

				Contador contador = this.contadorRepository.findOne(32);
				boolean cobrarMulta = false;
				if (contador != null) {
					if (contador.getProximoValor() == 1)
						cobrarMulta = true;
				}

				if (histEmolumento != null && aluno.getCurso().getGrau() == Grau.LICENCIATURA && cobrarMulta) {
					gHistorico = new GuiaPagamentoHistorico();
					gHistorico.setAluno(aluno);
					gHistorico.setAnoLectivo(anoLectivoAtivo);
					gHistorico.setEmolumento(emolumento);
					gHistorico.setGuia(gSalva);
					gHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
					gHistorico.setValor(histEmolumento.getValor());
					gHistorico.setQuantidade("1");
					gHistorico.setCodigoIva(emolumento.getCodigoIva());
					gHistorico.setPercentagemIva(emolumento.getPercentagemIva().toString());
					this.historicoGuiaRepository.save(gHistorico);
					this.guiaService.gerarHistoricoAudit(gHistorico);
					gSalva.setValor(gSalva.getValor() + histEmolumento.getValor());
					gSalva.setUltimaModificacao(new Date());
					this.guia.save(gSalva);
				}
			}
			
			
			// PREPARA O RETONO
			mensagem = buscarMatriculaDoAluno.isEmpty() ? "Matricula feita com sucesso" : "Confirmação de matricula feita com sucesso ";
			// this.repositoryHistoricoAluno.
			matricula.setAnoCurricular(efetuarMatricula.getAnoMatricula());
			matricula.setCurso(curso);
			matricula.setAluno(aluno);
			matricula.setAnoLectivo(anoLectivoAtivo);
			matricula.setTipoInscricao(tipoInscricao);
			matricula.setData(new Date());
			matricula.setProcessamentoReferencia(false);
			// matricula.setEmpresaConvenio(empresaConvenio);
			matricula.setNumeroDeAluno(aluno.getNumeroDeAluno());
			matricula.setAnoLectivoMatricula(anoLectivoAtivo.getAnoLectivo().toString());
			matricula.setPlanoPagamento(planoPagamento);
			matricula.setTurmaBase(turmaBase);
			matricula.setUsuarioInscreveu(usuario);
			matricula.setDataInscricao(new Date());
			matricula.setPercentagemDesconto(0);
			matricula.setUltimaModificacao(new Date());
			//verificado,inscritoOnline
			matricula.setVerificado(false);
			matricula.setInscritoOnline(false);
			
			Matricula matriculaFeita = repositoryMatricula.save(matricula);
			this.matriculasService.gerarHistorico(matricula);
			
			
			HistoricoAluno historico;
			for (InputDisciplinaViewModel busca : disciplinaTurma) {
				historico = new HistoricoAluno();

				Disciplina disciplina = this.repositoryDisciplina.findOne(busca.getId());

				Turma turma = turmaRepository.findOne(busca.getIdTurma());

				historico.setAnoCorricular(disciplina.getAnoCorricular());
				historico.setTurma(turma);
				historico.setSituacao(Situacao.CURSANDO);
				historico.setDisciplina(disciplina);
				historico.setDataInscricao(new Date());
				historico.setAluno(aluno);
				historico.setNumeroDeAluno(aluno.getNumeroDeAluno());
				historico.setMatricula(matriculaFeita);
				historico.setAnoLectivo(anoLectivoAtivo);
				historico.setCurso(curso);
				historico.setUsuarioInscreveu(usuario);

				// CABEÇALHO DE OBRIGATÓRIEDADE
				historico.setAprovado(false);
				historico.setExtraordinaria(false);
				historico.setPode2epoca(false);
				historico.setPodeMelhoria(false);
				historico.setValidada(false);
				historico.setFechada(false);
				historico.setPodeEpEsp(false);
				historico.setSemFrequencia(false);

				List<HistoricoAluno> existe = this.repositoryHistoricoAluno.findByAlunoAndMatriculaAnoLectivoAndDisciplina(aluno, anoLectivoAtivo, disciplina);
				if (existe.isEmpty()) {
					historico.setUltimaModificacao(new Date());
					this.repositoryHistoricoAluno.save(historico);
					this.historicoAlunoService.gerarHistorico(historico);
				}
			}

			aluno.setInscrito(true);
			aluno.setUltimaModificacao(new Date());
			this.alunoRepository.save(aluno);
			this.alunoService.gerarHistorico(aluno);
			this.matriculasService.gerarHistorico(matriculaFeita);

			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(matricula);
			c.setMensagem(mensagem + "!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
	
	
	
	@RequestMapping(value = "/salvarColegio", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvarColegio(@RequestBody MatriculaColegioViewModel efetuarMatricula) {
		ResponseCliente c = new ResponseCliente();
		
		Aluno aluno                   = this.alunoRepository.findByNumeroDeAluno(efetuarMatricula.getNumeroDeAluno().toString());
		Curso curso                   = this.cursoRepository.findById(efetuarMatricula.getCurso());
		Turma turma                   = this.turmaRepository.findOne(efetuarMatricula.getTurma());
		PlanoPagamento planoPagamento = this.planoPagamentoRepository.findOne(efetuarMatricula.getPlanoPagamento());
		AnoLectivo anoLectivo         = this.anoLectivoService.anoLectivo();
		
		Usuario usuario = this.usuarioRepository.findByUserCode(efetuarMatricula.getUserCode());
		
		Matricula mExiste = this.repositoryMatricula.findByAlunoAndAnoLectivo(aluno, anoLectivo);
		
		if(mExiste!=null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Este aluno já está matriculado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		
		Matricula m                   = new Matricula();
		m.setAluno(aluno);
		m.setAnoLectivo(anoLectivo);
		m.setAnoLectivoMatricula(anoLectivo.getAnoLectivo().toString());
		m.setAnulado(false);
		m.setCurso(curso);
		m.setData(new Date());
		m.setDataAlteracao(new Date());
		m.setNumeroDeAluno(aluno.getNumeroDeAluno());
		m.setPercentagemDesconto(0);
		m.setPercentagemDesconto(efetuarMatricula.getPercentagemDesconto());
		m.setProcessamentoReferencia(false);
		m.setTurmaBase(turma);
		m.setPlanoPagamento(planoPagamento);
		m.setVerificado(false);
		m.setInscritoOnline(false);
		
		Matricula matricula = this.repositoryMatricula.save(m);
		
		List<Disciplina> disciplinas = this.repositoryDisciplina.findByCursoAndAnoCorricular(curso,1);
		
		
		MatriculaConfirmadaViewModel model=new MatriculaConfirmadaViewModel();
		List<DisciplinaClienteColegio> dsM=new ArrayList<DisciplinaClienteColegio>();
		DisciplinaClienteColegio ds;
		
		
		HistoricoAluno ha;
		for (Disciplina o : disciplinas) {
			 ha=new HistoricoAluno();
			 ds=new DisciplinaClienteColegio();
			 
			 ha.setMatricula(matricula);
			 ha.setAluno(aluno);
			 ha.setNumeroDeAluno(aluno.getNumeroDeAluno());
			 ha.setTurma(turma);
			 ha.setAnoLectivo(anoLectivo);
			 ha.setCurso(curso);
			 ha.setDisciplina(o);
			 ha.setAprovado(false);
			 ha.setExtraordinaria(false);
			 ha.setFechada(false);
			 ha.setPode2epoca(false);
			 ha.setPodeEpEsp(false);
			 ha.setPodeMelhoria(false);
			 ha.setSemFrequencia(false);
			 ha.setValidada(false);
			 //PEGANDO DETALHE DA DISCIPLINA
			 ds.setDescricao(o.getDescricao());
			 ds.setId(o.getId());
			 dsM.add(ds);
			 this.repositoryHistoricoAluno.save(ha);
		}
		model.setDisciplinas(dsM);
		
		//GERAR A GUIA.
		gerarGuia(aluno, curso, usuario, anoLectivo);
		
		c.setResultado(model);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Matricula efetivada com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	
    }

	
	private void gerarGuia(Aluno aluno, Curso curso, Usuario usuario,AnoLectivo anoLectivo) {
		Emolumento emolumento = this.emolumentoRepository.findByCodigo(7);
		EmolumentoHistorico histEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento, curso, anoLectivo);
		
		Guia guia = new Guia();
		guia.setAluno(aluno);
		guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
		guia.setAutomaticamente(true);
		guia.setDataEmicao(new Date());
		guia.setLiquidada(false);
		guia.setUsuarioEmitiu(usuario);
		guia.setMoeda(this.moedaRepository.findOne(3));
		guia.setInstituicao(this.instituicaoRepository.findOne(2));
		guia.setAnoLectivo(anoLectivo);
		guia.setValor(histEmolumento.getValor());
		guia.setDataVencimento(new Date());


		guia.setUltimaModificacao(new Date());
		this.guia.save(guia);

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
		// setar o valor da guia
		guia.setNumeroGuia(definitivo);

		// atualizador os dados da ultima guia e a proxima guia
		numeroGerado.setUltimoNumero(proximoNumeroInteiro);
		numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
		this.numeroGeradoRepository.save(numeroGerado);
		guia.setUltimaModificacao(new Date());
		// final geração do número da guia
		Guia gSalva = this.guia.save(guia);
		GuiaPagamentoHistorico gHistorico = new GuiaPagamentoHistorico();

		gHistorico.setAluno(aluno);
		gHistorico.setAnoLectivo(anoLectivo);
		gHistorico.setEmolumento(emolumento);
		gHistorico.setGuia(gSalva);
		gHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
		gHistorico.setValor(histEmolumento.getValor());

		this.historicoGuiaRepository.save(gHistorico);
	}
	
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody MatriculaRecebida editarMatricula) {
		ResponseCliente c = new ResponseCliente();

		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(editarMatricula.getNumeroDeAluno().toString());
		AnoLectivo anoLectivo = this.repositoryAnoLectivo.findOne(editarMatricula.getAnoLectivo());

		// MATRICULA A SE ATUALIZAR.
		Matricula matricula = this.repositoryMatricula.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivo, false);

		// DADOS COMPLETARES
		EmpresaConvenio empresaConvenio = editarMatricula.getEmpresaBolsa() != null
				? this.empresaConvenioRepository.findOne(editarMatricula.getEmpresaBolsa())
				: null;

		PlanoPagamento planoPagamento = editarMatricula.getPlanoPagamento() != null
				? this.planoPagamentoRepository.findOne(editarMatricula.getPlanoPagamento())
				: null;

		Turma turma = this.turmaRepository.findOne(editarMatricula.getTurmaBase());
		TipoInscricao tipoInscricao = this.tipoInscricaoRepository.findOne(editarMatricula.getTipoInscricao());
		Usuario usuario = this.usuarioRepository
				.findByUserName(editarMatricula.getUserName() != null ? editarMatricula.getUserName() : null);

		if (!matricula.getTurmaBase().equals(turma) && matricula.getAluno().getNumeroDeAluno() == "1000000") {

			Emolumento emolumento = this.emolumentoRepository.findOne(30);

			EmolumentoHistorico emolumentoValor = this.emolumentoHistoricoRepository
					.findByEmolumentoAndCursoAndAnoLectivo(emolumento, aluno.getCurso(), anoLectivo);

			Guia guia = new Guia();
			guia.setAluno(aluno);
			guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
			guia.setAutomaticamente(true);
			guia.setDataEmicao(new Date());
			guia.setLiquidada(false);
			guia.setUsuarioEmitiu(usuario);
			guia.setMoeda(this.moedaRepository.findOne(3));
			guia.setInstituicao(this.instituicaoRepository.findOne(2));
			// guia.setValor(emolumento.getValor());
			// guia.setEmolumento(emolumento);
			guia.setAnoLectivo(anoLectivo);
			guia.setValor(emolumentoValor.getValor());
			guia.setDataVencimento(new Date());
			guia.setUltimaModificacao(new Date());
			this.guia.save(guia);

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
			// setar o valor da guia
			guia.setNumeroGuia(definitivo);

			// atualizador os dados da ultima guia e a proxima guia
			numeroGerado.setUltimoNumero(proximoNumeroInteiro);
			numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
			this.numeroGeradoRepository.save(numeroGerado);

			// final geração do número da guia
			guia.setUltimaModificacao(new Date());
			Guia gSalva = this.guia.save(guia);

			GuiaPagamentoHistorico gHistorico = new GuiaPagamentoHistorico();

			gHistorico.setAluno(aluno);
			gHistorico.setAnoLectivo(anoLectivo);
			gHistorico.setEmolumento(emolumento);
			gHistorico.setGuia(gSalva);
			gHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
			gHistorico.setValor(emolumentoValor.getValor());

			this.historicoGuiaRepository.save(gHistorico);
			this.guiaService.gerarHistoricoAudit(gHistorico);

		}
		// DADOS DE FACTO
		matricula.setPercentagemDesconto(editarMatricula.getPercentagemDesconto());
		matricula.setAnoCurricular(editarMatricula.getAnoCorricularInscricao());
		matricula.setAnoLectivo(anoLectivo);
		matricula.setAnoLectivoMatricula(anoLectivo.getAnoLectivo().toString());
		matricula.setCurso(aluno.getCurso());
		matricula.setData(matricula.getData());
		matricula.setEmpresaConvenio(empresaConvenio);
		matricula.setNumeroDeAluno(aluno.getNumeroDeAluno());
		matricula.setPlanoPagamento(planoPagamento);
		// matricula.setSemestre(editarMatricula.get);
		matricula.setTipoInscricao(tipoInscricao);
		matricula.setTurmaBase(turma);
		matricula.setDataAlteracao(new Date());
		matricula.setUsuarioAlterou(usuario);
		matricula.setUltimaModificacao(new Date());
		this.repositoryMatricula.save(matricula);
		this.matriculasService.gerarHistorico(matricula);
		HistoricoAluno disciplina = null;
		if (matricula.getTipoInscricao().getId() == 1) {
			List<HistoricoAluno> historico = this.repositoryHistoricoAluno.findByMatricula(matricula);
			for (HistoricoAluno o : historico) {
				disciplina = this.repositoryHistoricoAluno.findOne(o.getId());
				disciplina.setTurma(turma);
				matricula.setDataAlteracao(new Date());
				matricula.setUsuarioAlterou(usuario);
				disciplina.setUltimaModificacao(new Date());
				this.repositoryHistoricoAluno.save(disciplina);
				this.historicoAlunoService.gerarHistorico(disciplina);

			}
		}
		this.matriculasService.gerarHistorico(matricula);
		// PREPARA O RETONO
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(null);
		c.setMensagem("Atualização efetivada com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// matricula/atualizarGeral
	@RequestMapping(value = "/atualizarGeral", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizarGeral(@RequestBody EdicaoMatriculaNova editarMatricula) {
		ResponseCliente c = new ResponseCliente();

		Matricula matricula = this.repositoryMatricula.findOne(editarMatricula.getIdMatricula());

		// BUSCA USUARIO
		Usuario usuario = this.usuarioRepository
				.findByUserName(editarMatricula.getUserName() != null ? editarMatricula.getUserName() : null);

		// DADOS COMPLETARES

		EmpresaConvenio empresaConvenio = editarMatricula.getIdEmpresaConvenio() != null
				? this.empresaConvenioRepository.findOne(editarMatricula.getIdEmpresaConvenio())
				: null;

		PlanoPagamento planoPagamento = editarMatricula.getIdPlanoDePagamento() != null
				? this.planoPagamentoRepository.findOne(editarMatricula.getIdPlanoDePagamento())
				: null;

		Turma turmaBase = editarMatricula.getTurmaBaseId() != null
				? this.turmaRepository.findOne(editarMatricula.getTurmaBaseId())
				: null;
		// Usuario usuario =
		// this.usuarioRepository.findByUserName(editarMatricula.getUserName() != null ?
		// editarMatricula.getUserName() : null);

		// DADOS DE FACTO
		matricula.setPercentagemDesconto(editarMatricula.getPercentagemDesconto());
		matricula.setEmpresaConvenio(empresaConvenio);
		matricula.setPlanoPagamento(planoPagamento);
		// matricula.setTurmaBase(turma);
		matricula.setDataAlteracao(new Date());
		matricula.setUsuarioAlterou(usuario);

		// matricula.setUsuarioAlterou(usuario);

		if (turmaBase != null) {
			String sufixoTurma1 = matricula.getTurmaBase().getTurma().substring(1, 2);
			String sufixoTurma2 = turmaBase.getTurma().substring(1, 2);
			
			if (!sufixoTurma1.equals(sufixoTurma2)) {
				// PODEMOS GERAR GUIA DE ALTERAÇÃO DE TURNO.
				Emolumento emolumento = this.emolumentoRepository.findOne(30);

				EmolumentoHistorico emolumentoValor = this.emolumentoHistoricoRepository
						.findByEmolumentoAndCursoAndAnoLectivo(emolumento, matricula.getAluno().getCurso(),
								matricula.getAnoLectivo());

				Guia guia = new Guia();
				guia.setAluno(matricula.getAluno());
				guia.setNumeroDeAluno(matricula.getAluno().getNumeroDeAluno());
				guia.setAutomaticamente(true);
				guia.setDataEmicao(new Date());
				guia.setLiquidada(false);
				// guia.setUsuarioEmitiu(usuario);
				guia.setMoeda(this.moedaRepository.findOne(3));
				guia.setInstituicao(this.instituicaoRepository.findOne(2));
				// guia.setValor(emolumento.getValor());
				// guia.setEmolumento(emolumento);
				guia.setAnoLectivo(matricula.getAnoLectivo());
				guia.setValor(emolumentoValor.getValor());
				guia.setDataVencimento(new Date());
				guia.setUltimaModificacao(new Date());
				this.guia.save(guia);

				String definitivo = "";
				Integer lectivo = matricula.getAnoLectivo().getAnoLectivo();
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
				// setar o valor da guia
				guia.setNumeroGuia(definitivo);

				// atualizador os dados da ultima guia e a proxima guia
				numeroGerado.setUltimoNumero(proximoNumeroInteiro);
				numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
				this.numeroGeradoRepository.save(numeroGerado);
				guia.setUltimaModificacao(new Date());
				// final geração do número da guia
				Guia gSalva = this.guia.save(guia);

				GuiaPagamentoHistorico gHistorico = new GuiaPagamentoHistorico();

				gHistorico.setAluno(matricula.getAluno());
				gHistorico.setAnoLectivo(matricula.getAnoLectivo());
				gHistorico.setEmolumento(emolumento);
				gHistorico.setGuia(gSalva);
				gHistorico.setNumeroDeAluno(matricula.getAluno().getNumeroDeAluno());
				gHistorico.setValor(emolumentoValor.getValor());

				this.historicoGuiaRepository.save(gHistorico);
				this.guiaService.gerarHistoricoAudit(gHistorico);
			}
			matricula.setTurmaBase(turmaBase);
		}
		matricula.setUltimaModificacao(new Date());
		this.repositoryMatricula.save(matricula);
		this.matriculasService.gerarHistorico(matricula);
		List<DisciplinaCliente> disciplinas = editarMatricula.getDisciplinas();
		// Turma turma = null;
		HistoricoAluno historico = null;
		Disciplina disciplina = null;

		for (DisciplinaCliente o : disciplinas) {

			
			if (o.isMatriculaCorrente() && o.getIdHistorico() != null) {

				historico = this.repositoryHistoricoAluno.findOne(o.getIdHistorico());

				if (matricula.getTipoInscricao().getId() == 1) {
					historico.setTurma(matricula.getTurmaBase());
				}
				historico.setUltimaModificacao(new Date());
				this.repositoryHistoricoAluno.save(historico);
				this.historicoAlunoService.gerarHistorico(historico);
			}

			if (o.isRemover() && o.getIdHistorico() != null) {
				historico = this.repositoryHistoricoAluno.findOne(o.getIdHistorico());

				this.repositoryHistoricoAluno.delete(historico);
			}

			if (o.isAdicionar()) {
				historico = new HistoricoAluno();

				disciplina = this.repositoryDisciplina.findOne(o.getId());
				// this.turmaRepository.findByTurma("");

				if (o.getIdTurma() == null) {
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					c.setMensagem("Verifique as turmas.");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

				}

				Turma turmaDisciplina = this.turmaRepository.findOne(o.getIdTurma());

				historico.setTurma(turmaDisciplina);
				historico.setAluno(matricula.getAluno());
				historico.setNumeroDeAluno(matricula.getAluno().getNumeroDeAluno());
				historico.setDisciplina(disciplina);
				historico.setMatricula(matricula);
				historico.setAnoCorricular(disciplina.getAnoCorricular());
				historico.setAnoLectivo(matricula.getAnoLectivo());
				historico.setCurso(matricula.getCurso());
				historico.setDataInscricao(new Date());
				historico.setSituacao(Situacao.CURSANDO);

				if (disciplina.getAnoCorricular() != matricula.getAnoCurricular()
						&& matricula.getTipoInscricao().getId() == 2) {
					historico.setSemFrequencia(true);
				}
				historico.setUltimaModificacao(new Date());
				this.repositoryHistoricoAluno.save(historico);
				this.historicoAlunoService.gerarHistorico(historico);
			}
		}
		// PREPARA O RETONO
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(null);
		c.setMensagem("Atualização efetivada com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// ALTERAR TURMA DA DISCIPLINA
	@RequestMapping(value = "/alterarTurmaDisciplina", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> alterarTurmaDisciplina(@RequestBody TurmaDisciplinaAlterar pego) {
		ResponseCliente c = new ResponseCliente();
		int codigo;
		String mensagem;

		// BUSCAR A MATRICULA DESEJADA
		HistoricoAluno historico = this.repositoryHistoricoAluno.findOne(pego.getIdHistorico());

		// BUSCAR TIPO DE INSCRIÇÃO.
		Turma turma = this.turmaRepository.findOne(pego.getIdTurma());

		if (turma.getId() == historico.getTurma().getId()) {
			mensagem = "Não pode atualizar a mesma Turma";
			codigo = 300;
		} else {
			mensagem = "Turma atualizada com sucesso.";
			codigo = 200;
			// ALTERAR O TIPO DE INSCRIÇÃO
			historico.setTurma(turma);
			// SALVAR A MATRÍCULA
			historico.setUltimaModificacao(new Date());
			this.repositoryHistoricoAluno.save(historico);
			this.historicoAlunoService.gerarHistorico(historico);
		}

		c.setCodigo(codigo);
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// PlanoPagamentoAlteracao
	@RequestMapping(value = "/atualizaBolseiro", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizaBolseiro(@RequestBody BolseiroAtualizar editarMatricula) {
		ResponseCliente c = new ResponseCliente();

		Matricula matricula = this.repositoryMatricula.findOne(editarMatricula.getIdMatricula());

		Usuario usuario = this.usuarioRepository
				.findByUserName(editarMatricula.getUserName() != null ? editarMatricula.getUserName() : null);

		EmpresaConvenio empresaConvenio = this.empresaConvenioRepository.findOne(editarMatricula.getIdEmpresa());

		matricula.setEmpresaConvenio(empresaConvenio);
		matricula.setUsuarioAlterou(usuario);
		matricula.setDataAlteracao(new Date());
		matricula.setUltimaModificacao(new Date());
		this.repositoryMatricula.save(matricula);
		this.matriculasService.gerarHistorico(matricula);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(null);
		c.setMensagem("Atualizado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// PlanoPagamentoAlteracao
	@RequestMapping(value = "/percentemDesconto", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> percentemDesconto(@RequestBody PercentagemDesconto editarMatricula) {
		ResponseCliente c = new ResponseCliente();

		Matricula matricula = this.repositoryMatricula.findOne(editarMatricula.getIdMatricula());

		Usuario usuario = this.usuarioRepository
				.findByUserName(editarMatricula.getUserName() != null ? editarMatricula.getUserName() : null);

		matricula.setPercentagemDesconto(editarMatricula.getPercentagem());
		matricula.setUsuarioAlterou(usuario);
		matricula.setDataAlteracao(new Date());
		matricula.setUltimaModificacao(new Date());
		this.repositoryMatricula.save(matricula);
		this.matriculasService.gerarHistorico(matricula);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(null);
		c.setMensagem("Atualizado com sucesso.");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/atualizarTurmaBase", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizarTurmaBase(@RequestBody TurmaBaseEdicao editarMatricula) {
		ResponseCliente c = new ResponseCliente();
		
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);

		Matricula matricula = this.repositoryMatricula.findOne(editarMatricula.getIdMatricula());

		if (matricula != null) {
			if (matricula.getPlanoPagamento().getId() == 9) {
				c.setCodigo(300);
				c.setMensagem("Inscrição especial não pode ser alterada.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		}

		Usuario usuario = this.usuarioRepository
				.findByUserName(editarMatricula.getUserName() != null ? editarMatricula.getUserName() : null);

		Turma turmaBase = editarMatricula.getIdTurma() != null
				? this.turmaRepository.findOne(editarMatricula.getIdTurma())
				: null;

		if (turmaBase != null) {
			String sufixoTurma1 = matricula.getTurmaBase().getTurma().substring(1, 2);
			String sufixoTurma2 = turmaBase.getTurma().substring(1, 2);

			if (!sufixoTurma1.equals(sufixoTurma2)) {
				// PODEMOS GERAR GUIA DE ALTERAÇÃO DE TURNO.
				Emolumento emolumento = this.emolumentoRepository.findOne(30);

				EmolumentoHistorico emolumentoValor = this.emolumentoHistoricoRepository
						.findByEmolumentoAndCursoAndAnoLectivo(emolumento, matricula.getAluno().getCurso(),
								matricula.getAnoLectivo());

				Guia guia = new Guia();
				guia.setAluno(matricula.getAluno());
				guia.setNumeroDeAluno(matricula.getAluno().getNumeroDeAluno());
				guia.setAutomaticamente(true);
				guia.setDataEmicao(new Date());
				guia.setLiquidada(false);
				guia.setAnulada(false);
				guia.setAcordo(false);
				guia.setParaAcordoPagamento(false);
				guia.setGerouCredito(false);
				guia.setGeradaOnline(false);
				guia.setGeradaReferencia(false);
				guia.setLiquidacaoAGT(false);
				guia.setAlterada(false);
				guia.setLiquidacaoCredito(false);
				guia.setDataSistema(dataSistema);
				guia.setUsuarioEmitiu(usuario);
				guia.setMoeda(this.moedaRepository.findOne(3));
				guia.setInstituicao(this.instituicaoRepository.findOne(2));
				// guia.setValor(emolumento.getValor());
				// guia.setEmolumento(emolumento);
				guia.setAnoLectivo(matricula.getAnoLectivo());
				guia.setValor(emolumentoValor.getValor());
				guia.setDataVencimento(new Date());
				guia.setUltimaModificacao(new Date());
				this.guia.save(guia);

				String definitivo = "";
				Integer lectivo = matricula.getAnoLectivo().getAnoLectivo();
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
				GuiaCandidatura proformaCandidaturaExistente = guiaCandidaturaRepo.buscarProforma(numero);
				if (proformaExiste != null || proformaCandidaturaExistente != null) {
					do {
						proximoNumero++;
						
						numero =  gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
						proformaExiste = this.guiaRepository.findProforma(numero);
						proformaCandidaturaExistente = guiaCandidaturaRepo.buscarProforma(numero);
					} while (proformaExiste != null || proformaCandidaturaExistente != null);
				}
				// setar o valor da guia
				guia.setNumeroGuia(definitivo);
				guia.setNumeroFacturaProforma(numero);
				guia.setTipoFactura(TipoFatura.FACTURA_PROFORMA);
				
				// final geração do número da guia
				guia.setUltimaModificacao(new Date());
				Guia gSalva = this.guia.save(guia);
				
				this.gerarDocService.gerarFileProformaAluno(gSalva);
				
				numeroGeradoFP.setUltimoNumero(proximoNumero);
				numeroGeradoFP.setProximoNumero(proximoNumero + 1);
				this.numeroGeradoRepository.save(numeroGeradoFP);

				// atualizador os dados da ultima guia e a proxima guia
				numeroGerado.setUltimoNumero(proximoNumeroInteiro);
				numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
				this.numeroGeradoRepository.save(numeroGerado);

				GuiaPagamentoHistorico gHistorico = new GuiaPagamentoHistorico();
				
				double valorComIva = 0;
				double valorImposto = 0;
				
				valorImposto = (emolumentoValor.getValor() * emolumento.getPercentagemIva()) / 100;
				valorComIva = emolumentoValor.getValor() + valorImposto;
				
				double valorTotalIvaDesconto = (valorComIva - (emolumentoValor.getValor() * emolumento.getPercentagemDesconto()) / 100);
				double desconto = (emolumentoValor.getValor() * emolumento.getPercentagemDesconto()) / 100;


				gHistorico.setAluno(matricula.getAluno());
				gHistorico.setCodigoIva(emolumento.getCodigoIva());
				gHistorico.setPercentagemIva(emolumento.getPercentagemIva().toString());
				gHistorico.setQuantidade("1");
				gHistorico.setValorImposto(FormataData.formatarValor(valorImposto));
				gHistorico.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));
				gHistorico.setDesconto(FormataData.formatarValor(desconto));
				gHistorico.setAnoLectivo(matricula.getAnoLectivo());
				gHistorico.setEmolumento(emolumento);
				gHistorico.setGuia(gSalva);
				gHistorico.setNumeroDeAluno(matricula.getAluno().getNumeroDeAluno());
				gHistorico.setValor(emolumentoValor.getValor());

				this.historicoGuiaRepository.save(gHistorico);
				this.guiaService.gerarHistoricoAudit(gHistorico);
			}

			matricula.setTurmaBase(turmaBase);
			matricula.setUsuarioAlterou(usuario);
			matricula.setDataAlteracao(new Date());
			matricula.setUltimaModificacao(new Date());
			this.repositoryMatricula.save(matricula);
			this.matriculasService.gerarHistorico(matricula);
			if (matricula.getTipoInscricao().getId() == 1 || matricula.getTipoInscricao().getId() == 2
					|| matricula.getTipoInscricao().getId() == 3) {
				List<HistoricoAluno> historico = this.repositoryHistoricoAluno.findByMatricula(matricula);
				for (HistoricoAluno o : historico) {
					HistoricoAluno ha = this.repositoryHistoricoAluno.findOne(o.getId());
					ha.setTurma(turmaBase);

					if (o.getDisciplina().getAnoCorricular() == matricula.getAnoCurricular()) {
						ha.setUsuarioAlterou(usuario);
						ha.setUltimaModificacao(new Date());
						this.repositoryHistoricoAluno.save(ha);
						this.historicoAlunoService.gerarHistorico(ha);
					}
				}
			}
		}
		this.matriculasService.gerarHistorico(matricula);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(null);
		c.setMensagem("Turma base atualizada com sucesso.!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// ALTERAR TURMA DA DISCIPLINA

	// matricula/adicionarOrRemoverdisciplina
	@RequestMapping(value = "/adicionarOrRemoverdisciplina", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> adicionarOrRemoverdisciplina(
			@RequestBody AdicionarOrRemoverDisciplina pego) {
		ResponseCliente c = new ResponseCliente();
		Usuario usuario = this.usuarioRepository.findByUserName(pego.getUserName());

		Matricula matricula = this.repositoryMatricula.findOne(pego.getIdMatricula());

		

		if (matricula != null) {
			if (matricula.getPlanoPagamento().getId() == 9) {
				c.setCodigo(300);
				c.setMensagem("Não pode editar inscrição especial");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		}

		if (pego.isEvento()) {
		
			Disciplina disciplina = this.repositoryDisciplina.findOne(pego.getIdDisciplina());
			
			Turma turma = this.turmaRepository.findOne(pego.getIdTurma());

			HistoricoAluno ia = new HistoricoAluno();

			ia.setAluno(matricula.getAluno());

			ia.setNumeroDeAluno(matricula.getAluno().getNumeroDeAluno());
			ia.setMatricula(matricula);
			ia.setTurma(turma);
			ia.setDisciplina(disciplina);
			ia.setAnoLectivo(matricula.getAnoLectivo());
			ia.setAnoCorricular(disciplina.getAnoCorricular());
			ia.setCurso(matricula.getAluno().getCurso());
			ia.setDataAlteracao(new Date());
			ia.setDataInscricao(new Date());

			// CABEÇALHO DE OBRIGATÓRIEDADE
			ia.setAprovado(false);
			ia.setExtraordinaria(false);
			ia.setPode2epoca(false);
			ia.setPodeMelhoria(false);
			ia.setValidada(false);
			ia.setFechada(false);
			ia.setPodeEpEsp(false);
			ia.setSemFrequencia(false);

			// ia.set

			ia.setSituacao(Situacao.CURSANDO);
			ia.setUsuarioInscreveu(usuario);

			if (matricula.getTipoInscricao().getId() == 2
					&& matricula.getAnoCurricular() != disciplina.getAnoCorricular()) {
				ia.setSemFrequencia(true);
			}
			ia.setUltimaModificacao(new Date());
			this.repositoryHistoricoAluno.save(ia);
			this.historicoAlunoService.gerarHistorico(ia);
			c.setCodigo(200);
			c.setMensagem("Disciplina adicionada com sucesso.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

		} else {
			HistoricoAluno historico = this.repositoryHistoricoAluno.findOne(pego.getIdHistorico());
			// VER AS VALIDAÇÕES.
			if (historico.getMatricula().getAnoCurricular() == historico.getDisciplina().getAnoCorricular()
					&& (historico.getMatricula().getTipoInscricao().getId() == 1
							|| historico.getMatricula().getTipoInscricao().getId() == 2
							|| historico.getMatricula().getTipoInscricao().getId() == 3)) {
				c.setCodigo(300);
				c.setMensagem("Não pode remover disciplina do ano completo.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			if (historico.getAvaliacao1() != null || historico.getAvaliacao2() != null
					|| historico.getAvaliacao3() != null || historico.getAvaliacao4() != null
					|| historico.getNotaPratica() != null || historico.getNotaRecurso() != null
					|| historico.getNotaRecursoOral() != null || historico.getNotaFinalRecurso() != null
					|| historico.getNotaFinalMelhoria() != null || historico.getNotaFinalContinua() != null
					|| historico.getNotaFinal() != null || historico.getNotaExameOral() != null
					|| historico.getNotaExame() != null || historico.getNotaEpocaEspecialOral() != null
					|| historico.getNotaEpocaEspecial() != null || historico.getNotaCursoDeVerao() != null) {
				c.setCodigo(300);
				c.setMensagem("Disciplina com nota não se pode remover.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			this.repositoryHistoricoAluno.delete(historico);
			c.setCodigo(200);
			c.setMensagem("Disciplina removida com sucesso.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/anular", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> anular(@RequestBody AlunoId2 codigoMatricula) {
		ResponseCliente c = new ResponseCliente();

		TipoDeDeclaracao tipoDeclaracao = this.tipoDeDeclaracaoRepository.findOne(6);
		Matricula matricula = this.repositoryMatricula.findOne(codigoMatricula.getId());

		// verificar pedido de anulação de matricula.
		RegistroDocumentos registro = this.registroDocumentoRepository
				.findByAlunoAndTipoDeclaracaoAndRetirado(matricula.getAluno(), tipoDeclaracao, false);
		if (registro == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setResultado(matricula);
			c.setMensagem("Nehum pedido de anulação matrícula.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		if (registro != null && !registro.getGuiaPagamento().isLiquidada()) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setResultado(matricula);
			c.setMensagem("Efetue o pagamento do pedido.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		registro.setRetirado(true);
		this.registroDocumentoRepository.save(registro);

		matricula.setAnulado(true);
		matricula.setDataAnulamento(new Date());
		matricula.setUltimaModificacao(new Date());
		this.repositoryMatricula.save(matricula);
		this.matriculasService.gerarHistorico(matricula);
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(matricula.getNumeroDeAluno());

		if (aluno != null) {
			aluno.setInactivo(false);
			aluno.setDataInativo(new Date());
			aluno.setUltimaModificacao(new Date());
			this.alunoRepository.save(aluno);
			this.alunoService.gerarHistorico(aluno);
		}
		this.matriculasService.gerarHistorico(matricula);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(matricula);
		c.setMensagem("Matrícula anulada com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// ALTER TIPO DE INSCRIÇÃO...
	// intellectus/matricula/alterarTipoInscricao
	@RequestMapping(value = "/alterarTipoInscricao", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> alterarTipoInscricao(@RequestBody TipoInscricaoMatricula pego) {
		ResponseCliente c = new ResponseCliente();
		int codigo;
		String mensagem;

		// 000
		Usuario usuario = this.usuarioRepository.findByUserName(pego.getUserName() != null ? pego.getUserName() : null);

		// BUSCAR A MATRICULA DESEJADA
		Matricula matricula = this.repositoryMatricula.findOne(pego.getIdMatricula());
		// BUSCAR TIPO DE INSCRIÇÃO.
		TipoInscricao tipoInscricao = this.tipoInscricaoRepository.findOne(pego.getIdTipoInscricao());
		// usuario
		matricula.setAlterouTipoInscricao(usuario);

		if (tipoInscricao.getId() == matricula.getTipoInscricao().getId()) {
			mensagem = "Não pode atualizar o mesmo tipo.";
			codigo = 300;
		} else {
			mensagem = "Tipo de inscrição atualiado com sucesso.";
			codigo = 200;
			// ALTERAR O TIPO DE INSCRIÇÃO
			matricula.setTipoInscricao(tipoInscricao);
			// SALVAR A MATRÍCULA
			matricula.setUltimaModificacao(new Date());
			this.repositoryMatricula.save(matricula);
			this.matriculasService.gerarHistorico(matricula);
		}
		this.matriculasService.gerarHistorico(matricula);
		c.setCodigo(codigo);
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/transferencia", method = RequestMethod.PUT, produces = "application/json")
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> transferencia(@RequestBody TransferenciaMatricula matricula) {
		ResponseCliente c = new ResponseCliente();
		
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		String dataSistema = localDate.format(formatter);
		
		Usuario usuario = this.usuarioRepository
				.findByUserName(matricula.getUserName() != null ? matricula.getUserName() : null);

		// BUSCA O ALUNO
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(matricula.getNumeroDeAluno().toString());
		

		// BUSCA O ANO LECTIVO
		List<AnoLectivo> anoLectivo = this.repositoryAnoLectivo.findByStatus(true);

		// BUSCAR A MATRICULA CORRENTE
		//Matricula mtrAluno = this.repositoryMatricula.findByAlunoAndAnoLectivo(aluno, anoLectivo.get(0));
		
		//VERIFICAR NÚMERO DE TROCAS DE CURSO NO ANO
		List<Matricula> matriculas= this.repositoryMatricula.buscarAlunoAndAnoLectivoAndAnulado(aluno.getNumeroDeAluno(), matricula.getCodigoAnoLectivo());
		
		if(matriculas.size()>=2) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Atingiu o limite de trocas de curso no ano corrente! Dirija-se a área acádemica.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);	
		}
		// BUSCA O CURSO DESTINO
		Curso cursoDestino = this.cursoRepository.findById(matricula.getCodigoCursoDestino());

		// BUSCA A INSTITUIÇÃO
		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		// BUSCA A MOEDA
		Moeda moeda = this.moedaRepository.findOne(3);

		if (aluno.isInactivo() || aluno.isFimCurso()) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Aluno Inactivo/Fim curso não pode mudar de curso");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		Curso cursoAntigo = aluno.getCurso();

		if (matriculas.get(0) != null) {
			matriculas.get(0).setAnulado(true);
			matriculas.get(0).setDataAnulamento(new Date());
			matriculas.get(0).setUltimaModificacao(new Date());
			this.repositoryMatricula.save(matriculas.get(0));
			this.matriculasService.gerarHistorico(matriculas.get(0));
		}

		aluno.setCurso(cursoDestino);
		aluno.setUltimaModificacao(new Date());
		this.alunoRepository.save(aluno);
		this.alunoService.gerarHistorico(aluno);
		Emolumento emolumento = this.emolumentoRepository.findByCodigo(9);
		EmolumentoHistorico histEmolumento = this.emolumentoHistoricoRepository
				.findByEmolumentoAndCursoAndAnoLectivo(emolumento, aluno.getCurso(), anoLectivo.get(0));
		Guia guia = new Guia();
		guia.setAluno(aluno);
		guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
		guia.setAutomaticamente(true);
		guia.setDataEmicao(new Date());
		guia.setDataSistema(dataSistema);
		guia.setLiquidada(false);
		guia.setUsuarioEmitiu(usuario);
		guia.setMoeda(moeda);
		guia.setInstituicao(instituicao);
		guia.setAnoLectivo(anoLectivo.get(0));
		guia.setValor(histEmolumento.getValor());
		guia.setDataVencimento(new Date());
		guia.setUltimaModificacao(new Date());
		guia.setAcordo(false);
		guia.setParaAcordoPagamento(false);
		guia.setGerouCredito(false);
		guia.setGeradaOnline(false);
		guia.setGeradaReferencia(false);
		guia.setLiquidacaoAGT(false);
		guia.setAlterada(false);
		guia.setLiquidacaoCredito(false);
		guia.setAnulada(false);
		this.guia.save(guia);

		String definitivo = "";
		Integer lectivo = anoLectivo.get(0).getAnoLectivo();

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
		
		AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
		String ano = String.valueOf(anoActivo.getAnoLectivo());
		String anoSubstring = ano.substring(2,4);
		Integer anoLimpo = Integer.parseInt(anoSubstring);
		
		NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
		Long proximoNumero = numeroGeradoFP.getProximoNumero();
		
		//String numero = gerarNumeroDocService.geracaoNumero();
		numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
		
		Guia proformaExiste = this.guiaRepository.findProforma(numero);
		GuiaCandidatura proformaCandidaturaExistente = guiaCandidaturaRepo.buscarProforma(numero);
		if (proformaExiste != null || proformaCandidaturaExistente != null) {
			do {
				proximoNumero++;
				
				numero =  gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
				proformaExiste = this.guiaRepository.findProforma(numero);
				proformaCandidaturaExistente = guiaCandidaturaRepo.buscarProforma(numero);
			} while (proformaExiste != null || proformaCandidaturaExistente != null);
		}
		// setar o valor da guia
		guia.setNumeroGuia(definitivo);
		guia.setNumeroFacturaProforma(numero);
		guia.setTipoFactura(TipoFatura.FACTURA_PROFORMA);
		
		guia.setUltimaModificacao(new Date());
		// final geração do número da guia

		// atualizador os dados da ultima guia e a proxima guia
		numeroGerado.setUltimoNumero(proximoNumeroInteiro);
		numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
		this.numeroGeradoRepository.save(numeroGerado);
		guia.setUltimaModificacao(new Date());
		// final geração do número da guia
		Guia gSalva = this.guia.save(guia);
		
		this.gerarDocService.gerarFileProformaAluno(gSalva);
		
		double valorComIva = 0;
		double valorImposto = 0;
		
		valorImposto = (histEmolumento.getValor() * emolumento.getPercentagemIva()) / 100;
		valorComIva = histEmolumento.getValor() + valorImposto;
		
		double valorTotalIvaDesconto = (valorComIva - (histEmolumento.getValor() * emolumento.getPercentagemDesconto()) / 100);
		double desconto = (histEmolumento.getValor() * emolumento.getPercentagemDesconto()) / 100;

		// HISTORICO DE PAGAMENTO
		GuiaPagamentoHistorico hist = new GuiaPagamentoHistorico();
		hist.setAluno(aluno);
		hist.setCodigoIva(emolumento.getCodigoIva());
		hist.setPercentagemIva(emolumento.getPercentagemIva().toString());
		hist.setQuantidade("1");
		hist.setValorImposto(FormataData.formatarValor(valorImposto));
		hist.setDesconto(FormataData.formatarValor(desconto));
		hist.setValorTotal(FormataData.formatarValor(valorTotalIvaDesconto));
		hist.setAnoLectivo(anoLectivo.get(0));
		hist.setGuia(gSalva);
		hist.setNumeroDeAluno(aluno.getNumeroDeAluno());
		hist.setEmolumento(emolumento);
		hist.setValor(histEmolumento.getValor());
		this.historicoGuiaRepository.save(hist);
		this.guiaService.gerarHistoricoAudit(hist);
		// INSTACIAR O MODELO DE TROCA DE CURSO.
		HistoricoTrocaCurso historico = new HistoricoTrocaCurso();

		historico.setAluno(aluno);
		historico.setCursoAtigo(cursoAntigo);
		historico.setCursoNovo(cursoDestino);
		historico.setDataTrocaCurso(new Date());
		historico.setNumeroDeAluno(Integer.parseInt(aluno.getNumeroDeAluno()));

		this.historicoTrocaCursoRepository.save(historico);

		aluno.setInscrito(false);
		aluno.setUltimaModificacao(new Date());
		this.alunoRepository.save(aluno);
		this.alunoService.gerarHistorico(aluno);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem("Transfencia efetuada com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}