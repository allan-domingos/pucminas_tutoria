package br.com.mineradora.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SensorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger id;

	private String nome;

	private LocalDateTime dataInclusao;

	private BarragemDTO barragem;

}
