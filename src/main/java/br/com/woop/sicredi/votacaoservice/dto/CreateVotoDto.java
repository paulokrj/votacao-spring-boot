package br.com.woop.sicredi.votacaoservice.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateVotoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Pauta não pode ser nula")
	@Min(value = 1, message = "Codigo da pauta deve ser maior que zero")
	private Long pautaCodigo;

	@NotNull(message = "Associado não pode ser nulo")
	@Min(value = 1, message = "Codigo do associado deve ser maior que zero")
	private Long associadoCodigo;
	private Boolean opcao;

	public CreateVotoDto() {
		super();
	}

	public CreateVotoDto(Long pautaCodigo, Long associadoCodigo, Boolean opcao) {
		super();
		this.pautaCodigo = pautaCodigo;
		this.associadoCodigo = associadoCodigo;
		this.opcao = opcao;
	}

	public Long getPautaCodigo() {
		return pautaCodigo;
	}

	public void setPautaCodigo(Long pautaCodigo) {
		this.pautaCodigo = pautaCodigo;
	}

	public Long getAssociadoCodigo() {
		return associadoCodigo;
	}

	public void setAssociadoCodigo(Long associadoCodigo) {
		this.associadoCodigo = associadoCodigo;
	}

	public Boolean getOpcao() {
		return opcao;
	}

	public void setOpcao(Boolean opcao) {
		this.opcao = opcao;
	}

	@Override
	public String toString() {
		return "VotoDto [pautaCodigo=" + pautaCodigo + ", associadoCodigo=" + associadoCodigo + ", opcao=" + opcao
				+ "]";
	}
}
