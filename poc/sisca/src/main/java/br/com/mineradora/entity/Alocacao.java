package br.com.mineradora.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Setter
@Getter
@Entity
@Table(name = "ALOCACAO")
public class Alocacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALOCACAO_SEQ")
    @SequenceGenerator(sequenceName = "ALOCACAO_SEQ", allocationSize = 1, name = "ALOCACAO_SEQ")
	@Column(name = "ID")
	private BigInteger id;
	
	@Column(name = "DATA_INCLUSAO")
	private LocalDateTime dataInclusao;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = Ativo.class)
	private Ativo ativo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = Local.class)
	private Local local;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = Patrimonio.class)
	private Patrimonio patrimonio;
	
	@Column(name = "AQUISICAO_ID")
	private BigInteger idAquisicao;
	
}
