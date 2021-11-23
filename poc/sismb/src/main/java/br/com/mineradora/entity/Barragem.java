package br.com.mineradora.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "BARRAGEM")
public class Barragem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BARRAGEM_SEQ")
    @SequenceGenerator(sequenceName = "BARRAGEM_SEQ", allocationSize = 1, name = "BARRAGEM_SEQ")
	@Column(name = "ID")
	private BigInteger id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "DATA_INCLUSAO")
	private LocalDateTime dataInclusao;

	@Column(name = "POLYGON", columnDefinition = "geometry" ) 
	private Geometry geometry;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = Sensor.class, mappedBy = "barragem")
	private Set<Sensor> sensores;
	
}
