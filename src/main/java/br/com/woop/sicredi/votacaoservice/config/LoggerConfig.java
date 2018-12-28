package br.com.woop.sicredi.votacaoservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.woop.sicredi.votacaoservice.context.logger.LoggerInjector;

@Configuration
public class LoggerConfig {
	
    @Bean
    public LoggerInjector loggerInjector() {
        return new LoggerInjector();
    }
}