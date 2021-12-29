package br.gov.pa.fscm.projection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.gov.pa.fscm.domain.Tiporesposta;

public class PerguntaDTO {
	private Long id;
	
    @NotBlank(message="Informe um título.")    
    private String titulo;

    @Size(min = 3, max = 1000, message = "O conteúdo deve ter entre {min} e {max} caracteres.")
    private String descricao;
    
    @NotNull(message="Informe um peso.")    
    private Integer peso;
    
    private Tiporesposta tiporesposta;
    
    private Long id_tiporesposta;

	public PerguntaDTO(Long id, String titulo, String descricao, Integer peso, Tiporesposta tiporesposta) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.peso = peso;
		this.tiporesposta = tiporesposta;
		if(this.tiporesposta !=  null) {
			this.id_tiporesposta = tiporesposta.getId();
		}
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

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Tiporesposta getTiporesposta() {
		return tiporesposta;
	}

	public void setTiporesposta(Tiporesposta tiporesposta) {
		this.tiporesposta = tiporesposta;
	}

	
	public Long getId_tiporesposta() {
		return id_tiporesposta;
	}

	public void setId_tiporesposta(Long id_tiporesposta) {
		this.id_tiporesposta = id_tiporesposta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		result = prime * result + ((tiporesposta == null) ? 0 : tiporesposta.hashCode());
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
		PerguntaDTO other = (PerguntaDTO) obj;
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
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
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
		return "PerguntaDTO [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", peso=" + peso + "]";
	}

	
}
