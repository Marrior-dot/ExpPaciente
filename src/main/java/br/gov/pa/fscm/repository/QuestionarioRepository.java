package br.gov.pa.fscm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.pa.fscm.domain.Questionario;
import br.gov.pa.fscm.projection.QuestionarioDTO;
import br.gov.pa.fscm.projection.QuestionarioView;

public interface QuestionarioRepository extends JpaRepository <Questionario, Long> {

	@Modifying
	@Query(value = "INSERT INTO questionario (titulo, descricao,publicado) values (:titulo, :descricao, :publicado)", nativeQuery = true)
	public void insertQuestionario(@Param("titulo") String titulo, @Param("descricao") String descricao, @Param("publicado") Boolean publicado);

	public Page<QuestionarioView> findAllByOrderByIdAsc(Pageable pageable);

	public QuestionarioDTO findQuestionarioById(Long id);
	
	public QuestionarioView findQuestionarioByPublicadoTrue();
	
	@Modifying
	@Query(value = "UPDATE questionario q SET q.titulo = :titulo, q.descricao = :descricao, q.publicado = :publicado "
			+ "WHERE q.id = :id", nativeQuery = true)
	public void saveQuestionarioById(@Param("id") Long id, @Param("titulo") String titulo, @Param("descricao") String descricao, @Param("publicado") Boolean publicado);
	
	@Modifying
	@Query(value = "UPDATE questionario q SET q.publicado = false", nativeQuery = true)
	public void saveAllQuestionariosFalse();		
	
}
