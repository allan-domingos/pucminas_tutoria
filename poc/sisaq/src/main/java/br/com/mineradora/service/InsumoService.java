package br.com.mineradora.service;

import java.util.List;

import br.com.mineradora.dto.InsumoDTO;

/**
 * 
 * @author Allan - allan.domingos@live.com
 * @since 03 de nov. de 2021
 */
public interface InsumoService {

	public List<InsumoDTO> findAll(); 
	
}
