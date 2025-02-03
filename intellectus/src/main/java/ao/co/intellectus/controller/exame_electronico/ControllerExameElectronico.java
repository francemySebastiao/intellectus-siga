/*package ao.co.intellectus.controller.exame_electronico;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.CandidatoExameCliente;
import ao.co.intellectus.DTO.exame_electronico.BuscarClassificacoes;
import ao.co.intellectus.DTO.exame_electronico.EditarNotasDTO;
import ao.co.intellectus.DTO.exame_electronico.ExameElectronivoView;
import ao.co.intellectus.DTO.exame_electronico.ExameEletronicoControlador;
import ao.co.intellectus.DTO.exame_electronico.PerguntaCodificada;
import ao.co.intellectus.DTO.exame_electronico.PerguntaView;
import ao.co.intellectus.DTO.exame_electronico.RespostaCandidatoView;
import ao.co.intellectus.DTO.exame_electronico.RespostaView;
import ao.co.intellectus.DTO.exame_electronico.TipoExameView;
import ao.co.intellectus.model.CadidatoAudit;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.exame_eletronico.ExameCurso;
import ao.co.intellectus.model.exame_eletronico.ExameEletronico;
import ao.co.intellectus.model.exame_eletronico.Pergunta;
import ao.co.intellectus.model.exame_eletronico.Resposta;
import ao.co.intellectus.model.exame_eletronico.TipoExame;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.CandidatoAudit;
import ao.co.intellectus.repository.CandidatoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.repository.exame_electronico.ExameCursoRepository;
import ao.co.intellectus.repository.exame_electronico.ExameEletronicoRepository;
import ao.co.intellectus.repository.exame_electronico.PerguntaRepository;
import ao.co.intellectus.repository.exame_electronico.RepostaRepository;
import ao.co.intellectus.servico.cafold.CandidatoServie;
import ao.co.intellectus.servico.cafold.CursoService;
import ao.co.intellectus.servico.exception.DadoInvalidoException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import ao.co.intellectus.servico.exema_electronico.ExameEletronicoService;
import ao.co.intellectus.servico.exema_electronico.RespostaExameElectronicoService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/exameElectronico")
public class ControllerExameElectronico {

	
	//exameElectronico/verificarClassificacoes
	@Autowired
	private CandidatoRepository candidatoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PerguntaRepository perguntaRepository;
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private ExameCursoRepository exameCursoRepository;
	@Autowired
	private ExameEletronicoRepository exameEletronico;
	@Autowired
	private RepostaRepository respostaRepository;
	@Autowired
	private CandidatoAudit candidatoAuditRepository;
	@Autowired
	private ExameEletronicoService exameEletronicoService;
	@Autowired
	private RespostaExameElectronicoService respostaExameElectronicoService;
	@Autowired
	private CursoService cursoService;
	//@Autowired
	//private MensagemCandidatoRepository mensagemCandidatoRepository;
	//@Autowired
	//private MensagemService mensagemService;
	@Autowired
	private CandidatoServie candidadoService;
	@Autowired
	private ExameEletronicoRepository exameEletronicoRepository;
//	@Autowired
//	private ExameEletronico exameEletronicos;
	
	private final static Integer VALOR_POR_EXAME = 10, NUMERO_PERGUNTAS_MINIMO = 15;

	@GetMapping(value = "/exameDocurso/{id}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> exameDocurso(@PathVariable Integer id) {
		Curso curso = this.cursoService.curso(id);
		// Serviço
		List<ExameCurso> examesDoCurso = this.exameCursoRepository.findByCurso(curso);
		if (examesDoCurso.isEmpty())
			throw new RegistoNaoEncontradoException("Não há registo de exame para este curso.");
		List<TipoExameView> tiposDeExameDoCurso = new ArrayList<TipoExameView>();
		for (ExameCurso exameCurso : examesDoCurso) {
			TipoExameView tipoExameView = new TipoExameView();
			tipoExameView.setTipoExame(exameCurso.getTipoExame());
			tipoExameView.setDescricao(exameCurso.getTipoExame().getDescricao());
			tiposDeExameDoCurso.add(tipoExameView);
		}
		return this.httpResponse.MensagemDeRetorno(0, tiposDeExameDoCurso);
	}

	@GetMapping(value = "/finalizar/{numeroDocumento}/{notaFinal}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> finalizar(@PathVariable String numeroDocumento, @PathVariable Integer notaFinal) {
		Candidato candidato = this.candidadoService.candidato(numeroDocumento);
		if (candidato != null) {
			for (ExameEletronico exameEletronico : this.exameEletronico.findByCandidato(candidato)) {
				exameEletronico.setDataFimProva(new Date());
				this.exameEletronico.save(exameEletronico);
			}
			Candidato candidatos = this.candidatoRepository.findById(candidato.getId());
			candidatos.setNotaFinal(notaFinal);
			candidatos = this.candidatoRepository.save(candidatos);
			return this.httpResponse.MensagemDeRetorno(0, "Prova finalizada com sucesso.");
		} else {
			return this.httpResponse.MensagemDeRetorno(2, "Erro ao validar candidato");
		}
	}
	
	@GetMapping(value = "/editarNota/{id}/{notaFinal}/{userCode}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> editarNota(@PathVariable Integer id, @PathVariable Integer notaFinal,@PathVariable Integer userCode) {
		Candidato candidato = this.candidatoRepository.findById(id);
		if (candidato != null) {
			CadidatoAudit candidatoAudit=new CadidatoAudit();
			Usuario usuario = this.usuarioRepository.findByUserCode(userCode);
			candidatoAudit.setNotaFinal(candidato.getNotaFinal());
			candidatoAudit.setAnoLectivo(candidato.getAnoLectivo());
			candidatoAudit.setArquivoIdentificacao(candidato.getArquivoIdentificacao());
			candidatoAudit.setBairro(candidato.getBairro());
			candidatoAudit.setTipoCandidatura(candidato.getTipoCandidatura());
			candidatoAudit.setCandidatoLicenciado(candidato.getCandidatoLicenciado());
			candidatoAudit.setGrau(candidato.getGrau());
			candidatoAudit.setCurso(candidato.getCurso());
			candidatoAudit.setExame(candidato.getExame());
			candidatoAudit.setTurno(candidato.getTurno());
			candidatoAudit.setNome(candidato.getNome());
			candidatoAudit.setDocumentoIdentificacao(candidato.getDocumentoIdentificacao());
			candidatoAudit.setNumeroDocumento(candidato.getNumeroDocumento());
			candidatoAudit.setDataEmissaoDocumento(candidato.getDataEmissaoDocumento());
			candidatoAudit.setComuna(candidato.getComuna());
			candidatoAudit.setNacionalidade(candidato.getNacionalidade());
			candidatoAudit.setProvincia(candidato.getProvincia());
			candidatoAudit.setMunicipio(candidato.getMunicipio());
			candidatoAudit.setMorada(candidato.getMorada());
			candidatoAudit.setDataNascimento(candidato.getDataNascimento());
			candidatoAudit.setEstadoCivil(candidato.getEstadoCivil());
			candidatoAudit.setSexo(candidato.getSexo());
			
			candidatoAudit.setProfissao(candidato.getProfissao());
			candidatoAudit.setNomeDoPai(candidato.getNomeDoPai());
			candidatoAudit.setNomeDaMae(candidato.getNomeDaMae());
			candidatoAudit.setNecessidadeEducacaoEspecial(candidato.getNecessidadeEducacaoEspecial());
			candidatoAudit.setTipoEscolaEnsinoMedio(candidato.getTipoEscolaEnsinoMedio());
			candidatoAudit.setEscolaEnsinoMedio(candidato.getEscolaEnsinoMedio());
			candidatoAudit.setCursoEnsinoMedio(candidato.getCursoEnsinoMedio());
			candidatoAudit.setTrabalhador(candidato.getTrabalhador());
			candidatoAudit.setTelefone(candidato.getTelefone());
			candidatoAudit.setTelefone1(candidato.getTelefone1());
			candidatoAudit.setTelefone2(candidato.getTelefone2());
			candidatoAudit.setEmail(candidato.getEmail());
			candidatoAudit.setProvinciaResidencia(candidato.getProvinciaResidencia());
			candidatoAudit.setMunicipioResidencia(candidato.getMunicipioResidencia());
			candidatoAudit.setComunaResidencia(candidato.getComunaResidencia());
			
			candidatoAudit.setFotografia(candidato.getFotografia());
			candidatoAudit.setTipoDeCandidatura(candidato.getTipoDeCandidatura());
			candidatoAudit.setCursoAcesso(candidato.getCursoAcesso());
			candidatoAudit.setSeriado(candidato.getSeriado());
			candidatoAudit.setInscricaoOnline(candidato.getInscricaoOnline());
			
			candidatoAudit.setInstituicao(candidato.getInstituicao());
			candidatoAudit.setUsuario(candidato.getUsuario());
			candidatoAudit.setDataCandidatura(candidato.getDataCandidatura());
			candidatoAudit.setNumeroCandidato(candidato.getNumeroCandidato());
			candidatoAudit.setCodigoUsuarioAlterouNotaFinal(usuario.getId());
			candidatoAudit = this.candidatoAuditRepository.save(candidatoAudit);
			
			
			
			candidato.setCodigoUsuarioAlterouNotaFinal(usuario.getId());
			candidato.setNotaFinal(notaFinal);
			candidato = this.candidatoRepository.save(candidato);
			return this.httpResponse.MensagemDeRetorno(0, "Nota alterada com sucesso");
		} else {
			return this.httpResponse.MensagemDeRetorno(2, "Erro ao validar candidato");
		}
	}
	
	@PostMapping(value = "/editarNotas/{userCode}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> editarNotas(@RequestBody List<EditarNotasDTO> editarNotasDTO,@PathVariable Integer userCode) {
		for(int i=0;i<editarNotasDTO.size();i++) {
			Candidato candidato = this.candidatoRepository.findById(editarNotasDTO.get(i).getId());
			if (candidato != null) {
				CadidatoAudit candidatoAudit=new CadidatoAudit();
				
				Usuario usuario = this.usuarioRepository.findByUserCode(userCode);
				candidatoAudit.setNotaFinal(candidato.getNotaFinal());
				candidatoAudit.setAnoLectivo(candidato.getAnoLectivo());
				candidatoAudit.setArquivoIdentificacao(candidato.getArquivoIdentificacao());
				candidatoAudit.setBairro(candidato.getBairro());
				candidatoAudit.setTipoCandidatura(candidato.getTipoCandidatura());
				candidatoAudit.setCandidatoLicenciado(candidato.getCandidatoLicenciado());
				candidatoAudit.setGrau(candidato.getGrau());
				candidatoAudit.setCurso(candidato.getCurso());
				candidatoAudit.setExame(candidato.getExame());
				candidatoAudit.setTurno(candidato.getTurno());
				candidatoAudit.setNome(candidato.getNome());
				candidatoAudit.setDocumentoIdentificacao(candidato.getDocumentoIdentificacao());
				candidatoAudit.setNumeroDocumento(candidato.getNumeroDocumento());
				candidatoAudit.setDataEmissaoDocumento(candidato.getDataEmissaoDocumento());
				candidatoAudit.setComuna(candidato.getComuna());
				candidatoAudit.setNacionalidade(candidato.getNacionalidade());
				candidatoAudit.setProvincia(candidato.getProvincia());
				candidatoAudit.setMunicipio(candidato.getMunicipio());
				candidatoAudit.setMorada(candidato.getMorada());
				candidatoAudit.setDataNascimento(candidato.getDataNascimento());
				candidatoAudit.setEstadoCivil(candidato.getEstadoCivil());
				candidatoAudit.setSexo(candidato.getSexo());
				
				candidatoAudit.setProfissao(candidato.getProfissao());
				candidatoAudit.setNomeDoPai(candidato.getNomeDoPai());
				candidatoAudit.setNomeDaMae(candidato.getNomeDaMae());
				candidatoAudit.setNecessidadeEducacaoEspecial(candidato.getNecessidadeEducacaoEspecial());
				candidatoAudit.setTipoEscolaEnsinoMedio(candidato.getTipoEscolaEnsinoMedio());
				candidatoAudit.setEscolaEnsinoMedio(candidato.getEscolaEnsinoMedio());
				candidatoAudit.setCursoEnsinoMedio(candidato.getCursoEnsinoMedio());
				candidatoAudit.setTrabalhador(candidato.getTrabalhador());
				candidatoAudit.setTelefone(candidato.getTelefone());
				candidatoAudit.setTelefone1(candidato.getTelefone1());
				candidatoAudit.setTelefone2(candidato.getTelefone2());
				candidatoAudit.setEmail(candidato.getEmail());
				candidatoAudit.setProvinciaResidencia(candidato.getProvinciaResidencia());
				candidatoAudit.setMunicipioResidencia(candidato.getMunicipioResidencia());
				candidatoAudit.setComunaResidencia(candidato.getComunaResidencia());
				
				candidatoAudit.setFotografia(candidato.getFotografia());
				candidatoAudit.setTipoDeCandidatura(candidato.getTipoDeCandidatura());
				candidatoAudit.setCursoAcesso(candidato.getCursoAcesso());
				candidatoAudit.setSeriado(candidato.getSeriado());
				candidatoAudit.setInscricaoOnline(candidato.getInscricaoOnline());
				
				candidatoAudit.setInstituicao(candidato.getInstituicao());
				candidatoAudit.setUsuario(candidato.getUsuario());
				candidatoAudit.setDataCandidatura(candidato.getDataCandidatura());
				candidatoAudit.setNumeroCandidato(candidato.getNumeroCandidato());
				candidatoAudit.setCodigoUsuarioAlterouNotaFinal(usuario.getId());
				candidatoAudit = this.candidatoAuditRepository.save(candidatoAudit);
				
				
				
				candidato.setCodigoUsuarioAlterouNotaFinal(usuario.getId());
				candidato.setNotaFinal(editarNotasDTO.get(i).getNotaFinal());
				candidato = this.candidatoRepository.save(candidato);
			} 
		}
			return this.httpResponse.MensagemDeRetorno(0, "Nota alterada com sucesso");
		}
	
	
	@GetMapping(value = "/exameCandidato/{numeroDocumento}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> exameCandidato(@PathVariable String numeroDocumento) {
		TipoExame tipoExame = TipoExame.PORTUGUES;
		Candidato candidato = this.candidadoService.candidato(numeroDocumento);
		List<ExameElectronivoView> todasProvas = new ArrayList<ExameElectronivoView>();
		// Exame de portugês
		ExameElectronivoView exameElectronivoView = new ExameElectronivoView();
		List<PerguntaView> todasPerguntas = new ArrayList<PerguntaView>();
		List<ExameEletronico> perguntasPortugues = this.exameEletronico.findByCandidatoAndTipoExame(candidato,
				tipoExame);
		// Gerar Exame da disciplina se não encontrar
		perguntasPortugues = perguntasPortugues.isEmpty() ? this.gerarExame(candidato, tipoExame) : perguntasPortugues;
		for (ExameEletronico exameEletronico : perguntasPortugues) {
			exameElectronivoView.setExameInicializado(exameEletronico.getDataInicioProva() == null ? false : true);
			exameElectronivoView.setExameFinicializado(exameEletronico.getDataFimProva() == null ? false : true);
			exameElectronivoView.setTipoExame(exameEletronico.getTipoExame().getDescricao());
			PerguntaView perguntaView = new PerguntaView();
			perguntaView.setId(exameEletronico.getId());
			perguntaView.setDescricao(exameEletronico.getPergunta().getDescricao());
			List<RespostaView> respostas = new ArrayList<RespostaView>();
			for (Resposta resposta : this.respostaRepository.findByPergunta(exameEletronico.getPergunta())) {
				RespostaView respostaView = new RespostaView();
				respostaView.setId(resposta.getId());
				respostaView.setDescricao(resposta.getDescricao());
				if (exameEletronico.getResposta() == null) {
					respostaView.setSeleccionada(false);
				} else if (exameEletronico.getResposta().getId() == resposta.getId()) {
					respostaView.setSeleccionada(true);
				} else {
					respostaView.setSeleccionada(false);
				}
				respostas.add(respostaView);
			}
			perguntaView.setRespostas(respostas);
			todasPerguntas.add(perguntaView);
		}
		exameElectronivoView.setPerguntas(todasPerguntas);
		todasProvas.add(exameElectronivoView);
		// Outro Exame
		exameElectronivoView = new ExameElectronivoView();
		todasPerguntas = new ArrayList<PerguntaView>();
		List<ExameEletronico> perguntasSegundoExame = this.exameEletronico.findByCandidatoAndTipoExameNot(candidato,
				tipoExame);
		// Gerar Exame da disciplina se não encontrar
		if (perguntasSegundoExame.isEmpty()) {
			ExameCurso exameCurso = this.exameCursoRepository.findByCursoAndTipoExameNot(candidato.getCurso(),
					tipoExame);
			perguntasSegundoExame = this.gerarExame(candidato, exameCurso.getTipoExame());
		}
		for (ExameEletronico exameEletronico : perguntasSegundoExame) {
			exameElectronivoView.setExameInicializado(exameEletronico.getDataInicioProva() == null ? false : true);
			exameElectronivoView.setExameFinicializado(exameEletronico.getDataFimProva() == null ? false : true);
			exameElectronivoView.setTipoExame(exameEletronico.getTipoExame().getDescricao());
			PerguntaView perguntaView = new PerguntaView();
			perguntaView.setId(exameEletronico.getId());
			perguntaView.setDescricao(exameEletronico.getPergunta().getDescricao());
			List<RespostaView> respostas = new ArrayList<RespostaView>();
			for (Resposta resposta : this.respostaRepository.findByPergunta(exameEletronico.getPergunta())) {
				RespostaView respostaView = new RespostaView();
				respostaView.setId(resposta.getId());
				respostaView.setDescricao(resposta.getDescricao());
				if (exameEletronico.getResposta() == null) {
					respostaView.setSeleccionada(false);
				} else if (exameEletronico.getResposta().getId() == resposta.getId()) {
					respostaView.setSeleccionada(true);
				} else {
					respostaView.setSeleccionada(false);
				}
				respostas.add(respostaView);
			}
			perguntaView.setRespostas(respostas);
			todasPerguntas.add(perguntaView);
		}
		exameElectronivoView.setPerguntas(todasPerguntas);
		todasProvas.add(exameElectronivoView);
		return this.httpResponse.MensagemDeRetorno(0, todasProvas);
	}

	@PostMapping(value = "/responder", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> responder(@RequestBody RespostaCandidatoView respostaCandidato) {
		ExameEletronico exameElectronico = this.exameEletronicoService.validarPergunta(respostaCandidato.getPergunta());
		if (exameElectronico.getDataFimProva() != null)
			throw new DadoInvalidoException("Infelizmente, o tempo terminou.");
		Resposta resposta = this.respostaExameElectronicoService.validarResposta(respostaCandidato.getResposta());
		exameElectronico.setResposta(resposta);
		exameElectronico.setCerta(resposta.getCorreta());
		exameElectronico
				.setClassificacao(resposta.getCorreta() ? exameElectronico.getPergunta().getClassificacao() : 0);
		this.exameEletronico.save(exameElectronico);
		return this.httpResponse.MensagemDeRetorno(0, "Resposta salva com sucesso.");
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/gerar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody CandidatoExameCliente pExame) {
		// BUSCA O CANDIDATO - Serviço
		List<Candidato> candidato = this.candidatoRepository
				.findBynumeroDocumentoAndAnoLectivoId(pExame.getNumeroDocumento(), pExame.getAnoLectivo());
		if (candidato.isEmpty())
			throw new RegistoNaoEncontradoException("Erro ao validar candidato.");
		Map<TipoExame, ExameCurso> mapa = new HashMap<TipoExame, ExameCurso>();
		for (ExameCurso ex : this.exameCursoRepository.findByCurso(candidato.get(0).getCurso())) {
			mapa.put(ex.getTipoExame(), ex);
		}
		if (mapa.isEmpty())
			throw new RegistoNaoEncontradoException("Erro ao validar os exame dos candidato.");
		// RESOLVENDO O PROBLEMA DE EXAME DE MATEMÁTICA
		if (mapa.containsKey(TipoExame.MATEMATICA)) {
			gerarExame(candidato.get(0), TipoExame.MATEMATICA);
		} else if (mapa.containsKey(TipoExame.PORTUGUES)) {
			gerarExame(candidato.get(0), TipoExame.PORTUGUES);
		} else if (mapa.containsKey(TipoExame.HISTORIA)) {
			gerarExame(candidato.get(0), TipoExame.HISTORIA);
		} else if (mapa.containsKey(TipoExame.MATEMATICA2)) {
			gerarExame(candidato.get(0), TipoExame.MATEMATICA2);
		} else {
			throw new RegistoNaoEncontradoException("Erro ao validar os exame dos candidato.");
		}
		return this.httpResponse.MensagemDeRetorno(0, "Exame gerado com sucesso.");
	}

	private List<ExameEletronico> gerarExame(Candidato candidato, TipoExame tipoExame) {
		List<ExameEletronico> exameAtigiu = null;
		List<PerguntaCodificada> perguntasCodificadas = new ArrayList<PerguntaCodificada>();
		List<Pergunta> perguntas = this.perguntaRepository.findByTipoExameAndCursoAndEstado(tipoExame, candidato.getCurso(),true);
		if (perguntas.isEmpty())
			throw new RegistoNaoEncontradoException("Erro ao validar as perguntas do curso.");
		else if (perguntas.size() < NUMERO_PERGUNTAS_MINIMO)
			throw new DadoInvalidoException("Registo de perguntas insuficientes para gerar exame.");
		else {
			int codificador = 0;
			Map<Integer, Pergunta> mapaAB = new HashMap<Integer, Pergunta>();
			for (Pergunta pr : perguntas) {
				PerguntaCodificada perguntaCodificada = new PerguntaCodificada();
				perguntaCodificada.setId(pr.getId());
				perguntaCodificada.setPergunta(pr.getDescricao());
				perguntaCodificada.setCodigo(codificador);
				perguntasCodificadas.add(perguntaCodificada);
				mapaAB.put(codificador, pr);
				codificador++;
			}
			boolean naoPodeSair = true;
			Integer gerado = null;
			Pergunta pergunta = null;

			// GERAR PERGUNTAS DE UMA ESPECIALIDADE
			while (naoPodeSair) {
				ExameEletronico exame = new ExameEletronico();
				// SORTEIO ACONTECE DE stack A SIZE
				Random gerador = new Random();
				gerado = gerador.nextInt(codificador);
				pergunta = mapaAB.get(gerado);
				exame.setCandidato(candidato);
				exame.setCurso(candidato.getCurso());
				exame.setAnoLectivo(candidato.getAnoLectivo());
				exame.setPergunta(pergunta);
				exame.setTipoExame(tipoExame);
				// Validar se já foi introduzido
				ExameEletronico isPerguntaExiste = this.exameEletronico
						.findByCandidatoAndTipoExameAndPergunta(candidato, tipoExame, pergunta);
				if (isPerguntaExiste == null)
					this.exameEletronico.save(exame);
				// SERVIÇO
				Integer classificacaoActual = 0;
				exameAtigiu = this.exameEletronico.findByCandidatoAndTipoExame(candidato, tipoExame);
				for (ExameEletronico examePorValidar : exameAtigiu) {
					classificacaoActual += examePorValidar.getPergunta().getClassificacao();
					if (classificacaoActual == VALOR_POR_EXAME)
						return exameAtigiu;
				}
			}
			return exameAtigiu;
		}

	}

	// @GetMapping(value = "/inciarExame/{numeroDocumento}", produces =
	// "application/json")
	@RequestMapping(value = "/inciarExame/{numeroDocumento}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> inciarExame(@PathVariable String numeroDocumento, ExameEletronico exameElectronico) {
		Candidato candidato = this.candidadoService.candidato(numeroDocumento);
		List<ExameEletronico> eEletronico = this.exameEletronico.findByCandidato(candidato);
		for (ExameEletronico o : eEletronico) {
			o.setDataInicioProva(new Date());
			this.exameEletronico.save(o);
		}
		return this.httpResponse.MensagemDeRetorno(0, "Exame iniciado com sucesso!", true);
	}*/

	/*
	@RequestMapping(value = "/inciarExame/{numeroDocumento}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> devolverDataExame(@PathVariable String numeroDocumento) {
		List
		}
		
		return this.httpResponse.MensagemDeRetorno(0);
	}

	
	@GetMapping(value = "/enviarMSM/{identificador}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> enviarMSM(@PathVariable String identificador) {
		List<MensagemCandidato> candidatos = this.mensagemCandidatoRepository.findByEnviadoAndIdentificador(false,
				identificador);
		for (MensagemCandidato ms : candidatos) {

			System.err.println("TELEFONE: " + ms.getTelefone() + " - MENSAGEM: " + ms.getMensagem());
			this.mensagemService.enviarSMS(ms.getTelefone(), ms.getMensagem());

		}
		return this.httpResponse.MensagemDeRetorno(0, "Mensagens enviadas com sucesso!");
	}
	*/
/*
	@RequestMapping(value = "/verificarClassificacoes", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> verificarClassificacoes(@RequestBody BuscarClassificacoes bClass) {
		String mensagem="";
		Integer codigo=0;
		//numeroDocumento
		ExameEletronicoControlador exameEletronicoControlador;
		List<ExameEletronicoControlador> examesEletronicoControlador=new ArrayList<ExameEletronicoControlador>();
		
		

		List<Candidato> candidatos = this.candidatoRepository.findByAnoLectivoIdAndCursoId(bClass.getAnoLectivo(), bClass.getCurso());
		
		List<ExameEletronico> exame;
		Integer classificacao=0;
		for (Candidato o : candidatos) {
			exame = this.exameEletronicoRepository.findByCandidato(o);
			
			classificacao=0;
			for (ExameEletronico exc : exame) {//ENTRAR NO LOOP
				if(exc.getResposta()!=null) {
					if(exc.getResposta().getCorreta()) {
						if(exc.getResposta()!=null) {
							if(exc.getResposta().getPergunta()!=null) {
								classificacao+=exc.getResposta().getPergunta().getClassificacao();										
							}
						}
						
					}					
				}
			}//GUARDAR NOTAS DE UM ALUNO
			if(!exame.isEmpty()) {
				exameEletronicoControlador=new ExameEletronicoControlador();
				exameEletronicoControlador.setId(o.getId());
				exameEletronicoControlador.setClassificacao(o.getNotaFinal());
				exameEletronicoControlador.setCurso(o.getCurso()!=null ? o.getCurso().getDescricao():null);
				exameEletronicoControlador.setNome(o.getNome());
				exameEletronicoControlador.setTelefone(o.getTelefone());
				exameEletronicoControlador.setTurno(o.getTurno()!=null ?o.getTurno().getDescricao():null);
				
				examesEletronicoControlador.add(exameEletronicoControlador);
			}	
		}
		mensagem = examesEletronicoControlador.isEmpty() ? "Não existe aluno para listar":"";
		
		codigo   = examesEletronicoControlador.isEmpty() ? 1 : 0;
		
		return this.httpResponse.MensagemDeRetorno(codigo, mensagem,examesEletronicoControlador);
	}*/
	/*
	@RequestMapping(value = "/listaGeral", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> ListaGeral(@RequestBody BuscarClassificacoes bClass) {
		System.out.println(bClass.getDataExame());
		System.out.println(bClass.getAnoLectivo());
		System.out.println(bClass.getCurso());
		String mensagem="";
		Integer codigo=0;
		//numeroDocumento
		ExameEletronicoControlador exameEletronicoControlador;
		List<ExameEletronicoControlador> examesEletronicoControlador=new ArrayList<ExameEletronicoControlador>();
		
		

		List<Candidato> candidatos = this.candidatoRepository.findByAnoLectivoId(bClass.getAnoLectivo());
		
		List<ExameEletronico> exame;
		Integer classificacao=0;
		for (Candidato o : candidatos) {
			exame = this.exameEletronicoRepository.findByCandidatoAndDataFimProva(o,bClass.getDataExame());
			
			classificacao=0;
			for (ExameEletronico exc : exame) {//ENTRAR NO LOOP
				if(exc.getResposta()!=null) {
					if(exc.getResposta().getCorreta()) {
						if(exc.getResposta()!=null) {
							if(exc.getResposta().getPergunta()!=null) {
								classificacao+=exc.getResposta().getPergunta().getClassificacao();										
							}
						}
						
					}					
				}
			}//GUARDAR NOTAS DE UM ALUNO
			if(!exame.isEmpty()) {
				exameEletronicoControlador=new ExameEletronicoControlador();
				exameEletronicoControlador.setId(o.getId());
				exameEletronicoControlador.setClassificacao(o.getNotaFinal());
				exameEletronicoControlador.setCurso(o.getCurso()!=null ? o.getCurso().getDescricao():null);
				exameEletronicoControlador.setNome(o.getNome());
				exameEletronicoControlador.setTelefone(o.getTelefone());
				exameEletronicoControlador.setTurno(o.getTurno()!=null ?o.getTurno().getDescricao():null);
				
				examesEletronicoControlador.add(exameEletronicoControlador);
			}	
		}
		mensagem = examesEletronicoControlador.isEmpty() ? "Não existe aluno para listar":"";
		
		codigo   = examesEletronicoControlador.isEmpty() ? 1 : 0;
		
		return this.httpResponse.MensagemDeRetorno(codigo, mensagem,examesEletronicoControlador);
	}
	
}*/