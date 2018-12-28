package br.com.woop.sicredi.votacaoservice.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.woop.sicredi.votacaoservice.context.logger.Log;
import br.com.woop.sicredi.votacaoservice.context.logger.Logger;

public class ApiBase {

	@Log
	protected Logger logger;

	protected <T> ResponseEntity<T> ok(T body) {
		this.logger.info("Success (200): " + body.toString());
		return ResponseEntity.ok(body);
	}

	protected <T> ResponseEntity<T> created(T object) {
		this.logger.info("Object created (201): " + object.toString());
		return new ResponseEntity<>(object, HttpStatus.CREATED);
	}

	protected <T> ResponseEntity<T> noContent() {
		this.logger.error("Register not found.");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	protected <T> ResponseEntity<T> expectationFailed(T object) {
		this.logger.error("Expectation failed (417): " + object.toString());
		return new ResponseEntity<>(object, HttpStatus.EXPECTATION_FAILED);
	}
}
