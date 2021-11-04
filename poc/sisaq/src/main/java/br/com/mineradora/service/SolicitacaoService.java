package br.com.mineradora.service;

import br.com.mineradora.dto.SolicitacaoDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
public interface SolicitacaoService {

	public SolicitacaoDTO findById(Long id); 
	
}
