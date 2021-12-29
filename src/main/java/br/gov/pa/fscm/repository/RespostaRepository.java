package br.gov.pa.fscm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.pa.fscm.domain.Resposta;
import br.gov.pa.fscm.projection.RespostaView;

public interface RespostaRepository  extends JpaRepository <Resposta, Long>{

	@Modifying
	@Query(value = "INSERT INTO resposta (id_opcao_fk, id_pergunta_fk, id_avaliacao_fk) values (:id_opcao, :id_pergunta, :id_avaliacao)", nativeQuery = true)
	public void insertResposta(@Param("id_opcao") Long id_opcao, @Param("id_pergunta") Long id_pergunta, @Param("id_avaliacao") Long id_avaliacao);

	public Page<RespostaView> findAllByOrderByIdAsc(Pageable pageable);
	
	@Query("SELECT r FROM Resposta r "
			 + "WHERE r.avaliacao.id  = :id_avaliacao ORDER BY r.pergunta.peso asc")	
	public Page<RespostaView> findAllByOrderByAvaliacao(Pageable pageable, Long id_avaliacao);
				
}
