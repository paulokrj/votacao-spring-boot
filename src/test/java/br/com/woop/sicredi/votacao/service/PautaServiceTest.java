package br.com.woop.sicredi.votacao.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.woop.sicredi.votacaoservice.dto.CreatePautaDto;
import br.com.woop.sicredi.votacaoservice.dto.PautaDto;
import br.com.woop.sicredi.votacaoservice.dto.ResultPautaDto;
import br.com.woop.sicredi.votacaoservice.exception.DataIntegrityException;
import br.com.woop.sicredi.votacaoservice.exception.ObjectNotFoundException;
import br.com.woop.sicredi.votacaoservice.model.Pauta;
import br.com.woop.sicredi.votacaoservice.model.Voto;
import br.com.woop.sicredi.votacaoservice.repository.PautaRepository;
import br.com.woop.sicredi.votacaoservice.service.PautaService;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {

	private ObjectMapper mapper;
	private PautaService service;
	private PautaRepository repository;

	@Before
	public void initTests() {
		mapper = Mockito.mock(ObjectMapper.class);
		repository = Mockito.mock(PautaRepository.class);
		this.service = new PautaService(mapper, repository);
	}

	@Test
	public void insertSuccess() {
		CreatePautaDto createPautaDto = new CreatePautaDto("teste", "teste");
		Pauta pauta = new Pauta(1L, "teste", "teste", LocalDateTime.now(), null, null);
		PautaDto pautaDto = new PautaDto(1L, "teste", "teste", LocalDateTime.now(), null, null);

		when(mapper.convertValue(createPautaDto, Pauta.class)).thenReturn(pauta);
		when(mapper.convertValue(pauta, PautaDto.class)).thenReturn(pautaDto);
		when(repository.save(Mockito.any(Pauta.class))).thenReturn(pauta);
		Optional<PautaDto> result = this.service.insert(createPautaDto);
		assertTrue(result.isPresent());
	}

	@Test
	public void findById() {
		Pauta pauta = new Pauta(1L, "teste", "teste", LocalDateTime.now(), null, null);

		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(pauta));
		Optional<Pauta> result = this.service.findById(1L);
		assertTrue(result.isPresent());
	}

	@Test
	public void findByCodigo() {
		Pauta pauta = new Pauta(1L, "teste", "teste", LocalDateTime.now(), null, null);
		PautaDto pautaDto = new PautaDto(1L, "teste", "teste", LocalDateTime.now(), null, null);

		when(mapper.convertValue(pauta, PautaDto.class)).thenReturn(pautaDto);
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(pauta));

		Optional<PautaDto> result = this.service.findByPautaCodigo(1L);
		assertTrue(result.isPresent());
	}

	@Test
	public void result() {
		Pauta pauta = new Pauta(1L, "teste", "teste", LocalDateTime.now(), null, null);
		ResultPautaDto resultPautaDto = new ResultPautaDto(1L, "teste", null);
		Voto voto1 = new Voto(1L, 1L, pauta, Boolean.TRUE, LocalDateTime.now());
		Voto voto2 = new Voto(1L, 2L, pauta, Boolean.FALSE, LocalDateTime.now());
		pauta.getVotoList().add(voto1);
		pauta.getVotoList().add(voto2);

		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(pauta));
		when(mapper.convertValue(pauta, ResultPautaDto.class)).thenReturn(resultPautaDto);

		Optional<ResultPautaDto> result = this.service.result(1L);
		assertTrue(result.isPresent());
		assertTrue(result.get().getTotalVotos() == 2L);
	}

	@Test
	public void start() {
		Pauta pauta = new Pauta(1L, "teste", "teste", LocalDateTime.now(), null, null);
		PautaDto pautaDto = new PautaDto(1L, "teste", "teste", LocalDateTime.now(), LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(60));
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(pauta));
		when(repository.save(Mockito.any(Pauta.class))).thenReturn(pauta);

		when(mapper.convertValue(pauta, PautaDto.class)).thenReturn(pautaDto);

		Optional<PautaDto> result = this.service.start(1L, 60L);
		assertTrue(result.isPresent());
	}

	@Test
	public void startNotFound() {
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		try {
			this.service.start(1L, 60L);
		} catch (ObjectNotFoundException ex) {
			assertNotNull(ex);
		}
	}

	@Test
	public void startPautaJaAberta() {
		Pauta pauta = new Pauta(1L, "teste", "teste", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now().plusMinutes(60L));
		when(repository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(pauta));

		try {
			this.service.start(1L, 60L);
		} catch (DataIntegrityException ex) {
			assertNotNull(ex);
		}
	}

}
