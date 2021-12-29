package br.gov.pa.fscm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.fscm.projection.QuestionarioDTO;
import br.gov.pa.fscm.projection.QuestionarioView;
import br.gov.pa.fscm.repository.QuestionarioRepository;

@Service 
@Transactional(readOnly = false)
public class QuestionarioService {

	@Autowired
	QuestionarioRepository questionarioRepository;
	
	public void salvar(QuestionarioDTO questionarioDTO) {
		if(questionarioDTO.getPublicado() == Boolean.TRUE) {
			questionarioRepository.saveAllQuestionariosFalse();
		}		
		questionarioRepository.insertQuestionario(questionarioDTO.getTitulo(), questionarioDTO.getDescricao(), questionarioDTO.getPublicado());		
	}
	
	@Transactional(readOnly = true)
	public Page<QuestionarioView> buscarTodos(Pageable pageable) {
		return questionarioRepository.findAllByOrderByIdAsc(pageable);
	}

	public void editar(QuestionarioDTO questionarioDTO) {
		if(questionarioDTO.getPublicado() == Boolean.TRUE) {
			questionarioRepository.saveAllQuestionariosFalse();
		}
		questionarioRepository.saveQuestionarioById(questionarioDTO.getId(), questionarioDTO.getTitulo(), questionarioDTO.getDescricao(), questionarioDTO.getPublicado());		
	}

	public void excluir(Long id) {
		questionarioRepository.deleteById(id);		
	}

	@Transactional(readOnly = true)
	public QuestionarioDTO buscarPorId(Long id) {
		return questionarioRepository.findQuestionarioById(id);
	}

	@Transactional(readOnly = true)
	public QuestionarioView buscarPorQuestionarioPublicado() {
		return questionarioRepository.findQuestionarioByPublicadoTrue();
	}	
/*
	@Override
	public Page<QuestionarioView> buscarPorDescricao(String descricao, Pageable pageable) {
		return null; //artigoRepository.findAllByTituloContainingOrConteudoContainingOrderByIdAsc(descricao, descricao, pageable);
	}
*/


}
