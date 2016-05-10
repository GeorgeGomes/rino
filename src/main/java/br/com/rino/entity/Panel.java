package br.com.rino.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_panel")
public class Panel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_panel")
	private Long codPanel;
	@Column(name="ds_panel")
	private String descricaoPanel;
	@Column(name="nome_imagem")
	private String nomeImagem;
	@Column(name="string")
	private String string;
	
	public Long getCodPanel() {
		return codPanel;
	}
	public void setCodPanel(Long codPanel) {
		this.codPanel = codPanel;
	}
	public String getDescricaoPanel() {
		return descricaoPanel;
	}
	public void setDescricaoPanel(String descricaoPanel) {
		this.descricaoPanel = descricaoPanel;
	}
	public String getNomeImagem() {
		return nomeImagem;
	}
	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	
	@Override
	public String toString() {
		return "Panel [codPanel=" + codPanel + ", descricaoPanel=" + descricaoPanel + ", nomeImagem=" + nomeImagem
				+ ", string=" + string + "]";
	}
	
}
