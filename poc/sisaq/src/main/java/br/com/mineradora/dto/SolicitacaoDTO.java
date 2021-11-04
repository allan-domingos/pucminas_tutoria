package br.com.mineradora.dto;

import java.io.Serializable;
import java.time.LocalDate;
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
	
	private Long id;
	
	private Long quantidade;
	
	private LocalDate dataInclusao;
	
	private InsumoDTO insumo;
	
	private Set<AquisicaoDTO> aquisicoes;

}
