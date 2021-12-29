package br.gov.pa.fscm.projection;

import org.springframework.beans.factory.annotation.Value;

public interface PerguntaView {
	
	public Long getId();
	
	public String getTitulo();
	
	public String getDescricao();
	
	public Integer getPeso();
	
	@Value("#{target.tiporesposta.id}") 
	public Long getIdTiporesposta();
	
	@Value("#{target.tiporesposta.descricao}") 
	public String getDescricaoTiporesposta();
}
