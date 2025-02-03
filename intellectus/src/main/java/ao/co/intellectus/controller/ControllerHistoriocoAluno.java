package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AcademicoCliente;
import ao.co.intellectus.DTO.AcademicoFicha;
import ao.co.intellectus.DTO.AcademicoFichaDeAluno;
import ao.co.intellectus.DTO.AdicionarDisciplina;
import ao.co.intellectus.DTO.AdicionarDisciplinasClientes;
import ao.co.intellectus.DTO.AlunoAnoLectivo;
import ao.co.intellectus.DTO.AuditoriaNotaCliente;
import ao.co.intellectus.DTO.ClassificacaoCliente;
import ao.co.intellectus.DTO.ClassificarUmAluno;
import ao.co.intellectus.DTO.DisciplinaAndAlunoAndAnoLectivo;
import ao.co.intellectus.DTO.DisciplinaNovaCliente;
import ao.co.intellectus.DTO.DisciplinaResumo;
import ao.co.intellectus.DTO.DisciplinasClienteAdicionar;
import ao.co.intellectus.DTO.FecharClassificacoesCliente;
import ao.co.intellectus.DTO.HistoricoAlunoCliente;
import ao.co.intellectus.DTO.HistoricoAlunoEditar;
import ao.co.intellectus.DTO.HistoricoAlunosTurma;
import ao.co.intellectus.DTO.HistoricoEAluno;
import ao.co.intellectus.DTO.MatriculaEditarCliente;
import ao.co.intellectus.DTO.ModeloAlteracaoNota;
import ao.co.intellectus.DTO.TurmaAndDisciplinaCliente;
import ao.co.intellectus.DTO.TurmaCliente;
import ao.co.intellectus.DTO.UsoInternoCliente;
import ao.co.intellectus.DTO.ValidarNotasDTO;
import ao.co.intellectus.DTO.colegio.DisciplinaClienteColegio;
import ao.co.intellectus.DTO.colegio.MatriculaConfirmadaViewModel;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAnexo;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.AutorizacaoLancamentoEspecial;
import ao.co.intellectus.model.AutorizacaoLancamentoNota;
import ao.co.intellectus.model.Contador;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Docente;
import ao.co.intellectus.model.FechamentoClassificacao;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.model.InscricaoExtraordinaria;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.LancamentoFaculdade;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.Prova;
import ao.co.intellectus.model.Situacao;
import ao.co.intellectus.model.TipoDisciplina;
import ao.co.intellectus.model.TipoProvaExtraOrdinaria;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.TurmaDisponivel;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoAnexoRepository;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.AutorizacaoLancamentoEspecialRepository;
import ao.co.intellectus.repository.AutorizacaoLancamentoNrepository;
import ao.co.intellectus.repository.ContadorRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.DocenteRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.FechamentoClassificacaoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.InscricaoExtraordinariaRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.LancamentoFaculdadeRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.PermissaoCursoRepository;
import ao.co.intellectus.repository.ProvaRepository;
import ao.co.intellectus.repository.TurmaDisponivelRepository;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.cafold.HistoricoAlunoService;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/historicoDeAluno")
public class ControllerHistoriocoAluno {
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private HistoricoAlunoRepository repositoryHistoricoAluno;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private TurmaDisponivelRepository turmasDisponiveis;
	@Autowired
	private AlunoAnexoRepository alunoAnexoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private FechamentoClassificacaoRepository fechamentoRepository;
	@Autowired
	private AutorizacaoLancamentoNrepository autorizacaoRepository;
	@Autowired
	private ProvaRepository provaRepository;
	@Autowired
	private PermissaoCursoRepository permissaoCursoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EquivalenciaHistoricoRepository equivalenciaHistoricoRepository;
	@Autowired
	private AutorizacaoLancamentoEspecialRepository autorizacaoLancamentoEspecialRepository;
	@Autowired
	private DocenteRepository docenteRepository;
	@Autowired
	private InscricaoExtraordinariaRepository inscricaoExtraordinariaRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private ContadorRepository contadorRepository;
	@Autowired
	private HistoricoAlunoService historicoAlunoService;
	@Autowired
	private LancamentoFaculdadeRepository lancamentoFaculdadeRepository;
	
	
	/*
	 * @Autowired private EmolumentoRepository emolumentoRepository;
	 * 
	 * @Autowired private EmolumentoHistoricoRepository
	 * emolumentoHistoricoRepository;
	 * 
	 * @Autowired private HistoricoGuiaPagamentoRepository
	 * historicoGuiaPagamentoRepository;
	 * 
	 * @Autowired private GuiaUsuadaRepository guiaUsuadaRepository;
	 */

	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Iterable<HistoricoAluno> buscarAnoActivo() {
		return this.repositoryHistoricoAluno.findAll();
	}

	@RequestMapping(value = "/buscarPorId/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscar(@PathVariable Integer numero, @PathVariable String userName) {
		ResponseCliente c = new ResponseCliente();

		Aluno umAluno = this.alunoRepository.findByNumeroDeAluno(numero.toString());

		PermissaoCurso up = this.permissaoCursoRepository.findByCursoAndUsuarioUserName(umAluno.getCurso(), userName);

		// COMEÇO AVALIAÇÃO
		// BUSCA O HISTÓRICO DO ALUNO PASSADO
		List<HistoricoAlunoCliente> alunosCliente = verificarPlanoAluno(umAluno);
		// FINAL AVALIACAO

		AlunoAnexo alunoFoto = this.alunoAnexoRepository.findByAluno(umAluno);

		HistoricoEAluno alunoResultado = new HistoricoEAluno();
		alunoResultado.setId(numero);
		alunoResultado.setNome(umAluno.getNome());
		alunoResultado.setCurso(umAluno.getCurso().getDescricao());
		alunoResultado.setFoto(alunoFoto.getFoto());
		alunoResultado.setContencioso(umAluno.isContencioso());
		alunoResultado.setFimCurso(umAluno.isFimCurso());
		alunoResultado.setInativo(umAluno.isInactivo());
		alunoResultado.setInscrito(umAluno.isInscrito());
		// OS DADOS DE CLASSIFICAÇÃO

		alunoResultado.setHistorico(alunosCliente);

		if (up != null) {
			c.setPermissao(up.getPermissao());
		}
		c.setResultado(alunoResultado);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	private List<HistoricoAlunoCliente> verificarPlanoAluno(Aluno umAluno) {
		List<HistoricoAluno> matriculasDoAluno = this.repositoryHistoricoAluno
				.findByAlunoAndCursoAndAprovadoAndDisciplinaStatus(umAluno, umAluno.getCurso(), true, true);

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
		List<HistoricoEquivalencia> equivalencia = this.equivalenciaHistoricoRepository.findByAluno(umAluno);
		for (HistoricoEquivalencia o : equivalencia) {
			if (!mapa.containsKey(o.getDisciplinaDestino().getId())) {
				mapaE.put(o.getDisciplinaDestino().getId(), o);
			}
		}

		List<HistoricoAlunoCliente> alunosCliente = new ArrayList<HistoricoAlunoCliente>();
		HistoricoAlunoCliente aluno;

		// busca todas as disciplinas activas de um curso
		List<Disciplina> disciplinas = this.disciplinaRepository.findByCursoAndStatus(umAluno.getCurso(), true);
		HistoricoAluno buscaHistorico;
		HistoricoEquivalencia historicoEquivalencia;
		for (Disciplina o : disciplinas) {
			aluno = new HistoricoAlunoCliente();
			buscaHistorico = mapa.get(o.getId());
			historicoEquivalencia = mapaE.get(o.getId());

			aluno.setAnoCorricular(o.getAnoCorricular());
			aluno.setDisciplina(o.getDescricao());
			aluno.setCodigoDisciplina(o.getId());

			if (buscaHistorico != null) {
				aluno.setAnoLectivoDescricao((buscaHistorico.getAnoLectivo().getAnoLectivoDescricao()));
				aluno.setAprovado(buscaHistorico.isAprovado());
				aluno.setNotaFinal(buscaHistorico.getNotaFinal());
				aluno.setSituacao(buscaHistorico.getSituacao());
				aluno.setTipoDisciplina(buscaHistorico.getDisciplina().getTipo());
				aluno.setTurma(buscaHistorico.getTurma().getTurma());
			} else if (historicoEquivalencia != null) {
				if (historicoEquivalencia.getNotaOrigem() >= 10) {
					aluno.setAnoLectivoDescricao(historicoEquivalencia.getEquivalencia().getAnoLectivo().getAnoLectivoDescricao());
					aluno.setAprovado(true);
					aluno.setNotaFinal(historicoEquivalencia.getNotaOrigem());
					aluno.setSituacao(Situacao.APROVADO);
					aluno.setTipoDisciplina(historicoEquivalencia.getDisciplinaDestino().getTipo());
					aluno.setTurma("EQ");
				}
			}

			alunosCliente.add(aluno);
		}
		return alunosCliente;
	}

	/*
	 * Retornar a matricula com as disciplinas para o front. DESENVOLVEDOR: Ernesto
	 * Tadeu T. Sambongo DATA: 19-06-2018 ALTERAÇÃO: Severino Emilio DATA ALTERAÇÃO:
	 * ...
	 * 
	 */

	// gerar guia caso seja alterada a turma...
	@RequestMapping(value = "/buscarParaAlteracao/{numero}/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarParaAlteracao(@PathVariable Integer numero,
			@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();

		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero.toString());
		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(ano);
		Matricula matricula = this.matriculaRepository.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivo, false);

		if (matricula == null) {
			c.setMensagem("Não existe nehuma matricula neste ano lectivo.");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		List<HistoricoAluno> historico = this.repositoryHistoricoAluno.findByMatricula(matricula);
		MatriculaEditarCliente matriculaRetorno = new MatriculaEditarCliente();
		// dados pessoais do aluno.
		matriculaRetorno.setNomeAluno(matricula.getAluno().getNome());
		matriculaRetorno.setNumeroDeAluno(Integer.parseInt(matricula.getAluno().getNumeroDeAluno()));
		// dados de matricula.
		matriculaRetorno.setAnoCorricularInscricao(matricula.getAnoCurricular());
		matriculaRetorno.setAnoLectivo(matricula.getAnoLectivo().getId());
		matriculaRetorno.setAnoLectivoDescricao(matricula.getAnoLectivo().getAnoLectivo());
		matriculaRetorno.setAnulado(matricula.isAnulado());
		matriculaRetorno.setContenciso(matricula.getAluno().isContencioso());
		matriculaRetorno.setFimCurso(matricula.getAluno().isFimCurso());
		matriculaRetorno.setInactivo(matricula.getAluno().isInactivo());
		matriculaRetorno.setCurso(matricula.getCurso().getPlano());
		matriculaRetorno.setCursoId(matricula.getCurso().getId());
		matriculaRetorno.setDataAnulacao(matricula.getDataAnulamento());
		matriculaRetorno.setDataMatricula(matricula.getData());

		matriculaRetorno.setPercentagemDesconto(matricula.getPercentagemDesconto());

		if (matricula.getPlanoPagamento() != null) {
			matriculaRetorno.setPlanoPagamento(matricula.getPlanoPagamento().getId());
			matriculaRetorno.setPlanoPagamentoDescricao(matricula.getPlanoPagamento().getDescricao());
		}

		if (matricula.getEmpresaConvenio() != null) {
			matriculaRetorno.setEmpresaBolsa(matricula.getEmpresaConvenio().getId());
			matriculaRetorno.setEmpresaBolsaDescricao(matricula.getEmpresaConvenio().getDesignacao());
		}

		if (matricula.getTurmaBase() != null) {
			matriculaRetorno.setTurmaBase(matricula.getTurmaBase().getId());
			matriculaRetorno.setTurmaBaseDescricao(matricula.getTurmaBase().getTurma());
		}

		if (matricula.getTipoInscricao() != null) {
			matriculaRetorno.setTipoInscricao(matricula.getTipoInscricao().getId());
			matriculaRetorno.setTipoInscricaoDescricao(matricula.getTipoInscricao().getDescricao());
		}

		HistoricoAlunoEditar editar;
		List<HistoricoAlunoEditar> disciplinas = new ArrayList<HistoricoAlunoEditar>();
		for (HistoricoAluno historicoAluno : historico) {
			editar = new HistoricoAlunoEditar();

			if (historicoAluno.getDisciplina() != null)
				editar.setAnoCorricular(historicoAluno.getDisciplina().getAnoCorricular());
			if (historicoAluno.getDisciplina() != null)
				editar.setCodigoDisciplina(historicoAluno.getDisciplina().getId());
			if (historicoAluno.getDisciplina() != null)
				editar.setDisciplina(historicoAluno.getDisciplina().getDescricao());
			editar.setSituacao(historicoAluno.getSituacao());
			if (historicoAluno.getDisciplina() != null)
				editar.setTipo(historicoAluno.getDisciplina().getTipo());
			if (historicoAluno.getTurma() != null)
				editar.setTurma(historicoAluno.getTurma().getTurma());

			editar.setNumeroDeAluno(Integer.parseInt(historicoAluno.getAluno().getNumeroDeAluno()));
			editar.setAnoLectivo(historicoAluno.getAnoLectivo().getId());

			disciplinas.add(editar);
		}
		matriculaRetorno.setHistorico(disciplinas);

		List<DisciplinaResumo> disciplinasResumo = new ArrayList<DisciplinaResumo>();
		DisciplinaResumo disciplay;
		List<HistoricoAluno> pendencias = this.repositoryHistoricoAluno.findByAprovadoAndAluno(false, aluno);
		for (HistoricoAluno historicoAluno : pendencias) {
			disciplay = new DisciplinaResumo();
			if (historicoAluno.getDisciplina() != null)
				disciplay.setId(historicoAluno.getDisciplina().getId());

			if (historicoAluno.getDisciplina() != null)
				disciplay.setAnoCorricular(historicoAluno.getDisciplina().getAnoCorricular());

			if (historicoAluno.getDisciplina() != null)
				disciplay.setDisciplina(historicoAluno.getDisciplina().getDescricao());
			disciplinasResumo.add(disciplay);
		}
		matriculaRetorno.setDisciplinas(disciplinasResumo);
		TurmaCliente turma;
		List<TurmaCliente> turmas = new ArrayList<TurmaCliente>();
		List<TurmaDisponivel> turmasDisponiveis = this.turmasDisponiveis
				.findByCursoAndAnoAndAceitaInscricoes(aluno.getCurso(), matricula.getAnoCurricular(), true);
		for (TurmaDisponivel turmaDisponivel : turmasDisponiveis) {
			turma = new TurmaCliente();
			turma.setIdTurma(turmaDisponivel.getTurma().getId());
			turma.setTurma(turmaDisponivel.getTurma().getTurma());
			turma.setTurno(turmaDisponivel.getTurma().getTurno());
			turmas.add(turma);
		}
		matriculaRetorno.setTurmas(turmas);
		// alunoResultado
		c.setResultado(matriculaRetorno);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarUsoInterno/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> declaracaoUsoInterno(@PathVariable String id) {
		ResponseCliente c = new ResponseCliente();
		Aluno umAluno = this.alunoRepository.findByNumeroDeAluno(id);
		List<HistoricoAluno> matriculasDoAluno = this.repositoryHistoricoAluno.findByAlunoAndCurso(umAluno,
				umAluno.getCurso());
		if (matriculasDoAluno.isEmpty()) {
			c.setMensagem("Nenhma matrícula encontrada.");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<HistoricoAlunoCliente> alunosCliente = new ArrayList<HistoricoAlunoCliente>();

		UsoInternoCliente aluno = new UsoInternoCliente();

		aluno.setNome(matriculasDoAluno.get(0).getAluno().getNome());
		aluno.setDataNascimento(matriculasDoAluno.get(0).getAluno().getDataNascimento());
		aluno.setCurso(matriculasDoAluno.get(0).getAluno().getCurso().getDescricao());
		aluno.setNomeDaMae(matriculasDoAluno.get(0).getAluno().getNomeDaMae());
		aluno.setNomeDoPai(matriculasDoAluno.get(0).getAluno().getNomeDoPai());
		aluno.setNumeroDeAluno(matriculasDoAluno.get(0).getAluno().getNumeroDeAluno());
		aluno.setNumeroDocumento(matriculasDoAluno.get(0).getAluno().getNumeroDocumento());
		aluno.setDataDeEmissao(matriculasDoAluno.get(0).getAluno().getDataEmissaoDocumento());

		AcademicoCliente conta;
		List<AcademicoCliente> cotas = new ArrayList<AcademicoCliente>();
		for (HistoricoAluno historicoAluno : matriculasDoAluno) {
			conta = new AcademicoCliente();
			conta.setAnoCorricular(historicoAluno.getAnoCorricular());
			// conta.setAnoLectivo(historicoAluno.getAnoLectivo().getAnoLectivo());
			conta.setDisciplina(historicoAluno.getDisciplina().getDescricao());
			conta.setNotaFinal(historicoAluno.getNotaFinal());
			cotas.add(conta);
		}
		aluno.setAcademico(cotas);

		HistoricoEAluno alunoResultado = new HistoricoEAluno();
		alunoResultado.setId(Integer.parseInt(id));
		alunoResultado.setNome(matriculasDoAluno.get(0).getAluno().getNome());
		alunoResultado.setCurso(matriculasDoAluno.get(0).getAluno().getCurso().getDescricao());
		alunoResultado.setHistorico(alunosCliente);

		c.setResultado(aluno);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/fichaDeInscricao", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> fichaDeAluno(@RequestBody AlunoAnoLectivo bAluno) {

		ResponseCliente c = new ResponseCliente();
		try {
			Aluno umAluno = this.alunoRepository.findByNumeroDeAluno(bAluno.getAluno().toString());
			AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(bAluno.getAnoLectivo());

			
			
			List<HistoricoAluno> matriculasDoAluno = this.repositoryHistoricoAluno.findByAlunoAndAnoLectivo(umAluno,anoLectivo);
		
			
			if (matriculasDoAluno.isEmpty()) {
				c.setMensagem("Nenhma matrícula encontrada.");
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			AcademicoFichaDeAluno aluno = new AcademicoFichaDeAluno();
			aluno.setNome(matriculasDoAluno.get(0).getAluno().getNome());
			aluno.setCurso(matriculasDoAluno.get(0).getAluno().getCurso().getDescricao());
			aluno.setFaculdade(matriculasDoAluno.get(0).getAluno().getCurso().getFaculdade().getFaculdade());
			aluno.setNumeroAluno(matriculasDoAluno.get(0).getAluno().getNumeroDeAluno().toString());
			aluno.setNumeroDocumento(matriculasDoAluno.get(0).getAluno().getNumeroDocumento());
			aluno.setDataDeEmissao(matriculasDoAluno.get(0).getAluno().getDataEmissaoDocumento());
			// aluno.setDataDeEmissao(matriculasDoAluno.get(0).getAluno().getDataEmissao());
			aluno.setArquivoDeIdentificacao(matriculasDoAluno.get(0).getAluno().getArquivoIdentificacao());
			aluno.setUniversidade(anoLectivo.getInstit().getDescricao());
			aluno.setAnoLectivo(anoLectivo.getAnoLectivo());
			aluno.setDataInscricao(matriculasDoAluno.get(0).getDataInscricao());
			aluno.setTelefone(matriculasDoAluno.get(0).getAluno().getTelefone());
			aluno.setEmail(matriculasDoAluno.get(0).getAluno().getEmail());
			aluno.setSexo(matriculasDoAluno.get(0).getAluno().getSexo().getDescricao());
			// aluno.setEndereco(matriculasDoAluno.get(0).getAluno().getEnde);

			AcademicoFicha ficha;
			List<AcademicoFicha> cotas = new ArrayList<AcademicoFicha>();

			for (HistoricoAluno historicoAluno : matriculasDoAluno) {
				ficha = new AcademicoFicha();
				ficha.setAnoCorricular(historicoAluno.getAnoCorricular());
				ficha.setDisciplina(historicoAluno.getDisciplina().getDescricao());
				ficha.setTipoInscricao(historicoAluno.getMatricula().getTipoInscricao().getDescricao());
				ficha.setTipo(historicoAluno.getDisciplina().getTipo().getDescricao());
				ficha.setTurma(historicoAluno.getTurma().getTurma());
				cotas.add(ficha);
			}
			aluno.setAcademico(cotas);

			
			c.setResultado(aluno);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception ex) {
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value = "/fichaDeInscricaoColegio", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> fichaDeAlunoColegio(@RequestBody AlunoAnoLectivo bAluno) {

		ResponseCliente c = new ResponseCliente();
		try {
			Aluno umAluno = this.alunoRepository.findByNumeroDeAluno(bAluno.getAluno().toString());
			AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(bAluno.getAnoLectivo());

			List<HistoricoAluno> matriculasDoAluno = this.repositoryHistoricoAluno.findByAlunoAndAnoLectivo(umAluno,anoLectivo);
			
			Matricula matricula = this.matriculaRepository.findByAlunoAndAnoLectivo(umAluno, anoLectivo);
			
			if (matriculasDoAluno.isEmpty()) {
				c.setMensagem("Nenhma matrícula encontrada.");
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			MatriculaConfirmadaViewModel model=new MatriculaConfirmadaViewModel();
			List<DisciplinaClienteColegio> dsM=new ArrayList<DisciplinaClienteColegio>();
			DisciplinaClienteColegio ds;
			
			
			model.setAluno(Integer.parseInt(matricula.getAluno().getNumeroDeAluno()));
			model.setAnoLectivo(anoLectivo.getAnoLectivo());
			model.setCurso(matricula.getCurso().getDescricao());
			model.setData(matricula.getData());
			model.setNumeroDeAluno(matricula.getAluno().getNumeroDeAluno());
			model.setPercentagemDesconto(matricula.getPercentagemDesconto());
			model.setPlanoPagamento(matricula.getPlanoPagamento().getDescricao());
			model.setTurmaBase(matricula.getTurmaBase().getTurma());
			model.setNome(matricula.getAluno().getNome());
			
			for (HistoricoAluno o : matriculasDoAluno) {
				ds=new DisciplinaClienteColegio();
				
				ds.setDescricao(o.getDisciplina().getDescricao());
				ds.setId(o.getDisciplina().getId());
				
				dsM.add(ds);
			}
			
			model.setDisciplinas(dsM);
			
			c.setResultado(model);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception ex) {
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/buscarPorTurmaAndDisciplina", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarTurmaAndDisciplina(@RequestBody TurmaAndDisciplinaCliente td) {

		// System.err.println("ANO LECTIVO RECEBIDO: "+td.getAnoLectivo());

		ResponseCliente c = new ResponseCliente();
		// Preencher o cabeçalho
		Turma turma = this.turmaRepository.findOne(td.getTurma());

		Disciplina disciplina = this.disciplinaRepository.findOne(td.getDisciplina());
		// LOCO

		AnoLectivo anoLectivo = this.anoLectivoRepository.findByAnoLectivo(td.getAnoLectivo());

		Usuario usuario = this.usuarioRepository.findByUserCode(td.getDocente());

		Docente userDoc = null;
		if (usuario != null) {
			userDoc = this.docenteRepository.findByUsuarioDocente(usuario);
		}

		Docente docente = null;
		if (userDoc != null) {
			docente = this.docenteRepository.findOne(userDoc.getId());
		}

		List<ClassificacaoCliente> classificacoes = new ArrayList<ClassificacaoCliente>();
		HistoricoAlunosTurma cls = new HistoricoAlunosTurma();

		List<HistoricoAluno> resultado = this.repositoryHistoricoAluno
				.findByTurmaAndDisciplinaAndAnoLectivoOrderByMatriculaAlunoNomeAsc(turma, disciplina, anoLectivo);

		
		if (resultado.isEmpty()) {
			c.setMensagem("Nenhum aluno encontrado.");
			c.setCodigo(300);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		// PERMISSÃO NORMAL
		// findByEmCursoAndProvaEstadoAndTipoDisciplina
		// findByFaculdadeAndProvaAndPermissao
		// VERIFICAR SE EXISTE AUTORIZAÇÃO PARA FACULDADE
		// GUY
		List<AutorizacaoLancamentoNota> provas = this.autorizacaoRepository.findByEmCursoAndProvaEstadoAndTipoDisciplina(true, true, disciplina.getTipo());
		
		List<Prova> prs = new ArrayList<Prova>();
		Map<Integer, Prova> mapaA = new HashMap<Integer, Prova>();
		for (AutorizacaoLancamentoNota au : provas) {
			List<LancamentoFaculdade> lanamcento = this.lancamentoFaculdadeRepository.findByFaculdadeAndProvaAndPermissao(disciplina.getCurso().getFaculdade(), au.getProva(),Permissao.GRAVAR);
			if (!lanamcento.isEmpty()) {
				prs.add(au.getProva());
				mapaA.put(au.getProva().getId(), au.getProva());
			}
		}

		// PERMISSÃO PARA LANÇAMENTO DE NOTA ESPECIAL ESPECIAL
		List<AutorizacaoLancamentoEspecial> auEspecial;
		if (docente != null && turma != null && disciplina != null && anoLectivo != null) {
			auEspecial = this.autorizacaoLancamentoEspecialRepository
					.findByDocenteAndDisciplinaAndAnoLectivoAndTurmaAndEmCurso(docente, disciplina, anoLectivo, turma,true);
			for (AutorizacaoLancamentoEspecial au : auEspecial) {
				if (!mapaA.containsKey(au.getProva().getId())) {
					prs.add(au.getProva());
				}
			}
		}

		ClassificacaoCliente classificacao;

		for (HistoricoAluno ha : resultado) {

			classificacao = new ClassificacaoCliente();

			 AlunoAnexo fotoAluno = this.alunoAnexoRepository.findByAlunoNumeroDeAluno(ha.getAluno().getNumeroDeAluno());
				 
			 if (fotoAluno!=null) 
				 classificacao.setFoto(fotoAluno.getFoto());
			
			
			classificacao.setId(ha.getId());
			classificacao.setNumeroDeAluno(Integer.parseInt(ha.getAluno().getNumeroDeAluno()));
			classificacao.setNome(ha.getAluno().getNome());

			// NOTAS E AVALIAÇÕES
			// 01
			classificacao.setPrimeiraAvaliacao(ha.getAvaliacao1());
			// 02o
			classificacao.setSegundaAvaliacao(ha.getAvaliacao2());
			// 03
			classificacao.setPrimeiroTrabalho(ha.getAvaliacao3());
			// 04
			classificacao.setSegundoTrabalho(ha.getAvaliacao4());
			// 05
			classificacao.setEpocaEspecial(ha.getNotaEpocaEspecial());
			// 06
			classificacao.setExame(ha.getNotaExame());
			// 07
			classificacao.setMelhorNota(ha.getMelhoriaNota());
			// 08
			classificacao.setRecurso(ha.getNotaRecurso());
			// 09
			classificacao.setExameOral(ha.getNotaExameOral());
			// 10

			classificacao.setNotaFinalContinua(ha.getNotaFinalContinua());

			// 11
			classificacao.setNotaFinal(ha.getNotaFinal());
			// 12
			classificacao.setPratica(ha.getNotaPratica());
			classificacao.setValidada(ha.isValidada());
			classificacao.setTipoDisciplina(ha.getDisciplina().getTipo());

			System.out.println("Aqui null " + ha.isSemFrequencia() + " / " + ha.getId());
			classificacao.setSemFrequencia(ha.isSemFrequencia());

			classificacao.setValidada(ha.isValidada());

			classificacao.setAprovado(ha.isAprovado());

			classificacao.setContencioso(ha.getAluno().isContencioso());

			//if(ha.getAluno().getCurso().getGrau())
			List<InscricaoExtraordinaria> inscricaoExtraOrnaria = this.inscricaoExtraordinariaRepository.findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidada(anoLectivo, ha.getAluno(),disciplina, true);

			
			Contador contador = this.contadorRepository.findOne(33);
			//PIPA
			//NÃO AVALIAR INCRIÇÃO EXTREAORIDNÁRIA
			if(contador.getProximoValor()==1) {
				if(ha.getAluno().getCurso().getGrau()==Grau.MESTRADO) {
					inscricaoExtraOrnaria = this.inscricaoExtraordinariaRepository.findByAlunoAndDisciplinaAndGuiaPagamentoLiquidada(ha.getAluno(),disciplina, true);
				}
				if (!inscricaoExtraOrnaria.isEmpty()) {
					classificacao.setPodeRecurso(true);
				}				
			}else {
				classificacao.setPodeRecurso(true);
			}

			// if(ha.getNumeroDeAluno().equals("181267")) {
			classificacoes.add(classificacao);
			// }
		}

		// resultado
		int totalDeAlunos = resultado.size();

		cls.setTotalDeAlunos(totalDeAlunos);

		// cls.setValidar(true);

		cls.setProvas(prs);
		cls.setAnoLectivo(anoLectivo.getId());
		cls.setDisciplina(disciplina.getId());
		cls.setAnoCurricular(disciplina.getAnoCorricular());
		cls.setTurma(turma.getId());
		cls.setHistoricoAlunos(classificacoes);
		c.setResultado(cls);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/auditoriaNota/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> auditoriaNota(@PathVariable Integer id) {
		HistoricoAluno historicoAluno = this.repositoryHistoricoAluno.findOne(id);
		if (historicoAluno == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Erro ao validar Histórico da classificação.");
		} else {
			AuditoriaNotaCliente nota = new AuditoriaNotaCliente();
			nota.setUsuarioPrimeiraAvaliacao(historicoAluno.getUsuarioPrimeiraFrequencia() == null ? null
					: historicoAluno.getUsuarioPrimeiraFrequencia().getName());
			nota.setDataPrimeiraAvaliacao(historicoAluno.getDataPrimeiraFrequencia());
			nota.setUsuarioSegundaAvaliacao(historicoAluno.getUsuarioSegundaFrequencia() == null ? null
					: historicoAluno.getUsuarioSegundaFrequencia().getName());
			nota.setDataSegundaAvaliacao(historicoAluno.getDataSegundaFrequencia());
			nota.setUsuarioTerceiraAvaliacao(historicoAluno.getUsuarioTerceiraFrequencia() == null ? null
					: historicoAluno.getUsuarioTerceiraFrequencia().getName());
			nota.setDataTerceiraAvaliacao(historicoAluno.getDataTerceiraFrequencia());
			nota.setUsuarioQuartaAvaliacao(historicoAluno.getUsuarioQuartaFrequencia() == null ? null
					: historicoAluno.getUsuarioQuartaFrequencia().getName());
			nota.setDataquartaAvaliacao(historicoAluno.getDataQuartaFrequencia());
			nota.setUsuarioExameAvaliacao(
					historicoAluno.getUsuarioExame() == null ? null : historicoAluno.getUsuarioExame().getName());
			nota.setDataExameAvaliacao(historicoAluno.getDataExame());
			nota.setUsuarioExameOralAvaliacao(historicoAluno.getUsuarioExameOral() == null ? null
					: historicoAluno.getUsuarioExameOral().getName());
			nota.setDataExameOralAvaliacao(historicoAluno.getDataExameOral());
			nota.setUsuarioRecursoAvaliacao(
					historicoAluno.getUsuarioRecurso() == null ? null : historicoAluno.getUsuarioRecurso().getName());
			nota.setDataRecursoAvaliacao(historicoAluno.getDataRecurso());
			nota.setUsuarioEpocaEspcialAvaliacao(
					historicoAluno.getUsuarioEspecial() == null ? null : historicoAluno.getUsuarioEspecial().getName());
			nota.setDataEpocaEspcialAvaliacao(historicoAluno.getDataNotaEpocaEspecialOral());
			nota.setUsuarioVeraoAvaliacao(historicoAluno.getUsuarioCursoVerao() == null ? null
					: historicoAluno.getUsuarioCursoVerao().getName());
			nota.setDataVeraoAvaliacao(historicoAluno.getDataNotaCursoVerao());
			nota.setUsuarioMelhoriaAvaliacao(
					historicoAluno.getUsuarioMelhoria() == null ? null : historicoAluno.getUsuarioMelhoria().getName());
			nota.setDataMelhoriaAvaliacao(historicoAluno.getDataMelhoria());
			return ObjectoDeRetornoParcial.MensagemDeRetorno(nota, 0, null);
		}
	}

	@RequestMapping(value = "/buscarDisciplinaPorAluno", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarDisciplinaPorAluno(@RequestBody TurmaAndDisciplinaCliente td) {
		ResponseCliente c = new ResponseCliente();

		AnoLectivo anoLectivo = this.anoLectivoRepository.findByAnoLectivo(td.getAnoLectivo());
		

		
		Disciplina disciplina = this.disciplinaRepository.findOne(td.getDisciplina());
		
	
		
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(td.getNumeroDeAluno().toString());
		
		
		
		List<HistoricoAluno> hts = this.repositoryHistoricoAluno.findByAlunoAndMatriculaAnoLectivoAndDisciplina(aluno,anoLectivo, disciplina);

	
		
		Instituicao instituicacao = this.instituicaoRepository.findOne(2);

		HistoricoAluno ha = null;
		for (HistoricoAluno o : hts) {
			ha = o;
		}


		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		Iterable<Prova> provas = this.provaRepository.findAll();
		List<Prova> pvs = new ArrayList<Prova>();

		if (instituicao.getSigla().equals("UGS")) {
			for (Prova pv : provas) {
				if (disciplina.getTipo() == TipoDisciplina.PRIMEIRO_SEMESTRE) {
					if (pv.getId() != 4 && pv.getId() != 5)
						pvs.add(pv);
				}

				if (disciplina.getTipo() == TipoDisciplina.SEGUNDO_SEMESTRE) {
					if (pv.getId() != 1 && pv.getId() != 2)
						pvs.add(pv);
				}

				if (disciplina.getTipo() == TipoDisciplina.ANUAL) {
					pvs.add(pv);
				}

				if (disciplina.getTipo() == TipoDisciplina.PROJECTO) {
					pvs.add(pv);
				}
			}
		} else {
			for (Prova pv : provas) {
				pvs.add(pv);
			}
		}

		ClassificarUmAluno classificacao = new ClassificarUmAluno();
		classificacao.setProvas(pvs);

		if (instituicacao.getSigla().equals("IGS")) {
			List<Prova> findAll = (List<Prova>) this.provaRepository.findAll();
			classificacao.setProvas(findAll);
		}

		if (ha != null) {
			// ID
			classificacao.setId(ha.getId());
			// NOTAS E AVALIAÇÕES
			// 01
			classificacao.setPrimeiraAvaliacao(ha.getAvaliacao1());
			classificacao.setDataPrimeiraAvaliacao(ha.getDataPrimeiraFrequencia());
			if (ha.getUsuarioPrimeiraFrequencia() != null)
				classificacao.setUsuarioAv1(ha.getUsuarioPrimeiraFrequencia().getName());
			// 02

			classificacao.setSegundaAvaliacao(ha.getAvaliacao2());
			classificacao.setDataSegundaAvaliacao(ha.getDataSegundaFrequencia());
			if (ha.getUsuarioSegundaFrequencia() != null)
				classificacao.setUsuarioAv2(ha.getUsuarioSegundaFrequencia().getName());
			// 03
			classificacao.setPrimeiroTrabalho(ha.getAvaliacao3());
			classificacao.setDataPrimeiroTrabalho(ha.getDataTerceiraFrequencia());
			if (ha.getUsuarioTerceiraFrequencia() != null)
				classificacao.setUsuarioPrimeiroTrabaho(ha.getUsuarioTerceiraFrequencia().getName());

			// 04
			classificacao.setSegundoTrabalho(ha.getAvaliacao4());
			classificacao.setDataSegundoTrabalho(ha.getDataQuartaFrequencia());
			if (ha.getUsuarioQuartaFrequencia() != null)
				classificacao.setUsuarioSegundoTrabaho(ha.getUsuarioQuartaFrequencia().getName());
			// 05
			classificacao.setEpocaEspecial(ha.getNotaEpocaEspecial());
			classificacao.setDataEspecial(ha.getDataNotaEpocaEspecial());
			if (ha.getUsuarioEspecial() != null)
				classificacao.setUsuarioEpocaEspecial(ha.getUsuarioEspecial().getName());
			// 06
			classificacao.setExame(ha.getNotaExame());
			classificacao.setDataExame(ha.getDataExame());
			if (ha.getUsuarioExame() != null)
				classificacao.setUsuarioExame(ha.getUsuarioExame().getName());
			// 07
			classificacao.setMelhorNota(ha.getMelhoriaNota());
			classificacao.setDataMelhoriaNota(ha.getDataMelhoria());
			if (ha.getUsuarioMelhoria() != null)
				classificacao.setUsuarioMelhoria(ha.getUsuarioMelhoria().getName());
			// 08
			classificacao.setRecurso(ha.getNotaRecurso());
			classificacao.setDataRecurso(ha.getDataRecurso());
			if (ha.getUsuarioRecurso() != null)
				classificacao.setUsuarioPratica(ha.getUsuarioRecurso().getName());
			// 09
			classificacao.setExameOral(ha.getNotaExameOral());
			classificacao.setDataOralExame(ha.getDataExameOral());
			if (ha.getUsuarioExameOral() != null)
				classificacao.setUsuarioExameOral(ha.getUsuarioExameOral().getName());

			// 10
			classificacao.setNotaFinalContinua(ha.getNotaFinalContinua());
			classificacao.setDataNotaFinalContinua(ha.getDataNotaFinalContinua());
			// 11
			classificacao.setNotaFinal(ha.getNotaFinal());
			classificacao.setDataNotaFinal(ha.getDataNotaFinal());

			// 12
			classificacao.setPratica(ha.getNotaPratica());
			classificacao.setDataPratica(ha.getDataNotaPratica());
			if (ha.getUsuarioPratica() != null)
				classificacao.setUsuarioPratica(ha.getUsuarioPratica().getName());

			// 13
			classificacao.setValidada(ha.isValidada());
			// 14
			classificacao.setTipoDisciplina(ha.getDisciplina().getTipo());
		}

		if (ha != null)
			classificacao.setSemFrequencia(ha.isSemFrequencia());
		c.setResultado(classificacao);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// UM ALUNO
	@RequestMapping(value = "/classificarUmAluno", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> classificarUmAluno(@RequestBody ModeloAlteracaoNota classifique) {
		ResponseCliente c = new ResponseCliente();
		String mensagem = "";
		
		HistoricoAluno hAluno = this.repositoryHistoricoAluno.findOne(classifique.getId());
		
		
		Prova au = this.provaRepository.findOne(classifique.getProva());
		Usuario usuario = this.usuarioRepository.findByUserName(classifique.getUserName());

		//PUTY
		// @SuppressWarnings("unused")
		Contador contador = this.contadorRepository.findOne(33);

		Map<Integer, TipoProvaExtraOrdinaria> mapa = new HashMap<Integer, TipoProvaExtraOrdinaria>();

		List<Prova> provas = this.provaRepository.findByExtraordinaria(true);
		for (Prova prova : provas) {
			if (prova.getId() == au.getId()) {
				if (prova.getId() == 6) {
					mapa.put(au.getId(), TipoProvaExtraOrdinaria.ER);
				}

				if (prova.getId() == 12) {
					mapa.put(au.getId(), TipoProvaExtraOrdinaria.EE);
				}

				if (prova.getId() == 22) {
					mapa.put(au.getId(), TipoProvaExtraOrdinaria.M);
				}

				if (prova.getId() == 24) {
					mapa.put(au.getId(), TipoProvaExtraOrdinaria.CV);
				}
				if (prova.getId() == 25) {
					mapa.put(au.getId(), TipoProvaExtraOrdinaria.PE);
				}
			}
		}

		// CHOCOTONA

		TipoProvaExtraOrdinaria provaExtra = mapa.get(au.getId());

		List<InscricaoExtraordinaria> pagouExtraordinaria = this.inscricaoExtraordinariaRepository
				.findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidadaAndProva(hAluno.getAnoLectivo(),
						hAluno.getAluno(), hAluno.getDisciplina(), true, provaExtra);

		if (contador != null) {
			if (contador.getProximoValor() == 1) {
				if (au != null) {
					if (au.isExtraordinaria()) {
						if (pagouExtraordinaria.isEmpty()) {
							c.setMensagem("verifique o pagamento de exa. especial");
							c.setCodigo(ResponseCode.values()[2].getDescricao());
							return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
						}
					}
				}
			}
		}

		
		if (au != null)
			mensagem = au.getProva();

		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		if (instituicao.getSigla().equals("UGS")) {
			// PRIMEIRA AVALIAÇÃO
			if (au.getId() == 1) {
				if (hAluno.getDisciplina().getTipo().equals(TipoDisciplina.PRIMEIRO_SEMESTRE)
						|| hAluno.getDisciplina().getTipo().equals(TipoDisciplina.ANUAL)) {
					hAluno.setAvaliacao1(classifique.getNota());
					hAluno.setNotaFinalContinua(classifique.getNota());
					hAluno.setNotaFinal(classifique.getNota());
					hAluno.setValidada(false);
					hAluno.setDataPrimeiraFrequencia(new Date());
					hAluno.setUsuarioPrimeiraFrequencia(usuario);
				} else {
					c.setMensagem("disciplina selecionada não suporta a prova.");
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}

			// SEGUNDA AVALIAÇÃO
			if (au.getId() == 2) {
				if (hAluno.getDisciplina().getTipo().equals(TipoDisciplina.PRIMEIRO_SEMESTRE)
						|| hAluno.getDisciplina().getTipo().equals(TipoDisciplina.ANUAL)) {
					hAluno.setAvaliacao2(classifique.getNota());
					hAluno.setNotaFinalContinua(classifique.getNota());
					hAluno.setNotaFinal(classifique.getNota());
					hAluno.setDataSegundaFrequencia(new Date());
					hAluno.setUsuarioSegundaFrequencia(usuario);
					hAluno.setValidada(false);
				} else {
					c.setMensagem("disciplina selecionada não suporta a prova.");
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}
			// PRÁTICA
			if (au.getId() == 3) {
				hAluno.setNotaPratica(classifique.getNota());
				hAluno.setNotaFinalContinua(classifique.getNota());
				hAluno.setNotaFinal(classifique.getNota());
				hAluno.setDataNotaPratica(new Date());
				hAluno.setUsuarioPratica(usuario);
				hAluno.setValidada(false);
			}
			// TERCEIRA AVALIAÇÃO
			if (au.getId() == 4) {
				if (hAluno.getDisciplina().getTipo().equals(TipoDisciplina.SEGUNDO_SEMESTRE)
						|| hAluno.getDisciplina().getTipo().equals(TipoDisciplina.ANUAL)) {
					hAluno.setAvaliacao3(classifique.getNota());
					hAluno.setNotaFinalContinua(classifique.getNota());
					hAluno.setNotaFinal(classifique.getNota());
					hAluno.setDataTerceiraFrequencia(new Date());
					hAluno.setUsuarioTerceiraFrequencia(usuario);
					hAluno.setValidada(false);
				} else {
					c.setMensagem("disciplina selecionada não suporta a prova.");
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}

			}
			// QUARTA AVALIAÇÃO
			if (au.getId() == 5) {
				if (hAluno.getDisciplina().getTipo().equals(TipoDisciplina.SEGUNDO_SEMESTRE)
						|| hAluno.getDisciplina().getTipo().equals(TipoDisciplina.ANUAL)) {
					hAluno.setAvaliacao4(classifique.getNota());
					hAluno.setDataQuartaFrequencia(new Date());
					hAluno.setUsuarioQuartaFrequencia(usuario);
					hAluno.setValidada(false);
				} else {
					c.setMensagem("disciplina selecionada não suporta a prova.");
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}
			// SALVAR OS DADOS DAS FREQUENCIAS.
			hAluno.setUltimaModificacao(new Date());
			this.repositoryHistoricoAluno.save(hAluno);
			this.historicoAlunoService.gerarHistorico(hAluno);

			Float media;
			// VALIDAR MÉDIA PARA DISCIPLINA DO PRIMEIRO SEMESTRE.
			if (hAluno.getDisciplina().getTipo() == TipoDisciplina.PRIMEIRO_SEMESTRE) {
				if (hAluno.getAvaliacao1() != null && hAluno.getAvaliacao2() != null) {
					media = (hAluno.getAvaliacao1() + hAluno.getAvaliacao2()) / 2;

					float mediaRoud = Math.round(media);
					// NOTA FINAL CONTINUA
					hAluno.setNotaFinalContinua(mediaRoud);

					// NOTA FINAL
					if (mediaRoud >= 12) {
						hAluno.setNotaFinal(mediaRoud);
						hAluno.setAprovado(true);
						hAluno.setValidada(false);
						hAluno.setSituacao(Situacao.APROVADO);
						hAluno.setValidada(false);
					} else {
						hAluno.setNotaFinal(null);
					}
				}
			}

			// VALIDAR MÉDIA PARA DISCIPLINA DO SEGUNDO SEMESTRE.
			if (hAluno.getDisciplina().getTipo() == TipoDisciplina.SEGUNDO_SEMESTRE) {
				if (hAluno.getAvaliacao3() != null && hAluno.getAvaliacao4() != null) {
					media = (hAluno.getAvaliacao3() + hAluno.getAvaliacao4()) / 2;

					float mediaRoud = Math.round(media);
					// NOTA FINAL CONTINUA
					hAluno.setNotaFinalContinua(mediaRoud);

					// NOTA FINAL
					if (mediaRoud >= 12) {
						hAluno.setNotaFinal(mediaRoud);
						hAluno.setSituacao(Situacao.APROVADO);
						hAluno.setAprovado(true);
						hAluno.setValidada(false);
					} else {
						hAluno.setNotaFinal(null);
					}
				}
			}

			// VALIDAR MÉDIA PARA DISCIPLINA DA ANUAL
			if (hAluno.getDisciplina().getTipo() == TipoDisciplina.ANUAL) {
				if (hAluno.getAvaliacao1() != null && hAluno.getAvaliacao2() != null && hAluno.getAvaliacao3() != null
						&& hAluno.getAvaliacao4() != null) {
					media = (hAluno.getAvaliacao1() + hAluno.getAvaliacao2() + hAluno.getAvaliacao3()
							+ hAluno.getAvaliacao4()) / 4;

					float mediaRoud = Math.round(media);
					// NOTA FINAL CONTINUA
					hAluno.setNotaFinalContinua(mediaRoud);

					// NOTA FINAL
					if (mediaRoud >= 12) {
						hAluno.setNotaFinal(mediaRoud);
						hAluno.setSituacao(Situacao.APROVADO);
						hAluno.setAprovado(true);
						hAluno.setValidada(false);
					} else {
						hAluno.setNotaFinal(null);
					}
				}

			}
			hAluno.setUltimaModificacao(new Date());
			this.repositoryHistoricoAluno.save(hAluno);
			this.historicoAlunoService.gerarHistorico(hAluno);
			if (au.getId() == 1 || au.getId() == 2 || au.getId() == 4 || au.getId() == 5) {
				c.setMensagem(mensagem + " alterada com sucesso");
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			// FINAL VERIFICAÇÃO....
		} else {
			// PRIMEIRA AVALIAÇÃO
			if (au.getId() == 1) {
				hAluno.setAvaliacao1(classifique.getNota());
				hAluno.setDataPrimeiraFrequencia(new Date());
				hAluno.setUsuarioPrimeiraFrequencia(usuario);
			}

			// SEGUNDA AVALIAÇÃO
			if (au.getId() == 2) {
				hAluno.setAvaliacao2(classifique.getNota());
				hAluno.setNotaFinalContinua(classifique.getNota());
				
				hAluno.setDataSegundaFrequencia(new Date());
				hAluno.setUsuarioSegundaFrequencia(usuario);
			}
			// PRÁTICA
			if (au.getId() == 3) {
				hAluno.setNotaPratica(classifique.getNota());
				hAluno.setNotaFinalContinua(classifique.getNota());
				
				hAluno.setDataNotaPratica(new Date());
				hAluno.setUsuarioPratica(usuario);
			}
			// TERCEIRA AVALIAÇÃO
			if (au.getId() == 4) {
				hAluno.setAvaliacao3(classifique.getNota());
				hAluno.setNotaFinalContinua(classifique.getNota());
				
				hAluno.setDataTerceiraFrequencia(new Date());
				hAluno.setUsuarioTerceiraFrequencia(usuario);
			}
			// QUARTA AVALIAÇÃO
			if (au.getId() == 5) {
				hAluno.setAvaliacao4(classifique.getNota());
				hAluno.setNotaFinalContinua(classifique.getNota());
				
				hAluno.setDataQuartaFrequencia(new Date());
				hAluno.setUsuarioQuartaFrequencia(usuario);
			}
			
			//**********************************************
			float comulativa = 0;
			float notaPoderada = 0;

			int pf1 = 35, pf2 = 35, pt1 = 10, pt2 = 10, po = 10;

			if (hAluno.getAvaliacao1() != null) {
				notaPoderada = hAluno.getAvaliacao1() * pf1;
				comulativa += notaPoderada;
			}
			if (hAluno.getAvaliacao2() != null) {
				notaPoderada = hAluno.getAvaliacao2() * pf2;
				comulativa += notaPoderada;
			}
			if (hAluno.getAvaliacao3() != null) {
				notaPoderada = hAluno.getAvaliacao3() * pt1;
				comulativa += notaPoderada;
			}
			if (hAluno.getAvaliacao4() != null) {
				notaPoderada = hAluno.getAvaliacao4() * pt2;
				comulativa += notaPoderada;
			}
			if (hAluno.getNotaPratica() != null) {
				notaPoderada = hAluno.getNotaPratica() * po;
				comulativa += notaPoderada;
			}

			int somaDePesos = pf1 + pf2 + pt1 + pt2 + po;

			float mediaF = comulativa / somaDePesos;
			
			float mediaRoudF = Math.round(mediaF);

			// NOTA FINAL CONTINUA
			hAluno.setNotaFinalContinua(mediaRoudF);
			// NOTA FINAL
			if (mediaRoudF >= 13) {
				hAluno.setNotaFinal(mediaRoudF);
				hAluno.setAprovado(true);
				hAluno.setSituacao(Situacao.APROVADO);
				hAluno.setDataNotaFinal(new Date());
			} else {
				hAluno.setSituacao(Situacao.REPROVADO);
				hAluno.setAprovado(false);
				hAluno.setNotaFinal(null);
			}
			//**********************************************
			
			// SALVAR OS DADOS DAS FREQUENCIAS.
			hAluno.setUltimaModificacao(new Date());
			this.repositoryHistoricoAluno.save(hAluno);
			this.historicoAlunoService.gerarHistorico(hAluno);

			Float media;
			// VALIDAR MÉDIA PARA DISCIPLINA DO PRIMEIRO SEMESTRE.
			if (hAluno.getDisciplina().getTipo() == TipoDisciplina.PRIMEIRO_SEMESTRE) {
				if (hAluno.getAvaliacao1() != null && hAluno.getAvaliacao2() != null) {
					media = (hAluno.getAvaliacao1() + hAluno.getAvaliacao2()) / 2;

					float mediaRoud = Math.round(media);
					// NOTA FINAL CONTINUA
					hAluno.setNotaFinalContinua(mediaRoud);

					// NOTA FINAL
					if (mediaRoud >= 12) {
						hAluno.setNotaFinal(mediaRoud);
						hAluno.setAprovado(true);
						hAluno.setSituacao(Situacao.APROVADO);
					} else {
						hAluno.setNotaFinal(null);
					}
				}
			}

			// NOCOLAU
			// VALIDAR MÉDIA PARA DISCIPLINA DO SEGUNDO SEMESTRE.
			if (hAluno.getDisciplina().getTipo() == TipoDisciplina.SEGUNDO_SEMESTRE) {
				if (hAluno.getAvaliacao3() != null && hAluno.getAvaliacao4() != null) {
					media = (hAluno.getAvaliacao3() + hAluno.getAvaliacao4()) / 2;

					float mediaRoud = Math.round(media);
					// NOTA FINAL CONTINUA
					hAluno.setNotaFinalContinua(mediaRoud);

					// NOTA FINAL
					if (mediaRoud >= 13) {
						hAluno.setNotaFinal(mediaRoud);
						hAluno.setSituacao(Situacao.APROVADO);
						hAluno.setAprovado(true);
					} else {
						hAluno.setNotaFinal(null);
					}
				}
			}

			// VALIDAR MÉDIA PARA DISCIPLINA DA ANUAL
			if (hAluno.getDisciplina().getTipo() == TipoDisciplina.ANUAL) {
				if (hAluno.getAvaliacao1() != null && hAluno.getAvaliacao2() != null && hAluno.getAvaliacao3() != null
						&& hAluno.getAvaliacao4() != null) {
					media = (hAluno.getAvaliacao1() + hAluno.getAvaliacao2() + hAluno.getAvaliacao3()
							+ hAluno.getAvaliacao4()) / 4;

					float mediaRoud = Math.round(media);
					// NOTA FINAL CONTINUA
					hAluno.setNotaFinalContinua(mediaRoud);

					// NOTA FINAL
					if (mediaRoud >= 12) {
						hAluno.setNotaFinal(mediaRoud);
						hAluno.setSituacao(Situacao.APROVADO);
						hAluno.setAprovado(true);
					} else {
						hAluno.setNotaFinal(null);
					}
				}

			}
			hAluno.setUltimaModificacao(new Date());
			this.repositoryHistoricoAluno.save(hAluno);
			this.historicoAlunoService.gerarHistorico(hAluno);
			if (au.getId() == 1 || au.getId() == 2 || au.getId() == 4 || au.getId() == 5 || au.getId() == 3) {
				c.setMensagem(mensagem + " alterada com sucesso");
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		}

		// EXAME
		if (au.getId() == 10) {
			hAluno.setNotaExame(classifique.getNota());
			hAluno.setNotaFinal(hAluno.getNotaFinalContinua());
			hAluno.setSituacao(Situacao.REPROVADO);
			hAluno.setAprovado(false);
			hAluno.setValidada(false);
			hAluno.setDataExame(new Date());
			hAluno.setUsuarioExame(usuario);
		}
		// EXAME ORAL
		if (au.getId() == 11) {
			hAluno.setNotaExameOral(classifique.getNota());
			hAluno.setValidada(false);
			hAluno.setDataExameOral(new Date());
			hAluno.setUsuarioExameOral(usuario);
		}

		aplicarMediaFinal(classifique, hAluno);
		hAluno.setUltimaModificacao(new Date());
		this.repositoryHistoricoAluno.save(hAluno);
		this.historicoAlunoService.gerarHistorico(hAluno);
		if (au.getId() == 10 || au.getId() == 11) {
			c.setMensagem(mensagem + " alterada com sucesso");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		// RECURSO
		if (au.getId() == 6) {
			hAluno.setNotaRecurso(classifique.getNota());
			hAluno.setNotaFinal(hAluno.getNotaExame());
			hAluno.setSituacao(Situacao.REPROVADO);
			hAluno.setAprovado(false);
			hAluno.setValidada(false);
			hAluno.setDataRecurso(new Date());
			hAluno.setUsuarioRecurso(usuario);

		}
		// RECURSO ORAL
		if (au.getId() == 9) {
			hAluno.setNotaRecursoOral(classifique.getNota());
			hAluno.setValidada(false);
			hAluno.setDataNotaRecursoOral(new Date());
			hAluno.setUsuarioRecursoOral(usuario);
		}

		// ÉPOCA ESPECIAL
		if (au.getId() == 12) {
			hAluno.setNotaEpocaEspecial(classifique.getNota());
			hAluno.setValidada(false);
			hAluno.setDataNotaEpocaEspecial(new Date());
			hAluno.setUsuarioEspecial(usuario);
		}

		// ORAL ÉPOCA ESPECIAL
		if (au.getId() == 13) {
			hAluno.setNotaEpocaEspecialOral(classifique.getNota());
			hAluno.setValidada(false);
			hAluno.setDataNotaEpocaEspecialOral(new Date());
			hAluno.setUsuarioEspecialOral(usuario);
		}
		// MELHORIA
		if (au.getId() == 22) {
			hAluno.setMelhoriaNota(classifique.getNota());
			hAluno.setValidada(false);
			hAluno.setDataMelhoria(new Date());
			hAluno.setUsuarioMelhoria(usuario);
		}
		// MELHORIA ORAL
		if (au.getId() == 23) {
			hAluno.setMelhoriaNotaOral(classifique.getNota());
			hAluno.setValidada(false);
			hAluno.setDataMelhoriaOral(new Date());
			hAluno.setUsuarioMelhoriaOral(usuario);
		}
		// VERÃO
		if (au.getId() == 24) {
			hAluno.setNotaCursoDeVerao(classifique.getNota());
			hAluno.setValidada(false);
			hAluno.setDataNotaCursoVerao(new Date());
			hAluno.setUsuarioCursoVerao(usuario);
		}

		// PROVA EXTRAORDINARIA
		if (au.getId() == 25) {
			hAluno.setDataNotaExtraordinaria(new Date());
			hAluno.setNotaExtraodinaria(classifique.getNota());
			hAluno.setValidada(false);
			hAluno.setUsuarioExtraordinaria(usuario);
		}

		// VALIDAR MÉDIA PARA DISCIPLINA DO PROJECT
		// PRECISAMOS VER
		if (hAluno.getDisciplina().getTipo() == TipoDisciplina.PROJECTO) {
			hAluno.setProjectoNota(classifique.getNota());
			hAluno.setDataNotaProjecto(new Date());
			hAluno.setValidada(false);
			hAluno.setUsuarioNotaProjecto(usuario);
		}

		aplicarMediaFinal(classifique, hAluno);
		hAluno.setUltimaModificacao(new Date());
		this.repositoryHistoricoAluno.save(hAluno);
		this.historicoAlunoService.gerarHistorico(hAluno);
		c.setMensagem(mensagem + " alterada com sucesso");
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	private void aplicarMediaFinal(ModeloAlteracaoNota classifique, HistoricoAluno hAluno) {
		if (classifique.getNota() != null) {
			float notaFinal = Math.round(classifique.getNota());
			hAluno.setNotaFinal(notaFinal);
			hAluno.setDataNotaFinal(new Date());
			if (classifique.getNota() >= 10) {
				hAluno.setSituacao(Situacao.APROVADO);
				hAluno.setAprovado(true);
			} else {
				hAluno.setSituacao(Situacao.REPROVADO);
				hAluno.setAprovado(false);
			}
		}
	}
	
	@RequestMapping(value = "/validarTodasNotas", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> validarTodasNotas(@RequestBody ValidarNotasDTO validar){
		
		ResponseCliente c = new ResponseCliente();
		
		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(validar.getAnoLectivo());
		Curso curso = this.cursoRepository.findByIdAndStatus(validar.getCurso(), true);
		
		if(!validar.isValidar()) {
			
			c.setMensagem("Deve preencher o campo validar.");
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		List<HistoricoAluno> notasPorValidar = this.repositoryHistoricoAluno.validarNotas(anoLectivo.getId(), curso.getId(), validar.getDisciplina() ,validar.getTurma());
		for (HistoricoAluno historicoAluno : notasPorValidar) {
			
			historicoAluno.setValidada(validar.isValidar());
			historicoAluno.setDataValidacao(new Date());
			this.repositoryHistoricoAluno.save(historicoAluno);
		}
		
		c.setMensagem("validações de notas feitas com sucesso.");
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/classificar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> classificar(@RequestBody HistoricoAlunosTurma classificar) {
		ResponseCliente c = new ResponseCliente();
		List<ClassificacaoCliente> als = classificar.getHistoricoAlunos();
		Usuario usuario = this.usuarioRepository.findByUserCode(classificar.getUserCode());

		Prova prova = this.provaRepository.findOne(classificar.getProva());

		// VALIDAÇÃO
		//int totalValidadas = 0;
		if (classificar.isValidar()) {
			for (ClassificacaoCliente classifique : als) {
				HistoricoAluno hAluno = this.repositoryHistoricoAluno.findOne(classifique.getId());
				hAluno.setValidada(classifique.isValidada());
				hAluno.setUsuarioValidou(usuario);
				hAluno.setDataValidacao(new Date());
				
				
				hAluno.setUltimaModificacao(new Date());
				this.repositoryHistoricoAluno.save(hAluno);
				this.historicoAlunoService.gerarHistorico(hAluno);
			}
			c.setResultado(classificar);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem("Foram validadas ");
			//c.setMensagem("Foram validadas " + totalValidadas + " notas");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		if (prova != null) {

			// BUSCA A INSTITUIÇÃO PARA CREDENCIAR O A INSTITUIÇÃO
			Instituicao instituicao = this.instituicaoRepository.findOne(2);

			// INICIO UGS
			if (instituicao.getSigla().equals("UGS")) {
				for (ClassificacaoCliente classifique : als) {
					
					//--------------------------------------------------------------------------------
					HistoricoAluno hAluno = this.repositoryHistoricoAluno.findOne(classifique.getId());
					if(!hAluno.isValidada()) {
						// PRIMEIRA AVALIAÇÃO
						if (classificar.getProva() == 1) {
							hAluno.setAvaliacao1(classifique.getPrimeiraAvaliacao());
							hAluno.setDataPrimeiraFrequencia(new Date());
							hAluno.setUsuarioPrimeiraFrequencia(usuario);
						}
						// SEGUNDA AVALIAÇÃO
						if (classificar.getProva() == 2) {
							hAluno.setAvaliacao2(classifique.getSegundaAvaliacao());
							hAluno.setDataSegundaFrequencia(new Date());
							hAluno.setUsuarioSegundaFrequencia(usuario);
						}
						
						// PRÁTICA
						if (classificar.getProva() == 3) {
							hAluno.setNotaPratica(classifique.getPratica());
							hAluno.setDataNotaPratica(new Date());
							hAluno.setUsuarioPratica(usuario);
							
						}
						
						// TERCEIRA AVALIAÇÃO
						if (classificar.getProva() == 4) {
							hAluno.setAvaliacao3(classifique.getPrimeiroTrabalho());
							hAluno.setDataTerceiraFrequencia(new Date());
							hAluno.setUsuarioTerceiraFrequencia(usuario);
						}
						
						// QUARTA AVALIAÇÃO
						if (classificar.getProva() == 5) {
							hAluno.setAvaliacao4(classifique.getSegundoTrabalho());
							hAluno.setDataQuartaFrequencia(new Date());
							hAluno.setUsuarioQuartaFrequencia(usuario);
						}
						// COLOCAR A VALIDAÇÃO DAS FREQUENCIAS...
						hAluno.setUltimaModificacao(new Date());
						this.repositoryHistoricoAluno.save(hAluno);
						this.historicoAlunoService.gerarHistorico(hAluno);
						
						Float media;
						// VALIDAR MÉDIA PARA DISCIPLINA DO PRIMEIRO SEMESTRE.
						if (hAluno.getDisciplina().getTipo() == TipoDisciplina.PRIMEIRO_SEMESTRE) {
							if (hAluno.getAvaliacao1() != null && hAluno.getAvaliacao2() != null) {
								media = (hAluno.getAvaliacao1() + hAluno.getAvaliacao2()) / 2;
								
								float mediaRoud = Math.round(media);
								// NOTA FINAL CONTINUA
								hAluno.setNotaFinalContinua(mediaRoud);
								
								// NOTA FINAL
								if (mediaRoud >= 12) {
									hAluno.setNotaFinal(mediaRoud);
									hAluno.setAprovado(true);
									hAluno.setSituacao(Situacao.APROVADO);
									hAluno.setDataNotaFinal(new Date());
								} else {
									hAluno.setSituacao(Situacao.REPROVADO);
									hAluno.setAprovado(false);
									hAluno.setNotaFinal(null);
								}
							}
						}
						
						// VALIDAR MÉDIA PARA DISCIPLINA DO SEGUNDO SEMESTRE.
						if (hAluno.getDisciplina().getTipo() == TipoDisciplina.SEGUNDO_SEMESTRE) {
							if (hAluno.getAvaliacao3() != null && hAluno.getAvaliacao4() != null) {
								media = (hAluno.getAvaliacao3() + hAluno.getAvaliacao4()) / 2;
								
								float mediaRoud = Math.round(media);
								// NOTA FINAL CONTINUA
								hAluno.setNotaFinalContinua(mediaRoud);
								
								// NOTA FINAL
								if (mediaRoud >= 12) {
									hAluno.setNotaFinal(mediaRoud);
									hAluno.setSituacao(Situacao.APROVADO);
									hAluno.setAprovado(true);
									hAluno.setDataNotaFinal(new Date());
								} else {
									hAluno.setSituacao(Situacao.REPROVADO);
									hAluno.setAprovado(false);
									
									hAluno.setNotaFinal(null);
								}
							}
						}
						
						// VALIDAR MÉDIA PARA DISCIPLINA DA ANUAL
						if (hAluno.getDisciplina().getTipo() == TipoDisciplina.ANUAL) {
							if (hAluno.getAvaliacao1() != null && hAluno.getAvaliacao2() != null
									&& hAluno.getAvaliacao3() != null && hAluno.getAvaliacao4() != null) {
								media = (hAluno.getAvaliacao1() + hAluno.getAvaliacao2() + hAluno.getAvaliacao3()
								+ hAluno.getAvaliacao4()) / 4;
								
								float mediaRoud = Math.round(media);
								// NOTA FINAL CONTINUA
								hAluno.setNotaFinalContinua(mediaRoud);
								// NOTA FINAL
								if (mediaRoud >= 12) {
									hAluno.setNotaFinal(mediaRoud);
									hAluno.setSituacao(Situacao.APROVADO);
									hAluno.setAprovado(true);
									hAluno.setDataNotaFinal(new Date());
								} else {
									hAluno.setSituacao(Situacao.REPROVADO);
									hAluno.setAprovado(false);
									hAluno.setNotaFinal(null);
								}
							}
						}
						
						// COLOCAR A VALIDAÇÃO DAS RECORRENCIAS...
						// EXAME
						if (classificar.getProva() == 10) {
							hAluno.setNotaExame(classifique.getExame());
							hAluno.setDataExame(new Date());
							hAluno.setUsuarioExame(usuario);
							
							// DEFINIR SITUAÇÃO DO ALUNO
							if (classifique.getExame() != null) {
								float mediaRoud = Math.round(classifique.getExame());
								definirSituacaoDisciplina(hAluno, mediaRoud);
							}
						}
						
						// EXAME ORAL
						if (classificar.getProva() == 11) {
							hAluno.setNotaExameOral(classifique.getExameOral());
							hAluno.setDataExameOral(new Date());
							hAluno.setUsuarioExameOral(usuario);
							
							// DEFINIR SITUAÇÃO DO ALUNO
							if (classifique.getExameOral() != null) {
								float mediaRoud = Math.round(classifique.getExameOral());
								definirSituacaoDisciplina(hAluno, mediaRoud);
							}
						}
						
						// RECURSO
						if (classificar.getProva() == 6) {
							
							
							List<InscricaoExtraordinaria> pagouAProva = inscricaoExtraordinariaRepository.findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidadaAndProva(hAluno.getAnoLectivo(), hAluno.getAluno(), hAluno.getDisciplina(), true,TipoProvaExtraOrdinaria.ER);
							
							//hAluno.getAluno().getCurso().getGrau()!=Grau.MESTRADO
							// !pagouAProva.isEmpty()
							if (!pagouAProva.isEmpty() || hAluno.getAluno().getCurso().getGrau()==Grau.MESTRADO) {
								hAluno.setNotaRecurso(classifique.getRecurso());
								hAluno.setDataRecurso(new Date());
								hAluno.setUsuarioRecurso(usuario);
								
								// DEFINIR SITUAÇÃO DO ALUNO
								if (classifique.getRecurso() != null) {
									float mediaRoud = Math.round(classifique.getRecurso());
									definirSituacaoDisciplina(hAluno, mediaRoud);
								}
							}
						}
						
						// RECURSO ORAL
						if (classificar.getProva() == 9) {
							hAluno.setNotaRecursoOral(classifique.getRecursoOral());
							hAluno.setDataNotaRecursoOral(new Date());
							hAluno.setUsuarioRecursoOral(usuario);
							
							// DEFINIR SITUAÇÃO DO ALUNO
							if (classifique.getRecursoOral() != null) {
								float mediaRoud = Math.round(classifique.getRecursoOral());
								definirSituacaoDisciplina(hAluno, mediaRoud);
							}
						}
						
						// ÉPOCA ESPECIAL
						if (classificar.getProva() == 12) {
							List<InscricaoExtraordinaria> pagouAProva = inscricaoExtraordinariaRepository
									.findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidadaAndProva(
											hAluno.getAnoLectivo(), hAluno.getAluno(), hAluno.getDisciplina(), true,
											TipoProvaExtraOrdinaria.EE);
							if (!pagouAProva.isEmpty()) {
								hAluno.setNotaEpocaEspecial(classifique.getEpocaEspecial());
								hAluno.setDataNotaEpocaEspecial(new Date());
								hAluno.setUsuarioEspecial(usuario);
								
								// DEFINIR SITUAÇÃO DO ALUNO
								if (classifique.getEpocaEspecial() != null) {
									float mediaRoud = Math.round(classifique.getEpocaEspecial());
									definirSituacaoDisciplina(hAluno, mediaRoud);
								}
							}
						}
						// ORAL ÉPOCA ESPECIAL
						if (classificar.getProva() == 13) {
							hAluno.setNotaEpocaEspecialOral(classifique.getEpocaEspecialOral());
							hAluno.setDataNotaEpocaEspecialOral(new Date());
							hAluno.setUsuarioEspecialOral(usuario);
							
							// DEFINIR SITUAÇÃO DO ALUNO
							if (classifique.getEpocaEspecialOral() != null) {
								float mediaRoud = Math.round(classifique.getEpocaEspecialOral());
								definirSituacaoDisciplina(hAluno, mediaRoud);
							}
						}
						// MELHORIA
						if (classificar.getProva() == 22) {
							hAluno.setMelhoriaNota(classifique.getMelhorNota());
							hAluno.setDataMelhoria(new Date());
							hAluno.setUsuarioMelhoria(usuario);
							
							// DEFINIR SITUAÇÃO DO ALUNO
							if (classifique.getMelhorNota() != null) {
								float mediaRoud = Math.round(classifique.getMelhorNota());
								definirSituacaoDisciplina(hAluno, mediaRoud);
							}
						}
						
						// MELHORIA ORAL
						if (classificar.getProva() == 23) {
							// hAluno.setMelhoriaNota(melhoriaNota);
							hAluno.setMelhoriaNotaOral(classifique.getMelhoriaOral());
							hAluno.setDataMelhoriaOral(new Date());
							hAluno.setUsuarioMelhoriaOral(usuario);
							
							// DEFINIR SITUAÇÃO DO ALUNO
							if (classifique.getMelhoriaOral() != null) {
								float mediaRoud = Math.round(classifique.getMelhoriaOral());
								definirSituacaoDisciplina(hAluno, mediaRoud);
							}
						}
						// VERÃO
						
						if (classificar.getProva() == 23) {
							List<InscricaoExtraordinaria> pagouAProva = inscricaoExtraordinariaRepository.findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidadaAndProva(hAluno.getAnoLectivo(), hAluno.getAluno(), hAluno.getDisciplina(), true,TipoProvaExtraOrdinaria.CV);
							
							if(!pagouAProva.isEmpty()) {
								
								hAluno.setNotaCursoDeVerao(classifique.getVerao());
								hAluno.setDataNotaCursoVerao(new Date());
								hAluno.setUsuarioCursoVerao(usuario);
								
								// DEFINIR SITUAÇÃO DO ALUNO
								if (classifique.getVerao() != null) {
									float mediaRoud = Math.round(classifique.getVerao());
									definirSituacaoDisciplina(hAluno, mediaRoud);
								}
							}
						}
						
						// AVALIAR COM BASE A RECORRENCIA
						hAluno.setUltimaModificacao(new Date());
						this.repositoryHistoricoAluno.save(hAluno);
						this.historicoAlunoService.gerarHistorico(hAluno);
						
					}
					//-----------------------------------------------------------------
					
					
					
				}
			}
			// FINAL UGS

			/*
			 * 4 Contínua Freq 1 5 Contínua Freq 2 1 Contínua Trab 1 2 Contínua Trab 2 3
			 * Contínua Out
			 */
			// INICIO IGS
			// int cotroleProva=10;
			if (instituicao.getSigla().equals("IGS")) {

				for (ClassificacaoCliente classifique : als) {
					HistoricoAluno hAluno = this.repositoryHistoricoAluno.findOne(classifique.getId());

					// PRIMEIRA AVALIAÇÃO
					if (classificar.getProva() == 4) {
						hAluno.setAvaliacao1(classifique.getPrimeiraAvaliacao());
						hAluno.setDataPrimeiraFrequencia(new Date());
						hAluno.setUsuarioPrimeiraFrequencia(usuario);
					}
					// SEGUNDA AVALIAÇÃO
					if (classificar.getProva() == 5) {
						hAluno.setAvaliacao2(classifique.getSegundaAvaliacao());
						hAluno.setDataSegundaFrequencia(new Date());
						hAluno.setUsuarioSegundaFrequencia(usuario);
					}

					// PRÁTICA
					if (classificar.getProva() == 3) {
						hAluno.setNotaPratica(classifique.getPratica());
						hAluno.setDataNotaPratica(new Date());
						hAluno.setUsuarioPratica(usuario);

					}

					// TERCEIRA AVALIAÇÃO
					if (classificar.getProva() == 1) {
						hAluno.setAvaliacao3(classifique.getPrimeiroTrabalho());
						hAluno.setDataTerceiraFrequencia(new Date());
						hAluno.setUsuarioTerceiraFrequencia(usuario);
					}

					// QUARTA AVALIAÇÃO
					if (classificar.getProva() == 2) {
						hAluno.setAvaliacao4(classifique.getSegundoTrabalho());
						hAluno.setDataQuartaFrequencia(new Date());
						hAluno.setUsuarioQuartaFrequencia(usuario);
					}
					// COLOCAR A VALIDAÇÃO DAS FREQUENCIAS...
					hAluno.setUltimaModificacao(new Date());
					this.repositoryHistoricoAluno.save(hAluno);
					this.historicoAlunoService.gerarHistorico(hAluno);
					Float media;
					// VALIDAR MÉDIA PARA DISCIPLINA DO PRIMEIRO SEMESTRE.
					if (hAluno.getDisciplina().getTipo() == TipoDisciplina.PRIMEIRO_SEMESTRE || hAluno.getDisciplina().getTipo() == TipoDisciplina.SEGUNDO_SEMESTRE) {

						float comulativa = 0;
						float notaPoderada = 0;

						int pf1 = 35, pf2 = 35, pt1 = 10, pt2 = 10, po = 10;

						if (hAluno.getAvaliacao1() != null) {
							notaPoderada = hAluno.getAvaliacao1() * pf1;
							comulativa += notaPoderada;
						}
						if (hAluno.getAvaliacao2() != null) {
							notaPoderada = hAluno.getAvaliacao2() * pf2;
							comulativa += notaPoderada;
						}
						if (hAluno.getAvaliacao3() != null) {
							notaPoderada = hAluno.getAvaliacao3() * pt1;
							comulativa += notaPoderada;
						}
						if (hAluno.getAvaliacao4() != null) {
							notaPoderada = hAluno.getAvaliacao4() * pt2;
							comulativa += notaPoderada;
						}
						if (hAluno.getNotaPratica() != null) {
							notaPoderada = hAluno.getNotaPratica() * po;
							comulativa += notaPoderada;
						}

						int somaDePesos = pf1 + pf2 + pt1 + pt2 + po;

						media = comulativa / somaDePesos;

						float mediaRoud = Math.round(media);

						// NOTA FINAL CONTINUA
						hAluno.setNotaFinalContinua(mediaRoud);
						// NOTA FINAL
						if (mediaRoud >= 13) {
							hAluno.setNotaFinal(mediaRoud);
							hAluno.setAprovado(true);
							hAluno.setSituacao(Situacao.APROVADO);
							hAluno.setDataNotaFinal(new Date());
						} else {
							hAluno.setSituacao(Situacao.REPROVADO);
							hAluno.setAprovado(false);
							hAluno.setNotaFinal(null);
						}
					}

					// VALIDAR MÉDIA PARA DISCIPLINA DA ANUAL
					if (hAluno.getDisciplina().getTipo() == TipoDisciplina.ANUAL) {
						if (hAluno.getAvaliacao1() != null && hAluno.getAvaliacao2() != null
								&& hAluno.getAvaliacao3() != null && hAluno.getAvaliacao4() != null) {
							media = (hAluno.getAvaliacao1() + hAluno.getAvaliacao2() + hAluno.getAvaliacao3()
									+ hAluno.getAvaliacao4()) / 4;

							float mediaRoud = Math.round(media);
							// NOTA FINAL CONTINUA
							hAluno.setNotaFinalContinua(mediaRoud);
							// NOTA FINAL
							if (mediaRoud >= 13) {
								hAluno.setNotaFinal(mediaRoud);
								hAluno.setSituacao(Situacao.APROVADO);
								hAluno.setAprovado(true);
								hAluno.setDataNotaFinal(new Date());
							} else {
								hAluno.setSituacao(Situacao.REPROVADO);
								hAluno.setAprovado(false);
								hAluno.setNotaFinal(null);
							}
						}
					}

					// COLOCAR A VALIDAÇÃO DAS RECORRENCIAS...
					// EXAME
					if (classificar.getProva() == 10) {
						hAluno.setNotaExame(classifique.getExame());
						hAluno.setDataExame(new Date());
						hAluno.setUsuarioExame(usuario);

						// DEFINIR SITUAÇÃO DO ALUNO
						if (classifique.getExame() != null) {
							float mediaRoud = Math.round(classifique.getExame());
							definirSituacaoDisciplina(hAluno, mediaRoud);
						}
					}

					// EXAME ORAL
					if (classificar.getProva() == 11) {
						hAluno.setNotaExameOral(classifique.getExameOral());
						hAluno.setDataExameOral(new Date());
						hAluno.setUsuarioExameOral(usuario);

						// DEFINIR SITUAÇÃO DO ALUNO
						if (classifique.getExameOral() != null) {
							float mediaRoud = Math.round(classifique.getExameOral());
							definirSituacaoDisciplina(hAluno, mediaRoud);
						}
					}

					// RECURSO
					if (classificar.getProva() == 6) {
						List<InscricaoExtraordinaria> pagouAProva = inscricaoExtraordinariaRepository.findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidadaAndProva(hAluno.getAnoLectivo(), hAluno.getAluno(), hAluno.getDisciplina(), true,TipoProvaExtraOrdinaria.ER);
						
						Contador contador = this.contadorRepository.findOne(33);
						
						if(contador.getProximoValor()==1) {
							if (!pagouAProva.isEmpty()) {
								hAluno.setNotaRecurso(classifique.getRecurso());
								hAluno.setDataRecurso(new Date());
								hAluno.setUsuarioRecurso(usuario);
								
								// DEFINIR SITUAÇÃO DO ALUNO
								if (classifique.getRecurso() != null) {
									float mediaRoud = Math.round(classifique.getRecurso());
									definirSituacaoDisciplina(hAluno, mediaRoud);
								}
							}							
						}else {
							hAluno.setNotaRecurso(classifique.getRecurso());
							hAluno.setDataRecurso(new Date());
							hAluno.setUsuarioRecurso(usuario);
							
							// DEFINIR SITUAÇÃO DO ALUNO
							if (classifique.getRecurso() != null) {
								float mediaRoud = Math.round(classifique.getRecurso());
								definirSituacaoDisciplina(hAluno, mediaRoud);
							}
						}
					}
					// RECURSO ORAL
					if (classificar.getProva() == 9) {
						hAluno.setNotaRecursoOral(classifique.getRecursoOral());
						hAluno.setDataNotaRecursoOral(new Date());
						hAluno.setUsuarioRecursoOral(usuario);

						// DEFINIR SITUAÇÃO DO ALUNO
						if (classifique.getRecursoOral() != null) {
							float mediaRoud = Math.round(classifique.getRecursoOral());
							definirSituacaoDisciplina(hAluno, mediaRoud);
						}
					}

					// ÉPOCA ESPECIAL
					if (classificar.getProva() == 12) {
						List<InscricaoExtraordinaria> pagouAProva = inscricaoExtraordinariaRepository
								.findByAnoLectivoAndAlunoAndDisciplinaAndGuiaPagamentoLiquidadaAndProva(
										hAluno.getAnoLectivo(), hAluno.getAluno(), hAluno.getDisciplina(), true,
										TipoProvaExtraOrdinaria.EE);
						if (!pagouAProva.isEmpty()) {
							hAluno.setNotaEpocaEspecial(classifique.getEpocaEspecial());
							hAluno.setDataNotaEpocaEspecial(new Date());
							hAluno.setUsuarioEspecial(usuario);

							// DEFINIR SITUAÇÃO DO ALUNO
							if (classifique.getEpocaEspecial() != null) {
								float mediaRoud = Math.round(classifique.getEpocaEspecial());
								definirSituacaoDisciplina(hAluno, mediaRoud);
							}
						}
					}
					// ORAL ÉPOCA ESPECIAL
					if (classificar.getProva() == 13) {
						hAluno.setNotaEpocaEspecialOral(classifique.getEpocaEspecialOral());
						hAluno.setDataNotaEpocaEspecialOral(new Date());
						hAluno.setUsuarioEspecialOral(usuario);

						// DEFINIR SITUAÇÃO DO ALUNO
						if (classifique.getEpocaEspecialOral() != null) {
							float mediaRoud = Math.round(classifique.getEpocaEspecialOral());
							definirSituacaoDisciplina(hAluno, mediaRoud);
						}
					}
					// MELHORIA
					if (classificar.getProva() == 22) {
						hAluno.setMelhoriaNota(classifique.getMelhorNota());
						hAluno.setDataMelhoria(new Date());
						hAluno.setUsuarioMelhoria(usuario);

						// DEFINIR SITUAÇÃO DO ALUNO
						if (classifique.getMelhorNota() != null) {
							float mediaRoud = Math.round(classifique.getMelhorNota());
							definirSituacaoDisciplina(hAluno, mediaRoud);
						}
					}

					// MELHORIA ORAL
					if (classificar.getProva() == 23) {
						// hAluno.setMelhoriaNota(melhoriaNota);
						hAluno.setMelhoriaNotaOral(classifique.getMelhoriaOral());
						hAluno.setDataMelhoriaOral(new Date());
						hAluno.setUsuarioMelhoriaOral(usuario);

						// DEFINIR SITUAÇÃO DO ALUNO
						if (classifique.getMelhoriaOral() != null) {
							float mediaRoud = Math.round(classifique.getMelhoriaOral());
							definirSituacaoDisciplina(hAluno, mediaRoud);
						}
					}
					// VERÃO
					if (classificar.getProva() == 23) {
						hAluno.setNotaCursoDeVerao(classifique.getVerao());
						hAluno.setDataNotaCursoVerao(new Date());
						hAluno.setUsuarioCursoVerao(usuario);

						// DEFINIR SITUAÇÃO DO ALUNO
						if (classifique.getVerao() != null) {
							float mediaRoud = Math.round(classifique.getVerao());
							definirSituacaoDisciplina(hAluno, mediaRoud);
						}
					}

					// AVALIAR COM BASE A RECORRENCIA
					hAluno.setUltimaModificacao(new Date());
					this.repositoryHistoricoAluno.save(hAluno);
					this.historicoAlunoService.gerarHistorico(hAluno);
				}
			}
			// FINAL IGS

			c.setResultado(classificar);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem(prova.getProva() + " lançada com sucesso.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
			// c.setResultado("A época lançada nõa se encontra aberta.");
			c.setCodigo(ResponseCode.values()[1].getDescricao());
			c.setMensagem("Não existe nehuma época aberta.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	private void definirSituacaoDisciplina(HistoricoAluno hAluno, float mediaRoud) {
		hAluno.setNotaFinal(mediaRoud);
		if (mediaRoud >= 10) {
			hAluno.setSituacao(Situacao.APROVADO);
			hAluno.setAprovado(true);
		} else {
			hAluno.setSituacao(Situacao.REPROVADO);
			hAluno.setAprovado(false);
		}
		hAluno.setUltimaModificacao(new Date());
		this.repositoryHistoricoAluno.save(hAluno);
		this.historicoAlunoService.gerarHistorico(hAluno);
	}

	@RequestMapping(value = "/removerDisciplina", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> removerDisciplina(@RequestBody DisciplinaAndAlunoAndAnoLectivo pego) {
		ResponseCliente c = new ResponseCliente();
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(pego.getAluno().toString());
		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(pego.getAnoLectivo());
		Disciplina disciplina = this.disciplinaRepository.findOne(pego.getDisciplina());
		List<HistoricoAluno> rDisciplia = this.repositoryHistoricoAluno.findByAlunoAndMatriculaAnoLectivoAndDisciplina(aluno,anoLectivo, disciplina);
		this.repositoryHistoricoAluno.delete(rDisciplia);
		c.setMensagem("Disciplina removida com sucesso. ");
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/fecharClassificacoes", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> fecharClassificacoes(@RequestBody FecharClassificacoesCliente fecho) {
		ResponseCliente c = new ResponseCliente();
		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(fecho.getAnoLectivo());

		Curso curso = this.cursoRepository.findByIdAndStatus(fecho.getCurso(), true);

		FechamentoClassificacao fechoI = new FechamentoClassificacao();

		fechoI.setAnoCurricular(fecho.getAnoCorricular());
		fechoI.setAnoLectivo(anoLectivo);
		fechoI.setCurso(curso);
		fechoI.setFechar(fecho.isFechar());
		fechoI.setSemestre(fecho.getSemestre());
		fechoI.setValidar(fecho.isValidar());

		this.fechamentoRepository.save(fechoI);

		c.setMensagem("Fechamento efetivado com sucesso. ");
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/adicionarDisciplina", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> adicionarDisciplina(@RequestBody AdicionarDisciplinasClientes pego) {
		ResponseCliente c = new ResponseCliente();

		Aluno aluno = pego.getAluno().toString() != null
				? this.alunoRepository.findByNumeroDeAluno(pego.getAluno().toString())
				: null;
		if (aluno == null) {
			c.setMensagem("Erro de envio de aluno.");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		AnoLectivo anoLectivo = pego.getAnoLectivo() != null ? this.anoLectivoRepository.findOne(pego.getAnoLectivo())
				: null;

		if (anoLectivo == null) {
			c.setMensagem("Erro de envio de ano lectivo.");
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		Matricula matricula = this.matriculaRepository.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivo, false);

		HistoricoAluno hAluno = new HistoricoAluno();

		List<DisciplinasClienteAdicionar> disciplinas = pego.getDisciplinaTurma();

		String mensagem = disciplinas.size() > 1 ? "Disciplinas" : "Disciplina";
		for (DisciplinasClienteAdicionar dAdicionar : disciplinas) {
			Disciplina disciplina = this.disciplinaRepository.findOne(dAdicionar.getDisciplinaId());
			Turma turma = this.turmaRepository.findOne(dAdicionar.getTurmaId());

			hAluno.setAluno(aluno);
			hAluno.setAnoLectivo(anoLectivo);
			hAluno.setDisciplina(disciplina);
			hAluno.setMatricula(matricula);
			hAluno.setAnoCorricular(disciplina.getAnoCorricular());
			hAluno.setTurma(turma);
			hAluno.setDataInscricao(new Date());
			hAluno.setNumeroDeAluno(aluno.getNumeroDeAluno());
			hAluno.setSituacao(Situacao.CURSANDO);
			hAluno.setUltimaModificacao(new Date());
			this.repositoryHistoricoAluno.save(hAluno);
			this.historicoAlunoService.gerarHistorico(hAluno);
		}

		c.setMensagem(mensagem + " adicionada com sucesso. ");

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// http://192.168.1.85:8898/historicoDeAluno/novaDisciplina
	@RequestMapping(value = "/novaDisciplina", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> novaDisciplina(@RequestBody AdicionarDisciplina pego) {
		ResponseCliente c = new ResponseCliente();

		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(pego.getNumeroDeAluno().toString());
		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(pego.getAnoLectivo());
		Matricula matricula = this.matriculaRepository.findByAlunoAndAnoLectivoAndAnulado(aluno, anoLectivo, false);

		HistoricoAluno historico;
		List<DisciplinaNovaCliente> novas = pego.getNovas();

		if (novas.isEmpty()) {
			c.setMensagem("Verifique sua operação.");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
		}

		for (DisciplinaNovaCliente dn : novas) {
			historico = new HistoricoAluno();

			// dados da disciplinas.
			Disciplina disciplina = this.disciplinaRepository.findOne(dn.getDisciplina());
			Turma turma = this.turmaRepository.findOne(dn.getTurma());

			historico.setDisciplina(disciplina);
			historico.setAluno(aluno);
			historico.setNumeroDeAluno(aluno.getNumeroDeAluno());
			historico.setAnoCorricular(disciplina.getAnoCorricular());
			historico.setAnoLectivo(anoLectivo);
			historico.setMatricula(matricula);
			historico.setTurma(turma);
			historico.setSituacao(Situacao.CURSANDO);
			historico.setUltimaModificacao(new Date());
			this.repositoryHistoricoAluno.save(historico);
			this.historicoAlunoService.gerarHistorico(historico);
		}
		String mensagem;
		mensagem = novas.size() == 1 ? "Disciplina adicionada com sucesso." : "Disciplinas adicionadas com sucesso";
		c.setMensagem(mensagem);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}