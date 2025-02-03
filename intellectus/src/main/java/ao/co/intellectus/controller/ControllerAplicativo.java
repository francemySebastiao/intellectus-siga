package ao.co.intellectus.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;

import ao.co.intellectus.DTO.AlunoResumoSituacao;
import ao.co.intellectus.DTO.DisciplinaCursandoCliente;
import ao.co.intellectus.DTO.EmolumentoCliente;
import ao.co.intellectus.DTO.GuiaLiquidadaCliente;
import ao.co.intellectus.DTO.InscricaoPorAno;
import ao.co.intellectus.DTO.SituacaoAcademicaCliente;
import ao.co.intellectus.DTO.TodasGuiasCliente;
import ao.co.intellectus.model.Aluno;
import ao.co.intellectus.model.AlunoAnexo;
import ao.co.intellectus.model.Guia;
import ao.co.intellectus.model.GuiaEmAbertoCliente;
import ao.co.intellectus.model.Matricula;
import ao.co.intellectus.model.PermissaoCurso;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.repository.AlunoAnexoRepository;
import ao.co.intellectus.servico.cafold.AlunoService;
import ao.co.intellectus.servico.cafold.DisciplinaService;
import ao.co.intellectus.servico.cafold.EmailService;
import ao.co.intellectus.servico.cafold.EmolumentoService;
import ao.co.intellectus.servico.cafold.GuiaEmAbertoService;
import ao.co.intellectus.servico.cafold.GuiaLiquidadaService;
import ao.co.intellectus.servico.cafold.GuiaService;
import ao.co.intellectus.servico.cafold.InscricaoService;
import ao.co.intellectus.servico.cafold.MatriculasService;
import ao.co.intellectus.servico.cafold.ObjectoDeRetornoParcial;
import ao.co.intellectus.servico.cafold.PermissaoService;
import ao.co.intellectus.servico.cafold.TodasGuiasService;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import ao.co.intellectus.servico.notificacoes.HttpResponse;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/aplicativo")
public class ControllerAplicativo {
	@Autowired
	private AlunoAnexoRepository alunoAnexoRepository;
	//@Autowired
	//private GuiaPagamentoRepository guiaRepository;
	@Autowired
	private MatriculasService matriculasService;
	@Autowired
	private InscricaoService inscricaoService;
	@Autowired
	private DisciplinaService disciplinaService;
	@Autowired
	private PermissaoService permissaoService;
	@Autowired
	private GuiaLiquidadaService guiaLiquidadaService;
	@Autowired
	private GuiaEmAbertoService guiaEmAbertoService;
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private TodasGuiasService todasGuiaService;
	@Autowired
	private EmolumentoService emolumentoService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private GuiaService guiaService;
	@Autowired
	private HttpResponse notificacaoService;
	private String assunto, mensagem;


	@RequestMapping(value = "/loginAPP/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> login(@PathVariable String numero, @PathVariable String userName) {
		Aluno aluno = this.alunoService.aluno(numero);
		AlunoResumoSituacao dadosDoAluno = new AlunoResumoSituacao();
		AlunoAnexo fotoAluno = this.alunoAnexoRepository.findByAlunoNumeroDeAluno(aluno.getNumeroDeAluno());
		PermissaoCurso permisaoCurso = this.permissaoService.permissaoAoCurso(numero, userName);
		dadosDoAluno.setNome(aluno.getNome());
		dadosDoAluno.setCurso(aluno.getCurso().getPlano());
		dadosDoAluno.setCodigoCurso(aluno.getCurso().getId());
		dadosDoAluno.setNumero(Integer.parseInt(aluno.getNumeroDeAluno()));
		dadosDoAluno.setEmail(aluno.getEmail());
		dadosDoAluno.setTelefone(aluno.getTelefone());
		dadosDoAluno.setContencioso(aluno.isContencioso());
		dadosDoAluno.setAnulado(aluno.isInactivo());
		dadosDoAluno.setFimCurso(aluno.isFimCurso());
		dadosDoAluno.setFoto(fotoAluno.getFoto());
		dadosDoAluno.setGrau(aluno.getCurso().getGrau().getDescricao());
		return this.notificacaoService.MensagemDeRetorno(0, null, dadosDoAluno, permisaoCurso);	
	}

	@RequestMapping(value = "/disciplinasIncritasConcluidas/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> disciplinasIncritas(@PathVariable String numero,@PathVariable String userName) {
		List<Matricula> todasMatriculasDeUmAluno = this.matriculasService.todasMatriculasDeUmAluno(numero);
		SituacaoAcademicaCliente situacao = new SituacaoAcademicaCliente();
		PermissaoCurso permisaoCurso = this.permissaoService.permissaoAoCurso(numero, userName);
		List<InscricaoPorAno> inscricaoPorAno = inscricaoService.todasInscricoesPorAnoDeUmAluno(todasMatriculasDeUmAluno);
		List<DisciplinaCursandoCliente> disciplinasInscritas = this.disciplinaService.disciplinasInscritas(numero);
		situacao.setInscricaoPorAno(inscricaoPorAno);
		situacao.setDisciplinasInscritas(disciplinasInscritas);
		return this.notificacaoService.MensagemDeRetorno(0, null, situacao, permisaoCurso);	
	}

	@RequestMapping(value = "/guiasAPP/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiasAPP(@PathVariable String numero, @PathVariable String userName) {
		SituacaoAcademicaCliente situacao = new SituacaoAcademicaCliente();
		PermissaoCurso permisaoCurso = this.permissaoService.permissaoAoCurso(numero, userName);
		List<GuiaLiquidadaCliente> guiasLuiquidadas = this.guiaLiquidadaService.guiasLuiquidadas(numero);
		List<GuiaEmAbertoCliente> guiasEmAbero = this.guiaEmAbertoService.guiasEmAbero(numero);
		situacao.setGuiasLiquidadas(guiasLuiquidadas);
		situacao.setGuiasAbertas(guiasEmAbero);
		return this.notificacaoService.MensagemDeRetorno(0, null, situacao, permisaoCurso);	
	}

	@RequestMapping(value = "/todasGuiasAPP/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> todasGuiasAPP(@PathVariable String numero, @PathVariable String userName) {
		Aluno aluno = this.alunoService.aluno(numero);
		List<TodasGuiasCliente> todasGuias = this.todasGuiaService.todasGuias(aluno.getNumeroDeAluno());
		return this.notificacaoService.MensagemDeRetorno(0, todasGuias);	
	}

	@RequestMapping(value = "/guiasLiquidadasAPP/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiasLiquidadasAPP(@PathVariable String numero,@PathVariable String userName) {
		PermissaoCurso permisaoCurso = this.permissaoService.permissaoAoCurso(numero, userName);
		List<GuiaLiquidadaCliente> guiasLuiquidadas = this.guiaLiquidadaService.guiasLuiquidadas(numero);
		return this.notificacaoService.MensagemDeRetorno(0, null, guiasLuiquidadas, permisaoCurso);	
	}

	@RequestMapping(value = "/guiasAbertasAPP/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiasAbertasAPP(@PathVariable String numero, @PathVariable String userName) {
		PermissaoCurso permisaoCurso = this.permissaoService.permissaoAoCurso(numero, userName);
		List<GuiaEmAbertoCliente> guiasEmAbero = this.guiaEmAbertoService.guiasEmAbero(numero);
		return this.notificacaoService.MensagemDeRetorno(0, null, guiasEmAbero, permisaoCurso);
	}

	@RequestMapping(value = "/emolumentosDaGuiaAPP/{idDaGuia}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiasAPP(@PathVariable Integer idDaGuia) {
		Guia guia = this.guiaService.guia(idDaGuia);
		List<EmolumentoCliente> emolumentos = this.emolumentoService.emolumentos(guia,guia.getAluno().getCurso(), guia.getAnoLectivo());
		if (emolumentos.isEmpty()) 
			throw new RegistoNaoEncontradoException("Não há registo de emolumentos para a guia seleccionada.");
		return this.notificacaoService.MensagemDeRetorno(0, emolumentos);	
	}

	@RequestMapping(value = "/guiaReciboApp/{codigoGuia}/{numero}/{userName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> guiaReciboApp(@PathVariable String codigoGuia, @PathVariable String numero,@PathVariable String userName) {
		try {
			Aluno aluno = this.alunoService.validarEmailInstitucional(numero);
			this.assunto = "Guia Liquidada";
			this.mensagem = "Prezado(a) " + aluno.getNome()+ ", em anexo está a Guia Liquidada que solicitou.";
			
			byte[] ficheiro = guiaService.guiaDePagamento(codigoGuia, userName);
			
			
			return emailService.enviarGuiaLiquidada(aluno.getEmailInstitucional(), this.assunto, mensagem, codigoGuia,ficheiro);
		} catch (JRException erro) {
			this.mensagem += " o ficheiro não está a ser gerado\n Erro: " + erro.toString();
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2, "Erro ao validar guia. Tente novamente mais tarde");
		} catch (ClassNotFoundException | SQLException erro) {
			this.mensagem += " há um erro no Backend\n Codigo do Erro: " + erro.toString();
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,"Erro ao Processar guia. Tente novamente mais tarde");
		} catch (Exception erro) {
			this.mensagem += " há um erro no Backend\n Código do Erro: " + erro.toString();
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,"Erro ao Processar guia. Tente novamente mais tarde");
		}
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/gerar-referencia/{numeroGuia}/{numero}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ResponseEntity<ResponseCliente> listarAnoLectivos(@PathVariable Integer numeroGuia,@PathVariable String numero){
		Guia guia = this.guiaService.guia(numeroGuia);
		try {
			if (guia == null) {
				this.mensagem = "O Estudante com o telefone \""+numero+"\" não consegue gerar referência porque a guia não existe."
						+ "\nPor favor, valide com o pessoal de desenvolvimento.";
				this.emailService.reportarFalha(this.assunto, this.mensagem);
				return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,"Erro ao Gerar-referência. Tente novamente mais tarde");
			} else {
				return this.guiaService.gerarReferencia(guia,numero);
			}			
		}catch(UnirestException erro) {
			this.mensagem = "O Estudante \""+guia.getAluno().getNumeroDeAluno()+"\" não consegue gerar referência porque há um erro no backend."
					+ "\n Codigo do Erro: " + erro.getMessage() 
					+ "\nPor favor, valide com o pessoal de desenvolvimento.";
			this.emailService.reportarFalha(this.assunto, this.mensagem);
			return ObjectoDeRetornoParcial.MensagemDeRetorno(null, 2,"Erro ao Gerar-referência. Tente novamente mais tarde");
		}			
	}
	
}
