package br.gov.pa.fscm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.pa.fscm.domain.Tiporesposta;
import br.gov.pa.fscm.projection.TiporespostaDTO;
import br.gov.pa.fscm.projection.TiporespostaView;

public interface TiporespostaRepository  extends JpaRepository <Tiporesposta, Long>{

	@Modifying
	@Query(value = "INSERT INTO tiporesposta (descricao) values (:descricao)", nativeQuery = true)
	public void insertTiporesposta(@Param("descricao") String descricao);

	public Page<TiporespostaView> findAllByOrderByIdAsc(Pageable pageable);
	
	public List<TiporespostaDTO> findAllByOrderByDescricaoAsc();

	public TiporespostaDTO findTiporespostaById(Long id);
	
	@Modifying
	@Query(value = "UPDATE tiporesposta q SET q.descricao = :descricao "
			+ "WHERE q.id = :id", nativeQuery = true)
	public void saveTiporespostaById(@Param("id") Long id, @Param("descricao") String descricao);	
}
