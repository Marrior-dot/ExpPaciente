package br.gov.pa.fscm.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import br.gov.pa.fscm.projection.QuestionarioDTO;
import br.gov.pa.fscm.projection.QuestionarioView;
import br.gov.pa.fscm.service.QuestionarioService;


@Controller
@RequestMapping("questionarios")
public class QuestionarioController {

	@Autowired
	QuestionarioService questionarioService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(QuestionarioDTO questionarioDTO, ModelMap model) {
		return "questionario/cadastro";
	}	
	
	
	@PostMapping("/salvar")
	public String cadastrar(@Valid QuestionarioDTO questionarioDTO, BindingResult result, ModelMap model, RedirectAttributes attr) {

		if(result.hasErrors()) {
			return "questionario/cadastro";
		}						
		
		questionarioService.salvar(questionarioDTO);	
		
		attr.addFlashAttribute("success", "Questionario cadastrado com sucesso.");		
		return "redirect:/questionarios/listar";		
	}	
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		int currentPage = 1;
        int pageSize = 20;
            
        Page<QuestionarioView> questionarioPage = questionarioService.buscarTodos(PageRequest.of(currentPage-1, pageSize));
        
		int totalPages = questionarioPage.getTotalPages();
		model.addAttribute("paginaAtual", questionarioPage.getNumber()+1);
	    if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
	    }	    	    
		model.addAttribute("questionarios", questionarioPage);
		return "questionario/lista";
	}	
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		QuestionarioDTO questionarioDTO = questionarioService.buscarPorId(id);				
		model.addAttribute("questionarioDTO", questionarioDTO);	
		return "questionario/cadastro";
	}	
	
	@PostMapping("/editar")
	public String editar(@Valid QuestionarioDTO questionarioDTO, Long id, BindingResult result, ModelMap model, RedirectAttributes attr) {							
				
		if(result.hasErrors()) {
			return "questionario/cadastro";
		}
		questionarioDTO.setId(id);
		questionarioService.editar(questionarioDTO);
	
		
		attr.addFlashAttribute("success", "Questionário editado com sucesso.");
		return "redirect:/questionarios/listar";
	}		

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {			
		questionarioService.excluir(id);
		model.addAttribute("success", "Questionário excluído com sucesso.");		
		return listar(model);
	}

	@GetMapping("/filtrar")
	public String filtrarArtigo(HttpServletRequest request, ModelMap model) {
		
		
		int currentPage = 1;
        int pageSize = 20;        
        String descricao = null;
        Page<QuestionarioView> questionarioPage = null;
        
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
		
		if(descricao != null){
			//questionarioPage = questionarioService.buscarPorDescricao(descricao, PageRequest.of(currentPage-1, pageSize));
		}	
		else{
			questionarioPage = questionarioService.buscarTodos(PageRequest.of(currentPage-1, pageSize));
		}	
		
		int totalPages = questionarioPage.getTotalPages();
		model.addAttribute("paginaAtual", questionarioPage.getNumber()+1);
	    if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
	    }		
	    
	    model.addAttribute("questionarios", questionarioPage);		
				
		return "questionario/lista";
	}		
	
}
