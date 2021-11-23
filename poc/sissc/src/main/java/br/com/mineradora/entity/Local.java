package br.com.mineradora.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
@Setter
@Getter
@Entity
@Table(name = "LOCAL")
public class Local implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCAL_SEQ")
    @SequenceGenerator(sequenceName = "LOCAL_SEQ", allocationSize = 1, name = "LOCAL_SEQ")
	@Column(name = "ID")
	private BigInteger id;
	
	@Column(name = "DATA_INCLUSAO")
	private LocalDateTime dataInclusao;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "ENDERECO")
	private String endereco;
	
	@Column(name = "POINT", columnDefinition = "geometry" ) 
	private Geometry geometry; 
	
}
