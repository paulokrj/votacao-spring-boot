package br.com.woop.sicredi.votacaoservice.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.woop.sicredi.votacaoservice.dto.CreatePautaDto;
import br.com.woop.sicredi.votacaoservice.dto.PautaDto;
import br.com.woop.sicredi.votacaoservice.dto.ResultPautaDto;
import br.com.woop.sicredi.votacaoservice.service.PautaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class PautaApi extends ApiBase {

	@Autowired
	private PautaService service;

	@CrossOrigin
	@GetMapping("/pauta/{pautaCodigo}")
	@ApiOperation(value = "Buscar pauta pelo codigo", response = String.class)
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<PautaDto> findByPautaCodigo(
			@ApiParam(value = "Pauta Codigo") @PathVariable Long pautaCodigo) {
		return this.service.findByPautaCodigo(pautaCodigo).map(obj -> this.ok(obj)).orElseGet(() -> this.noContent());
	}

	@CrossOrigin
	@GetMapping("/pauta/{pautaCodigo}/result")
	@ApiOperation(value = "Buscar resultado da pauta pelo codigo", response = String.class)
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<ResultPautaDto> resultByPautaCodigo(
			@ApiParam(value = "Pauta Codigo") @PathVariable Long pautaCodigo) {
		return this.service.result(pautaCodigo).map(obj -> this.ok(obj)).orElseGet(() -> this.noContent());
	}

	@CrossOrigin
	@PostMapping("/pauta")
	@ApiOperation(value = "Inserir nova pauta", response = String.class)
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<PautaDto> create(@Valid @ApiParam(value = "Document") @RequestBody CreatePautaDto createPautaDto) {
		return this.service.insert(createPautaDto).map(obj -> this.created(obj))
				.orElseGet(() -> this.expectationFailed(null));
	}

	@CrossOrigin
	@PatchMapping("/pauta/{pautaCodigo}/start")
	@ApiOperation(value = "Iniciar sessão de votação em uma pauta", response = String.class)
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<PautaDto> startPadrao(@ApiParam(value = "Pauta Codigo") @PathVariable Long pautaCodigo) {
		return this.service.start(pautaCodigo, 1L).map(obj -> this.ok(obj)).orElseGet(() -> this.noContent());
	}

	@CrossOrigin
	@PatchMapping("/pauta/{pautaCodigo}/start/{minutos}")
	@ApiOperation(value = "Iniciar sessão de votação em uma pauta", response = String.class)
	@ApiResponses({ @ApiResponse(code = 500, message = "Internal Server Error") })
	public ResponseEntity<PautaDto> start(@ApiParam(value = "Pauta Codigo") @PathVariable Long pautaCodigo, @ApiParam(value = "Minutos") @PathVariable Long minutos) {
		return this.service.start(pautaCodigo, minutos).map(obj -> this.ok(obj)).orElseGet(() -> this.noContent());
	}
}