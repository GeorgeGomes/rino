package br.com.rino.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_facebook")
public class Facebook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_facebook")
	private Long codFacebook;
	
	@Column(name="app_id")
	private String appId;
	
	@Column(name="app_secret")
	private String appSecret;
	
	@Column(name="hashtag")
	private String hashtag;
	
	@Column(name="nome_imagem_online")
	private String nomeImagemOnline;
	
	@Column(name="nome_imagem_offline")
	private String nomeImagemOffline;
	
	@Column(name="mensagem_online")
	private String mensagemOnline;
	
	@Column(name="mensagem_offline")
	private String mensagemOffline;
	
	@Column(name="nome_imagem_agradecimento")
	private String nomeImagemAgradecimento;
	
	@Column(name="string_led")
	private String stringLed;
	
	@Column(name="string_flash")
	private String stringFlash;
	
	@Column(name="online")
	private String online;
	
	@Column(name="logout_facebook")
	private String logoutFacebook;

	public Long getCodFacebook() {
		return codFacebook;
	}

	public void setCodFacebook(Long codFacebook) {
		this.codFacebook = codFacebook;
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

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public String getNomeImagemOnline() {
		return nomeImagemOnline;
	}

	public void setNomeImagemOnline(String nomeImagemOnline) {
		this.nomeImagemOnline = nomeImagemOnline;
	}

	public String getNomeImagemOffline() {
		return nomeImagemOffline;
	}

	public void setNomeImagemOffline(String nomeImagemOffline) {
		this.nomeImagemOffline = nomeImagemOffline;
	}

	public String getMensagemOnline() {
		return mensagemOnline;
	}

	public void setMensagemOnline(String mensagemOnline) {
		this.mensagemOnline = mensagemOnline;
	}

	public String getMensagemOffline() {
		return mensagemOffline;
	}

	public void setMensagemOffline(String mensagemOffline) {
		this.mensagemOffline = mensagemOffline;
	}

	public String getNomeImagemAgradecimento() {
		return nomeImagemAgradecimento;
	}

	public void setNomeImagemAgradecimento(String nomeImagemAgradecimento) {
		this.nomeImagemAgradecimento = nomeImagemAgradecimento;
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

	@Override
	public String toString() {
		return "Facebook [codFacebook=" + codFacebook + ", appId=" + appId + ", appSecret=" + appSecret + ", hashtag="
				+ hashtag + ", nomeImagemOnline=" + nomeImagemOnline + ", nomeImagemOffline=" + nomeImagemOffline
				+ ", mensagemOnline=" + mensagemOnline + ", mensagemOffline=" + mensagemOffline
				+ ", nomeImagemAgradecimento=" + nomeImagemAgradecimento + ", stringLed=" + stringLed + ", stringFlash="
				+ stringFlash + ", online=" + online + ", logoutFacebook=" + logoutFacebook + "]";
	}

}
