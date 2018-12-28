package br.com.woop.sicredi.votacaoservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.woop.sicredi.votacaoservice.dto.CreatePautaDto;
import br.com.woop.sicredi.votacaoservice.dto.PautaDto;
import br.com.woop.sicredi.votacaoservice.dto.ResultPautaDto;
import br.com.woop.sicredi.votacaoservice.exception.DataIntegrityException;
import br.com.woop.sicredi.votacaoservice.exception.ObjectNotFoundException;
import br.com.woop.sicredi.votacaoservice.model.Pauta;
import br.com.woop.sicredi.votacaoservice.model.Voto;
import br.com.woop.sicredi.votacaoservice.repository.PautaRepository;

@Service
public class PautaService {

	private ObjectMapper mapper;
	private PautaRepository repository;
	
	@Autowired
	public PautaService(ObjectMapper mapper, PautaRepository repository) {
		super();
		this.mapper = mapper;
		this.repository = repository;
	}

	public Optional<PautaDto> insert(CreatePautaDto pautaDto) {
		return Optional.ofNullable(this.mapper.convertValue(pautaDto, Pauta.class))
				.flatMap(obj -> Optional.ofNullable(this.repository.save(obj)))
				.map(obj -> this.mapper.convertValue(obj, PautaDto.class));
	}

	public Optional<Pauta> findById(Long pautaCodigo) {
		return this.repository.findById(pautaCodigo);
	}
	
	public Optional<PautaDto> findByPautaCodigo(Long pautaCodigo) {
		return this.repository.findById(pautaCodigo).map(obj -> this.mapper.convertValue(obj, PautaDto.class));
	}

	public Optional<ResultPautaDto> result(Long pautaCodigo) {
		Optional<Pauta> optPauta = this.repository.findById(pautaCodigo);

		return optPauta.flatMap(obj -> Optional.ofNullable(this.mapper.convertValue(obj, ResultPautaDto.class)))
				.flatMap(obj -> this.calcResult(obj, optPauta.get().getVotoList()));
	}
	
	public Optional<PautaDto> start(Long pautaCodigo, Long tempoSessao) {
		Optional<Pauta> optPauta = this.repository.findById(pautaCodigo);

		if (!optPauta.isPresent())
			throw new ObjectNotFoundException(String.format("Pauta não encontrada: %d", pautaCodigo));

		Pauta pauta = optPauta.get();

		if (this.isOpenPauta(pauta))
			throw new DataIntegrityException(String.format("Pauta %d, já foi inicializada", pautaCodigo));

		return this.setTempoVotacao(pauta, tempoSessao).flatMap(obj -> Optional.ofNullable(this.repository.save(obj)))
				.map(obj -> this.mapper.convertValue(obj, PautaDto.class));
	}
	
	public Boolean isValid(Long pautaCodigo) {
		return this.repository.getByPautaCodigoAndBetweenSessao(pautaCodigo, LocalDateTime.now())
				.map(obj -> Boolean.TRUE).orElse(Boolean.FALSE);
	}

	private Optional<ResultPautaDto> calcPercentagem(ResultPautaDto result) {
		for (Map.Entry<Boolean, Long> entry : result.getResult().entrySet()) {
			result.getPercentage().put(entry.getKey(), (entry.getValue() * 100) / result.getTotalVotos());
		}
		return Optional.ofNullable(result);
	}

	private Optional<ResultPautaDto> calcResult(ResultPautaDto result, List<Voto> votoList) {
		result.setResult(votoList.stream().collect(Collectors.groupingBy(Voto::getOpcao, Collectors.counting())));
		result.setTotalVotos(votoList.stream().map(Voto::getOpcao).count());
		return this.calcPercentagem(result);
	}

	private Boolean isOpenPauta(Pauta pauta) {
		return pauta.getDataInicioVotacao() != null;
	}

	private Optional<Pauta> setTempoVotacao(Pauta pauta, Long tempoSessao) {
		pauta.setDataInicioVotacao(LocalDateTime.now());
		pauta.setDataFimVotacao(LocalDateTime.now().plusMinutes(tempoSessao));

		return Optional.ofNullable(pauta);
	}
}
