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

@Setter
@Getter
@Entity
@Table(name = "SENSOR")
public class Sensor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SENSOR_SEQ")
    @SequenceGenerator(sequenceName = "SENSOR_SEQ", allocationSize = 1, name = "SENSOR_SEQ")
	@Column(name = "ID")
	private BigInteger id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "DATA_INCLUSAO")
	private LocalDateTime dataInclusao;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = Barragem.class)
	private Barragem barragem;

}
