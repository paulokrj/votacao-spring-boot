package br.com.woop.sicredi.votacaoservice.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${api.info.title}")
	private String title;

	@Value("${api.info.description}")
	private String description;

	@Value("${api.info.version}")
	private String version;

	@Value("${api.info.termOfService}")
	private String termOfService;

	@Value("${api.info.contact.name}")
	private String name;

	@Value("${api.info.contact.url}")
	private String url;

	@Value("${api.info.contact.email}")
	private String email;

	@Value("${api.info.license}")
	private String license;

	@Value("${api.info.licenseUrl}")
	private String licenseUrl;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.woop.sicredi")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(title, description, version, termOfService, new Contact(name, url, email), license,
				licenseUrl, Collections.emptyList());
	}

}
