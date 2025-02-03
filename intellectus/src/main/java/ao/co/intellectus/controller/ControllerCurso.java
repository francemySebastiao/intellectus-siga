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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.UsuarioAudit;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.CursoList;
import ao.co.intellectus.model.Faculdade;
import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.model.request.CursoRequest;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.FaculdadeRepository;
import ao.co.intellectus.repository.PermissaoCursoRepository;
import ao.co.intellectus.repository.UsuarioRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;


@RestController
@RequestMapping("/curso")
public class ControllerCurso { 

	@Autowired
	private CursoRepository repository;
	@Autowired
	private FaculdadeRepository faculdadeRepository;
	@Autowired
	private PermissaoCursoRepository permissaoCursoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public ControllerCurso(CursoRepository repository) {
		this.repository = repository;
	}


	@RequestMapping(value = "/todos", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listaCursos() {
		Iterable<Curso> cursos =  this.repository.findAll();
		if (cursos == null)
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhum Curso Encontrado.");
			
		return ObjectoDeRetornoParcial.MensagemDeRetorno(cursos, 0, null);
	}
	
	
	@RequestMapping(value = "/ativos", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> cursosActivos() {
		ResponseCliente c = new ResponseCliente();
		List<Curso> cursos = (List<Curso>) this.repository.findByStatus(true);
		List<Curso> planos = bidingDadosCurso(c, cursos);
		c.setResultado(planos);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ativos-equivalencia", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> cursosActivosEquivalencia() {
		ResponseCliente c = new ResponseCliente();
		List<Curso> cursos = (List<Curso>) this.repository.findByStatus(true);
		List<Curso> planos = bidingDadosCurso(c, cursos);
		c.setResultado(planos);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/permitido", method = RequestMethod.POST, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> permitido(@RequestBody UsuarioAudit usuario) {
		ResponseCliente c = new ResponseCliente();
		Usuario user = this.usuarioRepository.findByUserName(usuario.getUserName());
		List<PermissaoCurso> cursos = this.permissaoCursoRepository.findByUsuarioAndPermissaoAndCursoStatus(user, Permissao.GRAVAR,true);
		if (cursos.isEmpty()) {
			c.setMensagem("Nenhum curso Encontrado.");
		}
		List<Curso> planos=new ArrayList<Curso>();
		Curso curso;
       
		for (PermissaoCurso cc : cursos) {
			curso=new Curso();
			
			curso.setCodigo(cc.getCurso().getCodigo());
			curso.setDescricao(cc.getCurso().getPlano());
			curso.setDuracao(cc.getCurso().getDuracao());
			curso.setFaculdade(cc.getCurso().getFaculdade());
			curso.setGrau(cc.getCurso().getGrau());
			curso.setId(cc.getCurso().getId());
			curso.setInicio(cc.getCurso().getInicio());
			curso.setPlano(cc.getCurso().getPlano());
			curso.setQtdCadeiras(cc.getCurso().getQtdCadeiras());
			curso.setSigla(cc.getCurso().getSigla());
			curso.setStatus(cc.getCurso().isStatus());
		
			planos.add(curso);
		}
		c.setResultado(planos);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}


	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody CursoRequest cursoRequest) {
		ResponseCliente c = new ResponseCliente();

		try {

			Faculdade faculdade = cursoRequest.getFaculdade() != null
					? this.faculdadeRepository.findOne(cursoRequest.getFaculdade())
					: null;

			Curso pesquisadoPorCodigo = this.repository.findByCodigo(cursoRequest.getCodigo());
			Curso pesquisadoPorDescricao = this.repository.findByDescricao(cursoRequest.getDescricao());

			Curso curso = new Curso();

			if (pesquisadoPorCodigo == null) {
				if (pesquisadoPorDescricao == null) {
					BeanUtils.copyProperties(cursoRequest, curso, "faculdade");

					curso.setFaculdade(faculdade);

					this.repository.save(curso);

					c.setCodigo(ResponseCode.values()[0].getDescricao());
					c.setResultado(curso);
					c.setMensagem("Curso salvo com sucesso!");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				} else {
					c.setCodigo(ResponseCode.values()[3].getDescricao());
					c.setResultado(curso);
					c.setMensagem("Esse curso já existe.");
					return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
				}

			} else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setResultado(curso);
				c.setMensagem(" Já existe Curso com esse código.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		} catch (Exception ex) {
			// TODO: handle exception
		
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/listCursoIdAndDescricao")
	@CrossOrigin(origins = "*")
	public List<CursoList> IdAndDescricao() {

		List<CursoList> listaDeCursos = new ArrayList<CursoList>();

		Iterable<Curso> cursos = repository.findByStatus(true);
		CursoList c;
		for (Curso curso : cursos) {
			c = new CursoList();
			c.setDescricao(curso.getPlano());
			c.setPlano(curso.getDescricao());
			c.setId(curso.getId());
			c.setGrau(curso.getGrau());
			listaDeCursos.add(c);
		}
		return listaDeCursos;
	}
	
	@RequestMapping(value = "/buscarCursos")
	@CrossOrigin(origins = "*")
	public List<CursoList> buscarCursos(@RequestBody UsuarioAudit user) {
		
		List<CursoList> lista=new ArrayList<CursoList>();
		CursoList li;
		
		Iterable<Curso> todos = this.repository.findAll();
		for (Curso curso : todos) {
			li=new CursoList();
			PermissaoCurso permissao = this.permissaoCursoRepository.findByCursoIdAndUsuario(curso.getId(), this.usuarioRepository.findByUserName(user.getUserName()));
			
			if(permissao != null) {
				if(permissao.getPermissao()==Permissao.GRAVAR) {
					li.setDescricao(curso.getPlano());
					li.setGrau(curso.getGrau());
					li.setId(curso.getId());
					
					lista.add(li);
				}
			}
			
		}
		
		return lista;
	}
	
	
	

	@RequestMapping(value = "/todosID")
	@CrossOrigin(origins = "*")
	public Iterable<Curso> getIdAndDescricao() {
		return repository.ListSelectMenu();
	}

	
	@RequestMapping(value = "/buscarPorDescricao/{descricao}", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorDescricao(@PathVariable String descricao) {

		ResponseCliente c = new ResponseCliente();

		List<Curso> cursos = ((List<Curso>) repository.findByDescricaoLike("%" + descricao + "%"));
		if (cursos.isEmpty()) {
			c.setCodigo(ResponseCode.values()[2].getDescricao());
			c.setMensagem("Curso não encontrado.");
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
		c.setResultado(cursos);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarPorId/{id}", method = RequestMethod.GET, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> buscarPorDescricao(@PathVariable Integer id) {
		ResponseCliente c = new ResponseCliente();
		Curso curso = repository.findByIdAndStatus(id,true);

		c.setResultado(curso);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}

	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> atualizar(@RequestBody CursoRequest cursoRequest) {
		ResponseCliente c = new ResponseCliente();
		try {

			if (cursoRequest != null) {
				Faculdade faculdade = this.faculdadeRepository.findOne(cursoRequest.getFaculdade());

				Curso curso = this.repository.findByIdAndStatus(cursoRequest.getId(),true);
				// boolean guardian=cp.isSeriado();
				/* Atualizando todos os campos do curso editado. */
				BeanUtils.copyProperties(cursoRequest, curso, "curso");

				curso.setFaculdade(faculdade);
				// cp.setSeriado(guardian);
				/* Mensagem de sucesso. */
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(curso);
				c.setMensagem("Curso " + curso.getDescricao() + " atualizado com sucesso!");
				this.repository.save(curso);
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setResultado(cursoRequest);
				c.setMensagem("O curso enviado não pode ser nullo!");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}
		} catch (Exception ex) {
			// TODO: handle exception
		
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
	
	private List<Curso> bidingDadosCurso(ResponseCliente c, List<Curso> cursos) {
		if (cursos.isEmpty()) {
			c.setMensagem("Nenhum curso Encontrado.");
		}
		List<Curso> planos=new ArrayList<Curso>();
		Curso curso;
       
		for (Curso cc : cursos) {
			curso=new Curso();			
			curso.setCodigo(cc.getCodigo());
			curso.setDescricao(cc.getPlano());
			curso.setDuracao(cc.getDuracao());
			curso.setFaculdade(cc.getFaculdade());
			curso.setGrau(cc.getGrau());
			curso.setId(cc.getId());
			curso.setInicio(cc.getInicio());
			curso.setPlano(cc.getPlano());
			curso.setQtdCadeiras(cc.getQtdCadeiras());
			curso.setSigla(cc.getSigla());
			curso.setStatus(cc.isStatus());
			planos.add(curso);
		}
		return planos;
	}
}