package br.com.woop.sicredi.votacaoservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.woop.sicredi.votacaoservice.model.Voto;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {

	Boolean existsByAssociadoCodigoAndPautaPautaCodigo(Long associadoCodigo, Long pautaCodigo);
}
