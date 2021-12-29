package br.gov.pa.fscm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pergunta")

public class Pergunta  extends AbstractPersistable<Long>{
	
	@Column(name = "titulo", nullable = true, unique = false, length = 1000 )
	private String titulo;
	
	@Column(name = "descricao", nullable = true, unique = false, length = 1000 )
	private String descricao;
	
	@Column(name = "peso", nullable = true, unique = false, length = 10)
	private int peso;		
	
	@ManyToOne
	@JoinColumn(name = "id_questionario_fk", nullable = true)
	private Questionario questionario;	

	@ManyToOne
	@JoinColumn(name = "id_tiporesposta_fk", nullable = true)
	private Tiporesposta tiporesposta;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public Tiporesposta getTiporesposta() {
		return tiporesposta;
	}

	public void setTiporesposta(Tiporesposta tiporesposta) {
		this.tiporesposta = tiporesposta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + peso;
		result = prime * result + ((questionario == null) ? 0 : questionario.hashCode());
		result = prime * result + ((tiporesposta == null) ? 0 : tiporesposta.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Pergunta other = (Pergunta) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (peso != other.peso)
			return false;
		if (questionario == null) {
			if (other.questionario != null)
				return false;
		} else if (!questionario.equals(other.questionario))
			return false;
		if (tiporesposta == null) {
			if (other.tiporesposta != null)
				return false;
		} else if (!tiporesposta.equals(other.tiporesposta))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pergunta [titulo=" + titulo + ", descricao=" + descricao + ", peso=" + peso + ", questionario="
				+ questionario + ", tiporesposta=" + tiporesposta + "]";
	}		
	
	
	
}
