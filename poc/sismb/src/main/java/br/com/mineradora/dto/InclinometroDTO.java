package br.com.mineradora.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import br.com.mineradora.entity.Sensor;
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
public class InclinometroDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger id;

	private BigDecimal valor;

	private LocalDateTime data;

	private Sensor sensor;
	
	private Double[] coordenadas;

}
