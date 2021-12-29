package br.gov.pa.fscm.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tiporesposta")
public class Tiporesposta  extends AbstractPersistable<Long>{

	@Column(name = "descricao", nullable = true, unique = false, length = 500 )
	private String descricao;	
	
	@OneToMany(mappedBy = "tiporesposta")
	private List<Pergunta> perguntas;
	
	@OneToMany(mappedBy = "tiporesposta")
	private List<Opcao> opcoes;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Pergunta> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	public List<Opcao> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Opcao> opcoes) {
		this.opcoes = opcoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((opcoes == null) ? 0 : opcoes.hashCode());
		result = prime * result + ((perguntas == null) ? 0 : perguntas.hashCode());
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
		Tiporesposta other = (Tiporesposta) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (opcoes == null) {
			if (other.opcoes != null)
				return false;
		} else if (!opcoes.equals(other.opcoes))
			return false;
		if (perguntas == null) {
			if (other.perguntas != null)
				return false;
		} else if (!perguntas.equals(other.perguntas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tiporesposta [descricao=" + descricao + ", perguntas=" + perguntas + ", opcoes=" + opcoes + "]";
	}	
	
	
}
