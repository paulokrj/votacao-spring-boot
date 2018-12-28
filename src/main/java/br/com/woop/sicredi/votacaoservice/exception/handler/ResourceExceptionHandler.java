package br.com.woop.sicredi.votacaoservice.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.woop.sicredi.votacaoservice.context.logger.Log;
import br.com.woop.sicredi.votacaoservice.context.logger.Logger;
import br.com.woop.sicredi.votacaoservice.exception.DataIntegrityException;
import br.com.woop.sicredi.votacaoservice.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@Log
	protected Logger logger;

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Não encontrado", e.getMessage(), request.getRequestURI());
		logger.error(err.toString());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Integridade de dados", e.getMessage(), request.getRequestURI());
		logger.error(err.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}