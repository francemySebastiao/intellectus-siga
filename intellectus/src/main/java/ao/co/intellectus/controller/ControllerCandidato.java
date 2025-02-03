package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.BuscaAluno;
import ao.co.intellectus.DTO.CandidatoSeriar;
import ao.co.intellectus.DTO.CandidatoSimples;
import ao.co.intellectus.DTO.exame_electronico.CandidatoView;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAnexo;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.CadidatoAudit;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.Comuna;
import ao.co.intellectus.model.ContaCorrenteAluno;
import ao.co.intellectus.model.ContaCorrenteCandidato;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.CursoEnsinoMedio;
import ao.co.intellectus.model.Emolumento;
import ao.co.intellectus.model.EmolumentoHistorico;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaCandidatura;
import ao.co.intellectus.model.GuiaCandidaturaHistorico;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Municipio;
import ao.co.intellectus.model.NumeroGerado;
import ao.co.intellectus.model.Pais;
import ao.co.intellectus.model.Provincia;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.enumeracao.TipoCandidatura;
import ao.co.intellectus.model.enumeracao.TipoFatura;
//import ao.co.intellectus.model.enumeracao.TipoFatura;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.model.request.CandidatoRequest;
import ao.co.intellectus.repository.AlunoAnexoRepository;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CandidatoAudit;
import ao.co.intellectus.repository.CandidatoRepository;
import ao.co.intellectus.repository.ComunaRepository;
import ao.co.intellectus.repository.ContaCorrenteCandidatoRepository;
import ao.co.intellectus.repository.ContaCorrenteRepository;
import ao.co.intellectus.repository.CursoEnsinoMedioRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.EmolumentoHistoricoRepository;
import ao.co.intellectus.repository.EmolumentoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaHistoricoRepository;
import ao.co.intellectus.repository.GuiaCandidaturaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MuniciopioRepository;
import ao.co.intellectus.repository.NumeroGeradoRepository;
import ao.co.intellectus.repository.PaisRepository;
import ao.co.intellectus.repository.ProvinciaRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.GeradorDeArquivo;
import ao.co.intellectus.servico.GerarNumeroDocumento;
//import ao.co.intellectus.servico.GeradorDeArquivo;
//import ao.co.intellectus.servico.GerarNumeroDocumento;
import ao.co.intellectus.servico.cafold.AlunoService;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import ao.co.intellectus.util.FormataData;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/candidato")
public class ControllerCandidato {
	@Autowired
	private CandidatoRepository repository;
	@Autowired
	private CandidatoAudit candidatoAuditRepository;
	@Autowired
	private GuiaCandidaturaRepository repositoryGuia;
	@Autowired
	private AlunoRepository repositoryAluno;
	@Autowired
	private CursoRepository repositoryCurso;
	@Autowired
	private GuiaCandidaturaRepository guiaCadidatura;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	private EmolumentoRepository emolumentoRepositry;
	@Autowired
	private EmolumentoHistoricoRepository emolumentoHistoricoRepository;
	@Autowired
	private PaisRepository paisReposiotry;
	@Autowired
	private ProvinciaRepository provinciaRepsitory;
	@Autowired
	private MuniciopioRepository municipioRepository;
	@Autowired
	private CursoEnsinoMedioRepository cursoEnsinoMedioREpository;
	@Autowired
	private ComunaRepository comunaRepository;
	@Autowired
	private AlunoAnexoRepository alunoAnexo;
	@Autowired
	private NumeroGeradoRepository numeroGeradoRepository;
	@Autowired
	private GuiaCandidaturaHistoricoRepository guiaCandidaturaHistoricoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ContaCorrenteCandidatoRepository contaCandidatoRepository;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private HttpResponse httpResponse;
	@Autowired
	private GerarNumeroDocumento gerarNumeroDocService;
	@Autowired
	private GeradorDeArquivo gerarDocService;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	// @Autowired
	// private CursoRepository cursoRepository;
	
	FormataData forma = new FormataData();

	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}

	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> candidatos() {
		ResponseCliente c = new ResponseCliente();
		// Iterable<Candidato> todos = repository.findAll();
		Curso curso = this.repositoryCurso.findByIdAndStatus(16, true);
		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(18);
		List<Candidato> todos = this.repository.findByCursoAndAnoLectivo(curso, anoLectivo);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorNomeAndNumero", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorNomeAndNumero(@RequestBody BuscaAluno candidato) {

		ResponseCliente c = new ResponseCliente();
		List<Candidato> candidatos = candidato.getNumero().isEmpty()
				? this.repository.findByNomeLike("%" + candidato.getNome() + "%")
				: this.repository.findByNumeroCandidato(Integer.parseInt(candidato.getNumero()));

		if (candidatos.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(candidatos);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// buscarPorNomeLike
	@RequestMapping(value = "/buscarPorNomeLike", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorNome(@RequestBody BuscaAluno alunoBusca) {

		// System.out.println("Nome Candidato:" + nome);
		List<Candidato> candidatos = repository.findByNomeLike(alunoBusca.getNome());

		ResponseCliente c = new ResponseCliente();

		if (candidatos.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(candidatos);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorNome/{nome}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<ResponseCliente> buscarPorNome(@PathVariable String nome) {
		List<Candidato> candidatos = repository.findByNomeLike("%" + nome + "%");
		ResponseCliente c = new ResponseCliente();

		if (candidatos.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(candidatos);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorCodigo/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCodigo(@PathVariable Integer numero) {
		ResponseCliente c = new ResponseCliente();
		try {
			List<Candidato> candidato = this.repository.findByNumeroCandidatoOrderByAnoLectivoDesc(numero);
			// Candidato candidato = this.repository.findById(numero);
			if (candidato.isEmpty()) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setMensagem("Candidato não encontrado.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			// CandidatoRequest vCandidato=new CandidatoRequest();
			// BeanUtils.copyProperties(candidato, vCandidato, "cursoId");
			/* SETAR OS VALORES DE RETORNO. */
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(candidato);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception e) {
			c.setCodigo(ResponseCode.values()[1].getDescricao());
			// System.err.println(e.getLocalizedMessage());
			c.setMensagem(e.getLocalizedMessage());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/buscarParaConta/{numero}/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarParaConta(@PathVariable Integer numero, @PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();
		try {

			AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(ano);

			Candidato candidato = this.repository.findByNumeroCandidatoAndAnoLectivo(numero, anoLectivo);
			// Candidato candidato = this.repository.findById(numero);
			if (candidato == null) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setMensagem("Candidato não encontrado.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			CandidatoSimples cSimples = new CandidatoSimples();

			cSimples.setAnoLectivo(candidato.getAnoLectivo().getAnoLectivo());
			cSimples.setCurso(candidato.getCurso().getDescricao());
			cSimples.setNome(candidato.getNome());
			cSimples.setNumero(numero);
			cSimples.setId(candidato.getId());

			/* SETAR OS VALORES DE RETORNO. */
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(cSimples);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception e) {
			c.setCodigo(ResponseCode.values()[1].getDescricao());
			// System.err.println(e.getLocalizedMessage());
			c.setMensagem(e.getLocalizedMessage());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/buscarPorNumeroNoAno/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorNumeroNoAno(@PathVariable Integer numero) {
		ResponseCliente c = new ResponseCliente();
		try {
			Candidato candidato = this.repository.findById(numero);

			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(candidato);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception e) {
			c.setCodigo(ResponseCode.values()[1].getDescricao());
			// System.err.println(e.getLocalizedMessage());
			c.setMensagem(e.getLocalizedMessage());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/buscarDetalhePorId/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarDetalhes(@PathVariable Integer id) {
		ResponseCliente c = new ResponseCliente();
		try {
			Candidato candidato = repository.findById(id);
			GuiaCandidatura guiDoCandidato = this.guiaCadidatura.findByCandidato(candidato);
			if (candidato == null) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			CandidatoSeriar vCandidato = new CandidatoSeriar();
			BeanUtils.copyProperties(candidato, vCandidato, "curso");

			if (guiDoCandidato != null) {
				vCandidato.setCurso(guiDoCandidato.getCandidato().getCurso());
				vCandidato.setGuiaLiquidada(guiDoCandidato.isLiquidada());
			}
			/* SETAR OS VALORES DE RETORNO. */
			vCandidato.setSeriado(candidato.isSeriado() == null ? false : candidato.isSeriado());
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(vCandidato);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception e) {
			c.setCodigo(ResponseCode.values()[1].getDescricao());
			c.setMensagem(e.getLocalizedMessage());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@Transactional
	@PostMapping(value = "/salvarCandidaturaNormal", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvarCandidaturaNormal(@RequestBody CandidatoRequest candidatoRequest) {
		if (candidatoRequest.getUserCode() == null) {
			return this.httpResponse.MensagemDeRetorno(2, "Não foi encontrado o utilizador");
		}

		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(candidatoRequest.getAnoLectivo());
		Candidato candidato = repository.findByNumeroDocumentoAndAnoLectivo(candidatoRequest.getNumeroDocumento(),
				anoLectivo);
		if (candidato != null)
			return this.httpResponse.MensagemDeRetorno(2, "O Candidato introduzido já existe");
		List<Aluno> alunoExistente = this.repositoryAluno
				.findByNomeAndNomeDoPaiAndNomeDaMaeAndNumeroDocumentoAndFimCurso(candidatoRequest.getNome(),
						candidatoRequest.getNomeDoPai(), candidatoRequest.getNomeDaMae(),
						candidatoRequest.getNumeroDocumento(), false);

		if (alunoExistente.isEmpty()) {
			Curso curso = repositoryCurso.findByIdAndStatus(candidatoRequest.getCurso(), true);
			Pais nacionalidade = candidatoRequest.getNacionalidade() != null
					? this.paisReposiotry.findOne(candidatoRequest.getNacionalidade())
					: null;
			Provincia provincia = candidatoRequest.getProvincia() != null
					? this.provinciaRepsitory.findOne(candidatoRequest.getProvincia())
					: null;
			Provincia provinciaResidencia = candidatoRequest.getProvinciaResidencia() != null
					? this.provinciaRepsitory.findOne(candidatoRequest.getProvinciaResidencia())
					: null;
			Municipio municipioResidencia = candidatoRequest.getMunicipioResidencia() != null
					? this.municipioRepository.findOne(candidatoRequest.getMunicipioResidencia())
					: null;
			Municipio municipio = candidatoRequest.getMunicipio() != null
					? this.municipioRepository.findOne(candidatoRequest.getMunicipio())
					: null;
			Instituicao instituicao = this.instituicaoRepository.findOne(2);
			CursoEnsinoMedio tipoEscolaEnsinoMedio = candidatoRequest.getTipoEscolaEnsinoMedio() != null
					? this.cursoEnsinoMedioREpository.findOne(candidatoRequest.getTipoEscolaEnsinoMedio())
					: null;
			Comuna comuna = candidatoRequest.getComuna() != null
					? this.comunaRepository.findOne(candidatoRequest.getComuna())
					: null;
			Comuna comunaResidencia = candidatoRequest.getComunaResidencia() != null
					? this.comunaRepository.findOne(candidatoRequest.getComunaResidencia())
					: null;

			Usuario usuario = this.usuarioRepository.findByUserCode(candidatoRequest.getUserCode());

			candidato = new Candidato();
			candidato.setTipoCandidatura(candidatoRequest.getTipoCandidatura());
			candidato.setCandidatoLicenciado(candidatoRequest.isCandidatoLicenciado());
			if (curso != null) {
				candidato.setGrau(curso.getGrau());
				candidato.setCurso(curso);
			}
			candidato.setAnoLectivo(anoLectivo);
			candidato.setExame(candidatoRequest.getExame());
			candidato.setTurno(candidatoRequest.getTurno());
			candidato.setNome(candidatoRequest.getNome());
			candidato.setDocumentoIdentificacao(candidatoRequest.getDocumentoIdentificacao());
			candidato.setNumeroDocumento(candidatoRequest.getNumeroDocumento());
			candidato.setArquivoIdentificacao(candidatoRequest.getArquivoIdentificacao());
			candidato.setDataEmissaoDocumento(candidatoRequest.getDataEmissaoDocumento());
			candidato.setComuna(comuna);
			candidato.setNacionalidade(nacionalidade);
			candidato.setProvincia(provincia);
			candidato.setMunicipio(municipio);
			candidato.setBairro(candidatoRequest.getBairro());
			candidato.setMorada(candidatoRequest.getMorada());
			candidato.setDataNascimento(candidatoRequest.getDataNascimento());
			candidato.setEstadoCivil(candidatoRequest.getEstadoCivil());
			candidato.setSexo(candidatoRequest.getSexo());

			candidato.setProfissao(candidatoRequest.getProfissao());
			candidato.setNomeDoPai(candidatoRequest.getNomeDoPai());
			candidato.setNomeDaMae(candidatoRequest.getNomeDaMae());
			candidato.setNecessidadeEducacaoEspecial(candidatoRequest.getNecessidadeEducacaoEspecial());
			candidato.setTipoEscolaEnsinoMedio(tipoEscolaEnsinoMedio);
			candidato.setEscolaEnsinoMedio(candidatoRequest.getEscolaEnsinoMedio());
			candidato.setCursoEnsinoMedio(candidatoRequest.getCursoEnsinoMedio());
			candidato.setTrabalhador(candidatoRequest.isTrabalhador());
			candidato.setTelefone(candidatoRequest.getTelefone());
			candidato.setTelefone1(candidatoRequest.getTelefone1());
			candidato.setTelefone2(candidatoRequest.getTelefone2());
			candidato.setEmail(candidatoRequest.getEmail());
			candidato.setProvinciaResidencia(provinciaResidencia);
			candidato.setMunicipioResidencia(municipioResidencia);
			candidato.setComunaResidencia(comunaResidencia);
			// Adcionar
			candidato.setFotografia(false);
			candidato.setTipoDeCandidatura(false);
			candidato.setCursoAcesso(false);
			candidato.setSeriado(false);
			candidato.setInscricaoOnline(false);

			candidato.setInstituicao(instituicao);
			candidato.setUsuario(usuario);
			candidato.setDataCandidatura(new Date());
			// pegar ultimo número do candidato

			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(1);
			String proximoNumeroString = numeroGerado.getProximoNumero().toString();
			Integer proximoNumeroInteiro = Integer.parseInt(proximoNumeroString);

			candidato.setNumeroCandidato(proximoNumeroInteiro);

			if (curso.getGrau() == Grau.CESE) {
				candidato.setNotaExame(20.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			} else if (curso.getGrau() == Grau.CET) {
				candidato.setNotaExame(15.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			} else if (curso.getGrau() == Grau.CEA) {
				candidato.setNotaExame(12.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			} else if (curso.getGrau() == Grau.POSGRADUACAO) {
				candidato.setNotaExame(15.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			} else if (curso.getGrau() == Grau.MESTRADO) {
				candidato.setNotaExame(15.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			}

			candidato = repository.save(candidato);

			// sCandidato.setNumeroCandidato(sCandidato.getId());

			// atualizar os dados do controlar de geração de números
			String geradoString = proximoNumeroInteiro.toString();
			Long geradoLong = Long.parseLong(geradoString);
			numeroGerado.setUltimoNumero(geradoLong);
			numeroGerado.setProximoNumero(geradoLong + 1);
			this.numeroGeradoRepository.save(numeroGerado);
			// final da geracao do numero de candidato.

			repository.save(candidato);

			double valorGuia = 0;
			System.out.println("1 - Valor da guia" + valorGuia);

			Emolumento emolumento = this.emolumentoRepositry.findByCodigo(2003);

			EmolumentoHistorico emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
					candidato.getCurso(), candidato.getAnoLectivo());

			if (emolPag != null)

				valorGuia = emolPag.getValor();
			System.out.println("2 - Valor da guia" + valorGuia);

			/*
			 * if (instituicao != null) { String descricao = instituicao.getDescricao(); if
			 * (descricao.equals("Universidade Gregório Semedo")) { valorGuia =7000; } }
			 */

			// ínico de nova validação
			if (instituicao != null) {
				String descricao = instituicao.getDescricao();
				if (descricao.equals("Universidade Gregório Semedo")) {
					if (curso.getGrau() == Grau.LICENCIATURA) {
						valorGuia = emolPag.getValor();
						System.out.println("3 - Valor da guia" + valorGuia);
					} else {
						valorGuia = 0;
						System.out.println("4 - Valor da guia" + valorGuia);
					}
				}
			}

			// Fim da validação
			if (curso.getGrau() == Grau.CESE) {
				valorGuia = 0;
				System.out.println("5 - Valor da guia" + valorGuia);
			}

			if (curso.getGrau() == Grau.CET) {
				valorGuia = 0;
			}

			if (curso.getGrau() == Grau.CEA) {
				valorGuia = 0;
			}

			if (curso.getGrau() == Grau.POSGRADUACAO) {
				emolumento = this.emolumentoRepositry.findByCodigo(6);
				emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						candidato.getCurso(), candidato.getAnoLectivo());
//				System.out.println("Candidato--> " + candidato.getId());
//				System.out.println("Emolumento vazio--> " + emolumento.getId());
//				System.out.println("Candidato e Curso vazio--> " + candidato.getCurso().getId());
//				
				valorGuia = 0;
				System.out.println("6 - Valor da guia" + valorGuia);

				// valorGuia = emolPag.getValor();

			}

			if (curso.getGrau() == Grau.MESTRADO) {
				emolumento = this.emolumentoRepositry.findByCodigo(6);
				emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						candidato.getCurso(), candidato.getAnoLectivo());
				// valorGuia = emolPag.getValor();
				valorGuia = 0;
				System.out.println("7 - Valor da guia" + valorGuia);

			}
			System.out.println("8 - Valor da guia" + valorGuia);
			// gerar guia de candidatura.
			GuiaCandidatura guia = new GuiaCandidatura(candidato, new Date(), valorGuia, new Date(), true);

			// LIQUIDAR AUTOMATICAMENTE A GUIA
			if (valorGuia == 0) {
				guia.setLiquidada(true);
				guia.setUsuarioLiquidou(this.usuarioRepository.findOne(47));
				guia.setDataLiquidacao(new Date());
			}

			// FIM LIQUIDAR AUTOMATICAMENTE
			guia.setAnoLectivo(anoLectivo);
			guia.setInstituicao(instituicao);

			// geraNumeroGuia

			// --------------------------------------------------------
			Integer lectivo = anoLectivo.getAnoLectivo();
			// geracao de numero de guia.
			String definitivo = "";

			NumeroGerado UltimoNumero = this.numeroGeradoRepository.findOne(3);
			Long pNumeroInteiro = UltimoNumero.getProximoNumero();

			// gerar número de guia
			definitivo = geraNumeroGuia(lectivo, definitivo, pNumeroInteiro);

			// pesquisa pra ver se o número da guia já existe.
			GuiaCandidatura guiaExistente = this.repositoryGuia.findByNumeroGuia(definitivo);
			if (guiaExistente != null) {
				do {
					pNumeroInteiro++;
					definitivo = geraNumeroGuia(lectivo, definitivo, pNumeroInteiro);
					guiaExistente = this.repositoryGuia.findByNumeroGuia(definitivo);
				} while (guiaExistente != null);
			}

			String numero = "";

			/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
			String ano = String.valueOf(anoActivo.getAnoLectivo());
			String anoSubstring = ano.substring(2, 4);
			Integer anoLimpo = Integer.parseInt(anoSubstring);*/

			NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
			Long proximoNumero = numeroGeradoFP.getProximoNumero();

			// String numero = gerarNumeroDocService.geracaoNumero();
			numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);

			GuiaCandidatura proformaExiste = this.guiaCadidatura.buscarProforma(numero);
			Guia proformaGuia = this.guiaRepository.findProforma(numero);
			if (proformaExiste != null || proformaGuia != null) {
				do {
					proximoNumero++;

					numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
					proformaExiste = this.guiaCadidatura.buscarProforma(numero);
					proformaGuia = this.guiaRepository.findProforma(numero);
				} while (proformaExiste != null || proformaGuia != null);
			}

			LocalDateTime localDate = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			String dataSistema = localDate.format(formatter);

			guia.setDataEmicao(new Date());
			guia.setDataSistema(dataSistema);
			guia.setNumeroGuia(definitivo);
			guia.setNumeroFacturaProforma(numero);
			guia.setUsuarioEmitiu(usuario);
			guia.setTipoFactura(TipoFatura.FACTURA_PROFORMA);

			// salva a guia.
			GuiaCandidatura guiaCandidatura = this.repositoryGuia.save(guia);
			this.gerarDocService.gerarFileProformaCandidato(guiaCandidatura);

			numeroGeradoFP.setUltimoNumero(proximoNumero);
			numeroGeradoFP.setProximoNumero(proximoNumero + 1);
			this.numeroGeradoRepository.save(numeroGeradoFP);

			// geracao de numero de guia.
			UltimoNumero.setUltimoNumero(pNumeroInteiro);
			UltimoNumero.setProximoNumero(pNumeroInteiro + 1);
			this.numeroGeradoRepository.save(numeroGerado);

			// CASO A CANDIDATURA FOR POR NORMAL
			// EMOLUMENTO DE CÓDIGO 6
			// emolumento = this.emolumentoRepositry.findByCodigo(6);
			if (curso.getGrau() == Grau.MESTRADO) {
				emolumento = this.emolumentoRepositry.findByCodigo(6);
			} else {
				emolumento = this.emolumentoRepositry.findByCodigo(2003);
			}

			emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
					candidato.getCurso(), candidato.getAnoLectivo());
			// System.out.println("Afinal é aqui carambas --- " + emolPag);
			// APLICAR EMOLUMENTO A GUIA
			aplicarEmolumento(anoLectivo, emolumento, emolPag, guiaCandidatura, emolumento.getEmolumento());

			// candidatoRequest

			if (candidatoRequest.isCandidatoLicenciado()) {

				List<GuiaCandidaturaHistorico> historicoGuia = this.guiaCandidaturaHistoricoRepository.findByGuia(guia);

				for (GuiaCandidaturaHistorico o : historicoGuia) {
					this.guiaCandidaturaHistoricoRepository.delete(o);
				}
				// NORMAL
				// emolumento= this.emolumentoRepositry.findByCodigo(2003);
				if (curso.getGrau() == Grau.LICENCIATURA) {
					candidato.setNotaExame(10D);
					emolumento = this.emolumentoRepositry.findByCodigo(20019);
					System.out.println("Intercepto " + emolumento);
				} else {
					emolumento = this.emolumentoRepositry.findByCodigo(6);
				}
				emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						candidato.getCurso(), candidato.getAnoLectivo());
				aplicarEmolumento(guia.getAnoLectivo(), emolumento, emolPag, guia, emolumento.getEmolumento());

				historicoGuia = this.guiaCandidaturaHistoricoRepository.findByGuia(guia);
				valorGuia = 0;
				for (GuiaCandidaturaHistorico o : historicoGuia) {
					System.out.println("100 Valor da guia " + o.getValor());
					valorGuia += o.getValor();
					System.out.println("200 Valor da guia " + valorGuia);
				}

				if (curso.getGrau() == Grau.LICENCIATURA) {
					guia.setValor(valorGuia);
					System.out.println("300 Valor da guia " + valorGuia);
				} else {
					guia.setValor(0);
				}
			}

			candidato.setTipoDeCandidatura(false);
			candidato.setCursoAcesso(false);
			this.repository.save(candidato);
			// INSTANCIA UMA CONTA DE CANDIDATO.
			ContaCorrenteCandidato cCandidato = new ContaCorrenteCandidato();
			// PREENCHER OS DADOS DA CONTA DE CANDIDATO.
			cCandidato.setAnoLectivo(anoLectivo);
			cCandidato.setCandidato(candidato);
			cCandidato.setDataMovimento(new Date());
			cCandidato.setInstituicao(instituicao);
			cCandidato.setNumeroDeCandidato(candidato.getNumeroCandidato().toString());
			cCandidato.setValor(0.0);
			cCandidato.setValorAnterior(0.0);
			// SALVAR A CONTA DO CANDIDATO.
			this.contaCandidatoRepository.save(cCandidato);
			return this.httpResponse.MensagemDeRetorno(0, "Candidato salvo com sucesso!", candidato);
		} else {
			return this.httpResponse.MensagemDeRetorno(2,
					"O aluno já existente. [" + alunoExistente.get(0).getCurso().getPlano() + "]", candidato);
		}
	}

	@Transactional
	@PostMapping(value = "/salvarCandidaturaEquivalencia", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvarCandidaturaEquivalencia(
			@RequestBody CandidatoRequest candidatoRequest) {

		if (candidatoRequest.getUserCode() == null) {
			return this.httpResponse.MensagemDeRetorno(2, "Não foi encontrado o utilizador");
		}

		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(candidatoRequest.getAnoLectivo());
		Candidato candidato = repository.findByNumeroDocumentoAndAnoLectivo(candidatoRequest.getNumeroDocumento(),
				anoLectivo);
		if (candidato != null)
			return this.httpResponse.MensagemDeRetorno(2, "O Candidato introduzido já existe");
		List<Aluno> alunoExistente = this.repositoryAluno
				.findByNomeAndNomeDoPaiAndNomeDaMaeAndNumeroDocumentoAndFimCurso(candidatoRequest.getNome(),
						candidatoRequest.getNomeDoPai(), candidatoRequest.getNomeDaMae(),
						candidatoRequest.getNumeroDocumento(), false);
		if (alunoExistente.isEmpty()) {
			Curso curso = repositoryCurso.findByIdAndStatus(candidatoRequest.getCurso(), true);
			Pais nacionalidade = candidatoRequest.getNacionalidade() != null
					? this.paisReposiotry.findOne(candidatoRequest.getNacionalidade())
					: null;
			Provincia provincia = candidatoRequest.getProvincia() != null
					? this.provinciaRepsitory.findOne(candidatoRequest.getProvincia())
					: null;
			Provincia provinciaResidencia = candidatoRequest.getProvinciaResidencia() != null
					? this.provinciaRepsitory.findOne(candidatoRequest.getProvinciaResidencia())
					: null;
			Municipio municipioResidencia = candidatoRequest.getMunicipioResidencia() != null
					? this.municipioRepository.findOne(candidatoRequest.getMunicipioResidencia())
					: null;
			Municipio municipio = candidatoRequest.getMunicipio() != null
					? this.municipioRepository.findOne(candidatoRequest.getMunicipio())
					: null;
			Instituicao instituicao = this.instituicaoRepository.findOne(2);
			CursoEnsinoMedio tipoEscolaEnsinoMedio = candidatoRequest.getTipoEscolaEnsinoMedio() != null
					? this.cursoEnsinoMedioREpository.findOne(candidatoRequest.getTipoEscolaEnsinoMedio())
					: null;
			Comuna comuna = candidatoRequest.getComuna() != null
					? this.comunaRepository.findOne(candidatoRequest.getComuna())
					: null;
			Comuna comunaResidencia = candidatoRequest.getComunaResidencia() != null
					? this.comunaRepository.findOne(candidatoRequest.getComunaResidencia())
					: null;
			Usuario usuario = this.usuarioRepository.findByUserCode(candidatoRequest.getUserCode());

			usuario = this.usuarioRepository.findByUserName(candidatoRequest.getUserName());

			candidato = new Candidato();
			candidato.setTipoCandidatura(candidatoRequest.getTipoCandidatura());
			candidato.setCandidatoLicenciado(candidatoRequest.isCandidatoLicenciado());
			candidato.setGrau(curso.getGrau());
			candidato.setCurso(curso);
			candidato.setAnoLectivo(anoLectivo);
			candidato.setTurno(candidatoRequest.getTurno());
			candidato.setNome(candidatoRequest.getNome());
			candidato.setDocumentoIdentificacao(candidatoRequest.getDocumentoIdentificacao());
			candidato.setNumeroDocumento(candidatoRequest.getNumeroDocumento());
			candidato.setArquivoIdentificacao(candidatoRequest.getArquivoIdentificacao());
			candidato.setDataEmissaoDocumento(candidatoRequest.getDataEmissaoDocumento());
			candidato.setComuna(comuna);
			candidato.setNacionalidade(nacionalidade);
			candidato.setProvincia(provincia);
			candidato.setMunicipio(municipio);
			candidato.setBairro(candidatoRequest.getBairro());
			candidato.setMorada(candidatoRequest.getMorada());
			candidato.setDataNascimento(candidatoRequest.getDataNascimento());
			candidato.setEstadoCivil(candidatoRequest.getEstadoCivil());
			candidato.setSexo(candidatoRequest.getSexo());
			candidato.setProfissao(candidatoRequest.getProfissao());
			candidato.setNomeDoPai(candidatoRequest.getNomeDoPai());
			candidato.setNomeDaMae(candidatoRequest.getNomeDaMae());
			candidato.setNecessidadeEducacaoEspecial(candidatoRequest.getNecessidadeEducacaoEspecial());
			candidato.setTipoEscolaEnsinoMedio(tipoEscolaEnsinoMedio);
			candidato.setEscolaEnsinoMedio(candidatoRequest.getEscolaEnsinoMedio());
			candidato.setCursoEnsinoMedio(candidatoRequest.getCursoEnsinoMedio());
			candidato.setTrabalhador(candidatoRequest.isTrabalhador());
			candidato.setTelefone(candidatoRequest.getTelefone());
			candidato.setTelefone1(candidatoRequest.getTelefone1());
			candidato.setTelefone2(candidatoRequest.getTelefone2());
			candidato.setEmail(candidatoRequest.getEmail());
			candidato.setProvinciaResidencia(provinciaResidencia);
			candidato.setMunicipioResidencia(municipioResidencia);
			candidato.setComunaResidencia(comunaResidencia);
			candidato.setNotaExame(10D);
			// Adcionar
			candidato.setFotografia(false);
			candidato.setTipoDeCandidatura(true);
			candidato.setCursoAcesso(false);
			candidato.setSeriado(false);
			candidato.setInscricaoOnline(false);

			candidato.setInstituicao(instituicao);
			candidato.setUsuario(usuario);
			candidato.setDataCandidatura(new Date());
			// pegar ultimo número do candidato

			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(1);
			String proximoNumeroString = numeroGerado.getProximoNumero().toString();
			Integer proximoNumeroInteiro = Integer.parseInt(proximoNumeroString);

			candidato.setNumeroCandidato(proximoNumeroInteiro);

			if (curso.getGrau() == Grau.CESE) {
				candidato.setNotaExame(20.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			} else if (curso.getGrau() == Grau.POSGRADUACAO) {
				candidato.setNotaExame(20.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			} else if (curso.getGrau() == Grau.MESTRADO) {
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			}

			candidato.setUsuario(usuario);
			candidato = repository.save(candidato);

			// atualizar os dados do controlar de geração de números
			String geradoString = proximoNumeroInteiro.toString();
			Long geradoLong = Long.parseLong(geradoString);
			numeroGerado.setUltimoNumero(geradoLong);
			numeroGerado.setProximoNumero(geradoLong + 1);
			this.numeroGeradoRepository.save(numeroGerado);
			// final da geracao do numero de candidato.

			repository.save(candidato);

			double valorGuia = 0;

			// ATRELA O PEDIDO DE EQUIVALÊNCIA
			Emolumento emolumento = this.emolumentoRepositry.findByCodigo(12);
			EmolumentoHistorico emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
					candidato.getCurso(), candidato.getAnoLectivo());
			valorGuia += emolPag.getValor();

			// ATRELA DE FACTO A CANDIDATURA NORMAL
			emolumento = this.emolumentoRepositry.findByCodigo(6);
			emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
					candidato.getCurso(), candidato.getAnoLectivo());
			valorGuia += emolPag.getValor();

			if (curso.getGrau() == Grau.CESE) {
				valorGuia = 0;
			} else if (curso.getGrau() == Grau.POSGRADUACAO) {
				valorGuia = 0;
			} else if (curso.getGrau() == Grau.MESTRADO) {
				emolumento = this.emolumentoRepositry.findByCodigo(6);
				emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						candidato.getCurso(), candidato.getAnoLectivo());
				valorGuia = emolPag.getValor();
			}

			// gerar guia de candidatura.
			// if(candidatoRequest.getRenovarEquivalencia())
			// valorGuia = 0;

			GuiaCandidatura guia = new GuiaCandidatura(candidato, new Date(), valorGuia, new Date(), true);
			// LIQUIDAR AUTOMATICAMENTE A GUIA
			if (valorGuia == 0) {
				guia.setLiquidada(true);
				guia.setUsuarioLiquidou(this.usuarioRepository.findOne(47));
				guia.setDataLiquidacao(new Date());
			}
			// FIM LIQUIDAR AUTOMATICAMENTE
			guia.setAnoLectivo(anoLectivo);
			guia.setInstituicao(instituicao);
			// geraNumeroGuia
			// --------------------------------------------------------
			Integer lectivo = anoLectivo.getAnoLectivo();
			// geracao de numero de guia.
			String definitivo = "";
			NumeroGerado UltimoNumero = this.numeroGeradoRepository.findOne(3);
			Long pNumeroInteiro = UltimoNumero.getProximoNumero();
			// gerar número de guia
			definitivo = geraNumeroGuia(lectivo, definitivo, pNumeroInteiro);
			// pesquisa pra ver se o número da guia já existe.
			GuiaCandidatura guiaExistente = this.repositoryGuia.findByNumeroGuia(definitivo);
			if (guiaExistente != null) {
				do {
					pNumeroInteiro++;
					definitivo = geraNumeroGuia(lectivo, definitivo, pNumeroInteiro);
					guiaExistente = this.repositoryGuia.findByNumeroGuia(definitivo);
				} while (guiaExistente != null);
			}

			String numero = "";

			/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
			String ano = String.valueOf(anoActivo.getAnoLectivo());
			String anoSubstring = ano.substring(2, 4);
			Integer anoLimpo = Integer.parseInt(anoSubstring);*/

			NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
			Long proximoNumero = numeroGeradoFP.getProximoNumero();

			// String numero = gerarNumeroDocService.geracaoNumero();
			numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);

			GuiaCandidatura proformaExiste = this.guiaCadidatura.buscarProforma(numero);
			Guia proformaGuia = this.guiaRepository.findProforma(numero);
			if (proformaExiste != null || proformaGuia != null) {
				do {
					proximoNumero++;

					numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
					proformaExiste = this.guiaCadidatura.buscarProforma(numero);
					proformaGuia = this.guiaRepository.findProforma(numero);
				} while (proformaExiste != null || proformaGuia != null);
			}

			LocalDateTime localDate = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			String dataSistema = localDate.format(formatter);

			guia.setDataEmicao(new Date());
			guia.setDataSistema(dataSistema);
			guia.setNumeroGuia(definitivo);
			guia.setNumeroFacturaProforma(numero);
			guia.setUsuarioEmitiu(usuario);
			guia.setTipoFactura(TipoFatura.FACTURA_PROFORMA);

			// salva a guia.
			GuiaCandidatura guiaCandidatura = this.repositoryGuia.save(guia);
			this.gerarDocService.gerarFileProformaCandidato(guiaCandidatura);

			numeroGeradoFP.setUltimoNumero(proximoNumero);
			numeroGeradoFP.setProximoNumero(proximoNumero + 1);
			this.numeroGeradoRepository.save(numeroGeradoFP);

			// geracao de numero de guia.
			UltimoNumero.setUltimoNumero(pNumeroInteiro);
			UltimoNumero.setProximoNumero(pNumeroInteiro + 1);
			this.numeroGeradoRepository.save(numeroGerado);
			// EMOLUMENTO DE CÓDIGO 6
			emolumento = this.emolumentoRepositry.findByCodigo(6);
			emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
					candidato.getCurso(), candidato.getAnoLectivo());
			// APLICAR EMOLUMENTO A GUIA
			aplicarEmolumento(anoLectivo, emolumento, emolPag, guiaCandidatura, "Candidatura");

			// EMOLUMENTO DE CÓDIGO 12
			emolumento = this.emolumentoRepositry.findByCodigo(12);
			emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
					candidato.getCurso(), candidato.getAnoLectivo());
			// APLICAR EMOLUMENTO A GUIA
			aplicarEmolumento(anoLectivo, emolumento, emolPag, guiaCandidatura, "Pedido de Equivalência");

			// INSTANCIA UMA CONTA DE CANDIDATO.
			ContaCorrenteCandidato cCandidato = new ContaCorrenteCandidato();
			// PREENCHER OS DADOS DA CONTA DE CANDIDATO.
			cCandidato.setAnoLectivo(anoLectivo);
			cCandidato.setCandidato(candidato);
			cCandidato.setDataMovimento(new Date());
			cCandidato.setInstituicao(instituicao);
			cCandidato.setNumeroDeCandidato(candidato.getNumeroCandidato().toString());
			cCandidato.setValor(0.0);
			cCandidato.setValorAnterior(0.0);
			// SALVAR A CONTA DO CANDIDATO.
			this.contaCandidatoRepository.save(cCandidato);
			return this.httpResponse.MensagemDeRetorno(0, "Candidato salvo com sucesso!", candidato);
		} else {
			return this.httpResponse.MensagemDeRetorno(2,
					"O aluno já existente. [" + alunoExistente.get(0).getCurso().getPlano() + "]", candidato);
		}
	}

	@Transactional
	@PostMapping(value = "/salvarCandidaturaCursoAcesso", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvarCandidaturaCursoAcesso(
			@RequestBody CandidatoRequest candidatoRequest) {

		if (candidatoRequest.getUserCode() == null) {
			return this.httpResponse.MensagemDeRetorno(2, "Não foi encontrado o utilizador");
		}

		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(candidatoRequest.getAnoLectivo());
		Candidato candidato = repository.findByNumeroDocumentoAndAnoLectivo(candidatoRequest.getNumeroDocumento(),
				anoLectivo);
		if (candidato != null)
			return this.httpResponse.MensagemDeRetorno(2, "O Candidato introduzido já existe");
		List<Aluno> alunoExistente = this.repositoryAluno
				.findByNomeAndNomeDoPaiAndNomeDaMaeAndNumeroDocumentoAndFimCurso(candidatoRequest.getNome(),
						candidatoRequest.getNomeDoPai(), candidatoRequest.getNomeDaMae(),
						candidatoRequest.getNumeroDocumento(), false);
		if (alunoExistente.isEmpty()) {
			Curso curso = repositoryCurso.findByIdAndStatus(candidatoRequest.getCurso(), true);
			Pais nacionalidade = candidatoRequest.getNacionalidade() != null
					? this.paisReposiotry.findOne(candidatoRequest.getNacionalidade())
					: null;
			Provincia provincia = candidatoRequest.getProvincia() != null
					? this.provinciaRepsitory.findOne(candidatoRequest.getProvincia())
					: null;
			Provincia provinciaResidencia = candidatoRequest.getProvinciaResidencia() != null
					? this.provinciaRepsitory.findOne(candidatoRequest.getProvinciaResidencia())
					: null;
			Municipio municipioResidencia = candidatoRequest.getMunicipioResidencia() != null
					? this.municipioRepository.findOne(candidatoRequest.getMunicipioResidencia())
					: null;
			Municipio municipio = candidatoRequest.getMunicipio() != null
					? this.municipioRepository.findOne(candidatoRequest.getMunicipio())
					: null;
			Instituicao instituicao = this.instituicaoRepository.findOne(2);
			CursoEnsinoMedio tipoEscolaEnsinoMedio = candidatoRequest.getTipoEscolaEnsinoMedio() != null
					? this.cursoEnsinoMedioREpository.findOne(candidatoRequest.getTipoEscolaEnsinoMedio())
					: null;
			Comuna comuna = candidatoRequest.getComuna() != null
					? this.comunaRepository.findOne(candidatoRequest.getComuna())
					: null;
			Comuna comunaResidencia = candidatoRequest.getComunaResidencia() != null
					? this.comunaRepository.findOne(candidatoRequest.getComunaResidencia())
					: null;
			Usuario usuario = this.usuarioRepository.findByUserCode(candidatoRequest.getUserCode());

			usuario = this.usuarioRepository.findByUserName(candidatoRequest.getUserName());

			candidato = new Candidato();
			candidato.setTipoCandidatura(candidatoRequest.getTipoCandidatura());
			// candidato.setCandidatoLicenciado(candidatoRequest.isCandidatoLicenciado());
			// candidato.setGrau(curso.getGrau());
			candidato.setCurso(curso);
			candidato.setAnoLectivo(anoLectivo);
			candidato.setTurno(candidatoRequest.getTurno());
			candidato.setNome(candidatoRequest.getNome());
			candidato.setDocumentoIdentificacao(candidatoRequest.getDocumentoIdentificacao());
			candidato.setNumeroDocumento(candidatoRequest.getNumeroDocumento());
			candidato.setArquivoIdentificacao(candidatoRequest.getArquivoIdentificacao());
			candidato.setDataEmissaoDocumento(candidatoRequest.getDataEmissaoDocumento());
			candidato.setComuna(comuna);
			candidato.setNacionalidade(nacionalidade);
			candidato.setProvincia(provincia);
			candidato.setMunicipio(municipio);
			candidato.setBairro(candidatoRequest.getBairro());
			candidato.setMorada(candidatoRequest.getMorada());
			candidato.setDataNascimento(candidatoRequest.getDataNascimento());
			candidato.setEstadoCivil(candidatoRequest.getEstadoCivil());
			candidato.setSexo(candidatoRequest.getSexo());
			candidato.setProfissao(candidatoRequest.getProfissao());
			candidato.setNomeDoPai(candidatoRequest.getNomeDoPai());
			candidato.setNomeDaMae(candidatoRequest.getNomeDaMae());
			candidato.setNecessidadeEducacaoEspecial(candidatoRequest.getNecessidadeEducacaoEspecial());
			candidato.setTipoEscolaEnsinoMedio(tipoEscolaEnsinoMedio);
			candidato.setEscolaEnsinoMedio(candidatoRequest.getEscolaEnsinoMedio());
			candidato.setCursoEnsinoMedio(candidatoRequest.getCursoEnsinoMedio());
			candidato.setTrabalhador(candidatoRequest.isTrabalhador());
			candidato.setTelefone(candidatoRequest.getTelefone());
			candidato.setTelefone1(candidatoRequest.getTelefone1());
			candidato.setTelefone2(candidatoRequest.getTelefone2());
			candidato.setEmail(candidatoRequest.getEmail());
			candidato.setProvinciaResidencia(provinciaResidencia);
			candidato.setMunicipioResidencia(municipioResidencia);
			candidato.setComunaResidencia(comunaResidencia);
			// Adcionar
			candidato.setFotografia(false);
			candidato.setTipoDeCandidatura(false);
			candidato.setCursoAcesso(true);
			candidato.setSeriado(false);
			candidato.setInscricaoOnline(false);

			candidato.setInstituicao(instituicao);
			candidato.setUsuario(usuario);
			candidato.setDataCandidatura(new Date());
			// pegar ultimo número do candidato

			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(1);
			String proximoNumeroString = numeroGerado.getProximoNumero().toString();
			Integer proximoNumeroInteiro = Integer.parseInt(proximoNumeroString);

			candidato.setNumeroCandidato(proximoNumeroInteiro);

			if (curso.getGrau() == Grau.CESE) {
				candidato.setNotaExame(20.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			} else if (curso.getGrau() == Grau.POSGRADUACAO) {
				candidato.setNotaExame(20.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			} else if (curso.getGrau() == Grau.MESTRADO) {
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			}

			candidato.setUsuario(usuario);
			candidato = repository.save(candidato);

			// atualizar os dados do controlar de geração de números
			String geradoString = proximoNumeroInteiro.toString();
			Long geradoLong = Long.parseLong(geradoString);
			numeroGerado.setUltimoNumero(geradoLong);
			numeroGerado.setProximoNumero(geradoLong + 1);
			this.numeroGeradoRepository.save(numeroGerado);
			// final da geracao do numero de candidato.
			repository.save(candidato);

			double valorGuia = 0;

			Emolumento emolumento;
			EmolumentoHistorico emolPag;

			emolumento = this.emolumentoRepositry.findByCodigo(95);
			emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
					candidato.getCurso(), candidato.getAnoLectivo());
			valorGuia = emolPag.getValor();

			if (curso.getGrau() == Grau.CESE) {
				valorGuia = 0;
			} else if (curso.getGrau() == Grau.POSGRADUACAO) {
				valorGuia = 0;
			} else if (curso.getGrau() == Grau.MESTRADO) {
				emolumento = this.emolumentoRepositry.findByCodigo(6);
				emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						candidato.getCurso(), candidato.getAnoLectivo());
				valorGuia = emolPag.getValor();
			}
			// gerar guia de candidatura.
			GuiaCandidatura guia = new GuiaCandidatura(candidato, new Date(), valorGuia, new Date(), true);
			Instituicao inst = this.instituicaoRepository.findOne(2);

			// LIQUIDAR AUTOMATICAMENTE A GUIA
			if (valorGuia == 0) {
				guia.setLiquidada(true);
				guia.setUsuarioLiquidou(this.usuarioRepository.findOne(47));
				guia.setDataLiquidacao(new Date());
			}

			// FIM LIQUIDAR AUTOMATICAMENTE
			guia.setAnoLectivo(anoLectivo);
			guia.setInstituicao(inst);

			// geraNumeroGuia

			// --------------------------------------------------------
			Integer lectivo = anoLectivo.getAnoLectivo();
			// geracao de numero de guia.
			String definitivo = "";

			NumeroGerado UltimoNumero = this.numeroGeradoRepository.findOne(3);
			Long pNumeroInteiro = UltimoNumero.getProximoNumero();

			// gerar número de guia
			definitivo = geraNumeroGuia(lectivo, definitivo, pNumeroInteiro);

			// pesquisa pra ver se o número da guia já existe.
			GuiaCandidatura guiaExistente = this.repositoryGuia.findByNumeroGuia(definitivo);
			if (guiaExistente != null) {
				do {
					pNumeroInteiro++;
					definitivo = geraNumeroGuia(lectivo, definitivo, pNumeroInteiro);
					guiaExistente = this.repositoryGuia.findByNumeroGuia(definitivo);
				} while (guiaExistente != null);
			}

			String numero = "";

			/*AnoLectivo anoActivo = anoLectivoRepository.buscarPorEstado();
			String ano = String.valueOf(anoActivo.getAnoLectivo());
			String anoSubstring = ano.substring(2, 4);
			Integer anoLimpo = Integer.parseInt(anoSubstring);*/

			NumeroGerado numeroGeradoFP = this.numeroGeradoRepository.findOne(6);
			Long proximoNumero = numeroGeradoFP.getProximoNumero();

			// String numero = gerarNumeroDocService.geracaoNumero();
			numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);

			GuiaCandidatura proformaExiste = this.guiaCadidatura.buscarProforma(numero);
			Guia proformaGuia = this.guiaRepository.findProforma(numero);
			if (proformaExiste != null || proformaGuia != null) {
				do {
					proximoNumero++;

					numero = gerarNumeroDocService.gerarNumeroFacturaProforma(numero, forma.anoLectivo(), proximoNumero);
					proformaExiste = this.guiaCadidatura.buscarProforma(numero);
					proformaGuia = this.guiaRepository.findProforma(numero);
				} while (proformaExiste != null || proformaGuia != null);
			}

			LocalDateTime localDate = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			String dataSistema = localDate.format(formatter);

			guia.setDataEmicao(new Date());
			guia.setDataSistema(dataSistema);
			guia.setNumeroGuia(definitivo);
			guia.setNumeroFacturaProforma(numero);
			guia.setUsuarioEmitiu(usuario);
			guia.setTipoFactura(TipoFatura.FACTURA_PROFORMA);

			// salva a guia.
			GuiaCandidatura guiaCandidatura = this.repositoryGuia.save(guia);
			this.gerarDocService.gerarFileProformaCandidato(guiaCandidatura);

			numeroGeradoFP.setUltimoNumero(proximoNumero);
			numeroGeradoFP.setProximoNumero(proximoNumero + 1);
			this.numeroGeradoRepository.save(numeroGeradoFP);

			// geracao de numero de guia.
			UltimoNumero.setUltimoNumero(pNumeroInteiro);
			UltimoNumero.setProximoNumero(pNumeroInteiro + 1);
			this.numeroGeradoRepository.save(numeroGerado);

			// CODIGO DE EMOLUMENTO 95
			emolumento = this.emolumentoRepositry.findByCodigo(95);
			emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
					candidato.getCurso(), candidato.getAnoLectivo());

			// APLICAR EMOLUMENTO A GUIA
			aplicarEmolumento(anoLectivo, emolumento, emolPag, guiaCandidatura, "Candidatura por curso de acesso.");

			// INSTANCIA UMA CONTA DE CANDIDATO.
			ContaCorrenteCandidato cCandidato = new ContaCorrenteCandidato();
			// PREENCHER OS DADOS DA CONTA DE CANDIDATO.
			cCandidato.setAnoLectivo(anoLectivo);
			cCandidato.setCandidato(candidato);
			cCandidato.setDataMovimento(new Date());
			cCandidato.setInstituicao(instituicao);
			cCandidato.setNumeroDeCandidato(candidato.getNumeroCandidato().toString());
			cCandidato.setValor(0.0);
			cCandidato.setValorAnterior(0.0);
			// SALVAR A CONTA DO CANDIDATO.
			this.contaCandidatoRepository.save(cCandidato);
			return this.httpResponse.MensagemDeRetorno(0, "Candidato salvo com sucesso!", candidato);
		} else {
			return this.httpResponse.MensagemDeRetorno(2,
					"O aluno já existente. [" + alunoExistente.get(0).getCurso().getPlano() + "]", candidato);
		}
	}

	@Transactional
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody CandidatoRequest candidatoRequest) {
		ResponseCliente c = new ResponseCliente();
		AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(candidatoRequest.getAnoLectivo());
		Candidato pesquisado = repository.findByNumeroDocumentoAndNomeAndAnoLectivo(
				candidatoRequest.getNumeroDocumento(), candidatoRequest.getNome(), anoLectivo);

		Candidato candidato = new Candidato();
		if (pesquisado == null) {
			// curso
			Curso curosId = repositoryCurso.findByIdAndStatus(candidatoRequest.getCurso(), true);
			// nacionalidade
			Pais nacionalidade = candidatoRequest.getNacionalidade() != null
					? this.paisReposiotry.findOne(candidatoRequest.getNacionalidade())
					: null;
			Provincia provincia = candidatoRequest.getProvincia() != null
					? this.provinciaRepsitory.findOne(candidatoRequest.getProvincia())
					: null;
			Provincia provinciaResidencia = candidatoRequest.getProvinciaResidencia() != null
					? this.provinciaRepsitory.findOne(candidatoRequest.getProvinciaResidencia())
					: null;
			Municipio municipioResidencia = candidatoRequest.getMunicipioResidencia() != null
					? this.municipioRepository.findOne(candidatoRequest.getMunicipioResidencia())
					: null;
			// municipio de nascencia / Naturalidade
			Municipio municipio = candidatoRequest.getMunicipio() != null
					? this.municipioRepository.findOne(candidatoRequest.getMunicipio())
					: null;
			// instituição
			Instituicao instituicao = candidatoRequest.getInstituicao() != null
					? this.instituicaoRepository.findOne(candidatoRequest.getInstituicao())
					: null;
			// Tipo escola
			CursoEnsinoMedio tipoEscolaEnsinoMedio = candidatoRequest.getTipoEscolaEnsinoMedio() != null
					? this.cursoEnsinoMedioREpository.findOne(candidatoRequest.getTipoEscolaEnsinoMedio())
					: null;
			// comuna residencia
			Comuna comuna = candidatoRequest.getComunaResidencia() != null
					? this.comunaRepository.findOne(candidatoRequest.getComunaResidencia())
					: null;

			//

			List<Aluno> alunoExistente = this.repositoryAluno
					.findByNomeAndNomeDoPaiAndNomeDaMaeAndNumeroDocumentoAndFimCurso(candidatoRequest.getNomeDoPai(),
							candidatoRequest.getNomeDoPai(), candidatoRequest.getNomeDaMae(),
							candidatoRequest.getNumeroDocumento(), false);

			if (!alunoExistente.isEmpty()) {
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				c.setResultado(candidato);
				c.setMensagem("O aluno já existente. [" + alunoExistente.get(0).getCurso().getPlano() + "]");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

			// BUSCA USUARIO EMITIU GUIA-CANDIDATURA

			// VERIFICAR O FEITO DO USUARIO
			// Usuario usuario =
			// this.usuarioRepository.findOne(candidatoRequest.getUserCode());

			Usuario usuario = this.usuarioRepository.findByUserCode(candidatoRequest.getUserCode());

			if (candidatoRequest.getUserName() != null)
				usuario = this.usuarioRepository.findByUserName(candidatoRequest.getUserName());

			BeanUtils.copyProperties(candidatoRequest, candidato, "curso", "nacionalidade", "provincia", "municipio");
			candidato.setCurso(curosId);
			candidato.setAnoLectivo(anoLectivo);
			candidato.setNacionalidade(nacionalidade);
			candidato.setProvincia(provincia);
			candidato.setMunicipio(municipio);
			candidato.setProvinciaResidencia(provinciaResidencia);
			candidato.setMunicipioResidencia(municipioResidencia);
			// usuario
			candidato.setInstituicao(instituicao);
			candidato.setComunaResidencia(comuna);

			candidato.setTipoEscolaEnsinoMedio(tipoEscolaEnsinoMedio);

			candidato.setDataCandidatura(new Date());
			candidato.setProfissao(candidatoRequest.getProfissao());
			// pegar ultimo número do candidato
			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(1);
			String proximoNumeroString = numeroGerado.getProximoNumero().toString();
			Integer proximoNumeroInteiro = Integer.parseInt(proximoNumeroString);

			candidato.setNumeroCandidato(proximoNumeroInteiro);

			if (curosId.getGrau() == Grau.CESE) {
				candidato.setNotaExame(20.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			}
			if (curosId.getGrau() == Grau.POSGRADUACAO) {
				candidato.setNotaExame(20.0);
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			}
			if (curosId.getGrau() == Grau.MESTRADO) {
				candidatoRequest.setCursoAcesso(false);
				candidatoRequest.setTipoDeCandidatura(false);
			}

			candidato.setUsuario(usuario);
			Candidato sCandidato = repository.save(candidato);

			// sCandidato.setNumeroCandidato(sCandidato.getId());

			// atualizar os dados do controlar de geração de números
			String geradoString = proximoNumeroInteiro.toString();
			Long geradoLong = Long.parseLong(geradoString);
			numeroGerado.setUltimoNumero(geradoLong);
			numeroGerado.setProximoNumero(geradoLong + 1);
			this.numeroGeradoRepository.save(numeroGerado);
			// final da geracao do numero de candidato.

			repository.save(sCandidato);

			double valorGuia = 0;

			Emolumento emolumento;
			EmolumentoHistorico emolPag;

			if (candidatoRequest.isCursoAcesso()) {
				emolumento = this.emolumentoRepositry.findByCodigo(95);
				emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						sCandidato.getCurso(), sCandidato.getAnoLectivo());
				valorGuia = emolPag.getValor();
			} else {
				if (candidatoRequest.isTipoDeCandidatura()) {
					emolumento = this.emolumentoRepositry.findByCodigo(6);
					emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
							sCandidato.getCurso(), sCandidato.getAnoLectivo());
					valorGuia = emolPag.getValor();
					// 12
					emolumento = this.emolumentoRepositry.findByCodigo(12);
					emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
							sCandidato.getCurso(), sCandidato.getAnoLectivo());
					valorGuia += emolPag.getValor();
				} else {
					emolumento = this.emolumentoRepositry.findByCodigo(6);
					emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
							sCandidato.getCurso(), sCandidato.getAnoLectivo());

					if (candidatoRequest.isCandidatoLicenciado()) {
						valorGuia = emolPag.getValor();
					} else {

						valorGuia = emolPag.getValor();
						if (instituicao != null) {
							String descricao = instituicao.getDescricao();
							if (descricao.equals("Universidade Gregório Semedo")) {
								valorGuia = 0;
							}

						}
					}
				}
			}

			if (curosId.getGrau() == Grau.CESE) {
				valorGuia = 0;
			}

			if (curosId.getGrau() == Grau.POSGRADUACAO) {
				emolumento = this.emolumentoRepositry.findByCodigo(6);
				emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						sCandidato.getCurso(), sCandidato.getAnoLectivo());
				valorGuia = emolPag.getValor();
			}

			if (curosId.getGrau() == Grau.MESTRADO) {
				emolumento = this.emolumentoRepositry.findByCodigo(6);
				emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						sCandidato.getCurso(), sCandidato.getAnoLectivo());
				valorGuia = emolPag.getValor();
			}
			// gerar guia de candidatura.
			GuiaCandidatura guia = new GuiaCandidatura(sCandidato, new Date(), valorGuia, new Date(), true);
			Instituicao inst = this.instituicaoRepository.findOne(2);

			// LIQUIDAR AUTOMATICAMENTE A GUIA
			if (valorGuia == 0) {
				guia.setLiquidada(true);
				guia.setUsuarioLiquidou(this.usuarioRepository.findOne(47));
				guia.setDataLiquidacao(new Date());
			}

			// FIM LIQUIDAR AUTOMATICAMENTE
			guia.setAnoLectivo(anoLectivo);
			guia.setInstituicao(inst);

			// geraNumeroGuia

			// --------------------------------------------------------
			Integer lectivo = anoLectivo.getAnoLectivo();
			// geracao de numero de guia.
			String definitivo = "";

			NumeroGerado UltimoNumero = this.numeroGeradoRepository.findOne(3);
			Long pNumeroInteiro = UltimoNumero.getProximoNumero();

			// gerar número de guia
			definitivo = geraNumeroGuia(lectivo, definitivo, pNumeroInteiro);

			// pesquisa pra ver se o número da guia já existe.
			GuiaCandidatura guiaExistente = this.repositoryGuia.findByNumeroGuia(definitivo);
			if (guiaExistente != null) {
				do {
					pNumeroInteiro++;
					definitivo = geraNumeroGuia(lectivo, definitivo, pNumeroInteiro);
					guiaExistente = this.repositoryGuia.findByNumeroGuia(definitivo);
				} while (guiaExistente != null);
			}
			guia.setNumeroGuia(definitivo);

			guia.setUsuarioEmitiu(usuario);
			// salva a guia.

			GuiaCandidatura guiaCandidatura = this.repositoryGuia.save(guia);

			// geracao de numero de guia.
			UltimoNumero.setUltimoNumero(pNumeroInteiro);
			UltimoNumero.setProximoNumero(pNumeroInteiro + 1);
			this.numeroGeradoRepository.save(numeroGerado);
			// -----------------------------------------
			/*
			 * AQUI NESTE BLOCO DE CODIGO, RELACIONAMOS A GUIA COM O HISTÓRICO
			 * 
			 * 
			 */

			// GuiaCandidaturaHistorico gHistorico=null;
			if (candidatoRequest.isCursoAcesso()) {
				// CASO A CANDIDATURA FOR POR CURSO DE ACESSO.

				// CODIGO DE EMOLUMENTO 95
				emolumento = this.emolumentoRepositry.findByCodigo(95);
				emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
						sCandidato.getCurso(), sCandidato.getAnoLectivo());

				// APLICAR EMOLUMENTO A GUIA
				aplicarEmolumento(anoLectivo, emolumento, emolPag, guiaCandidatura, "Candidatura por curso de acesso.");
			} else {
				if (candidatoRequest.isTipoDeCandidatura()) {
					// CASO A CANDIDATURA FOR POR EQUIVALÊNCIA

					// EMOLUMENTO DE CÓDIGO 6
					emolumento = this.emolumentoRepositry.findByCodigo(6);
					emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
							sCandidato.getCurso(), sCandidato.getAnoLectivo());

					// APLICAR EMOLUMENTO A GUIA
					aplicarEmolumento(anoLectivo, emolumento, emolPag, guiaCandidatura, "Equivalência");

					// EMOLUMENTO DE CÓDIGO 12
					emolumento = this.emolumentoRepositry.findByCodigo(12);
					emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
							sCandidato.getCurso(), sCandidato.getAnoLectivo());

					// APLICAR EMOLUMENTO A GUIA
					aplicarEmolumento(anoLectivo, emolumento, emolPag, guiaCandidatura, "Candidatura Normal");
				} else {
					// CASO A CANDIDATURA FOR POR NORMAL
					// EMOLUMENTO DE CÓDIGO 6
					emolumento = this.emolumentoRepositry.findByCodigo(6);
					emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
							sCandidato.getCurso(), sCandidato.getAnoLectivo());

					// APLICAR EMOLUMENTO A GUIA
					aplicarEmolumento(anoLectivo, emolumento, emolPag, guiaCandidatura, "Candidatura Normal");

					sCandidato.setTipoDeCandidatura(false);
					sCandidato.setCursoAcesso(false);

					this.repository.save(sCandidato);
				}
			}
			// INSTANCIA UMA CONTA DE CANDIDATO.
			ContaCorrenteCandidato cCandidato = new ContaCorrenteCandidato();
			// PREENCHER OS DADOS DA CONTA DE CANDIDATO.
			cCandidato.setAnoLectivo(anoLectivo);
			cCandidato.setCandidato(sCandidato);
			cCandidato.setDataMovimento(new Date());
			cCandidato.setInstituicao(instituicao);
			cCandidato.setNumeroDeCandidato(sCandidato.getNumeroCandidato().toString());
			cCandidato.setValor(0.0);
			cCandidato.setValorAnterior(0.0);
			// SALVAR A CONTA DO CANDIDATO.
			this.contaCandidatoRepository.save(cCandidato);

			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(candidato);
			c.setMensagem("Candidato salvo com sucesso!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setResultado(candidato);
			c.setMensagem("O Candidato já existe nesse semestre.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

	}

	@Transactional
	private void aplicarEmolumento(AnoLectivo anoLectivo, Emolumento emolumento, EmolumentoHistorico emolPag,
			GuiaCandidatura guiaCandidatura, String tipo) {
		GuiaCandidaturaHistorico gHistorico = new GuiaCandidaturaHistorico();

		gHistorico.setGuia(guiaCandidatura);
		gHistorico.setObs(tipo);
		if (emolPag != null) {
			gHistorico.setValor(emolPag.getValor());
		} else {
			gHistorico.setValor(0);
		}

		gHistorico.setCodigoIva(emolumento.getCodigoIva());
		gHistorico.setPercentagemIva(emolumento.getPercentagemIva().toString());
		gHistorico.setQuantidade("1");
		gHistorico.setAnoLectivo(anoLectivo);
		gHistorico.setEmolumento(emolumento);
		gHistorico.setNumeroDeCandidato(guiaCandidatura.getCandidato().getNumeroCandidato());

		this.guiaCandidaturaHistoricoRepository.save(gHistorico);
	}

	/*
	 * 
	 * nessa sequencia, aplicamos a passagem de candidato para aluno, isso é feito
	 * em uma pesquisa de update.
	 */
	@Transactional
	@PutMapping(value = "/seriar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> seriar(@RequestBody CandidatoView candidatoSerirar) throws SQLException {

		/* CRIAMOS UMA INSTANCIA DE ALUNO */
		Aluno aluno = new Aluno();
		ResponseCliente rc = new ResponseCliente();
		/* CAPTURAMOS OS DADOS DO CANDIDATO PARA FORMAR UM ALUNO */

		Candidato c = repository.findOne(candidatoSerirar.getId());

		GuiaCandidatura guia = this.guiaCadidatura.findByCandidato(c);

		if (!guia.isLiquidada()) {
			rc.setCodigo(300);
			rc.setMensagem("A guia de candidatura não está liquidada.");
			return new ResponseEntity<ResponseCliente>(rc, HttpStatus.OK);
		}

		if (c.getCurso().getGrau() == Grau.LICENCIATURA) {

			if (c.getNotaExame() == null) {
				rc.setCodigo(300);
				rc.setMensagem("Verifique a nota de exame.");
				return new ResponseEntity<ResponseCliente>(rc, HttpStatus.OK);
			}

			if (c.getNotaExame() < 10) {
				rc.setCodigo(300);
				rc.setMensagem("Verifique a nota de exame.");
				return new ResponseEntity<ResponseCliente>(rc, HttpStatus.OK);
			}

		}

		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);

		Instituicao instituicao = this.instituicaoRepository.findOne(2);

		Pais nacionalidade = this.paisReposiotry.findOne(c.getNacionalidade().getId());
		Provincia provinciaResidencia = c.getProvinciaResidencia() != null
				? this.provinciaRepsitory.findOne(c.getProvinciaResidencia().getId())
				: null;
		Provincia provincia = null;
		if (c.getProvincia() != null)
			provincia = c.getProvincia().getId() != null ? this.provinciaRepsitory.findOne(c.getProvincia().getId())
					: null;

		Municipio municipio = null;
		if (c.getMunicipio() != null)
			municipio = c.getMunicipio().getId() != null ? this.municipioRepository.findOne(c.getMunicipio().getId())
					: null;

		Municipio municipioResidencia = null;
		if (c.getMunicipioResidencia() != null)
			municipioResidencia = c.getMunicipioResidencia() != null
					? this.municipioRepository.findOne(c.getMunicipioResidencia().getId())
					: null;

		// AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(c.getAnoLectivo());
		if (c.isSeriado()) {
			rc.setCodigo(ResponseCode.values()[3].getDescricao());
			rc.setResultado(c);
			rc.setMensagem("O candidato com o número " + c.getNumeroCandidato() + " já foi seriado!");
			return new ResponseEntity<ResponseCliente>(rc, HttpStatus.OK);
		} else {

			// SET IDENTITY_INSERT siga_intellectus.dbo.t_aluno ON
			// AQUI PRECISO CHAMAR A CONEXÃO JBDBC PARA CORRER ESSE QUERY

			Conexao conn = new Conexao();

			Connection ativo = conn.getConn();

			PreparedStatement pree = ativo.prepareStatement("SET IDENTITY_INSERT siga_intellectus.dbo.t_aluno ON");
			pree.execute();

			BeanUtils.copyProperties(c, aluno);

			aluno.setDataCadastro(new Date());
			aluno.setDataCandidatura(c.getDataCandidatura());
			aluno.setNacionalidade(nacionalidade);
			aluno.setProvinciaResidencia(provinciaResidencia);
			aluno.setProvincia(provincia);
			aluno.setMunicipio(municipio);
			aluno.setMunicipioResidencia(municipioResidencia);
			aluno.setCopiaCertificado(false);
			aluno.setCopiaDocumentoMilitar(false);
			aluno.setAtestadoMedico(false);
			aluno.setPrimeiraMatricula(true);
			aluno.setInscrito(false);

			// preparação para a geração do número de aluno
			String definitivo = "";
			Calendar hoje = Calendar.getInstance();
			Integer lectivo = hoje.get(Calendar.YEAR);
			String valor = lectivo.toString().substring(2);
			lectivo = Integer.parseInt(valor);
			// String lectivoString = lectivo.toString();

			// lectivoString.

			NumeroGerado numeroGerado = this.numeroGeradoRepository.findOne(2);
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
			Aluno alunoExistente = this.repositoryAluno.findByNumeroDeAluno(definitivo);
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

					alunoExistente = this.repositoryAluno.findByNumeroDeAluno(definitivo);
				} while (alunoExistente != null);
			}
			aluno.setNumeroDeAluno(definitivo);
			aluno.setId(Integer.parseInt(definitivo));

			// atualizar os dados do controlar de geração de números
			numeroGerado.setUltimoNumero(proximoNumeroInteiro);
			numeroGerado.setProximoNumero(proximoNumeroInteiro + 1);
			this.numeroGeradoRepository.save(numeroGerado);

			PreparedStatement preeo = ativo.prepareStatement("SET IDENTITY_INSERT siga_intellectus.dbo.t_aluno OFF");
			preeo.execute();

			/* SALVAMOS O ALUNO */
			// Validando campos
			aluno.setContencioso(false);
			aluno.setCopiaDocumentoIdentificacao(false);
			aluno.setCopiaDocumentoMilitar(false);
			aluno.setUltimaModificacao(new Date());
			aluno.setInactivo(false);
			aluno.setFimCurso(false);
			aluno.setPrimeiraMatricula(false);
			aluno.setCursoAcesso(false);
			aluno.setTrabalhador(false);
			aluno.setFotografias(false);
			aluno.setAtestadoMedico(false);
			aluno.setCopiaCertificado(false);
			aluno.setInscrito(false);

			Aluno a = repositoryAluno.save(aluno);
			this.alunoService.gerarHistorico(aluno);
			// setar o tipo de candidatura no aluno
			if (c.isCursoAcesso()) {
				// curso de acesso.
				a.setTipoCandidatura(TipoCandidatura.CANDIDATURA_POR_CURSO_ACESSO);
			} else {
				if (c.isTipoDeCandidatura()) {
					// equivalencia
					a.setTipoCandidatura(TipoCandidatura.CANDIDATURA_EQUIVALENCIA);
				} else {
					// normal
					a.setTipoCandidatura(TipoCandidatura.CANDIDATURA_NORMAL);
				}
			}

			// criar anexo do aluno
			AlunoAnexo alunoAnexo = new AlunoAnexo();

			alunoAnexo.setAluno(a);
			alunoAnexo.setNumeroDeAluno(a.getNumeroDeAluno());
			alunoAnexo.setFoto(null);
			// salvar os dados da foto
			this.alunoAnexo.save(alunoAnexo);
			a.setUltimaModificacao(new Date());
			this.repositoryAluno.save(a);
			this.alunoService.gerarHistorico(a);
			/* Acertar os dados no candidato após seriado. */
			c.setSeriado(true);
			c.setCodigoAluno(a.getId());
			repository.save(c);

			// BUSCA A CONTA DO CANDIDATO.
			ContaCorrenteCandidato contaCandidato = this.contaCandidatoRepository
					.findByAnoLectivoAndNumeroDeCandidato(anoLectivo.get(0), c.getNumeroCandidato().toString());

			ContaCorrenteAluno conta = new ContaCorrenteAluno();
			conta.setAluno(a);
			conta.setNumeroDeAluno(a.getNumeroDeAluno());
			conta.setDataMovimento(new Date());
			conta.setAnoLectivo(anoLectivo.get(0));
			conta.setInstituicao(instituicao);
			conta.setValor(contaCandidato != null ? contaCandidato.getValor() : 0.0);
			conta.setValorAnterior(0.0);
			this.contaCorrenteRepository.save(conta);
			/* Retorno de Aluno. */
			rc.setCodigo(ResponseCode.values()[0].getDescricao());
			rc.setResultado(a);
			rc.setMensagem("Candidato " + c.getNome() + ", Seriado com sucesso!");
			return new ResponseEntity<ResponseCliente>(rc, HttpStatus.OK);
		}

	}

	private String geraNumeroGuia(Integer lectivo, String definitivo, Long proximoNumeroInteiro) {
		if (proximoNumeroInteiro >= 1 && proximoNumeroInteiro <= 9)
			definitivo = lectivo + "00000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 10 && proximoNumeroInteiro <= 99)
			definitivo = lectivo + "0000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 100 && proximoNumeroInteiro <= 999)
			definitivo = lectivo + "000" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 1000 && proximoNumeroInteiro <= 9999)
			definitivo = lectivo + "00" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 10000 && proximoNumeroInteiro <= 99999)
			definitivo = lectivo + "0" + proximoNumeroInteiro;
		if (proximoNumeroInteiro >= 100000 && proximoNumeroInteiro <= 999999)
			definitivo = lectivo + proximoNumeroInteiro.toString();
		return definitivo;
	}

	@Transactional
	@RequestMapping(value = "/atualizar/{userCode}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody CandidatoRequest candidato,
			@PathVariable Integer userCode) {
		ResponseCliente c = new ResponseCliente();
		if (candidato != null) {
			Candidato cp = repository.findOne(candidato.getId());

			// Auditoria
			Usuario usuario = this.usuarioRepository.findByUserCode(userCode);
			if (cp != null) {
				CadidatoAudit candidatoAudit = new CadidatoAudit();

				// candidatoAudit.setNotaFinal(cp.getNotaFinal());
				candidatoAudit.setAnoLectivo(cp.getAnoLectivo());
				candidatoAudit.setArquivoIdentificacao(cp.getArquivoIdentificacao());
				candidatoAudit.setBairro(cp.getBairro());
				candidatoAudit.setTipoCandidatura(cp.getTipoCandidatura());
				candidatoAudit.setCandidatoLicenciado(cp.getCandidatoLicenciado());
				candidatoAudit.setGrau(cp.getGrau());
				candidatoAudit.setCurso(cp.getCurso());
				candidatoAudit.setExame(cp.getExame());
				candidatoAudit.setTurno(cp.getTurno());
				candidatoAudit.setNome(cp.getNome());
				candidatoAudit.setDocumentoIdentificacao(cp.getDocumentoIdentificacao());
				candidatoAudit.setNumeroDocumento(cp.getNumeroDocumento());
				candidatoAudit.setDataEmissaoDocumento(cp.getDataEmissaoDocumento());
				candidatoAudit.setComuna(cp.getComuna());
				candidatoAudit.setNacionalidade(cp.getNacionalidade());
				candidatoAudit.setProvincia(cp.getProvincia());
				candidatoAudit.setMunicipio(cp.getMunicipio());
				candidatoAudit.setMorada(cp.getMorada());
				candidatoAudit.setDataNascimento(cp.getDataNascimento());
				candidatoAudit.setEstadoCivil(cp.getEstadoCivil());
				candidatoAudit.setSexo(cp.getSexo());
				candidatoAudit.setCodigoAluno(cp.getId());
				candidatoAudit.setProfissao(cp.getProfissao());
				candidatoAudit.setNomeDoPai(cp.getNomeDoPai());
				candidatoAudit.setNomeDaMae(cp.getNomeDaMae());
				candidatoAudit.setNecessidadeEducacaoEspecial(cp.getNecessidadeEducacaoEspecial());
				candidatoAudit.setTipoEscolaEnsinoMedio(cp.getTipoEscolaEnsinoMedio());
				candidatoAudit.setEscolaEnsinoMedio(cp.getEscolaEnsinoMedio());
				candidatoAudit.setCursoEnsinoMedio(cp.getCursoEnsinoMedio());
				candidatoAudit.setTrabalhador(cp.getTrabalhador());
				candidatoAudit.setTelefone(cp.getTelefone());
				candidatoAudit.setTelefone1(cp.getTelefone1());
				candidatoAudit.setTelefone2(cp.getTelefone2());
				candidatoAudit.setEmail(cp.getEmail());
				candidatoAudit.setProvinciaResidencia(cp.getProvinciaResidencia());
				candidatoAudit.setMunicipioResidencia(cp.getMunicipioResidencia());
				candidatoAudit.setComunaResidencia(cp.getComunaResidencia());

				candidatoAudit.setFotografia(cp.getFotografia());
				candidatoAudit.setTipoDeCandidatura(cp.getTipoDeCandidatura());
				candidatoAudit.setCursoAcesso(cp.getCursoAcesso());
				candidatoAudit.setSeriado(cp.getSeriado());
				candidatoAudit.setInscricaoOnline(cp.getInscricaoOnline());

				candidatoAudit.setInstituicao(cp.getInstituicao());
				candidatoAudit.setUsuario(cp.getUsuario());
				candidatoAudit.setDataCandidatura(cp.getDataCandidatura());
				candidatoAudit.setNumeroCandidato(cp.getNumeroCandidato());
				candidatoAudit.setCodigoUsuarioAlterouNotaFinal(usuario.getId());
				// candidatoAudit.setDataAlterou(cp.getData_alterou());
				// candidatoAudit.setData_alterou_geral(cp.getData_alterou_geral());

				candidatoAudit = this.candidatoAuditRepository.save(candidatoAudit);
			}

			// Auditoria

			TipoCandidatura tipoCandidatura = cp.getTipoCandidatura();

			Curso curso = repositoryCurso.findById(candidato.getCurso());

			boolean guardian = cp.isSeriado() == null ? false : cp.isSeriado();
			/* Atualizando todos os campos do candidato editado. */
			BeanUtils.copyProperties(candidato, cp, "curso");

			// cp.setCursoAcesso(candidato.isCursoAcesso());

			cp.setSeriado(guardian);
			/* Mensagem de sucesso. */

			GuiaCandidatura guia = this.guiaCadidatura.findByCandidato(cp);

			GuiaCandidatura indicador = guia;

			double valorGuia = 0;

			if (guia.isLiquidada() || cp.getNotaExame() != null) {

				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setMensagem("A guia de candidatura já foi liquidada ou candidato já realizou o exame.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {

				if (tipoCandidatura != candidato.getTipoCandidatura() || indicador == null) {

					if (candidato.getTipoCandidatura() == TipoCandidatura.CANDIDATURA_EQUIVALENCIA) {
						cp.setTipoDeCandidatura(true);

						guia = this.guiaCadidatura.findByCandidato(cp);
						List<GuiaCandidaturaHistorico> historicoGuia = this.guiaCandidaturaHistoricoRepository
								.findByGuia(guia);

						for (GuiaCandidaturaHistorico o : historicoGuia) {
							this.guiaCandidaturaHistoricoRepository.delete(o);
						}

						// EQUIVALNCIA
						Emolumento emolumento = this.emolumentoRepositry.findByCodigo(12);
						EmolumentoHistorico emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(
								emolumento, cp.getCurso(), cp.getAnoLectivo());
						aplicarEmolumento(cp.getAnoLectivo(), emolumento, emolPag, guia,
								candidato.getTipoCandidatura().getDescricao());

						// NORMAL
						emolumento = this.emolumentoRepositry.findByCodigo(6);
						emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(emolumento,
								cp.getCurso(), cp.getAnoLectivo());
						aplicarEmolumento(cp.getAnoLectivo(), emolumento, emolPag, guia, "Candidatura Normal");

						historicoGuia = this.guiaCandidaturaHistoricoRepository.findByGuia(guia);
						valorGuia = 0;
						for (GuiaCandidaturaHistorico o : historicoGuia) {
							valorGuia += o.getValor();
						}

						cp.setNotaExame(10D);
						cp.setTipoCandidatura(candidato.getTipoCandidatura());
						guia.setValor(valorGuia);
						this.guiaCadidatura.save(guia);

					}

					if (candidato.getTipoCandidatura() == TipoCandidatura.CANDIDATURA_POR_CURSO_ACESSO) {
						cp.setCursoAcesso(true);

						guia = this.guiaCadidatura.findByCandidato(cp);
						List<GuiaCandidaturaHistorico> historicoGuia = this.guiaCandidaturaHistoricoRepository
								.findByGuia(guia);

						for (GuiaCandidaturaHistorico o : historicoGuia) {
							this.guiaCandidaturaHistoricoRepository.delete(o);
						}

						// NORMAL
						Emolumento emolumento = this.emolumentoRepositry.findByCodigo(95);
						EmolumentoHistorico emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(
								emolumento, cp.getCurso(), cp.getAnoLectivo());
						aplicarEmolumento(cp.getAnoLectivo(), emolumento, emolPag, guia,
								candidato.getTipoCandidatura().getDescricao());

						historicoGuia = this.guiaCandidaturaHistoricoRepository.findByGuia(guia);
						// double valorGuia = 0;
						for (GuiaCandidaturaHistorico o : historicoGuia) {
							valorGuia += o.getValor();
						}

						cp.setTipoCandidatura(candidato.getTipoCandidatura());
						guia.setValor(valorGuia);
						this.guiaCadidatura.save(guia);
					}

					if (candidato.getTipoCandidatura() == TipoCandidatura.CANDIDATURA_NORMAL) {
						cp.setTipoDeCandidatura(false);

						guia = this.guiaCadidatura.findByCandidato(cp);
						List<GuiaCandidaturaHistorico> historicoGuia = this.guiaCandidaturaHistoricoRepository
								.findByGuia(guia);

						for (GuiaCandidaturaHistorico o : historicoGuia) {
							this.guiaCandidaturaHistoricoRepository.delete(o);
						}

						// NORMAL
						Emolumento emolumento = this.emolumentoRepositry.findByCodigo(2003);
						EmolumentoHistorico emolPag = this.emolumentoHistoricoRepository.findByEmolumentoAndCursoAndAnoLectivo(
								emolumento, cp.getCurso(), cp.getAnoLectivo());
						aplicarEmolumento(cp.getAnoLectivo(), emolumento, emolPag, guia,
								candidato.getTipoCandidatura().getDescricao());

						historicoGuia = this.guiaCandidaturaHistoricoRepository.findByGuia(guia);
						// double valorGuia = 0;
						/*
						 * for (GuiaCandidaturaHistorico o : historicoGuia) {
						 * 
						 * if (candidato.isCandidatoLicenciado()) { valorGuia += o.getValor(); } else {
						 * valorGuia += 0; } }
						 */

						valorGuia = emolPag.getValor();

						cp.setTipoCandidatura(candidato.getTipoCandidatura());
						guia.setValor(valorGuia);
						this.guiaCadidatura.save(guia);
					}
				}

				this.repository.save(cp);
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(candidato);
				c.setMensagem("ESTAMOS NA MIRA DA VERDADE");
			}

			if (cp.getCurso().getId() != candidato.getCurso()) {
				if (cp.getNotaExame() != null || guia.isLiquidada()) {
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					c.setMensagem("Candidato já fez o exame ou a guia já foi liquidada");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				} else {
					cp.setCurso(curso);
					this.repository.save(cp);
				}
			}

			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(candidato);
			c.setMensagem("Candidato " + candidato.getNome() + " atualizado com sucesso!");
			repository.save(cp);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setResultado(candidato);
			c.setMensagem("O candidato enviado não pode ser nullo!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/buscarEmolumnetoCandidato/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarEmolumnetoCandidato(@PathVariable Integer numero) {
		ResponseCliente c = new ResponseCliente();

		Candidato candidato = this.repository.findOne(numero);

		GuiaCandidatura can = this.guiaCadidatura.findByCandidato(candidato);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(can);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@GetMapping("/lista/candidatos")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioListaCandidatos(@RequestParam String data1, @RequestParam String data2,
			@RequestParam String tipo, @RequestParam String id_ano) throws Exception {
		SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");

		Date dataF1 = formatar.parse(data1);
		Date dataF2 = formatar.parse(data2);
		byte[] relatrio = servicoListaCandidatos(dataF1, dataF2, tipo, id_ano);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoListaCandidatos(Date data1, Date dat2, String tipo, String id_ano) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("data1", data1);
		paramets.put("data2", dat2);
		paramets.put("tipo", Integer.parseInt(tipo));
		paramets.put("codigo_ano", Integer.parseInt(id_ano));
		InputStream inputStream = null;

		inputStream = this.getClass().getResourceAsStream("/relatorio/R_Listagem_Candidatos.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	// candidato/pauta/exame
	@GetMapping("/pauta/exame")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> pautaExame(@RequestParam Integer curso, @RequestParam Integer ano) throws Exception {

		// SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
		byte[] relatrio = servicoPautaExame(curso, ano);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoPautaExame(Integer curso, Integer ano) throws JRException {
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("id_curso", curso);
		paramets.put("id_ano", ano);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Pauta_Exame_Electronico.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@RequestMapping(value = "/lista/tipo/{begin}/{end}/{anoLectivo}/{curso}/{titpoCandidatura}/{turno}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> candidatosPorTipo(@PathVariable String begin, @PathVariable String end,
			@PathVariable Integer anoLectivo, @PathVariable Integer curso, @PathVariable String titpoCandidatura,
			@PathVariable String turno) throws Exception {
		byte[] relatrio = servicoCandidatosPorTipo(begin, end, anoLectivo, curso, titpoCandidatura, turno);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoCandidatosPorTipo(String begin, String end, Integer anoLectivo, Integer curso,
			String titpoCandidatura, String turno) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("begin", begin);
		paramets.put("end", end);
		paramets.put("anoLectivo", anoLectivo);
		paramets.put("curso", curso);
		paramets.put("tipoCandidato", titpoCandidatura);
		paramets.put("turno", turno);

		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorio/R_Lista_Presenca_Candidato_PorTipo.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}