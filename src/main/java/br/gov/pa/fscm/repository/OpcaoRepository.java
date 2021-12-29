package br.gov.pa.fscm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.pa.fscm.domain.Opcao;
import br.gov.pa.fscm.projection.OpcaoDTO;
import br.gov.pa.fscm.projection.OpcaoView;

public interface OpcaoRepository  extends JpaRepository <Opcao, Long>{

	@Modifying
	@Query(value = "INSERT INTO opcao (descricao, valor, id_tiporesposta_fk) values (:descricao, :valor, :id_tiporesposta)", nativeQuery = true)
	public void insertOpcao(@Param("descricao") String descricao, @Param("valor") Integer valor, @Param("id_tiporesposta") Long id_tiporesposta);

	public Page<OpcaoView> findAllByOrderByIdAsc(Pageable pageable);

	public OpcaoDTO findOpcaoById(Long id);
	
	@Modifying
	@Query(value = "UPDATE opcao q SET q.descricao = :descricao, q.valor = :valor "
			+ "WHERE q.id = :id", nativeQuery = true)
	public void saveOpcaoById(@Param("id") Long id, @Param("descricao") String descricao, @Param("valor") Integer valor);
	
	@Query("SELECT o FROM Opcao o, Tiporesposta tr "
			 + "WHERE  o.tiporesposta = tr and  tr.id = ?1  ORDER BY o.valor asc")
	public Page<OpcaoView> findAllByTiporesposta(Long id_tiporesposta, Pageable pageable);	

	@Query("SELECT o FROM Opcao o, Pergunta p "
			 + "WHERE o.tiporesposta = p.tiporesposta and p.id = :id_pergunta ORDER BY o.valor asc")
	public List<OpcaoView> findAllByPerguntaId(@Param("id_pergunta") Long id_pergunta);	
}
