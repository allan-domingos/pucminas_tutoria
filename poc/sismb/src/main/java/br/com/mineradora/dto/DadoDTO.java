package br.com.mineradora.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DadoDTO {

	private BigInteger id;
	private String nome;
	private BigDecimal valor;
	private LocalDateTime data;
	private Double[] coordenadas;
	
}
