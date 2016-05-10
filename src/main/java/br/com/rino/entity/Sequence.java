package br.com.rino.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sequence")
public class Sequence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_sequence")
	private Long codSequence;
	
	@Column(name="url")
	private String url;

	public Long getCodSequence() {
		return codSequence;
	}

	public void setCodSequence(Long codSequence) {
		this.codSequence = codSequence;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
