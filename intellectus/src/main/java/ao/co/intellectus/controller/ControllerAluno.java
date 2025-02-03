package ao.co.intellectus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.multipart.MultipartFile;

import ao.co.intellectus.DTO.AlunoCademico;
import ao.co.intellectus.DTO.AlunoCliente;
import ao.co.intellectus.DTO.AlunoGeral;
import ao.co.intellectus.DTO.BuscaAluno;
import ao.co.intellectus.DTO.CapturaImage;
import ao.co.intellectus.DTO.DisciplinaMatricula;
import ao.co.intellectus.DTO.DisciplinasInscritasCleinte;
import ao.co.intellectus.DTO.ResumoHistoricoNota;
import ao.co.intellectus.DTO.ServicoesModel;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAnexo;
import ao.co.intellectus.model.AnoLectivo;
import ao.co.intellectus.model.CertificadoIntermedio;
import ao.co.intellectus.model.CertificadoIntermedioMestrado;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.DeclaracoesUsuario;
import ao.co.intellectus.model.Disciplina;
import ao.co.intellectus.model.Grau;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.HistoricoAluno;
import ao.co.intellectus.model.HistoricoAlunoAudit;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.Municipio;
import ao.co.intellectus.model.Pais;
import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.Provincia;
import ao.co.intellectus.model.ServicosAuditoria;
import ao.co.intellectus.model.Situacao;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoAnexoRepository;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CertificadoIntermedioMestradoRepository;
import ao.co.intellectus.repository.CertificadoIntermedioRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.DeclaracoesUsuariorRepository;
import ao.co.intellectus.repository.DisciplinaRepository;
import ao.co.intellectus.repository.GuiaPagamentoRepository;
import ao.co.intellectus.repository.HistoricoAlunoAuditRepository;
import ao.co.intellectus.repository.HistoricoAlunoRepository;
import ao.co.intellectus.repository.MatriculaRepository;
import ao.co.intellectus.repository.MuniciopioRepository;
import ao.co.intellectus.repository.PaisRepository;
import ao.co.intellectus.repository.PermissaoCursoRepository;
import ao.co.intellectus.repository.ProvinciaRepository;
import ao.co.intellectus.repository.ServicosAuditoriaRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.cafold.AlunoService;
import ao.co.intellectus.servico.cafold.Conexao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
@RequestMapping("/aluno")
public class ControllerAluno {
	@Autowired
	private AlunoRepository repository;
	@Autowired
	private CursoRepository repositoryCurso;
	@Autowired
	private AlunoAnexoRepository alunoAnexoRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private CertificadoIntermedioRepository certificadoIntermedioRepository;
	@Autowired
	private CertificadoIntermedioMestradoRepository certificadoIntermedioMestradoRepo;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	@Autowired
	private PermissaoCursoRepository permissaoPorCursoRepository;
	@Autowired
	private MatriculaRepository matriculaRepository;
	@Autowired
	private HistoricoAlunoRepository historicoAlunoRepository;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private ServicosAuditoriaRepository servicosAuditoriaRepository;
	@Autowired
	private HistoricoAlunoAuditRepository historicoAlunoAuditRepository;
	@Autowired
	private DeclaracoesUsuariorRepository declaracoesUsuarioRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PaisRepository paisRepository;
	@Autowired
	private ProvinciaRepository provinciaRepository;
	@Autowired
	private MuniciopioRepository municipioRepository;
	//@Autowired
	//private HttpServletRequest httpServeletRequest;
	
	
	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> alunos() {
		ResponseCliente c = new ResponseCliente();

		List<Aluno> alunos = (List<Aluno>) repository.findAll();
		if (alunos.isEmpty()) {
			c.setMensagem("Nenhum aluno Encontrado.");
		}
		for (Aluno aluno : alunos) {
			aluno.setId(Integer.parseInt(aluno.getNumeroDeAluno()));
		}
		c.setResultado(alunos);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/alunos", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin(origins = "*")
	Page<Aluno> employeesPageable(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	@RequestMapping(value = "/buscarPorNome/{nome}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorNome(@PathVariable String nome) {
		List<Aluno> alunos = repository.findByNomeLike("%" + nome + "%");
		ResponseCliente c = new ResponseCliente();
		

		if (alunos == null) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		for (Aluno aluno : alunos) {
			aluno.setId(Integer.parseInt(aluno.getNumeroDeAluno()));
		}
	
	
		c.setResultado(alunos);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	// aluno/buscarPorNomeLike
	// MUDAR A PESQUISA DE NOME DE GET PARA POST QUANDO
	@RequestMapping(value = "/buscarPorNomeLike", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorNomeLike(@RequestBody BuscaAluno alunoBusca) {

		List<Aluno> alunos = repository.findByNomeLike(alunoBusca.getNome());

		ResponseCliente c = new ResponseCliente();

		if (alunos.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		//PermissaoCurso up = null;
		for (Aluno aluno : alunos) {

			//up = this.permissaoPorCursoRepository.findByCursoAndUsuarioUserName(aluno.getCurso(),alunoBusca.getUserName());
			
			aluno.setId(Integer.parseInt(aluno.getNumeroDeAluno()));
		}
		
		
		
		c.setPermissao(Permissao.LEITURA);
		c.setResultado(alunos);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorNomeAndNumero", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorNomeAndNumero(@RequestBody BuscaAluno alunoBusca) {
		List<Aluno> alunos = this.repository.findByNomeLikeOrNumeroDeAluno("%" + alunoBusca.getNome() + "%",
				alunoBusca.getNumero());
		ResponseCliente c = new ResponseCliente();

		if (alunos.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Nenhum registro encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		for (Aluno aluno : alunos) {
			aluno.setId(Integer.parseInt(aluno.getNumeroDeAluno()));
		}
		c.setResultado(alunos);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorId/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscar(@PathVariable String numero) {
		ResponseCliente c = new ResponseCliente();
		// try {
		Aluno aluno = repository.findByNumeroDeAluno(numero);

		
		
		if (aluno == null) {
			c.setMensagem("Nenhum Aluno encontrado.");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		//PermissaoCurso pu = this.permissaoPorCursoRepository.findByCursoAndUsuarioUserName(aluno.getCurso());

		AlunoCliente ac = new AlunoCliente();

		AlunoAnexo alunoAnexo = this.alunoAnexoRepository.findByAluno(aluno);

		if(alunoAnexo==null) {
			AlunoAnexo aAnexo=new AlunoAnexo();
			
			aAnexo.setAluno(aluno);
			aAnexo.setFoto(null);
			aAnexo.setNumeroDeAluno(aluno.getNumeroDeAluno());
			this.alunoAnexoRepository.save(aAnexo);
		}
		
		
		
		//BeanUtils.copyProperties(aluno, ac, "id", "cursoID", "DescricaoCurso");
		ac.setId(Integer.parseInt(aluno.getNumeroDeAluno()));
		ac.setCurso(aluno.getCurso().getId());
		ac.setDescricaoCurso(aluno.getCurso().getPlano());
		if(alunoAnexo!=null)
		ac.setFoto(alunoAnexo.getFoto());
		/*
		if (pu != null) {
		   aluno.setPermissao(pu.getPermissao());
		}
		*/
		
		aluno.setFoto(alunoAnexo.getFoto());// porra da foto.---> #Filho do assogueiro.

		// ac.setF
		/* SETAR OS VALORES DE RETORNO. */
		
		c.setResultado(aluno);
		
		/*
		if (pu != null) {
			c.setPermissao(pu.getPermissao());
		}
		*/
		c.setPermissao(Permissao.GRAVAR);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		/*
		 * } catch (Exception e) { c.setCodigo(ResponseCode.values()[1].getDescricao());
		 * c.setMensagem("Mensagem: " + e.getCause()); return new
		 * ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
		 */
	}
	
	@RequestMapping(value = "/buscarParaLancamento/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarParaLancamento(@PathVariable String numero) {
		ResponseCliente c = new ResponseCliente();
		// try {
		Aluno aluno = repository.findByNumeroDeAluno(numero);
		
		AlunoCademico alunoL=new AlunoCademico();

		if (aluno == null) {
			c.setMensagem("Nenhum Aluno encontrado.");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	

		AlunoAnexo alunoAnexo = this.alunoAnexoRepository.findByAluno(aluno);

		alunoL.setCurso(aluno.getCurso().getPlano());
		alunoL.setCursoID(aluno.getCurso().getId());
		alunoL.setFimCurso(aluno.isFimCurso());
		alunoL.setFoto(alunoAnexo.getFoto());
		alunoL.setGrau(aluno.getCurso().getGrau().getDescricao());
		alunoL.setNome(aluno.getNome());
		alunoL.setNumeroDeAluno(aluno.getNumeroDeAluno());
		
		
		aluno.setFoto(alunoAnexo.getFoto());

		
       //come
		List<Matricula> matriculas = this.matriculaRepository.findByAluno(aluno);
		
		List<Integer> inscricoes=new ArrayList<>();
		for (Matricula m : matriculas) {
			inscricoes.add(m.getAnoLectivo().getAnoLectivo());
		}
		alunoL.setInscricoes(inscricoes);
		
		//BUSCAR AS DISCIPLINAS DO CURSO
		
		List<DisciplinaMatricula> discisCurso=new ArrayList<DisciplinaMatricula>();
		DisciplinaMatricula disciCurso;
		
		
		//List<HistoricoAluno> historico = this.historicoAlunoRepository.findByAlunoAndCurso(aluno, aluno.getCurso());
		
		 List<HistoricoAluno> historico = this.historicoAlunoRepository.findByAluno(aluno);
		
		
		
		for (HistoricoAluno o : historico) {
			disciCurso=new DisciplinaMatricula();
			
			disciCurso.setDescricao(o.getDisciplina().getDescricao());
			disciCurso.setId(o.getDisciplina().getId());
			disciCurso.setAnoCorricular(o.getAnoCorricular());
			disciCurso.setAnoLectivo(o.getMatricula().getAnoLectivo().getAnoLectivo());
			disciCurso.setTipoDisciplina(o.getDisciplina().getTipo());
			
			discisCurso.add(disciCurso);
		}
		
		alunoL.setDisciplinas(discisCurso);
		
		
		c.setResultado(alunoL);
		c.setPermissao(Permissao.GRAVAR);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/buscarPorIdPermissao/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorIdPermissao(@PathVariable String numero, @PathVariable String userName) {
		ResponseCliente c = new ResponseCliente();
		// try {
		Aluno aluno = repository.findByNumeroDeAluno(numero);

		if (aluno == null) {
			c.setMensagem("Nenhum Aluno encontrado.");
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		PermissaoCurso pu = this.permissaoPorCursoRepository.findByCursoAndUsuarioUserName(aluno.getCurso(), userName);

		AlunoCliente ac = new AlunoCliente();

		AlunoAnexo alunoAnexo = this.alunoAnexoRepository.findByAluno(aluno);

		BeanUtils.copyProperties(aluno, ac, "id", "cursoID", "DescricaoCurso");
		ac.setId(Integer.parseInt(aluno.getNumeroDeAluno()));
		ac.setCurso(aluno.getCurso().getId());
		ac.setDescricaoCurso(aluno.getCurso().getPlano());
		ac.setFoto(alunoAnexo.getFoto());
		if (pu != null) {
		   aluno.setPermissao(pu.getPermissao());
		}
		
		aluno.setFoto(alunoAnexo.getFoto());// porra da foto.---> #Filho do assogueiro.

		// ac.setF
		/* SETAR OS VALORES DE RETORNO. */
		c.setResultado(aluno);
		if (pu != null) {
			c.setPermissao(pu.getPermissao());
		}

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		/*
		 * } catch (Exception e) { c.setCodigo(ResponseCode.values()[1].getDescricao());
		 * c.setMensagem("Mensagem: " + e.getCause()); return new
		 * ResponseEntity<ResponseCliente>(c, HttpStatus.OK); }
		 */
	}


	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody AlunoGeral aluno) {
		ResponseCliente c = new ResponseCliente();
		if (aluno.getId() == null) {
			c.setCodigo(ResponseCode.values()[3].getDescricao());
			c.setResultado(aluno);
			c.setMensagem("Não existe aluno com o referente codigo.");
		}
		String mensagem = "";

		Usuario usuario = this.usuarioRepository.findByUserCode(aluno.getUserCode());
	     
		List<AnoLectivo> anoLectivo = this.anoLectivoRepository.findByStatus(true);
		// curso
		Curso curso = repositoryCurso.findByIdAndStatus(aluno.getCurso(), true);
		//Curso curso = repositoryCurso.findById(aluno.getCurso());

		// alunoPasquisado
		Aluno alunoPesquisado = repository.findByNumeroDeAluno(aluno.getNumeroDeAluno());
		String observacao = alunoPesquisado.getObservacao();
		
		//DADOS PESSIAS(PAIS,PROVINCIA E MUNICIPIO)
		Pais nacionalidade            = aluno.getNacionalidade()      !=null ? this.paisRepository.findOne(aluno.getNacionalidade()): null;
		Pais paisResidencia           = aluno.getPaisDeResidencia()   !=null ? this.paisRepository.findOne(aluno.getPaisDeResidencia()):null;
		Provincia provincia           = aluno.getProvincia()          !=null ? this.provinciaRepository.findOne(aluno.getProvincia()):null;
		Provincia provinciaResidencia = aluno.getProvinciaResidencia()!=null ? this.provinciaRepository.findOne(aluno.getProvinciaResidencia()):null;
		Municipio municipio           = aluno.getMunicipio()          !=null ? this.municipioRepository.findOne(aluno.getMunicipio()): null;
		Municipio municipioResidencia = aluno.getMunicipioResidencia()!=null ? this.municipioRepository.findOne(aluno.getMunicipioResidencia()):null;
		

		// VERIFICAR GUIAS EM ABERTO.
		List<Guia> guiasAbertas = this.guiaRepository.findByAlunoAndLiquidadaAndAnulada(alunoPesquisado, false, false);
		mensagem = guiasAbertas.size() == 1 ? "existem uma guia não liquidada, impossivel fim curso"
				: "existem guias não liquidadas, impossivel fim curso";

		if (!guiasAbertas.isEmpty() && aluno.isFimCurso()) {
			c.setMensagem(mensagem);
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}

		// Verificar Todas as matriculas e validar as notas do historico acivas.
		List<Disciplina> qtdDisciplinas = this.disciplinaRepository.findByCursoAndStatus(curso, true);

		// List<HistoricoAluno> disciplinaContem=new ArrayList<HistoricoAluno>();
		int totalCursadas = 0;
		int totalCursadasMestrado = 0;
	
		int totalCurso = qtdDisciplinas.size();
		int totalCursoMestrado = qtdDisciplinas.size();

		// System.out.println("Total de disciplinas do curso: "+totalCurso);

		int somaDasNotas = 0;
		int somaDasNotasMestrado = 0;
		Double mediaFinal = null;
		Double mediaFinalMestrado = null;
		int totalCursoMestradoActual = 0;
		//int totalCursadasMestradoActual = 0;
		
		int soma = 0;

		//COMEÇO DA REALIDADE
		List<CertificadoIntermedio> cursadasConsocilidadas;
		List<CertificadoIntermedio> disciplinasCursadas;
		
		//MESTRADO
		List<CertificadoIntermedioMestrado> cursadasConsocilidadasMestrado;
		List<CertificadoIntermedioMestrado> disciplinasCursadasMestrado;
		
		List<Disciplina> disciplinaMestrado = new ArrayList<Disciplina>();
		
		HistoricoAluno hAluno;
		if(!alunoPesquisado.isFimCurso()) {
			
			if(alunoPesquisado.getCurso().getGrau() == Grau.MESTRADO) {
				disciplinaMestrado = this.disciplinaRepository.descricao(curso.getId());
				if(disciplinaMestrado.isEmpty()) {
					c.setMensagem("Aprovação disciplina é nula");
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}
			
			if (aluno.isFimCurso()) {
				// INICIALIZAR UM ARRAY VAZIU PARA APLICAR AS DISCIPLINAS DE FORMA A NÃO
				// REPETIR.
				cursadasConsocilidadas = new ArrayList<CertificadoIntermedio>();
				
				//MESTRADO
				cursadasConsocilidadasMestrado = new ArrayList<CertificadoIntermedioMestrado>();

				// BUSCA OS DADOS BRUTOS DA BASE PARA CONCILHAR COM A SEPARAÇÃO
				disciplinasCursadas = this.certificadoIntermedioRepository
						.findByNumeroAndValidacao(Integer.parseInt(alunoPesquisado.getNumeroDeAluno()), true);
				
				
				disciplinasCursadasMestrado = this.certificadoIntermedioMestradoRepo
						.findByNumeroAndValidacao(Integer.parseInt(aluno.getNumeroDeAluno()), true);

				// int i=0;
				for (CertificadoIntermedio it : disciplinasCursadas) {
					if (!cursadasConsocilidadas.contains(it)) {
						cursadasConsocilidadas.add(it);
					}
				}
				
				// MESTRADO
				for (CertificadoIntermedioMestrado mest : disciplinasCursadasMestrado) {
					if (!cursadasConsocilidadasMestrado.contains(mest)) {
						cursadasConsocilidadasMestrado.add(mest);
					}
				}

				// ELIMINA REPETIÇÃO DE NOTAS PARA CALCULAR A NOTA FINAL.
				for (CertificadoIntermedio ci : cursadasConsocilidadas) {
					somaDasNotas += ci.getNotaFinal();
				}
				
				// MESTRADO
				int disciplinaNull = 0;
				for (CertificadoIntermedioMestrado cim : cursadasConsocilidadasMestrado) {
					if(cim.getNotaFinal() != null) {
						somaDasNotasMestrado += cim.getNotaFinal();
						
					}else {

						disciplinaNull += 1;
					}
				}

				totalCursoMestradoActual = totalCursoMestrado - disciplinaNull;
				
				totalCursadasMestrado = disciplinasCursadasMestrado.size();
				
				//totalCursadasMestradoActual = totalCursadasMestrado - disciplinaNull;
				
				soma = totalCursoMestradoActual - totalCursadasMestrado;
				
				mediaFinal = (double) somaDasNotas / totalCurso;
				
				mediaFinalMestrado = (double) somaDasNotasMestrado / (totalCursoMestrado - soma);
				
				if(soma == 5) {
					
				int i = 0;	
					for (Disciplina disciplina : disciplinaMestrado) {
						
						hAluno = historicoAlunoRepository.NumeroDeAlunoAndDisciplinaId(aluno.getNumeroDeAluno(), disciplina.getId());
						hAluno.setAprovado(true);
						hAluno.setSituacao(Situacao.APROVADO);
						hAluno.setValidada(true);
						historicoAlunoRepository.save(hAluno);
						
						i=i+1;
					}
					
					totalCursadasMestrado = totalCursadasMestrado + i;
				}
				totalCursadas = disciplinasCursadas.size();
			}
			// DISCIPLINAS NÃO FINALIZADAS.
			if (aluno.isFimCurso() == true && alunoPesquisado.getCurso().getGrau() == Grau.LICENCIATURA && totalCurso > totalCursadas) {
				mensagem = "Falta finalizar disciplinas Licenciatura: " + totalCursadas + "/" + totalCurso;
				c.setMensagem(mensagem);
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			
			} else if (aluno.isFimCurso() == true && alunoPesquisado.getCurso().getGrau() == Grau.MESTRADO &&  totalCursoMestradoActual > totalCursadasMestrado) {
				
				//System.out.println("Mais aqui" + totalCursadasMestrado);
				mensagem = "Falta finalizar disciplinas mestrado: " + totalCursadasMestrado + "/" + totalCursoMestradoActual;
				c.setMensagem(mensagem);
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}else if(aluno.isFimCurso() == true && alunoPesquisado.getCurso().getGrau() == Grau.CET && totalCurso > totalCursadas) {
				mensagem = "Falta finalizar disciplinas CET: " + totalCursadas + "/" + totalCurso;
				c.setMensagem(mensagem);
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}else if(aluno.isFimCurso() == true && alunoPesquisado.getCurso().getGrau() == Grau.CESE && totalCurso > totalCursadas) {
				
				mensagem = "Falta finalizar disciplinas CESE: " + totalCursadas + "/" + totalCurso;
				c.setMensagem(mensagem);
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}else if(aluno.isFimCurso() == true && alunoPesquisado.getCurso().getGrau() == Grau.POSGRADUACAO && totalCurso > totalCursadas) {
				
				mensagem = "Falta finalizar disciplinas POSGRADUAÇÃO: " + totalCursadas + "/" + totalCurso;
				c.setMensagem(mensagem);
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			
			// DISCIPLINAS NÃO FINALIZADAS.
			if (aluno.isFimCurso() == true && alunoPesquisado.getCurso().getGrau() == Grau.MESTRADO &&  totalCursoMestradoActual > totalCursadasMestrado) {
				mensagem = "Falta finalizar disciplinas mestrado: " + totalCursadasMestrado + "/" + totalCursoMestradoActual;
				c.setMensagem(mensagem);
				c.setCodigo(ResponseCode.values()[2].getDescricao());
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
			
			if (aluno.isFimCurso() && alunoPesquisado.getCurso().getGrau() == Grau.LICENCIATURA && totalCursadas >= totalCurso) { 
				if (totalCursadas == totalCurso) {
					alunoPesquisado.setMediaFinal(Math.round(mediaFinal));
					alunoPesquisado.setDataFimDoCurso(aluno.getDataFimDoCurso());
					alunoPesquisado.setAnoFimDoCurso(anoLectivo.get(0));
					
				} else {
					mensagem = "Existem disciplinas repetidas licenciatura: " + totalCursadas + " / " + totalCurso;
					c.setMensagem(mensagem);
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}else if(aluno.isFimCurso() && alunoPesquisado.getCurso().getGrau() == Grau.MESTRADO && totalCursadasMestrado >= totalCursoMestradoActual) {
				
				if (totalCursadasMestrado == totalCursoMestradoActual) {
					alunoPesquisado.setMediaFinal(Math.round(mediaFinalMestrado));
					alunoPesquisado.setDataFimDoCurso(aluno.getDataFimDoCurso());
					alunoPesquisado.setAnoFimDoCurso(anoLectivo.get(0));
					
				} else {
					mensagem = "Existem disciplinas repetidas mestrado: " + totalCursadasMestrado + " / " + totalCursoMestradoActual;
					c.setMensagem(mensagem);
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}else if(aluno.isFimCurso() && alunoPesquisado.getCurso().getGrau() == Grau.CET && totalCursadas >= totalCurso) {
				
				if (totalCursadas == totalCurso) {
					alunoPesquisado.setMediaFinal(Math.round(mediaFinal));
					alunoPesquisado.setDataFimDoCurso(aluno.getDataFimDoCurso());
					alunoPesquisado.setAnoFimDoCurso(anoLectivo.get(0));
					
				} else {
					mensagem = "Existem disciplinas repetidas CET: " + totalCursadas + " / " + totalCurso;
					c.setMensagem(mensagem);
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}else if(aluno.isFimCurso() && alunoPesquisado.getCurso().getGrau() == Grau.CESE && totalCursadas >= totalCurso) {
				
				if (totalCursadas == totalCurso) {
					alunoPesquisado.setMediaFinal(Math.round(mediaFinal));
					alunoPesquisado.setDataFimDoCurso(aluno.getDataFimDoCurso());
					alunoPesquisado.setAnoFimDoCurso(anoLectivo.get(0));
					
				} else {
					mensagem = "Existem disciplinas repetidas CESE: " + totalCursadas + " / " + totalCurso;
					c.setMensagem(mensagem);
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}else if(aluno.isFimCurso() && alunoPesquisado.getCurso().getGrau() == Grau.POSGRADUACAO && totalCursadas >= totalCurso) {
				
				if (totalCursadas == totalCurso) {
					alunoPesquisado.setMediaFinal(Math.round(mediaFinal));
					alunoPesquisado.setDataFimDoCurso(aluno.getDataFimDoCurso());
					alunoPesquisado.setAnoFimDoCurso(anoLectivo.get(0));
					
				} else {
					mensagem = "Existem disciplinas repetidas POSGRADUAÇÃO: " + totalCursadas + " / " + totalCurso;
					c.setMensagem(mensagem);
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}else if(aluno.isFimCurso() && totalCursadas >= totalCurso) {
				
				if (totalCursadas == totalCurso) {
					alunoPesquisado.setMediaFinal(Math.round(mediaFinal));
					alunoPesquisado.setDataFimDoCurso(aluno.getDataFimDoCurso());
					alunoPesquisado.setAnoFimDoCurso(anoLectivo.get(0));
				}else {
					
					mensagem = "Existe disciplinas repetidas: " + totalCursadas + " / " + totalCurso;
					c.setMensagem(mensagem);
					c.setCodigo(ResponseCode.values()[2].getDescricao());
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}
			}
		}
		///FECHO DO TRATO VERIDICO
		this.alunoService.gerarHistorico(alunoPesquisado);
		/* Atualizando todos os campos do candidato editado. */
		BeanUtils.copyProperties(aluno, alunoPesquisado, "curso", "id", "nacionalidade", "provincia", "municipio","paisDeResidencia", "provinciaResidencia", "municipioResidencia", "anoFimDoCurso", "instituicao");
		alunoPesquisado.setCurso(alunoPesquisado.getCurso());
		alunoPesquisado.setNacionalidade(nacionalidade);
		alunoPesquisado.setPaisDeResidencia(paisResidencia);
		alunoPesquisado.setProvincia(provincia);
		alunoPesquisado.setProvinciaResidencia(provinciaResidencia);
		// alunoPesquisado.setInstituicao(instituicao);
		alunoPesquisado.setMunicipio(municipio);
		alunoPesquisado.setMunicipioResidencia(municipioResidencia);
		alunoPesquisado.setTelefone(aluno.getTelefone());
		alunoPesquisado.setTelefone1(aluno.getTelefone1());
		alunoPesquisado.setTelefone2(aluno.getTelefone2());
		alunoPesquisado.setUsuario(usuario);
		// PERMISSÃO DE DEIXAR NO CONTENCIOSO
		alunoPesquisado.setContencioso(aluno.isContencioso());

		// PERMISSÃO DE INATIVAR UM ALUNO
		if (aluno.isInactivo()) {
			alunoPesquisado.setInactivo(aluno.isInactivo());
			alunoPesquisado.setDataInativo(new Date());
		}

		alunoPesquisado.setDataEmissaoDocumento(aluno.getDataEmissaoDocumento());

		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setResultado(alunoPesquisado);
		c.setMensagem("Aluno atualizado com sucesso!");
		alunoPesquisado.setUltimaModificacao(new Date());
		if (aluno.getObservacao()==null) {
			aluno.setObservacao("Nao observado");
		}
		
		String observacaoGerada= observacao!=null ? observacao+" - DATA: "+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "; FUNCIONÁRIO: "+usuario.getName() +" => "+ aluno.getObservacao(): "DATA: "+ new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "; FUNCIONÁRIO: "+usuario.getName() +" => "+ aluno.getObservacao() ;
	
		alunoPesquisado.setObservacao(observacaoGerada);
		repository.save(alunoPesquisado);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/fichaInscricao/{numero}/{anoLectivo}", method = RequestMethod.GET)
	public void relatorio(HttpServletResponse response, @PathVariable String numero, @PathVariable Integer anoLectivo)
			throws JRException, IOException {
		InputStream inputStream = new FileInputStream((new File("E:/Ficha_inscricao.jrxml")));
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codigo_ano_lectivo", anoLectivo);
		map.put("numero_matricula", numero);

		// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
		// conectar());
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conectar());
		// Configura a respota para o tipo PDF
		response.setContentType("application/pdf");
		// Define que o arquivo pode ser visualizado no navegador e também nome final do
		// arquivo
		// para fazer download do relatório troque 'inline' por 'attachment'
		response.setHeader("Content-Disposition", "inline; filename=listaCurso.pdf");
		// Faz a exportação do relatório para o HttpServletResponse
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}

	public static Connection conectar() {

		Conexao con = new Conexao(); 
		return con.getConn();
	}

	@GetMapping("/relatorios/ficha-inscricao")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioFicha(@RequestParam Integer numeroAluno, @RequestParam Integer anoLectivo)
			throws Exception {
		byte[] relatrio = relatorioPorFichaInscricao(numeroAluno, anoLectivo);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] relatorioPorFichaInscricao(Integer id, Integer anoLectivo) throws JRException {

		Date data = new Date();
		Locale local = new Locale("pt", "BR");
		DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", local);

		Map<String, Object> paramets = new HashMap<>();
		paramets.put("codigo_ano_lectivo", anoLectivo);
		paramets.put("numero_matricula", id);
		paramets.put("data", formato.format(data));

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/Ficha_inscricao.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/relatorios/ficha-aluno")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioFichaAluno(@RequestParam Integer id) throws Exception {
		byte[] relatrio = servicoFichaAluno(id);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoFichaAluno(Integer id) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_aluno", id);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Ficha_Aluno.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@GetMapping("/relatorios/ficha-academico")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> relatorioFichaAcademico(@RequestParam Integer id) throws Exception {
		byte[] relatrio = servicoFichaAcademico(id);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] servicoFichaAcademico(Integer id) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_matricula", id);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/R_Ficha_Academica.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@RequestMapping(value = "/anexo", method = RequestMethod.PUT)
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> uploadFoto(@RequestParam MultipartFile file, @RequestParam String idAluno)
			throws IOException {
		ResponseCliente c = new ResponseCliente();
		//System.err.println("CONTENT TYPE: "+this.httpServeletRequest.getContentType());
		try {
			
			String mensagem = "";
			Aluno aluno = repository.findByNumeroDeAluno(idAluno);
			AlunoAnexo fotoAluno = this.alunoAnexoRepository.findByAluno(aluno);
			mensagem = fotoAluno.getFoto() != null ? "Foto actualizado com sucesso!" : "Foto adicionado com sucesso!";

			fotoAluno.setAluno(aluno);
			fotoAluno.setFoto(file.getBytes());
			c.setMensagem(mensagem);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(fotoAluno);

			alunoAnexoRepository.save(fotoAluno);
	
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@GetMapping("/imprimir/cartao")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<byte[]> imprimirCartaoAluno(@RequestParam String numeroAluno,
			@RequestParam Integer anoLectivo) throws Exception {
		byte[] relatrio = imprimirCartaoAlunoServico(numeroAluno, anoLectivo);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE).body(relatrio);
	}

	public byte[] imprimirCartaoAlunoServico(String numeroAluno, Integer anoLectivo) throws JRException {
		Map<String, Object> paramets = new HashMap<>();
		paramets.put("numero_aluno", numeroAluno);
		paramets.put("codigo_ano_lectivo", anoLectivo);
		InputStream inputStream = this.getClass().getResourceAsStream("/relatorio/F_Cartao.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, paramets, conectar());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	@RequestMapping(value = "/capturar", method = RequestMethod.PUT)
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> capturarFoto(@RequestBody CapturaImage foto) throws IOException {
		ResponseCliente c = new ResponseCliente();
		try {

			String mensagem = "";
			Aluno aluno = repository.findByNumeroDeAluno(foto.getIdAluno());
			AlunoAnexo fotoAluno = this.alunoAnexoRepository.findByAluno(aluno);
			mensagem = fotoAluno.getFoto() != null ? "Foto actualizado com sucesso!" : "Foto adicionado com sucesso!";

			fotoAluno.setAluno(aluno);
			fotoAluno.setFoto(foto.getFoto());
			c.setMensagem(mensagem);
			c.setCodigo(ResponseCode.values()[0].getDescricao());
			c.setResultado(fotoAluno);

			alunoAnexoRepository.save(fotoAluno);
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/servico/auditoria/{numeroAluno}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> servicoAuditoria(@PathVariable String numeroAluno) { 	
		ResponseCliente c = new ResponseCliente();
		
		Aluno aluno = this.alunoService.aluno(numeroAluno);
		
        List<ServicosAuditoria> servicos = this.servicosAuditoriaRepository.findByEstado(true);
		
        ServicoesModel sModel=new ServicoesModel();
        
        sModel.setContencioso(aluno.isContencioso());
        sModel.setFimCurso(aluno.isFimCurso());
        sModel.setInativo(aluno.isInactivo());
        sModel.setCurso(aluno.getCurso().getPlano());
        sModel.setGrau(aluno.getCurso().getGrau().toString());
        sModel.setNome(aluno.getNome());
        sModel.setNumeroDeAluno(Integer.parseInt(numeroAluno));
        
        sModel.setServicosAuditoria(servicos); 
        
        c.setResultado(sModel);
        c.setCodigo(200);
        return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/servico/auditoria/lancamentoNota/{numeroAluno}/{anoCurricular}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> servicoAuditoria(@PathVariable String numeroAluno,@PathVariable Integer anoCurricular) { 	
		ResponseCliente c = new ResponseCliente();
		
		Aluno aluno = this.alunoService.aluno(numeroAluno);
		
		
		List<HistoricoAluno> historico = this.historicoAlunoRepository.findByAlunoAndDisciplinaAnoCorricular(aluno, anoCurricular);
		
		DisciplinasInscritasCleinte disciplina;
		List<DisciplinasInscritasCleinte> disciplinas=new ArrayList<DisciplinasInscritasCleinte>();
		
		for (HistoricoAluno o : historico) {
			disciplina=new DisciplinasInscritasCleinte();
			disciplina.setId(o.getDisciplina().getId());
			disciplina.setDescricao(o.getDisciplina().getDescricao());
			disciplina.setCodigoMatricula(o.getDisciplina().getAnoCorricular());
			disciplinas.add(disciplina);
		}
        c.setResultado(disciplinas);
        c.setCodigo(200);
        return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value = "/servico/auditoria/notaGeral/{numeroAluno}/{disciplina}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> servicoAuditoriaNotaGeral(@PathVariable String numeroAluno,@PathVariable Integer disciplina) { 	
		ResponseCliente c = new ResponseCliente();
		
		List<ResumoHistoricoNota> resumos=new ArrayList<ResumoHistoricoNota>();
		ResumoHistoricoNota resumo;
		List<HistoricoAlunoAudit> historico = this.historicoAlunoAuditRepository.findByNumeroDeAlunoAndDisciplinaId(numeroAluno, disciplina);
		for (HistoricoAlunoAudit o : historico) {
			resumo=new ResumoHistoricoNota();
			
			//PRIMEIRA AVALIAÇÃO
			
			//resumos.contains(historico);
			
			resumo.setAccao("Lançamento de nota "+o.getAvaliacao1());
			resumo.setNota(o.getAvaliacao1());
			resumo.setDisciplina(o.getDisciplina().getDescricao());
			resumo.setDataAlteracao(o.getDataPrimeiraFrequencia());
			if(o.getUsuarioPrimeiraFrequencia()!=null)
			resumo.setUsuario(o.getUsuarioPrimeiraFrequencia().getName());
			
			if(!resumos.contains(resumo)) {
				resumos.add(resumo);				
			}	
		}
        c.setResultado(resumos);
        c.setCodigo(200);
        return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	//declaracoesUsuarioRepository
	
	@RequestMapping(value = "/declaracaoUsuario/{usuario}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> declaracaoUsuario(@PathVariable Integer usuario) { 	
		ResponseCliente c = new ResponseCliente();
		
		
		DeclaracoesUsuario userGood = this.declaracoesUsuarioRepository.findByUsuario(usuario);
		
        c.setResultado(userGood);
        c.setCodigo(200);
        return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listaDeclaracaoUsuario/{usuario}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listaDeclaracaoUsuario(@PathVariable Integer usuario) { 	
		ResponseCliente c = new ResponseCliente();
		
		
		DeclaracoesUsuario userGood = this.declaracoesUsuarioRepository.findByUsuario(usuario);
		
        c.setResultado(userGood);
        c.setCodigo(200);
        return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}