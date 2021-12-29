package br.gov.pa.fscm.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class JasperService {

	private static final String JASPER_DIRETORIO = "/jasper/";
	private static final String JASPER_PREFIXO = "experiencia-usuario-";
	private static final String JASPER_SUFIXO = ".jrxml";
	
	@Autowired
	private Connection connection;
	
	private Map<String, Object> params = new HashMap<>();
	
	public void addParams(String key, Object value) {
		this.params.put(key,value);
	}
	
	public byte[] exportarPDF(String code) {
		byte[] bytes = null;		
		try {
			InputStream jasper = this.getClass().getResourceAsStream(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
			JasperReport jasperReport = JasperCompileManager.compileReport(jasper);
			JasperPrint print = JasperFillManager.fillReport(jasperReport, params, connection);
			bytes = JasperExportManager.exportReportToPdf(print);
		} catch ( JRException e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
}
