package br.gov.pa.fscm.web.controller;



import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.gov.pa.fscm.service.JasperService;

@Controller
public class JasperController {

	@Autowired
	private JasperService jasperService;
	
	@GetMapping("/relatorios/listar")
	public String abrir() {
		return "relatorio/lista";
	}
	
	@GetMapping("/relatorios/pdf/jr1")
	public void exibirRelatorio(@RequestParam("code") String code, @RequestParam("acao") String acao, HttpServletResponse response) throws IOException {
		byte[] bytes = jasperService.exportarPDF(code);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		if(acao.equals("v")) {
			response.setHeader("Contente-disposition", "inline; filename=relatorio-" + code + ".pdf");			
		}
		else{
			response.setHeader("Contente-disposition", "attachment; filename=relatorio-" + code + ".pdf");
		}
		response.getOutputStream().write(bytes);
	}
	
	@GetMapping("/relatorios/pdf/jr2/{code}")
	public void exibirRelatorio2(@PathVariable("code") String code, @RequestParam("mes") String mes, HttpServletResponse response) throws IOException {
		
		jasperService.addParams("FILTRO_MES", mes);
		byte[] bytes = jasperService.exportarPDF(code);
		
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		response.setHeader("Contente-disposition", "inline; filename=relatorio-" + code + ".pdf");			
		response.getOutputStream().write(bytes);
	}	
}
