package br.com.woop.sicredi.votacaoservice.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.woop.sicredi.votacaoservice.model.Pauta;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {

	@Query("select p from Pauta p where p.pautaCodigo = ?1 and  p.dataInicioVotacao <= ?2 and p.dataFimVotacao >= ?2")
	Optional<Pauta> getByPautaCodigoAndBetweenSessao(Long pautaCodigo, LocalDateTime data);
}
