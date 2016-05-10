package br.com.rino.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_jogadas")
public class Jogadas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_jogadas")
	private Long codJogadas;
	
	@Column(name="angulo_inicial")
	private Integer anguloInicial;
	
	@Column(name="angulo_final")
	private Integer anguloFinal;
	
	@Column(name="nome_imagem")
	private String nomeImagem;
	
	@Column(name="cod_roleta")
	private Long codRoleta;

	public Long getCodJogadas() {
		return codJogadas;
	}

	public void setCodJogadas(Long codJogadas) {
		this.codJogadas = codJogadas;
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

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public Long getCodRoleta() {
		return codRoleta;
	}

	public void setCodRoleta(Long codRoleta) {
		this.codRoleta = codRoleta;
	}

}
