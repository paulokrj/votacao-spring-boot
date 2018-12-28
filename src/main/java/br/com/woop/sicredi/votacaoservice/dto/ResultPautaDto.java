package br.com.woop.sicredi.votacaoservice.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultPautaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long pautaCodigo;

	private String descricao;

	private Map<Boolean, Long> result = new HashMap<>();

	private Map<Boolean, Long> percentage = new HashMap<>();

	private Long totalVotos;

	public ResultPautaDto() {
		super();
	}

	public ResultPautaDto(Long pautaCodigo, String descricao, Long totalVotos) {
		super();
		this.pautaCodigo = pautaCodigo;
		this.descricao = descricao;
		this.totalVotos = totalVotos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getPautaCodigo() {
		return pautaCodigo;
	}

	public void setPautaCodigo(Long pautaCodigo) {
		this.pautaCodigo = pautaCodigo;
	}

	public Map<Boolean, Long> getResult() {
		return result;
	}

	public void setResult(Map<Boolean, Long> result) {
		this.result = result;
	}

	public Long getTotalVotos() {
		return totalVotos;
	}

	public void setTotalVotos(Long totalVotos) {
		this.totalVotos = totalVotos;
	}

	public Map<Boolean, Long> getPercentage() {
		return percentage;
	}

	public void setPercentage(Map<Boolean, Long> percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "ResultPautaDto [pautaCodigo=" + pautaCodigo + ", descricao=" + descricao + ", result=" + result
				+ ", totalVotos=" + totalVotos + "]";
	}
}
