package br.com.mineradora.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DadoDTO {

	private String nome;
	private BigDecimal Valor;
	private LocalDateTime data;
	
}
