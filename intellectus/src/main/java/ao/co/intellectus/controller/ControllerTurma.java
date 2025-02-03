package ao.co.intellectus.controller;

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

import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.Turma;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.model.reponse.ResponseCode;
import ao.co.intellectus.model.request.TurmaRequest;
import ao.co.intellectus.repository.InstituicaoRepository;
import ao.co.intellectus.repository.TurmaRepository;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;

@RestController
@RequestMapping("/turma")
public class ControllerTurma {
	@Autowired
	private TurmaRepository repositoryTurma;

	@Autowired
	private InstituicaoRepository repositorioInstituicao;

	@RequestMapping(value = "/todas", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listaTurma() {
		Iterable<Turma> turmas =  this.repositoryTurma.findAll();
		if (turmas == null) 
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Nenhuma turma Encontrada.");
		return ObjectoDeRetornoParcial.MensagemDeRetorno(turmas, 0, null);
	}
	
	
	@RequestMapping(value = "/porInstituicao/{codigo}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> porInstituicao(@PathVariable Integer codigo) {
		ResponseCliente c = new ResponseCliente();

		Instituicao instituicao = codigo     !=null ? this.repositorioInstituicao.findOne(codigo)        :null;
		List<Turma> turmas      = instituicao!=null ? this.repositoryTurma.findByInstituicao(instituicao):null;

		if (turmas.isEmpty()) {
			c.setMensagem("Nenhuma turma Encontrado.");
		}

		c.setResultado(turmas);
		c.setCodigo(200);
		return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
	}
	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> salvar(@RequestBody TurmaRequest turmaRequest) {
		ResponseCliente c = new ResponseCliente();

		try {
			turmaRequest.setInstituicao(1);
			Instituicao instituicao = turmaRequest.getInstituicao() != null
					? this.repositorioInstituicao.findOne(turmaRequest.getInstituicao())
					: null;

			Turma pesquisado = this.repositoryTurma.findByTurma(turmaRequest.getTurma());
			Turma turma = new Turma();
			if (pesquisado == null) {
				BeanUtils.copyProperties(turmaRequest, turma, "instituicao");
				turma.setInstituicao(instituicao);
				this.repositoryTurma.save(turma);
				c.setCodigo(ResponseCode.values()[0].getDescricao());
				c.setResultado(turma);
				c.setMensagem("Turma salvo com sucesso!");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			} else {
				c.setCodigo(ResponseCode.values()[3].getDescricao());
				c.setResultado(turma);
				c.setMensagem("Essa turma já existe.");
				return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
			}

		} catch (Exception ex) {
			// TODO: handle exception
			
			return new ResponseEntity<ResponseCliente>(c, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/actualizar", method = RequestMethod.PUT)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody TurmaRequest turmaRequest) {
		try {
			Instituicao instituicao =this.repositorioInstituicao.findOne(turmaRequest.getInstituicao());
			if(instituicao == null)
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Erro ao validar instituição");
		
			Turma pesquisado = this.repositoryTurma.findByTurma(turmaRequest.getTurma());
			if(pesquisado == null)
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Erro ao validar turma.");
			
			Turma turma = new Turma();
			BeanUtils.copyProperties(turmaRequest, turma, "instituicao");
			turma.setInstituicao(instituicao);
			this.repositoryTurma.save(turma);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 0, "Turma salva com sucesso.");
		
		} catch (Exception ex) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,"Teve erro em: " + ex.getCause());
		}
	}
	

}
