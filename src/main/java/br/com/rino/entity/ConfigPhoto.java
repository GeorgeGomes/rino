package br.com.rino.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_config_photo")
public class ConfigPhoto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_config_photo")
	private Long codConfigPhoto;
	
	@Column(name="app_id")
	private String appId;
	
	@Column(name="app_secret")
	private String appSecret;
	
	@Column(name="nome_imagem_agradecimento")
	private String nomeImagemAgradecimento;
	
	@Column(name="nome_imagem_offline")
	private String nomeImagemOffline;
	
	@Column(name="nome_mensagem")
	private String mensagemOffline;
	
	@Column(name="string_led")
	private String stringLed;
	
	@Column(name="string_flash")
	private String stringFlash;
	
	@Column(name="online")
	private String online;
	
	@Column(name="logout_facebook")
	private String logoutFacebook;
	
	@Column(name="link_gallery")
	private String linkGallery;
	
	@Column(name="link_transfer")
	private String linkTransfer;
	
	@Column(name="nome_evento")
	private String nomeEvento;

	@Column(name="descricaoEvento")
	private String descricaoEvento;
	
	@Column(name="milisegundos_end")
	private Integer milisegundosEnd;
	
	public Long getCodConfigPhoto() {
		return codConfigPhoto;
	}

	public void setCodConfigPhoto(Long codConfigPhoto) {
		this.codConfigPhoto = codConfigPhoto;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getNomeImagemAgradecimento() {
		return nomeImagemAgradecimento;
	}

	public void setNomeImagemAgradecimento(String nomeImagemAgradecimento) {
		this.nomeImagemAgradecimento = nomeImagemAgradecimento;
	}
	
	public String getNomeImagemOffline() {
		return nomeImagemOffline;
	}

	public void setNomeImagemOffline(String nomeImagemOffline) {
		this.nomeImagemOffline = nomeImagemOffline;
	}

	public String getMensagemOffline() {
		return mensagemOffline;
	}

	public void setMensagemOffline(String mensagemOffline) {
		this.mensagemOffline = mensagemOffline;
	}

	public String getStringLed() {
		return stringLed;
	}

	public void setStringLed(String stringLed) {
		this.stringLed = stringLed;
	}

	public String getStringFlash() {
		return stringFlash;
	}

	public void setStringFlash(String stringFlash) {
		this.stringFlash = stringFlash;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getLogoutFacebook() {
		return logoutFacebook;
	}

	public void setLogoutFacebook(String logoutFacebook) {
		this.logoutFacebook = logoutFacebook;
	}

	public String getLinkGallery() {
		return linkGallery;
	}

	public void setLinkGallery(String linkGallery) {
		this.linkGallery = linkGallery;
	}

	public String getLinkTransfer() {
		return linkTransfer;
	}

	public void setLinkTransfer(String linkTransfer) {
		this.linkTransfer = linkTransfer;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getDescricaoEvento() {
		return descricaoEvento;
	}

	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
	}

	public Integer getMilisegundosEnd() {
		return milisegundosEnd;
	}

	public void setMilisegundosEnd(Integer milisegundosEnd) {
		this.milisegundosEnd = milisegundosEnd;
	}

}