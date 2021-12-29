package br.gov.pa.fscm.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "avaliacao")
public class Avaliacao  extends AbstractPersistable<Long>{

	@Column(name = "dhinicio", nullable = false, unique = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dhinicio = LocalDateTime.now();
	  
	@Column(name = "finalizada", nullable = false, unique = false)
    private Boolean finalizada = Boolean.FALSE;
	
	@OneToMany(mappedBy = "avaliacao")
	private List<Resposta> respostas;

	public LocalDateTime getDhinicio() {
		return dhinicio;
	}

	public void setDhinicio(LocalDateTime dhinicio) {
		this.dhinicio = dhinicio;
	}

	public Boolean getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dhinicio == null) ? 0 : dhinicio.hashCode());
		result = prime * result + ((finalizada == null) ? 0 : finalizada.hashCode());
		result = prime * result + ((respostas == null) ? 0 : respostas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
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
		if (respostas == null) {
			if (other.respostas != null)
				return false;
		} else if (!respostas.equals(other.respostas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Avaliacao [dhinicio=" + dhinicio + ", finalizada=" + finalizada + ", respostas=" + respostas + "]";
	}


}
