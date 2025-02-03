package ao.co.intellectus.config.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class SigaExceptionHandller extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String mesangemUsurario = messageSource.getMessage("mensagem.invalida", null, Locale.getDefault());
		String mesangemDesenvolvedor = ex.getCause().toString();

		return handleExceptionInternal(ex, new Erro(mesangemUsurario, mesangemDesenvolvedor), headers,
				HttpStatus.BAD_REQUEST, request);
	}

	public static class Erro {
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;

		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public void setMensagemUsuario(String mensagemUsuario) {
			this.mensagemUsuario = mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}

		public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}
	}

	@SuppressWarnings("static-access")
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> listaDeErro = listaDeErro(ex.getBindingResult());
		return handleExceptionInternal(ex, listaDeErro, headers, status.BAD_REQUEST, request);
	}

	private List<Erro> listaDeErro(BindingResult binding) {
		List<Erro> erros = new ArrayList<>();

		List<FieldError> fieldErrors = binding.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			String mu = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String md = fieldError.toString();
			erros.add(new Erro(mu, md));
		}
		return erros;
	}

}
