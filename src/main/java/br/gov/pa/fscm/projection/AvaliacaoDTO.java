package br.gov.pa.fscm.projection;

import java.time.LocalDateTime;

public class AvaliacaoDTO {
	
	private Long id;

    private Boolean finalizada;
    
    private LocalDateTime dhinicio;

	public AvaliacaoDTO(Long id, Boolean finalizada, LocalDateTime dhinicio) {
		this.id = id;
		this.finalizada = finalizada;
		this.dhinicio = dhinicio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}

	public LocalDateTime getDhinicio() {
		return dhinicio;
	}

	public void setDhinicio(LocalDateTime dhinicio) {
		this.dhinicio = dhinicio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dhinicio == null) ? 0 : dhinicio.hashCode());
		result = prime * result + ((finalizada == null) ? 0 : finalizada.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvaliacaoDTO other = (AvaliacaoDTO) obj;
		if (dhinicio == null) {
			if (other.dhinicio != null)
				return false;
		} else if (!dhinicio.equals(other.dhinicio))
			return false;
		if (finalizada == null) {
			if (other.finalizada != null)
				return false;
		} else if (!finalizada.equals(other.finalizada))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AvaliacaoDTO [id=" + id + ", finalizada=" + finalizada + ", dhinicio=" + dhinicio + "]";
	}
       



    
}
