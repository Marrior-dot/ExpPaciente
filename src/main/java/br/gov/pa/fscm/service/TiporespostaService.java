package br.gov.pa.fscm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.fscm.projection.TiporespostaDTO;
import br.gov.pa.fscm.projection.TiporespostaView;
import br.gov.pa.fscm.repository.TiporespostaRepository;

@Service 
@Transactional(readOnly = false)
public class TiporespostaService {

	@Autowired
	TiporespostaRepository tiporespostaRepository;
	
	public void salvar(TiporespostaDTO tiporespostaDTO) {
		tiporespostaRepository.insertTiporesposta(tiporespostaDTO.getDescricao());		
	}
	
	@Transactional(readOnly = true)
	public Page<TiporespostaView> buscarTodos(Pageable pageable) {
		return tiporespostaRepository.findAllByOrderByIdAsc(pageable);
	}

	@Transactional(readOnly = true)
	public List<TiporespostaDTO> buscarTodos() {
		return tiporespostaRepository.findAllByOrderByDescricaoAsc();
	}
	
	public void editar(TiporespostaDTO tiporespostaDTO) {
		tiporespostaRepository.saveTiporespostaById(tiporespostaDTO.getId(), tiporespostaDTO.getDescricao());		
	}

	public void excluir(Long id) {
		tiporespostaRepository.deleteById(id);		
	}

	@Transactional(readOnly = true)
	public TiporespostaDTO buscarPorId(Long id) {
		return tiporespostaRepository.findTiporespostaById(id);
	}
	
/*
	@Override
	public Page<QuestionarioView> buscarPorDescricao(String descricao, Pageable pageable) {
		return null; //artigoRepository.findAllByTituloContainingOrConteudoContainingOrderByIdAsc(descricao, descricao, pageable);
	}
*/


}
