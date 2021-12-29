package br.gov.pa.fscm.projection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class QuestionarioDTO {
	
	private Long id;
	
    @NotBlank(message="Informe um título.")    
    private String titulo;

    @Size(min = 3, max = 1000, message = "O conteúdo deve ter entre {min} e {max} caracteres.")
    private String descricao;
    
    private Boolean publicado;

	public QuestionarioDTO(Long id, String titulo, String descricao, Boolean publicado) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.publicado = publicado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getPublicado() {
		return publicado;
	}

	public void setPublicado(Boolean publicado) {
		this.publicado = publicado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((publicado == null) ? 0 : publicado.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		QuestionarioDTO other = (QuestionarioDTO) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (publicado == null) {
			if (other.publicado != null)
				return false;
		} else if (!publicado.equals(other.publicado))
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
		return "QuestionarioDTO [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", publicado="
				+ publicado + "]";
	}
    
    
}
