package br.com.rino.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="tb_photo")
public class Photo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_photo")
	private Long codPhoto;
	
	@Column(name="nome_foto")
	private String nomeFoto;
	
	@Column(name="nome_evento")
	private String nomeEvento;
	
	@Column(name="descricao_evento")
	private String descricaoEvento;
	
	@Column(name="email")
	private String email;
	
	@Column(name="pendente")
	private String pendente;
	
	@Lob
	@Column(name="file", columnDefinition="longblob")
	private byte[] file;

	public Long getCodPhoto() {
		return codPhoto;
	}

	public void setCodPhoto(Long codPhoto) {
		this.codPhoto = codPhoto;
	}

	public String getNomeFoto() {
		return nomeFoto;
	}

	public void setNomeFoto(String nomeFoto) {
		this.nomeFoto = nomeFoto;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPendente() {
		return pendente;
	}

	public void setPendente(String pendente) {
		this.pendente = pendente;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
