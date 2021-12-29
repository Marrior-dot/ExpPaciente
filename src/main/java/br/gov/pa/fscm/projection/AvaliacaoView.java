package br.gov.pa.fscm.projection;

import java.time.LocalDate;

public interface AvaliacaoView {

	public Long getId();
	
	public Boolean getFinalizada();
	
	public LocalDate getDhinicio();	
	
}
