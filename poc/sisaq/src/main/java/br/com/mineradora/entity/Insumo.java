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
import javax.persistence.OneToMany;
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
@Table(name = "INSUMO")
public class Insumo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INSUMO_SEQ")
    @SequenceGenerator(sequenceName = "INSUMO_SEQ", allocationSize = 1, name = "INSUMO_SEQ")
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "DATA_INCLUSAO")
	private LocalDate dataInclusao;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH , mappedBy = "municipio", targetEntity = Solicitacao.class )
	private Set<Solicitacao> solicitacoes;
	
}
