package br.gov.pa.fscm.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "opcao")
public class Opcao extends AbstractPersistable<Long> {

	@Column(name = "descricao", nullable = true, unique = false, length = 500 )
	private String descricao;
	
	@Column(name = "valor", nullable = true, unique = false, length = 10)
	private Integer valor;		
	
	@ManyToOne
	@JoinColumn(name = "id_tiporesposta_fk", nullable = true)
	private Tiporesposta tiporesposta;			
	
	@OneToMany(mappedBy = "opcao")
	private List<Resposta> respostas;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Tiporesposta getTiporesposta() {
		return tiporesposta;
	}

	public void setTiporesposta(Tiporesposta tiporesposta) {
		this.tiporesposta = tiporesposta;
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
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((respostas == null) ? 0 : respostas.hashCode());
		result = prime * result + ((tiporesposta == null) ? 0 : tiporesposta.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Opcao other = (Opcao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (respostas == null) {
			if (other.respostas != null)
				return false;
		} else if (!respostas.equals(other.respostas))
			return false;
		if (tiporesposta == null) {
			if (other.tiporesposta != null)
				return false;
		} else if (!tiporesposta.equals(other.tiporesposta))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Opcao [descricao=" + descricao + ", valor=" + valor + ", tiporesposta=" + tiporesposta + "]";
	}			

	
	
}
