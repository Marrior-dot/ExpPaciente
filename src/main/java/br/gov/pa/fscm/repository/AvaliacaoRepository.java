package br.gov.pa.fscm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.pa.fscm.domain.Avaliacao;
import br.gov.pa.fscm.projection.AvaliacaoDTO;

public interface AvaliacaoRepository extends JpaRepository <Avaliacao, Long> {

	public AvaliacaoDTO findAvaliacaoById(Long id);
	
	@Modifying
	@Query(value = "UPDATE avaliacao a SET a.finalizada = true "
			+ "WHERE a.id = :id", nativeQuery = true)
	public void saveAvaliacaoById(@Param("id") Long id);
	
}
