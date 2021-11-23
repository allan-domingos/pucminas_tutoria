package br.com.mineradora.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

import org.locationtech.jts.geom.Geometry;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "INCLINOMETRO")
public class Inclinometro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INCLINOMETRO_SEQ")
    @SequenceGenerator(sequenceName = "INCLINOMETRO_SEQ", allocationSize = 1, name = "INCLINOMETRO_SEQ")
	@Column(name = "ID")
	private BigInteger id;
	
	@Column(name = "VALOR")
	private BigDecimal valor;
	
	@Column(name = "DATA")
	private LocalDateTime data;
	
	@Column(name = "POINT", columnDefinition = "geometry" ) 
	private Geometry geometry;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = Sensor.class)
	private Sensor sensor;
	
}
