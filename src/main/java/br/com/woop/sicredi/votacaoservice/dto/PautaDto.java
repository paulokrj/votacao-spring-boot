package br.com.woop.sicredi.votacaoservice.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PautaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long pautaCodigo;

	private String descricao;

	private String observacao;
	
	private LocalDateTime dataCriacao;

	private LocalDateTime dataInicioVotacao;

	private LocalDateTime dataFimVotacao;

	public PautaDto() {
		super();
	}

	public PautaDto(Long pautaCodigo, String descricao, String observacao, LocalDateTime dataCriacao,
			LocalDateTime dataInicioVotacao, LocalDateTime dataFimVotacao) {
		super();
		this.pautaCodigo = pautaCodigo;
		this.descricao = descricao;
		this.observacao = observacao;
		this.dataCriacao = dataCriacao;
		this.dataInicioVotacao = dataInicioVotacao;
		this.dataFimVotacao = dataFimVotacao;
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

	public Long getPautaCodigo() {
		return pautaCodigo;
	}

	public void setPautaCodigo(Long pautaCodigo) {
		this.pautaCodigo = pautaCodigo;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataInicioVotacao() {
		return dataInicioVotacao;
	}

	public void setDataInicioVotacao(LocalDateTime dataInicioVotacao) {
		this.dataInicioVotacao = dataInicioVotacao;
	}

	public LocalDateTime getDataFimVotacao() {
		return dataFimVotacao;
	}

	public void setDataFimVotacao(LocalDateTime dataFimVotacao) {
		this.dataFimVotacao = dataFimVotacao;
	}

	@Override
	public String toString() {
		return "PautaDto [pautaCodigo=" + pautaCodigo + ", descricao=" + descricao + ", observacao=" + observacao
				+ ", dataCriacao=" + dataCriacao + ", dataInicioVotacao=" + dataInicioVotacao + ", dataFimVotacao="
				+ dataFimVotacao + "]";
	}
}
