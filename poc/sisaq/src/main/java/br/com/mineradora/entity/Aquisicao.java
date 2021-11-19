package br.com.mineradora.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @since 10 de abr. de 2021
 */
@Setter
@Getter
@Entity
@Table(name = "AQUISICAO")
public class Aquisicao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AQUISICAO_SEQ")
    @SequenceGenerator(sequenceName = "AQUISICAO_SEQ", allocationSize = 1, name = "AQUISICAO_SEQ")
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "QTD")
	private Long quantidade;
	
	@Column(name = "CNPJ")
	private String cnpj;
	
	@Column(name = "NOTA_FISCAL")
	private String notaFiscal;
	
	@Column(name = "VALOR")
	private BigDecimal valor;
	
	@Column(name = "DATA_INCLUSAO")
	private LocalDateTime dataInclusao;
		
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, targetEntity = Solicitacao.class)
	private Solicitacao solicitacao;
	
}
