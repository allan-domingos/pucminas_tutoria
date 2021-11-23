package br.com.mineradora.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Setter
@Getter
public class SolicitacaoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigInteger id;
	
	private BigInteger quantidade;
	
	private String nome;
	
	private String descricao;
	
	private LocalDateTime dataInclusao;
	
	private BigInteger idAtivo;
	
	private Set<AquisicaoDTO> aquisicoes;

}
