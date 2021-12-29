package br.gov.pa.fscm.projection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.gov.pa.fscm.domain.Avaliacao;
import br.gov.pa.fscm.domain.Opcao;
import br.gov.pa.fscm.domain.Pergunta;

public class RespostaDTO {

	private Long id;

    //@Size(min = 3, max = 500, message = "O conteúdo deve ter entre {min} e {max} caracteres.")
    private String descricao;
    
    //@NotNull(message="Informe um valor.")    
    private Integer valor;
    
	private Opcao opcao;
	
	private Pergunta pergunta;
	
	private Avaliacao avaliacao;
	
	private Long id_opcao;
	
	private Long id_pergunta;
	
	private Long id_tiporesposta;
	
	private Long id_questionario;
	
	private Long id_avaliacao;
	
	private String desc_opcao;
	
	private String desc_pergunta;
	
	private String desc_tiporesposta;
	
	private String desc_questionario;

	public RespostaDTO(Long id,
			@Size(min = 3, max = 500, message = "O conteúdo deve ter entre {min} e {max} caracteres.") String descricao,
			@NotNull(message = "Informe um valor.") Integer valor, Opcao opcao, Pergunta pergunta, Avaliacao avaliacao) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		
		if(opcao != null && pergunta != null && avaliacao != null) {
			this.opcao = opcao;
			this.pergunta = pergunta;
			this.avaliacao = avaliacao;
			this.id_opcao = opcao.getId();
			this.id_pergunta = pergunta.getId();
			this.id_tiporesposta = opcao.getTiporesposta().getId();
			this.id_questionario = pergunta.getQuestionario().getId();
			this.id_avaliacao = avaliacao.getId();
			this.desc_opcao = opcao.getDescricao();
			this.desc_pergunta = pergunta.getDescricao();
			this.desc_tiporesposta = opcao.getTiporesposta().getDescricao();
			this.desc_questionario = pergunta.getQuestionario().getDescricao();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Opcao getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcao opcao) {
		this.opcao = opcao;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Long getId_opcao() {
		return id_opcao;
	}

	public void setId_opcao(Long id_opcao) {
		this.id_opcao = id_opcao;
	}

	public Long getId_pergunta() {
		return id_pergunta;
	}

	public void setId_pergunta(Long id_pergunta) {
		this.id_pergunta = id_pergunta;
	}

	public Long getId_tiporesposta() {
		return id_tiporesposta;
	}

	public void setId_tiporesposta(Long id_tiporesposta) {
		this.id_tiporesposta = id_tiporesposta;
	}

	public Long getId_questionario() {
		return id_questionario;
	}

	public void setId_questionario(Long id_questionario) {
		this.id_questionario = id_questionario;
	}

	public Long getId_avaliacao() {
		return id_avaliacao;
	}

	public void setId_avaliacao(Long id_avaliacao) {
		this.id_avaliacao = id_avaliacao;
	}

	public String getDesc_opcao() {
		return desc_opcao;
	}

	public void setDesc_opcao(String desc_opcao) {
		this.desc_opcao = desc_opcao;
	}

	public String getDesc_pergunta() {
		return desc_pergunta;
	}

	public void setDesc_pergunta(String desc_pergunta) {
		this.desc_pergunta = desc_pergunta;
	}

	public String getDesc_tiporesposta() {
		return desc_tiporesposta;
	}

	public void setDesc_tiporesposta(String desc_tiporesposta) {
		this.desc_tiporesposta = desc_tiporesposta;
	}

	public String getDesc_questionario() {
		return desc_questionario;
	}

	public void setDesc_questionario(String desc_questionario) {
		this.desc_questionario = desc_questionario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avaliacao == null) ? 0 : avaliacao.hashCode());
		result = prime * result + ((desc_opcao == null) ? 0 : desc_opcao.hashCode());
		result = prime * result + ((desc_pergunta == null) ? 0 : desc_pergunta.hashCode());
		result = prime * result + ((desc_questionario == null) ? 0 : desc_questionario.hashCode());
		result = prime * result + ((desc_tiporesposta == null) ? 0 : desc_tiporesposta.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_avaliacao == null) ? 0 : id_avaliacao.hashCode());
		result = prime * result + ((id_opcao == null) ? 0 : id_opcao.hashCode());
		result = prime * result + ((id_pergunta == null) ? 0 : id_pergunta.hashCode());
		result = prime * result + ((id_questionario == null) ? 0 : id_questionario.hashCode());
		result = prime * result + ((id_tiporesposta == null) ? 0 : id_tiporesposta.hashCode());
		result = prime * result + ((opcao == null) ? 0 : opcao.hashCode());
		result = prime * result + ((pergunta == null) ? 0 : pergunta.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		RespostaDTO other = (RespostaDTO) obj;
		if (avaliacao == null) {
			if (other.avaliacao != null)
				return false;
		} else if (!avaliacao.equals(other.avaliacao))
			return false;
		if (desc_opcao == null) {
			if (other.desc_opcao != null)
				return false;
		} else if (!desc_opcao.equals(other.desc_opcao))
			return false;
		if (desc_pergunta == null) {
			if (other.desc_pergunta != null)
				return false;
		} else if (!desc_pergunta.equals(other.desc_pergunta))
			return false;
		if (desc_questionario == null) {
			if (other.desc_questionario != null)
				return false;
		} else if (!desc_questionario.equals(other.desc_questionario))
			return false;
		if (desc_tiporesposta == null) {
			if (other.desc_tiporesposta != null)
				return false;
		} else if (!desc_tiporesposta.equals(other.desc_tiporesposta))
			return false;
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
		if (id_avaliacao == null) {
			if (other.id_avaliacao != null)
				return false;
		} else if (!id_avaliacao.equals(other.id_avaliacao))
			return false;
		if (id_opcao == null) {
			if (other.id_opcao != null)
				return false;
		} else if (!id_opcao.equals(other.id_opcao))
			return false;
		if (id_pergunta == null) {
			if (other.id_pergunta != null)
				return false;
		} else if (!id_pergunta.equals(other.id_pergunta))
			return false;
		if (id_questionario == null) {
			if (other.id_questionario != null)
				return false;
		} else if (!id_questionario.equals(other.id_questionario))
			return false;
		if (id_tiporesposta == null) {
			if (other.id_tiporesposta != null)
				return false;
		} else if (!id_tiporesposta.equals(other.id_tiporesposta))
			return false;
		if (opcao == null) {
			if (other.opcao != null)
				return false;
		} else if (!opcao.equals(other.opcao))
			return false;
		if (pergunta == null) {
			if (other.pergunta != null)
				return false;
		} else if (!pergunta.equals(other.pergunta))
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
		return "RespostaDTO [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", opcao=" + opcao
				+ ", pergunta=" + pergunta + ", avaliacao=" + avaliacao + ", id_opcao=" + id_opcao + ", id_pergunta="
				+ id_pergunta + ", id_tiporesposta=" + id_tiporesposta + ", id_questionario=" + id_questionario
				+ ", id_avaliacao=" + id_avaliacao + ", desc_opcao=" + desc_opcao + ", desc_pergunta=" + desc_pergunta
				+ ", desc_tiporesposta=" + desc_tiporesposta + ", desc_questionario=" + desc_questionario + "]";
	}


	
	
}
