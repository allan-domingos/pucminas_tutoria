package br.com.mineradora.entity;

import java.io.Serializable;
import java.time.LocalDate;

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
 * @since 10 de abr. de 2021
 */
@Setter
@Getter
@Entity
@Table(name = "Alocacao")
public class Alocacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALOCACAO_SEQ")
    @SequenceGenerator(sequenceName = "ALOCACAO_SEQ", allocationSize = 1, name = "ALOCACAO_SEQ")
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DATA_INCLUSAO")
	private LocalDate dataInclusao;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = Aquisicao.class)
	private Aquisicao aquisicao;

}
