package br.gov.pa.fscm.web.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.pa.fscm.domain.Avaliacao;
import br.gov.pa.fscm.projection.AvaliacaoDTO;
import br.gov.pa.fscm.projection.PerguntaView;
import br.gov.pa.fscm.projection.RespostaDTO;
import br.gov.pa.fscm.service.AvaliacaoService;
import br.gov.pa.fscm.service.OpcaoService;
import br.gov.pa.fscm.service.PerguntaService;
import br.gov.pa.fscm.service.QuestionarioService;
import br.gov.pa.fscm.service.RespostaService;

@Controller
@RequestMapping("respostas")
public class RespostaController {
	
	@Autowired
	QuestionarioService questionarioService;
	
	@Autowired	
	PerguntaService perguntaService;
	
	@Autowired	
	OpcaoService opcaoService;
	
	@Autowired
	AvaliacaoService avaliacaoService;
	
	@Autowired
	RespostaService respostaService;	
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(RespostaController.class);
		
	@GetMapping("/iniciar")
	public String inicia(Long id_avaliacao, RespostaDTO respostaDTO, ModelMap model) {

		if(id_avaliacao == null) {
			Avaliacao avaliacao = new Avaliacao();
			avaliacao.setDhinicio(LocalDateTime.now());
			avaliacaoService.cadastrar(avaliacao);
			id_avaliacao = avaliacao.getId();
		}

		PerguntaView pergunta = this.retornaProximaPergunta(id_avaliacao);
		
		model.addAttribute("id_avaliacao", id_avaliacao);
		model.addAttribute("pergunta",pergunta);
		model.addAttribute("opcaos", opcaoService.buscarPorPergunta(pergunta.getId()));

		if(perguntaService.buscarPerguntaPublicada().size() == 0) {
			return "resposta/erro";
		}
		else if(perguntaService.buscarPerguntaPublicada().size() > 0  && perguntaService.buscarPerguntaPublicada().size() == perguntaService.buscarPerguntaRespondida(id_avaliacao).size()) {
			return "redirect:/respostas/finalizar";				
		}
				
		return "resposta/cadastro";	
	}	

	@PostMapping("/salvar")
	public String salva(Long id_avaliacao, Long id_pergunta, RespostaDTO respostaDTO, BindingResult result, ModelMap model, RedirectAttributes attr) {
		
		if(id_avaliacao == null) {
			return "resposta/cadastro";
		}	

		model.addAttribute("id_avaliacao", id_avaliacao);
				
		respostaDTO.setId_avaliacao(id_avaliacao);
		respostaDTO.setId_pergunta(id_pergunta);	
		respostaService.salvar(respostaDTO);			

		/* --- Testa se a lista de perguntas já foi toda respondida --- */
		if(perguntaService.buscarPerguntaPublicada().size() == 0) {
			return "resposta/erro";
		}
		else if(perguntaService.buscarPerguntaPublicada().size() > 0  && perguntaService.buscarPerguntaPublicada().size() == perguntaService.buscarPerguntaRespondida(id_avaliacao).size()) {
			AvaliacaoDTO avaliacaoDTO = avaliacaoService.buscarPorId(id_avaliacao);
			avaliacaoService.finaliza(avaliacaoDTO);
			return "redirect:/respostas/finalizar";				
		}
		
		/* --- Preparando as variáveis para a tela de cadastro de resposta --- */
						
		PerguntaView pergunta = this.retornaProximaPergunta(id_avaliacao);
			
		model.addAttribute("pergunta",pergunta);
		model.addAttribute("opcaos", opcaoService.buscarPorPergunta(pergunta.getId()));		
		
		return "resposta/cadastro";
	}		
	
	private PerguntaView retornaProximaPergunta(Long id_avaliacao) {		
		
		List<PerguntaView> perguntas = perguntaService.buscarPerguntaPublicada();
		List<PerguntaView> perguntasRespondidas = perguntaService.buscarPerguntaRespondida(id_avaliacao);				
		
		if(perguntas.isEmpty()) {
			return null;
		}
		if(!perguntas.isEmpty() && perguntasRespondidas.isEmpty()) {
			PerguntaView pergunta = perguntas.iterator().next();
			return pergunta;
		}		
		if(!perguntas.isEmpty() && !perguntasRespondidas.isEmpty()) {
			Integer contador = 0;			
			for(PerguntaView pergunta : perguntas){
				contador = contador + 1;
	            if(contador > perguntasRespondidas.size()) {
	            	return pergunta;
	            }
	        }			
		}
		return null;			
	}
		

	@GetMapping("/finalizar")
	public String finaliza(Long id_avaliacao) {	
		return "agradecimentos";	
	}
	
}
