package br.com.mineradora.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
@Setter
@Getter
public class AquisicaoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigInteger id;
	
	private BigInteger quantidade;
	
	private LocalDateTime dataInclusao;
	
	private String cnpj;
	
	private String notaFiscal;
	
	private BigDecimal valor;
		
	private SolicitacaoDTO solicitacao;
	
}
