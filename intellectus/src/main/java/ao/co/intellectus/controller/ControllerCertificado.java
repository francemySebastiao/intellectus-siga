package ao.co.intellectus.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.RegistoCertificadoCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.RegistoCertificado;
import ao.co.intellectus.model.Usuario;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.cafold.AlunoService;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.cafold.RegistoCertificadoService;
import ao.co.intellectus.servico.cafold.UsuarioService;

@RestController
@RequestMapping("/certificado")
public class ControllerCertificado {

	@Autowired
	private AlunoService alunoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private RegistoCertificadoService registoCertificadoService;

	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody RegistoCertificadoCliente registo) {
		Aluno aluno = this.alunoService.aluno(registo.getNumerodoAluno());
		if (aluno == null) {
			return ObjectoDeRetornoParcial.MensagemDeRetorno(aluno, 2, "Nenhum registo de aluno de encontrado");
		} else {
			if(aluno.isFimCurso()) {
				Usuario usuario = usuarioService.usuario(registo.getUserCode());
				if (usuario == null) {
					return ObjectoDeRetornoParcial.MensagemDeRetorno(usuario, 2, "Erro ao validar usuário.");
				} else {
					RegistoCertificado registoCertificado = new RegistoCertificado();
					registoCertificado.setAluno(aluno);
					registoCertificado.setUsuarioRegistou(usuario);
					registoCertificado.setNumeroCertificado(registo.getNumeroDoCertificado());
					registoCertificado.setDataRegisto(new Date());
					return this.registoCertificadoService.salvar(registoCertificado);
				}
			}else {
				return ObjectoDeRetornoParcial.MensagemDeRetorno(aluno, 2, "Aluno não terminou o curso");
			}
		}
	}

}
