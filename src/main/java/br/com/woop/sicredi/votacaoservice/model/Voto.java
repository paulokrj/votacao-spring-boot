package br.com.woop.sicredi.votacaoservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity()
@Table(name = "voto")
public class Voto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "voto_codigo")
    private Long votoCodigo;

	@Column(name = "associado_codigo")
    private Long associadoCodigo;

	@ManyToOne()
	@JoinColumn(name = "pauta_codigo", referencedColumnName = "pauta_codigo")
	@JsonIgnore
	private Pauta pauta;

	@Column(name = "opcao")
    private Boolean opcao;

	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

	public Voto() {
		super();
	}

	public Voto(Long votoCodigo, Long associadoCodigo, Pauta pauta, Boolean opcao, LocalDateTime dataCriacao) {
		super();
		this.votoCodigo = votoCodigo;
		this.associadoCodigo = associadoCodigo;
		this.pauta = pauta;
		this.opcao = opcao;
		this.dataCriacao = dataCriacao;
	}

	@PrePersist
	private void prePersist() {
		this.dataCriacao = LocalDateTime.now();
	}
	
	public Long getVotoCodigo() {
		return votoCodigo;
	}

	public void setVotoCodigo(Long votoCodigo) {
		this.votoCodigo = votoCodigo;
	}

	public Long getAssociadoCodigo() {
		return associadoCodigo;
	}

	public void setAssociadoCodigo(Long associadoCodigo) {
		this.associadoCodigo = associadoCodigo;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Boolean getOpcao() {
		return opcao;
	}

	public void setOpcao(Boolean opcao) {
		this.opcao = opcao;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public String toString() {
		return "Voto [votoCodigo=" + votoCodigo + ", associadoCodigo=" + associadoCodigo + ", pauta=" + pauta
				+ ", opcao=" + opcao + ", dataCriacao=" + dataCriacao + "]";
	}
}
