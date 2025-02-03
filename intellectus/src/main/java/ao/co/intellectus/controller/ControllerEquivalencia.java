package ao.co.intellectus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
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

import ao.co.intellectus.DTO.AdicionarDisciplinaEquivalencia;
import ao.co.intellectus.DTO.AnularEquivalencia;
import ao.co.intellectus.DTO.CursoOrigemCliente;
import ao.co.intellectus.DTO.DadosParaEquivalencia;
import ao.co.intellectus.DTO.DisciplinaEquivalencia;
import ao.co.intellectus.DTO.DisciplinaModelo;
import ao.co.intellectus.DTO.EquivalenciaCliente;
import ao.co.intellectus.DTO.EquivalenciaClienteModelo;
import ao.co.intellectus.DTO.EquivalenciaClienteS;
import ao.co.intellectus.DTO.HistoricoAndEquivalencias;
import ao.co.intellectus.DTO.HistoricoEquivalenciaModelo;
import ao.co.intellectus.DTO.ValidarEquivalenciaCliente;
import ao.co.intellectus.DTO.ValidarEquivalencias;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Equivalencia;
import ao.co.intellectus.model.HistoricoEquivalencia;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.EquivalenciaHistoricoRepository;
import ao.co.intellectus.repository.EquivalenciaRepository;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.cafold.EquivalenciaServices;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/equivalencia")
public class ControllerEquivalencia {
	@Autowired
	private EquivalenciaRepository equivalenciaRepository;
	@Autowired
	private EquivalenciaHistoricoRepository equivalenciaHistorico;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private InstituicaoRepository instituicaoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private EquivalenciaServices equivalenciaServices;
	

	
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody EquivalenciaCliente cEquivalencia) {
		ResponseCliente c = new ResponseCliente();
		Usuario usuario = this.usuarioRepository
				.findByUserName(cEquivalencia.getUserName() != null ? cEquivalencia.getUserName() : null);
		List<DisciplinaEquivalencia> disciplinas = cEquivalencia.getDisciplinas();

		if (disciplinas.isEmpty()) {
			c.setMensagem("Verificque as disciplinas, não foram recebidas.");
			c.setCodigo(300);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

		}

		Aluno aluno = cEquivalencia.getAluno() != null
				? this.alunoRepository.findByNumeroDeAluno(cEquivalencia.getAluno().toString())
				: null;
		AnoLectivo anoLectivo = cEquivalencia.getAnoLectivo() != null
				? this.anoLectivoRepository.findOne(cEquivalencia.getAnoLectivo())
				: null;

		if (aluno == null) {
			c.setMensagem("Verifique os dados do aluno!");
			c.setCodigo(300);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		Equivalencia equivalencia = new Equivalencia();
		equivalencia.setAluno(aluno);
		equivalencia.setAnoLectivo(anoLectivo);
		equivalencia.setNumeroAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
		equivalencia.setDataEquivalencia(new Date());
		equivalencia.setEscolaOrigem(cEquivalencia.getEscolaOrigem());
		equivalencia.setUsuarioEquivalencia(usuario);

		Curso curso;
		if (cEquivalencia.getTipo().getDescricao().equals("Interna")) {
			curso =this.cursoRepository.findById(Integer.parseInt(cEquivalencia.getCursoOrigem()));
			equivalencia.setCursoOrigem(curso.getDescricao());
		} else {
			equivalencia.setCursoOrigem(cEquivalencia.getCursoOrigem());
		}

		equivalencia.setTipo(cEquivalencia.getTipo());
		equivalencia.setCursoDestino(aluno.getCurso());

		Equivalencia equivalenciaSalva = this.equivalenciaRepository.save(equivalencia);
		this.equivalenciaServices.gerarHistorico(equivalencia);
		HistoricoEquivalencia hEquval;
		// List<DisciplinaEquivalencia> disciplinas = cEquivalencia.getDisciplinas();

		for (DisciplinaEquivalencia ds : disciplinas) {

			hEquval = new HistoricoEquivalencia();

			Disciplina destino = this.disciplinaRepository.findOne(ds.getDisciplinaDestino());
			hEquval.setEquivalencia(equivalenciaSalva);
			hEquval.setDisciplinaOrigem(ds.getDisciplinaOrigem());
			hEquval.setDisciplinaDestino(destino);
			hEquval.setNotaOrigem(ds.getNotaOrigem());
			hEquval.setNumeroAluno(Integer.parseInt(aluno.getNumeroDeAluno()));
			hEquval.setAluno(aluno);
			hEquval.setEstado(true);
			hEquval.setPrimeiraValidacao(true);
			hEquval.setSegundaValidacao(true);
			hEquval.setTerceiraValidacao(true);

			this.equivalenciaHistorico.save(hEquval);
			this.equivalenciaServices.gerarHistorico(hEquval);

		}
		c.setMensagem("Equivalência realizada com sucesso!");
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody EquivalenciaClienteModelo cEquivalencia) {
		ResponseCliente c = new ResponseCliente();
		Equivalencia eqBase = this.equivalenciaRepository.findOne(cEquivalencia.getId());

		if (cEquivalencia.getCursoDestino() == null) {
			c.setMensagem("O Curso destino não pode ser nulo.");
			c.setCodigo(300);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		Curso cursoDestino = this.cursoRepository.findById(cEquivalencia.getCursoDestino());

		Aluno aluno = this.alunoRepository.findOne(cEquivalencia.getAluno());
		BeanUtils.copyProperties(cEquivalencia, eqBase, "aluno", "cursoDestino", "disciplinas");
		eqBase.setCursoDestino(cursoDestino);
		eqBase.setAluno(aluno);
		this.equivalenciaRepository.save(eqBase);
		this.equivalenciaServices.gerarHistorico(eqBase);
		// List<DisciplinaModelo> disciplinas = cEquivalencia.getDisciplinas();
		for (DisciplinaModelo ds : cEquivalencia.getDisciplinas()) {
			HistoricoEquivalencia eHistorico = this.equivalenciaHistorico.findOne(ds.getId());
			BeanUtils.copyProperties(ds, eHistorico, "equivalencia", "disciplina");
			Disciplina dDestino = this.disciplinaRepository.findOne(ds.getDisciplinaDestino());
			eHistorico.setEquivalencia(eHistorico.getEquivalencia());
			eHistorico.setDisciplinaDestino(dDestino);
			this.equivalenciaHistorico.save(eHistorico);
			this.equivalenciaServices.gerarHistorico(eHistorico);
		}
		c.setMensagem("Equivalência atualizada com sucesso!");
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// equivalencia/validarEquivalencia
	@RequestMapping(value = "/validarEquivalencia", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> validarEquivalencia(@RequestBody ValidarEquivalencias validar) {
		ResponseCliente c = new ResponseCliente();
		HistoricoEquivalencia disciplina;

		List<ValidarEquivalenciaCliente> historico = validar.getDisciplinas();

		if (!historico.isEmpty()) {
			for (ValidarEquivalenciaCliente eq : historico) {
				disciplina = this.equivalenciaHistorico.findOne(eq.getId());
				disciplina.setPrimeiraValidacao(true);
				disciplina.setSegundaValidacao(true);
				disciplina.setTerceiraValidacao(true);

				disciplina.setDataPrimeiraValidacao(new Date());
				disciplina.setDataSegundaValidacao(new Date());
				disciplina.setDataTerceiraValidacao(new Date());

				disciplina.setDataPrimeiraValidacao(new Date());
				this.equivalenciaHistorico.save(disciplina);
				this.equivalenciaServices.gerarHistorico(disciplina);
			}

			c.setMensagem("Validação efetivada com sucesso.");
		} else {
			c.setMensagem("Não existe equivalencia para se validar.");
		}
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/todas", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> todas() {
		ResponseCliente c = new ResponseCliente();

		Iterable<Equivalencia> todas = this.equivalenciaRepository.findAll();

		c.setResultado(todas);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorAluno/{numero}/{anoLectivoP}", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> equivalencias(@PathVariable String numero,
			@PathVariable Integer anoLectivoP) {
		ResponseCliente c = new ResponseCliente();
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);
		// AnoLectivo anoLectivo = this.anoLectivoRepository.findOne(anoLectivoP);

		List<Equivalencia> equivalencias = this.equivalenciaRepository.findByAluno(aluno);
		if (equivalencias.isEmpty()) {
			c.setMensagem("Nenhum registro encontradado.");
			c.setCodigo(200);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);

		}
		EquivalenciaClienteS equivale;
		String escola = "";
		List<EquivalenciaClienteS> equivales = new ArrayList<EquivalenciaClienteS>();
		for (Equivalencia equivalencia : equivalencias) {
			equivale = new EquivalenciaClienteS();

			if (equivalencia.getCursoDestino() != null)
				equivale.setCursoDestino(equivalencia.getCursoDestino().getDescricao());
			// curso origem
			equivale.setCursoOrigem(equivalencia.getCursoOrigem());

			if (equivalencia.getTipo().getDescricao().equals("Interna")) {
				Instituicao instituicao = this.instituicaoRepository.findOne(2);
				escola = instituicao.getDescricao();
			} else {
				escola = equivalencia.getEscolaOrigem();
			}
			equivale.setEscolaOrigem(escola);

			equivale.setId(equivalencia.getId());
			equivale.setDataEquivalencia(equivalencia.getDataEquivalencia());
			equivale.setNome(equivalencia.getAluno().getNome());
			equivale.setNumeroAluno(Integer.parseInt(equivalencia.getAluno().getNumeroDeAluno()));
			equivale.setTipo(equivalencia.getTipo());
			equivale.setCursoId(equivalencia.getAluno().getCurso().getId());

			this.equivalenciaHistorico.findByEquivalencia(equivalencia);

			equivales.add(equivale);
		}
		c.setResultado(equivales);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/atualizarEquivalencia", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> atualizarEquivalecencia(@RequestBody AnularEquivalencia eq) {
		ResponseCliente c = new ResponseCliente();

		HistoricoEquivalencia eHistorico = this.equivalenciaHistorico.findOne(eq.getIdDisciplina());
	
		this.equivalenciaHistorico.delete(eHistorico);

		c.setMensagem("Equivalência atualizada sucesso!");
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarHistoricoPorEquivalencia1/{numero}", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> historicoEquivalencias1(@PathVariable Integer numero) {
		// Busca determina equivalência
		Equivalencia equivalencia = this.equivalenciaRepository.findOne(numero);
		HistoricoAndEquivalencias he = new HistoricoAndEquivalencias();

		if (equivalencia == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhuma equivalência encontrada.");
		}else{
			// Busca o Historico todo da equivalência encontrada.
			List<HistoricoEquivalencia> hEquivalencias = this.equivalenciaHistorico.findByEquivalencia(equivalencia);
			List<HistoricoEquivalenciaModelo> listaEquivalencias = new ArrayList<>();
			for (HistoricoEquivalencia hEq : hEquivalencias) {
				if (hEq.isEstado()) {
					HistoricoEquivalenciaModelo h = new HistoricoEquivalenciaModelo();
					BeanUtils.copyProperties(hEq, h, "disciplinaDestino");
					h.setDisciplinaDestino(hEq.getDisciplinaDestino().getId());
					h.setAnoCurricular(hEq.getDisciplinaDestino().getAnoCorricular());
					h.setUsuarioEquivalencia(hEq.getEquivalencia().getUsuarioEquivalencia()==null ? null:hEq.getEquivalencia().getUsuarioEquivalencia().getName());
					listaEquivalencias.add(h);
				}
			}
			he.setEquivalencia(equivalencia);
			he.setHistoricoEquivalencias(listaEquivalencias);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(he, 0, null);
		}
	}

	@RequestMapping(value = "/adicionar/disciplina", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> app(@RequestBody AdicionarDisciplinaEquivalencia adiconar) {
		ResponseCliente c = new ResponseCliente();

		Equivalencia equivalencia = this.equivalenciaRepository.findOne(adiconar.getIdEquivalencia());

		List<DisciplinaEquivalencia> disciplinas = adiconar.getDisciplinas();

		HistoricoEquivalencia historico;
		for (DisciplinaEquivalencia daqui : disciplinas) {
			historico = new HistoricoEquivalencia();

			historico.setAluno(equivalencia.getAluno());

			Disciplina disciplinaDestino = this.disciplinaRepository.findOne(daqui.getDisciplinaDestino());
			historico.setDisciplinaDestino(disciplinaDestino);
			historico.setDisciplinaOrigem(daqui.getDisciplinaOrigem());
			historico.setEquivalencia(equivalencia);
			historico.setNotaOrigem(daqui.getNotaOrigem());
			historico.setNumeroAluno(Integer.parseInt(equivalencia.getAluno().getNumeroDeAluno()));
			historico.setEstado(true);

			this.equivalenciaHistorico.save(historico);
			this.equivalenciaServices.gerarHistorico(historico);
		}

		String mensagem = disciplinas.size() == 1 ? "Disciplina adiciona com sucesso"
				: "Disciplina adiciona com sucesso";

		c.setResultado(null);
		c.setMensagem(mensagem);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	} 

	// equivalencia/dadosParaEquivalencia
	@RequestMapping(value = "/dadosParaEquivalencia/{numero}/{tipo}", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> retonoParaEquivalencia(@PathVariable String numero,@PathVariable String tipo) {
		ResponseCliente c = new ResponseCliente();

		
		
		Aluno aluno = this.alunoRepository.findByNumeroDeAluno(numero);

	    Instituicao instituicao = this.instituicaoRepository.findOne(2);
		if (aluno == null) {
			c.setMensagem("Verifique o número de aluno.");
			c.setCodigo(300);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		DadosParaEquivalencia e = new DadosParaEquivalencia();

		e.setNome(aluno.getNome());
		e.setNumero(Integer.parseInt(aluno.getNumeroDeAluno()));
		e.setTipoDocumento(aluno.getDocumentoIdentificacao());
		e.setNumeroDocumento(aluno.getNumeroDocumento());
		// CURSO DO ALUNO QUE É O DESTINO
		e.setCursoDestino(aluno.getCurso().getPlano());
		e.setCursoDestinoId(aluno.getCurso().getId());
		// CURSOS ORIGEM
		CursoOrigemCliente o;
		List<CursoOrigemCliente> cursosOrigem = new ArrayList<CursoOrigemCliente>();
		List<Matricula> cursos = this.matriculaRepository.findByAluno(aluno);
		//-----------------------
		if (tipo.equals("E")) {
			o = new CursoOrigemCliente();
			o.setCursoOrigem(aluno.getCurso().getPlano());
			o.setCursoOrigemId(aluno.getCurso().getId());
			cursosOrigem.add(o);	
		} else if(tipo.equals("I")){
			e.setEscolaOrigemId(instituicao.getId());
			e.setEscolaOrigem(instituicao.getDescricao());
			if (cursos.isEmpty()) {
				o = new CursoOrigemCliente();
				o.setCursoOrigem(aluno.getCurso().getPlano());
				o.setCursoOrigemId(aluno.getCurso().getId());
				cursosOrigem.add(o);
			} else {
				for (Matricula m : cursos) {
					o = new CursoOrigemCliente();
					o.setCursoOrigem(m.getCurso().getPlano());
					o.setCursoOrigemId(m.getCurso().getId());
					cursosOrigem.add(o);
				}
			}
		}
		//---------------------------
		e.setCurosAlunos(cursosOrigem);
		c.setResultado(e);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}