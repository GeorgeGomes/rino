package br.com.rino.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_config_carousel")
public class ConfigCarousel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_config_carousel")
	private Long codConfigCarousel;
	
	@Column(name="seconds_transition")
	private String secondsTransition;
	
	public Long getCodConfigCarousel() {
		return codConfigCarousel;
	}

	public void setCodConfigCarousel(Long codConfigCarousel) {
		this.codConfigCarousel = codConfigCarousel;
	}

	public String getSecondsTransition() {
		return secondsTransition;
	}

	public void setSecondsTransition(String secondsTransition) {
		this.secondsTransition = secondsTransition;
	}

}
