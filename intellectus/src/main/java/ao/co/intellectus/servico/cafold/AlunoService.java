package ao.co.intellectus.servico.cafold;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ao.co.intellectus.DTO.AlunoGeral;
import ao.co.intellectus.DTO.colegio.AlunoColegio;
import ao.co.intellectus.DTO.colegio.DisciplinaClienteColegio;
import ao.co.intellectus.DTO.colegio.MatriculaConfirmadaViewModel;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAudit;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.CertificadoIntermedio;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaEmAbertoCliente;
import ao.co.intellectus.model.GuiaPagamentoHistorico;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.Pais;
import ao.co.intellectus.model.Provincia;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.request.CandidatoRequest;
import ao.co.intellectus.repository.AlunoAuditRepository;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.HistoricoGuiaPagamentoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.MoedaRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.servico.exception.DadosDuplicadoException;
import ao.co.intellectus.servico.exception.EmailInstituicionalNaoEncontradoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import ao.co.intellectus.servico.exception.handlerException;
import ao.co.intellectus.servico.guias.GerarNumeroDeGuia;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private DisciplinaService disciplinaService;
	@Autowired
	private AlunoAuditRepository alunoAuditRepository;
	@Autowired
	private NacionalidadeService nacionalidadeService;
	@Autowired
	private ProvinciaService provinciaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private NumeroGeradoService numeroGeradoService;
	@Autowired
	private ConexaoService conexaoService;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private HistoricoAlunoRepository historicoAlunoRepository;
	@Autowired
	private GuiaPagamentoRepository guia;
	@Autowired
	private EmolumentoRepository emolumentoRepository;
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private HistoricoGuiaPagamentoRepository historicoGuiaRepository;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private MoedaRepository moedaRepository;

	
	public void salvar(Aluno aluno) {
		this.alunoRepository.save(aluno);
		this.gerarHistorico(aluno);
	}

	public Aluno salvar(AlunoColegio alunoColegio) {
		Aluno aluno = new Aluno();
		
		
		Curso curso=null;
		if(alunoColegio.getCurso()!=null)
		  curso = this.cursoRepository.findById(alunoColegio.getCurso());
		
		Turma turma=null;
		if(alunoColegio.getTurma()!=null)
		  turma = this.turmaRepository.findOne(alunoColegio.getTurma());
		
		
		Pais nacionalidade = this.nacionalidadeService.nacionalidade(alunoColegio.getNacionalidade());
		Pais nacionalidadeDoPai = this.nacionalidadeService.nacionalidade(alunoColegio.getNacionalidadeDoPai());
		Pais nacionalidadeDaMae = this.nacionalidadeService.nacionalidade(alunoColegio.getNacionalidadeDaMae());
		Provincia provincia = this.provinciaService.provincia(alunoColegio.getProvinciaResidencia());
		Usuario usuario = this.usuarioService.usuario(alunoColegio.getUserCode());
		if (alunoColegio.getId() != null)
			aluno.setId(alunoColegio.getId());
		aluno.setNome(alunoColegio.getNome());
		aluno.setGrauParentesco(alunoColegio.getGrauParentesco());
		aluno.setDocumentoIdentificacao(alunoColegio.getDocumentoIdentificacao());
		aluno.setNumeroDocumento(alunoColegio.getNumeroDocumento());
		aluno.setDataNascimento(alunoColegio.getDataNascimento());
		aluno.setNomeDoPai(alunoColegio.getNomeDoPai());
		aluno.setNacionalidade(nacionalidade);
		aluno.setNacionalidadeDoPai(nacionalidadeDoPai);
		aluno.setNacionalidadeDaMae(nacionalidadeDaMae);
		aluno.setNacionalidadeEncarregado(alunoColegio.getNacionalidadeEncarregado());
		aluno.setMoradaDoPai(alunoColegio.getMoradaDoPai());
		aluno.setMoradaDaMae(alunoColegio.getMoradaDaMae());
		aluno.setMoradaDaEncarregado(alunoColegio.getMoradaDaEncarregado());
		aluno.setNomeDoPai(alunoColegio.getNomeDoPai());
		aluno.setNomeDaMae(alunoColegio.getNomeDaMae());
		aluno.setNomeEncarregado(alunoColegio.getNomeEncarregado());
		aluno.setTelefone(alunoColegio.getTelefone());
		aluno.setTelefone1(alunoColegio.getTelefone1());
		aluno.setTelefone2(alunoColegio.getTelefone2());
		aluno.setTelefoneDoPai(alunoColegio.getTelefoneDoPai());
		aluno.setTelefone1DoPai(alunoColegio.getTelefone1DoPai());
		aluno.setTelefoneDaMae(alunoColegio.getTelefoneDaMae());
		aluno.setTelefone1DaMae(alunoColegio.getTelefone1DaMae());
		aluno.setTelefoneEncarregado(alunoColegio.getTelefoneEncarregado());
		aluno.setTelefone1Encarregado(alunoColegio.getTelefone1Encarregado());
		aluno.setEmail(alunoColegio.getEmail());
		aluno.setEmailDoPai(alunoColegio.getEmailDoPai());
		aluno.setEmailDaMae(alunoColegio.getEmailDaMae());
		aluno.setEmailEncarregado(alunoColegio.getEmailEncarregado());
		aluno.setProvinciaResidencia(provincia);
		aluno.setNecessidadeEducacaoEspecial(alunoColegio.getNecessidadeEducacaoEspecial());
		aluno.setMorada(alunoColegio.getMorada());
		aluno.setContencioso(alunoColegio.getContencioso());
		aluno.setCopiaDocumentoIdentificacao(alunoColegio.getCopiaDocumentoIdentificacao());
		aluno.setCopiaDocumentoMilitar(alunoColegio.getCopiaDocumentoMilitar());
		aluno.setFimCurso(alunoColegio.getFimCurso());
		aluno.setFotografias(alunoColegio.getFotografias());
		aluno.setInactivo(alunoColegio.getInactivo());
		aluno.setTrabalhador(alunoColegio.getIsTrabalhador());
		aluno.setPrimeiraMatricula(alunoColegio.getPrimeiraMatricula());
		aluno.setSexo(alunoColegio.getSexo());
		aluno.setUsuario(usuario);
		aluno.setCurso(curso);
		
		
		if(alunoColegio.getNumeroDeAluno() == null)
			aluno = this.gerarNumero(aluno);
		aluno = this.alunoRepository.save(aluno);
		
		
		
		Matricula matricula =new Matricula();
		
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
		matricula.setAluno(aluno);
		matricula.setAnoLectivo(anoLectivo.get(0));
		matricula.setAnoLectivoMatricula(anoLectivo.get(0).getAnoLectivo().toString());
		matricula.setCurso(curso);
		matricula.setData(new Date());
		matricula.setNumeroDeAluno(aluno.getNumeroDeAluno());
		matricula.setTurmaBase(turma);
		matricula.setTrabalhador(false);
		matricula.setAnulado(false);
		matricula.setTransitaAno(false);
		
		Matricula mSalva = this.matriculaRepository.save(matricula);
		
		MatriculaConfirmadaViewModel model=new MatriculaConfirmadaViewModel();
		List<DisciplinaClienteColegio> dsM=new ArrayList<DisciplinaClienteColegio>();
		DisciplinaClienteColegio ds;
		
		List<Disciplina> disciplinas = this.disciplinaRepository.findByCursoAndAnoCorricular(curso,1);
		
		HistoricoAluno ha;
		for (Disciplina o : disciplinas) {
			 ha=new HistoricoAluno();
			 ds=new DisciplinaClienteColegio();
			 
			 ha.setMatricula(mSalva);
			 ha.setAluno(aluno);
			 ha.setNumeroDeAluno(aluno.getNumeroDeAluno());
			 ha.setTurma(turma);
			 ha.setAnoLectivo(mSalva.getAnoLectivo());
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
			 
			 this.historicoAlunoRepository.save(ha);
		}
		model.setDisciplinas(dsM);
		
		this.gerarHistorico(aluno);
		
		gerarGuia(aluno, curso, usuario, anoLectivo);
		
		return aluno;
	}

	private void gerarGuia(Aluno aluno, Curso curso, Usuario usuario, List<AnoLectivo> anoLectivo) {
		Emolumento emolumento = this.emolumentoRepository.findByCodigo(7);
		EmolumentoHistorico histEmolumento = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento, curso, anoLectivo.get(0));
		
		Guia guia = new Guia();
		guia.setAluno(aluno);
		guia.setNumeroDeAluno(aluno.getNumeroDeAluno());
		guia.setAutomaticamente(true);
		guia.setDataEmicao(new Date());
		guia.setLiquidada(false);
		guia.setUsuarioEmitiu(usuario);
		guia.setMoeda(this.moedaRepository.findOne(3));
		guia.setInstituicao(this.instituicaoRepository.findOne(2));
		guia.setAnoLectivo(anoLectivo.get(0));
		guia.setValor(histEmolumento.getValor());
		guia.setDataVencimento(new Date());


		guia.setUltimaModificacao(new Date());
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
		gHistorico.setAnoLectivo(anoLectivo.get(0));
		gHistorico.setEmolumento(emolumento);
		gHistorico.setGuia(gSalva);
		gHistorico.setNumeroDeAluno(aluno.getNumeroDeAluno());
		gHistorico.setValor(histEmolumento.getValor());

		this.historicoGuiaRepository.save(gHistorico);
	}
	
	
	public Aluno actualizar(AlunoColegio alunoColegio) {
		Aluno aluno = this.aluno(alunoColegio.getNumeroDeAluno());
		Pais nacionalidade = this.nacionalidadeService.nacionalidade(alunoColegio.getNacionalidade());
		Pais nacionalidadeDoPai = this.nacionalidadeService.nacionalidade(alunoColegio.getNacionalidadeDoPai());
		Pais nacionalidadeDaMae = this.nacionalidadeService.nacionalidade(alunoColegio.getNacionalidadeDaMae());
		Provincia provincia = this.provinciaService.provincia(alunoColegio.getProvinciaResidencia());
		Usuario usuario = this.usuarioService.usuario(alunoColegio.getUserCode());
		aluno.setNome(alunoColegio.getNome());
		aluno.setGrauParentesco(alunoColegio.getGrauParentesco());
		aluno.setDocumentoIdentificacao(alunoColegio.getDocumentoIdentificacao());
		aluno.setNumeroDocumento(alunoColegio.getNumeroDocumento());
		aluno.setDataNascimento(alunoColegio.getDataNascimento());
		aluno.setNomeDoPai(alunoColegio.getNomeDoPai());
		aluno.setNacionalidade(nacionalidade);
		aluno.setNacionalidadeDoPai(nacionalidadeDoPai);
		aluno.setNacionalidadeDaMae(nacionalidadeDaMae);
		aluno.setNacionalidadeEncarregado(alunoColegio.getNacionalidadeEncarregado());
		aluno.setMoradaDoPai(alunoColegio.getMoradaDoPai());
		aluno.setMoradaDaMae(alunoColegio.getMoradaDaMae());
		aluno.setMoradaDaEncarregado(alunoColegio.getMoradaDaEncarregado());
		aluno.setNomeDoPai(alunoColegio.getNomeDoPai());
		aluno.setNomeDaMae(alunoColegio.getNomeDaMae());
		aluno.setNomeEncarregado(alunoColegio.getNomeEncarregado());
		aluno.setTelefone(alunoColegio.getTelefone());
		aluno.setTelefone1(alunoColegio.getTelefone1());
		aluno.setTelefone2(alunoColegio.getTelefone2());
		aluno.setTelefoneDoPai(alunoColegio.getTelefoneDoPai());
		aluno.setTelefone1DoPai(alunoColegio.getTelefone1DoPai());
		aluno.setTelefoneDaMae(alunoColegio.getTelefoneDaMae());
		aluno.setTelefone1DaMae(alunoColegio.getTelefone1DaMae());
		aluno.setTelefoneEncarregado(alunoColegio.getTelefoneEncarregado());
		aluno.setTelefone1Encarregado(alunoColegio.getTelefone1Encarregado());
		aluno.setEmail(alunoColegio.getEmail());
		aluno.setEmailDoPai(alunoColegio.getEmailDoPai());
		aluno.setEmailDaMae(alunoColegio.getEmailDaMae());
		aluno.setEmailEncarregado(alunoColegio.getEmailEncarregado());
		aluno.setProvinciaResidencia(provincia);
		aluno.setNecessidadeEducacaoEspecial(alunoColegio.getNecessidadeEducacaoEspecial());
		aluno.setMorada(alunoColegio.getMorada());
		aluno.setContencioso(alunoColegio.getContencioso());
		aluno.setCopiaDocumentoIdentificacao(alunoColegio.getCopiaDocumentoIdentificacao());
		aluno.setCopiaDocumentoMilitar(alunoColegio.getCopiaDocumentoMilitar());
		aluno.setFimCurso(alunoColegio.getFimCurso());
		aluno.setFotografias(alunoColegio.getFotografias());
		aluno.setInactivo(alunoColegio.getInactivo());
		aluno.setTrabalhador(alunoColegio.getIsTrabalhador());
		aluno.setPrimeiraMatricula(alunoColegio.getPrimeiraMatricula());
		aluno.setUsuario(usuario);
		aluno = this.alunoRepository.save(aluno);
		this.gerarHistorico(aluno);
		return aluno;
	}

	public Aluno aluno(String numero) {
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);
		if (aluno != null)
			return aluno;
		throw new RegistoNaoEncontradoException("Registo de aluno não encontrado.");
	}

	public boolean validar(CandidatoRequest candidatoRequest) {
		List<Aluno> aluno = this.alunoRepository.findByNomeAndNomeDoPaiAndNomeDaMaeAndNumeroDocumentoAndFimCurso(
				candidatoRequest.getNome(), candidatoRequest.getNomeDoPai(), candidatoRequest.getNomeDaMae(),
				candidatoRequest.getNumeroDocumento(), false);
		if (aluno.isEmpty())
			throw new DadosDuplicadoException("O aluno já existente. [");
		return aluno == null;
	}

	public List<Aluno> alunos(String nome) {
		List<Aluno> alunos = this.alunoRepository.findByNomeLike("%" + nome + "%");
		if (alunos.isEmpty())
			throw new RegistoNaoEncontradoException("Nenhum registo de aluno não encontrado.");
		return alunos;
	}

	public Aluno alunoPorEmail(String email) {
		Aluno aluno = this.alunoRepository.findByEmailInstitucional(email);
		if (aluno != null)
			return aluno;
		throw new RegistoNaoEncontradoException("Registo de aluno não encontrado.");
	}

	public Aluno validarEmailInstitucional(String email) {
		Aluno aluno = this.alunoRepository.findByNumeroDeAlunoAndEmailInstitucionalIsNotNull(email);
		if (aluno != null)
			return aluno;
		throw new EmailInstituicionalNaoEncontradoException(
				"O estudante com o número: " + email + " não possui um email instituicional.");
	}

	public List<Aluno> todos() {
		List<Aluno> alunos = this.alunoRepository.findAll();
		if (alunos.isEmpty())
			throw new RegistoNaoEncontradoException("Registo de aluno não encontrado.");
		return alunos;
	}

	public Aluno alunoPorEmailAndDataDeNascimento(String email, Date data) {
		Optional<Aluno> aluno = this.alunoRepository.findByEmailInstitucionalAndDataNascimento(email, data);
		if (aluno.isPresent())
			return aluno.get();
		throw new RegistoNaoEncontradoException("Registo de aluno não encontrado.");
	}

	public ResponseEntity<ResponseCliente> alunoTemGuia(List<GuiaEmAbertoCliente> guiasAbertas) {
		String mensagem = guiasAbertas.size() == 1 ? "Existe uma guia não liquidada, impossível aplicar fim curso"
				: "Existem guias não liquidadas, impossível aplicar fim curso";
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, mensagem);
	}

	public ResponseEntity<ResponseCliente> alunoTemDisciplinasInconsitentes(Integer disciplinasDoCurso,
			Integer disciplinasCursadas) {
		String mensagem = (disciplinasDoCurso > disciplinasCursadas)
				? "Falta finalizar disciplinas: " + disciplinasCursadas + "/" + disciplinasDoCurso
				: "Existe disciplinas repetidas: " + disciplinasCursadas + " / " + disciplinasDoCurso;
		return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, mensagem);
	}

	public Aluno inactivo(Aluno alunoActualizar, AlunoGeral alunoGeral) {
		Aluno aluno = alunoActualizar;
		if (alunoGeral.isInactivo()) {
			aluno.setInactivo(alunoGeral.isInactivo());
			aluno.setDataInativo(new Date());
		}
		return aluno;
	}

	public Double mediaFinal(Aluno aluno, Curso curso) {
		if (aluno.isFimCurso()) {
			Double somaDasNotas = 0.0;
			Integer totalDisciplinas = this.disciplinaService.disciplinasPorCurso(curso).size();
			List<CertificadoIntermedio> disciplinasCursadas = new ArrayList<CertificadoIntermedio>();
			for (CertificadoIntermedio it : this.disciplinaService.disciplinasCursadas(aluno)) {
				if (!disciplinasCursadas.contains(it)) {
					disciplinasCursadas.add(it);
				}
			}
			for (CertificadoIntermedio ci : disciplinasCursadas) {
				somaDasNotas += ci.getNotaFinal();
			}
			return (somaDasNotas / totalDisciplinas);
		} else {
			return null;
		}
	}

	public void gerarHistorico(Aluno aluno) {
		AlunoAudit alunoAudit = new AlunoAudit();
		BeanUtils.copyProperties(aluno, alunoAudit);
		this.alunoAuditRepository.save(alunoAudit);
	}

	public void actualizarNumero(Guia guia, String telefone) {
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(guia.getNumeroDeAluno());
		aluno.setTelefone(telefone);
		aluno.setUltimaModificacao(new Date());
		this.alunoRepository.save(aluno);
		this.gerarHistorico(aluno);
	}

	public Aluno gerarNumero(Aluno aluno) {
		try {
			Connection conexaoLocal = this.conexaoService.getConexaoLocal();
			PreparedStatement pree = conexaoLocal.prepareStatement("SET IDENTITY_INSERT siga_intellectus.dbo.t_aluno ON");
			pree.execute();
			// preparação para a geração do número de aluno
			String definitivo = "";
			Calendar hoje = Calendar.getInstance();
			Integer lectivo = hoje.get(Calendar.YEAR);
			String valor = lectivo.toString().substring(2);
			lectivo = Integer.parseInt(valor);
			// String lectivoString = lectivo.toString();

			// lectivoString.
			NumeroGerado numeroGerado = this.numeroGeradoService.numeroGerado(2);
			Long proximoNumeroInteiro = numeroGerado.getProximoNumero();
			// metódo gerar numero de guia chamado
			// definitivo = gerarNumeroAluno(definitivo, lectivo, proximoNumeroInteiro);
			if (proximoNumeroInteiro >= 1 && proximoNumeroInteiro <= 9)
				definitivo = lectivo + "000" + proximoNumeroInteiro;
			if (proximoNumeroInteiro >= 10 && proximoNumeroInteiro <= 99)
				definitivo = lectivo + "00" + proximoNumeroInteiro;
			if (proximoNumeroInteiro >= 100 && proximoNumeroInteiro <= 999)
				definitivo = lectivo + "0" + proximoNumeroInteiro;
			if (proximoNumeroInteiro >= 1000 && proximoNumeroInteiro <= 9999)
				definitivo = lectivo + proximoNumeroInteiro.toString();
			// Guia guiaExistente = this.repository.findByNumeroGuia(definitivo);
			Aluno alunoExistente = this.alunoRepository.findByNumeroDeAluno(definitivo);
			if (alunoExistente != null) {
				do {
					proximoNumeroInteiro++;
					// metódo gerar numero de guia chamado
					// definitivo = gerarNumeroAluno(definitivo, lectivo, proximoNumeroInteiro);

					if (proximoNumeroInteiro >= 1 && proximoNumeroInteiro <= 9)
						definitivo = lectivo + "000" + proximoNumeroInteiro;
					if (proximoNumeroInteiro >= 10 && proximoNumeroInteiro <= 99)
						definitivo = lectivo + "00" + proximoNumeroInteiro;
					if (proximoNumeroInteiro >= 100 && proximoNumeroInteiro <= 999)
						definitivo = lectivo + "0" + proximoNumeroInteiro;
					if (proximoNumeroInteiro >= 1000 && proximoNumeroInteiro <= 9999)
						definitivo = lectivo + proximoNumeroInteiro.toString();
					alunoExistente = this.alunoRepository.findByNumeroDeAluno(definitivo);
				} while (alunoExistente != null);
			}
			aluno.setNumeroDeAluno(definitivo);
			aluno.setId(Integer.parseInt(definitivo));

			// atualizar os dados do controlar de geração de números
			numeroGerado.setUltimoNumero(proximoNumeroInteiro);
			numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
			this.numeroGeradoService.salvar(numeroGerado);
			PreparedStatement preeo = conexaoLocal.prepareStatement("SET IDENTITY_INSERT siga_intellectus.dbo.t_aluno OFF");
			preeo.execute();
			/* SALVAMOS O ALUNO */
			aluno.setUltimaModificacao(new Date());
			return aluno;
		} catch (ClassNotFoundException e) {
			throw new handlerException (e.getMessage());
		} catch (SQLException e) {
			throw new handlerException (e.getMessage());
		}
	}

}
