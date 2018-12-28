package br.com.woop.sicredi.votacaoservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.woop.sicredi.votacaoservice.dto.CreateVotoDto;
import br.com.woop.sicredi.votacaoservice.dto.VotoDto;
import br.com.woop.sicredi.votacaoservice.exception.DataIntegrityException;
import br.com.woop.sicredi.votacaoservice.model.Pauta;
import br.com.woop.sicredi.votacaoservice.model.Voto;
import br.com.woop.sicredi.votacaoservice.repository.VotoRepository;

@Service
public class VotoService {

	private ObjectMapper mapper;
	private PautaService pautaService;
	private VotoRepository repository;

	@Autowired
	public VotoService(ObjectMapper mapper, PautaService pautaService, VotoRepository repository) {
		super();
		this.mapper = mapper;
		this.pautaService = pautaService;
		this.repository = repository;
	}

	public Optional<VotoDto> insert(CreateVotoDto votoDto) {
		if (!this.pautaService.isValid(votoDto.getPautaCodigo()))
			throw new DataIntegrityException(String.format("Esta pauta não esta disponível: %d", votoDto.getPautaCodigo()));

		if (this.repository.existsByAssociadoCodigoAndPautaPautaCodigo(votoDto.getAssociadoCodigo(),
				votoDto.getPautaCodigo()))
			throw new DataIntegrityException(String.format("O associado %d, já votou nesta pauta %d",
					votoDto.getAssociadoCodigo(), votoDto.getPautaCodigo()));

		return Optional.ofNullable(this.mapper.convertValue(votoDto, Voto.class))
				.flatMap(obj -> this.setPauta(obj, votoDto.getPautaCodigo()))
				.flatMap(obj -> Optional.ofNullable(this.repository.save(obj)))
				.map(obj -> this.mapper.convertValue(obj, VotoDto.class));
	}
	
	private Optional<Voto> setPauta(Voto voto, Long pautaCodigo) {
		Optional<Pauta> optPauta = this.pautaService.findById(pautaCodigo);
		if (optPauta.isPresent())
			voto.setPauta(optPauta.get());
		
		return Optional.ofNullable(voto);
	}
}
