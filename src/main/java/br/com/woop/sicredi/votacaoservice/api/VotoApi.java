package br.com.woop.sicredi.votacaoservice.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.woop.sicredi.votacaoservice.dto.CreateVotoDto;
import br.com.woop.sicredi.votacaoservice.dto.VotoDto;
import br.com.woop.sicredi.votacaoservice.service.VotoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class VotoApi extends ApiBase {

	@Autowired
	private VotoService service;

	@CrossOrigin
	@PostMapping("/voto")
	@ApiOperation(value = "Inserir novo voto", response = String.class)
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<VotoDto> create(@Valid @ApiParam(value = "Document") @RequestBody CreateVotoDto votoDto) {
		return this.service.insert(votoDto).map(obj -> this.created(obj))
				.orElseGet(() -> this.expectationFailed(null));
	}
}
