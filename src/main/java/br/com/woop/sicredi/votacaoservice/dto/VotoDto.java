package br.com.woop.sicredi.votacaoservice.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VotoDto implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long votoCodigo;

    private Long associadoCodigo;

	private Boolean opcao;

	private LocalDateTime dataCriacao;

	public VotoDto() {
		super();
	}

	public VotoDto(Long votoCodigo, Long associadoCodigo, Boolean opcao, LocalDateTime dataCriacao) {
		super();
		this.votoCodigo = votoCodigo;
		this.associadoCodigo = associadoCodigo;
		this.opcao = opcao;
		this.dataCriacao = dataCriacao;
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

	public Long getVotoCodigo() {
		return votoCodigo;
	}

	public void setVotoCodigo(Long votoCodigo) {
		this.votoCodigo = votoCodigo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public String toString() {
		return "VotoDto [votoCodigo=" + votoCodigo + ", associadoCodigo=" + associadoCodigo + ", opcao=" + opcao
				+ ", dataCriacao=" + dataCriacao + "]";
	}
}
