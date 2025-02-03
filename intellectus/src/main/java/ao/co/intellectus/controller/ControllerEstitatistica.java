package ao.co.intellectus.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.AgrupamentoDeCandidatos;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.CadidatosInscritos;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.CandidatoPorGrau;
import ao.co.intellectus.model.CandidatosSeriados;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.InscricaoAnual;
import ao.co.intellectus.model.InscricaoPorTipo;
import ao.co.intellectus.model.InscricoesAnuladas;
import ao.co.intellectus.model.InscritosPorAno;
import ao.co.intellectus.model.InscritosPorGrau;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.ProgInscritoPorCurso;
import ao.co.intellectus.model.ProgMatriculadoPorAnoCurricular;
import ao.co.intellectus.model.Sexo;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.Turno;
import ao.co.intellectus.model.estatistica.V_PROG_ESTATISTICA_MATRIZ_GLOBAL;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CadidatosInscritosRepository;
import ao.co.intellectus.repository.CandidatoPorGrauRepository;
import ao.co.intellectus.repository.CandidatoRepository;
import ao.co.intellectus.repository.CandidatosSeriadosRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.InscricaoAnualRepository;
import ao.co.intellectus.repository.InscricaoPorTipoRepository;
import ao.co.intellectus.repository.InscricoesAnuladasRepository;
import ao.co.intellectus.repository.InscritoPorAnoRepository;
import ao.co.intellectus.repository.InscritosPorGrauRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.ProgInscritoPorCursoRepository;
import ao.co.intellectus.repository.ProgMatriculadoPorAnoCurricularRepository;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.repository.estatistica.V_PROG_ESTATISTICA_MATRIZ_GLOBAL_REPOSITORY;
import ao.co.intellectus.servico.cafold.Conexao;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/estatistica")
public class ControllerEstitatistica {
	@Autowired
	private InscritoPorAnoRepository inscritoPorAnoRepository;
	@Autowired
	private InscricoesAnuladasRepository inscricoesAnuladasRepository;
	@Autowired
	private CadidatosInscritosRepository cadidatosInscritosRepository;
	@Autowired
	private CandidatosSeriadosRepository candidatosSeriadosRepository;
	@Autowired
	private CandidatoPorGrauRepository candidatoPorGrauRepository;
	@Autowired
	private InscritosPorGrauRepository inscritosPorGrauRepository;
	@Autowired
	private InscricaoPorTipoRepository inscricaoPorTipoRepository;
	@Autowired
	private InscricaoAnualRepository inscricaoAnualRepository;
	@Autowired
	private ProgMatriculadoPorAnoCurricularRepository progMatriculadoPorAnoCurricularRepository;
	@Autowired
	private ProgInscritoPorCursoRepository progInscritoPorCursoRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private CandidatoRepository repository;
	@Autowired
	private AnoLectivoRepository anoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private V_PROG_ESTATISTICA_MATRIZ_GLOBAL_REPOSITORY progEstatisticaMatrizGlobalRepository;
	

	public static Connection conectar() {
		Conexao con = new Conexao();
		return con.getConn();
	}
	
	@RequestMapping(value = "/inscritosPorAnoLectivo/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorCodigo(@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();
		InscritosPorAno anoLectivo = this.inscritoPorAnoRepository.findByAnoLectivo(ano);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(anoLectivo);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/inscritosAnuladasPorAnoLectivo/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> inscritosAnuladasPorAnoLectivo(@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();
		InscricoesAnuladas anoLectivo = this.inscricoesAnuladasRepository.findByAnoLectivo(ano);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(anoLectivo);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/candidatosInscritos/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> candidatosInscritos(@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();

		CadidatosInscritos anoLectivo = this.cadidatosInscritosRepository.findByAnoLectivo(ano);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(anoLectivo);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// CandidatosSeriadosRepository
	@RequestMapping(value = "/candidatosSeriados/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> candidatosSeriados(@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();

		CandidatosSeriados anoLectivo = this.candidatosSeriadosRepository.findByAnoLectivo(ano);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(anoLectivo);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// CandidatoPorGrauRepository
	@RequestMapping(value = "/candidatosPorGrau/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> candidatosPorGrau(@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();

		List<CandidatoPorGrau> anoLectivo = this.candidatoPorGrauRepository.findByAnoLectivo(ano);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(anoLectivo);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/inscritosPorGrau/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> inscritosPorGrau(@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();

		List<InscritosPorGrau> inscritos = this.inscritosPorGrauRepository.findByAno(ano);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(inscritos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	// inscricaoPorTipoRepository

	@RequestMapping(value = "/inscritosPorTipo/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> inscritosPorTipo(@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();

		List<InscricaoPorTipo> inscritos = this.inscricaoPorTipoRepository.findByAno(ano);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(inscritos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// estatistica/InacricoesNoAno
	@RequestMapping(value = "/InacricoesNoAno/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> InacricoesNoAno(@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();

		List<InscricaoAnual> inscritos = this.inscricaoAnualRepository.findByAno(ano);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(inscritos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// TOTAL DE ALUNOS POR ANO ACADEMICO
	@RequestMapping(value = "/inscritoPorAnoCurricular/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> inscritoPorAnoCurricular(@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();

		List<ProgMatriculadoPorAnoCurricular> inscritos = this.progMatriculadoPorAnoCurricularRepository
				.findByAnoLectivo(ano);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(inscritos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// TOTAL DE ALUNOS POR ANO ACADEMICO
	@RequestMapping(value = "/inscritoPorCurso/{ano}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> inscritoPorCurso(@PathVariable Integer ano) {
		ResponseCliente c = new ResponseCliente();

		List<ProgInscritoPorCurso> inscritos = this.progInscritoPorCursoRepository.findByAnoLectivo(ano);

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(inscritos);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// Número de Candidatos agrupados por curso num determinado ano
	@RequestMapping(value = "/buscarCandidatosPorCurso/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatobuscarPorCurso(@PathVariable Integer anoLectivo) {
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			Iterable<Curso> cursos = this.cursoRepository.findAll();
			if (cursos == null) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(cursos, 2, "Nenhum registro de curso encontrado");
			} else {
				List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
				for (Curso curso : cursos) {
					List<Candidato> candidatos = this.repository.findByCursoAndAnoLectivo(curso, ano);
					AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
					agrupamentoDeCandidatos.setCurso(curso.getDescricao());
					agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
					agrupamentoDeCandidatos.setAnoLectivo(ano.getAnoLectivo());
					todosAgrupamentos.add(agrupamentoDeCandidatos);
				}
				return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
			}
		}
	}

	// TOTAL DE CANDIDATOS POR CURSO E SEXO
	@RequestMapping(value = "/buscarCandidatoAdmitidosPorCurso/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatoAdmitidosPorCurso(@PathVariable Integer anoLectivo) {
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			Iterable<Curso> cursos = this.cursoRepository.findAll();
			if (cursos == null) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(cursos, 2, "Nenhum registro de curso encontrado");
			} else {
				List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
				for (Curso curso : cursos) {
					List<Candidato> candidatos = this.repository
							.findByAnoLectivoAndCursoAndNotaExameGreaterThanEqual(ano, curso, 10);
					AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
					agrupamentoDeCandidatos.setAnoLectivo(ano.getAnoLectivo());
					agrupamentoDeCandidatos.setCurso(curso.getDescricao());
					agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
					todosAgrupamentos.add(agrupamentoDeCandidatos);
				}
				return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
			}
		}
	}

	// TOTAL DE Candidatos POR ANO ACADEMICO
	@RequestMapping(value = "/buscarCandidatosPorTurno/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatobuscarPorTurnoA(@PathVariable Integer anoLectivo) {
		List<Turno> turnos = Arrays.asList(Turno.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
			for (Turno turno : turnos) {
				List<Candidato> candidatos = this.repository.findByTurnoAndAnoLectivo(turno, ano);
				AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
				agrupamentoDeCandidatos.setAnoLectivo(anoLectivo);
				agrupamentoDeCandidatos.setTurno(turno.getDescricao());
				agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
				todosAgrupamentos.add(agrupamentoDeCandidatos);
			}
			return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
		}
	}

	// TOTAL DE Candidatos ADMITIDOS POR ANO ACADEMICO
	@RequestMapping(value = "/buscarCandidatosAdmitidosPorTurno/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatoAdmitidosPorTurno(@PathVariable Integer anoLectivo) {
		List<Turno> turnos = Arrays.asList(Turno.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
			for (Turno turno : turnos) {
				List<Candidato> candidatos = this.repository.findByAnoLectivoAndTurnoAndNotaExameGreaterThanEqual(ano,
						turno, 10);
				AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
				agrupamentoDeCandidatos.setAnoLectivo(ano.getAnoLectivo());
				agrupamentoDeCandidatos.setTurno(turno.getDescricao());
				agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
				todosAgrupamentos.add(agrupamentoDeCandidatos);
			}
			return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
		}
	}

	// TOTAL DE Candidatos agrupados por curso e turno
	@RequestMapping(value = "/buscarCandidatosPorCursoETurno/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatoPorCursoETurno(@PathVariable Integer anoLectivo) {
		List<Turno> turnos = Arrays.asList(Turno.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
			for (Turno turno : turnos) {
				Iterable<Curso> cursos = this.cursoRepository.findAll();
				if (cursos == null) {
					return ObjectoDeRetornoParcial.MensagemDeRetorno(cursos, 2, "Nenhum registro de curso encontrado");
				} else {
					for (Curso curso : cursos) {
						List<Candidato> candidatos = this.repository.findByCursoAndTurnoAndAnoLectivo(curso, turno,
								ano);
						AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
						agrupamentoDeCandidatos.setAnoLectivo(anoLectivo);
						agrupamentoDeCandidatos.setTurno(turno.getDescricao());
						agrupamentoDeCandidatos.setCurso(curso.getDescricao());
						agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
						todosAgrupamentos.add(agrupamentoDeCandidatos);
					}
				}
			}
			return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
		}
	}

	// TOTAL DE Candidatos Admitidos por curso e turno
	@RequestMapping(value = "/buscarCandidatosAdmitidosPorCursoETurno/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatoAdmitidosPorCursoETurno(@PathVariable Integer anoLectivo) {
		List<Turno> turnos = Arrays.asList(Turno.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
			for (Turno turno : turnos) {
				Iterable<Curso> cursos = this.cursoRepository.findAll();
				if (cursos == null) {
					return ObjectoDeRetornoParcial.MensagemDeRetorno(cursos, 2, "Nenhum registro de curso encontrado");
				} else {
					for (Curso curso : cursos) {
						List<Candidato> candidatos = this.repository
								.findByAnoLectivoAndCursoAndTurnoAndNotaExameGreaterThanEqual(ano, curso, turno, 10);
						AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
						agrupamentoDeCandidatos.setAnoLectivo(ano.getAnoLectivo());
						agrupamentoDeCandidatos.setTurno(turno.getDescricao());
						agrupamentoDeCandidatos.setCurso(curso.getDescricao());
						agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
						todosAgrupamentos.add(agrupamentoDeCandidatos);
					}
				}
			}
			return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
		}
	}

	// TOTAL DE CANDIDATOS POR EXAME DIA DE DISCRIÇÃO
	// TOTAL DE Candidatos POR SEXO
	@RequestMapping(value = "/buscarCandidatosPorDia/{dataCandidatura}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatoPorSexoPorAno(@PathVariable String dataCandidatura) {
		try {
			SimpleDateFormat formatar = new SimpleDateFormat("dd-MM-yyyy");
			Date data = formatar.parse(dataCandidatura);
			List<Candidato> candidatos = this.repository.findByDataCandidatura(data);
			if (candidatos == null) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Nenhum registro encontrado.");
			} else {
				List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
				AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
				agrupamentoDeCandidatos.setDataDeCandidatura(dataCandidatura);
				agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
				todosAgrupamentos.add(agrupamentoDeCandidatos);
				return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
			}
		} catch (Exception erro) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "A data introduzida não é válida !.");
		}
	}

	// TOTAL DE Candidatos POR SEXO
	@RequestMapping(value = "/buscarCandidatosPorSexo/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatoPorSexoPorAno(@PathVariable Integer anoLectivo) {
		List<Sexo> sexos = Arrays.asList(Sexo.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
			for (Sexo sexo : sexos) {
				List<Candidato> candidatos = this.repository.findBySexoAndAnoLectivo(sexo, ano);
				AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
				agrupamentoDeCandidatos.setAnoLectivo(anoLectivo);
				agrupamentoDeCandidatos.setSexo(sexo.getDescricao());
				agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
				todosAgrupamentos.add(agrupamentoDeCandidatos);
			}
			return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
		}
	}

	// TOTAL DE Candidatos Admitidos POR SEXO
	@RequestMapping(value = "/buscarCandidatosAdmitidosPorSexo/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatoAdmitidosPorSexoPorAno(@PathVariable Integer anoLectivo) {
		List<Sexo> sexos = Arrays.asList(Sexo.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
			for (Sexo sexo : sexos) {
				List<Candidato> candidatos = this.repository.findByAnoLectivoAndSexoAndNotaExameGreaterThanEqual(ano,
						sexo, 10);
				AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
				agrupamentoDeCandidatos.setAnoLectivo(ano.getAnoLectivo());
				agrupamentoDeCandidatos.setSexo(sexo.getDescricao());
				agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
				todosAgrupamentos.add(agrupamentoDeCandidatos);
			}
			return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
		}
	}

	// TOTAL DE CANDIDATOS POR CURSO E SEXO
	@RequestMapping(value = "/buscarCandidatosPorSexoECurso/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatoPorCursoESexo(@PathVariable Integer anoLectivo) {
		List<Sexo> sexos = Arrays.asList(Sexo.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			Iterable<Curso> cursos = this.cursoRepository.findAll();
			if (cursos == null) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(cursos, 2, "Nenhum registro de curso encontrado");
			} else {
				List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
				for (Curso curso : cursos) {
					for (Sexo sexo : sexos) {
						List<Candidato> candidatos = this.repository.findBySexoAndCursoAndAnoLectivo(sexo, curso, ano);
						AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
						agrupamentoDeCandidatos.setAnoLectivo(anoLectivo);
						agrupamentoDeCandidatos.setCurso(curso.getDescricao());
						agrupamentoDeCandidatos.setSexo(sexo.getDescricao());
						agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
						todosAgrupamentos.add(agrupamentoDeCandidatos);
					}
				}
				return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
			}
		}
	}

	// TOTAL DE CANDIDATOS Admitidos POR CURSO E SEXO
	@RequestMapping(value = "/buscarCandidatosAdmitidosPorSexoECurso/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatoAdmitidosPorCursoESexo(@PathVariable Integer anoLectivo) {
		List<Sexo> sexos = Arrays.asList(Sexo.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			Iterable<Curso> cursos = this.cursoRepository.findAll();
			if (cursos == null) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(cursos, 2, "Nenhum registro de curso encontrado");
			} else {
				List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
				for (Curso curso : cursos) {
					for (Sexo sexo : sexos) {
						List<Candidato> candidatos = this.repository
								.findByAnoLectivoAndSexoAndCursoAndNotaExameGreaterThanEqual(ano, sexo, curso, 10);
						AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
						agrupamentoDeCandidatos.setAnoLectivo(ano.getAnoLectivo());
						agrupamentoDeCandidatos.setCurso(curso.getDescricao());
						agrupamentoDeCandidatos.setSexo(sexo.getDescricao());
						agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
						todosAgrupamentos.add(agrupamentoDeCandidatos);
					}
				}
				return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
			}
		}
	}

	// TOTAL DE CANDIDATOS Admitidos POR Turno e Sexo
	@RequestMapping(value = "/buscarCandidatosAdmitidosPorSexoETurno/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatosAdmitidosPorSexoETurno(@PathVariable Integer anoLectivo) {
		List<Sexo> sexos = Arrays.asList(Sexo.values());
		List<Turno> turnos = Arrays.asList(Turno.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
			for (Turno turno : turnos) {
				for (Sexo sexo : sexos) {
					List<Candidato> candidatos = this.repository
							.findByAnoLectivoAndSexoAndTurnoAndNotaExameGreaterThanEqual(ano, sexo, turno, 10);
					AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
					agrupamentoDeCandidatos.setAnoLectivo(ano.getAnoLectivo());
					agrupamentoDeCandidatos.setTurno(turno.getDescricao());
					agrupamentoDeCandidatos.setSexo(sexo.getDescricao());
					agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
					todosAgrupamentos.add(agrupamentoDeCandidatos);
				}
			}
			return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
		}
	}

	// TOTAL DE CANDIDATOS Admitidos POR Curso, Turno e Sexo
	@RequestMapping(value = "/buscarCandidatosAdmitidosPorCursoSexoETurno/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarCandidatosAdmitidosPorCursoSexoETurno(
			@PathVariable Integer anoLectivo) {
		List<Sexo> sexos = Arrays.asList(Sexo.values());
		List<Turno> turnos = Arrays.asList(Turno.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			Iterable<Curso> cursos = this.cursoRepository.findAll();
			if (cursos == null) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(cursos, 2, "Nenhum registro de curso encontrado");
			} else {
				List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
				for (Curso curso : cursos) {
					for (Sexo sexo : sexos) {
						for (Turno turno : turnos) {
							List<Candidato> candidatos = this.repository
									.findByAnoLectivoAndSexoAndCursoAndTurnoAndNotaExameGreaterThanEqual(ano, sexo,
											curso, turno, 10);
							AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
							agrupamentoDeCandidatos.setAnoLectivo(ano.getAnoLectivo());
							agrupamentoDeCandidatos.setTurno(turno.getDescricao());
							agrupamentoDeCandidatos.setCurso(curso.getDescricao());
							agrupamentoDeCandidatos.setSexo(sexo.getDescricao());
							agrupamentoDeCandidatos.setNumeroDeCandidatos(candidatos.size());
							todosAgrupamentos.add(agrupamentoDeCandidatos);
						}
					}
				}
				return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
			}
		}
	}

	// Estatística de Matrícula
	// Número de Estudantes matriculados por ano académico
	@RequestMapping(value = "/buscarEstudantesMatriculadosPorAno/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarEstudantesMatriculadosPorAno(@PathVariable Integer anoLectivo) {
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			List<Matricula> matriculas = this.matriculaRepository.findByAnoLectivo(ano);
			AgrupamentoDeCandidatos agrupamento = new AgrupamentoDeCandidatos();
			agrupamento.setNumeroDeCandidatos(matriculas.size());
			agrupamento.setAnoLectivo(ano.getAnoLectivo());
			return ObjectoDeRetornoParcial.MensagemDeRetorno(agrupamento, 0, null);
		}
	}

	// Número de Estudantes matriculados por Curso
	@RequestMapping(value = "/buscarEstudantesMatriculadosPorCurso/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarEstudantesMatriculadosPorCurso(@PathVariable Integer anoLectivo) {
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			Iterable<Curso> cursos = this.cursoRepository.findAll();
			if (cursos == null) {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(cursos, 2, "Nenhum registro de Curso encontrado");
			} else {
				List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
				for (Curso curso : cursos) {
					List<Matricula> matriculas = this.matriculaRepository.findByAnoLectivoAndCurso(ano, curso);
					AgrupamentoDeCandidatos agrupamentoDeCandidatos = new AgrupamentoDeCandidatos();
					agrupamentoDeCandidatos.setAnoLectivo(ano.getAnoLectivo());
					agrupamentoDeCandidatos.setCurso(curso.getDescricao());
					agrupamentoDeCandidatos.setNumeroDeCandidatos(matriculas.size());
					todosAgrupamentos.add(agrupamentoDeCandidatos);
				}
				return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
			}
		}
	}

	// Número de Estudantes matriculados por Turno
	@RequestMapping(value = "/buscarEstudantesMatriculadosPorTurno/{anoLectivo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarEstudantesMatriculadosPorTurno(@PathVariable Integer anoLectivo) {
		List<Turno> turnos = Arrays.asList(Turno.values());
		AnoLectivo ano = this.anoRepository.findByAnoLectivo(anoLectivo);
		if (ano == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(ano, 2, "Nenhum registro de ano encontrado");
		} else {
			List<AgrupamentoDeCandidatos> todosAgrupamentos = new ArrayList<AgrupamentoDeCandidatos>();
			for (Turno turno : turnos) {
				AgrupamentoDeCandidatos agrupamento = new AgrupamentoDeCandidatos();
				Iterable<Turma> turmas = this.turmaRepository.findByTurno(turno);
				Integer numeroDeMatriculas = 0;
				for (Turma turma : turmas) {
					if (this.matriculaRepository.findByTurmaBase(turma) != null) {
						numeroDeMatriculas+=1;
					}
				}
				agrupamento.setNumeroDeCandidatos(numeroDeMatriculas);
				agrupamento.setTurno(turno.getDescricao());
				todosAgrupamentos.add(agrupamento);
			}
			return ObjectoDeRetornoParcial.MensagemDeRetorno(todosAgrupamentos, 0, null);
		}
	}


	
	/*
	 * RELATÓRIOS QUE MOSTRA A MATRIZ GLOBAL DE ALUNOS INSCRITOS QUEBRADO POR CURSO E ANO CURRICULAR...
	 * DATA: 17/03/2020
	 * ANALISTAS: ERNESTO SAMBONGO & FRANCISCO DEVOPS
	 */
	@RequestMapping(value = "/matriz-global/{anoLectivo}/{grau}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> horarioTurma(@PathVariable Integer anoLectivo,@PathVariable String grau) throws Exception {		
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano_lectivo"  , anoLectivo);
		paramets.put("grau" , grau);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/academico/R_Matriz_Global.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}
	
	/*
	 * MOSTRA NA TELA A MATRIZ GLOBAL DE ALUNOS INSCRITOS QUEBRADO POR CURSO E ANO CURRICULAR...
	 * DATA: 17/03/2020
	 * ANALISTAS: ERNESTO SAMBONGO & FRANCISCO DEVOPS
	 */
	@RequestMapping(value = "/matriz-global-tela/{anoLectivo}/{grau}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> horarioTurmaTela(@PathVariable Integer anoLectivo,@PathVariable String grau) {
		ResponseCliente c = new ResponseCliente();	
		List<V_PROG_ESTATISTICA_MATRIZ_GLOBAL> matrizGlobal = this.progEstatisticaMatrizGlobalRepository.findByAnoLectivoAndGrau(anoLectivo, grau);
		
		c.setResultado(matrizGlobal);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	/*
	 * RELATÓRIOS QUE MOSTRA A MATRIZ GLOBAL DE ALUNOS INSCRITOS QUEBRADO POR CURSO E ANO CURRICULAR...
	 * DATA: 17/03/2020
	 * ANALISTAS: ERNESTO SAMBONGO & FRANCISCO DEVOPS
	 */
	
	@RequestMapping(value = "/alunos-em-divida/{ano}/{grau}/{valor}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> alunosEmDivida(@PathVariable Integer ano,@PathVariable String grau,@PathVariable String valor) throws Exception {		
		

		
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano"    , ano);
		paramets.put("grau"           , grau);
		paramets.put("valor", valor);
		
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/financeiro/R_Alunos_Dividas.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}
	
	@RequestMapping(value = "/alunos-bolseiros/{ano}/{grau}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> alunosBolseiros(@PathVariable Integer ano,@PathVariable String grau) throws Exception {		
		

	
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano"    , ano);
		paramets.put("grau"   , grau);
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/academico/R_Listagem_Bolseiros.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}
	
	@RequestMapping(value = "/alunos-bolseiros-por-empresa/{ano}/{grau}/{empresa}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> alunosBolseiros(@PathVariable Integer ano,@PathVariable String grau,@PathVariable String empresa) throws Exception {		
		
		
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("ano"    , ano);
		paramets.put("grau"   , grau);
		paramets.put("empresa"   , empresa);
		
		
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/academico/R_Listagem_Bolseiros_Por_Instituicao.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		byte[] relatrio = JasperExportManager.exportReportToPdf(jasperPrint);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}
}
