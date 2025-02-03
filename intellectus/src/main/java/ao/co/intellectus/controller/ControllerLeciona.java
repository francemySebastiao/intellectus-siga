package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.CursoDocenteResumo;
import ao.co.intellectus.DTO.DisciplinaDescricao;
import ao.co.intellectus.DTO.DisciplinaResumoCliente;
import ao.co.intellectus.DTO.EntidadePadrao;
import ao.co.intellectus.DTO.HorarioPorTipo;
import ao.co.intellectus.DTO.HorarioTurma;
import ao.co.intellectus.DTO.LecionaCliente;
import ao.co.intellectus.DTO.LecionaClienteCompleto;
import ao.co.intellectus.DTO.TurmaCliente;
import ao.co.intellectus.DTO.TurmaDescricao;
import ao.co.intellectus.DTO.TurmaDisciplinaDescricao;
import ao.co.intellectus.DTO.Verificar;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.CursoDocente;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.DisciplinaDocente;
import ao.co.intellectus.model.Docente;
import ao.co.intellectus.model.HorarioCalendario;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Intervalo;
import ao.co.intellectus.model.Leciona;
import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.ProgDisciplinaTurma;
import ao.co.intellectus.model.Sala;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.TurmaDocente;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CursoDocenteRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.DisciplinaDocenteRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.DocenteRepository;
import ao.co.intellectus.repository.HorarioCalendarioRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.IntervaloRepository;
import ao.co.intellectus.repository.LecionaRepository;
import ao.co.intellectus.repository.PermissaoCursoRepository;
import ao.co.intellectus.repository.ProgDisciplinaTurmaRepository;
import ao.co.intellectus.repository.SalaRepository;
import ao.co.intellectus.repository.TurmaDocenteRepository;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.repository.cursoRepo;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.cafold.ConexaoService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/leciona")
public class ControllerLeciona {
	@Autowired
	private LecionaRepository lecionaRepository;
	@Autowired
	private AnoLectivoRepository repositoryAnoLectivo;
	@Autowired
	private DisciplinaRepository repositoryDisciplina;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private CursoRepository repositoryCurso;
	@Autowired
	private cursoRepo repoCurso;
	@Autowired
	private DocenteRepository docenteRepository;
	@Autowired
	private SalaRepository repositorySala;
	@Autowired
	private IntervaloRepository intervaloResository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private HorarioCalendarioRepository horarioCalendarioRepository;
	// @Autowired
	// private HorarioProfessorRepository horarioProfessorRepository;
	@Autowired
	private DisciplinaDocenteRepository disciplinaDocenteRepository;
	@Autowired
	private TurmaDocenteRepository turmaDocenteRepository;
	@Autowired
	private CursoDocenteRepository cursoDocenteRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PermissaoCursoRepository permissaoCursoRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private ProgDisciplinaTurmaRepository progDisciplinaTurmaRepository;
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	@Autowired
	private ConexaoService conexaoService;

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody LecionaCliente lecionaCliente) {
		ResponseCliente c = new ResponseCliente();
		Leciona leciona = new Leciona();
		// PESQUISA TODOS OS PARAMETROS
		Docente docente = this.docenteRepository.findOne(lecionaCliente.getDocente());
		AnoLectivo anoLectivo = this.repositoryAnoLectivo.findOne(lecionaCliente.getAnoLectivo());
		// pesquisa os intervalos
		Intervalo intervalo = this.intervaloResository.findOne(lecionaCliente.getIntervalo());
		Disciplina disciplina = this.repositoryDisciplina.findOne(lecionaCliente.getDisciplina());
		Curso curso = this.repositoryCurso.findByIdAndStatus(lecionaCliente.getCurso(), true);
		Turma turma = this.turmaRepository.findOne(lecionaCliente.getTurma());
		Sala sala = this.repositorySala.findOne(lecionaCliente.getSala());
		Instituicao instituicao = this.instituicaoRepository.findOne(2);
		// REGISTRA HORARIO LANÇADO.
		BeanUtils.copyProperties(lecionaCliente, leciona, "disciplina", "curso", "turma", "docente", "anoLectivo",
				"Sala");

		List<HorarioCalendario> calendario = this.horarioCalendarioRepository
				.findByAnoLectivoAndDiaSemanaNomeAndSemestre(anoLectivo, lecionaCliente.getDiaSemana(),
						lecionaCliente.getSemestre());

		// this.lecionaRepository.findByAnoLectivoAnoLectivoAndDocenteIdAndDisciplinaIdAndCursoId(anoLectivo,
		// docente, disciplina, curso)

		List<Leciona> turmaOcupada = this.lecionaRepository
				.findByAnoLectivoAndDocenteAndDisciplinaAndTurmaAndDiaSemanaAndIntervalo(anoLectivo, docente,
						disciplina, turma, lecionaCliente.getDiaSemana(), intervalo);
		if (!turmaOcupada.isEmpty()) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("A turma selecionada já se encontra ocupada.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		for (HorarioCalendario hc : calendario) {
			BeanUtils.copyProperties(lecionaCliente, leciona, "disciplina", "curso", "turma", "docente", "anoLectivo",
					"Sala");
			leciona.setDiaAula(hc.getData());
			leciona.setDocente(docente);
			leciona.setIntervalo(intervalo);
			leciona.setAnoLectivo(anoLectivo);
			leciona.setDisciplina(disciplina);
			leciona.setCurso(curso);
			leciona.setTurma(turma);
			leciona.setSala(sala);
			leciona.setInstituicao(instituicao);
			leciona.setDiaSemanaInteiro(hc.getDiaSemana());
			leciona.setDescontoMinutoFinal(60);
			leciona.setDescontoMinutos(60);
			// leciona.setDiaSemana(hc.getDiaSemanaNome());
			leciona.setDataCadastro(new Date());

			this.lecionaRepository.save(leciona);
			leciona = new Leciona();
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(lecionaCliente);
		c.setMensagem("Hórario Lançado com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/verificar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> confirmar(@RequestBody LecionaCliente lecionaCliente) {
		ResponseCliente c = new ResponseCliente();

		Docente docente = this.docenteRepository.findOne(lecionaCliente.getDocente());
		Sala sala = this.repositorySala.findOne(lecionaCliente.getSala());
		AnoLectivo anoLectivo = this.repositoryAnoLectivo.findOne(lecionaCliente.getAnoLectivo());
		Intervalo intervalo = this.intervaloResository.findOne(lecionaCliente.getIntervalo());
		Turma turma = this.turmaRepository.findOne(lecionaCliente.getTurma());
		Curso curso = this.repoCurso.findOne(lecionaCliente.getCurso());

		List<Leciona> buscaSala = this.lecionaRepository.findBySalaAndDiaSemanaAndSemestreAndIntervaloAndAnoLectivo(sala,
				lecionaCliente.getDiaSemana(), lecionaCliente.getSemestre(), intervalo, anoLectivo);
		List<Leciona> buscaDocente = this.lecionaRepository.findByDocenteAndDiaSemanaAndSemestreAndIntervaloAndAnoLectivo(docente,
				lecionaCliente.getDiaSemana(), lecionaCliente.getSemestre(), intervalo, anoLectivo);
		List<Leciona> buscaTurma = this.lecionaRepository.findByTurmaAndCursoAndAnoCurricularAndDiaSemanaAndSemestreAndIntervaloAndAnoLectivo(
				turma, curso, lecionaCliente.getAnoCurricular(), lecionaCliente.getDiaSemana(), lecionaCliente.getSemestre(), intervalo, anoLectivo);

		Verificar verifique = new Verificar();

		if (buscaSala.isEmpty()) {
			verifique.setSala(false);
		} else {
			verifique.setSala(true);
		}

		if (buscaDocente.isEmpty()) {
			verifique.setDocente(false);
		} else {
			verifique.setDocente(true);
		}

		if (buscaTurma.isEmpty()) {
			verifique.setTurma(false);
		} else {

			verifique.setTurma(true);
		}

		/*
		 * if (verifique.isDocente() && verifique.isSala()) {
		 * c.setMensagem("Horário ocupado!"); } else if (verifique.isDocente() &&
		 * verifique.isSala() == false) { c.setMensagem("Professor ocupado!"); } else if
		 * (verifique.isDocente() == false && verifique.isSala()) {
		 * c.setMensagem("Sala ocupada!"); } else { c.setMensagem("Horário livre."); }
		 */

		if (verifique.isDocente() && verifique.isSala() && verifique.isTurma()) {
			c.setMensagem("Este horário já foi preenchido!");
		} else if (verifique.isDocente()) {
			c.setMensagem("Este professor já está ocupado neste horário!");
		} else if (verifique.isSala()) {
			c.setMensagem("Esta sala já se encontra ocupada!");
		} else if (verifique.isTurma()) {
			c.setMensagem("Essa turma já tem aula neste horário!");
		} else {
			c.setMensagem("Horário livre para lançamento.");
		}

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(verifique);

		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos() {
		ResponseCliente c = new ResponseCliente();
		Iterable<Leciona> todos = this.lecionaRepository.findByOrderByDiaAulaDesc();

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarHorario", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarHorario(@RequestBody LecionaCliente leciona) {
		ResponseCliente c = new ResponseCliente();

		AnoLectivo anoLectivo = this.repositoryAnoLectivo.findOne(leciona.getAnoLectivo());
		if (anoLectivo == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Por favor preencha o campo Ano Lectivo como um Inteiro");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		Turma turma = this.turmaRepository.findOne(leciona.getTurma());
		if (turma == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Por favor preencha o campo Turma como um Inteiro");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		Curso curso = this.repositoryCurso.findByIdAndStatus(leciona.getCurso(), true);

		if (curso == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Por favor preencha o campo Curso como um Inteiro");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		// Iterable<Leciona> rHorario =
		// this.lecionaRepository.findByAnoLectivoAndTurmaAndAnoCurricularAndCurso(anoLectivo,turma,
		// leciona.getAnoCurricular(), curso);
		Disciplina disciplina = this.disciplinaRepository.findOne(leciona.getDisciplina());
		if (disciplina == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setMensagem("Por favor preencha o campo Disciplina como um Inteiro");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<Leciona> rHorario = this.lecionaRepository.findByAnoLectivoAndTurmaAndDisciplina(anoLectivo, turma,
				disciplina);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(rHorario);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarTodosHorario", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarTodos() {
		ResponseCliente c = new ResponseCliente();
		Iterable<Leciona> todos = this.lecionaRepository.findAll();
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(todos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/verificar/editar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> confirmarEditar(@RequestBody LecionaCliente lecionaCliente) {
		ResponseCliente c = new ResponseCliente();

		Docente docente = this.docenteRepository.findOne(lecionaCliente.getDocente());
		Sala sala = this.repositorySala.findOne(lecionaCliente.getSala());
		AnoLectivo anoLectivo = this.repositoryAnoLectivo.findOne(lecionaCliente.getAnoLectivo());
		Intervalo intervalo = this.intervaloResository.findOne(lecionaCliente.getIntervalo());

		List<Leciona> buscaSala = this.lecionaRepository.findBySalaAndDiaSemanaAndSemestreAndIntervaloAndAnoLectivo(sala,
				lecionaCliente.getDiaSemana(), lecionaCliente.getSemestre(), intervalo, anoLectivo);
		List<Leciona> buscaDocente = this.lecionaRepository.findByDocenteAndDiaSemanaAndSemestreAndIntervaloAndAnoLectivo(docente,
				lecionaCliente.getDiaSemana(), lecionaCliente.getSemestre(), intervalo, anoLectivo);
		

		Verificar vefique = new Verificar();

		if (!buscaSala.isEmpty()) {
			vefique.setSala(true);
		} else {
			vefique.setSala(false);
		}

		if (!buscaDocente.isEmpty()) {
			vefique.setDocente(true);
		} else {
			vefique.setDocente(false);
		}

//		if (vefique.isDocente() && vefique.isSala()) {
//			c.setMensagem("Horário ocupado, deseja continuar ?");
//		} else if (vefique.isDocente() && vefique.isSala() == false) {
//			c.setMensagem("Professor Ocupado, deseja continuar ?");
//		} else if (vefique.isDocente() == false && vefique.isSala()) {
//			c.setMensagem("Sala Ocupado, deseja continuar ?");
//		} else {
//			c.setMensagem("Horário livre.");
//		}

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(vefique);

		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody LecionaCliente lecionaCliente) {
		ResponseCliente c = new ResponseCliente();

		// PESQUISA TODOS OS PARAMETROS
		Docente docenteNovo = this.docenteRepository.findOne(lecionaCliente.getDocente());

		Sala sala = this.repositorySala.findOne(lecionaCliente.getSala());

		Leciona leciona = this.lecionaRepository.findById(lecionaCliente.getId());

		BeanUtils.copyProperties(lecionaCliente, leciona, "disciplina", "curso", "turma", "docente", "anoLectivo",
				"Sala");

		leciona.setDocente(docenteNovo);
		leciona.setSala(sala);
		leciona.setDataCadastro(new Date());

		this.lecionaRepository.save(leciona);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(lecionaCliente);
		c.setMensagem("Hórario Atualizado com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/removerHorario", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> remover(@RequestParam Integer lecionaDelete) {
		ResponseCliente c = new ResponseCliente();

		this.lecionaRepository.delete(lecionaDelete);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(lecionaDelete);
		c.setMensagem("Hórario removido com sucesso!");
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorDocente", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> buscarPorDocente(@RequestBody EntidadePadrao entidade) {
		ResponseCliente c = new ResponseCliente();
		Docente docente = entidade.getCodigo() != null ? this.docenteRepository.findOne(entidade.getCodigo()) : null;
		List<Leciona> horarios = docente != null ? this.lecionaRepository.findByDocente(docente) : null;

		if (horarios.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registo horário encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(horarios);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/horarioPorTurmaAnoAndLectivo", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> buscarTurmaPorAno(@RequestBody HorarioTurma horarioTurma) {
		ResponseCliente c = new ResponseCliente();
		AnoLectivo anoLectivo = this.repositoryAnoLectivo.findOne(horarioTurma.getAnoLectivo());
		Turma turma = this.turmaRepository.findOne(horarioTurma.getTurma());

		List<Leciona> retorno = this.lecionaRepository.findByAnoLectivoAndTurmaAndAnoCurricular(anoLectivo, turma,
				horarioTurma.getAnoCurricular());

		List<LecionaClienteCompleto> turmas = new ArrayList<LecionaClienteCompleto>();
		LecionaClienteCompleto lTurma;

		for (Leciona leciona : retorno) {
			lTurma = new LecionaClienteCompleto();
			// REGISTRA HORARIO LANÇADO.
			BeanUtils.copyProperties(leciona, lTurma);
			// estou aqui a ver a turma....
			// lTurma.setTurma(turma.getTurma());
			// lTurma.setCurso(retorno.get(0).getCurso().getDescricao());
			// lTurma.setAnoLectivo(anoLectivo.getAnoLectivo());
			// lTurma.setDisciplina(leciona.getDisciplina().getDescricao());
			// lTurma.setSala(leciona.getSala().getDesignacao());
			// lTurma.setDataCadastro(leciona.getDataCadastro());
			// lTurma.setDocente(leciona.getDocente().getNome());
			// lTurma.setDiaSemana(leciona.);
			/*
			 * leciona.setDisciplina(disciplina); leciona.setSala(sala);
			 */
			turmas.add(lTurma);
		}
		if (retorno.isEmpty()) {
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(retorno);
			c.setMensagem("Nenhum registro econtrado.");
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(turmas);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// VERIFICAR COM O ANDERSON PARA MANDAR POR PADRÃO O ANO CORRENTE,
	// POSTERIORMENTE ALTERNAR COM BASE A OUTROS ANOS...
	@RequestMapping(value = "/buscarTurmaAndDisciplia/{codigoDocente}/{ano}", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> buscarTurmaAndDisciplina(@PathVariable Integer codigoDocente,
			@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();
		// BICO
		// BUSCA O USUARIO DO FUNCIONÁRIO LOGADO

		// Docente findByUsuarioDocenteUserCode =
		// this.docenteRepository.findByUsuarioDocenteUserCode(3);

		// System.err.println("Codigo do usuário: "+findByUsuarioDocenteUserCode);

		Usuario usuario = this.usuarioRepository.findByUserCode(codigoDocente);

		if (usuario == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum usuario encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		// System.out.println("Nome do usuário: "+usuario.getName());

		// System.out.println("User do usuário: "+usuario.getUserCode());
		// BUSCA O DOCENTE ASSOCIADO AO USUARIO PEGO NA LINHA ANTERIOR
		// System.err.println("Usuário"+usuario);
		Docente docente = this.docenteRepository.findByUsuarioDocente(usuario);

		if (docente == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Este Funcionário não é um docente.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		// BUSCA TODOS OS CURSOS QUE O DOCENTE ESTÁ RELACIONADO NAQUELE ANO...
		List<CursoDocente> cursoDocente = this.cursoDocenteRepository.findByDocenteAndAnoLectivo(docente.getId(), ano);

		// BUSCA DISCIPLINA DO DOCENTE
		// List<DisciplinaDocente> dicdoc =
		// this.disciplinaDocenteRepository.findByDocente(id);
		List<DisciplinaDocente> dicdoc = this.disciplinaDocenteRepository.findByDocenteAndAnoLectivo(docente.getId(),
				ano);

		// BUSCA DADOS GERAIS DO DOCENTE
		// List<HorarioProfessor> docente =
		// this.horarioProfessorRepository.findByDocente(id);
		List<TurmaDocente> turmaDocente = this.turmaDocenteRepository.findByAnoLectivoAndDocenteOrderByTurmaId(ano,
				docente.getId());

		if (turmaDocente.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Não existe nenhum horário para listar");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		DisciplinaDescricao dc;
		TurmaDescricao tc;
		List<TurmaDescricao> tDescricao = new ArrayList<TurmaDescricao>();
		List<DisciplinaDescricao> dDescricao = new ArrayList<DisciplinaDescricao>();
		TurmaDisciplinaDescricao tds = new TurmaDisciplinaDescricao();

		// RODA AS AS DISCIPLINAS BEM COMO OS CURSOS QUE O DOCENTE TEVE RELACIONAMENTO
		for (DisciplinaDocente o : dicdoc) {
			dc = new DisciplinaDescricao();

			dc.setDescricao(o.getDisciplina());
			dc.setId(o.getDisciplinaId());
			dc.setDescricaoCurso(o.getCurso());
			dc.setCodigoCurso(o.getCursoId());
			dc.setAnoLectivo(o.getAnoLectivo());
			dc.setCodigoTurma(o.getCodigoTurma());
			dc.setDescricaoTurma(o.getTurma());

			dDescricao.add(dc);
		}

		// RODA AS TURMAS QUE O DOCENTE DEVE RELACIONAMENTO
		for (TurmaDocente o : turmaDocente) {
			tc = new TurmaDescricao();
			tc.setDescricao(o.getTurma());
			tc.setId(o.getTurmaId());
			tc.setAnoLectivo(o.getAnoLectivo());
			tDescricao.add(tc);
		}

		// SETA OS VALORES DAS TURMAS BEM COMO DAS DSICIPLINAS NO OBJECTO DE RESPOSTA...
		tds.setDisciplina(dDescricao);
		tds.setTurma(tDescricao);
		tds.setCursos(cursoDocente);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(tds);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarCursoAvaliacao/{codigoDocente}", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> buscarCursoAvaliacao(@PathVariable Integer codigoDocente) {
		ResponseCliente c = new ResponseCliente();
		// BICO
		// BUSCA O USUARIO DO FUNCIONÁRIO LOGADO
		Usuario usuario = this.usuarioRepository.findByUserCode(codigoDocente);

		if (usuario == null) {
			c.setMensagem("Verifique o codigo de usúario.");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		// BUSCA O DOCENTE ASSOCIADO AO USUARIO PEGO NA LINHA ANTERIOR
		CursoDocenteResumo cursoDocente;
		List<CursoDocenteResumo> cursosDocente = new ArrayList<CursoDocenteResumo>();
		List<PermissaoCurso> cursosPermissao = this.permissaoCursoRepository.findByUsuarioAndPermissao(usuario,
				Permissao.GRAVAR);
		if (cursosPermissao.isEmpty()) {
			c.setMensagem("Nenhum curso relacionado.");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		for (PermissaoCurso pc : cursosPermissao) {
			cursoDocente = new CursoDocenteResumo();
			cursoDocente.setCurso(pc.getCurso().getPlano());
			cursoDocente.setId(pc.getCurso().getId());
			cursosDocente.add(cursoDocente);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(cursosDocente);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarDisciplinasPorCurso/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCurso(@PathVariable Integer id) {
		ResponseCliente c = new ResponseCliente();
		Curso curso = this.repositoryCurso.findById(id);
		List<Disciplina> disciplinas = this.disciplinaRepository.findByCursoAndStatus(curso, true);
		List<DisciplinaResumoCliente> dcs = new ArrayList<DisciplinaResumoCliente>();
		DisciplinaResumoCliente dc;
		for (Disciplina o : disciplinas) {
			dc = new DisciplinaResumoCliente();
			dc.setAprovacaoDisciplina(o.isAprovacaoDisciplina());
			dc.setTipo(o.getTipo().getDescricao());
			dc.setDescricao(o.getDescricao());
			dc.setId(o.getId());

			dcs.add(dc);
		}

		if (disciplinas.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhuma disciplina encontrado nesse curso");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		c.setResultado(dcs);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// findByAnoLectivoAndDisciplinaId
	@RequestMapping(value = "/buscarTurmaAndDisciplinaResumo/{disciplina}/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarTurmaAndDisciplinaResumo(@PathVariable Integer anoLectivo,
			@PathVariable Integer disciplina) {
		ResponseCliente c = new ResponseCliente();
		TurmaCliente turmaCliente;
		List<TurmaCliente> turmasCliente = new ArrayList<TurmaCliente>();

		List<ProgDisciplinaTurma> turmas = this.progDisciplinaTurmaRepository
				.findByAnoLectivoAndDisciplinaId(anoLectivo, disciplina);

		for (ProgDisciplinaTurma o : turmas) {
			turmaCliente = new TurmaCliente();
			turmaCliente.setIdTurma(o.getTurmaId());
			turmaCliente.setTurma(o.getTurma());
			turmasCliente.add(turmaCliente);
		}
		c.setResultado(turmasCliente);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// VERIFICAR COM O ANDERSON PARA MANDAR POR PADRÃO O ANO CORRENTE,
	// POSTERIORMENTE ALTERNAR COM BASE A OUTROS ANOS...
	@RequestMapping(value = "/buscarDadosEspeciais/{codigoDocente}/{ano}", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> buscarDadosEspeciais(@PathVariable Integer codigoDocente,
			@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();
		// BICO
		// BUSCA O USUARIO DO FUNCIONÁRIO LOGADO
		// Usuario usuario = this.usuarioRepository.findByUserCode(codigoDocente);

		Docente usuario = this.docenteRepository.findOne(codigoDocente);

		if (usuario.getUsuarioDocente() == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Relacione o docente com seu usuario.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		// BUSCA O DOCENTE ASSOCIADO AO USUARIO PEGO NA LINHA ANTERIOR
		Docente docente = this.docenteRepository.findByUsuarioDocente(usuario.getUsuarioDocente());

		if (docente == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Este Funcionário não é um docente.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		// BUSCA TODOS OS CURSOS QUE O DOCENTE ESTÁ RELACIONADO NAQUELE ANO...
		List<CursoDocente> cursoDocente = this.cursoDocenteRepository.findByDocenteAndAnoLectivo(docente.getId(), ano);

		// BUSCA DISCIPLINA DO DOCENTE
		// List<DisciplinaDocente> dicdoc =
		// this.disciplinaDocenteRepository.findByDocente(id);
		List<DisciplinaDocente> dicdoc = this.disciplinaDocenteRepository.findByDocenteAndAnoLectivo(docente.getId(),
				ano);

		// BUSCA DADOS GERAIS DO DOCENTE
		// List<HorarioProfessor> docente =
		// this.horarioProfessorRepository.findByDocente(id);
		List<TurmaDocente> turmaDocente = this.turmaDocenteRepository.findByAnoLectivoAndDocenteOrderByTurmaId(ano,
				docente.getId());

		if (turmaDocente.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Não existe nenhum horário para listar");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		DisciplinaDescricao dc;
		TurmaDescricao tc;
		List<TurmaDescricao> tDescricao = new ArrayList<TurmaDescricao>();
		List<DisciplinaDescricao> dDescricao = new ArrayList<DisciplinaDescricao>();
		TurmaDisciplinaDescricao tds = new TurmaDisciplinaDescricao();

		// RODA AS AS DISCIPLINAS BEM COMO OS CURSOS QUE O DOCENTE TEVE RELACIONAMENTO
		for (DisciplinaDocente o : dicdoc) {
			dc = new DisciplinaDescricao();

			dc.setDescricao(o.getDisciplina());
			dc.setId(o.getDisciplinaId());
			dc.setDescricaoCurso(o.getCurso());
			dc.setCodigoCurso(o.getCursoId());
			dc.setAnoLectivo(o.getAnoLectivo());
			dc.setCodigoTurma(o.getCodigoTurma());
			dc.setDescricaoTurma(o.getTurma());

			dDescricao.add(dc);
		}

		// RODA AS TURMAS QUE O DOCENTE DEVE RELACIONAMENTO
		for (TurmaDocente o : turmaDocente) {
			tc = new TurmaDescricao();
			tc.setDescricao(o.getTurma());
			tc.setId(o.getTurmaId());
			tc.setAnoLectivo(o.getAnoLectivo());
			tDescricao.add(tc);
		}

		// SETA OS VALORES DAS TURMAS BEM COMO DAS DSICIPLINAS NO OBJECTO DE RESPOSTA...
		tds.setDisciplina(dDescricao);
		tds.setTurma(tDescricao);
		tds.setCursos(cursoDocente);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(tds);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarHorarioPorTipo", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> buscarHorarioPorTipo(@RequestBody HorarioPorTipo tHorario) {
		ResponseCliente c = new ResponseCliente();

		Curso curso = tHorario.getCurso() != null ? this.repositoryCurso.findByIdAndStatus(tHorario.getCurso(), true)
				: null;
		AnoLectivo anoLectivo = tHorario.getAnoLectivo() != null
				? this.repositoryAnoLectivo.findOne(tHorario.getAnoLectivo())
				: null;

		List<Leciona> leciona = null;
		Docente docente;
		Turma turma;
		Sala sala;

		if (tHorario.getTipoHorario().getDescricao() == "Docente") {
			docente = tHorario.getDocente() != null ? this.docenteRepository.findOne(tHorario.getDocente()) : null;
			leciona = this.lecionaRepository.findByAnoLectivoAndAnoCurricularAndCursoAndDocenteAndSemestre(anoLectivo,
					tHorario.getAnoCurricular(), curso, docente, tHorario.getSemestre());
			c.setMensagem("Horário por Docente");
		}

		if (tHorario.getTipoHorario().getDescricao() == "Turma") {
			turma = this.turmaRepository.findOne(tHorario.getTurma());
			leciona = this.lecionaRepository.findByAnoLectivoAndAnoCurricularAndCursoAndSemestreAndTurma(anoLectivo,
					tHorario.getAnoCurricular(), curso, tHorario.getSemestre(), turma);
			c.setMensagem("Horário por Turma");
		}

		if (tHorario.getTipoHorario().getDescricao() == "Sala") {
			sala = this.repositorySala.findOne(tHorario.getSala());
			leciona = this.lecionaRepository.findByAnoLectivoAndAnoCurricularAndCursoAndSemestreAndSala(anoLectivo,
					tHorario.getAnoCurricular(), curso, tHorario.getSemestre(), sala);
			c.setMensagem("Horário por Sala");
		}
		c.setResultado(leciona);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}

	@GetMapping("/pauta/global")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> pautaGlobal(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam String ano_curricular, @RequestParam Integer condicao)
			throws Exception {
		byte[] relatrio = servicoPautaGlobal(codigo_ano_lectivo, codigo_disciplina, codigo_curso, codigo_turma,
				ano_curricular, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoPautaGlobal(Integer codigo_ano_lectivo, Integer codigo_disciplina, Integer codigo_curso,
			Integer codigo_turma, String ano_curricular, Integer condicao) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano_lectivo", codigo_ano_lectivo);
		paramets.put("codigo_disciplina", codigo_disciplina);
		paramets.put("codigo_curso", codigo_curso);
		paramets.put("codigo_turma", codigo_turma);
		paramets.put("ano_curricular", ano_curricular);
		paramets.put("condicao", condicao);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Pauta_Global.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets,
				conexaoService.getConexaoActual());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/pauta/avaliacoes")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> pautaAvaliacoes(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam String ano_curricular) throws Exception {
		byte[] relatrio = servicoPautaAvaliacoes(codigo_ano_lectivo, codigo_disciplina, codigo_curso, codigo_turma,
				ano_curricular);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoPautaAvaliacoes(Integer codigo_ano_lectivo, Integer codigo_disciplina, Integer codigo_curso,
			Integer codigo_turma, String ano_curricular) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano_lectivo", codigo_ano_lectivo);
		paramets.put("codigo_disciplina", codigo_disciplina);
		paramets.put("codigo_curso", codigo_curso);
		paramets.put("codigo_turma", codigo_turma);
		paramets.put("ano_curricular", ano_curricular);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Pauta_Avaliacao_Continua.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/pauta/sem/classicacao")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> pautaSemClassificacao(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam String ano_curricular, @RequestParam Integer condicao)
			throws Exception {
		byte[] relatrio = servicoSemClassificacao(codigo_ano_lectivo, codigo_disciplina, codigo_curso, codigo_turma,
				ano_curricular, condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoSemClassificacao(Integer codigo_ano_lectivo, Integer codigo_disciplina, Integer codigo_curso,
			Integer codigo_turma, String ano_curricular, Integer condicao) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano_lectivo", codigo_ano_lectivo);
		paramets.put("codigo_disciplina", codigo_disciplina);
		paramets.put("codigo_curso", codigo_curso);
		paramets.put("codigo_turma", codigo_turma);
		paramets.put("ano_curricular", ano_curricular);
		paramets.put("condicao", condicao);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Pauta_Vazia.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/pauta/recurso")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> pautaRecurso(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam String ano_curricular) throws Exception {
		byte[] relatrio = servicoRecurso(codigo_ano_lectivo, codigo_disciplina, codigo_curso, codigo_turma,
				ano_curricular);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoRecurso(Integer codigo_ano_lectivo, Integer codigo_disciplina, Integer codigo_curso,
			Integer codigo_turma, String ano_curricular) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano_lectivo", codigo_ano_lectivo);
		paramets.put("codigo_disciplina", codigo_disciplina);
		paramets.put("codigo_curso", codigo_curso);
		paramets.put("codigo_turma", codigo_turma);
		paramets.put("ano_curricular", ano_curricular);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Pauta_Recurso.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/diploma")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> diploma(@RequestParam Integer numero_aluno) throws Exception {
		byte[] relatrio = servicoDiploma(numero_aluno);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoDiploma(Integer numero_aluno) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_aluno", numero_aluno);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Diploma.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/presenca/disciplina")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> listaPresencaPorDisciplina(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam String ano_curricular) throws Exception {
		byte[] relatrio = servicoPresencaPorDisciplina(codigo_ano_lectivo, codigo_disciplina, codigo_curso,
				codigo_turma, ano_curricular);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoPresencaPorDisciplina(Integer codigo_ano_lectivo, Integer codigo_disciplina,
			Integer codigo_curso, Integer codigo_turma, String ano_curricular) throws JRException {
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("codigo_ano_lectivo", codigo_ano_lectivo);
		paramets.put("codigo_disciplina", codigo_disciplina);
		paramets.put("codigo_curso", codigo_curso);
		paramets.put("codigo_turma", codigo_turma);
		paramets.put("ano_curricular", ano_curricular);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Pauta_Presenca_Disciplina.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/controle/presenca")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> listaControlePresenca(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam String ano_curricular, @RequestParam String tipo_prova,
			@RequestParam Integer condicao) throws Exception {
		byte[] relatrio = servicoControlePresenca(codigo_ano_lectivo, codigo_disciplina, codigo_curso, codigo_turma,
				ano_curricular, tipo_prova, condicao);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoControlePresenca(Integer codigo_ano_lectivo, Integer codigo_disciplina, Integer codigo_curso,
			Integer codigo_turma, String ano_curricular, String tipo_prova, Integer condicao) throws JRException {
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("codigo_ano_lectivo", codigo_ano_lectivo);
		paramets.put("codigo_disciplina", codigo_disciplina);
		paramets.put("codigo_curso", codigo_curso);
		paramets.put("codigo_turma", codigo_turma);
		paramets.put("ano_curricular", ano_curricular);
		paramets.put("tipo_prova", tipo_prova);
		paramets.put("condicao", condicao);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Pauta_Presenca.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/turma/base")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> listaTurmaBase(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam String ano_curricular) throws Exception {
		byte[] relatrio = servicoTurmaBase(codigo_ano_lectivo, codigo_disciplina, codigo_curso, codigo_turma,
				ano_curricular);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoTurmaBase(Integer codigo_ano_lectivo, Integer codigo_disciplina, Integer codigo_curso,
			Integer codigo_turma, String ano_curricular) throws JRException {
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("codigo_ano_lectivo", codigo_ano_lectivo);
		paramets.put("codigo_disciplina", codigo_disciplina);
		paramets.put("codigo_curso", codigo_curso);
		paramets.put("codigo_turma", codigo_turma);
		paramets.put("ano_curricular", ano_curricular);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Pauta_Turma_Base.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@RequestMapping(value = "/horario/docente", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")

	public ResponseEntity<byte[]> horariosDocente(@RequestParam Integer ano_letivo,
			@RequestParam Integer codigo_docente, @RequestParam String semestre) throws Exception {

		byte[] relatrio = servicoHorarioDocente(ano_letivo, codigo_docente, semestre);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoHorarioDocente(Integer ano_letivo, Integer codigo_docente, String semestre)
			throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano_letivo", ano_letivo);
		paramets.put("codigo_docente", codigo_docente);
		paramets.put("semestre", semestre);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Horario_Docente.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/horario/turma")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> horariosTurma(@RequestParam String ano_curricular, @RequestParam Integer ano_lectivo,
			@RequestParam String semestre, @RequestParam String turma) throws Exception {

		byte[] relatrio = servicoHorarioTurma(ano_curricular, ano_lectivo, semestre, turma);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoHorarioTurma(String ano_curricular, Integer ano_lectivo, String semestre, String turma)
			throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano_curricular", ano_curricular);
		paramets.put("ano_letivo", ano_lectivo);
		paramets.put("semestre", semestre);
		paramets.put("turma", turma);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Horario_Turma.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/horario/sala")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> horariosSala(@RequestParam Integer ano_letivo, @RequestParam String sala,
			@RequestParam String semestre) throws Exception {

		byte[] relatrio = servicoHorarioSala(ano_letivo, sala, semestre);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoHorarioSala(Integer ano_letivo, String sala, String semestre) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano_letivo", ano_letivo);
		paramets.put("sala", sala);
		paramets.put("semestre", semestre);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Horario_Sala.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/presenca/sem/contencioso")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> listaPresencaSemContencioso(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_turma,
			@RequestParam String tipo_prova) throws Exception {
		byte[] relatrio = servicoPresencaSemContencioso(codigo_ano_lectivo, codigo_disciplina, codigo_turma,
				tipo_prova);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoPresencaSemContencioso(Integer codigo_ano_lectivo, Integer codigo_disciplina,
			Integer codigo_turma, String tipo_prova) throws JRException {
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("codigo_ano_lectivo", codigo_ano_lectivo);
		paramets.put("codigo_disciplina", codigo_disciplina);

		paramets.put("codigo_turma", codigo_turma);

		paramets.put("tipo_prova", tipo_prova);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Sem_Contencioso.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/presenca/recurso")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> listaPresencaRecursto(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_turma) throws Exception {
		byte[] relatrio = servicoPresencaRecursto(codigo_ano_lectivo, codigo_disciplina, codigo_turma);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoPresencaRecursto(Integer codigo_ano_lectivo, Integer codigo_disciplina, Integer codigo_turma)
			throws JRException {
		Map<String, Object> paramets = new HashMap<>();

		paramets.put("codigo_ano_lectivo", codigo_ano_lectivo);
		paramets.put("codigo_disciplina", codigo_disciplina);

		paramets.put("codigo_turma", codigo_turma);
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorio/R_Pauta_Presenca_Extraordinaria.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/pauta/global/docente")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> pautaGlobalDocente(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam Integer condicao) throws Exception {
		byte[] relatrio = servicoPautaGlobalDocente(codigo_ano_lectivo, codigo_disciplina, codigo_curso, codigo_turma,
				condicao);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoPautaGlobalDocente(Integer codigo_ano_lectivo, Integer codigo_disciplina, Integer codigo_curso,
			Integer codigo_turma, Integer condicao) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano_lectivo", codigo_ano_lectivo);
		paramets.put("codigo_disciplina", codigo_disciplina);
		paramets.put("codigo_curso", codigo_curso);
		paramets.put("codigo_turma", codigo_turma);

		paramets.put("condicao", condicao);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Pauta_Global.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	/*
	 * @RequestMapping(value = "/buscarPorCodigo/{numero}", method =
	 * RequestMethod.GET, produces = "application/json")
	 * 
	 * @ResponseBody
	 * 
	 * @CrossOrigin(origins = "*") public ResponseEntity<ResponseCliente>
	 * buscarPorCodigo(@PathVariable Integer numero) { ResponseCliente c=new
	 * ResponseCliente();
	 * 
	 * AnoLectivo anoLectivo = this.repositoryAnoLectivo.findByAnoLectivo(numero);
	 * c.setCodigo(ResponseCode.values()[0].getDescricao());
	 * c.setResultado(anoLectivo); return new ResponseEntity<ResponseCliente>(c,
	 * HttpStatus.OK); }
	 */

	@GetMapping("/horarios/docente/{docente}/{anoLectivo}/{semestre}")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> hoririosDocente(@PathVariable Integer docente, @PathVariable Integer anoLectivo,
			@PathVariable String semestre) throws Exception {

		Usuario usuario = this.usuarioRepositorio.findByUserCode(docente);

		Integer codigoDocente = null;
		Docente userDocente = this.docenteRepository.findByUsuarioDocente(usuario);
		if (userDocente != null) {
			codigoDocente = userDocente.getId();
		}

		byte[] relatrio = servicoHorariosDocente(anoLectivo, codigoDocente, semestre);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoHorariosDocente(Integer codigo_ano_lectivo, Integer codigo_docente, String semestre)
			throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano_letivo", codigo_ano_lectivo);
		paramets.put("codigo_docente", codigo_docente);
		paramets.put("semestre", semestre);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Horario_Docente.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/pauta/globalIscid")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> pautaGlobalIscid(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam String ano_curricular, @RequestParam Integer condicao)
			throws Exception {

		Map<String, Object> paramets = new HashMap<>();
		paramets.put("cod_ano", codigo_ano_lectivo);
		paramets.put("cod_disciplina", codigo_disciplina);
		paramets.put("cod_curso", codigo_curso);
		paramets.put("cod_turma", codigo_turma);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/pauta_global_escid.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	@GetMapping("/pauta/globalSemClassificacaoIscid")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> pautaGlobalSemClassificacaoIscid(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam String ano_curricular, @RequestParam Integer condicao)
			throws Exception {

		Map<String, Object> paramets = new HashMap<>();
		paramets.put("cod_ano", codigo_ano_lectivo);
		paramets.put("cod_disciplina", codigo_disciplina);
		paramets.put("cod_curso", codigo_curso);
		paramets.put("cod_turma", codigo_turma);
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorio/pauta_global_escid_semclassificação.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	@GetMapping("/presenca/controlePresencaIscid")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> pautaGlobalControlePresencaIscid(@RequestParam Integer codigo_ano_lectivo,
			@RequestParam Integer codigo_disciplina, @RequestParam Integer codigo_curso,
			@RequestParam Integer codigo_turma, @RequestParam String tipo_prova) throws Exception {

		Map<String, Object> paramets = new HashMap<>();
		paramets.put("cod_ano", codigo_ano_lectivo);
		paramets.put("cod_disciplina", codigo_disciplina);
		paramets.put("cod_curso", codigo_curso);
		paramets.put("cod_turma", codigo_turma);
		paramets.put("tipo_prova", tipo_prova);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_acta.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	@RequestMapping(value = "/horario/turma/{anoLectivo}/{semestre}/{turma}/{curso}/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> horarioTurma(@PathVariable Integer anoLectivo, @PathVariable String semestre,
			@PathVariable String turma, @PathVariable String curso, @PathVariable Integer ano) throws Exception {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano_lectivo", anoLectivo);
		paramets.put("semestre", semestre);
		paramets.put("turma", turma);
		paramets.put("curso", curso);
		paramets.put("ano", ano);

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Horario_Turma.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());

		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

}