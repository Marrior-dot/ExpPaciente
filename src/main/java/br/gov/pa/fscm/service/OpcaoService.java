package br.gov.pa.fscm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.fscm.projection.OpcaoDTO;
import br.gov.pa.fscm.projection.OpcaoView;
import br.gov.pa.fscm.repository.OpcaoRepository;

@Service 
@Transactional(readOnly = false)
public class OpcaoService {

	@Autowired
	OpcaoRepository opcaoRepository;
	
	public void salvar(OpcaoDTO opcaoDTO, Long id_tiporesposta) {
		opcaoRepository.insertOpcao(opcaoDTO.getDescricao(), opcaoDTO.getValor(), id_tiporesposta);		
	}
	
	@Transactional(readOnly = true)
	public Page<OpcaoView> buscarTodos(Pageable pageable) {
		return opcaoRepository.findAllByOrderByIdAsc(pageable);
	}

	public void editar(OpcaoDTO opcaoDTO) {
		opcaoRepository.saveOpcaoById(opcaoDTO.getId(),opcaoDTO.getDescricao(), opcaoDTO.getValor());		
	}

	public void excluir(Long id) {
		opcaoRepository.deleteById(id);		
	}

	@Transactional(readOnly = true)
	public OpcaoDTO buscarPorId(Long id) {
		return opcaoRepository.findOpcaoById(id);
	}

	public Page<OpcaoView> buscarPorTiporesposta(Long id_tiporesposta,  Pageable pageable) {
		return opcaoRepository.findAllByTiporesposta(id_tiporesposta, pageable);
	}

	public List<OpcaoView> buscarPorPergunta(Long id_pergunta) {
		return opcaoRepository.findAllByPerguntaId(id_pergunta);
	}

}
