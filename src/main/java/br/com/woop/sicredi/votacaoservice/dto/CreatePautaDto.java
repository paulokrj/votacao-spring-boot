package br.com.woop.sicredi.votacaoservice.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class CreatePautaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Descrição não pode ser nula")
	private String descricao;

	private String observacao;

	public CreatePautaDto() {
		super();
	}

	public CreatePautaDto(String descricao, String observacao) {
		super();
		this.descricao = descricao;
		this.observacao = observacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "PautaDto [descricao=" + descricao + ", observacao=" + observacao + "]";
	}
}
