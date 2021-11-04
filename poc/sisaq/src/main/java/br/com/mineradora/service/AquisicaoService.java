package br.com.mineradora.service;

import br.com.mineradora.dto.AquisicaoDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
public interface AquisicaoService {

	public AquisicaoDTO findById(Long id); 
	
}
