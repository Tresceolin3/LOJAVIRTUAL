package dev.ceolin.lojavirtual;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.ceolin.lojavirtual.model.dto.ObjetoErrorDTO;

@RestControllerAdvice
@ControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ExceptionDevJava.class)
	public ResponseEntity<Object> handleExceptionCustom(ExceptionDevJava exceptionDevJava) {
		ObjetoErrorDTO objetoErrorDTO = new ObjetoErrorDTO();

		objetoErrorDTO.setError(exceptionDevJava.getMessage());
		objetoErrorDTO.setCode(HttpStatus.OK.toString());

		return new ResponseEntity<Object>(objetoErrorDTO, HttpStatus.OK);
	}

	/* captura execoes */
	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {

		ObjetoErrorDTO objetoErrorDTO = new ObjetoErrorDTO();

		String msg = "";

		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			for (ObjectError objectError : list) {
				msg += objectError.getDefaultMessage() + "\n";
			}
		}
		if (ex instanceof HttpMessageNotReadableException) {
				msg = "Nao esta sendo enviado dados para o BODY corpo da resquisao";
		} else {
			msg = ex.getMessage();
		}
		objetoErrorDTO.setError(msg);
		objetoErrorDTO.setCode(status.value() + " ==> " + status.getReasonPhrase());

		return new ResponseEntity<Object>(objetoErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/* Captura erro na parte de banco */
	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class })
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {

		ObjetoErrorDTO objetoErroDTO = new ObjetoErrorDTO();

		String msg = "";

		if (ex instanceof DataIntegrityViolationException) {
			msg = "Erro de integridade no banco: "
					+ ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof ConstraintViolationException) {
			msg = "Erro de chave estrangeira: "
					+ ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof SQLException) {
			msg = "Erro de SQL do Banco: " + ((SQLException) ex).getCause().getCause().getMessage();
		} else {
			msg = ex.getMessage();
		}

		objetoErroDTO.setError(msg);
		objetoErroDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

		ex.printStackTrace();

		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
