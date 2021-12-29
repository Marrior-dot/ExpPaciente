package br.gov.pa.fscm.projection;

import org.springframework.beans.factory.annotation.Value;

public interface RespostaView {

	public Long getId();
	
	public Integer getValor();
	
	public String getDescricao();
	
	@Value("#{target.opcao.descricao}") 
	public Long getDescricaoOpcao();
	
	@Value("#{target.opcao.tiporesposta.descricao}") 
	public String getDescricaoTiporesposta();	

	@Value("#{target.pergunta.descricao}") 
	public Long getDescricaoPergunta();
	
	@Value("#{target.pergunta.titulo}") 
	public Long getTituloPergunta();
	
	@Value("#{target.pergunta.questionario.descricao}") 
	public String getDescricaoQuestionario();	
}
