package br.com.mineradora.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 07 de nov. de 2021
 */
@Setter
@Getter
public class PatrimonioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigInteger id;
	
	private LocalDateTime dataInclusao;
	
	private String numero;
	
	private AtivoDTO ativo;
	
}
