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

import br.com.woop.sicredi.votacaoservice.dto.CreateVotoDto;
import br.com.woop.sicredi.votacaoservice.dto.VotoDto;
import br.com.woop.sicredi.votacaoservice.exception.DataIntegrityException;
import br.com.woop.sicredi.votacaoservice.model.Pauta;
import br.com.woop.sicredi.votacaoservice.model.Voto;
import br.com.woop.sicredi.votacaoservice.repository.VotoRepository;
import br.com.woop.sicredi.votacaoservice.service.PautaService;
import br.com.woop.sicredi.votacaoservice.service.VotoService;

@RunWith(MockitoJUnitRunner.class)
public class VotoServiceTest {

	private VotoService service;

	private ObjectMapper mapper;
	private PautaService pautaService;
	private VotoRepository repository;

	@Before
	public void initTests() {
		this.mapper = Mockito.mock(ObjectMapper.class);
		pautaService = Mockito.mock(PautaService.class);
		repository = Mockito.mock(VotoRepository.class);

		this.service = new VotoService(mapper, pautaService, repository);
	}

	@Test
	public void insertSuccess() {
		CreateVotoDto createVotoDto = new CreateVotoDto(1L, 1L, Boolean.TRUE);
		Pauta pauta = new Pauta(1L, "teste", "teste", LocalDateTime.now(), LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(60));
		Voto voto = new Voto(1L, 1L, pauta, Boolean.TRUE, LocalDateTime.now());
		VotoDto votoDto = new VotoDto(1L, 1L, Boolean.TRUE, LocalDateTime.now());

		when(pautaService.isValid(Mockito.anyLong())).thenReturn(Boolean.TRUE);
		when(repository.existsByAssociadoCodigoAndPautaPautaCodigo(Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(Boolean.FALSE);
		when(pautaService.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(pauta));
		when(repository.save(Mockito.any(Voto.class))).thenReturn(voto);

		when(mapper.convertValue(createVotoDto, Voto.class)).thenReturn(voto);
		when(mapper.convertValue(voto, VotoDto.class)).thenReturn(votoDto);

		Optional<VotoDto> result = this.service.insert(createVotoDto);
		assertTrue(result.isPresent());
	}

	@Test
	public void insertInvalid() {
		CreateVotoDto createVotoDto = new CreateVotoDto(1L, 1L, Boolean.TRUE);
		when(pautaService.isValid(Mockito.anyLong())).thenReturn(Boolean.FALSE);

		try {
			this.service.insert(createVotoDto);
		} catch (DataIntegrityException ex) {
			assertNotNull(ex);
		}
	}

	@Test
	public void insertExists() {
		CreateVotoDto createVotoDto = new CreateVotoDto(1L, 1L, Boolean.TRUE);

		when(pautaService.isValid(Mockito.anyLong())).thenReturn(Boolean.TRUE);
		when(repository.existsByAssociadoCodigoAndPautaPautaCodigo(Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(Boolean.TRUE);
		
		try {
			this.service.insert(createVotoDto);
		} catch (DataIntegrityException ex) {
			assertNotNull(ex);
		}
	}
}
