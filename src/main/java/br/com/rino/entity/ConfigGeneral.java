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

}
