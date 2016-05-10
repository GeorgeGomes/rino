package br.com.rino.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_config_panel")
public class ConfigPanel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_config_panel")
	private Long codConfigPanel;
	
	@Column(name="nome_imagem_init")
	private String nomeImagemInit;
	
	@Column(name="nome_imagem_end")
	private String nomeImagemEnd;
	
	@Column(name="pagina_agradecimento")
	private String paginaAgradecimento;
	
	@Column(name="milisegundos_end")
	private Integer milisegundosEnd;

	public Long getCodConfigPanel() {
		return codConfigPanel;
	}

	public void setCodConfigPanel(Long codConfigPanel) {
		this.codConfigPanel = codConfigPanel;
	}

	public String getNomeImagemInit() {
		return nomeImagemInit;
	}

	public void setNomeImagemInit(String nomeImagemInit) {
		this.nomeImagemInit = nomeImagemInit;
	}

	public String getNomeImagemEnd() {
		return nomeImagemEnd;
	}

	public void setNomeImagemEnd(String nomeImagemEnd) {
		this.nomeImagemEnd = nomeImagemEnd;
	}

	public String getPaginaAgradecimento() {
		return paginaAgradecimento;
	}

	public void setPaginaAgradecimento(String paginaAgradecimento) {
		this.paginaAgradecimento = paginaAgradecimento;
	}

	public Integer getMilisegundosEnd() {
		return milisegundosEnd;
	}

	public void setMilisegundosEnd(Integer milisegundosEnd) {
		this.milisegundosEnd = milisegundosEnd;
	}

}
