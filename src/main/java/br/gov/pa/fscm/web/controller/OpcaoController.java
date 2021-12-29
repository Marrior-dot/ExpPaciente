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

import br.gov.pa.fscm.projection.OpcaoDTO;
import br.gov.pa.fscm.projection.OpcaoView;
import br.gov.pa.fscm.service.OpcaoService;
import br.gov.pa.fscm.service.TiporespostaService;

@Controller
@RequestMapping("opcaos")
public class OpcaoController {

	@Autowired
	OpcaoService opcaoService;
	
	@Autowired
	TiporespostaService tiporespostaService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(OpcaoDTO opcaoDTO, ModelMap model, HttpServletRequest request) {
        Long id_tiporesposta = null;
		if(request.getParameter("id_tiporesposta") != null && request.getParameter("id_tiporesposta").length() > 0) {
        	id_tiporesposta = Long.parseLong(request.getParameter("id_tiporesposta"));
    	    model.addAttribute("tiporespostaDTO", tiporespostaService.buscarPorId(id_tiporesposta));
        } 		
		return "opcao/cadastro";
	}	
	
	
	@PostMapping("/salvar")
	public String cadastrar(Long id_tiporesposta, @Valid OpcaoDTO opcaoDTO, BindingResult result, ModelMap model, RedirectAttributes attr) {

		model.addAttribute("tiporespostaDTO", tiporespostaService.buscarPorId(id_tiporesposta));
		model.addAttribute("id_questionario", id_tiporesposta);	
		
		if(result.hasErrors()) {
			return "opcao/cadastro";
		}						
		
		opcaoService.salvar(opcaoDTO, id_tiporesposta);	
		
		attr.addFlashAttribute("success", "Opcao cadastrada com sucesso.");		
		return "redirect:/opcaos/filtrar/?id_tiporesposta="+id_tiporesposta.toString();		
	}	
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		
		int currentPage = 1;
        int pageSize = 5;
            
        Page<OpcaoView> opcaoPage = opcaoService.buscarTodos(PageRequest.of(currentPage-1, pageSize));
        
		int totalPages = opcaoPage.getTotalPages();
		model.addAttribute("paginaAtual", opcaoPage.getNumber()+1);
	    if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
	    }	    	    
		model.addAttribute("opcaos", opcaoPage);
		return "opcao/lista";
	}	
	
	@GetMapping("/editar/{id}/{id_tiporesposta}")
	public String preEditar(@PathVariable("id") Long id, @PathVariable("id_tiporesposta") Long id_tiporesposta, ModelMap model) {
		
		model.addAttribute("tiporespostaDTO", tiporespostaService.buscarPorId(id_tiporesposta));
		model.addAttribute("id_tiporesposta", id_tiporesposta);
		model.addAttribute("opcaoDTO", opcaoService.buscarPorId(id));	
		return "opcao/cadastro";
	}	
	
	@PostMapping("/editar")
	public String editar(Long id, Long id_tiporesposta, @Valid OpcaoDTO opcaoDTO, BindingResult result, ModelMap model, RedirectAttributes attr) {							
			
		if(result.hasErrors()) {
			return "opcao/cadastro";
		}
		opcaoDTO.setId(id);
		opcaoService.editar(opcaoDTO);
	
		
		attr.addFlashAttribute("success", "Opcao editada com sucesso.");
		return "redirect:/opcaos/filtrar/?id_tiporesposta="+id_tiporesposta.toString();
	}		

	@GetMapping("/excluir/{id}/{id_tiporesposta}")
	public String excluir(@PathVariable("id") Long id, @PathVariable("id_tiporesposta") Long id_tiporesposta, ModelMap model) {			
		opcaoService.excluir(id);
		model.addAttribute("success", "Opcao excluída com sucesso.");		
		return "redirect:/opcaos/filtrar/?id_tiporesposta="+id_tiporesposta.toString();
	}

	@GetMapping("/filtrar")
	public String filtrarArtigo(HttpServletRequest request, ModelMap model) {
		
		
		int currentPage = 1;
        int pageSize = 20;        
        String descricao = null;
        Page<OpcaoView> opcaoPage = null;
        Long id_tiporesposta = null;
        
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
		
        if(request.getParameter("id_tiporesposta") != null && request.getParameter("id_tiporesposta").length() > 0) {
        	id_tiporesposta = Long.parseLong(request.getParameter("id_tiporesposta"));
    	    model.addAttribute("tiporespostaDTO", tiporespostaService.buscarPorId(id_tiporesposta));
        }                        
        
		if(id_tiporesposta != null){
			opcaoPage = opcaoService.buscarPorTiporesposta(id_tiporesposta, PageRequest.of(currentPage-1, pageSize));
			model.addAttribute("opcaoDTO", opcaoService.buscarPorId(id_tiporesposta));
		}	
		else{
			opcaoPage = opcaoService.buscarTodos(PageRequest.of(currentPage-1, pageSize));
		} 			
						
		int totalPages = opcaoPage.getTotalPages();
		model.addAttribute("paginaAtual", opcaoPage.getNumber()+1);
	    if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
	    }		
	    
	    model.addAttribute("opcaos", opcaoPage);
				
		return "opcao/lista";
	}		
	
}
