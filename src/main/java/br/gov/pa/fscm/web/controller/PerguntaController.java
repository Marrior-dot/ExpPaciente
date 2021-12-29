package br.gov.pa.fscm.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.pa.fscm.projection.PerguntaDTO;
import br.gov.pa.fscm.projection.PerguntaView;
import br.gov.pa.fscm.projection.QuestionarioDTO;
import br.gov.pa.fscm.projection.QuestionarioView;
import br.gov.pa.fscm.service.PerguntaService;
import br.gov.pa.fscm.service.QuestionarioService;
import br.gov.pa.fscm.service.TiporespostaService;

@Controller
@RequestMapping("perguntas")
public class PerguntaController {

	@Autowired
	PerguntaService perguntaService;
	
	@Autowired	
	QuestionarioService questionarioService;
	
	@Autowired
	TiporespostaService tiporespostaService;	
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(PerguntaController.class);
	
	
	// ------------------------------------------------ //
	
	@GetMapping("/cadastrar/{id_questionario}")
	public String cadastrar(PerguntaDTO perguntaDTO, ModelMap model, @PathVariable("id_questionario") Long id_questionario) {		
		model.addAttribute("questionarioDTO", questionarioService.buscarPorId(id_questionario));
		model.addAttribute("tiporespostas", tiporespostaService.buscarTodos());
		model.addAttribute("id_questionario", id_questionario);
		return "pergunta/cadastro";
	}	
	
	
	@PostMapping("/salvar")
	public String cadastrar(Long id_questionario, Long id_tiporesposta, @Valid PerguntaDTO perguntaDTO, BindingResult result, ModelMap model, RedirectAttributes attr) {
		
		model.addAttribute("questionarioDTO", questionarioService.buscarPorId(id_questionario));
		model.addAttribute("tiporespostas", tiporespostaService.buscarTodos());
		model.addAttribute("id_questionario", id_questionario);		
		
		if(result.hasErrors()) {
			return "pergunta/cadastro";
		}						
		
		perguntaService.salvar(perguntaDTO, id_questionario);	
		
		attr.addFlashAttribute("success", "Pergunta cadastrada com sucesso.");	
		return "redirect:/perguntas/filtrar/?id_questionario="+id_questionario.toString();		
	}	
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		int currentPage = 1;
        int pageSize = 20;
            
        Page<PerguntaView> perguntaPage = perguntaService.buscarTodos(PageRequest.of(currentPage-1, pageSize));
        
		int totalPages = perguntaPage.getTotalPages();
		model.addAttribute("paginaAtual", perguntaPage.getNumber()+1);
	    if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
	    }	    	    
		model.addAttribute("perguntas", perguntaPage);
		return "pergunta/lista";
	}	
	
	@GetMapping("/editar/{id}/{id_questionario}")
	public String preEditar(Long id_tiporesposta, @PathVariable("id") Long id, @PathVariable("id_questionario") Long id_questionario, ModelMap model) {					
				
		PerguntaView perguntaView = perguntaService.buscarViewPorId(id);
		
		model.addAttribute("perguntaDTO", perguntaService.buscarPorId(id));			
		model.addAttribute("questionarioDTO", questionarioService.buscarPorId(id_questionario));
		model.addAttribute("tiporespostas", tiporespostaService.buscarTodos());
		model.addAttribute("id_tiporesposta", perguntaService.buscarViewPorId(id).getIdTiporesposta());		
		model.addAttribute("id_questionario", id_questionario);		
		return "pergunta/cadastro";
	}	
	
	@PostMapping("/editar")
	public String editar(Long id_questionario, @Valid PerguntaDTO perguntaDTO, Long id, BindingResult result, ModelMap model, RedirectAttributes attr) {							
			
		
		//logger.info("---------------- TESTE ----------------");
		//logger.info(id_questionario.toString());	 
		
		if(result.hasErrors()) {
			return "pergunta/cadastro";
		}
		perguntaDTO.setId(id);
		perguntaService.editar(perguntaDTO);
	
		
		attr.addFlashAttribute("success", "Pergunta editada com sucesso.");
		return "redirect:/perguntas/filtrar/?id_questionario="+id_questionario.toString();
	}		

	@GetMapping("/excluir/{id}/{id_questionario}")
	public String excluir(@PathVariable("id") Long id, @PathVariable("id_questionario") Long id_questionario, ModelMap model) {			
		perguntaService.excluir(id);
		model.addAttribute("success", "Pergunta excluída com sucesso.");		
		return "redirect:/perguntas/filtrar/?id_questionario="+id_questionario.toString();
	}

	@GetMapping("/filtrar")
	public String filtrarArtigo(HttpServletRequest request, ModelMap model) {
		
		
		int currentPage = 1;
        int pageSize = 20;        
        String descricao = null;
        Long id_questionario = null;
        Page<PerguntaView> perguntaPage = null;
        
        if(request.getParameter("size") != null && request.getParameter("size").length() > 0) {
        	pageSize = Integer.parseInt(request.getParameter("size"));
        }
        if(request.getParameter("page") != null && request.getParameter("page").length() > 0) {
        	currentPage = Integer.parseInt(request.getParameter("page"));
        }      
        
        if((request.getParameter("descricao") != null) && request.getParameter("descricao").length() > 0) {
        	descricao = request.getParameter("descricao");
        	model.addAttribute("descricao", descricao);
        } 		
		
        if(request.getParameter("id_questionario") != null && request.getParameter("id_questionario").length() > 0) {
        	id_questionario = Long.parseLong(request.getParameter("id_questionario"));
    	    model.addAttribute("questionarioDTO", questionarioService.buscarPorId(id_questionario));
        }                        
        
		if(id_questionario != null){
			perguntaPage = perguntaService.buscarPorQuestionario(id_questionario, PageRequest.of(currentPage-1, pageSize));			
		}	
		else{
			perguntaPage = perguntaService.buscarTodos(PageRequest.of(currentPage-1, pageSize));
		} 		
		
		int totalPages = perguntaPage.getTotalPages();
		model.addAttribute("paginaAtual", perguntaPage.getNumber()+1);
	    if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
	    }		
	    
	    model.addAttribute("perguntas", perguntaPage);	    
				
		return "pergunta/lista";
	}		
	
}
