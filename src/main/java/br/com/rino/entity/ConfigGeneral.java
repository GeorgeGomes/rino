package br.com.rino.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_config")
public class ConfigGeneral implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5057022397917681240L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_config")
	private Long codConfig;
	
	@Column(name="porta_serial")
	private String portaSerial;
	
	@Column(name="email_host")
	private String emailHost;
	
	@Column(name="email_port")
	private String emailPort;
	
	@Column(name="email_sender")
	private String emailSender;
	
	@Column(name="email_password")
	private String emailPassword;
	
	@Column(name="email_subject")
	private String emailSubject;
	
	@Column(name="email_body")
	private String emailBody;

	public Long getCodConfig() {
		return codConfig;
	}

	public void setCodConfig(Long codConfig) {
		this.codConfig = codConfig;
	}

	public String getPortaSerial() {
		return portaSerial;
	}

	public void setPortaSerial(String portaSerial) {
		this.portaSerial = portaSerial;
	}

	public String getEmailHost() {
		return emailHost;
	}

	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}

	public String getEmailPort() {
		return emailPort;
	}

	public void setEmailPort(String emailPort) {
		this.emailPort = emailPort;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	
}
