package br.gov.pa.fscm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.fscm.projection.RespostaDTO;
import br.gov.pa.fscm.projection.RespostaView;
import br.gov.pa.fscm.repository.RespostaRepository;

@Service 
@Transactional(readOnly = false)
public class RespostaService {
	
	@Autowired
	RespostaRepository respostaRepository;
	
	public void salvar(RespostaDTO respostaDTO) {
		respostaRepository.insertResposta(respostaDTO.getId_opcao(),respostaDTO.getId_pergunta(),respostaDTO.getId_avaliacao());		
	}
	
	@Transactional(readOnly = true)
	public Page<RespostaView> buscarTodos(Pageable pageable) {
		return respostaRepository.findAllByOrderByIdAsc(pageable);
	}
	@Transactional(readOnly = true)
	public Page<RespostaView> buscarPorAvaliacao(Pageable pageable, Long id_avaliacao) {
		return respostaRepository.findAllByOrderByAvaliacao(pageable, id_avaliacao);
	}

}
