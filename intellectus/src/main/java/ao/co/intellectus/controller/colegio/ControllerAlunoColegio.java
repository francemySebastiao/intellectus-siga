package ao.co.intellectus.controller.colegio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ao.co.intellectus.DTO.colegio.AlunoColegio;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.cafold.AlunoService;
import ao.co.intellectus.servico.cafold.TurmaService;
import ao.co.intellectus.servico.notificacoes.HttpResponse;

@RestController
@RequestMapping("/colegio/alunos")
public class ControllerAlunoColegio {

	@Autowired
	private HttpResponse notificacao;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private TurmaService turmaService;

	@GetMapping(value = "/todos", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todos() {
		return this.notificacao.MensagemDeRetorno(0, this.alunoService.todos());
	}

	@PostMapping(value = "/salvar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> salvar(@RequestBody AlunoColegio alunoColegio) {
		
		Aluno aluno = this.alunoService.salvar(alunoColegio);
		
		return this.notificacao.MensagemDeRetorno(0,aluno);
	}

	@PutMapping(value = "/actualizar", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> actualizar(@RequestBody AlunoColegio alunoColegio) {
		
		//PORQUE ESSE SERVIÇO ESTÁ A DAR ERRO DEVOPS ??????????????????? 
		//this.alunoService.actualizar(alunoColegio);
		return this.notificacao.MensagemDeRetorno(0, "Aluno actulizado com sucesso.");
	}
	
	@GetMapping(value = "/pesquisarPorNumero/{numero}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pesquisarPorNumero(@PathVariable String numero) {
		return this.notificacao.MensagemDeRetorno(0, this.alunoService.aluno(numero));
	}
	
	@GetMapping(value = "/pesquisarPorNome/{nome}", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> pesquisarPorNome(@PathVariable String nome) {
		return this.notificacao.MensagemDeRetorno(0, this.alunoService.alunos(nome));
	}
	
	@GetMapping(value = "/turmas", produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> turmas() {
		return this.notificacao.MensagemDeRetorno(0, this.turmaService.todas());
	}

	// Curso com as suas respectivas turmas e vice-versa
}
