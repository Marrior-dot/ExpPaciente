package br.gov.pa.fscm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.gov.pa.fscm.domain.Pergunta;
import br.gov.pa.fscm.projection.PerguntaDTO;
import br.gov.pa.fscm.projection.PerguntaView;

public interface PerguntaRepository  extends JpaRepository <Pergunta, Long>{


	@Modifying
	@Query(value = "INSERT INTO pergunta (titulo, descricao, peso, id_questionario_fk, id_tiporesposta_fk) values (:titulo, :descricao, :peso, :id_questionario, :id_tiporesposta)", nativeQuery = true)
	public void insertPergunta(@Param("titulo") String titulo, @Param("descricao") String descricao, @Param("peso") Integer peso, @Param("id_questionario") Long id_questionario, @Param("id_tiporesposta") Long id_tiporesposta);

	public Page<PerguntaView> findAllByOrderByIdAsc(Pageable pageable);

	public PerguntaDTO findPerguntaById(Long id);
	
	public PerguntaView findAllById(Long id);
	
	@Modifying
	@Query(value = "UPDATE pergunta q SET q.titulo = :titulo, q.descricao = :descricao, q.peso = :peso, q.id_tiporesposta_fk = :id_tiporesposta "
			+ "WHERE q.id = :id", nativeQuery = true)
	public void savePerguntaById(@Param("id") Long id, @Param("titulo") String titulo, @Param("descricao") String descricao, @Param("peso") Integer peso, @Param("id_tiporesposta") Long id_tiporesposta);

	@Query("SELECT p FROM Pergunta p, Questionario q "
			 + "WHERE  p.questionario = q and  q.id = ?1  ORDER BY p.peso asc")
	public Page<PerguntaView> findAllByQuestionario(Long id_questionario, Pageable pageable);
	
	
	@Query("SELECT p FROM Pergunta p, Questionario q "
			 + "WHERE  p.questionario = q and  q.publicado = true ORDER BY p.peso asc")
	public List<PerguntaView> findPerguntaByQuestionarioPublicadoTrueOrderByPesoAsc();
	
	
	@Query("SELECT p FROM Pergunta p, Resposta r "
			 + "WHERE  p = r.pergunta and  r.avaliacao.id = :id_avaliacao ORDER BY p.peso asc")
	public List<PerguntaView> findPerguntaByAvaliacaoOrderByPesoAsc(@Param("id_avaliacao") Long id_avaliacao);	
		
}
