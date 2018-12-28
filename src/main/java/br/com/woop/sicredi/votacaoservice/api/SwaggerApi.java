package br.com.woop.sicredi.votacaoservice.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/")
@RestController
@ApiIgnore
public class SwaggerApi {
	
	@GetMapping
    public void index(HttpServletResponse response) throws IOException {
    	response.sendRedirect("swagger-ui.html");
    }
}