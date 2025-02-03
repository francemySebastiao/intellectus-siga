package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
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

import ao.co.intellectus.DTO.MaticuladosCliente;
import ao.co.intellectus.DTO.TurmaBusca;
import ao.co.intellectus.DTO.TurmaCliente;
import ao.co.intellectus.DTO.TurmaDisponivelCliente;
import ao.co.intellectus.DTO.colegio.TurmaViewModelColegio;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.ProgDisciplinaTurma;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.TurmaDisponivel;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.model.request.TurmaDisponivelRequest;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.ProgDisciplinaTurmaRepository;
import ao.co.intellectus.repository.TurmaDisponivelRepository;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.servico.cafold.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/turmasDisponiveis")
public class ControllerTurmasDisponiveis {

	@Autowired
	private TurmaDisponivelRepository turmaDisponivel;
	@Autowired
	private CursoRepository repositoryCurso;
	@Autowired
	private TurmaRepository repositoryTurma;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private ProgDisciplinaTurmaRepository progDisciplinaTurmaRepository;
	// @Autowired
	// private HistoricoAlunoRepository historicoAlunoRepositorico;

	@RequestMapping(value = "/todasTurmasDisponivel", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listaTurma() {
		ResponseCliente c = new ResponseCliente();

		List<TurmaDisponivel> turmas = (List<TurmaDisponivel>) this.turmaDisponivel.findAll();

		if (turmas.isEmpty()) {
			c.setMensagem("Nenhuma turma disponivel encontrado.");
		}

		c.setResultado(turmas);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody TurmaDisponivelRequest turmaDisponivelRequest) {
		ResponseCliente c = new ResponseCliente();

		try {

			Curso curso = turmaDisponivelRequest.getCurso() != null
					? this.repositoryCurso.findByIdAndStatus(turmaDisponivelRequest.getCurso(), true)
					: null;

			Turma turma = turmaDisponivelRequest.getTurma() != null
					? this.repositoryTurma.findOne(turmaDisponivelRequest.getTurma())
					: null;

			TurmaDisponivel pesquisado = this.turmaDisponivel.findByCursoAndTurmaAndAno(curso, turma,
					turmaDisponivelRequest.getAno());
			TurmaDisponivel turmaDisponivel = new TurmaDisponivel();
		
			if (pesquisado == null) {
				BeanUtils.copyProperties(turmaDisponivelRequest, turmaDisponivel, "curso", "turma");
				turmaDisponivel.setCurso(curso);
				turmaDisponivel.setTurma(turma);
				this.turmaDisponivel.save(turmaDisponivel);
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(turma);
				c.setMensagem("Turma disponiblizada com sucesso!");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setResultado(turma);
				c.setMensagem("Essa turma já existe para esse ano curricular.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

		} catch (Exception ex) {
			// TODO: handle exception
		
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody TurmaDisponivelRequest turmaDisponivelRequest) {
		ResponseCliente c = new ResponseCliente();

		if (turmaDisponivelRequest != null) {
			Curso curso = this.repositoryCurso.findByIdAndStatus(turmaDisponivelRequest.getCurso(), true);
			Turma turma = this.repositoryTurma.findOne(turmaDisponivelRequest.getTurma());

			TurmaDisponivel turmaDisponivelNovo = this.turmaDisponivel.findOne(turmaDisponivelRequest.getId());
			// boolean guardian=cp.isSeriado();
			/* Atualizando todos os campos do candidato editado. */
			BeanUtils.copyProperties(turmaDisponivelRequest, turmaDisponivelNovo, "curso");

			turmaDisponivelNovo.setCurso(curso);
			turmaDisponivelNovo.setTurma(turma);
			// cp.setSeriado(guardian);
			/* Mensagem de sucesso. */
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(turmaDisponivelNovo);
			c.setMensagem("Turma disponivel " + turmaDisponivelNovo.getTurma().getTurma() + " atualizado com sucesso!");
			this.turmaDisponivel.save(turmaDisponivelNovo);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setResultado(turmaDisponivelRequest);
			c.setMensagem("A turma disponivel enviado não pode ser nullo!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/buscarPorCurso/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCurso(@PathVariable Integer id) {
		ResponseCliente c = new ResponseCliente();
		Curso curso = this.repositoryCurso.findByIdAndStatus(id, true);

		List<TurmaDisponivel> turmasDisponivel = this.turmaDisponivel.findByCurso(curso);

		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);

		if (turmasDisponivel.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhuma turma encontrado nesse curso");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		List<TurmaDisponivel> disponiveis = new ArrayList<TurmaDisponivel>();
		TurmaDisponivel disponivel;
		int inscritos = 0;

		for (TurmaDisponivel td : turmasDisponivel) {
			inscritos = 0;
			disponivel = new TurmaDisponivel();
			List<Matricula> matriculados = this.matriculaRepository
					.findByAnoCurricularAndAnoLectivoAndCursoAndTurmaBaseAndAnulado(td.getAno(), anoLectivo.get(0), curso,
							td.getTurma(),false);

			// findByAnoCorricularAndAnoLectivoAndCursoAndTurma
			inscritos = matriculados.size();

			disponivel.setAceitaInscricoes(td.isAceitaInscricoes());
			disponivel.setAno(td.getAno());
			disponivel.setCapacidade(td.getCapacidade());
			disponivel.setCurso(td.getCurso());
			disponivel.setInscritos(inscritos);
			disponivel.setTurma(td.getTurma());
			disponivel.setId(td.getId());

			disponiveis.add(disponivel);
		}
		// c.setResultado(turmasDisponivel);
		c.setResultado(disponiveis);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/verAlunosInscritos/{curso}/{turma}/{anoCurricular}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> verAlunosInscritos(@PathVariable Integer curso, @PathVariable Integer turma,
			@PathVariable Integer anoCurricular) {
		ResponseCliente c = new ResponseCliente();

		Curso cursoPego = this.repositoryCurso.findById(curso);
		Turma turmaPego = this.repositoryTurma.findOne(turma);
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);

		List<MaticuladosCliente> matriculas = new ArrayList<MaticuladosCliente>();
		MaticuladosCliente matricula;
		List<Matricula> matriculados = this.matriculaRepository.findByAnoCurricularAndAnoLectivoAndCursoAndTurmaBaseAndAnulado(anoCurricular, anoLectivo.get(0), cursoPego, turmaPego,false);
		for (Matricula m : matriculados) {
			matricula = new MaticuladosCliente();

			matricula.setAnoCurricular(anoCurricular);
			matricula.setCurso(cursoPego.getDescricao());
			matricula.setNome(m.getAluno().getNome());
			matricula.setNumero(Integer.parseInt(m.getNumeroDeAluno()));
			matricula.setTurma(turmaPego.getTurma());

			matriculas.add(matricula);
		}

		// c.setResultado(turmasDisponivel);
		c.setResultado(matriculas);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarTurmaDisponivelPorId/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarTurmaDisponivelPorId(@PathVariable Integer id) {
		TurmaDisponivel buscaTurmaDisponivel = this.turmaDisponivel.findOne(id);
		ResponseCliente c = new ResponseCliente();

		if (buscaTurmaDisponivel == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(buscaTurmaDisponivel);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Iterable<TurmaDisponivelCliente> todos() {
		List<TurmaDisponivelCliente> cTurmaDisponivel = new ArrayList<TurmaDisponivelCliente>();
		Iterable<TurmaDisponivel> turmasDispoveis = this.turmaDisponivel.findAll();
		TurmaDisponivelCliente turma;
		for (TurmaDisponivel turmaDisponivel : turmasDispoveis) {
			turma = new TurmaDisponivelCliente();
			turma.setId(turmaDisponivel.getTurma().getId());
			turma.setAno(turmaDisponivel.getAno());
			turma.setTurma(turmaDisponivel.getTurma().getTurma());
			turma.setCapacidade(turmaDisponivel.getCapacidade());
			if(turmaDisponivel.getCurso() != null){
				turma.setCurso(turmaDisponivel.getCurso().getDescricao());
				turma.setCursoCodigo(turmaDisponivel.getCurso().getId());
			}
			turma.setAceitaInscricoes(turmaDisponivel.isAceitaInscricoes());
			turma.setTurno(turmaDisponivel.getTurma().getTurno());

			cTurmaDisponivel.add(turma);
		}
		return cTurmaDisponivel;
	}

	
	@RequestMapping(value = "/buscarTurmaAndDisciplinaResumo/{disciplina}/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarTurmaAndDisciplinaResumo(@PathVariable Integer anoLectivo,@PathVariable Integer disciplina) {
		ResponseCliente c = new ResponseCliente();
		List<TurmaDisponivelCliente> cTurmaDisponivel = new ArrayList<TurmaDisponivelCliente>();
		List<TurmaCliente> turmasCliente=new ArrayList<TurmaCliente>();
		
		List<ProgDisciplinaTurma> turmas = this.progDisciplinaTurmaRepository.findByAnoLectivoAndDisciplinaId(anoLectivo, disciplina);
		TurmaDisponivelCliente turma;
		for (ProgDisciplinaTurma o : turmas) {
			turma = new TurmaDisponivelCliente();
			turma.setId(o.getId());
			turma.setTurma(o.getTurma());
			cTurmaDisponivel.add(turma);
		}
		c.setResultado(turmasCliente);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/buscarPorCursoAndDisponivel/{codigo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCursoAndDisponivel(@PathVariable Integer codigo) {
		ResponseCliente c = new ResponseCliente();
		Curso curso = this.repositoryCurso.findByIdAndStatus(codigo, true);

		List<TurmaDisponivel> turmasDisponivel = this.turmaDisponivel.findByCursoAndAceitaInscricoes(curso, true);

		if (turmasDisponivel.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhuma turma encontrado nesse curso");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		TurmaDisponivelCliente turma;
		List<TurmaDisponivelCliente> cTurmaDisponivel = new ArrayList<TurmaDisponivelCliente>();
		for (TurmaDisponivel turmaDisponivel : turmasDisponivel) {
			turma = new TurmaDisponivelCliente();

			turma.setId(turmaDisponivel.getTurma().getId());
			
			turma.setCapacidade(turmaDisponivel.getCapacidade());
			
			turma.setAceitaInscricoes(turmaDisponivel.isAceitaInscricoes());
			
			turma.setCurso(turmaDisponivel.getCurso().getPlano());
			
			turma.setCursoCodigo(turmaDisponivel.getCurso().getId());

			turma.setTurma(turmaDisponivel.getTurma().getTurma());
			
			turma.setAno(turmaDisponivel.getAno());
			
			turma.setTurno(turmaDisponivel.getTurma().getTurno());
			
			cTurmaDisponivel.add(turma);
		}
		c.setResultado(cTurmaDisponivel);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorCursoAndAno", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Iterable<TurmaDisponivelCliente> buscarPorCursoAndAno(@RequestBody TurmaBusca recebe) {
		List<TurmaDisponivelCliente> cTurmaDisponivel = new ArrayList<TurmaDisponivelCliente>();
		Curso curso = this.repositoryCurso.findByIdAndStatus(recebe.getCurso(), true);
		List<TurmaDisponivel> bTurmasDisponiveis = this.turmaDisponivel.findByCursoAndAnoAndAceitaInscricoes(curso,
				recebe.getAnoCurricular(), true);
		TurmaDisponivelCliente turma;
		for (TurmaDisponivel turmaDisponivel : bTurmasDisponiveis) {
			turma = new TurmaDisponivelCliente();

			turma.setCapacidade(turmaDisponivel.getCapacidade());
			turma.setAceitaInscricoes(turmaDisponivel.isAceitaInscricoes());
			turma.setCurso(turmaDisponivel.getCurso().getDescricao());
			turma.setTurma(turmaDisponivel.getTurma().getTurma());
			turma.setAno(turmaDisponivel.getAno());
			turma.setTurno(turmaDisponivel.getTurma().getTurno());

			cTurmaDisponivel.add(turma);
		}
		return cTurmaDisponivel;
	}

	@RequestMapping(value = "/buscarPorCursoAndDisponivelColegio/{codigo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCursoAndDisponivelColegio(@PathVariable Integer codigo) {
		ResponseCliente c = new ResponseCliente();
		Curso curso = this.repositoryCurso.findByIdAndStatus(codigo, true);

		List<TurmaDisponivel> turmasDisponivel = this.turmaDisponivel.findByCursoAndAceitaInscricoes(curso, true);

		if (turmasDisponivel.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhuma turma encontrado nesse curso");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		TurmaViewModelColegio turma;
		List<TurmaViewModelColegio> cTurmaDisponivel = new ArrayList<TurmaViewModelColegio>();
		
		
		for (TurmaDisponivel o : turmasDisponivel) {
			turma = new TurmaViewModelColegio();
			
			turma.setId(o.getTurma().getId());
			turma.setTurma(o.getTurma().getTurma());

			cTurmaDisponivel.add(turma);
		}
		
		c.setResultado(cTurmaDisponivel);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/listagens/turma")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioListagensAlunosPorTurma(@RequestParam Integer codigo_ano,
			@RequestParam String ano_curricular, @RequestParam Integer codigo_turma, @RequestParam Integer codigo_curso)
			throws Exception {
		byte[] relatrio = servicoListagensAlunosPorTurma(codigo_ano, ano_curricular, codigo_turma, codigo_curso);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoListagensAlunosPorTurma(Integer codigo_ano, String ano_curricular, Integer codigo_turma,
			Integer codigo_curso) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano", codigo_ano);
		paramets.put("ano_curricular", ano_curricular);
		paramets.put("codigo_turma", codigo_turma);
		paramets.put("codigo_curso", codigo_curso);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Listagem_Por_Turma.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}
}