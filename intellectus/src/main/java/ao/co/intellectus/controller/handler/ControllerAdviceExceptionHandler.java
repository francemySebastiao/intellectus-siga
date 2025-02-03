package ao.co.intellectus.controller.handler;

import java.net.UnknownHostException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ao.co.intellectus.DTO.EmailCliente;
import ao.co.intellectus.model.Instituicao;
import ao.co.intellectus.model.reponse.ResponseCliente;
import ao.co.intellectus.servico.cafold.EmailService;
import ao.co.intellectus.servico.cafold.InstituicaoService;
import ao.co.intellectus.servico.cafold.RedeService;
import ao.co.intellectus.servico.exception.ConexaoException;
import ao.co.intellectus.servico.exception.DadoInvalidoException;
import ao.co.intellectus.servico.exception.DadosDuplicadoException;
import ao.co.intellectus.servico.exception.DataInvalidaException;
import ao.co.intellectus.servico.exception.DataNullaException;
import ao.co.intellectus.servico.exception.EmailException;
import ao.co.intellectus.servico.exception.EmailInstituicionalNaoEncontradoException;
import ao.co.intellectus.servico.exception.EnvioDeMensagemException;
import ao.co.intellectus.servico.exception.ExameElectronicoException;
import ao.co.intellectus.servico.exception.ImpressaoDeRelatorioException;
import ao.co.intellectus.servico.exception.InstituicaoNaoEncontradaException;
import ao.co.intellectus.servico.exception.RegistoNaoEncontradoException;
import ao.co.intellectus.servico.exception.TipoDeUsuarioNaoEncontradoException;
import ao.co.intellectus.servico.notificacoes.HttpResponse;


@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@Autowired
	private HttpResponse notificacao;
	@Autowired
	private EmailService emailService;
	@Autowired
	private InstituicaoService instituicaoService;
	@Autowired
	private RedeService redeService;
	private EmailCliente emailCliente;
	private String assuntoDoEmail, mensagemDoEmail;
	
	
	
	@ExceptionHandler(RegistoNaoEncontradoException.class)
	public ResponseEntity<ResponseCliente> handlerRegistoNaoEncontradoException(RegistoNaoEncontradoException erro){
		return this.notificacao.MensagemDeRetorno(2, erro.getMessage());
	}
	
	@ExceptionHandler(TipoDeUsuarioNaoEncontradoException.class)
	public ResponseEntity<ResponseCliente> handlerTipoDeUsuarioNaoEncontradoException(TipoDeUsuarioNaoEncontradoException erro) {
		return this.notificacao.MensagemDeRetorno(2, erro.getMessage());
	}
	
	@ExceptionHandler(MessagingException.class)
	public ResponseEntity<ResponseCliente> handlerMessagingException(MessagingException erro){
		return this.notificacao.MensagemDeRetorno(2, erro.getMessage());
	}
	
	@ExceptionHandler(DataNullaException.class)
	public ResponseEntity<ResponseCliente> DataNotNullException(DataNullaException erro){
		return this.notificacao.MensagemDeRetorno(2, erro.getMessage());
	}
	
	@ExceptionHandler(DataInvalidaException.class)
	public ResponseEntity<ResponseCliente> 	DataInvalidaException(DataInvalidaException erro){
		return this.notificacao.MensagemDeRetorno(3, erro.getMessage());
	}
	
	@ExceptionHandler(DadosDuplicadoException.class)
	public ResponseEntity<ResponseCliente> 	DadosDuplicadoException(DadosDuplicadoException erro){
		return this.notificacao.MensagemDeRetorno(3, erro.getMessage());
	}
	
	@ExceptionHandler(DadoInvalidoException.class)
	public ResponseEntity<ResponseCliente> DadoInvalidoException(DadoInvalidoException erro){
		return this.notificacao.MensagemDeRetorno(2, erro.getMessage());
	}
	
	@ExceptionHandler(InstituicaoNaoEncontradaException.class)
	public ResponseEntity<ResponseCliente> handlerInstituicaoNaoEncontradaException(InstituicaoNaoEncontradaException erro){
		this.emailCliente = new EmailCliente();
		String[] destinario = {"suporte@intellectus.co.ao"};
		String[] bCC = {"francisco.lourenco@intellectus.co.ao","ernesto.sambongo@intellectus.co.ao"};
		this.emailCliente.setAssunto("ERRO  - Validação da instituição");
		this.emailCliente.setMensagem(erro.getMessage());
		this.emailCliente.setDestinatario(destinario);
		this.emailCliente.setbCC(bCC);
		this.emailService.enviar(this.emailCliente);
		return this.notificacao.MensagemDeRetorno(2, "Erro ao validar email institucional.");
	}
	
	@ExceptionHandler(EmailInstituicionalNaoEncontradoException.class)
	public ResponseEntity<ResponseCliente> EmailInstituicionalNaoEncontradoException(EmailInstituicionalNaoEncontradoException erro) {
		this.assuntoDoEmail = "ERRO  - Email instituicional";
		this.mensagemDoEmail = erro.getMessage();
		this.emailService.reportarFalha(assuntoDoEmail, this.mensagemDoEmail);
		return this.notificacao.MensagemDeRetorno(2, "Erro ao validar email institucional.");
	}
	
	@ExceptionHandler(EnvioDeMensagemException.class)
	public void handlerMimoException(EnvioDeMensagemException erro){
		this.assuntoDoEmail = "API MENSAGEM - ERRO AO ENVIAR MENSAGEM";
		this.mensagemDoEmail = erro.getMessage();
		this.emailService.reportarFalha(assuntoDoEmail, this.mensagemDoEmail);
	}
	
	@ExceptionHandler(ConexaoException.class)
	public void hanlerConexaoException(ConexaoException erro) {
		this.assuntoDoEmail = "API Conexão - ERRO AO COMUNICAR COM A BD";
		this.mensagemDoEmail = erro.getMessage();
		this.emailService.reportarFalha(assuntoDoEmail, this.mensagemDoEmail);
	}
	
	@ExceptionHandler(EmailException.class)
	public void EmailException(EmailException erro) {
		this.assuntoDoEmail = "ENVIO DE EMAIL - ERRO AO ENVIAR EMAIL";
		this.mensagemDoEmail = erro.getMessage();
		this.emailService.reportarFalha(assuntoDoEmail, this.mensagemDoEmail);
	}
	
	@ExceptionHandler(ImpressaoDeRelatorioException.class)
	public void ImpressaoDeRelatorioException(ImpressaoDeRelatorioException erro) {
		this.emailCliente = new EmailCliente();
		String[] destinario = {"suporte@intellectus.co.ao"};
		String[] bCC = {"francisco.lourenco@intellectus.co.ao","ernesto.sambongo@intellectus.co.ao"};
		this.emailCliente.setAssunto("ENVIO DE IMPRESSÃO - ERRO AO IMPRIMIR RELATÓRIO");
		this.emailCliente.setMensagem(erro.getMessage());
		this.emailCliente.setDestinatario(destinario);
		this.emailCliente.setbCC(bCC);
		this.emailService.enviar(this.emailCliente);
	}
	
	@ExceptionHandler(ExameElectronicoException.class)
	public ResponseEntity<ResponseCliente> PerguntaExameException(ExameElectronicoException erro) {
		try {
			Instituicao instituicao = this.instituicaoService.instituicao(2);
			this.assuntoDoEmail = "ERRO - EXAME ELECTRÓNICO";
			this.mensagemDoEmail = erro.getMessage()
					+"\nHOST: " + this.redeService.getNomeHost()
					+"\nUNIDADE:  " +instituicao.getDescricao();
			this.emailService.reportarFalha(assuntoDoEmail, this.mensagemDoEmail);
		} catch (UnknownHostException e) {
			this.emailService.reportarFalha(assuntoDoEmail, e.getMessage());
		}
		return this.notificacao.MensagemDeRetorno(2, erro.getMessage());
	}
	
}
