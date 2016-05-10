package br.com.rino.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_roleta")
public class Whell implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cd_roleta")
	private Long codWhell;
	
	@Column(name="ds_fatia")
	private String descricaoFatia;
	
	@Column(name="nome_imagem")
	private String nomeImagem;
	
	@Column(name="premios")
	private Long premios;
	
	@Column(name="vitorias")
	private Integer vitorias;
	
	@Column(name="angulo_inicial")
	private Integer anguloInicial;
	
	@Column(name="angulo_final")
	private Integer anguloFinal;
	
	public Long getCodWhell() {
		return codWhell;
	}
	public void setCodWhell(Long codWhell) {
		this.codWhell = codWhell;
	}
	public String getDescricaoFatia() {
		return descricaoFatia;
	}
	public void setDescricaoFatia(String descricaoFatia) {
		this.descricaoFatia = descricaoFatia;
	}
	public String getNomeImagem() {
		return nomeImagem;
	}
	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}
	public Long getPremios() {
		return premios;
	}
	public void setPremios(Long premios) {
		this.premios = premios;
	}
	public Integer getVitorias() {
		return vitorias;
	}
	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}
	public Integer getAnguloInicial() {
		return anguloInicial;
	}
	public void setAnguloInicial(Integer anguloInicial) {
		this.anguloInicial = anguloInicial;
	}
	public Integer getAnguloFinal() {
		return anguloFinal;
	}
	public void setAnguloFinal(Integer anguloFinal) {
		this.anguloFinal = anguloFinal;
	}
	
}
