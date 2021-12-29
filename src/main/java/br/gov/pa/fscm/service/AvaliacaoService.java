package br.gov.pa.fscm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.fscm.domain.Avaliacao;
import br.gov.pa.fscm.projection.AvaliacaoDTO;
import br.gov.pa.fscm.repository.AvaliacaoRepository;

@Service 
@Transactional(readOnly = false)
public class AvaliacaoService {

	@Autowired
	AvaliacaoRepository avaliacaoRepository;
	
	public void cadastrar(Avaliacao avaliacao) {
		avaliacaoRepository.save(avaliacao);	
	}	
	
	public void finaliza(AvaliacaoDTO avaliacaoDTO) {
		avaliacaoRepository.saveAvaliacaoById(avaliacaoDTO.getId());		
	}	
	
	public AvaliacaoDTO buscarPorId(Long id) {
		return avaliacaoRepository.findAvaliacaoById(id);
	}
}
