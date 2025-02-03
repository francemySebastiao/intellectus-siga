package ao.co.intellectus.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.DisciplinaCliente;
import ao.co.intellectus.DTO.DisciplinaMatriculaCliente;
import ao.co.intellectus.DTO.DisciplinasTurmasCliente;
import ao.co.intellectus.DTO.OutrosAnos;
import ao.co.intellectus.DTO.TurmaCliente;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.TurmaDisponivel;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.model.request.DisciplinaRequest;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.TurmaDisponivelRepository;

@RestController
@RequestMapping("/disciplina")
public class ControllerDisciplina {
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private TurmaDisponivelRepository turmaDisponivel;

	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public Iterable<Disciplina> disciplinas() {
		return disciplinaRepository.findByStatus(true);
	}
	
	@RequestMapping(value = "/todas-equivalencia", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public Iterable<Disciplina> disciplinasEquivalencia() {
		return disciplinaRepository.buscarDisciplinasParaEquivalencia();
	}
	
	@RequestMapping(value = "/todas-disciplinas", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public Iterable<Disciplina> disciplinasHorario() {
		return disciplinaRepository.buscarDisciplinasParaHorarios();
	}
	
	

	@RequestMapping(value = "/buscarPorCurso/{id}", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCurso(@PathVariable Integer id) {
		ResponseCliente c = new ResponseCliente();
		Curso curso = this.cursoRepository.findByIdAndStatus(id,true);
		List<Disciplina> disciplinas = this.disciplinaRepository.findByCursoAndStatus(curso,true);
		if(disciplinas.isEmpty()){
		    c.setCodigo(ResponseCode.values()[2].getDescricao());
		    c.setMensagem("Nenhuma disciplina encontrado nesse curso");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(disciplinas);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

		@RequestMapping(value = "/buscarPorCursoTodas/{id}", method = RequestMethod.GET, produces = "application/json")
		@CrossOrigin(origins = "*")
		public ResponseEntity<ResponseCliente> buscarPorCursoTodas(@PathVariable Integer id) {
			ResponseCliente c = new ResponseCliente();
			Curso curso = this.cursoRepository.findByIdAndStatus(id,true);
			List<Disciplina> disciplinas = this.disciplinaRepository.findByCurso(curso);
			if(disciplinas.isEmpty()){
			    c.setCodigo(ResponseCode.values()[2].getDescricao());
			    c.setMensagem("Nenhuma disciplina encontrado nesse curso");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			c.setResultado(disciplinas);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/todasDoCurso/{id}", method = RequestMethod.GET, produces = "application/json")
		@CrossOrigin(origins = "*")
		public ResponseEntity<ResponseCliente> todasDoCurso(@PathVariable Integer id) {
			ResponseCliente c = new ResponseCliente();
			Curso curso = this.cursoRepository.findById(id);
			List<Disciplina> disciplinas = this.disciplinaRepository.findByCurso(curso);
			if(disciplinas.isEmpty()){
			    c.setCodigo(ResponseCode.values()[2].getDescricao());
			    c.setMensagem("Nenhuma disciplina encontrado nesse curso");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			c.setResultado(disciplinas);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		

	@RequestMapping(value = "/buscarTodasEmDetalhe", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarTodasEmDetalhe() {
		Iterable<Disciplina> todasEmDetalhe = this.disciplinaRepository.findAll();
		ResponseCliente c = new ResponseCliente();
		if (todasEmDetalhe.equals(null)) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		List<DisciplinaCliente> cDisciplina = new ArrayList<DisciplinaCliente>();
		DisciplinaCliente disciplinaAdiciona;
		for (Disciplina disciplina : todasEmDetalhe) {
			disciplinaAdiciona = new DisciplinaCliente();

			disciplinaAdiciona.setId(disciplina.getId());
			disciplinaAdiciona.setDescricao(disciplina.getDescricao());
			disciplinaAdiciona.setSigla(disciplina.getSigla());
			cDisciplina.add(disciplinaAdiciona);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(cDisciplina);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/buscarDisciplinaPorCurso/{numero}", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarDisciplinaPorCurso(@PathVariable Integer numero) {
		Curso curso = this.cursoRepository.findByIdAndStatus(numero,true);
		List<Disciplina> todasEmDetalhe = this.disciplinaRepository.findByCursoAndStatusOrderByAnoCorricularAsc(curso,true);
		ResponseCliente c = new ResponseCliente();
		if (todasEmDetalhe.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		List<DisciplinaCliente> cDisciplina = new ArrayList<DisciplinaCliente>();
		DisciplinaCliente disciplinaAdiciona;
		for (Disciplina disciplina : todasEmDetalhe) {
			disciplinaAdiciona = new DisciplinaCliente();
			disciplinaAdiciona.setId(disciplina.getId());
			disciplinaAdiciona.setDescricao(disciplina.getDescricao());
			disciplinaAdiciona.setSigla(disciplina.getSigla());
			cDisciplina.add(disciplinaAdiciona);
		}
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(cDisciplina);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buscarPorId/{id}", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarDisciplinaPorId(@PathVariable Integer id) {
		Disciplina buscarDisciplina = this.disciplinaRepository.findOne(id);
		ResponseCliente c = new ResponseCliente();

		if (buscarDisciplina == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(buscarDisciplina);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorDetalhe/{id}", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarDisciplinaDetalhe(@PathVariable Integer id) {
		Disciplina buscarDisciplina = this.disciplinaRepository.findOne(id);

		ResponseCliente c = new ResponseCliente();

		if (buscarDisciplina == null) {
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		DisciplinaCliente cDisciplina = new DisciplinaCliente();
		cDisciplina.setId(buscarDisciplina.getId());
		cDisciplina.setDescricao(buscarDisciplina.getDescricao());

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(cDisciplina);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarDisciplinaMatricula", method = RequestMethod.POST, produces = "application/json")
	@CrossOrigin(origins = "*")
	public List<DisciplinasTurmasCliente> buscarDisciplinaMatricula(@RequestBody DisciplinaMatriculaCliente buscaDisciplina) {
		// anos adicionais
		Boolean[] adicionais = new Boolean[5];
		adicionais[0] = buscaDisciplina.isPrimeiroAno();
		adicionais[1] = buscaDisciplina.isSegundoAno();
		adicionais[2] = buscaDisciplina.isTerceiroAno();
		adicionais[3] = buscaDisciplina.isQuartoAno();
		adicionais[4] = buscaDisciplina.isQuintoAno();

		List<OutrosAnos> anosAdicionais = new ArrayList<OutrosAnos>();

		OutrosAnos anoAdicional;
		for (int i = 0; i < adicionais.length; i++) {
			anoAdicional = new OutrosAnos();
			if (i == 0) {
				anoAdicional.setAnoCurricularValidado(adicionais[i]);
				anoAdicional.setAnoCurricular(1);
				anosAdicionais.add(anoAdicional);
			}
			if (i == 1) {
				anoAdicional.setAnoCurricularValidado(adicionais[i]);
				anoAdicional.setAnoCurricular(2);
				anosAdicionais.add(anoAdicional);
			}
			if (i == 2) {
				anoAdicional.setAnoCurricularValidado(adicionais[i]);
				anoAdicional.setAnoCurricular(3);
				anosAdicionais.add(anoAdicional);
			}
			if (i == 3) {
				anoAdicional.setAnoCurricularValidado(adicionais[i]);
				anoAdicional.setAnoCurricular(4);
				anosAdicionais.add(anoAdicional);
			}
			if (i == 4) {
				anoAdicional.setAnoCurricularValidado(adicionais[i]);
				anoAdicional.setAnoCurricular(5);
				anosAdicionais.add(anoAdicional);
			}
		}
		Curso curso = this.cursoRepository.findByIdAndStatus(buscaDisciplina.getCurso(),true);
		List<Disciplina> buscaDisciplinaMatricula = this.disciplinaRepository.findByCursoAndAnoCorricular(curso,
				buscaDisciplina.getAnoCurricular());

		DisciplinaCliente disciplinaAdiciona = new DisciplinaCliente();
		List<DisciplinaCliente> cDisciplina = new ArrayList<DisciplinaCliente>();
		List<DisciplinasTurmasCliente> disciplinasTurmas = new ArrayList<DisciplinasTurmasCliente>();

		List<TurmaCliente> cTurmaDisponivel = new ArrayList<TurmaCliente>();
		Curso cursoTurma = this.cursoRepository.findByIdAndStatus(buscaDisciplina.getCurso(),true);

		List<TurmaDisponivel> bTurmasDisponiveis = this.turmaDisponivel.findByCursoAndAnoAndAceitaInscricoes(cursoTurma,
				buscaDisciplina.getAnoCurricular(), true);
		TurmaCliente turma;
		TurmaDisponivel turmaMatricula = new TurmaDisponivel();

		for (TurmaDisponivel turmaDisponivel : bTurmasDisponiveis) {
			turma = new TurmaCliente();
			turma.setIdTurma(turmaDisponivel.getTurma().getId());
			turma.setTurma(turmaDisponivel.getTurma().getTurma());
			turma.setTurno(turmaDisponivel.getTurma().getTurno());
			if (buscaDisciplina.getTurno() == turmaDisponivel.getTurma().getTurno()) {
				if (turmaDisponivel.isAceitaInscricoes())
					turmaMatricula = turmaDisponivel;
			}
			cTurmaDisponivel.add(turma);
		}

		for (Disciplina disciplina : buscaDisciplinaMatricula) {
			disciplinaAdiciona = new DisciplinaCliente();
			disciplinaAdiciona.setId(disciplina.getId());
			disciplinaAdiciona.setDescricao(disciplina.getDescricao());
			disciplinaAdiciona.setSigla(disciplina.getSigla());
			//if(turmaMatricula!=null)
			disciplinaAdiciona.setTurma(turmaMatricula.getTurma().getTurma());
			//if(turmaMatricula!=null)
			disciplinaAdiciona.setIdTurma(turmaMatricula.getTurma().getId());
			disciplinaAdiciona.setAnoCurricular(disciplina.getAnoCorricular());
			if (buscaDisciplina.getTipoInscricao() == 1) {
				disciplinaAdiciona.setMatricula(true);
			}
			cDisciplina.add(disciplinaAdiciona);
		}

		List<Disciplina> disciplinasAdicionais = new ArrayList<Disciplina>();

		if (buscaDisciplina.getTipoInscricao() != 1) {
			for (OutrosAnos outrosAnos : anosAdicionais) {

				if (outrosAnos.isAnoCurricularValidado()) {
					disciplinasAdicionais = this.disciplinaRepository.findByCursoAndAnoCorricular(curso,
							outrosAnos.getAnoCurricular());

					bTurmasDisponiveis = this.turmaDisponivel.findByCursoAndAnoAndAceitaInscricoes(cursoTurma,
							buscaDisciplina.getAnoCurricular(), true);

					disciplinasAdicionais.size();
					Disciplina disciplina = null;
					for (int i = 0; i < disciplinasAdicionais.size(); i++) {
						disciplina = new Disciplina();
						disciplina = disciplinasAdicionais.get(i);
						disciplinaAdiciona = new DisciplinaCliente();
						disciplinaAdiciona.setId(disciplina.getId());
						disciplinaAdiciona.setDescricao(disciplina.getDescricao());
						disciplinaAdiciona.setSigla(disciplina.getSigla());
						disciplinaAdiciona.setTurma(turmaMatricula.getTurma().getTurma());
						disciplinaAdiciona.setIdTurma(turmaMatricula.getTurma().getId());
						disciplinaAdiciona.setAnoCurricular(disciplina.getAnoCorricular());

						if (buscaDisciplina.getTipoInscricao() == 1) {
							disciplinaAdiciona.setMatricula(true);
						}
						cDisciplina.add(disciplinaAdiciona);
					}
				}
			}
		}
		DisciplinasTurmasCliente td = new DisciplinasTurmasCliente();
		// Adiciona as disciplinas e Turmas para a Matricula.
		td.setTipoInscricao(buscaDisciplina.getTipoInscricao());
		td.setDisciplinas(cDisciplina);
		td.setTurmas(cTurmaDisponivel);
		disciplinasTurmas.add(td);
		return disciplinasTurmas;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody DisciplinaRequest disciplinaRequest) {
		ResponseCliente c = new ResponseCliente();
		Curso curso = disciplinaRequest.getCurso() != null ? this.cursoRepository.findByIdAndStatus(disciplinaRequest.getCurso(),true) : null;

		Disciplina pesquisadoPorDescricaoAndCurso = this.disciplinaRepository.findByDescricaoAndCurso(disciplinaRequest.getDescricao(), curso);
		Disciplina disciplina = new Disciplina();
		if (pesquisadoPorDescricaoAndCurso == null) {
			disciplina.setDescricao(disciplinaRequest.getDescricao());
			disciplina.setAnoCorricular(disciplinaRequest.getAnoCorricular());
			disciplina.setHoras(disciplinaRequest.getHoras());
			disciplina.setNuclear(disciplinaRequest.isNuclear());
			disciplina.setExtraCurricular(disciplinaRequest.isExtraCurricular());
			disciplina.setOpcinal(disciplinaRequest.isOpcinal());
			disciplina.setStatus(disciplinaRequest.isStatus());
			disciplina.setControleTransicaoAno(disciplinaRequest.getControleTransicaoAno());
			disciplina.setSigla(disciplinaRequest.getSigla());
			disciplina.setTotalCargaHoraria(disciplinaRequest.getTotalCargaHoraria());
			disciplina.setPonderacao(disciplinaRequest.getPonderacao());
			disciplina.setCursoDeVerao(disciplinaRequest.isCursoDeVerao());
			
			disciplina.setTipo(disciplinaRequest.getTipo());

			disciplina.setCurso(curso);

			this.disciplinaRepository.save(disciplina);

			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(disciplina);
			c.setMensagem("Disciplina salvo com sucesso!");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} else {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setResultado(curso);
			c.setMensagem(" Essa disciplina já foi atribuida neste curso");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody DisciplinaRequest disciplinaRequest) {
		ResponseCliente c=new ResponseCliente();
		if(disciplinaRequest != null){
			Curso curso = this.cursoRepository.findByIdAndStatus(disciplinaRequest.getCurso(),true);
			Disciplina disciplina = this.disciplinaRepository.findOne(disciplinaRequest.getId());

			BeanUtils.copyProperties(disciplinaRequest,disciplina,"disciplina");
			disciplina.setCursoDeVerao(disciplinaRequest.isCursoDeVerao());
			disciplina.setCurso(curso);
			/*Mensagem de sucesso.*/
			c.setCodigo(ResponseCode.values()[0].getDescricao()); 
	    	c.setResultado(disciplina);
	    	c.setMensagem("Disciplina "+disciplina.getDescricao()+" atualizado com sucesso!"); 
	    	this.disciplinaRepository.save(disciplina);
			return  new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}else{
			c.setCodigo(ResponseCode.values()[3].getDescricao()); 
	    	c.setResultado(disciplinaRequest);
	    	c.setMensagem("A disciplina enviado não pode ser nullo!");
			return  new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
}