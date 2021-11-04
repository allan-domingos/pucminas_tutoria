package br.com.mineradora.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "SOLICITACAO")
public class Solicitacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOLICITACAO_SEQ")
    @SequenceGenerator(sequenceName = "SOLICITACAO_SEQ", allocationSize = 1, name = "SOLICITACAO_SEQ")
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "QTD")
	private Long quantidade;
	
	@Column(name = "DATA_INCLUSAO")
	private LocalDate dataInclusao;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = Insumo.class)
	private Insumo insumo;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH , mappedBy = "municipio", targetEntity = Aquisicao.class )
	private Set<Aquisicao> aquisicoes;

}