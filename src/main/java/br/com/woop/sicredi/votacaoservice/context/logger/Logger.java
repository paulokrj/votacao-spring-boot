package br.com.woop.sicredi.votacaoservice.context.logger;

import org.slf4j.LoggerFactory;

public class Logger {

private org.slf4j.Logger baseLogger;
	
	public Logger(org.slf4j.Logger baseLogger) {
		this.baseLogger = baseLogger;
	}
	
	public static Logger get(Class<?> clazz) {
		return new Logger(LoggerFactory.getLogger(clazz));
	}
	
	public void info(String message, Object... args) {
		baseLogger.info(String.format(message, args));
	}
	
	public void debug(String message, Object... args) {
		baseLogger.debug(String.format(message, args));
	}
	
	public void error(String message, Object... args) {
		baseLogger.error(String.format(message, args));
	}

	public void error(Throwable throwable, String message, Object... args) {
		if(throwable == null) {
			error(message, args);
			return;
		}
		baseLogger.error(String.format(message, args), throwable);
	}
	
	public void error(Throwable throwable) {
		error(throwable, String.valueOf(throwable.getMessage()));
	}

	public org.slf4j.Logger slf4j() {
		return baseLogger;
	}
}
