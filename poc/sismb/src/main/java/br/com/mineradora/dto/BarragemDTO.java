package br.com.mineradora.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Allan de Lemos Domingos (aldomingos@stefanini.com)
 * @since Nov 21, 2021
 *
 */
@Setter
@Getter
public class BarragemDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger id;

	private String nome;

	private LocalDateTime dataInclusao;
	
	private Double[] coordenadas;

	private List<SensorDTO> sensores;

}
