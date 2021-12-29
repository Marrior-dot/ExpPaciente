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

import br.gov.pa.fscm.projection.TiporespostaDTO;
import br.gov.pa.fscm.projection.TiporespostaView;
import br.gov.pa.fscm.service.TiporespostaService;

@Controller
@RequestMapping("tiporespostas")
public class TiporespostaController {

	@Autowired
	TiporespostaService tiporespostaService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(TiporespostaDTO tiporespostaDTO, ModelMap model) {
		return "tiporesposta/cadastro";
	}	
	
	
	@PostMapping("/salvar")
	public String cadastrar(@Valid TiporespostaDTO tiporespostaDTO, BindingResult result, ModelMap model, RedirectAttributes attr) {

		if(result.hasErrors()) {
			return "tiporesposta/cadastro";
		}						
		
		tiporespostaService.salvar(tiporespostaDTO);	
		
		attr.addFlashAttribute("success", "Tiporesposta cadastrada com sucesso.");		
		return "redirect:/tiporespostas/listar";		
	}	
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		int currentPage = 1;
        int pageSize = 20;
            
        Page<TiporespostaView> tiporespostaPage = tiporespostaService.buscarTodos(PageRequest.of(currentPage-1, pageSize));
        
		int totalPages = tiporespostaPage.getTotalPages();
		model.addAttribute("paginaAtual", tiporespostaPage.getNumber()+1);
	    if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
	    }	    	    
		model.addAttribute("tiporespostas", tiporespostaPage);
		return "tiporesposta/lista";
	}	
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		TiporespostaDTO tiporespostaDTO = tiporespostaService.buscarPorId(id);				
		model.addAttribute("tiporespostaDTO", tiporespostaDTO);	
		return "tiporesposta/cadastro";
	}	
	
	@PostMapping("/editar")
	public String editar(@Valid TiporespostaDTO tiporespostaDTO, Long id, BindingResult result, ModelMap model, RedirectAttributes attr) {							
				
		if(result.hasErrors()) {
			return "tiporesposta/cadastro";
		}
		tiporespostaDTO.setId(id);
		tiporespostaService.editar(tiporespostaDTO);
	
		
		attr.addFlashAttribute("success", "Tiporesposta editada com sucesso.");
		return "redirect:/tiporespostas/listar";
	}		

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {			
		tiporespostaService.excluir(id);
		model.addAttribute("success", "Tiporesposta excluída com sucesso.");		
		return listar(model);
	}

	@GetMapping("/filtrar")
	public String filtrarTiporesposta(HttpServletRequest request, ModelMap model) {
		
		
		int currentPage = 1;
        int pageSize = 20;        
        String descricao = null;
        Page<TiporespostaView> tiporespostaPage = null;
        
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
			//tiporespostaPage = tiporespostaService.buscarPorDescricao(descricao, PageRequest.of(currentPage-1, pageSize));
		}	
		else{
			tiporespostaPage = tiporespostaService.buscarTodos(PageRequest.of(currentPage-1, pageSize));
		} 		
		
		int totalPages = tiporespostaPage.getTotalPages();
		model.addAttribute("paginaAtual", tiporespostaPage.getNumber()+1);
	    if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
	    }		
	    
	    model.addAttribute("tiporespostas", tiporespostaPage);		
				
		return "tiporesposta/lista";
	}		
	
}
