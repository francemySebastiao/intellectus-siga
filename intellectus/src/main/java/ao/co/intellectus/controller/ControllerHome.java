package ao.co.intellectus.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.Candidato;
import ao.co.intellectus.model.Curso;
import ao.co.intellectus.model.Permissao;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.TipoUsuario;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.repository.AlunoRepository;
import ao.co.intellectus.repository.AnoLectivoRepository;
import ao.co.intellectus.repository.CandidatoRepository;
import ao.co.intellectus.repository.CursoRepository;
import ao.co.intellectus.repository.PermissaoCursoRepository;
import ao.co.intellectus.repository.UsuarioRepository;

@RestController
public class ControllerHome {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CursoRepository cursoRepository;
	@Autowired
	private PermissaoCursoRepository permissaoCursoRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private CandidatoRepository candidatoRepository;
	@Autowired
	private AnoLectivoRepository anoLectivoRepository;
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> home(@RequestBody Usuario usuario) throws IOException {
		ResponseCliente c = new ResponseCliente();
		String mensagem="";
		Usuario u = new Usuario();
		
		c.setTipoUsuario(TipoUsuario.F);
		boolean eUmAluno=false;
		
		List<Candidato> candidatos = this.candidatoRepository.findBynumeroDocumentoAndAnoLectivoId(usuario.getUserName(), this.anoLectivoRepository.findByStatus(true).get(0).getId());
		
		if(candidatos.isEmpty()) {
			List<Aluno> aluno = this.alunoRepository.findByNumeroDocumento(usuario.getUserName());
			if(aluno.isEmpty()) {
				Aluno pego = this.alunoRepository.findByNumeroDeAluno(usuario.getUserName());
				if(pego!=null)
				  aluno.add(pego);
			}
			Usuario userExiste = this.usuarioRepository.findByUserName(usuario.getUserName());
			mensagem = userExiste ==null ? "Login replicado com sucesso." : "Bem-vindo ao sistema de gestão académica";
			//mensagem = userExiste ==null ? "Login replicado com sucesso." : "Feliz natal e um bom 2020";
			
			if(!aluno.isEmpty()) {
				eUmAluno=true;	
				c.setTipoUsuario(TipoUsuario.A);
				mensagem="Bem-vindo ao sistema de aluno";
				//mensagem="Feliz natal e um bom 2020";
			}
			
			
			if (userExiste == null && c.getTipoUsuario()==TipoUsuario.F) {
				BeanUtils.copyProperties(usuario, u);
				u.setDataCriacao(new Date());
				Usuario uSalvo = this.usuarioRepository.save(u);
				Iterable<Curso> cursos = this.cursoRepository.findAll();
				for (Curso curso : cursos) {
					PermissaoCurso pCurso=new PermissaoCurso();
					pCurso.setCurso(curso);
					pCurso.setUsuario(uSalvo);
					pCurso.setPermissao(Permissao.LEITURA);
					
					this.permissaoCursoRepository.save(pCurso);
				}
			}	
		}
		
		
		if(!candidatos.isEmpty()) {
			c.setTipoUsuario(TipoUsuario.C);
			eUmAluno=true;
		}
		
		
		c.setResultado(eUmAluno);
		c.setCodigo(ResponseCode.values()[0].getDescricao());
		c.setMensagem(mensagem);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
}
