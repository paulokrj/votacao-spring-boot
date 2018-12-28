package br.com.woop.sicredi.votacaoservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "pauta")
public class Pauta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pauta_codigo")
    private Long pautaCodigo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

	@Column(name = "data_inicio_votacao")
	private LocalDateTime dataInicioVotacao;

	@Column(name = "data_fim_votacao")
	private LocalDateTime dataFimVotacao;

	@OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Voto> votoList = new ArrayList<>();

	public Pauta() {
		super();
	}

	public Pauta(Long pautaCodigo, String descricao, String observacao, LocalDateTime dataCriacao,
			LocalDateTime dataInicioVotacao, LocalDateTime dataFimVotacao) {
		super();
		this.pautaCodigo = pautaCodigo;
		this.descricao = descricao;
		this.observacao = observacao;
		this.dataCriacao = dataCriacao;
		this.dataInicioVotacao = dataInicioVotacao;
		this.dataFimVotacao = dataFimVotacao;
	}
	
	@PrePersist
	private void prePersist() {
		this.dataCriacao = LocalDateTime.now();
	}

	public Long getPautaCodigo() {
		return pautaCodigo;
	}

	public void setPautaCodigo(Long pautaCodigo) {
		this.pautaCodigo = pautaCodigo;
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

	public List<Voto> getVotoList() {
		return votoList;
	}

	public void setVotoList(List<Voto> votoList) {
		this.votoList = votoList;
	}

	@Override
	public String toString() {
		return "Pauta [pautaCodigo=" + pautaCodigo + ", descricao=" + descricao + ", observacao=" + observacao
				+ ", dataCriacao=" + dataCriacao + ", dataInicioVotacao=" + dataInicioVotacao + ", dataFimVotacao="
				+ dataFimVotacao + ", votoList=" + votoList + "]";
	}
}
