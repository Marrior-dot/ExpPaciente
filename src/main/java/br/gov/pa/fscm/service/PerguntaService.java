package br.gov.pa.fscm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.fscm.projection.PerguntaDTO;
import br.gov.pa.fscm.projection.PerguntaView;
import br.gov.pa.fscm.repository.PerguntaRepository;

@Service 
@Transactional(readOnly = false)
public class PerguntaService {

	@Autowired
	PerguntaRepository perguntaRepository;
	
	public void salvar(PerguntaDTO perguntaDTO, Long id_questionario) {
		perguntaRepository.insertPergunta(perguntaDTO.getTitulo(), perguntaDTO.getDescricao(), perguntaDTO.getPeso(), id_questionario, perguntaDTO.getId_tiporesposta());		
	}
	
	@Transactional(readOnly = true)
	public Page<PerguntaView> buscarTodos(Pageable pageable) {
		return perguntaRepository.findAllByOrderByIdAsc(pageable);
	}

	public void editar(PerguntaDTO perguntaDTO) {
		perguntaRepository.savePerguntaById(perguntaDTO.getId(), perguntaDTO.getTitulo(), perguntaDTO.getDescricao(), perguntaDTO.getPeso(), perguntaDTO.getId_tiporesposta());		
	}

	public void excluir(Long id) {
		perguntaRepository.deleteById(id);		
	}

	@Transactional(readOnly = true)
	public PerguntaDTO buscarPorId(Long id) {
		return perguntaRepository.findPerguntaById(id);
	}

	@Transactional(readOnly = true)
	public PerguntaView buscarViewPorId(Long id) {
		return perguntaRepository.findAllById(id);
	}	
	
	public Page<PerguntaView> buscarPorQuestionario(Long id_questionario, Pageable pageable) {
		return perguntaRepository.findAllByQuestionario(id_questionario, pageable);
	}
	
	public List<PerguntaView> buscarPerguntaPublicada() {
		return perguntaRepository.findPerguntaByQuestionarioPublicadoTrueOrderByPesoAsc();
	}
	
	public List<PerguntaView> buscarPerguntaRespondida(Long id_avaliacao) {
		return perguntaRepository.findPerguntaByAvaliacaoOrderByPesoAsc(id_avaliacao);
	}

}
